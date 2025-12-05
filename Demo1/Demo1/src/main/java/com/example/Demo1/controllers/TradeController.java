package com.example.Demo1.controllers;

import com.example.Demo1.dtos.TradeDto;
import com.example.Demo1.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/trades")
public class TradeController {
  @Autowired
  private TradeService tradeService;

  @PostMapping
  public ResponseEntity<TradeDto> create(TradeDto tradeDto) {
    TradeDto createdTradeDto = tradeService.create(tradeDto);
    return new ResponseEntity<>(createdTradeDto, HttpStatus.CREATED);
  }
}
