package net.taus.data.marketplace.api.security;

import java.util.UUID;

public class UserSession {

  private final Long corporateId;
  private final String corporateSessionId;
  private UUID userUID;
  private boolean requestTermsAgreement;
  private long lifeTime;


  public UserSession(final UUID userUID, final Long corporateId, final String corporateSessionId) {
    this.userUID = userUID;
    this.corporateId = corporateId;
    this.corporateSessionId = corporateSessionId;
  }

  public UserSession(final Long corporateId, final String corporateSessionId) {
    this.corporateId = corporateId;
    this.corporateSessionId = corporateSessionId;
  }

  public UUID getUserUid() {
    return userUID;
  }

  public void setUserUid(final UUID userUID) {
    this.userUID = userUID;
  }

  public String getCorporateSessionId() {
    return corporateSessionId;
  }

  public Long getCorporateId() {
    return corporateId;
  }

  public boolean isRequestTermsAgreement() {
    return requestTermsAgreement;
  }

  public void setRequestTermsAgreement(final boolean requestTermsAgreement) {
    this.requestTermsAgreement = requestTermsAgreement;
  }

  public long getLifeTime() {
    return lifeTime;
  }

  public UserSession setLifeTime(final long lifeTime) {
    this.lifeTime = lifeTime;
    return this;
  }

}