package net.taus.data.marketplace.model.matchingdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "matching_data_request_event")
@EntityListeners(AuditingEntityListener.class)
public class MatchingDataRequestEvent {

  @Id
  @Column(insertable = false, updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long id;

  @ManyToOne
  @JoinColumn(name = "matching_data_request_id", referencedColumnName = "id")
  @ToString.Exclude
  @JsonIgnore
  private MatchingDataRequest parent;

  @Enumerated(EnumType.STRING)
  private MatchingDataRequest.Status status;

  @Enumerated(EnumType.STRING)
  private Type type;

  @Column(length = 1000)
  private String description;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public MatchingDataRequestEvent(final MatchingDataRequest matchingDataRequest, final Type type, final String description) {
    this.parent = matchingDataRequest;
    this.status = matchingDataRequest.getStatus();
    this.type = type;
    this.description = description;
  }

  public enum Type {
    INFO, WARNING, ERROR
  }

}
