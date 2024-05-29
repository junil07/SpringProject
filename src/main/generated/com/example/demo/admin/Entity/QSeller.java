package com.example.demo.admin.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSeller is a Querydsl query type for Seller
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeller extends EntityPathBase<Seller> {

    private static final long serialVersionUID = 206937553L;

    public static final QSeller seller = new QSeller("seller");

    public final NumberPath<Short> sellerActivation = createNumber("sellerActivation", Short.class);

    public final StringPath sellerAddress = createString("sellerAddress");

    public final DateTimePath<java.util.Date> sellerBirth = createDateTime("sellerBirth", java.util.Date.class);

    public final StringPath sellerBnum = createString("sellerBnum");

    public final StringPath sellerEmail = createString("sellerEmail");

    public final StringPath sellerId = createString("sellerId");

    public final DateTimePath<java.util.Date> sellerLastLogin = createDateTime("sellerLastLogin", java.util.Date.class);

    public final StringPath sellerName = createString("sellerName");

    public final StringPath sellerPassword = createString("sellerPassword");

    public final StringPath sellerPhoneNum = createString("sellerPhoneNum");

    public QSeller(String variable) {
        super(Seller.class, forVariable(variable));
    }

    public QSeller(Path<? extends Seller> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeller(PathMetadata metadata) {
        super(Seller.class, metadata);
    }

}

