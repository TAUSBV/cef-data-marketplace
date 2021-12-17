package net.taus.data.marketplace.model.support;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

public class UUIDUtil {

  public static UUID fromBytes(byte[] bytes) {
    ByteBuffer bb = ByteBuffer.wrap(bytes);
    return new UUID(bb.getLong(), bb.getLong());
  }

  public static byte[] toBytes(UUID uuid) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();
  }

  public static String toUrlEncoded(UUID uuid) {
    return Base64.getUrlEncoder().encodeToString(toBytes(uuid)).split("=")[0];
  }

  public static UUID fromUrlEncoded(final String uuid) {
    byte[] bytes = Base64.getUrlDecoder().decode(uuid + "==");
    return fromBytes(bytes);
  }
}
