package com.example.Demo1.controllers;

import com.example.Demo1.dtos.TradeDto;
import com.example.Demo1.services.TradeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trades")
public class TradeController {
  Logger logger = LoggerFactory.getLogger(TradeController.class);
  @Autowired
  private TradeService tradeService;

  @PostMapping
  public ResponseEntity<TradeDto> create(@Valid @RequestBody TradeDto tradeDto) {
    logger.info("creating trade: {}", tradeDto);
    TradeDto createdTradeDto = tradeService.create(tradeDto);
    return new ResponseEntity<>(createdTradeDto, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<TradeDto>> getAllTrades() {
    logger.info("getting all trades");
    List<TradeDto> trades = tradeService.getAll();
    return new ResponseEntity<>(trades, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TradeDto> getTradeById(@PathVariable Integer id) {
    logger.info("getting trade by id: {}", id);
    TradeDto tradeDto = tradeService.getById(id);
    return new ResponseEntity<>(tradeDto, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TradeDto> updateTradeById(@PathVariable Integer id, @Valid @RequestBody TradeDto tradeDto) {
    logger.info("updating trade by id: {}", id);
    TradeDto updatedTrade = tradeService.updateById(id, tradeDto);
    return new ResponseEntity<>(updatedTrade, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<TradeDto> deleteTradeById(@PathVariable Integer id) {
    logger.info("deleting trade by id: {}", id);
    TradeDto deletedTrade = tradeService.deleteById(id);
    return new ResponseEntity<>(deletedTrade, HttpStatus.OK);
  }
}
