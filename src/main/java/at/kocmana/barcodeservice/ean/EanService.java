package at.kocmana.barcodeservice.ean;

import at.kocmana.barcodeservice.ean.model.Ean13;
import at.kocmana.barcodeservice.ean.model.Ean8;
import java.awt.image.BufferedImage;

public interface EanService {
  BufferedImage generateEan13(Ean13 barcode);

  BufferedImage generateEan8(Ean8 barcode);
}
