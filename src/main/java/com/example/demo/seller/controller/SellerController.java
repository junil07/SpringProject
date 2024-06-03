package com.example.demo.seller.controller;

import com.example.demo.admin.Entity.Seller;
import com.example.demo.admin.security.SecurityServiceImple;
import com.example.demo.buyer.DTO.BuyerDTO;
import com.example.demo.seller.DTO.OrderitemDTO;
import com.example.demo.seller.DTO.SellerDTO;
import com.example.demo.seller.domain.Orderitem;
import com.example.demo.seller.service.OrderitemService;
import com.example.demo.seller.service.ProductService;
import com.example.demo.seller.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Comparator;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/seller")
public class SellerController {

	private OrderitemService orderitemService;
	private SellerService sellerService;
	private ProductService productService;
	private BuyerDTO buyerDTO;
	private SellerDTO sellerDTO;
	private SecurityServiceImple securityService;
//	OrderitemService orderitemService
	public SellerController(OrderitemService orderitemService, SellerService sellerService,
							ProductService productService, BuyerDTO buyerDTO
							,SellerDTO sellerDTO, SecurityServiceImple securityService){
		this.orderitemService = orderitemService;
		this.sellerService = sellerService;
		this.productService = productService;
		this.buyerDTO = buyerDTO;
		this.sellerDTO = sellerDTO;
		this.securityService = securityService;
	}

	//메인 페이지 메핑
	@RequestMapping({"/", "index"})
	public String index(Model model) {
		Map<String, Integer> count = orderitemService.findTotalCount();
		model.addAttribute("count", count);
		Map<String, Integer> price = orderitemService.getTotalPricePerDate();
		model.addAttribute("price", price);
		Map<String, Integer> buyer = orderitemService.findDisBuyer();
		model.addAttribute("buyer", buyer);
		Map<String, Integer> product = orderitemService.countsellproduct();
		model.addAttribute("product", product);
		List<Seller> sellers = sellerService.getAllSellers();
		model.addAttribute("sellers", sellers);
		return "seller/index";
	}

	//로그인 페이지 메핑
	@RequestMapping("login")
	public String login(@AuthenticationPrincipal User user, Principal principal, Model model) {

		System.out.println("판매자 로그인 버튼으로 이동");
		String alert = "";

		if (principal != null) {
			if (securityService.hasRole(user, "ROLE_BUYER")) {
				alert = "이미 구매자 로그인이 되어있습니다.";
			} else if (securityService.hasRole(user, "ROLE_SELLER")) {
				alert = "이미 판매자 로그인이 되어있습니다.";
			}
			// System.out.println(user.getUsername());
			// ㄴ 현재 로그인 되어있는 사람의 아이디를 반환
		}

		model.addAttribute("alert", alert);
		model.addAttribute("checkTest", "2");
		model.addAttribute("buyerDTO", buyerDTO);
		model.addAttribute("sellerDTO", sellerDTO);

		return "seller/login";
	}

	// ㄹㅇ 로그인
	@RequestMapping("reallogin")
	public String realLogin(Model model, SellerDTO sellerDTO) {
		model.addAttribute("sellerDTO", sellerDTO);
		return "seller/reallogin";
	}

	//회원가입 페이지 메핑
	@RequestMapping("signup")
	public String signup(Model model) {
		return "seller/signup";
	}

	//회원가입 페이지 메핑
	@RequestMapping("test")
	public String test(Model model) {
		return "seller/test";
	}
}