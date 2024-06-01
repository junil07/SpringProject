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
                        "p.productName, " +
                        "p.productPrice, " +
                        "SUM(oi.orderitemPcount) AS total_count, " +
                        "pi.productImageName, " +
                        "pi.productImageExtension, " +
                        "pd.productDetailMaker " +
                        "FROM " +
                        "Orderitem oi " +
                        "JOIN oi.productId p " +
                        "LEFT JOIN ProductImage pi ON p.productId = pi.productId.productId " +
                        "LEFT JOIN Product_detail pd ON p.productId = pd.productId.productId " +
                        "GROUP BY " +
                        "p.productId, p.productName, p.productPrice, pi.productImageName, pi.productImageExtension, pd.productDetailMaker " +
                        "ORDER BY " +
                        "total_count DESC "+
                        "LIMIT 4"
        ).getResultList();
    }
}