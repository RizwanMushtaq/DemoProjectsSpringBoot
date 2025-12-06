package com.example.Demo1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity
public class TradeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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
