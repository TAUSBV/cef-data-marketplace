package net.taus.data.marketplace.api.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A {@link LinkedHashMap} that supports maximum size
 *
 * @param <K>
 * @param <V>
 */
public class MaxSizeLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
  private final int maxSize;

  public MaxSizeLinkedHashMap(int maxSize) {
    this.maxSize = maxSize;
  }

  @Override
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    return size() > maxSize;
  }
}
