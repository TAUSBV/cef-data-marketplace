package net.taus.data.marketplace.model.purchase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "download")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Download {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "BINARY(16)")
  @EqualsAndHashCode.Include
  private UUID uid;

  @OneToOne
  @JoinColumn(name = "item_id")
  private PurchaseItem item;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Enumerated(value = EnumType.STRING)
  @Column(length = 20)
  private Status status;

  public Download() {
    this.uid = UUID.randomUUID();
    this.status = Status.AVAILABLE;
  }

  public Download(final PurchaseItem item) {
    this();
    this.item = item;
  }

  public enum Status {
    AVAILABLE, CONSUMED
  }

  /**
   * Get the download link for a purchase product
   * @param appUiUrl The host of the DMP UI
   * @param uid The unique identifier of the download
   * @return A download-once link for a purchase product
   */
  public static String getUrl(final String appUiUrl, final String uid) {
    return String.format("%s/purchases/downloads/%s", appUiUrl, uid);
  }
}
