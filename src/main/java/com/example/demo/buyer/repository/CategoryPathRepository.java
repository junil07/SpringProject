package com.example.demo.buyer.repository;

import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.entity.CategoryPath;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryPathRepository extends JpaRepository<CategoryPath, Long> {

}
