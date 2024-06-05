package com.example.demo.buyer.repository;

import com.example.demo.buyer.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductProductIdOrderByReviewIdDesc(Integer productId);

    void deleteReviewByReviewId(Integer reviewId);
}
