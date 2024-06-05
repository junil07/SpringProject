package com.example.demo.buyer.controller;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.admin.security.SecurityServiceImple;
import com.example.demo.admin.service.BuyerService;
import com.example.demo.admin.service.BuyerServiceImple;
import com.example.demo.buyer.DTO.BuyerDTO;
import com.example.demo.buyer.entity.ProductView;
import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.entity.Review;
import com.example.demo.buyer.entity.Stock;
import com.example.demo.buyer.repository.ProductViewRepository;
import com.example.demo.buyer.repository.ReviewRepository;
import com.example.demo.buyer.service.*;
import com.example.demo.buyer.service.CategoryService;
import com.example.demo.buyer.service.CustomProductService;
import com.example.demo.buyer.service.ProductSizeimple;
import com.example.demo.seller.DTO.SellerDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
	private BuyerDTO buyerDTO;

	@Autowired
	private SellerDTO sellerDTO;

	@Autowired
	private SecurityServiceImple securityService;

	@Autowired
	private StockService stockService;

	@Autowired
	private BuyerServiceImple buyerService1;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	// 로그인 페이지로 이동
	@RequestMapping("/buyer/login")
	public String buyerLogin(@AuthenticationPrincipal User user, Principal principal, Model model) {

		System.out.println("구매자 로그인 버튼으로 이동");
		String alert = "";

		if (principal != null) {
			if (securityService.hasRole(user, "ROLE_BUYER")) {
				alert = "이미 구매자 로그인이 되어있습니다.";
			} else if (securityService.hasRole(user, "ROLE_SELLER")) {
				alert = "이미 판매자 로그인이 되어있습니다.";
			}
			/*
			System.out.println(user.getUsername());
			ㄴ 현재 유지되고 있는 세션의 아이디를 반환
			System.out.println(user.getAuthorities());
			*/
		}

		model.addAttribute("alert", alert);
		model.addAttribute("checkTest", "1");
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

	// 구매자 회원가입
	@RequestMapping("/buyer/register")
	public String signUp(Model model) {
		model.addAttribute("buyer", new Buyer());
		return "/buyer/signup";
	}

	// 회원가입 진행
	@RequestMapping(value = "/buyer/register", method = RequestMethod.POST)
	public String signUpProc(Buyer buyer) {
		// 암호화
		buyer.setBuyerPassword(passwordEncoder.encode(buyer.getBuyerPassword()));
		buyer.setBuyerActivation((short) 1);
		buyer.setBuyerGrade((short) 1);
		buyerService1.register(buyer);
		return "redirect:/buyer/login";
	}

	// ID 중복확인 - ajax용
	@RequestMapping("/buyer/idCheck")
	@ResponseBody
	public int idDuplicate(@RequestBody String buyerId) {
		int idCheck = buyerService1.idCheck(buyerId);
		return idCheck;
	}
}