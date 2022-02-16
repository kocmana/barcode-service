package at.kocmana.barcodeservice.configuration;

import at.kocmana.barcodeservice.common.RequestLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestLoggingConfiguration implements WebMvcConfigurer {

  private final RequestLoggingInterceptor requestLoggingInterceptor;

  @Autowired
  public RequestLoggingConfiguration(RequestLoggingInterceptor requestLoggingInterceptor) {
    this.requestLoggingInterceptor = requestLoggingInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(requestLoggingInterceptor);
  }

}
