package net.taus.data.marketplace.api.purchase.services;

import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.language.api.model.*;
import net.taus.data.marketplace.api.matchingdata.MatchingDataService;
import net.taus.data.marketplace.api.user.UserService;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequest;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequestRepository;
import net.taus.data.marketplace.model.product.Product;
import net.taus.data.marketplace.model.purchase.PurchaseItem;
import net.taus.data.marketplace.model.purchase.PurchaseItemSellerShare;
import net.taus.data.marketplace.model.support.UUIDSerializer;
import net.taus.data.marketplace.model.support.UUIDUtil;
import net.taus.data.marketplace.model.user.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static net.taus.data.language.api.model.TranslationUnitUnicity.ORIGINAL_ONlY;

public class ProductService {

  private final UserService users;
  private final DataLanguageClient dataLanguageClient;
  private final MatchingDataService matchingDataService;
  private final MatchingDataRequestRepository matchingDataRequestRepository;
  private final UUIDSerializer uuidSerializer;

  public ProductService(final UserService users,
                        final DataLanguageClient dataLanguageClient,
                        final MatchingDataService matchingDataService,
                        final MatchingDataRequestRepository matchingDataRequestRepository, final UUIDSerializer uuidSerializer) {
    this.users = users;
    this.dataLanguageClient = dataLanguageClient;
    this.matchingDataService = matchingDataService;
    this.matchingDataRequestRepository = matchingDataRequestRepository;
    this.uuidSerializer = uuidSerializer;
  }

  public PurchaseItem createPurchaseItem(final UUID documentUid) {
    return dataLanguageClient.findTranslationDocumentByUid(documentUid)
      .flatMap(d -> users.findByCorporateId(d.getOwnerId())
        .map(u -> PurchaseItemSellerShare.fromTranslationDocument(d, u))
        .map(share -> {
          Product p = Product.create(d, share.getPrice(), share.getTotalUnits(), share.getTotalSourceWords(), share.getTotalTargetWords());
          return new PurchaseItem(p, List.of(share));
        }))
      .orElseThrow(() -> new BusinessException("Product not found."));
  }

