package com.example.demo.buyer.entity;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.seller.domain.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "review")
public class Review {
    @Id
    private Integer reviewId;

    @Column
    private int reviewRating;

    @Column
    private String reviewContent;

    @Column
    private int reviewGood;

    @Column
    private String reviewDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="PRODUCT_ID",referencedColumnName = "productId")
    private Product product;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="BUYER_ID")
    private Buyer buyer;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImages;

    public List<ReviewImage> getReviewImages() {
        return reviewImages;
    }

    public void setReviewImages(List<ReviewImage> reviewImages) {
        this.reviewImages = reviewImages;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getReviewGood() {
        return reviewGood;
    }

    public void setReviewGood(int reviewGood) {
        this.reviewGood = reviewGood;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
