package com.example.Demo1.services;

import com.example.Demo1.dtos.TradeDto;

import java.util.List;

public interface TradeService {
  TradeDto create(TradeDto tradeDto);

  List<TradeDto> getAll();

  TradeDto getById(Integer id);

  TradeDto deleteById(Integer id);

  TradeDto updateById(Integer id, TradeDto tradeDto);
}
