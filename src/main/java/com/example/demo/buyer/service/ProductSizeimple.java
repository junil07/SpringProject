package com.example.demo.buyer.service;

import com.example.demo.buyer.DTO.ProductViewDto;
import com.example.demo.buyer.repository.ProductViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductSizeimple implements BuyerService{

    @Autowired
    private ProductViewRepository productViewRepository;

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public List getRows() {
        return null;
    }

    @Override
    public List<String> getRowParamOne(String productId) {
        List<ProductViewDto> productViewDtos = productViewRepository.findProductDetailSizeByProductId(productId);
        List<String> sizes = new ArrayList<>();

        // ProductViewDto 리스트를 순회하면서 각 객체에서 숫자를 추출하여 리스트에 추가
        for (ProductViewDto dto : productViewDtos) {
            // ProductViewDto에서 숫자를 추출하는 코드를 작성해야 함
            String sizeString = dto.getProductDetailSize(); // 예시일 뿐, 실제로는 getProductDetailSize 메서드가 존재하지 않을 수 있음

            // sizeString이 null이 아니고 비어있지 않을 때만 처리
            if (sizeString != null && !sizeString.isEmpty()) {
                // 쉼표로 구분된 문자열을 분할하여 각 숫자를 리스트에 추가
                String[] sizeArray = sizeString.split(",");
                sizes.addAll(Arrays.asList(sizeArray));
            }
        }

        return sizes;
    }
}
