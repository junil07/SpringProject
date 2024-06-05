package com.example.demo.seller.service;

import com.example.demo.seller.DTO.ProductDTO;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.ProductImage;
import com.example.demo.seller.repository.ProductImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ProductImageService {

    private final ProductImageRepository productImageRepository;

    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @Autowired
    private ProductService productService;

    public ProductImage getProductImage(Product product) {
        return productImageRepository.findByproduct(product);
    }

    //추가
    @Transactional
    public void addProductImage(MultipartFile file, ProductDTO productDTO) {
        if (!file.isEmpty()) {
            ProductImage productImage = new ProductImage();
            productImage.setProductImageSname(UUID.randomUUID().toString());
            productImage.setProduct(productService.getProduct(productDTO.getProductId()));

            productImage.setProductImageName(getFileName(file));
            productImage.setProductImageExtension("."+getFileExtension(file.getOriginalFilename())); // 파일 확장자명 가져오기
            productImage.setProductImageSize((int) file.getSize()); // 파일 크기 가져오기 (바이트 단위)

            try{
                Path path = Paths.get("src/main/resources/static/assets/image/reviewImage" + File.separator + productImage.getProductImageSname() + productImage.getProductImageExtension());
                Files.write(path, file.getBytes());
                productImageRepository.save(productImage);
            } catch (IOException e) {

            }
        }
    }

    //삭제
    public void delProductImage(String pk, ProductImage productImage) {
        //파일 삭제
        File file = new File("src/main/resources/static/assets/image/pMain" + File.separator + productImage.getProductImageSname() + productImage.getProductImageExtension());
        if (file.delete()) {
            System.out.println("파일이 성공적으로 삭제되었습니다.");
        } else {
            System.out.println("파일을 삭제하는 중에 문제가 발생했습니다.");
        }

        //DB 삭제
        productImageRepository.deleteById(pk);
    }

    //업데이트
//    @Transactional
//    public void updateProductImage(MultipartFile file, ProductImage productImage) {
//        if (!file.isEmpty()) {
//            productImage.setProductImageSname(productImage.getProductImageSname());
//            productImage.setProduct(productImage.getProduct());
//            String fileName = file.getOriginalFilename(); // 파일 이름 가져오기
//            productImage.setProductImageName(fileName);
//            productImage.setProductImageExtension("."+getFileExtension(fileName)); // 파일 확장자명 가져오기
//            productImage.setProductImageSize((int) file.getSize()); // 파일 크기 가져오기 (바이트 단위)
//
//            try{
//                Path path = Paths.get("src/main/resources/static/assets/image/pMain" + File.separator + productImage.getProductImageSname() + productImage.getProductImageExtension());
//                Files.write(path, file.getBytes());
//                productImageRepository.save(productImage);
//            } catch (IOException e) {
//
//            }
//        }
//    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public static String getFileName(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex != -1) {
            return fileName.substring(0, dotIndex);
        } else {
            return fileName;
        }
    }
}
