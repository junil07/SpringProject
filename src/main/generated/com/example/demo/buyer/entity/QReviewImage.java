package com.example.demo.buyer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewImage is a Querydsl query type for ReviewImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewImage extends EntityPathBase<ReviewImage> {

    private static final long serialVersionUID = -283201331L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewImage reviewImage = new QReviewImage("reviewImage");

    public final QReview review;

    public final StringPath reviewImageExtension = createString("reviewImageExtension");

    public final StringPath reviewImageName = createString("reviewImageName");

    public final NumberPath<Integer> reviewImageSize = createNumber("reviewImageSize", Integer.class);

    public final StringPath reviewImageSname = createString("reviewImageSname");

    public QReviewImage(String variable) {
        this(ReviewImage.class, forVariable(variable), INITS);
    }

    public QReviewImage(Path<? extends ReviewImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewImage(PathMetadata metadata, PathInits inits) {
        this(ReviewImage.class, metadata, inits);
    }

    public QReviewImage(Class<? extends ReviewImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

