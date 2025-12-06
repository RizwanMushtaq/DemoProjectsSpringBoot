package com.example.Demo1.services;

import com.example.Demo1.dtos.TradeDto;
import com.example.Demo1.entities.TradeEntity;
import com.example.Demo1.exceptions.ResourceNotFoundException;
import com.example.Demo1.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TradeServiceImplTest {
  @Mock
  private TradeRepository tradeRepository;
  @Mock
  private ModelMapper modelMapper;
  @InjectMocks
  private TradeServiceImpl tradeService;
  private TradeDto tradeDto;
  private TradeEntity tradeEntity;

  @BeforeEach
  void setup() {
    tradeDto = TradeDto
        .builder()
        .id(1)
        .type("BUY")
        .userId(123)
        .symbol("APL")
        .shares(10)
        .price(321)
        .build();

    tradeEntity = TradeEntity
        .builder()
        .id(1)
        .type("BUY")
        .userId(123)
        .symbol("APL")
        .shares(10)
        .price(321)
        .build();
  }

  @Test
  void testCreateTrade() {
    when(modelMapper.map(tradeDto, TradeEntity.class)).thenReturn(tradeEntity);
    when(tradeRepository.save(tradeEntity)).thenReturn(tradeEntity);
    when(modelMapper.map(tradeEntity, TradeDto.class)).thenReturn(tradeDto);

    TradeDto result = tradeService.create(tradeDto);

    assertNotNull(result);
    assertEquals("BUY", result.getType());
    verify(tradeRepository).save(tradeEntity);
    verify(modelMapper).map(tradeEntity, TradeDto.class);
  }

  @Test
  void testGetByIdNotFound() {
    when(tradeRepository.findById(1)).thenReturn(Optional.empty());
    assertThrows(ResourceNotFoundException.class,
        () -> tradeService.getById(1));
  }

  @Test
  void testGetByIdSuccess() {
    when(tradeRepository.findById(1)).thenReturn(Optional.of(tradeEntity));
    when(modelMapper.map(tradeEntity, TradeDto.class)).thenReturn(tradeDto);

    TradeDto result = tradeService.getById(1);

    assertNotNull(result);
    assertEquals(1, result.getId());
    verify(tradeRepository).findById(1);
  }

  @Test
  void testDeleteByIdSuccess() {
    when(tradeRepository.findById(1)).thenReturn(Optional.of(tradeEntity));
    when(modelMapper.map(tradeEntity, TradeDto.class)).thenReturn(tradeDto);

    TradeDto result = tradeService.deleteById(1);

    assertNotNull(result);
    verify(tradeRepository).deleteById(1);
  }

  @Test
  void testDeleteByIdNotFound() {
    when(tradeRepository.findById(1)).thenReturn(Optional.empty());
    assertThrows(ResourceNotFoundException.class,
        () -> tradeService.deleteById(1));
  }

  @Test
  void testUpdateByIdSuccess() {
    TradeDto updatedDtoInput = TradeDto.builder()
        .id(1)
        .type("SELL")
        .userId(100)
        .symbol("APL")
        .shares(10)
        .price(150)
        .build();
    TradeEntity existingEntity = TradeEntity.builder()
        .id(1)
        .type("BUY")
        .userId(100)
        .symbol("APL")
        .shares(10)
        .price(150)
        .build();
    when(tradeRepository.findById(1)).thenReturn(Optional.of(existingEntity));
    when(tradeRepository.save(existingEntity)).thenReturn(existingEntity);
    // Stub mapping from DTO -> Entity
    doAnswer(invocation -> {
      TradeDto source = invocation.getArgument(0);
      TradeEntity destination = invocation.getArgument(1);
      destination.setType(source.getType());
      destination.setUserId(source.getUserId());
      destination.setSymbol(source.getSymbol());
      destination.setShares(source.getShares());
      destination.setPrice(source.getPrice());
      destination.setTimestamp(source.getTimestamp());
      return destination;
    }).when(modelMapper).map(any(TradeDto.class), any(TradeEntity.class));
    // Stub mapping from Entity -> DTO
    doAnswer(invocation -> {
      TradeEntity e = invocation.getArgument(0);
      return TradeDto.builder()
          .id(e.getId())
          .type(e.getType())
          .userId(e.getUserId())
          .symbol(e.getSymbol())
          .shares(e.getShares())
          .price(e.getPrice())
          .timestamp(e.getTimestamp())
          .build();
    }).when(modelMapper).map(any(TradeEntity.class), eq(TradeDto.class));

    TradeDto result = tradeService.updateById(1, updatedDtoInput);

    assertNotNull(result);
    assertEquals("SELL", result.getType());
    verify(tradeRepository).findById(1);
    verify(tradeRepository).save(existingEntity);
    verify(modelMapper).map(any(TradeDto.class), any(TradeEntity.class));
    verify(modelMapper).map(any(TradeEntity.class), eq(TradeDto.class));
  }
}
