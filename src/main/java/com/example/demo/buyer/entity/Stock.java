package com.example.demo.buyer.entity;

import com.example.demo.seller.domain.Product;
import jakarta.persistence.*;

@Entity
@Table(name="stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;

    @Column(name="STOCK_SIZE")
    private int stockSize;
    @Column(name="STOCK_COUNT")
    private int stockCount;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID",referencedColumnName = "productId")
    private Product product;

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public int getStockSize() {
        return stockSize;
    }

    public void setStockSize(int stockSize) {
        this.stockSize = stockSize;
    }
}
