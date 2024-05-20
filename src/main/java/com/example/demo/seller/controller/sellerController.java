package com.example.demo.seller.controller;

import com.example.demo.seller.domain.Order_list;
import com.example.demo.seller.domain.Seller;
import com.example.demo.seller.service.Order_listService;
import com.example.demo.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/seller")
public class sellerController {

	private final Order_listService order_listServicer;
	private final SellerService sellerService;

	public sellerController(Order_listService orderListService, SellerService sellerService){
		this.order_listServicer = orderListService;
		this.sellerService = sellerService;
	}

	//메인 페이지 메핑
	@RequestMapping({"/", "index"})
	public String index(Model model) {
		Map<String, Integer> data = order_listServicer.getTotalPricePerDate();
		model.addAttribute("data", data);
		List<Seller> sellers = sellerService.getAllSellers();
		model.addAttribute("sellers", sellers);
		return "seller/index";
	}

	//로그인 페이지 메핑
	@RequestMapping("login")
	public String login(Model model) {
		return "seller/login";
	}

	//회원가입 페이지 메핑
	@RequestMapping("signup")
	public String signup(Model model) {
		return "seller/signup";
	}
}
