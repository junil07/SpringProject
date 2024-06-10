package com.example.demo.seller.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderlist is a Querydsl query type for Orderlist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderlist extends EntityPathBase<Orderlist> {

    private static final long serialVersionUID = 1798066679L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderlist orderlist = new QOrderlist("orderlist");

    public final com.example.demo.admin.Entity.QBuyer buyer;

    public final StringPath orderlistAddress = createString("orderlistAddress");

    public final DateTimePath<java.time.LocalDateTime> orderlistDate = createDateTime("orderlistDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> orderlistId = createNumber("orderlistId", Integer.class);

    public final NumberPath<Integer> orderlistTprice = createNumber("orderlistTprice", Integer.class);

    public QOrderlist(String variable) {
        this(Orderlist.class, forVariable(variable), INITS);
    }

    public QOrderlist(Path<? extends Orderlist> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderlist(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderlist(PathMetadata metadata, PathInits inits) {
        this(Orderlist.class, metadata, inits);
    }

    public QOrderlist(Class<? extends Orderlist> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyer = inits.isInitialized("buyer") ? new com.example.demo.admin.Entity.QBuyer(forProperty("buyer")) : null;
    }

}

