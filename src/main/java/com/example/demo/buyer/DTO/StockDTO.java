package com.example.demo.buyer.DTO;

import com.example.demo.seller.domain.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDTO {
    private Integer stockId;
    private int stockSize;
    private int stockCount;
    private long productId;

    // 기본 생성자
    public StockDTO() {
    }

    // 모든 필드를 초기화하는 생성자
    public StockDTO(Integer stockId, int stockSize, int stockCount, long productId) {
        this.stockId = stockId;
        this.stockSize = stockSize;
        this.stockCount = stockCount;
        this.productId = productId;
    }


}

