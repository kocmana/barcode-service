package at.kocmana.barcodeservice.qr;

import at.kocmana.barcodeservice.qr.model.Qr;
import java.awt.image.BufferedImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class QrServiceCachingDecorator implements QrService {

  private static final Logger log = LoggerFactory.getLogger(QrServiceCachingDecorator.class);

  private final QrServiceImpl delegate;

  public QrServiceCachingDecorator(QrServiceImpl delegate) {
    this.delegate = delegate;
  }

  @Override
  @Cacheable(value = "qrCode", key = "#request")
  public BufferedImage generateQrCode(Qr request) {
    logFetchFromSource(request.payload());
    return delegate.generateQrCode(request);
  }

  private void logFetchFromSource(String qrCode) {
    log.info("QR Code request {} not found in cache, fetching from source.", qrCode);
  }
}
