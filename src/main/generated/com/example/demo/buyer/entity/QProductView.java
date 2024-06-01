package com.example.demo.buyer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductView is a Querydsl query type for ProductView
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductView extends EntityPathBase<ProductView> {

    private static final long serialVersionUID = 1180795934L;

    public static final QProductView productView = new QProductView("productView");

    public final NumberPath<Integer> categoryId = createNumber("categoryId", Integer.class);

    public final StringPath productDetailAs = createString("productDetailAs");

    public final StringPath productDetailColor = createString("productDetailColor");

    public final StringPath productDetailCountry = createString("productDetailCountry");

    public final NumberPath<Float> productDetailHeight = createNumber("productDetailHeight", Float.class);

    public final NumberPath<Integer> productDetailId = createNumber("productDetailId", Integer.class);

    public final StringPath productDetailMaker = createString("productDetailMaker");

    public final StringPath productDetailMate = createString("productDetailMate");

    public final StringPath productDetailSize = createString("productDetailSize");

    public final StringPath productDetailStandard = createString("productDetailStandard");

    public final StringPath productDetailWaring = createString("productDetailWaring");

    public final StringPath productDetailYear = createString("productDetailYear");

    public final NumberPath<Integer> productDiscount = createNumber("productDiscount", Integer.class);

    public final StringPath productExplain = createString("productExplain");

    public final StringPath productHashtag = createString("productHashtag");

    public final NumberPath<Integer> productId = createNumber("productId", Integer.class);

    public final StringPath productImageExtension = createString("productImageExtension");

    public final StringPath productImageName = createString("productImageName");

    public final NumberPath<Integer> productImageSize = createNumber("productImageSize", Integer.class);

    public final StringPath productImageSname = createString("productImageSname");

    public final StringPath productName = createString("productName");

    public final NumberPath<Integer> productPrice = createNumber("productPrice", Integer.class);

    public final StringPath sellerId = createString("sellerId");

    public QProductView(String variable) {
        super(ProductView.class, forVariable(variable));
    }

    public QProductView(Path<? extends ProductView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductView(PathMetadata metadata) {
        super(ProductView.class, metadata);
    }

}

