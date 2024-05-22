package com.example.demo.buyer.service;

import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.entity.CategoryPath;
import com.example.demo.buyer.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryService implements BuyerService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getRows() {
        List<Category> categories = categoryRepository.findAll();
        Map<Long, List<Category>> categoriesByParentId = categories.stream().collect(Collectors.groupingBy(Category::getCategoryParentId));

        for (Category category : categories) {
            category.setChildren(categoriesByParentId.get(category.getCategoryId()));
        }

        return categoriesByParentId.get(0L);
    }
}