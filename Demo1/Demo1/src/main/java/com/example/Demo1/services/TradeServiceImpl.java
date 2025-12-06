package com.example.Demo1.services;

import com.example.Demo1.dtos.TradeDto;
import com.example.Demo1.entities.TradeEntity;
import com.example.Demo1.exceptions.ResourceNotFoundException;
import com.example.Demo1.repositories.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
  private final TradeRepository tradeRepository;
  private final ModelMapper modelMapper;

  @Override
  public TradeDto create(TradeDto tradeDto) {
    TradeEntity tradeEntity = modelMapper.map(tradeDto, TradeEntity.class);
    tradeEntity.setId(null);
    TradeEntity savedTradeEntity = tradeRepository.save(tradeEntity);
    return mapToDto(savedTradeEntity);
  }

  @Override
  @Transactional(readOnly = true)
  public List<TradeDto> getAll() {
    List<TradeEntity> tradeEntities = tradeRepository.findAll();
    return tradeEntities.stream()
        .map(this::mapToDto)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public TradeDto getById(Integer id) {
    TradeEntity existing = getTradeEntity(id);
    return mapToDto(existing);
  }

  @Override
  public TradeDto deleteById(Integer id) {
    TradeEntity existing = getTradeEntity(id);
    tradeRepository.deleteById(id);
    return mapToDto(existing);
  }

  @Override
  public TradeDto updateById(Integer id, TradeDto tradeDto) {
    TradeEntity existing = getTradeEntity(id);
    modelMapper.map(tradeDto, existing);
    TradeEntity updatedTradeEntity = tradeRepository.save(existing);
    return mapToDto(updatedTradeEntity);
  }

  private TradeEntity getTradeEntity(Integer id) {
    return tradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trade not found with id: " + id));
  }

  private TradeDto mapToDto(TradeEntity entity) {
    return modelMapper.map(entity, TradeDto.class);
  }
}
