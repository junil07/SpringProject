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

    public final QOrder_list order_list;

    public final StringPath ORDERITEM_DSTATUS = createString("ORDERITEM_DSTATUS");

    public final NumberPath<Integer> ORDERITEM_ID = createNumber("ORDERITEM_ID", Integer.class);

    public final NumberPath<Integer> ORDERITEM_PCOUNT = createNumber("ORDERITEM_PCOUNT", Integer.class);

    public final NumberPath<Integer> ORDERITEM_PRICE = createNumber("ORDERITEM_PRICE", Integer.class);

    public final StringPath ORDERITEM_PSTATUS = createString("ORDERITEM_PSTATUS");

    public final QProduct productid;

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
        this.order_list = inits.isInitialized("order_list") ? new QOrder_list(forProperty("order_list"), inits.get("order_list")) : null;
        this.productid = inits.isInitialized("productid") ? new QProduct(forProperty("productid"), inits.get("productid")) : null;
    }

}

