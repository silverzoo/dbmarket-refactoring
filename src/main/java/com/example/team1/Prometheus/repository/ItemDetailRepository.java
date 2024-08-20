package com.example.team1.Prometheus.repository;

import com.example.team1.Prometheus.entity.ItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface ItemDetailRepository extends JpaRepository<ItemDetail, Long> {
}
