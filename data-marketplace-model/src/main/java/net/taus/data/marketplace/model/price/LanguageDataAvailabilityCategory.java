package net.taus.data.marketplace.model.price;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "language_data_availability_category")
@Data
public class LanguageDataAvailabilityCategory {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50)
  private String label;

  @Column(columnDefinition = "int unsigned default 0 not null", name = "higher_bound_percentile")
  private double higherBoundPercentile;

  @Column(columnDefinition = "double unsigned default 0 not null", name = "higher_bound")
  private double higherBound;

  @Column(columnDefinition = "double unsigned default 0 not null", name = "availability_modifier")
  private double availabilityModifier;

  @Column(columnDefinition = "tinyint unsigned")
  private int sort;
}