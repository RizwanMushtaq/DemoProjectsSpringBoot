package com.example.Demo1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TradeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String type;
  private int userId;
  private String symbol;
  private String shares;
  private String price;
  private long timestamp;
}
