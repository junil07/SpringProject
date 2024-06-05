package com.example.demo.buyer.service;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.buyer.entity.Review;
import com.example.demo.buyer.entity.ReviewImage;
import com.example.demo.buyer.repository.ReviewImageRepository;
import com.example.demo.buyer.repository.ReviewRepository;
import com.example.demo.seller.repository.BuyerRepository;
import com.example.demo.seller.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewImageRepository reviewImageRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private BuyerRepository buyerRepository;


    @Transactional
    public void deleteReviewById(Integer reviewId) {
        // 부모 엔티티(Review) 삭제
        // 리뷰에 연결된 이미지들을 가져오기
        List<ReviewImage> reviewImages = reviewImageRepository.findByReviewReviewId(reviewId);

        // 이미지 파일 삭제
        for (ReviewImage reviewImage : reviewImages) {
            try {
                Path path = Paths.get("src/main/resources/static/assets/image/reviewImage" + File.separator + reviewImage.getReviewImageSname() + reviewImage.getReviewImageExtension());
                Files.deleteIfExists(path); // 파일이 존재하는 경우에만 삭제
            } catch (NoSuchFileException e) {
                System.err.format("%s: no such file or directory%n", e.getFile());
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }

        reviewRepository.deleteById(reviewId);
    }
    @Transactional
    public void addReview(Integer productId,Integer rating, String content, List<MultipartFile> files){
        Review review = new Review();

        review.setProduct(productService.getProduct(productId));
        review.setBuyer(buyerRepository.findByBuyerId("jiwon15"));
        review.setReviewRating(rating);
        review.setReviewContent(content);

        Review savedReview = reviewRepository.save(review);

        if(files !=null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                ReviewImage reviewImage = new ReviewImage();
                reviewImage.setReviewImageSname(UUID.randomUUID().toString());
                reviewImage.setReview(savedReview);
                reviewImage.setReviewImageName(file.getOriginalFilename());
                reviewImage.setReviewImageExtension("." + getFileExtension(file.getOriginalFilename()));
                reviewImage.setReviewImageSize((int) file.getSize());

                try {
                    Path path = Paths.get("src/main/resources/static/assets/image/reviewImage/", reviewImage.getReviewImageSname() + reviewImage.getReviewImageExtension());
                    Files.write(path, file.getBytes());

                    reviewImageRepository.save(reviewImage);
                } catch (IOException e) {

                }
            }
        }

    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
