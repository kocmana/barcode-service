package at.kocmana.barcodeservice.upc.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class UpcATest {

  @ParameterizedTest
  @ValueSource(strings = {"            ", "aaaaaaaaaaaa", "123456", "012345678"})
  @NullAndEmptySource
  void whenIllegalBarcodeIsUsed_ThenConstructorThrowsIllegalArgumentException(String barcode) {
    var expectedMessage =
        String.format("Barcode \"%s\" is invalid for UPC-A. Must adhere to the pattern: \"[0-9]{11}\".",
            barcode);

    assertThatIllegalArgumentException().isThrownBy(() -> new UpcA(barcode))
        .withMessage(expectedMessage)
        .withNoCause();
  }

  @ParameterizedTest
  @ValueSource(strings = {"00000000000", "99999999999"})
  void whenCorrectBarcodeIsUsed_ThenBarcodeIsSetCorrectly(String expectedBarcode) {
    var underTest = new UpcA(expectedBarcode);
    var actualBarcode = underTest.barcode();

    assertThat(actualBarcode).isEqualTo(expectedBarcode);
  }

  @Test
  void whenGetManufacturerIdIsCalled_ThenCorrectPartOfTheBarcodeIsReturned() {
    var underTest = new UpcA("11111122222");

    var actualManufacturerId = underTest.getManufacturerId();

    assertThat(actualManufacturerId).isEqualTo("111111");
  }

  @Test
  void whenGetItemNumberIsCalled_ThenCorrectPartOfTheBarcodeIsReturned() {
    var underTest = new UpcA("11111122222");

    var actualItemNumber = underTest.getItemNumber();

    assertThat(actualItemNumber).isEqualTo("22222");
  }

}
