package com.example.team1.Prometheus.repository;

import com.example.team1.Prometheus.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO 데이터베이스 연결
public interface ItemPostRepository extends JpaRepository<Item, Long>{
}


