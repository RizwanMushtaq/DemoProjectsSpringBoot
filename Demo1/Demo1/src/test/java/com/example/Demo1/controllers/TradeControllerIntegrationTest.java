package com.example.Demo1.controllers;

import com.example.Demo1.dtos.TradeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradeControllerIntegrationTest {
  private static Integer createdTradeId;
  @LocalServerPort
  private int port;
  private RestClient restClient;

  @BeforeEach
  void setUp() {
    restClient = RestClient.builder().baseUrl("http://localhost:" + port).build();
  }

  @Test
  void testCreateTrade() {
    TradeDto request = TradeDto.builder()
        .type("BUY")
        .userId(100)
        .symbol("APL")
        .shares(10)
        .price(150)
        .timestamp(1000)
        .build();
    TradeDto response = restClient.post()
        .uri("/trades")
        .body(request)
        .retrieve()
        .body(TradeDto.class);

    assertNotNull(response);
    assertNotNull(response.getId());
    assertEquals("BUY", response.getType());
    assertEquals(100, response.getUserId());

    createdTradeId = response.getId();
  }
}
