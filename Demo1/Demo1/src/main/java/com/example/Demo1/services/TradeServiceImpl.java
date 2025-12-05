package com.example.Demo1.services;

import com.example.Demo1.dtos.TradeDto;
import com.example.Demo1.entities.TradeEntity;
import com.example.Demo1.exceptions.ResourceNotFoundException;
import com.example.Demo1.repositories.TradeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    TradeEntity savedTradeEntity = tradeRepository.save(tradeEntity);
    return modelMapper.map(savedTradeEntity, TradeDto.class);
  }

  @Override
  public List<TradeDto> getAll() {
    List<TradeEntity> tradeEntities = tradeRepository.findAll();
    return tradeEntities.stream()
        .map(tradeEntity -> modelMapper.map(tradeEntity, TradeDto.class))
        .toList();
  }

  @Override
  public TradeDto getById(Integer id) {
    TradeEntity tradeEntity = tradeRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Trade not found with id: " + id)
    );
    return modelMapper.map(tradeEntity, TradeDto.class);
  }

  @Override
  public TradeDto deleteById(Integer id) {
    TradeEntity tradeEntity = tradeRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Trade not found with id: " + id)
    );
    tradeRepository.deleteById(id);
    return modelMapper.map(tradeEntity, TradeDto.class);
  }

  @Override
  public TradeDto updateById(Integer id, TradeDto tradeDto) {
    TradeEntity tradeEntity = tradeRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Trade not found with id: " + id)
    );
    tradeEntity.setType(tradeDto.getType());
    tradeEntity.setUserId(tradeDto.getUserId());
    tradeEntity.setSymbol(tradeDto.getSymbol());
    tradeEntity.setShares(tradeDto.getShares());
    tradeEntity.setPrice(tradeDto.getPrice());
    tradeEntity.setTimestamp(tradeDto.getTimestamp());
    TradeEntity updatedTradeEntity = tradeRepository.save(tradeEntity);
    return modelMapper.map(updatedTradeEntity, TradeDto.class);
  }
}
