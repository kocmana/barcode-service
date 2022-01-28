package at.kocmana.barcodeservice.qr;

import at.kocmana.barcodeservice.qr.model.Qr;
import java.awt.image.BufferedImage;

public interface QrService {
  BufferedImage generateQrCode(Qr payload);
}
