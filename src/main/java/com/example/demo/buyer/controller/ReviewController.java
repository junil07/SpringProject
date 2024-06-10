package com.example.demo.buyer.controller;

import com.example.demo.admin.security.SecurityServiceImple;
import com.example.demo.buyer.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private SecurityServiceImple securityService;
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
    public ResponseEntity<String> addReview(@AuthenticationPrincipal User user, Principal principal,
                                            @RequestParam("productId") Integer productId,
                                            @RequestParam("rating") Integer rating,
                                            @RequestParam("content") String content,
                                            @RequestPart(value = "files[]", required = false) List<MultipartFile> files) {
        System.out.print("Product ID: " + productId + ", Rating: " + rating + ", Content: " + content);

        String buyerId="";
        if (principal != null) { // 로그인이 되었을 때 이야기임 로그인이 안되었을 때 예외는 if else 밖에
            if (securityService.hasRole(user, "ROLE_BUYER")) { // 만약 '구매자'의 권한을 갖고 있다면
                buyerId = user.getUsername();
            } else { // 다른 권한을 갖고 있거나
            }
        }else{
            buyerId=null;
        }

        if (files != null) {
            System.out.println(", Number of files: " + files.size());
        } else {
            System.out.println(", No files attached.");
        }

        try {
            reviewService.addReview(productId,buyerId ,rating, content, files);
            return ResponseEntity.ok("리뷰가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace(); // 오류 출력
            return ResponseEntity.status(500).body("리뷰 등록에 실패했습니다.");
        }
    }
}