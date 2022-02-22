package at.kocmana.barcodeservice.upc;

import at.kocmana.barcodeservice.upc.model.UpcA;
import at.kocmana.barcodeservice.upc.model.UpcE;
import java.awt.image.BufferedImage;
import org.krysalis.barcode4j.impl.upcean.UPCABean;
import org.krysalis.barcode4j.impl.upcean.UPCEBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;

@Service
public class UpcServiceImpl implements UpcService {
  @Override
  public BufferedImage generateUpcA(UpcA barcode) {

    var barcodeGenerator = new UPCABean();
    var canvas = new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

    barcodeGenerator.generateBarcode(canvas, barcode.barcode());
    return canvas.getBufferedImage();
  }

  @Override
  public BufferedImage generateUpcE(UpcE barcode) {

    var barcodeGenerator = new UPCEBean();
    var canvas = new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

    barcodeGenerator.generateBarcode(canvas, barcode.barcode());
    return canvas.getBufferedImage();
  }

}
