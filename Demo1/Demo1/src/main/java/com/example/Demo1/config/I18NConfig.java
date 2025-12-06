package com.example.Demo1.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class I18NConfig extends AcceptHeaderLocaleResolver {
  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
    source.setBasename("classpath:messages");
    source.setDefaultEncoding("UTF-8");
    return source;
  }

  @Override
  public Locale resolveLocale(HttpServletRequest request) {
    String lang = request.getHeader("Accept-Language");
    return lang == null || lang.isEmpty() ? Locale.ENGLISH : Locale.forLanguageTag(lang);
  }
}
