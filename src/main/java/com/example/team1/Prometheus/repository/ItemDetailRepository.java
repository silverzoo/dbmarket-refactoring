package com.example.team1.Prometheus.repository;

import com.example.team1.Prometheus.entity.Item;
import com.example.team1.Prometheus.entity.ItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ItemDetailRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUserId(Long userId);

    List<Item> findByCategoryId(Long categoryId);
}
