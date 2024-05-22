package com.example.demo.buyer.service;

import com.example.demo.buyer.entity.CategoryPath;
import com.example.demo.buyer.repository.CategoryPathRepository;
import com.example.demo.buyer.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryPathServiceImple implements CategoryPathService{

    @Autowired
    private CategoryPathRepository categoryPathRepository;

    @Override
    public List<CategoryPath> getCategoryPath(){
        return categoryPathRepository.findAll();
    }

}