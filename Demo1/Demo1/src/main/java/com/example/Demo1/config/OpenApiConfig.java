package com.example.Demo1.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI tradeOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Trade API")
            .description("CRUD API for Trades")
            .version("v1.0"))
        .externalDocs(new ExternalDocumentation()
            .description("Project GitHub")
            .url("https://github.com/RizwanMushtaq/DemoProjectsSpringBoot"));
  }
}

