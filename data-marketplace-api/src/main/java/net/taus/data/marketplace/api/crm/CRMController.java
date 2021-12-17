package net.taus.data.marketplace.api.crm;

import net.taus.data.marketplace.api.base.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/crm")
public class CRMController extends BaseController {

  private final CRMService crmService;

  public CRMController(final CRMService crmService) {
    this.crmService = crmService;
  }

  @GetMapping(value = { "/form/{formId}", "/form/{formId}/" })
  public ResponseEntity<String> get(@Validated @PathVariable("formId") @NotNull(message = "formId is mandatory") final String formId) {
    var crmResponse = crmService.getForm(formId);
    return ResponseEntity.ok(crmResponse.body());
  }
}
