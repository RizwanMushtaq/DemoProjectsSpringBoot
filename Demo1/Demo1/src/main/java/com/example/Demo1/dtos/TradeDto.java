package com.example.Demo1.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradeDto {
  private Integer id;
  @NotNull
  private String type;
  @NotNull
  private Integer userId;
  @NotNull
  private String symbol;
  @NotNull
  private String shares;
  @NotNull
  private String price;
  @NotNull
  private Integer timestamp;
}
