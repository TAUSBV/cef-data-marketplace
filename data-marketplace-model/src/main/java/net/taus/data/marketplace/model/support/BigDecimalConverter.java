package net.taus.data.marketplace.model.support;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

/**
 * TODO we should generally avoid this and straight up use {@link BigDecimal} for all monetary values
 */
public class BigDecimalConverter {

  /**
   * Rounds given double value to 2 decimal places. Uses HALF_EVEN rounding mode.
   *
   * @param doubleValue the value we want to round
   * @return the rounded value
   */
  public static double round(double doubleValue) {
    return round(doubleValue, 2);
  }

  /**
   * Rounds given double value to N decimal places. Uses HALF_EVEN rounding mode.
   *
   * @param doubleValue the value we want to round
   * @param scale       How many decimal places the rounded double will have
   * @return the rounded value
   */
  public static double round(double doubleValue, int scale) {
    return BigDecimal.valueOf(doubleValue).setScale(scale, HALF_EVEN).doubleValue();
  }
}
