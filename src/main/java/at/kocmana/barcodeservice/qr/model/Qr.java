package at.kocmana.barcodeservice.qr.model;

import static java.util.Objects.isNull;

public record Qr(String payload) {

  private static final int MAX_PAYLOAD_LENGTH = 4296;

  public Qr {
    if (isNull(payload) || payload.isBlank()) {
      throw new IllegalArgumentException("Provided payload for QR generation is null or blank.");
    }

    if (payload.length() > MAX_PAYLOAD_LENGTH) {
      var message = String.format("Provided payload exceeds maximum payload size of %d characters.",
          MAX_PAYLOAD_LENGTH);
      throw new IllegalArgumentException(message);
    }
  }

}
