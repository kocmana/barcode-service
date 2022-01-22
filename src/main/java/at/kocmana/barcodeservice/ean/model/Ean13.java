package at.kocmana.barcodeservice.ean.model;

import static java.util.Objects.isNull;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public record Ean13(String barcode) {

  private static final String EAN13_REGEX = "[0-9]{12}";
  private static final Predicate<String> VALID_EAN13 = Pattern.compile(EAN13_REGEX).asMatchPredicate();

  public Ean13 {

    if (isNull(barcode) || !VALID_EAN13.test(barcode)) {
      var message = String.format("Barcode \"%s\" is invalid for EAN13. Must adhere to the pattern: \"%s\".",
          barcode, EAN13_REGEX);
      throw new IllegalArgumentException(message);
    }
  }

}
