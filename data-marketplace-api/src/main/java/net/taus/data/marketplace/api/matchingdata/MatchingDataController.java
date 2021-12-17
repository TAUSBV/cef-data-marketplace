package net.taus.data.marketplace.api.matchingdata;

import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.common.TextParsingException;
import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.api.validation.MatchingDataRequestForm;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequest;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequestEvent;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequestException;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequestRepository;
import net.taus.data.marketplace.model.matchingdata.views.MatchingDataRequestSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodName;

@RestController
@RequestMapping("/matching-data")
@Validated
public class MatchingDataController extends BaseController {

  private final MatchingDataRequestRepository repository;
  private final MatchingDataService service;

  @Autowired
  public MatchingDataController(MatchingDataRequestRepository repository,
                                MatchingDataService service) {
    this.repository = repository;
    this.service = service;
  }

  @GetMapping(value = "/{uid}")
  @ResponseBody
  public ResponseEntity<MatchingDataRequestSummary> read(@PathVariable UUID uid) {
    return repository.findByUid(uid).map(service::decorate).map(ResponseEntity::ok).orElseGet(() -> notFound().build());
  }

  @PostMapping(value = "")
  @ResponseBody
  public ResponseEntity<UUID> create(@Valid @ModelAttribute MatchingDataRequestForm matchingDataRequestForm) throws MatchingDataRequestException, SystemException, IOException {

    // Perform checks for the provided input query. FailFast or get the total rows parsed.
    var totalUnits = service.validateInputQuery(matchingDataRequestForm.getFile(), matchingDataRequestForm.getType());

    // When validations pass persist the MD request
    var mdRequest = new MatchingDataRequest(matchingDataRequestForm.getSourceLanguageId(),
      matchingDataRequestForm.getTargetLanguageId(),
      matchingDataRequestForm.getEmail(),
      matchingDataRequestForm.getOriginalFilename(),
      matchingDataRequestForm.getFileSize(),
      totalUnits,
      matchingDataRequestForm.getType(),
      matchingDataRequestForm.getSampleLanguage(),
      matchingDataRequestForm.getDomainId());
    mdRequest.addEvent(MatchingDataRequestEvent.Type.INFO, "Request created");
    mdRequest = repository.save(mdRequest);

    // Upload files to remote
    service.transfer(matchingDataRequestForm, mdRequest.getUid() + ".zip");
    mdRequest.setStatus(MatchingDataRequest.Status.ANALYZING);
    mdRequest.addEvent(MatchingDataRequestEvent.Type.INFO, "Files uploaded");
    repository.save(mdRequest);

    var uri = fromMethodName(MatchingDataController.class, "read", mdRequest.getUid()).build().toUri();
    return created(uri).body(mdRequest.getUid());
  }

  @GetMapping(value = "/examples/download")
  @ResponseBody
  public ResponseEntity<Resource> downloadExamples() throws IOException {

    File file = ResourceUtils.getFile("classpath:" + MatchingDataService.EXAMPLES_FILE_NAME);
    Path path = Paths.get(file.getAbsolutePath());
    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + MatchingDataService.EXAMPLES_FILE_NAME);

    return ResponseEntity.ok()
      .headers(headers)
      .contentLength(file.length())
      .contentType(MediaType.APPLICATION_OCTET_STREAM)
      .body(resource);
  }

  @ExceptionHandler({ DataProcessingException.class, TextParsingException.class, IOException.class })
  public ResponseEntity<?> handleInputQueryException(Exception ex) {
    return unprocessableEntity().body("There was an error processing the input query: " + (ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage()));
  }

  @ExceptionHandler({ MatchingDataRequestException.class })
  public ResponseEntity<?> handleMatchingDataRequestException(MatchingDataRequestException ex) {
    return unprocessableEntity().body("There was an error handling your request: " + ex.getMessage());
  }

}