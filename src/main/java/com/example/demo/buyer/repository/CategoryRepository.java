package com.example.demo.buyer.repository;

import com.example.demo.buyer.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryParentId(Long parentId);

    //다인 수정
    Category findByCategoryId(Long category);
}
