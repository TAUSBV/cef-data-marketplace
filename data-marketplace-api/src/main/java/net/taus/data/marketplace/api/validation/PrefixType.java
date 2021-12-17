package net.taus.data.marketplace.api.validation;

public enum PrefixType {
  MR("Mr."),
  MS("Ms.");

  private final String name;

  PrefixType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
