package com.example.Demo1.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TradeDto {
  private Integer id;
  @NotNull
  private String type;
  @NotNull
  private Integer userId;
  @NotNull
  private String symbol;
  @NotNull
  private Integer shares;
  @NotNull
  private Integer price;
  @NotNull
  private Integer timestamp;
}
