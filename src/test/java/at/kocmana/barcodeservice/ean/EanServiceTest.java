package at.kocmana.barcodeservice.ean;

import static at.kocmana.barcodeservice.testutil.FileUtil.readBufferedImageFromFile;
import static at.kocmana.barcodeservice.testutil.ImageUtil.compareImages;
import static org.assertj.core.api.Assertions.assertThat;

import at.kocmana.barcodeservice.ean.model.Ean13;
import at.kocmana.barcodeservice.ean.model.Ean8;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EanServiceImpl.class)
class EanServiceTest {

  @Autowired
  EanService underTest;

  @Test
  void whenGenerateEan13_CorrectImageIsReturned() {
    //given
    var ean13 = new Ean13("123456789012");
    var filename = "ean/test-ean13.bmp";
    var expectedImage = readBufferedImageFromFile(filename);

    //when
    var actualResult = underTest.generateEan13(ean13);

    //then
    assertThat(compareImages(actualResult, expectedImage)).isTrue();
  }

  @Test
  void whenGenerateEan8_CorrectImageIsReturned() {
    //given
    var ean8 = new Ean8("1234567");
    var filename = "ean/test-ean8.bmp";
    var expectedImage = readBufferedImageFromFile(filename);

    //when
    var actualResult = underTest.generateEan8(ean8);

    //then
    assertThat(compareImages(actualResult, expectedImage)).isTrue();
  }

}
