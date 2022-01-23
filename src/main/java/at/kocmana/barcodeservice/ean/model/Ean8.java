package at.kocmana.barcodeservice.ean.model;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public record Ean8(String barcode) {

  private static final String EAN8_REGEX = "[0-9]{7}";
  private static final Predicate<String> VALID_EAN8 = Pattern.compile(EAN8_REGEX).asMatchPredicate();

  public Ean8 {

    if (isNull(barcode) || !VALID_EAN8.test(barcode)) {
      var message = String.format("Barcode \"%s\" is invalid for EAN8. Must adhere to the pattern: \"%s\".",
              barcode, EAN8_REGEX);
      throw new IllegalArgumentException(message);
    }
  }

}
