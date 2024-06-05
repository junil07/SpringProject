package com.example.demo.buyer.entity;

import jakarta.persistence.*;

@Entity
@Table(name="review_image")
public class ReviewImage {
    @Id
    private String reviewImageSname;

    @Column
    private String reviewImageName;

    @Column
    private String reviewImageExtension;

    @Column
    private int reviewImageSize;

    @ManyToOne
    @JoinColumn(name="REVIEW_ID")
    private Review review;

    public String getReviewImageSname() {
        return reviewImageSname;
    }

    public void setReviewImageSname(String reviewImageSname) {
        this.reviewImageSname = reviewImageSname;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public int getReviewImageSize() {
        return reviewImageSize;
    }

    public void setReviewImageSize(int reviewImageSize) {
        this.reviewImageSize = reviewImageSize;
    }

    public String getReviewImageExtension() {
        return reviewImageExtension;
    }

    public void setReviewImageExtension(String reviewImageExtension) {
        this.reviewImageExtension = reviewImageExtension;
    }

    public String getReviewImageName() {
        return reviewImageName;
    }

    public void setReviewImageName(String reviewImageName) {
        this.reviewImageName = reviewImageName;
    }
}
