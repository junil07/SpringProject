package com.example.demo.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller/pm")
public class pmController {
	public pmController() {

	}
	
	//상품관리 페이지 메핑
	//상품 조회/수정
	@RequestMapping("inquiry")
	public String inquiry(Model model) {
		System.out.println();
	    return "/seller/pm/inquiry";
	}
	//상품 등록
	@RequestMapping("registration")
	public String registration(Model model) {
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
}
