package com.elice.team1.prometheus.item.repository;

import com.elice.team1.prometheus.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO 데이터베이스 연결
public interface ItemPostRepository extends JpaRepository<Item, Long>{
}


