package com.example.Demo1.repositories;

import com.example.Demo1.entities.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Integer> {
}
