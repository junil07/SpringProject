package com.example.demo.buyer.service;

import com.example.demo.buyer.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public void deleteReviewById(Integer reviewId){
        reviewRepository.deleteById(reviewId);
    }
}
