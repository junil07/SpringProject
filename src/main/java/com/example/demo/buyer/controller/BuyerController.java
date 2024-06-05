package com.example.demo.buyer.controller;

import com.example.demo.buyer.entity.ProductView;
import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.entity.Review;
import com.example.demo.buyer.entity.Stock;
import com.example.demo.buyer.repository.ProductViewRepository;
import com.example.demo.buyer.repository.ReviewRepository;
import com.example.demo.buyer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	private ReviewRepository reviewRepository;

	@Autowired
	private ProductSizeimple productSizeimple;

	@Autowired
	private StockService stockService;

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
	public String detail(Model model, @RequestParam("productId") Integer productId){
		System.out.print(productId);
		List<Category> categories = categoryService.getRows();
		List<ProductView> productDetail = productViewRepository.findByProductId(productId);
		List<String> productSize = productSizeimple.getRowParamOne(productId);
		List<Stock> stockList = stockService.getStockList(productId);
		List<Review> reviewList = reviewRepository.findByProductProductIdOrderByReviewIdDesc(productId);
		model.addAttribute("categories", categories);
		model.addAttribute("productDetail", productDetail);
		model.addAttribute("productSize", productSize);
		model.addAttribute("stockList", stockList);
		model.addAttribute("reviewList", reviewList);
		return "buyer/product_detail";
	}

}
