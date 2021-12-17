package net.taus.data.marketplace.model.support;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class TimeConverter {

  public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
    if (timestamp == null)
      return null;
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId());
  }

}
