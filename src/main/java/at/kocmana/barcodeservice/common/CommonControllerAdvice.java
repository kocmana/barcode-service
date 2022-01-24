package at.kocmana.barcodeservice.common;

import at.kocmana.barcodeservice.common.model.BarcodeGenerationException;
import at.kocmana.barcodeservice.common.model.ErrorResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonControllerAdvice extends ResponseEntityExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(CommonControllerAdvice.class);

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  ResponseEntity<ErrorResponse> handleMethodArgumentExceptions(IllegalArgumentException exception,
                                                               HttpServletRequest request) {
    log.warn("Could not handle request {} due to the following exception: \"{}\", returning HTTP 400",
        request.getContextPath(), exception.getMessage());

    return ResponseEntity.badRequest()
        .body(ErrorResponse.withMessage(exception.getMessage()));
  }

  @ExceptionHandler(BarcodeGenerationException.class)
  ResponseEntity<ErrorResponse> handleMethodArgumentExceptions(BarcodeGenerationException exception,
                                                               HttpServletRequest request) {
    log.warn("Could not generate barcode for request {} due to the following exception: \"{}\", returning HTTP 400",
        request.getContextPath(), exception.getMessage());

    return ResponseEntity.internalServerError()
        .body(ErrorResponse.withMessage(exception.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorResponse> handleGenericException(Exception exception,
                                                               HttpServletRequest request) {
    log.warn("Unexpected exception during handling of request {}: \"{}\", returning HTTP 400",
        request.getContextPath(), exception.getMessage(), exception);

    return ResponseEntity.internalServerError()
        .body(ErrorResponse.withMessage("Unexpected exception occurred."));
  }

}
