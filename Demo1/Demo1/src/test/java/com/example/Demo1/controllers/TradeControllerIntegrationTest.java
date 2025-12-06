package com.example.Demo1.controllers;

import com.example.Demo1.dtos.TradeDto;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
  @Order(1)
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

  @Test
  @Order(2)
  void testGetTradeById() {
    TradeDto response = restClient.get()
        .uri("/trades/" + createdTradeId)
        .retrieve()
        .body(TradeDto.class);

    assertNotNull(response);
    assertEquals(createdTradeId, response.getId());
    assertEquals("BUY", response.getType());
    assertEquals(100, response.getUserId());
  }

  @Test
  @Order(3)
  void testGetAllTrades() {
    TradeDto[] response = restClient.get()
        .uri("/trades")
        .retrieve()
        .body(TradeDto[].class);
    assertNotNull(response);
    assert (response.length >= 1);
  }

  @Test
  @Order(4)
  void testUpdateTrade() {
    TradeDto request = TradeDto.builder()
        .type("SELL")
        .userId(100)
        .symbol("APL")
        .shares(5)
        .price(155)
        .timestamp(1000)
        .build();
    TradeDto response = restClient.put()
        .uri("/trades/" + createdTradeId)
        .body(request)
        .retrieve()
        .body(TradeDto.class);

    assertNotNull(response);
    assertEquals(createdTradeId, response.getId());
    assertEquals("SELL", response.getType());
    assertEquals(5, response.getShares());
  }

  @Test
  @Order(5)
  void testDeleteTrade() {
    TradeDto response = restClient.delete()
        .uri("/trades/" + createdTradeId)
        .retrieve()
        .body(TradeDto.class);
    assertNotNull(response);
    assertEquals(createdTradeId, response.getId());
  }
}
