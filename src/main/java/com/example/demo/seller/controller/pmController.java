package com.example.demo.seller.controller;

import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.service.CategoryService;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.Product_detail;
import com.example.demo.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
		List<Product> products = productService.getProductList();
		model.addAttribute("product", products);
	    return "/seller/pm/inquiry";
	}

	//상품 등록
	@RequestMapping("registration")
	public String registration(Model model) {
		List<Category> categories = categoryService.getAll();
		model.addAttribute("category", categories);
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
		List<Product> product = productService.getProductList();
		model.addAttribute("product", product);
		return "/seller/pm/product_detail"; // Thymeleaf 템플릿의 경로
	}


}
