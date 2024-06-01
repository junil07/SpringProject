package com.example.demo.seller.controller;

import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.service.CategoryService;
import com.example.demo.seller.DTO.ProductDTO;
import com.example.demo.seller.DTO.ProductDetailDTO;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.ProductImage;
import com.example.demo.seller.domain.Product_detail;
//import com.example.demo.seller.service.ProductImageService;
import com.example.demo.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/seller/pm")
public class pmController {

	@Autowired
	private ProductService productService;
//	@Autowired
//	private ProductImageService productImageService;
	@Autowired
	private CategoryService categoryService;

	public pmController() {

	}

	//상품관리 페이지 메핑
	//상품 조회/수정
	@RequestMapping("inquiry")
	public String inquiry(Model model) {
		List<Product> product = productService.getProductList();
		model.addAttribute("product", product);
		return "/seller/pm/inquiry";
	}

	//상품 등록
	@GetMapping("registration")
	public String registration(Model model) {
		List<Category> categories = categoryService.getAll();
		model.addAttribute("category", categories);
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("productDetailDTO", new ProductDetailDTO());
		return "/seller/pm/registration";
	}
	//연관상품 등록
	@RequestMapping("relation")
	public String relation(Model model) {
		return "/seller/pm/relation";
	}
	//배송정보 관리
	@RequestMapping("deliver")
	public String deliver(Model model) {
		return "/seller/pm/deliver";
	}

	//상품 상세페이지 이동
	@GetMapping("/{productId}")
	public String getProductDetail(@PathVariable("productId") Integer productId, Model model) {
		//상품
		Product product = productService.getProduct(productId);
		ProductDTO productDTO = productService.getProductDTO(product);
		model.addAttribute("productDTO", productDTO);
		//상품 상세
		Product_detail productDetail = productService.getProductDetail(product);
		ProductDetailDTO productDetailDTO = productService.getProductDetailDTO(productDetail);
		//카테고리
		model.addAttribute("productDetailDTO", productDetailDTO);
		List<Category> categories = categoryService.getAll();
		model.addAttribute("category", categories);
		//이미지
//		ProductImage productImage = productImageService.productImageRoute(product);
//		model.addAttribute("productImage", productImage);
//		model.addAttribute("productImageRoute", "/assets/image/pMain/" + productImage.getProductImageSname() + productImage.getProductImageExtension());

		// 저장된 카테고리 불러오기
		Category subSubCategory = product.getCategory();
		model.addAttribute("ssubSubCategory", subSubCategory);
		Category subCategory = categoryService.getCategory(subSubCategory.getCategoryParentId());
		model.addAttribute("ssubCategory", subCategory);
		Category sCategory = categoryService.getCategory(subCategory.getCategoryParentId());
		model.addAttribute("sCategory", sCategory);
		return "/seller/pm/product_detail"; // Thymeleaf 템플릿의 경로
	}

	//서브 카테고리
	@GetMapping("/proc/subcategories/{categoryId}")
	@ResponseBody
	public List<Category> getSubcategories(@PathVariable("categoryId") Long parentId) {
		// categoryId를 이용하여 해당 카테고리의 하위 카테고리를 가져옴
		List<Category> subcategories = categoryService.getSubCategories(parentId);
		return subcategories;
	}

	//서브-서브 카테고리
	@GetMapping("/proc/subSubcategories/{categoryId}")
	@ResponseBody
	public List<Category> getSubSubcategories(@PathVariable("categoryId") Long parentId) {
		// categoryId를 이용하여 해당 카테고리의 하위 카테고리를 가져옴
		List<Category> subSubcategories = categoryService.getSubCategories(parentId);
		return subSubcategories;
	}

	@PostMapping("/products")
	public String saveProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
							  @ModelAttribute("productDetailDTO") ProductDetailDTO productDetailDTO,
							  @RequestParam("file") MultipartFile file) {
		productService.addProduct(productDTO);
		productService.addProductDetail(productDetailDTO);
//		productImageService.addProductImage(file, productDTO);

		return "redirect:/seller/pm/inquiry"; // 파일 업로드 성공 시 리다이렉트할 뷰 이름
	}

	@PostMapping("/productsUpdate")
	public String updateProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
								@ModelAttribute("productDetailDTO") ProductDetailDTO productDetailDTO,
								@RequestParam("file") MultipartFile file) {
		productService.addProduct(productDTO);
		productService.addProductDetail(productDetailDTO);
//		productImageService.addProductImage(file, productDTO);

		return "redirect:/seller/pm/{productId}"; // 파일 업로드 성공 시 리다이렉트할 뷰 이름
	}



}
