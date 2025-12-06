package com.example.Demo1.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
  private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

  /**
   * Same contract as for {@code doFilter}, but guaranteed to be just invoked
   * once per request within a single request thread. See
   * {@link #shouldNotFilterAsyncDispatch()} for details.
   * <p>Provides HttpServletRequest and HttpServletResponse arguments instead
   * of the default ServletRequest and ServletResponse ones.
   *
   * @param request
   * @param response
   * @param filterChain
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    long startTime = System.currentTimeMillis();
    // Wrap the request and response to log their content
    ContentCachingRequestWrapper wrappedRequest =
        new ContentCachingRequestWrapper(request, 1024 * 1024);
    ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
    try {
      filterChain.doFilter(wrappedRequest, wrappedResponse);
    } finally {
      long duration = System.currentTimeMillis() - startTime;

      // Log request
      String requestBody = new String(wrappedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);
      logger.info("REQUEST {} {} \nHeaders: {}\nBody: {}",
          request.getMethod(),
          request.getRequestURI(),
          wrappedRequest.getHeaderNames(),
          requestBody);

      // Log response
      String responseBody = new String(wrappedResponse.getContentAsByteArray(), StandardCharsets.UTF_8);
      logger.info("RESPONSE {} {} \nStatus: {}\nBody: {}\nTime Taken: {} ms",
          request.getMethod(),
          request.getRequestURI(),
          wrappedResponse.getStatus(),
          responseBody,
          duration);

      wrappedResponse.copyBodyToResponse(); // Important to copy the response body back
    }
  }
}
