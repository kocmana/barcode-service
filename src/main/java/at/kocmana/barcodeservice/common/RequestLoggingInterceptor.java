package at.kocmana.barcodeservice.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

  private static final Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

  @Override
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    addToMdcContext(request);
    logRequest(request);
    return true;
  }

  private void addToMdcContext(HttpServletRequest request) {
    MDC.put("METHOD", request.getMethod());
    MDC.put("URI", request.getRequestURI());
  }

  private void logRequest(final HttpServletRequest request) {
    log.info("Request received: {} {}",
        request.getMethod(),
        request.getRequestURI());
  }

  @Override
  public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                              Object handler, Exception ex) {
    logResponse(request, response);
  }

  private void logResponse(final HttpServletRequest request, final HttpServletResponse response) {
    var status = HttpStatus.valueOf(response.getStatus());

    if (status.is2xxSuccessful() || status.is1xxInformational()) {
      log.info("Request {} {} handled successfully - yielded HTTP {}",
          request.getMethod(), request.getRequestURI(),
          response.getStatus());
    } else if (status.is4xxClientError()) {
      log.warn("Request {} {} could not be handled due to incorrect user input - yielded HTTP {}.",
          request.getMethod(), request.getRequestURI(),
          response.getStatus());
    } else {
      log.error("Request {} {} yielded unexpected status: HTTP {}.",
          request.getMethod(), request.getRequestURI(),
          response.getStatus());
    }
  }
}
