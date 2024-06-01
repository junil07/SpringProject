package com.example.demo.seller.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1393228614L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final com.example.demo.buyer.entity.QCategory category;

    public final NumberPath<Integer> productActivation = createNumber("productActivation", Integer.class);

    public final StringPath productCode = createString("productCode");

    public final NumberPath<Integer> productDiscount = createNumber("productDiscount", Integer.class);

    public final StringPath productExplain = createString("productExplain");

    public final StringPath productHashtag = createString("productHashtag");

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productName = createString("productName");

    public final NumberPath<Integer> productPrice = createNumber("productPrice", Integer.class);

    public final com.example.demo.admin.Entity.QSeller seller;

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.example.demo.buyer.entity.QCategory(forProperty("category")) : null;
        this.seller = inits.isInitialized("seller") ? new com.example.demo.admin.Entity.QSeller(forProperty("seller")) : null;
    }

}

