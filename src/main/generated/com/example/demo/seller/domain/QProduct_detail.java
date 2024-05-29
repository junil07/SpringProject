package com.example.demo.seller.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct_detail is a Querydsl query type for Product_detail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct_detail extends EntityPathBase<Product_detail> {

    private static final long serialVersionUID = 361966454L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct_detail product_detail = new QProduct_detail("product_detail");

    public final QProduct product;

    public final StringPath productDetailAs = createString("productDetailAs");

    public final StringPath productDetailColor = createString("productDetailColor");

    public final StringPath productDetailCountry = createString("productDetailCountry");

    public final NumberPath<Double> productDetailHeight = createNumber("productDetailHeight", Double.class);

    public final NumberPath<Integer> productDetailId = createNumber("productDetailId", Integer.class);

    public final StringPath productDetailMaker = createString("productDetailMaker");

    public final StringPath productDetailMate = createString("productDetailMate");

    public final StringPath productDetailSize = createString("productDetailSize");

    public final StringPath productDetailStandard = createString("productDetailStandard");

    public final StringPath productDetailWarning = createString("productDetailWarning");

    public final StringPath productDetailYear = createString("productDetailYear");

    public QProduct_detail(String variable) {
        this(Product_detail.class, forVariable(variable), INITS);
    }

    public QProduct_detail(Path<? extends Product_detail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct_detail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct_detail(PathMetadata metadata, PathInits inits) {
        this(Product_detail.class, metadata, inits);
    }

    public QProduct_detail(Class<? extends Product_detail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

