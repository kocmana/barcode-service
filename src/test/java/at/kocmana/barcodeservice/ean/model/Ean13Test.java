package at.kocmana.barcodeservice.ean.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class Ean13Test {

  @ParameterizedTest
  @ValueSource(strings = {"                ", "aaaaaaaaaaaaaaa", "12345678901", "1234567890123"})
  @NullAndEmptySource
  void whenIllegalBarcodeIsUsed_ThenConstructorThrowsIllegalArgumentException(String barcode) {
    var expectedMessage = String.format("Barcode \"%s\" is invalid for EAN13. Must adhere to the pattern: \"[0-9]{12}\".",
            barcode);

    assertThatIllegalArgumentException().isThrownBy(() -> new Ean13(barcode))
            .withMessage(expectedMessage)
            .withNoCause();
  }

  @ParameterizedTest
  @ValueSource(strings = {"000000000000", "999999999999"})
  void whenCorrectBarcodeIsUsed_ThenBarcodeIsSetCorrectly(String expectedBarcode) {

    var underTest = new Ean13(expectedBarcode);
    var actualBarcode = underTest.barcode();

    assertThat(actualBarcode).isEqualTo(expectedBarcode);
  }

}
