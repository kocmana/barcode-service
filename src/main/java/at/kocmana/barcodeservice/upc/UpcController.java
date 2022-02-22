package at.kocmana.barcodeservice.upc;

import at.kocmana.barcodeservice.upc.model.UpcA;
import at.kocmana.barcodeservice.upc.model.UpcE;
import java.awt.image.BufferedImage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.IMAGE_JPEG_VALUE)
public class UpcController {

  private final UpcService upcService;

  public UpcController(UpcService upcService) {
    this.upcService = upcService;
  }

  @GetMapping("/upca/{barcode}")
  public ResponseEntity<BufferedImage> generateUpcA(@PathVariable("barcode") UpcA barcode) {

    var image = upcService.generateUpcA(barcode);

    return ResponseEntity.ok(image);
  }

  @GetMapping("/upce/{barcode}")
  public ResponseEntity<BufferedImage> generateUpcE(@PathVariable("barcode") UpcE barcode) {

    var image = upcService.generateUpcE(barcode);

    return ResponseEntity.ok(image);
  }


}
