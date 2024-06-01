package com.example.demo.seller.controller;

import com.example.demo.seller.DTO.OrderitemDTO;
import com.example.demo.seller.domain.Orderitem;
import com.example.demo.seller.service.OrderitemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller/cm")
public class CmController {

	private static final Logger logger = LoggerFactory.getLogger(CmController.class);

	@Autowired
	private OrderitemService orderitemService;

	public CmController() {

	}

	// 판매관리 페이지 매핑
	@RequestMapping("ordersearch")
	public String ordersearch(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		// 최근 날짜순으로 불러오기
		orderitem.sort(Comparator.comparing(o -> o.getOrderlist().getOrderlistDate(), Comparator.reverseOrder()));
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
				.sorted(Comparator.comparing(o -> o.getOrderlist().getOrderlistDate(), Comparator.reverseOrder()))
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
		// 전체적인 리스트 출력(orderitemCase가 취소이거나 취소승인인것만)
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> "취소".equals(o.getOrderitemCase()) || "취소승인".equals(o.getOrderitemCase()))
				.sorted(Comparator.comparing(o -> o.getOrderitemDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("orderitem", filteredOrderItems);

		//orderitemCase가 취소인것만 출력
		List<Orderitem> filtercancelitem = orderitem.stream()
				.filter(o -> "취소".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("cancelcount", filtercancelitem);

		//취소 완료된것만 출력(최근3일)
		// 현재 날짜
		LocalDate currentDate = LocalDate.now();
		// 최근 3일 동안의 날짜 계산
		LocalDate threeDaysAgo = currentDate.minusDays(3);
		List<Orderitem> filterthreeday = orderitem.stream()
				.filter(o -> "취소".equals(o.getOrderitemCase()) || "취소승인".equals(o.getOrderitemCase()))
				.filter(o -> o.getOrderitemDate().isAfter(threeDaysAgo))
				.collect(Collectors.toList());
		model.addAttribute("threecount", filterthreeday);

		return "/seller/sm/cancel";
	}

	@PostMapping("/cancel")
	public ResponseEntity<?> handleOrderItems(
			@RequestBody List<OrderitemDTO> orderItems,
			@RequestHeader("Action-Type") String actionType) {
		try {
			if ("cancel".equals(actionType)) {
				orderitemService.updateOrderitemCase(orderItems);
			} else if ("withdraw".equals(actionType)) {
				orderitemService.withdrawOrderitemCase(orderItems);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown action type");
			}
			return ResponseEntity.ok("처리가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("처리 중 오류가 발생했습니다.");
		}
	}

	// 반품 관리
	@RequestMapping("preturn")
	public String preturn(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		// 최근 날짜순으로 불러오기
		// 전체적인 리스트 출력(orderitemCase가 취소이거나 취소승인인것만)
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> o.getOrderitemCase().contains("반품"))
				.sorted(Comparator.comparing(o -> o.getOrderitemDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("orderitem", filteredOrderItems);

		//반품요청
		List<Orderitem> returncount = orderitem.stream()
				.filter(o -> "반품요청".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("returncount", returncount);

		//반품수거중
		List<Orderitem> returningcount = orderitem.stream()
				.filter(o -> "반품수거중".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("returningcount", returningcount);

		//반품수거완료
		List<Orderitem> returnsucesscount = orderitem.stream()
				.filter(o -> "반품완료".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("returnsucesscount", returnsucesscount);

		//반품완료(최근3일)
		// 현재 날짜
		LocalDate currentDate = LocalDate.now();
		// 최근 3일 동안의 날짜 계산
		LocalDate threeDaysAgo = currentDate.minusDays(3);
		List<Orderitem> threecount = orderitem.stream()
				.filter(o -> "반품완료".equals(o.getOrderitemCase()))
				.filter(o -> o.getOrderitemDate().isAfter(threeDaysAgo))
				.collect(Collectors.toList());
		model.addAttribute("threecount", threecount);
		return "/seller/sm/preturn";
	}

	@PostMapping("/preturn")
	public ResponseEntity<?> handlereturnItems(
			@RequestBody List<OrderitemDTO> orderItems,
			@RequestHeader("Action-Type") String actionType) {
		try {
			if ("preturn".equals(actionType)) {
				orderitemService.updatereutrnitemCase(orderItems);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown action type");
			}
			return ResponseEntity.ok("처리가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("처리 중 오류가 발생했습니다.");
		}
	}

	// 교환 관리
	@RequestMapping("exchange")
	public String exchange(Model model) {

		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		// 최근 날짜순으로 불러오기
		// 전체적인 리스트 출력(orderitemCase가 취소이거나 취소승인인것만)
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> o.getOrderitemCase().contains("반품"))
				.sorted(Comparator.comparing(o -> o.getOrderitemDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("orderitem", filteredOrderItems);

		//반품요청
		List<Orderitem> returncount = orderitem.stream()
				.filter(o -> "반품요청".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("returncount", returncount);

		//반품수거중
		List<Orderitem> returningcount = orderitem.stream()
				.filter(o -> "반품수거중".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("returningcount", returningcount);

		//반품수거완료
		List<Orderitem> returnsucesscount = orderitem.stream()
				.filter(o -> "반품완료".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("returnsucesscount", returnsucesscount);

		//반품완료(최근3일)
		// 현재 날짜
		LocalDate currentDate = LocalDate.now();
		// 최근 3일 동안의 날짜 계산
		LocalDate threeDaysAgo = currentDate.minusDays(3);
		List<Orderitem> threecount = orderitem.stream()
				.filter(o -> "반품완료".equals(o.getOrderitemCase()))
				.filter(o -> o.getOrderitemDate().isAfter(threeDaysAgo))
				.collect(Collectors.toList());
		model.addAttribute("threecount", threecount);
		return "/seller/sm/exchange";
	}
}
