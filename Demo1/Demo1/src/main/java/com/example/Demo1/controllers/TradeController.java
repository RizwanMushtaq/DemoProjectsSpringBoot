package com.example.Demo1.controllers;

import com.example.Demo1.dtos.TradeDto;
import com.example.Demo1.services.TradeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trades")
public class TradeController {
  Logger logger = LoggerFactory.getLogger(TradeController.class);
  @Autowired
  private TradeService tradeService;

  @PostMapping
  public ResponseEntity<TradeDto> create(@Valid @RequestBody TradeDto tradeDto) {
    logger.info("Creating Trade...", tradeDto.getSymbol());
    System.out.println(tradeDto);
    TradeDto createdTradeDto = tradeService.create(tradeDto);
    return new ResponseEntity<>(createdTradeDto, HttpStatus.CREATED);
  }
}
