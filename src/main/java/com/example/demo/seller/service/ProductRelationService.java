package com.example.demo.seller.service;

import com.example.demo.seller.DTO.ProductDTO;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.ProductRelation;
import com.example.demo.seller.repository.ProductRelationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductRelationService {

    private final ProductRelationRepository productRelationRepository;

    @Autowired
    public ProductRelationService(ProductRelationRepository productRelationRepository) {
        this.productRelationRepository = productRelationRepository;
    }
    @Autowired
    private ProductService productService;

    @Transactional
    public void addRelationProduct(ProductRelation productRelation) {
        productRelationRepository.save(productRelation);
    }

    //relationProduct Two에서 1인 값만 뽑음
    public List<ProductRelation> getRelationProductIdList(String sellerId) {

        List<ProductRelation> productRelations = productRelationRepository.findByProductRelationTwo(1);

        List<ProductRelation> filteredProductRelations = productRelations.stream()
                .filter(productRelation -> productRelation.getProductRelationCode().contains(sellerId))
                .toList();

        return filteredProductRelations;
    }

    //relationProduct One에 속하는 List 반환
    public List<ProductRelation> getRelationProductList(int one) {
        return productRelationRepository.findByProductRelationOne(one);
    }

    //pk로 productRelation 하나 가져옴
    public ProductRelation getRelationProduct(long pk) {
        return productRelationRepository.findById(pk).orElse(null);
    }

    //code로 productRelation 하나 가져옴
    public ProductRelation getRelationProductByCode(String productRelationCode) {
        return productRelationRepository.findByProductRelationCode(productRelationCode);
    }

    //연관상품 추가 & 수정- 코드 변경
    public void saveRelationProduct(String relationName, String relationNum, List<String> relationList, String sellerId) {
        long longNum = 0;
        //수정인 경우
        if (!relationNum.isEmpty()) {
            //long 변환
            longNum = Long.parseLong(relationNum);
            //one 값 찾기
            ProductRelation productRelation = getRelationProduct(longNum);
            int one = productRelation.getProductRelationOne();
            //one 값에 해당하는 기존 연관상품들 가져오기
            List<ProductRelation> productRelationList = productRelationRepository.findByProductRelationOne(one);
            //수정된 테이블에 값이 삭제되었을 경우, N코드로 변환
            //N코드로 변환해야 할 코드를 담는 리스트 초기화 (기존 테이블을 코드만 뽑아 리스트로 변환)
            List<String> codeN = new ArrayList<>();
            for (int i = 0; i < productRelationList.size(); i++) {
                codeN.add(productRelationList.get(i).getProductRelationCode());
            }

            //기존 테이블 행의 수 만큼 반복
            for(int i = 0; i < productRelationList.size(); i++){
                String originCode = productRelationList.get(i).getProductRelationCode();
                //수정된 테이블 행의 수 만큼 반복
                for(int j = 0; j < relationList.size(); j++) {
                    String newCode = relationList.get(j);
                    //중복되는 값(=다시 저장되어야 하는 값)은 삭제
                    if(originCode.equals(newCode)) {
                        codeN.remove(newCode);
                    }
                }
            }

            //연관상품에서 삭제된 상품은 다시 N코드로 변환
            for(int i = 0; i < codeN.size(); i++) {
                Product product = new Product();
                //code로 product 반환
                product = productService.getProductByCode(codeN.get(i));
                //N코드 세팅
                product.setProductCode("N" + product.getProductId() + "-" + sellerId);
                //ProductDTO 변환
                ProductDTO productDTO = productService.getProductDTO(product);
                //업데이트
                productService.addProduct(productDTO);
            }
            //삭제!!!!!!!
            productRelationRepository.deleteAll(productRelationList);
        }

        int oneMax = productRelationRepository.findMaxOne()+1;
        int twoMax = 1;
        for (int i = 0; i < relationList.size(); i++) {
            Product product = productService.getProductByCode(relationList.get(i));
            ProductDTO productDTO = productService.getProductDTO(product);
            String code = updateRelationProduct(sellerId, oneMax, twoMax);
            productDTO.setProductCode(code);
            //product 코드 수정 & 저장!!!!!!
            productService.addProduct(productDTO);

            //productRelation 세팅
            ProductRelation productRelation = new ProductRelation();
            productRelation.setProductRelationCode(code);
            productRelation.setProductRelationName(relationName);
            productRelation.setProductRelationOne(oneMax);
            productRelation.setProductRelationTwo(twoMax);
            productRelation.setProduct(product);
            //productRelation 추가!!!!!!
            addRelationProduct(productRelation);

            //값 증가
            twoMax++;
        }
    }

    //연관 상품 삭제
    public void delRelationProduct(String productRelationCode, String sellerId) {

        //one 값 찾기
        ProductRelation productRelation = getRelationProductByCode(productRelationCode);
        int one = productRelation.getProductRelationOne();
        //one 값에 해당하는 기존 연관상품들 가져오기
        List<ProductRelation> productRelationList = productRelationRepository.findByProductRelationOne(one);

        //기존 테이블을 코드만 뽑아 리스트로 변환
        List<String> codeList = new ArrayList<>();
        for (int i = 0; i < productRelationList.size(); i++) {
            codeList.add(productRelationList.get(i).getProductRelationCode());
        }

        //연관상품 리스트 삭제
        productRelationRepository.deleteAll(productRelationList);
        //상품들 N코드로 업데이트
        for(int i = 0; i < codeList.size(); i++) {
            Product product = new Product();
            //code로 product 반환
            product = productService.getProductByCode(codeList.get(i));
            //N코드 세팅
            product.setProductCode("N" + product.getProductId() + "-" + sellerId);
            //ProductDTO 변환
            ProductDTO productDTO = productService.getProductDTO(product);
            //업데이트
            productService.addProduct(productDTO);
        }
    }

    //new 연관 상품 코드 생성
    private String updateRelationProduct(String sellerId, int oneMax, int twoMax) {
        return "C" + oneMax + twoMax + "-" + sellerId;
    }

    public List<Product> convertProductList(List<ProductRelation> productRelationList) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < productRelationList.size(); i++) {
            Product product = productService.getProduct(productRelationList.get(i).getProduct().getProductId());
            productList.add(product);
        }

        return productList;
    }


}