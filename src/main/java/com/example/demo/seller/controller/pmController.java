package com.example.demo.seller.controller;

import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.service.CategoryService;
import com.example.demo.seller.DTO.ProductDTO;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.Product_detail;
import com.example.demo.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seller/pm")
public class pmController {

	@Autowired
	private ProductService productService;
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
	public String getProductDetail(@PathVariable("productId") String productId, Model model) {
		List<Product_detail> productDetail = productService.getProductDetail(productId);
		model.addAttribute("productDetail", productDetail);
		List<Category> categories = categoryService.getAll();
		model.addAttribute("category", categories);
		Product product = productService.getProduct(productId);
		model.addAttribute("product", product);
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
	public String saveProduct(@ModelAttribute("productDTO") ProductDTO productDTO) {
		productService.addProduct(productDTO);
		System.out.println("에엥");
		return "/inquiry";
	}




}
