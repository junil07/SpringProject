package com.example.demo.seller.service;

import com.example.demo.buyer.service.CategoryService;
import com.example.demo.seller.DTO.ProductDTO;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.Product_detail;
import com.example.demo.seller.repository.ProductRepository;
import com.example.demo.seller.repository.Product_detailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements SellerService_ {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Autowired
    private Product_detailRepository product_detailRepository;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    //상품 하나 조회
    public Product getProduct(String productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public List<Product_detail> getProductDetail(String productId) {
        return product_detailRepository.findByProduct_ProductId(productId);
    }

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        productRepository.save(product);
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        // DTO로부터 데이터를 엔티티로 변환하여 설정
        product.setProductName(productDTO.getProductName());
        product.setProductExplain(productDTO.getProductExplain());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductDiscount(productDTO.getProductDiscount());
        product.setProductHashtag(productDTO.getProductHashtag());
        product.setProductActivation(productDTO.getProductActivation());
        product.setProductId(generateUniqueId(productDTO.getSellerId(), productDTO.getProductId()));

        // 나머지 필드 설정 (Seller, Category 등)
        product.setSeller(sellerService.getSeller("xx"));
        product.setCategory(categoryService.getCategory(productDTO.getCategoryId()));
        return product;
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        // DTO로부터 데이터를 엔티티로 변환하여 설정
        productDTO.setProductName(product.getProductName());
        productDTO.setProductExplain(product.getProductExplain());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductDiscount(product.getProductDiscount());
        productDTO.setProductHashtag(product.getProductHashtag());
        productDTO.setProductActivation(product.getProductActivation());
        productDTO.setProductId(product.getProductId());

        // 나머지 필드 설정 (Seller, Category 등)
        //productDTO.setSellerId(product.getSeller());
        //productDTO.setCategoryId(categoryService.getCategory(productDTO.getCategoryId()));
        return productDTO;
    }

    public String generateUniqueId(String seller, String id) {
        //중복방지 예외처리 할 것
        return seller + id;

    }







}