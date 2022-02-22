package at.kocmana.barcodeservice.upc;

import at.kocmana.barcodeservice.upc.model.UpcA;
import at.kocmana.barcodeservice.upc.model.UpcE;
import java.awt.image.BufferedImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UpcServiceCachingDecorator implements UpcService {

  private static final Logger log = LoggerFactory.getLogger(UpcServiceCachingDecorator.class);

  private final UpcServiceImpl delegate;

  public UpcServiceCachingDecorator(UpcServiceImpl delegate) {
    this.delegate = delegate;
  }

  @Override
  @Cacheable(value = "upc", key = "#barcode")
  public BufferedImage generateUpcA(UpcA barcode) {
    logFetchFromSource(barcode.barcode());
    return delegate.generateUpcA(barcode);
  }

  @Override
  @Cacheable(value = "upc", key = "#barcode")
  public BufferedImage generateUpcE(UpcE barcode) {
    logFetchFromSource(barcode.barcode());
    return delegate.generateUpcE(barcode);
  }

  private void logFetchFromSource(String barcode) {
    log.info("EAN for barcode {} not found in cache, fetching from source.", barcode);
  }
}
