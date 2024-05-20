package com.example.demo.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller/cm")
public class cmController {
	public cmController() {
		// TODO Auto-generated constructor stub
	}
	
	//판매관리 페이지 메핑
	//주문통합검색
	@RequestMapping("ordersearch")
	public String ordersearch(Model model) {
		return "/seller/sm/ordersearch";
	}
	//미결제 확인
	@RequestMapping("outstanding")
	public String outstanding(Model model) {
		return "/seller/sm/outstanding";
	}
	//발주확인
	@RequestMapping("order")
	public String order(Model model) {
		return "/seller/sm/order";
	}
	//배송현황 관리
	@RequestMapping("deliverystatus")
	public String deliverystatus(Model model) {
		return "/seller/sm/deliverystatus";
	}
	//구매확정 내역
	@RequestMapping("cm/buyconfirmation")
	public String buyconfirmation(Model model) {
		return "/seller/sm/buyconfirmation";
	}
	//취소관리
	@RequestMapping("cancel")
	public String cancel(Model model) {
		return "/seller/sm/cancel";
	}
	//반품관리
	@RequestMapping("preturn")
	public String preturn(Model model) {
		return "/seller/sm/preturn";
	}
	//교환관리
	@RequestMapping("exchange")
	public String exchange(Model model) {
		return "/seller/sm/exchange";
	}
}
