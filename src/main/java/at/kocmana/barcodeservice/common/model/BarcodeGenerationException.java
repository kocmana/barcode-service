package at.kocmana.barcodeservice.common.model;

public class BarcodeGenerationException extends RuntimeException {
  public BarcodeGenerationException(String message) {
    super(message);
  }

  public BarcodeGenerationException(Throwable cause) {
    super(cause);
  }
}
