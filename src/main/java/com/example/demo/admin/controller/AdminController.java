package com.example.demo.admin.controller;

import com.example.demo.admin.service.AdminServiceImple;
import com.example.demo.admin.service.BuyerServiceImple;
import com.example.demo.admin.service.ProductServiceImple;
import com.example.demo.seller.domain.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	private HttpSession httpSession;
	private AdminServiceImple adminService;
	private BuyerServiceImple buyerService;
	private ProductServiceImple productService;

	public AdminController(HttpSession httpSession, AdminServiceImple adminService,
						   BuyerServiceImple buyerService, ProductServiceImple productService) {
		this.httpSession = httpSession;
		this.adminService = adminService;
		this.buyerService = buyerService;
		this.productService = productService;
	}

	// 메인 페이지
	@RequestMapping("main")
	public String index(Principal principal, Model model) {
		User user = (User) ((Authentication) principal).getPrincipal();
		String adminName = adminService.findName(user.getUsername());

		model.addAttribute("adminName", adminName);

		return "admin/index";
	}

	// 로그인 페이지로 이동
	@RequestMapping("login")
	public String loginPage(Principal principal, Model model) {

		System.out.println("로그인 페이지 이동");
		String page = "admin/login";
		String alert = "";

		if (principal != null) {
			alert = "이미 로그인이 되었습니다";
		}

		model.addAttribute("alert", alert);

		return page;
	}

	// 상품 등록 승인
	@RequestMapping("approval")
	public String approval(Principal principal, Model model) {
		User user = (User) ((Authentication) principal).getPrincipal();
		String adminName = adminService.findName(user.getUsername());
		List<Product> productList = productService.getProductList(0);
		String[] columnName = {"ID", "판매자 ID", "이름", "가격", "할인율", "해쉬태그", "카테고리"};

		model.addAttribute("adminName", adminName);
		model.addAttribute("productList", productList);
		model.addAttribute("columnName", columnName);

		return "admin/menu/approval";
	}
}
