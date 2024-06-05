package com.example.demo.buyer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = -1563027850L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCart cart = new QCart("cart");

    public final com.example.demo.admin.Entity.QBuyer buyer;

    public final NumberPath<Integer> cartId = createNumber("cartId", Integer.class);

    public final NumberPath<Integer> cartProductCount = createNumber("cartProductCount", Integer.class);

    public final NumberPath<Integer> cartProductSize = createNumber("cartProductSize", Integer.class);

    public final com.example.demo.seller.domain.QProduct product;

    public QCart(String variable) {
        this(Cart.class, forVariable(variable), INITS);
    }

    public QCart(Path<? extends Cart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCart(PathMetadata metadata, PathInits inits) {
        this(Cart.class, metadata, inits);
    }

    public QCart(Class<? extends Cart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyer = inits.isInitialized("buyer") ? new com.example.demo.admin.Entity.QBuyer(forProperty("buyer")) : null;
        this.product = inits.isInitialized("product") ? new com.example.demo.seller.domain.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

