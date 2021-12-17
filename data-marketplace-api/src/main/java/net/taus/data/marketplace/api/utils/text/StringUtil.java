package net.taus.data.marketplace.api.utils.text;

public class StringUtil {

  public static String mask(final String text) {
    return text.replaceAll("(^[^@]{3}|(?!^)\\G)[^@]", "$1*");
  }
}
