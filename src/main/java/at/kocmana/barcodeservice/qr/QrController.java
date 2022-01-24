package at.kocmana.barcodeservice.qr;

import at.kocmana.barcodeservice.qr.model.Qr;
import java.awt.image.BufferedImage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.IMAGE_JPEG_VALUE)
public class QrController {

  private final QrService qrService;

  public QrController(QrService qrService) {
    this.qrService = qrService;
  }

  @GetMapping("/qr/{payload}")
  public ResponseEntity<BufferedImage> generateQrCode(@PathVariable("payload") Qr request) {

    var image = qrService.generateQrCode(request);

    return ResponseEntity.ok(image);
  }

}
