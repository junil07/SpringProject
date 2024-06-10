package com.example.demo.seller.service;

import java.util.Arrays;
import java.util.List;

import com.example.demo.seller.DTO.ProductDetailDTO;
import com.example.demo.seller.domain.ProductImage;
import com.example.demo.seller.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.buyer.service.CategoryService;
import com.example.demo.seller.DTO.ProductDTO;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.Product_detail;
import com.example.demo.seller.repository.ProductRepository;
import com.example.demo.seller.repository.Product_detailRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService{

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

    @Autowired
    private ProductImageRepository productImageRepository;
//    @Override
    public List<Product> getProductList(String sellerId) {
        return productRepository.findBySellerNameContains(sellerId);
    }

    //상품 하나 조회
    public Product getProduct(long productId) {
        return productRepository.findByProductId(productId);
    }

    //상품 삭제
    public void delProduct(long productId) {
        productRepository.deleteById(productId);
    }

    //상품 등록할때 pk 조회
    public Product getProductByCode(String productCode) {
        return productRepository.findByProductCode(productCode);
    }

    //상품 DTO 변환
    public ProductDTO getProductDTO(Product product) {
        return CToDProduct(product);
    }

    //상품상세 DTO 변환
    public ProductDetailDTO getProductDetailDTO(Product_detail productDetail) {
        return CToDProductDetail(productDetail);
    }

    public Product_detail getProductDetail(Product product) {
        return product_detailRepository.findByProduct(product);
    }

//    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO) {
        Product product = CToEProduct(productDTO);
        productRepository.save(product);
    }

    @Transactional
    public void addProductDetail(ProductDetailDTO productDetailDTO) {
        Product_detail productDetail = CToEProductDetail(productDetailDTO);
        product_detailRepository.save(productDetail);
    }

    //상품코드 생성
    public String getCode(String sellerId) {
        long fkNum = productRepository.findMaxPrimaryKey() + 1;
        return "N" + fkNum + "-" + sellerId;
    }

    private Product CToEProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setProductId(productDTO.getProductId());
        product.setProductCode(productDTO.getProductCode());
        product.setProductName(productDTO.getProductName());
        product.setProductExplain(productDTO.getProductExplain());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductDiscount(productDTO.getProductDiscount());
        product.setProductHashtag(CToS(productDTO.getProductHashtag()));
        product.setProductActivation(productDTO.getProductActivation());

        // 나머지 필드 설정 (Seller, Category 등)
        product.setSeller(sellerService.getSeller(productDTO.getSellerId()));
        product.setCategory(categoryService.getCategory(productDTO.getCategoryId()));
        return product;
    }

    private ProductDTO CToDProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();

        // DTO로부터 데이터를 엔로 변환하여 설정
        productDTO.setProductId(product.getProductId());
        productDTO.setProductCode(product.getProductCode());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductExplain(product.getProductExplain());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductDiscount(product.getProductDiscount());
        productDTO.setProductHashtag(CToL(product.getProductHashtag()));
        productDTO.setProductActivation(product.getProductActivation());

        // 나머지 필드 설정 (Seller, Category 등)
        productDTO.setSellerId(product.getSeller().getSellerId());
        productDTO.setCategoryId(product.getCategory().getCategoryId());
        return productDTO;
    }

    private Product_detail CToEProductDetail(ProductDetailDTO productDetailDTO) {
        Product_detail productDetail = new Product_detail();
        // DTO로부터 데이터를 엔티티로 변환하여 설정
        productDetail.setProductDetailId(productDetailDTO.getProductDetailId());
        productDetail.setProductDetailMate(productDetailDTO.getProductDetailMate());
        productDetail.setProductDetailColor(productDetailDTO.getProductDetailColor());
        productDetail.setProductDetailHeight(productDetailDTO.getProductDetailHeight());
        productDetail.setProductDetailMaker(productDetailDTO.getProductDetailMaker());
        productDetail.setProductDetailCountry(productDetailDTO.getProductDetailCountry());
        productDetail.setProductDetailSize(productDetailDTO.getProductDetailSize());
        productDetail.setProductDetailYear(productDetailDTO.getProductDetailYear());
        productDetail.setProductDetailWarning(productDetailDTO.getProductDetailWarning());
        productDetail.setProductDetailAs(productDetailDTO.getProductDetailAs());
        productDetail.setProductDetailStandard(productDetailDTO.getProductDetailStandard());
        productDetail.setProduct(getProduct(productDetailDTO.getProductId()));
        return productDetail;
    }

    private ProductDetailDTO CToDProductDetail(Product_detail productDetail) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        // DTO로부터 데이터를 엔티티로 변환하여 설정
        productDetailDTO.setProductDetailId(productDetail.getProductDetailId());
        productDetailDTO.setProductDetailMate(productDetail.getProductDetailMate());
        productDetailDTO.setProductDetailColor(productDetail.getProductDetailColor());
        productDetailDTO.setProductDetailHeight(productDetail.getProductDetailHeight());
        productDetailDTO.setProductDetailMaker(productDetail.getProductDetailMaker());
        productDetailDTO.setProductDetailCountry(productDetail.getProductDetailCountry());
        productDetailDTO.setProductDetailSize(productDetail.getProductDetailSize());
        productDetailDTO.setProductDetailYear(productDetail.getProductDetailYear());
        productDetailDTO.setProductDetailWarning(productDetail.getProductDetailWarning());
        productDetailDTO.setProductDetailAs(productDetail.getProductDetailAs());
        productDetailDTO.setProductDetailStandard(productDetail.getProductDetailStandard());

        productDetailDTO.setProductId(productDetail.getProduct().getProductId());

        return productDetailDTO;
    }

    //리스트로 변경
    private List<String> CToL(String hashtag) {
        return Arrays.asList(hashtag.split(","));
    }

    //문자열로 변경
    private String CToS(List<String> hashtag) {
        return String.join(",", hashtag);
    }

    public List<Product> getProductsByCategoryName(String categoryName) {
        return productRepository.findByCategory_CategoryName(categoryName);
    }

    public List<ProductImage> getProductImagesByCategoryName(String categoryName) {
        return productImageRepository.findByProduct_Category_CategoryName(categoryName);
      
    //판매자 아이디 가져오기
    private String getSellerId(String origin) {

            String delimiter = "-";
            // 특정 문자(-) 이후의 문자열 추출
            return getStringAfterDelimiter(origin, delimiter);
    }

    private static String getStringAfterDelimiter (String text, String delimiter){
        int index = text.indexOf(delimiter);
        if (index != -1 && index + delimiter.length() < text.length()) {
            return text.substring(index + delimiter.length());
        }
        System.out.println("Delimiter not found, or delimiter at the end of the string.");
        return "";
    }
}