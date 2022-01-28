package at.kocmana.barcodeservice.common;

import at.kocmana.barcodeservice.common.model.BarcodeGenerationException;
import at.kocmana.barcodeservice.common.model.ErrorResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Primary
public class CommonControllerAdvice extends ResponseEntityExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(CommonControllerAdvice.class);

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  ResponseEntity<Object> handleMethodArgumentExceptions(IllegalArgumentException exception,
                                                        WebRequest request) {
    return handleBadRequest(exception, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,
                                                                       HttpHeaders headers, HttpStatus status,
                                                                       WebRequest request) {
    return handleBadRequest(exception, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
    return handleBadRequest(exception, request);
  }

  private ResponseEntity<Object> handleBadRequest(Exception exception, WebRequest request) {
    log.warn("Could not handle request {} due to the following exception: \"{}\", returning HTTP 400",
        request.getContextPath(), exception.getMessage());

    return ResponseEntity.badRequest()
        .body(ErrorResponse.withMessage(exception.getMessage()));
  }

  @ExceptionHandler(BarcodeGenerationException.class)
  protected ResponseEntity<ErrorResponse> handleBarcodeGenerationExceptions(BarcodeGenerationException exception,
                                                                            HttpServletRequest request) {
    log.warn("Could not generate barcode for request {} due to the following exception: \"{}\", returning HTTP 400",
        request.getContextPath(), exception.getMessage());

    return ResponseEntity.internalServerError()
        .body(ErrorResponse.withMessage(exception.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErrorResponse> handleGenericException(Exception exception,
                                                                 HttpServletRequest request) {
    log.warn("Unexpected exception during handling of request {}: \"{}\", returning HTTP 400",
        request.getContextPath(), exception.getMessage(), exception);

    return ResponseEntity.internalServerError()
        .body(ErrorResponse.withMessage("Unexpected exception occurred."));
  }

}
