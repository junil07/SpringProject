package com.example.demo.buyer.controller;

import com.example.demo.buyer.entity.ProductView;
import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.repository.ProductViewRepository;
import com.example.demo.buyer.service.CategoryService;
import com.example.demo.buyer.service.CustomProductService;
import com.example.demo.buyer.service.ProductSizeimple;
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
}
