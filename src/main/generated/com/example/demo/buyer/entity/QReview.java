package com.example.demo.buyer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 1602032942L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.example.demo.admin.Entity.QBuyer buyer;

    public final com.example.demo.seller.domain.QProduct product;

    public final StringPath reviewContent = createString("reviewContent");

    public final DateTimePath<java.time.LocalDateTime> reviewDate = createDateTime("reviewDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> reviewGood = createNumber("reviewGood", Integer.class);

    public final NumberPath<Integer> reviewId = createNumber("reviewId", Integer.class);

    public final ListPath<ReviewImage, QReviewImage> reviewImages = this.<ReviewImage, QReviewImage>createList("reviewImages", ReviewImage.class, QReviewImage.class, PathInits.DIRECT2);

    public final NumberPath<Integer> reviewRating = createNumber("reviewRating", Integer.class);

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyer = inits.isInitialized("buyer") ? new com.example.demo.admin.Entity.QBuyer(forProperty("buyer")) : null;
        this.product = inits.isInitialized("product") ? new com.example.demo.seller.domain.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

