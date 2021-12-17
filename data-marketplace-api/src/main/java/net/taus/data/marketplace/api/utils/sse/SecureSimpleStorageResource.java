package net.taus.data.marketplace.api.utils.sse;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.BinaryUtils;
import io.awspring.cloud.core.io.s3.SimpleStorageResource;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.WritableResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.ExecutorServiceAdapter;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SecureSimpleStorageResource extends AbstractResource implements WritableResource {

  private final SimpleStorageResource simpleStorageResource;
  private final AmazonS3 amazonS3;
  private final String bucketName;
  private final String objectName;
  private final TaskExecutor taskExecutor;
  private final String versionId;

  public SecureSimpleStorageResource(final AmazonS3 amazonS3, final String bucketName, final String objectName, final TaskExecutor taskExecutor) {
    this.simpleStorageResource = new SimpleStorageResource(amazonS3, bucketName, objectName, taskExecutor);
    this.amazonS3 = amazonS3;
    this.bucketName = bucketName;
    this.objectName = objectName;
    this.taskExecutor = taskExecutor;
    this.versionId = null;
  }

  public SecureSimpleStorageResource(final AmazonS3 amazonS3, final String bucketName, final String objectName, final TaskExecutor taskExecutor, final String versionId) {
    this.simpleStorageResource = new SimpleStorageResource(amazonS3, bucketName, objectName, taskExecutor);
    this.amazonS3 = amazonS3;
    this.bucketName = bucketName;
    this.objectName = objectName;
    this.taskExecutor = taskExecutor;
    this.versionId = versionId;
  }

  @Override
  public boolean isWritable() {
    return simpleStorageResource.isWritable();
  }

  @Override
  public OutputStream getOutputStream() throws IOException {
    return new SecureSimpleStorageOutputStream();
  }

  @Override
  public String getDescription() {
    return simpleStorageResource.getDescription();
  }

  @Override
  public InputStream getInputStream() throws IOException {
    GetObjectRequest getObjectRequest = new GetObjectRequest(this.bucketName,
      this.objectName);
    if (this.versionId != null) {
      getObjectRequest.setVersionId(this.versionId);
    }
    return this.amazonS3.getObject(getObjectRequest).getObjectContent();
  }

  private class SecureSimpleStorageOutputStream extends OutputStream {

    // The minimum size for a multi part is 5 MB, hence the buffer size of 5 MB
    private static final int BUFFER_SIZE = 1024 * 1024 * 5;

    private final Object monitor = new Object();

    private final CompletionService<UploadPartResult> completionService;

    @SuppressWarnings("FieldMayBeFinal")
    private ByteArrayOutputStream currentOutputStream = new ByteArrayOutputStream(
      BUFFER_SIZE);

    private int partNumberCounter = 1;

    private InitiateMultipartUploadResult multiPartUploadResult;

    SecureSimpleStorageOutputStream() {
      this.completionService = new ExecutorCompletionService<>(
        new ExecutorServiceAdapter(SecureSimpleStorageResource.this.taskExecutor));
    }

    @Override
    public void write(int b) throws IOException {
      synchronized (this.monitor) {
        if (this.currentOutputStream.size() == BUFFER_SIZE) {
          initiateMultiPartIfNeeded();
          this.completionService.submit(new SecureSimpleStorageResource.SecureSimpleStorageOutputStream.UploadPartResultCallable(
            SecureSimpleStorageResource.this.amazonS3,
            this.currentOutputStream.toByteArray(),
            this.currentOutputStream.size(),
            SecureSimpleStorageResource.this.bucketName,
            SecureSimpleStorageResource.this.objectName,
            this.multiPartUploadResult.getUploadId(),
            this.partNumberCounter++, false));
          this.currentOutputStream.reset();
        }
        this.currentOutputStream.write(b);
      }
    }

    @Override
    public void close() throws IOException {
      synchronized (this.monitor) {
        if (this.currentOutputStream == null) {
          return;
        }

        if (isMultiPartUpload()) {
          finishMultiPartUpload();
        }
        else {
          finishSimpleUpload();
        }
      }
    }

    private boolean isMultiPartUpload() {
      return this.multiPartUploadResult != null;
    }

    private void finishSimpleUpload() {
      ObjectMetadata objectMetadata = new ObjectMetadata();
      objectMetadata.setContentLength(this.currentOutputStream.size());

      byte[] content = this.currentOutputStream.toByteArray();
      try {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        String md5Digest = BinaryUtils.toBase64(messageDigest.digest(content));
        objectMetadata.setContentMD5(md5Digest);
      }
      catch (NoSuchAlgorithmException e) {
        throw new IllegalStateException(
          "MessageDigest could not be initialized because it uses an unknown algorithm",
          e);
      }

      SecureSimpleStorageResource.this.amazonS3.putObject(
        SecureSimpleStorageResource.this.bucketName,
        SecureSimpleStorageResource.this.objectName,
        new ByteArrayInputStream(content), objectMetadata);

      // Release the memory early
      this.currentOutputStream = null;
    }

    private void finishMultiPartUpload() throws IOException {
      this.completionService.submit(
        new SecureSimpleStorageResource.SecureSimpleStorageOutputStream.UploadPartResultCallable(SecureSimpleStorageResource.this.amazonS3,
          this.currentOutputStream.toByteArray(),
          this.currentOutputStream.size(),
          SecureSimpleStorageResource.this.bucketName,
          SecureSimpleStorageResource.this.objectName,
          this.multiPartUploadResult.getUploadId(),
          this.partNumberCounter, true));
      try {
        List<PartETag> partETags = getMultiPartsUploadResults();
        SecureSimpleStorageResource.this.amazonS3
          .completeMultipartUpload(new CompleteMultipartUploadRequest(
            this.multiPartUploadResult.getBucketName(),
            this.multiPartUploadResult.getKey(),
            this.multiPartUploadResult.getUploadId(), partETags));
      }
      catch (ExecutionException e) {
        abortMultiPartUpload();
        throw new IOException("Multi part upload failed ", e.getCause());
      }
      catch (InterruptedException e) {
        abortMultiPartUpload();
        Thread.currentThread().interrupt();
      }
      finally {
        this.currentOutputStream = null;
      }
    }

    private void initiateMultiPartIfNeeded() {
      if (this.multiPartUploadResult == null) {
        this.multiPartUploadResult = SecureSimpleStorageResource.this.amazonS3
          .initiateMultipartUpload(new InitiateMultipartUploadRequest(
            SecureSimpleStorageResource.this.bucketName,
            SecureSimpleStorageResource.this.objectName));
      }
    }

    private void abortMultiPartUpload() {
      if (isMultiPartUpload()) {
        SecureSimpleStorageResource.this.amazonS3
          .abortMultipartUpload(new AbortMultipartUploadRequest(
            this.multiPartUploadResult.getBucketName(),
            this.multiPartUploadResult.getKey(),
            this.multiPartUploadResult.getUploadId()));
      }
    }

    private List<PartETag> getMultiPartsUploadResults()
      throws ExecutionException, InterruptedException {
      List<PartETag> result = new ArrayList<>(this.partNumberCounter);
      for (int i = 0; i < this.partNumberCounter; i++) {
        Future<UploadPartResult> uploadPartResultFuture = this.completionService
          .take();
        result.add(uploadPartResultFuture.get().getPartETag());
      }
      return result;
    }

    private final class UploadPartResultCallable
      implements Callable<UploadPartResult> {

      private final AmazonS3 amazonS3;

      private final int contentLength;

      private final int partNumber;

      private final boolean last;

      private final String bucketName;

      private final String key;

      private final String uploadId;

      @SuppressWarnings("FieldMayBeFinal")
      private byte[] content;

      private UploadPartResultCallable(AmazonS3 amazon, byte[] content,
                                       int writtenDataSize, String bucketName, String key, String uploadId,
                                       int partNumber, boolean last) {
        this.amazonS3 = amazon;
        this.content = content;
        this.contentLength = writtenDataSize;
        this.partNumber = partNumber;
        this.last = last;
        this.bucketName = bucketName;
        this.key = key;
        this.uploadId = uploadId;
      }

      @Override
      public UploadPartResult call() throws Exception {
        try {
          return this.amazonS3.uploadPart(new UploadPartRequest()
            .withBucketName(this.bucketName).withKey(this.key)
            .withUploadId(this.uploadId)
            .withInputStream(new ByteArrayInputStream(this.content))
            .withPartNumber(this.partNumber).withLastPart(this.last)
            .withPartSize(this.contentLength));
        }
        finally {
          // Release the memory, as the callable may still live inside the
          // CompletionService which would cause
          // an exhaustive memory usage
          this.content = null;
        }
      }

    }

  }
}
