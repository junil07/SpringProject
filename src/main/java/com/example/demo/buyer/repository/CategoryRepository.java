package com.example.demo.buyer.repository;

import com.example.demo.buyer.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}