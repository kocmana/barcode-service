package at.kocmana.barcodeservice.ean;

import at.kocmana.barcodeservice.ean.model.Ean13;
import at.kocmana.barcodeservice.ean.model.Ean8;
import java.awt.image.BufferedImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EanServiceCachingDecorator implements EanService{

  private static final Logger log = LoggerFactory.getLogger(EanServiceCachingDecorator.class);

  private final EanServiceImpl delegate;

  public EanServiceCachingDecorator(EanServiceImpl delegate) {
    this.delegate = delegate;
  }

  @Override
  @Cacheable(value = "ean", key = "#barcode")
  public BufferedImage generateEan13(Ean13 barcode) {
    logFetchFromSource(barcode.barcode());
    return delegate.generateEan13(barcode);
  }

  @Override
  @Cacheable(value = "ean", key = "#barcode")
  public BufferedImage generateEan8(Ean8 barcode) {
    logFetchFromSource(barcode.barcode());
    return delegate.generateEan8(barcode);
  }

  private void logFetchFromSource(String barcode) {
    log.info("EAN for barcode {} not found in cache, fetching from source.", barcode);
  }
}
