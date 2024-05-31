package com.example.demo.buyer.controller;

import com.example.demo.buyer.DTO.BuyerDTO;
import com.example.demo.buyer.entity.ProductView;
import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.repository.ProductViewRepository;
import com.example.demo.buyer.service.CategoryService;
import com.example.demo.buyer.service.CustomProductService;
import com.example.demo.buyer.service.ProductSizeimple;
import com.example.demo.seller.DTO.SellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class BuyerController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CustomProductService customProductService;

	@Autowired
	private ProductViewRepository productViewRepository;

	@Autowired
	private ProductSizeimple productSizeimple;

	@Autowired
	private BuyerDTO buyerDTO;

	@Autowired
	private SellerDTO sellerDTO;

	@RequestMapping("buyer/index")
	public String main
			(Model model) {
		List<Category> categories = categoryService.getRows();
		List<Map<String, Object>> productSummaryList = customProductService.getProductSummary();
		model.addAttribute("categories", categories);
		model.addAttribute("productSummaryList", productSummaryList);
		return "buyer/index";
	}

	@RequestMapping("buyer/detail")
	public String detail(Model model, @RequestParam("productId") String productId){
		System.out.print(productId);
		List<Category> categories = categoryService.getRows();
		List<ProductView> productDetail = productViewRepository.findByProductId(productId);
		List<String> productSize = productSizeimple.getRowParamOne(productId);
		model.addAttribute("categories", categories);
		model.addAttribute("productDetail", productDetail);
		model.addAttribute("productSize", productSize);
		return "buyer/product_detail";
	}

	// 로그인 페이지로 이동
	@RequestMapping("/buyer/login")
	public String buyerLogin(Model model) {
		model.addAttribute("buyerDTO", buyerDTO);
		model.addAttribute("sellerDTO", sellerDTO);
		return "seller/login";
	}

	// ㄹㅇ 로그인
	@RequestMapping("/buyer/reallogin")
	public String buyerRealLogin(BuyerDTO buyerDTO, Model model) {
		model.addAttribute("buyerDTO", buyerDTO);
		return "buyer/reallogin";
	}
}
