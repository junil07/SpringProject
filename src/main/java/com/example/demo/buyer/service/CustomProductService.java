package com.example.demo.buyer.service;

import com.example.demo.buyer.repository.CustomQueryRepository;
import com.example.demo.seller.repository.ProductRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomProductService {

    @Autowired
    private CustomQueryRepository customQueryRepository;

    public List<Map<String, Object>> getProductSummary() {
        List<Object[]> productList = customQueryRepository.findProductOrderSummary(); // 예시로 데이터베이스로부터 가져온 List<Object[]> 데이터

        // Product 정보를 저장할 List<Map<String, Object>> 생성
        List<Map<String, Object>> productSummaryList = new ArrayList<>();

        for (Object[] row : productList) {
            Map<String, Object> productSummary = new HashMap<>();

            productSummary.put("productId", ((Number) row[0]).intValue()); // 제품 ID
            productSummary.put("productCode", (String) row[1]);
            productSummary.put("productName", (String) row[2]); // 제품 이름
            productSummary.put("productPrice", ((Number) row[3]).intValue()); // 제품 가격
            productSummary.put("totalCount", ((Number) row[4]).intValue()); // 총 수량
            productSummary.put("productImageSname", (String) row[5]); // 제품 이미지 이름
            productSummary.put("productImageExtension", (String) row[6]); // 제품 이미지 확장자
            productSummary.put("productDetailMaker", (String) row[7]);

            // 필요한 필드들을 추출하여 Map에 저장
            productSummaryList.add(productSummary);
        }

        return productSummaryList;
    }
}