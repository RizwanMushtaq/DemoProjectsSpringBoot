package com.example.Demo1.services;

import com.example.Demo1.dtos.TradeDto;
import com.example.Demo1.entities.TradeEntity;
import com.example.Demo1.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
//    assertEquals("BUY", result.getType());
//    verify(tradeRepository).save(tradeEntity);
//    verify(modelMapper).map(tradeEntity, TradeDto.class);
  }
}
