package at.kocmana.barcodeservice.ean;

import at.kocmana.barcodeservice.ean.model.Ean13;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping(produces = MediaType.IMAGE_JPEG_VALUE)
public class EanController {

  private final EanService eanService;

  public EanController(EanService eanService) {
    this.eanService = eanService;
  }

  @GetMapping("/ean13/{barcode}")
  public ResponseEntity<BufferedImage> generateEan13(Ean13 barcode) {

    var image = eanService.generateEan13(barcode);

    return ResponseEntity.ok(image);
  }

}
