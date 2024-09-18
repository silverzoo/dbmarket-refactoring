package com.elice.team1.prometheus.item.repository;

import com.elice.team1.prometheus.category.entity.Category;
import com.elice.team1.prometheus.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemDetailRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUserId(Long userId);

    List<Item> findByCategory(Category category);
}
