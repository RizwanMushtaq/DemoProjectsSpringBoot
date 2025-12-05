package com.example.Demo1.services;

import com.example.Demo1.dtos.TradeDto;
import com.example.Demo1.entities.TradeEntity;
import com.example.Demo1.repositories.TradeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService {
  @Autowired
  private TradeRepository tradeRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public TradeDto create(TradeDto tradeDto) {
    TradeEntity tradeEntity = modelMapper.map(tradeDto, TradeEntity.class);
    tradeEntity.setId(null);
    System.out.println(tradeEntity);
    TradeEntity savedTradeEntity = tradeRepository.save(tradeEntity);
    System.out.println(savedTradeEntity);
    return modelMapper.map(savedTradeEntity, TradeDto.class);
  }
}
