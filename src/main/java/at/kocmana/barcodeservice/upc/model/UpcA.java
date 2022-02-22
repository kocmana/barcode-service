package at.kocmana.barcodeservice.upc.model;

import static java.util.Objects.isNull;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public record UpcA(String barcode) {

  private static final String UPC_A_REGEX = "[0-9]{11}";
  private static final Predicate<String> VALID_UPC_A = Pattern.compile(UPC_A_REGEX).asMatchPredicate();

  private static final int INDEX_MANUFACTORER_ID_START = 0;
  private static final int INDEX_MANUFACTORER_ID_END = 5;
  private static final int INDEX_ITEM_NUMBER_ID_START = INDEX_MANUFACTORER_ID_END + 1;
  private static final int INDEX_ITEM_NUMBER_ID_END = INDEX_MANUFACTORER_ID_END + 5;

  public UpcA {

    if (isNull(barcode) || !VALID_UPC_A.test(barcode)) {
      var message = String.format("Barcode \"%s\" is invalid for UPC-A. Must adhere to the pattern: \"%s\".",
          barcode, UPC_A_REGEX);
      throw new IllegalArgumentException(message);
    }

  }

  public String getManufacturerId() {
    //+1 since substring's to argument is exclusive
    return barcode.substring(INDEX_MANUFACTORER_ID_START, INDEX_MANUFACTORER_ID_END + 1);
  }

  public String getItemNumber() {
    //+1 since substring's to argument is exclusive
    return barcode.substring(INDEX_ITEM_NUMBER_ID_START, INDEX_ITEM_NUMBER_ID_END + 1);
  }

}
