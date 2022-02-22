package at.kocmana.barcodeservice.upc.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class UpcETest {

  @ParameterizedTest
  @ValueSource(strings = {"       ", "aaaaaaa", "123456", "12345678"})
  @NullAndEmptySource
  void whenIllegalBarcodeIsUsed_ThenConstructorThrowsIllegalArgumentException(String barcode) {
    var expectedMessage =
        String.format("Barcode \"%s\" is invalid for UPC-E. Must adhere to the pattern: \"[0-9]{7}\".",
            barcode);

    assertThatIllegalArgumentException().isThrownBy(() -> new UpcE(barcode))
        .withMessage(expectedMessage)
        .withNoCause();
  }

  @ParameterizedTest
  @ValueSource(strings = {"0000000", "9999999"})
  void whenCorrectBarcodeIsUsed_ThenBarcodeIsSetCorrectly(String expectedBarcode) {

    var underTest = new UpcE(expectedBarcode);
    var actualBarcode = underTest.barcode();

    assertThat(actualBarcode).isEqualTo(expectedBarcode);
  }

}
