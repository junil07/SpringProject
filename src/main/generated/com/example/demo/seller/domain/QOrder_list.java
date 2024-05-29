package com.example.demo.seller.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder_list is a Querydsl query type for Order_list
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder_list extends EntityPathBase<Order_list> {

    private static final long serialVersionUID = -106433724L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder_list order_list = new QOrder_list("order_list");

    public final com.example.demo.admin.Entity.QBuyer buyer;

    public final StringPath BUYER_LIST_ID = createString("BUYER_LIST_ID");

    public final StringPath ORDER_LIST_ADDRESS = createString("ORDER_LIST_ADDRESS");

    public final DatePath<java.time.LocalDate> ORDER_LIST_DATE = createDate("ORDER_LIST_DATE", java.time.LocalDate.class);

    public final NumberPath<Integer> ORDER_LIST_ID = createNumber("ORDER_LIST_ID", Integer.class);

    public final NumberPath<Integer> ORDER_LIST_TPRICE = createNumber("ORDER_LIST_TPRICE", Integer.class);

    public QOrder_list(String variable) {
        this(Order_list.class, forVariable(variable), INITS);
    }

    public QOrder_list(Path<? extends Order_list> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder_list(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder_list(PathMetadata metadata, PathInits inits) {
        this(Order_list.class, metadata, inits);
    }

    public QOrder_list(Class<? extends Order_list> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyer = inits.isInitialized("buyer") ? new com.example.demo.admin.Entity.QBuyer(forProperty("buyer")) : null;
    }

}

