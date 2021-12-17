package net.taus.data.marketplace.api.seller;

import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.language.api.model.OwnerSummary;
import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.api.user.UserService;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentPublishedSummary;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReviewRepository;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReviewStatistics;
import net.taus.data.marketplace.model.user.SlugId;
import net.taus.data.marketplace.model.user.User;
import net.taus.data.marketplace.model.user.UserDetail;
import net.taus.data.marketplace.model.user.views.SellerSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/sellers")
public class SellerController extends BaseController {

  private final DataLanguageClient dataLanguageClient;
  private final UserService users;
  private final TranslationDocumentReviewRepository reviews;

  public SellerController(final DataLanguageClient dataLanguageClient,
                          final UserService users,
                          final TranslationDocumentReviewRepository reviews) {
    this.dataLanguageClient = dataLanguageClient;
    this.users = users;
    this.reviews = reviews;
  }

  @GetMapping(value = { "", "/" })
  public ResponseEntity<Page<SellerSummary>> list(@RequestParam(name = "pg", defaultValue = "0") int pageNumber) {
    final Page<OwnerSummary> owners = dataLanguageClient.findOwnersSummary(3, 0, PageRequest.of(pageNumber, 6));
    final List<SellerSummary> list = owners.getContent().stream().flatMap(o -> users.findSellerSummaryByCorporateId(o.getOwnerId()).stream().peek(s -> decorate(s, o))).collect(toList());
    return ResponseEntity.ok(new PageImpl<>(list, owners.getPageable(), owners.getTotalElements()));
  }

  @GetMapping(value = { "/{slugId}", "/{slugId}/" })
  public ResponseEntity<SellerSummary> get(@PathVariable("slugId") final SlugId slugId) {
    return users.findSellerSummaryByUid(slugId.getUid())
      .flatMap(s -> dataLanguageClient.findOwnerSummaryByOwnerId(s.getCorporateId()).map(o -> decorate(s, o)))
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping(value = { "/{slugId}/documents", "/{slugId}/documents/" })
  public ResponseEntity<Page<TranslationDocumentPublishedSummary>> documents(@PathVariable("slugId") final SlugId slugId,
                                                                             @RequestParam(name = "pg", defaultValue = "0") int pageNumber) {
    return users.findByUid(slugId.getUid()).map(u -> {
      final var page = dataLanguageClient.findTranslationDocumentByOwnerUid(u.getCorporateId(),
        "PUBLISHED", null, 1, PageRequest.of(pageNumber, 6));
      final List<TranslationDocumentPublishedSummary> list = page.getContent().stream()
        .map(d -> {
          final TranslationDocumentReviewStatistics reviewStatistics = reviews.getStatistics(d.getUid());
          return new TranslationDocumentPublishedSummary(d, u, reviewStatistics);
        })
        .collect(toList());
      Page<TranslationDocumentPublishedSummary> newPage = new PageImpl<>(list, page.getPageable(), page.getTotalElements());
      return ResponseEntity.ok(newPage);
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping(value = { "/{slugId}/documents/{documentId}", "/{slugId}/documents/{documentId}/" })
  public ResponseEntity<TranslationDocumentPublishedSummary> document(@PathVariable("slugId") final SlugId slugId,
                                                                      @PathVariable("documentId") final UUID documentId) {
    return dataLanguageClient.findTranslationDocumentPublishedByUid(documentId, 30)
      .flatMap(d -> users.findByCorporateId(d.getOwnerId()).map(u -> {
        final TranslationDocumentReviewStatistics reviewStatistics = reviews.getStatistics(d.getUid());
        return new TranslationDocumentPublishedSummary(d, u, reviewStatistics);
      }))
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  private SellerSummary decorate(final SellerSummary sellerSummary, final OwnerSummary ownerSummary) {
    sellerSummary.setContentTypes(ownerSummary.getContentTypes());
    sellerSummary.setDomains(ownerSummary.getDomains());
    sellerSummary.setLanguages(ownerSummary.getLanguages());
    sellerSummary.setDocumentCount(ownerSummary.getDocumentCount());
    final User user = new User(sellerSummary.getCorporateId(), "", new UserDetail("", sellerSummary.getFirstName(), sellerSummary.getLastName(), sellerSummary.getCountry()));
    user.setUid(sellerSummary.getUid());
    if (Objects.nonNull(ownerSummary.getDocuments())) {
      sellerSummary.setDocuments(ownerSummary.getDocuments().stream().map(d -> {
        final TranslationDocumentReviewStatistics reviewStatistics = reviews.getStatistics(d.getUid());
        return new TranslationDocumentPublishedSummary(d, user, reviewStatistics);
      }).collect(toList()));
    }
    return sellerSummary;
  }
}
