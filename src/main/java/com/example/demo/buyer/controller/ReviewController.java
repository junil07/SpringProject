package com.example.demo.buyer.controller;

import com.example.demo.buyer.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Integer reviewId) {
        System.out.print(reviewId);
        try {
            reviewService.deleteReviewById(reviewId);
            System.out.print("sussece~~~@@@!@@#");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("리뷰 삭제에 실패했습니다.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addReview(
            @RequestParam("productId") Integer productId,
            @RequestParam("rating") Integer rating,
            @RequestParam("content") String content,
            @RequestPart(value = "files[]", required = false) List<MultipartFile> files) {
        System.out.print("Product ID: " + productId + ", Rating: " + rating + ", Content: " + content);

        if (files != null) {
            System.out.println(", Number of files: " + files.size());
        } else {
            System.out.println(", No files attached.");
        }

        try {
            reviewService.addReview(productId, rating, content, files);
            return ResponseEntity.ok("리뷰가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace(); // 오류 출력
            return ResponseEntity.status(500).body("리뷰 등록에 실패했습니다.");
        }
    }
}