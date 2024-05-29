package com.example.demo.buyer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryPath is a Querydsl query type for CategoryPath
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryPath extends EntityPathBase<CategoryPath> {

    private static final long serialVersionUID = 1665125273L;

    public static final QCategoryPath categoryPath1 = new QCategoryPath("categoryPath1");

    public final NumberPath<Integer> categoryDef = createNumber("categoryDef", Integer.class);

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final StringPath categoryName = createString("categoryName");

    public final StringPath categoryPath = createString("categoryPath");

    public QCategoryPath(String variable) {
        super(CategoryPath.class, forVariable(variable));
    }

    public QCategoryPath(Path<? extends CategoryPath> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryPath(PathMetadata metadata) {
        super(CategoryPath.class, metadata);
    }

}

