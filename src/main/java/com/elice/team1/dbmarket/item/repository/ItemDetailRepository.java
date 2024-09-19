package com.elice.team1.dbmarket.item.repository;

import com.elice.team1.dbmarket.category.entity.Category;
import com.elice.team1.dbmarket.item.entity.Item;
import com.elice.team1.dbmarket.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemDetailRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUser(User user);

    List<Item> findByCategory(Category category);
}
