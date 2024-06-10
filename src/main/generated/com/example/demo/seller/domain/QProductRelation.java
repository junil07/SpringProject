package com.example.demo.seller.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductRelation is a Querydsl query type for ProductRelation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductRelation extends EntityPathBase<ProductRelation> {

    private static final long serialVersionUID = -1774712618L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductRelation productRelation = new QProductRelation("productRelation");

    public final QProduct product;

    public final StringPath productRelationCode = createString("productRelationCode");

    public final NumberPath<Long> productRelationId = createNumber("productRelationId", Long.class);

    public final StringPath productRelationName = createString("productRelationName");

    public final NumberPath<Integer> productRelationOne = createNumber("productRelationOne", Integer.class);

    public final NumberPath<Integer> productRelationTwo = createNumber("productRelationTwo", Integer.class);

    public QProductRelation(String variable) {
        this(ProductRelation.class, forVariable(variable), INITS);
    }

    public QProductRelation(Path<? extends ProductRelation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductRelation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductRelation(PathMetadata metadata, PathInits inits) {
        this(ProductRelation.class, metadata, inits);
    }

    public QProductRelation(Class<? extends ProductRelation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

