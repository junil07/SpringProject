package com.example.demo.buyer.DTO;

public class ReviewImageDTO {
    private Integer reviewId;
    private Integer productId;
    private String buyerId;
    private int reviewRating;
    private String reviewContent;
    private int reviewGood;
    private String reviewDate;
    private String reviewImageName;
    private String reviewImageExtension;

    public ReviewImageDTO(Integer reviewId, Integer productId, String buyerId, int reviewRating, String reviewContent,
                          int reviewGood, String reviewDate, String reviewImageName, String reviewImageExtension){
        this.reviewId = reviewId;
        this.productId = productId;
        this.buyerId = buyerId;
        this.reviewRating = reviewRating;
        this.reviewContent = reviewContent;
        this.reviewGood = reviewGood;
        this.reviewDate = reviewDate;
        this.reviewImageName = reviewImageName;
        this.reviewImageExtension = reviewImageExtension;
    }
}
