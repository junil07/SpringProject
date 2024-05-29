package com.example.demo.admin.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBuyer is a Querydsl query type for Buyer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBuyer extends EntityPathBase<Buyer> {

    private static final long serialVersionUID = -285630175L;

    public static final QBuyer buyer = new QBuyer("buyer");

    public final NumberPath<Short> buyerActivation = createNumber("buyerActivation", Short.class);

    public final StringPath buyerAddress = createString("buyerAddress");

    public final DatePath<java.util.Date> buyerBirth = createDate("buyerBirth", java.util.Date.class);

    public final StringPath buyerEmail = createString("buyerEmail");

    public final NumberPath<Short> buyerGrade = createNumber("buyerGrade", Short.class);

    public final StringPath buyerId = createString("buyerId");

    public final DatePath<java.util.Date> buyerLastLogin = createDate("buyerLastLogin", java.util.Date.class);

    public final StringPath buyerName = createString("buyerName");

    public final StringPath buyerPassword = createString("buyerPassword");

    public final StringPath buyerPhoneNum = createString("buyerPhoneNum");

    public QBuyer(String variable) {
        super(Buyer.class, forVariable(variable));
    }

    public QBuyer(Path<? extends Buyer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBuyer(PathMetadata metadata) {
        super(Buyer.class, metadata);
    }

}

