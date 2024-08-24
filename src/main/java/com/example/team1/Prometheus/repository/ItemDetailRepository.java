package com.example.team1.Prometheus.repository;

import com.example.team1.Prometheus.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemDetailRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUserId(Long userId);
}
