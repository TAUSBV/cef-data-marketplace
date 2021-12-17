package net.taus.data.marketplace.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class User {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "BINARY(16)")
  @EqualsAndHashCode.Include
  private UUID uid;

  @Column(columnDefinition = "varchar(10)")
  private String prefix;
  @Column(columnDefinition = "bigint", name = "corporate_id")
  private Long corporateId;

  @Column(name = "payment_term_consent")
  private boolean paymentTermConsent;
  @Column(name = "terms_of_service_consent")
  private boolean termsOfServiceConsent;
  @Column(name = "privacy_policy_consent")
  private boolean privacyPolicyConsent;
  @Column(name = "request_terms_agreement")
  private boolean requestTermsAgreement;
  @Column(name = "payment_profile_valid")
  private boolean paymentProfileValid;
  private boolean verified;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Embedded
  private UserDetail details;

  public User() {
    this.uid = UUID.randomUUID();
  }

  public User(final Long corporateId, final String prefix, final UserDetail details) {
    this();
    this.corporateId = corporateId;
    this.details = details;
    this.prefix = prefix;
  }

  public User(final Long corporateId, final String prefix, final UserDetail details, final boolean requestTermsAgreement) {
    this(corporateId, prefix, details);
    this.requestTermsAgreement = requestTermsAgreement;
  }

  public User setRequestTermsAgreement(final boolean requestTermsAgreement) {
    this.requestTermsAgreement = requestTermsAgreement;
    return this;
  }

  public String getEmail() {
    return Optional.ofNullable(this.details).map(UserDetail::getEmail).orElse(null);
  }

  public String getFirstName() {
    return Optional.ofNullable(this.details).map(UserDetail::getFirstName).orElse(null);
  }

  public String getLastName() {
    return Optional.ofNullable(this.details).map(UserDetail::getLastName).orElse(null);
  }
}
