package net.taus.data.marketplace.model.user;

import java.util.UUID;

public class SlugId {

  private final UUID uid;
  private final String firstName;
  private final String lastName;

  public SlugId(final UUID uid, final String firstName, final String lastName) {
    this.uid = uid;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public UUID getUid() {
    return uid;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}
