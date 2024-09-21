package com.elice.team1.dbmarket.category.repository;

import com.elice.team1.dbmarket.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
