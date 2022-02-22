package at.kocmana.barcodeservice.upc;

import static at.kocmana.barcodeservice.testutil.FileUtil.readBufferedImageFromFile;
import static at.kocmana.barcodeservice.testutil.ImageUtil.compareImages;
import static org.assertj.core.api.Assertions.assertThat;

import at.kocmana.barcodeservice.upc.model.UpcA;
import at.kocmana.barcodeservice.upc.model.UpcE;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UpcServiceImpl.class)
class UpcServiceTest {

  @Autowired
  UpcService underTest;

  @Test
  @Disabled("Needs fixing as part of issue #5")
  void whenGenerateUpcA_CorrectImageIsReturned() {
    //given
    var upcA = new UpcA("12345678901");
    var filename = "upc/test-upc-a.bmp";
    var expectedImage = readBufferedImageFromFile(filename);

    //when
    var actualResult = underTest.generateUpcA(upcA);

    //then
    assertThat(compareImages(actualResult, expectedImage)).isTrue();
  }

  @Test
  @Disabled("Needs fixing as part of issue #5")
  void whenGenerateUpcE_CorrectImageIsReturned() {
    //given
    var upcE = new UpcE("1234567");
    var filename = "upc/test-upc-e.bmp";
    var expectedImage = readBufferedImageFromFile(filename);

    //when
    var actualResult = underTest.generateUpcE(upcE);

    //then
    assertThat(compareImages(actualResult, expectedImage)).isTrue();
  }

}
