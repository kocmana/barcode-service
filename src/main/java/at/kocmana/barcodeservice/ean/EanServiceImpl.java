package at.kocmana.barcodeservice.ean;

import at.kocmana.barcodeservice.ean.model.Ean13;
import at.kocmana.barcodeservice.ean.model.Ean8;
import java.awt.image.BufferedImage;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.impl.upcean.EAN8Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;

@Service
public class EanServiceImpl implements EanService {
  @Override
  public BufferedImage generateEan13(Ean13 barcode) {

    EAN13Bean barcodeGenerator = new EAN13Bean();
    BitmapCanvasProvider canvas =
        new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

    barcodeGenerator.generateBarcode(canvas, barcode.barcode());
    return canvas.getBufferedImage();
  }

  @Override
  public BufferedImage generateEan8(Ean8 barcode) {

    EAN8Bean barcodeGenerator = new EAN8Bean();
    BitmapCanvasProvider canvas =
        new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

    barcodeGenerator.generateBarcode(canvas, barcode.barcode());
    return canvas.getBufferedImage();
  }

}
