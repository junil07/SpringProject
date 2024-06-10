package com.example.demo.buyer.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> findProductOrderSummary() {
        return entityManager.createQuery(
                "SELECT " +
                        "p.productId, " +
                        "p.productCode, "+
                        "p.productName, " +
                        "p.productPrice, " +
                        "p.productDiscount, " +
                        "SUM(oi.orderitemPcount) AS total_count, " +
                        "pi.productImageSname, " +
                        "pi.productImageExtension, " +
                        "pd.productDetailMaker, " +
                        "p.productCode " +
                        "FROM " +
                        "Orderitem oi " +
                        "JOIN oi.product p " +
                        "LEFT JOIN ProductImage pi ON p.productId = pi.product.productId " +
                        "LEFT JOIN Product_detail pd ON p.productId = pd.product.productId " +
                        "GROUP BY " +
                        "p.productId, p.productCode, p.productName, p.productPrice,p.productDiscount ,pi.productImageSname, pi.productImageExtension, pd.productDetailMaker " +
                        "ORDER BY " +
                        "total_count DESC "+
                        "LIMIT 4"
        ).getResultList();
    }
}