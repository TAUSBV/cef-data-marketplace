package net.taus.data.marketplace.model.purchase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.taus.data.marketplace.model.user.UserDetail;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "buyer")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Buyer {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "BINARY(16)")
  @EqualsAndHashCode.Include
  private UUID uid;

  // TODO change to termsOfUse
  @Column(name = "terms_of_service_consent")
  private boolean termsOfServiceConsent;

  @Column(name = "privacy_policy_consent")
  private boolean privacyPolicyConsent;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Embedded
  private UserDetail details;

  public Buyer() {
    this.uid = UUID.randomUUID();
  }

  public Buyer(final UserDetail details, final boolean termsOfServiceConsent, final boolean privacyPolicyConsent) {
    this();
    this.details = details;
    this.termsOfServiceConsent = termsOfServiceConsent;
    this.privacyPolicyConsent = privacyPolicyConsent;
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

  public String getFullName() {
    return String.format("%s %s", this.getFirstName(), this.getLastName());
  }

  public String getFullAddress() {
    return Optional.ofNullable(this.details)
      .map(d -> String.format("%s %s %s %s", d.getAddress(), d.getCity(), d.getPostalCode(), d.getCountry()))
      .orElse(null);
  }
}