  public PurchaseItem createPurchaseItem(final Long sourceLanguageId,
                                         final Long targetLanguageId,
                                         final Long[] domainIds,
                                         final Long[] contentTypeIds,
                                         final UUID[] sellerUids,
                                         final Double priceMin,
                                         final Double priceMax,
                                         final LocalDateTime referenceDateTime) {
    final List<Language> languages = dataLanguageClient.getLanguages();
    final Language sourceLanguage = languages.stream().filter(l -> l.getId().equals(sourceLanguageId)).findFirst().orElseThrow(() -> new BusinessException(format("Invalid source language %s", sourceLanguageId)));
    final Language targetLanguage = languages.stream().filter(l -> l.getId().equals(targetLanguageId)).findFirst().orElseThrow(() -> new BusinessException(format("Invalid target language %s", sourceLanguageId)));

    final var domains = Optional.ofNullable(domainIds)
      .map(Arrays::asList)
      .map(ids -> {
        final var all = dataLanguageClient.getDomains().stream().filter(d -> ids.contains(d.getId())).collect(toList());
        if (all.size() != ids.size()) {
          final List<Long> nonexistent = new ArrayList<>(ids);
          nonexistent.removeAll(all.stream().map(Domain::getId).collect(toList()));
          throw new BusinessException(format("Invalid domains %s", nonexistent.stream().map(String::valueOf).collect(joining(", "))));
        }
        return all;
      }).orElse(List.of());

    final var contentTypes = Optional.ofNullable(contentTypeIds)
      .map(Arrays::asList)
      .map(ids -> {
        final var all = dataLanguageClient.getContentTypes().stream().filter(d -> ids.contains(d.getId())).collect(toList());
        if (all.size() != ids.size()) {
          final List<Long> nonexistent = new ArrayList<>(ids);
          nonexistent.removeAll(all.stream().map(ContentType::getId).collect(toList()));
          throw new BusinessException(format("Invalid content types %s", nonexistent.stream().map(String::valueOf).collect(joining(", "))));
        }
        return all;
      }).orElse(List.of());

    final var sellers = Optional.ofNullable(sellerUids).map(ids -> users.findAllByUidIn(Arrays.asList(ids)))
      .map(users -> {
        if (sellerUids.length != users.size()) {
          final String nonexistent = Arrays.stream(sellerUids).filter(id -> users.stream().noneMatch(u -> u.getUid().equals(id))).map(String::valueOf).collect(joining(", "));
          throw new BusinessException(format("Invalid sellers %s", nonexistent));
        }
        return users;
      }).orElse(List.of());

    if (Objects.nonNull(priceMin) && Objects.isNull(priceMax) || Objects.isNull(priceMin) && Objects.nonNull(priceMax)) {
      throw new BusinessException("Is not accepted only one of price minimum or price maximum, both or none must provided");
    }

    Long[] ownerIds = sellers.stream().mapToLong(User::getCorporateId).boxed().toArray(Long[]::new);
    if (ownerIds.length == 0) {
      ownerIds = null;
    }
    final LanguageDataParams params = LanguageDataParams.builder()
      .withSourceLanguageId(sourceLanguageId)
      .withTargetLanguageId(targetLanguageId)
      .withDomainIds(domainIds)
      .withContentTypeIds(contentTypeIds)
      .withOwnerIds(ownerIds)
      .withPriceMin(priceMin)
      .withPriceMax(priceMax)
      .withReferenceDateTime(referenceDateTime)
      .withUnitUnicity(ORIGINAL_ONlY)
      .build();

    final List<OwnerDocumentStatisticsSummary> ownerDocumentStatisticsList = dataLanguageClient.findOwnerDocumentStatisticsSummaryByLanguageDataParamsGroupByOwnerId(params);
    if (ownerDocumentStatisticsList.isEmpty()) {
      throw new BusinessException("Invalid Product");
    }
    final List<PurchaseItemSellerShare> shares = ownerDocumentStatisticsList.stream()
      .flatMap(o -> o.getDocuments().stream().map(d ->
        new PurchaseItemSellerShare(users.findByCorporateId(o.getOwnerId()).orElseThrow(),
          d.getDocumentUid(),
          d.getDocumentName(),
          d.getPrice(),
          d.getTotalUnits(),
          d.getTotalSourceWords(),
          d.getTotalTargetWords())))
      .collect(toList());

    String sku = Product.generateSku(shares.stream().map(PurchaseItemSellerShare::getDocumentUid).collect(toList()));

    final long totalUnits = shares.stream().mapToLong(PurchaseItemSellerShare::getTotalUnits).sum();
    final long totalSourceWords = shares.stream().mapToLong(PurchaseItemSellerShare::getTotalSourceWords).sum();
    final long totalTargetWords = shares.stream().mapToLong(PurchaseItemSellerShare::getTotalTargetWords).sum();
    final double price = shares.stream().mapToDouble(PurchaseItemSellerShare::getPrice).sum();

    Product product = Product.create(sku, sourceLanguage, targetLanguage, new HashSet<>(domains), new HashSet<>(contentTypes), priceMin, priceMax, price, totalUnits, totalSourceWords, totalTargetWords, referenceDateTime);
    return new PurchaseItem(product, shares);
  }

