package net.taus.data.language.api.model;

import java.util.Collections;
import java.util.List;

public class PageById<T> {

  private final List<T> content;
  private final long number;
  private final long total;
  private final long size;

  public PageById(final List<T> content, final long number, final long size, final long total) {
    this.content = content;
    this.number = number;
    this.size = size;
    this.total = total;
  }

  public static <T> PageById<T> empty() {
    return new PageById<>(Collections.emptyList(), 0, 0, 0);
  }

  public List<T> getContent() {
    return content;
  }

  public long getTotal() {
    return total;
  }

  public long getTotalElements() {
    return this.getContent().size();
  }

  public long getNumber() {
    return number;
  }

  public long getSize() {
    return size;
  }

  public boolean isLast() {
    return getNumber() + getSize() >= getTotal();
  }

  public boolean isFirst() {
    return getNumber() == 0L;
  }
}
