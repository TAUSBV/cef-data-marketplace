package net.taus.data.marketplace.model.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Optional;

@Data
@Embeddable
public class UserDetail {

  @Column(columnDefinition = "varchar(255)")
  private String email;
  @Column(columnDefinition = "varchar(255)", name = "first_name")
  private String firstName;
  @Column(columnDefinition = "varchar(255)", name = "last_name")
  private String lastName;
  @Column(columnDefinition = "varchar(255)")
  private String country;

  @Column(columnDefinition = "varchar(255)")
  private String address;
  @Column(columnDefinition = "varchar(255)")
  private String city;
  @Column(columnDefinition = "varchar(50)", name = "postal_code")
  private String postalCode;
  private String state;

  @Column(columnDefinition = "varchar(20)", name = "entity_type")
  @Enumerated(EnumType.STRING)
  private EntityType entityType;
  @Column(columnDefinition = "varchar(255)")
  private String company;
  @Column(columnDefinition = "varchar(20)")
  private String vat;

  public UserDetail() {
  }

  public UserDetail(final String email) {
    this.email = email;
  }

  public UserDetail(final String email,
                    final String firstName,
                    final String lastName,
                    final String country) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
  }

  public UserDetail(final String email,
                    final String firstName,
                    final String lastName,
                    final String country,
                    final String address,
                    final String city,
                    final String state,
                    final String postalCode,
                    final EntityType entityType,
                    final String company,
                    final String vat) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.address = address;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.entityType = entityType;
    this.company = company;
    this.vat = vat;
  }

  public enum EntityType {
    PRIVATE, BUSINESS;

    public static Optional<EntityType> get(final String name) {
      EntityType type;
      try {
        type = valueOf(name);
      }
      catch (IllegalArgumentException e) {
        type = null;
      }
      return Optional.ofNullable(type);
    }
  }

}