  /**
   * Calculates all necessary information in order to create a product based on a {@link net.taus.data.marketplace.model.matchingdata.MatchingDataRequest}
   *
   * @param matchingDataRequestUid The request's UUID
   * @param amount                 The amount percentage (ex. 0.25) we want to purchase
   * @return a product with appropriate seller shares based on a {@link net.taus.data.marketplace.model.matchingdata.MatchingDataRequest}
   */
  public PurchaseItem createPurchaseItem(final UUID matchingDataRequestUid, double amount) {
    // Get the request and it's result if available
    var matchingDataRequest = matchingDataRequestRepository.findByUid(matchingDataRequestUid).orElseThrow(() -> new BusinessException("Could not find your matching data request"));
    var optionalMatchingDataResult = matchingDataService.getMatchingDataResult(matchingDataRequest);
    if (optionalMatchingDataResult.isEmpty())
      throw new BusinessException("Could not find the Matching Data results for your request");
    var matchingDataResult = optionalMatchingDataResult.get();

    // Get the source/target languages and the domain from the data language API
    final List<Language> languages = dataLanguageClient.getLanguages();
    final Language sourceLanguage = languages.stream().filter(l -> l.getId().equals(matchingDataRequest.getSourceLanguageId())).findFirst().orElseThrow(() -> new BusinessException(format("Invalid source language %s", matchingDataRequest.getSourceLanguageId())));
    final Language targetLanguage = languages.stream().filter(l -> l.getId().equals(matchingDataRequest.getTargetLanguageId())).findFirst().orElseThrow(() -> new BusinessException(format("Invalid target language %s", matchingDataRequest.getTargetLanguageId())));
    Domain domain = null;
    if (matchingDataRequest.getDomainId() != null)
      domain = dataLanguageClient.getDomains().stream().filter(d -> d.getId().equals(matchingDataRequest.getDomainId())).findFirst().orElseThrow(() -> new BusinessException(format("Invalid domain %s", matchingDataRequest.getDomainId())));

    // The seller shares based on the amount
    final List<PurchaseItemSellerShare> shares = matchingDataService.getPurchaseItemSellerShares(matchingDataResult, amount);

    // The total product price as if it was fully purchased
    var totalPrice = matchingDataService.getTotalPrice(matchingDataResult, 1);

    // The minimum and maximum unit prices are calculated based on the individual seller documents
    final var unitPriceMin = shares.stream().mapToDouble(PurchaseItemSellerShare::getUnitPrice).min().orElse(0);
    final var unitPriceMax = shares.stream().mapToDouble(PurchaseItemSellerShare::getUnitPrice).max().orElse(0);


    // Define the product's name, description and SKU
    String name = format("Matching Data result set for request %s", uuidSerializer.convert(matchingDataRequestUid));
    String description = format("%s - %s", sourceLanguage.getNameAndCountry(), targetLanguage.getNameAndCountry());
    if (domain != null) {
      description += " - " + domain.getDescription();
    }
    String sku = UUIDUtil.toUrlEncoded(UUID.nameUUIDFromBytes((matchingDataRequestUid.toString()).getBytes(UTF_8)));

    // Create a new product based on the parameters calculated above
    var product = new Product(name,
      description,
      sku,
      sourceLanguage,
      targetLanguage,
      domain,
      matchingDataResult.getTotalSegmentCount(),
      matchingDataResult.getTotalSourceWordCount(),
      matchingDataResult.getTotalTargetWordCount(),
      totalPrice,
      unitPriceMin,
      unitPriceMax);

    // Set the matchingDataRequest reference that may be used if we want to change
    // the amount of a purchase item referencing this product in order to properly recalculate seller shares
    // and when we want to download a purchase item
    product.setMatchingDataRequest(matchingDataRequest);

    return new PurchaseItem(product, shares, amount);
  }

  public List<PurchaseItemSellerShare> getMatchingDataSellerShares(@NotNull final MatchingDataRequest matchingDataRequest, double amount) {
    // Get the request and it's result if available
    var optionalMatchingDataResult = matchingDataService.getMatchingDataResult(matchingDataRequest);
    if (optionalMatchingDataResult.isEmpty())
      throw new BusinessException("Could not find the Matching Data results for your request");
    var matchingDataResult = optionalMatchingDataResult.get();

    // The product seller shares based on the amount
    return matchingDataService.getPurchaseItemSellerShares(matchingDataResult, amount);
  }

  public void updatePurchaseItem(PurchaseItem purchaseItem, double amount) {
    List<PurchaseItemSellerShare> newShares;
    switch (purchaseItem.getProduct().getType()) {
      // Seller docs should have one and only one share
      case SELLER_DOCUMENT -> newShares = List.of(PurchaseItemSellerShare
        .fromSellerDocumentProduct(purchaseItem.getShares().stream().findFirst()
            .orElseThrow(() -> new SystemException("Could not find single share for purchase item of seller document")),
          purchaseItem.getProduct(),
          amount)
      );
      // Virtual docs do not allow changing the amount
      case VIRTUAL_DOCUMENT -> throw new BusinessException("This product does not allow purchase of subsets");
      // For matching data docs we calculate shares on the fly based on the MD engine results and the amount threshold
      case MATCHING_DOCUMENT -> newShares = getMatchingDataSellerShares(purchaseItem.getProduct().getMatchingDataRequest(), amount);
      // This should not happen:P
      default -> throw new SystemException("Unknown product type: " + purchaseItem.getProduct().getType());
    }

    purchaseItem.setAmount(amount);
    purchaseItem.setShares(newShares);
  }

}
