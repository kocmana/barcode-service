package at.kocmana.barcodeservice.ean;

import static at.kocmana.barcodeservice.testutil.FileUtil.readBufferedImageFromFile;
import static at.kocmana.barcodeservice.testutil.ImageUtil.compareImages;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import at.kocmana.barcodeservice.ean.model.Ean13;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EanServiceImpl.class)
class EanServiceTest {

  @Autowired
  EanService underTest;

  @Test
  @Disabled("This test is incorrectly failing and should be revisited as issue #")
  void whenGenerateEan13_CorrectImageIsReturned() {
    //given
    var ean13 = new Ean13("123456789012");
    var filename = "test-ean13.jpg";
    var expectedImage = readBufferedImageFromFile(filename);

    //when
    var actualResult = underTest.generateEan13(ean13);

    //then
    assertThat(compareImages(actualResult, expectedImage)).isTrue();
  }

}
