package com.example.demo.buyer.repository;

import com.example.demo.buyer.entity.ReviewImage;
import jakarta.transaction.Transactional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewImageRepository extends JpaRepository<ReviewImage, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ReviewImage ri WHERE ri.review.reviewId = :reviewId")
    void deleteByReviewId(@Param("reviewId") Integer reviewId);

    List<ReviewImage> findByReviewReviewId(@Param("reviewId")Integer reviewId);
}
