package com.example.Demo1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeDto {
  private String type;
  private int userId;
  private String symbol;
  private String shares;
  private String price;
  private long timestamp;
}
