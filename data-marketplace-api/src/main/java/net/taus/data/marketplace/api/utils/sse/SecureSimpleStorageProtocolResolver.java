package net.taus.data.marketplace.api.utils.sse;

import com.amazonaws.services.s3.AmazonS3;
import io.awspring.cloud.core.io.s3.AmazonS3ProxyFactory;
import io.awspring.cloud.core.support.documentation.RuntimeUse;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

public class SecureSimpleStorageProtocolResolver implements ProtocolResolver {

  private final AmazonS3 amazonS3;

  /**
   * <b>IMPORTANT:</b> If a task executor is set with an unbounded queue there will be a
   * huge memory consumption. The reason is that each multipart of 5MB will be put in
   * the queue to be uploaded. Therefore a bounded queue is recommended.
   */
  private TaskExecutor taskExecutor;

  public SecureSimpleStorageProtocolResolver(AmazonS3 amazonS3) {
    this.amazonS3 = AmazonS3ProxyFactory.createProxy(amazonS3);
    this.taskExecutor = new SyncTaskExecutor();
  }

  @RuntimeUse
  public void setTaskExecutor(TaskExecutor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }

  @Override
  public Resource resolve(String location, ResourceLoader resourceLoader) {
    final var prefix = "ss3://";
    if (location.toLowerCase().startsWith(prefix)) {
      location = location.replace(prefix, "s3://");
      return new SecureSimpleStorageResource(this.amazonS3,
        SecureSimpleStorageNameUtils.getBucketNameFromLocation(location),
        SecureSimpleStorageNameUtils.getObjectNameFromLocation(location),
        this.taskExecutor,
        SecureSimpleStorageNameUtils.getVersionIdFromLocation(location));
    }
    else {
      return null;
    }
  }

  public AmazonS3 getAmazonS3() {
    return this.amazonS3;
  }

}
