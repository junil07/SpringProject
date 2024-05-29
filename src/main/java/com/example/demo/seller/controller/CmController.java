package com.example.demo.seller.controller;

import com.example.demo.seller.domain.Orderitem;
import com.example.demo.seller.service.OrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller/cm")
public class CmController {

	@Autowired
	private OrderitemService orderitemService;

	public CmController() {

	}

	// 판매관리 페이지 매핑
	@RequestMapping("ordersearch")
	public String ordersearch(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		// 최근 날짜순으로 불러오기
		orderitem.sort(Comparator.comparing(o -> o.getOrder_list().getOrder_list_Date(), Comparator.reverseOrder()));
		model.addAttribute("orderitem", orderitem);
		return "/seller/sm/ordersearch";
	}

	// 미결제 확인
	@RequestMapping("outstanding")
	public String outstanding(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();

		// "결제전" 상태의 항목만 필터링
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> "결제전".equals(o.getOrderitemPstatus()))
				.sorted(Comparator.comparing(o -> o.getOrder_list().getOrder_list_Date(), Comparator.reverseOrder()))
				.collect(Collectors.toList());

		model.addAttribute("orderitem", filteredOrderItems);
		return "/seller/sm/outstanding";
	}

	// 발주 확인
	@RequestMapping("order")
	public String order(Model model) {
		return "/seller/sm/order";
	}

	// 배송현황 관리
	@RequestMapping("deliverystatus")
	public String deliverystatus(Model model) {
		return "/seller/sm/deliverystatus";
	}

	// 구매확정 내역
	@RequestMapping("buyconfirmation")
	public String buyconfirmation(Model model) {
		return "/seller/sm/buyconfirmation";
	}

	// 취소 관리
	@RequestMapping("cancel")
	public String cancel(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		// 최근 날짜순으로 불러오기
		// "결제전" 상태의 항목만 필터링
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> !"환불".equals(o.getOrderitemPstatus()) && !"배송완료".equals(o.getOrderitemDstatus()))
				.sorted(Comparator.comparing(o -> o.getOrder_list().getOrder_list_Date(), Comparator.reverseOrder()))
				.collect(Collectors.toList());

		model.addAttribute("orderitem", filteredOrderItems);
		return "/seller/sm/cancel";
	}

	// 반품 관리
	@RequestMapping("preturn")
	public String preturn(Model model) {
		return "/seller/sm/preturn";
	}

	// 교환 관리
	@RequestMapping("exchange")
	public String exchange(Model model) {
		return "/seller/sm/exchange";
	}
}
