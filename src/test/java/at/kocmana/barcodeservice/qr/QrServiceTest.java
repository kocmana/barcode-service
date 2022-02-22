package at.kocmana.barcodeservice.qr;

import static at.kocmana.barcodeservice.testutil.FileUtil.readBufferedImageFromFile;
import static at.kocmana.barcodeservice.testutil.ImageUtil.compareImages;
import static org.assertj.core.api.Assertions.assertThat;

import at.kocmana.barcodeservice.qr.model.Qr;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = QrServiceImpl.class)
class QrServiceTest {

  @Autowired
  QrService underTest;

  @Test
  void whenGenerateQrCode_CorrectImageIsReturned() {
    //given
    var qr = new Qr("some payload");
    var filename = "qr/test-qr.bmp";
    var expectedImage = readBufferedImageFromFile(filename);

    //when
    var actualResult = underTest.generateQrCode(qr);

    //then
    assertThat(compareImages(actualResult, expectedImage)).isTrue();
  }

}
