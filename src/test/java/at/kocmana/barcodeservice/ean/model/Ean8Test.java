package at.kocmana.barcodeservice.ean.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class Ean8Test {

  @ParameterizedTest
  @ValueSource(strings = {"            ", "aaaaaaaaaaaa", "123456", "012345678"})
  @NullAndEmptySource
  void whenIllegalBarcodeIsUsed_ThenConstructorThrowsIllegalArgumentException(String barcode) {
    var expectedMessage = String.format("Barcode \"%s\" is invalid for EAN8. Must adhere to the pattern: \"[0-9]{7}\".",
            barcode);

    assertThatIllegalArgumentException().isThrownBy(() -> new Ean8(barcode))
            .withMessage(expectedMessage)
            .withNoCause();
  }

  @ParameterizedTest
  @ValueSource(strings = {"0000000", "9999999"})
  void whenCorrectBarcodeIsUsed_ThenBarcodeIsSetCorrectly(String expectedBarcode) {

    var underTest = new Ean8(expectedBarcode);
    var actualBarcode = underTest.barcode();

    assertThat(actualBarcode).isEqualTo(expectedBarcode);
  }

}