package net.taus.data.marketplace.model.user.views;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SellerNameSummary {

  @EqualsAndHashCode.Include
  private final UUID uid;
  private final String firstName;
  private final String lastName;

  public String getFullName() {
    return String.format("%s %s", firstName, lastName);
  }

}
