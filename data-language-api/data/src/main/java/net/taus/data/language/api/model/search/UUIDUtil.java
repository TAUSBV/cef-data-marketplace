package net.taus.data.language.api.model.search;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDUtil {

  public static UUID fromBytes(final byte[] bytes) {
    ByteBuffer bb = ByteBuffer.wrap(bytes);
    return new UUID(bb.getLong(), bb.getLong());
  }

  public static byte[] toBytes(final UUID uuid) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();

  }
}
