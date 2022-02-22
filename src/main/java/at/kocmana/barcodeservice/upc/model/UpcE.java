package at.kocmana.barcodeservice.upc.model;

import static java.util.Objects.isNull;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public record UpcE(String barcode) {

  private static final String UPC_E_REGEX = "[0-9]{7}";
  private static final Predicate<String> VALID_UPC_E = Pattern.compile(UPC_E_REGEX).asMatchPredicate();

  public UpcE {

    if (isNull(barcode) || !VALID_UPC_E.test(barcode)) {
      var message = String.format("Barcode \"%s\" is invalid for UPC-E. Must adhere to the pattern: \"%s\".",
          barcode, UPC_E_REGEX);
      throw new IllegalArgumentException(message);
    }

  }

}
