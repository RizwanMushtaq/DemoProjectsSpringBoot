package com.example.Demo1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloIntegrationTest {
  private final RestClient restClient = RestClient.create();
  @LocalServerPort
  private int port;

  @Test
  void helloEndpoint_withName_returnsCustomGreeting() {
    String url = "http://localhost:" + port + "/hello?name=Rizwan";
    String body = restClient.get()
        .uri(url)
        .retrieve()
        .body(String.class);
    assertEquals("Hello Rizwan", body);
  }

  @Test
  void helloEndpoint_withoutName() {
    String url = "http://localhost:" + port + "/hello";
    String body = restClient.get()
        .uri(url)
        .retrieve()
        .body(String.class);
    assertEquals("Hello world", body);
  }
}
