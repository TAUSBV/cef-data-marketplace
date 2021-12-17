package net.taus.data.marketplace.model.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import net.taus.data.marketplace.model.SystemException;

/**
 * The pre-defined sizes of documents
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DatasetSize {
  COMPACT("Compact", 0.25),
  MEDIUM("Medium", 0.5),
  LARGE("Large", 0.75),
  FULL("Full", 1);

  private final String description;
  private final double amount;

  DatasetSize(String description, double amount) {
    this.description = description;
    this.amount = amount;
  }

  public static DatasetSize fromAmount(double amount) {
    for (DatasetSize dss : DatasetSize.values()) {
      if (dss.getAmount() == amount)
        return dss;
    }
    throw new SystemException("Not allowed dataset size");
  }
}
