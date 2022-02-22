package at.kocmana.barcodeservice.upc;

import at.kocmana.barcodeservice.upc.model.UpcA;
import at.kocmana.barcodeservice.upc.model.UpcE;
import java.awt.image.BufferedImage;

public interface UpcService {
  BufferedImage generateUpcA(UpcA barcode);

  BufferedImage generateUpcE(UpcE barcode);
}
