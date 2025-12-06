package com.example.Demo1.config;

import com.example.Demo1.filters.RequestResponseLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

  @Bean
  public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter(RequestResponseLoggingFilter filter) {
    FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(filter);
    registrationBean.addUrlPatterns("/trades/*"); // Only TradeController endpoints
    registrationBean.setOrder(1);
    return registrationBean;
  }
}

