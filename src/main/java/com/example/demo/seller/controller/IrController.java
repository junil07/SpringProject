package com.example.demo.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller/ir")
public class IrController {
	public IrController() {
		// TODO Auto-generated constructor stub
	}
	
	//문의/리뷰 관리 페이지 메핑
	//문의 관리
	@RequestMapping("inquiries")
	public String inquiries(Model model) {
		return "/seller/ir/inquiries";
	}
	
	//리뷰 관리
	@RequestMapping("reviews")
	public String reviews(Model model) {
		return "/seller/ir/reviews";
	}
}
