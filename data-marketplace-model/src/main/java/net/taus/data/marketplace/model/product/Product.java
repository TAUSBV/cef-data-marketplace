package net.taus.data.marketplace.model.product;

import lombok.Data;
import net.taus.data.language.api.model.ContentType;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.Language;
import net.taus.data.language.api.model.TranslationDocument;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequest;
import net.taus.data.marketplace.model.support.UUIDUtil;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static javax.persistence.EnumType.STRING;
import static net.taus.data.marketplace.model.product.Product.Type.*;
import static net.taus.data.marketplace.model.product.ProductPart.Type.*;

@Data
@Entity
@Table(name = "products")
public class Product {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 500)
  private String name;
  @Column(length = 5000)
  private String description;
  @Column(length = 1000)
  private String sku;
  private boolean amountVariable;
  private Long totalUnits;
  private Long totalSourceWords;
  private Long totalTargetWords;
  private Double price;
  private Double priceMin;
  private Double priceMax;
  @Enumerated(STRING)
  private Type type;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private List<ProductPart> parts;

  @ManyToOne
  @JoinColumn(name = "matching_data_request_id")
  private MatchingDataRequest matchingDataRequest;

  public Product() {
    this.parts = new ArrayList<>();
  }

  /**
   * All-arg constructor used to create a MATCHING_DOCUMENT product
   *
   * @param name             The product's name
   * @param description      The product's description
   * @param sku              The product's unique identifier
   * @param sourceLanguage   The product's source language
   * @param targetLanguage   The product's target language
   * @param domain           The optional product's domain (may be null)
   * @param totalUnits       The total units of the product
   * @param totalSourceWords The total source words of the product
   * @param totalTargetWords The total target words of the product
   * @param price            The product's full price (no amount considered)
   * @param unitPriceMin     The minimum unit price (aka price per word) of all the participating documents in the MD results
   * @param unitPriceMax     The maximum unit price (aka price per word) of all the participating documents in the MD results
   */
  public Product(final String name,
                 final String description,
                 final String sku,
                 final Language sourceLanguage,
                 final Language targetLanguage,
                 final Domain domain,
                 final long totalUnits,
                 final long totalSourceWords,
                 final long totalTargetWords,
                 final double price,
                 final double unitPriceMin,
                 final double unitPriceMax) {
    this();

    this.setName(name);
    this.setDescription(description);
    this.setSku(sku);
    this.setCreatedAt(LocalDateTime.now());
    this.setTotalUnits(totalUnits);
    this.setTotalSourceWords(totalSourceWords);
    this.setTotalTargetWords(totalTargetWords);
    this.setPrice(price);
    this.setPriceMin(unitPriceMin);
    this.setPriceMax(unitPriceMax);
    this.addSourceLanguagePart(sourceLanguage);
    this.addTargetLanguagePart(targetLanguage);
    this.addDomainsPart(domain == null ? new HashSet<>() : Set.of(domain));
    this.setAmountVariable(true);
    this.setType(MATCHING_DOCUMENT);
  }

  public static Product create(final TranslationDocument document,
                               final double price,
                               final long totalUnits,
                               final long totalSourceWords,
                               final long totalTargetWords) {

    final Product product = create(Product.generateSku(List.of(document.getUid())),
      document.getSourceLanguage(),
      document.getTargetLanguage(),
      Set.of(document.getDomain()),
      Set.of(document.getContentType()),
      document.getPricePerWord(),
      document.getPricePerWord(),
      price,
      totalUnits,
      totalSourceWords,
      totalTargetWords,
      LocalDateTime.now());
    product.setName(document.getName());
    product.setDescription(document.getDescription());
    product.addDocumentPart(document);
    product.setAmountVariable(true);
    product.setType(SELLER_DOCUMENT);
    return product;
  }

  public static Product create(final String sku,
                               final Language sourceLanguage,
                               final Language targetLanguage,
                               final Set<Domain> domains,
                               final Set<ContentType> contentTypes,
                               final Double priceMin,
                               final Double priceMax,
                               final double price,
                               final long totalUnits,
                               final long totalSourceWords,
                               final long totalTargetWords,
                               final LocalDateTime referenceDateTime) {
    final Product product = new Product();
    product.setName(format("Dataset of %s segments, %s %s words and %s %s words.",
      totalUnits, totalSourceWords, sourceLanguage.getNameAndCountry(), totalTargetWords, targetLanguage.getNameAndCountry()));
    String description = format("%s - %s", sourceLanguage.getNameAndCountry(), targetLanguage.getNameAndCountry());
    if (!domains.isEmpty()) {
      description += domains.stream().map(Domain::getDescription).collect(joining(", ", ", ", ""));
    }
    if (!contentTypes.isEmpty()) {
      description += contentTypes.stream().map(ContentType::getDescription).collect(joining(", ", ", ", ""));
    }
    product.setDescription(description);


    product.setSku(UUIDUtil.toUrlEncoded(UUID.nameUUIDFromBytes(sku.getBytes(UTF_8))));
    product.setCreatedAt(referenceDateTime);
    product.setTotalUnits(totalUnits);
    product.setTotalSourceWords(totalSourceWords);
    product.setTotalTargetWords(totalTargetWords);
    product.setPrice(price);
    product.setPriceMin(priceMin);
    product.setPriceMax(priceMax);
    product.setType(VIRTUAL_DOCUMENT);

    product.addSourceLanguagePart(sourceLanguage);
    product.addTargetLanguagePart(targetLanguage);
    product.addDomainsPart(domains);
    product.addContentTypesPart(contentTypes);

    return product;
  }

  public static String generateSku(List<UUID> uuids) {
    return uuids.stream()
      .sorted()
      .map(UUID::toString)
      .collect(joining());
  }

  public void addPart(final ProductPart part) {
    this.getParts().add(part);
  }

  public Optional<Language> getSourceLanguage() {
    return getLanguagePart(SOURCE_LANGUAGE);
  }

  public Optional<Language> getTargetLanguage() {
    return getLanguagePart(TARGET_LANGUAGE);
  }

  public Optional<UUID> getDocumentUid() {
    return this.getParts().stream().filter(p -> p.getType().equals(DOCUMENT)).findFirst().map(p -> UUID.fromString(p.getTypeId()));
  }

  public List<Domain> getDomains() {
    return this.getParts().stream().filter(p -> p.getType().equals(DOMAIN)).map(p -> new Domain(p.getTypeIdAsLong(), p.getDescription())).collect(toList());
  }

  public void addDocumentPart(final TranslationDocument document) {
    ProductPart part = new ProductPart();
    part.setTypeId(document.getUid().toString());
    part.setDescription(document.getName());
    part.setType(DOCUMENT);
    this.addPart(part);
  }

  private void addContentTypesPart(final Set<ContentType> contentTypes) {
    contentTypes.forEach(d -> {
      var p = new ProductPart();
      p.setTypeId(d.getId().toString());
      p.setDescription(d.getDescription());
      p.setType(CONTENT_TYPE);
      this.addPart(p);
    });
  }

  private void addDomainsPart(final Set<Domain> domains) {
    domains.forEach(d -> {
      var p = new ProductPart();
      p.setTypeId(d.getId().toString());
      p.setDescription(d.getDescription());
      p.setType(DOMAIN);
      this.addPart(p);
    });
  }

  private void addTargetLanguagePart(final Language language) {
    this.addPart(createLanguagePart(language, TARGET_LANGUAGE));
  }

  private void addSourceLanguagePart(final Language language) {
    this.addPart(createLanguagePart(language, SOURCE_LANGUAGE));
  }

  // TODO review how to deal better with the language information.
  private Optional<Language> getLanguagePart(ProductPart.Type type) {
    return getParts().stream().filter(p -> p.getType().equals(type))
      .findFirst().map(p -> {
        final Locale locale = Locale.forLanguageTag(p.getDescription());
        return new Language(p.getTypeIdAsLong(),
          p.getDescription(),
          locale.getLanguage(),
          locale.getDisplayName(),
          locale.getCountry(),
          locale.getDisplayCountry(),
          false);
      });
  }

  private ProductPart createLanguagePart(final Language language, final ProductPart.Type type) {
    ProductPart part = new ProductPart();
    part.setTypeId(language.getId().toString());
    part.setDescription(language.getTag());
    part.setType(type);
    return part;
  }

  public enum Type {
    VIRTUAL_DOCUMENT("VDOC"),
    SELLER_DOCUMENT("SDOC"),
    MATCHING_DOCUMENT("MDOC");
    private final String abbreviation;

    Type(final String abbreviation) {
      this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
      return abbreviation;
    }
  }

}
