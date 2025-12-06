package com.example.Demo1.services;

import com.example.Demo1.dtos.TradeDto;

import java.util.List;
import java.util.Locale;

public interface TradeService {
  TradeDto create(TradeDto tradeDto);

  List<TradeDto> getAll();

  TradeDto getById(Integer id, Locale locale);

  TradeDto deleteById(Integer id);

  TradeDto updateById(Integer id, TradeDto tradeDto);
}
