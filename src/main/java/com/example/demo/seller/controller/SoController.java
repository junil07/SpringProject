package com.example.demo.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller/so")
public class SoController {
	public SoController() {
		// TODO Auto-generated constructor stub
	}
	
	//정산관리 페이지 메핑
	//정산 내역
	@RequestMapping("calculate")
	public String calculate(Model model) {
		return "/seller/so/calculate";
	}
	
	//항목별 정산 내역
	@RequestMapping("dcalculate")
	public String dcalculate(Model model) {
		return "/seller/so/dcalculate";
	}
}
