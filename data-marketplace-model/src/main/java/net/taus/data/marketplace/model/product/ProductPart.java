package net.taus.data.marketplace.model.product;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "product_parts")
@EntityListeners(AuditingEntityListener.class)
public class ProductPart {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 100)
  private String typeId;
  @Column(length = 500)
  private String description;
  @Enumerated(EnumType.STRING)
  @Column(length = 50)
  private Type type;
  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public Long getTypeIdAsLong() {
    return Long.parseLong(this.getTypeId());
  }

  public String getCode() {
    return String.format("%s%s%s", this.getType(), this.getTypeId(), this.getDescription().replaceAll("-", ""));
  }

  public enum Type {
    SOURCE_LANGUAGE, TARGET_LANGUAGE, DOMAIN, CONTENT_TYPE, DOCUMENT
  }

}
