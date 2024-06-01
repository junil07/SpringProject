package com.example.demo.seller.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderitem is a Querydsl query type for Orderitem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderitem extends EntityPathBase<Orderitem> {

    private static final long serialVersionUID = 1797987436L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderitem orderitem = new QOrderitem("orderitem");

    public final StringPath orderitemCase = createString("orderitemCase");

    public final DatePath<java.time.LocalDate> orderitemDate = createDate("orderitemDate", java.time.LocalDate.class);

    public final StringPath orderitemDstatus = createString("orderitemDstatus");

    public final NumberPath<Integer> orderitemId = createNumber("orderitemId", Integer.class);

    public final NumberPath<Integer> orderitemPcount = createNumber("orderitemPcount", Integer.class);

    public final NumberPath<Integer> orderitemPrice = createNumber("orderitemPrice", Integer.class);

    public final StringPath orderitemPstatus = createString("orderitemPstatus");

    public final QOrderlist orderlist;

    public final QProduct product;

    public QOrderitem(String variable) {
        this(Orderitem.class, forVariable(variable), INITS);
    }

    public QOrderitem(Path<? extends Orderitem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderitem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderitem(PathMetadata metadata, PathInits inits) {
        this(Orderitem.class, metadata, inits);
    }

    public QOrderitem(Class<? extends Orderitem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderlist = inits.isInitialized("orderlist") ? new QOrderlist(forProperty("orderlist"), inits.get("orderlist")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

