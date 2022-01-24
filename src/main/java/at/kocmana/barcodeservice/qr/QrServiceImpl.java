package at.kocmana.barcodeservice.qr;

import at.kocmana.barcodeservice.common.model.BarcodeGenerationException;
import at.kocmana.barcodeservice.qr.model.Qr;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import org.springframework.stereotype.Service;

@Service
public class QrServiceImpl implements QrService {
  @Override
  public BufferedImage generateQrCode(Qr request) {

    QRCodeWriter barcodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix =
        null;
    try {
      bitMatrix = barcodeWriter.encode(request.payload(), BarcodeFormat.QR_CODE, 200, 200);
    } catch (WriterException exception) {
      throw new BarcodeGenerationException(exception);
    }

    return MatrixToImageWriter.toBufferedImage(bitMatrix);
  }
}
