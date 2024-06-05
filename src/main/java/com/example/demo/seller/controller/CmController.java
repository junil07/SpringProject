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

	//////////////////////////////// 판매관리 페이지 매핑
	@RequestMapping("ordersearch")
	public String ordersearch(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		// 최근 날짜순으로 불러오기
		orderitem.sort(Comparator.comparing(o -> o.getOrderlist().getOrderlistDate(), Comparator.reverseOrder()));
		model.addAttribute("orderitem", orderitem);
		return "/seller/sm/ordersearch";
	}

	///////////////////////////// 미결제 확인
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

	///////////////////////////// 발주 확인
	@RequestMapping("order")
	public String order(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.sorted(Comparator.comparing(o -> o.getOrderlist().getOrderlistDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("orderitem", filteredOrderItems);
		LocalDate currentDate = LocalDate.now();
		LocalDate threeDaysAgo = currentDate.minusDays(3);
		//배송상황이 배송전 인것만 출력
		List<Orderitem> ordersucess = orderitem.stream()
				.filter(o -> "배송전".equals(o.getOrderitemDstatus()))
				.filter(o -> o.getOrderitemDate().isAfter(threeDaysAgo))
				.collect(Collectors.toList());
		model.addAttribute("ordersucess", ordersucess);

		//배송상황이 배송전 인것만 출력
		List<Orderitem> ordering = orderitem.stream()
				.filter(o -> "배송중".equals(o.getOrderitemDstatus()))
				.filter(o -> o.getOrderitemDate().isAfter(threeDaysAgo))
				.collect(Collectors.toList());
		model.addAttribute("ordering", ordering);

		//배송상황이 배송전 인것만 출력
		List<Orderitem> orderend = orderitem.stream()
				.filter(o -> "배송완료".equals(o.getOrderitemDstatus()))
				.filter(o -> o.getOrderitemDate().isAfter(threeDaysAgo))
				.collect(Collectors.toList());
		model.addAttribute("orderend", orderend);
		return "/seller/sm/order";
	}

//	@PostMapping("/order/action")
//	public ResponseEntity<?> handlecancelaction(
//			@RequestBody List<OrderitemDTO> orderItems,
//			@RequestHeader("Action-Type") String actionType) {
//		try {
//			if ("ordercheck".equals(actionType)) {
//				orderitemService.ordercheck(orderItems);
//			} else if("ordersucess".equals(actionType)){
//				orderitemService.ordersucess(orderItems);
//			} else if("ordercancel".equals(actionType)){
//				orderitemService.ordercancel(orderItems);
//			} else {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown action type");
//			}
//			return ResponseEntity.ok("처리가 성공적으로 완료되었습니다.");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("처리 중 오류가 발생했습니다.");
//		}
//	}
//
//	@GetMapping("/order/reload")
//	public String ordercancelList(Model model) {
//		List<Orderitem> orderitem = orderitemService.getOrderitemList();
//		List<Orderitem> filteredOrderItems = orderitem.stream()
//				.filter(o -> o.getOrderitemCase().contains("정상처리"))
//				.sorted(Comparator.comparing(o -> o.getOrderitemDate(), Comparator.reverseOrder()))
//				.collect(Collectors.toList());
//		model.addAttribute("orderitem", filteredOrderItems);
//		// 필요한 추가 데이터들 로드
//		model.addAttribute("ordersucess", orderitem.stream().filter(o -> "배송전".equals(o.getOrderitemDstatus())).collect(Collectors.toList()));
//		model.addAttribute("ordering", orderitem.stream().filter(o -> "배송중".equals(o.getOrderitemDstatus())).collect(Collectors.toList()));
//		model.addAttribute("orderend", orderitem.stream().filter(o -> "배송완료".equals(o.getOrderitemDstatus())).collect(Collectors.toList()));
//		return "/seller/sm/order :: #exchangeSection"; // exchange.html의 특정 부분만 갱신
//	}

	/////////////////////////////// 배송현황 관리
	@RequestMapping("deliverystatus")
	public String deliverystatus(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> o.getOrderitemDstatus().contains("배송"))
				.sorted(Comparator.comparing(o -> o.getOrderlist().getOrderlistDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("orderitem", filteredOrderItems);

		//배송상황이 배송전 인것만 출력
		List<Orderitem> deliverycount = orderitem.stream()
				.filter(o -> "배송전".equals(o.getOrderitemDstatus()))
				.collect(Collectors.toList());
		model.addAttribute("deliverycount", deliverycount);

		//배송상황이 배송전 인것만 출력
		List<Orderitem> deliveryingcount = orderitem.stream()
				.filter(o -> "배송중".equals(o.getOrderitemDstatus()))
				.collect(Collectors.toList());
		model.addAttribute("deliveryingcount", deliveryingcount);

		//배송상황이 배송완료 인것만 출력
		List<Orderitem> deliverysucesscount = orderitem.stream()
				.filter(o -> "구매확정요청".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("deliverysucesscount", deliverysucesscount);

		return "/seller/sm/deliverystatus";
	}

	@PostMapping("/deliverystatus/action")
	public ResponseEntity<?> handledeliverystatus(
			@RequestBody List<OrderitemDTO> orderItems,
			@RequestHeader("Action-Type") String actionType) {
		try {
			if ("deliverycheck".equals(actionType)) {
				orderitemService.deliverycheck(orderItems);
			} else if("deliverysucess".equals(actionType)){
				orderitemService.deliverysucess(orderItems);
			}else if("sucesscheck".equals(actionType)){
				orderitemService.sucesscheck(orderItems);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown action type");
			}
			return ResponseEntity.ok("처리가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("처리 중 오류가 발생했습니다.");
		}
	}

	@GetMapping("/deliverystatus/reload")
	public String deliverystatuslList(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> o.getOrderitemCase().contains("정상처리"))
				.sorted(Comparator.comparing(o -> o.getOrderitemDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("orderitem", filteredOrderItems);
		// 필요한 추가 데이터들 로드
		model.addAttribute("deliverycount", orderitem.stream().filter(o -> "배송전".equals(o.getOrderitemDstatus())).collect(Collectors.toList()));
		model.addAttribute("deliveryingcount", orderitem.stream().filter(o -> "배송중".equals(o.getOrderitemDstatus())).collect(Collectors.toList()));
		model.addAttribute("deliverysucesscount", orderitem.stream().filter(o -> "구매확정요청".equals(o.getOrderitemCase())).collect(Collectors.toList()));
		return "/seller/sm/deliverystatus :: #exchangeSection"; // exchange.html의 특정 부분만 갱신
	}
	///////////////////////////// 구매확정 내역

	@RequestMapping("buyconfirmation")
	public String buyconfirmation(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> "구매확정요청".equals(o.getOrderitemCase()))
				.sorted(Comparator.comparing(o -> o.getOrderlist().getOrderlistDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("orderitem", filteredOrderItems);
		return "/seller/sm/buyconfirmation";
	}

	//////////////////////////////// 취소 관리
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
				.filter(o -> "취소승인".equals(o.getOrderitemCase()))
				.filter(o -> o.getOrderitemDate().isAfter(threeDaysAgo))
				.collect(Collectors.toList());
		model.addAttribute("threecount", filterthreeday);
		return "/seller/sm/cancel";
	}

	@PostMapping("/cancel/action")
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

	@GetMapping("/cancel/reload")
	public String reloadcancelList(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> o.getOrderitemCase().contains("취소"))
				.sorted(Comparator.comparing(o -> o.getOrderitemDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("orderitem", filteredOrderItems);
		// 필요한 추가 데이터들 로드
		model.addAttribute("cancelcount", orderitem.stream().filter(o -> "취소".equals(o.getOrderitemCase())).collect(Collectors.toList()));
		model.addAttribute("threecount", orderitem.stream().filter(o -> "취소승인".equals(o.getOrderitemCase()) && o.getOrderitemDate().isAfter(LocalDate.now().minusDays(3))).collect(Collectors.toList()));
		return "/seller/sm/cancel :: #exchangeSection"; // exchange.html의 특정 부분만 갱신
	}

	/////////////////////////////////// 반품 관리
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

	@PostMapping("/preturn/action")
	public ResponseEntity<?> handlereturnItems(
			@RequestBody List<OrderitemDTO> orderItems,
			@RequestHeader("Action-Type") String actionType) {
		try {
			if ("preturn".equals(actionType)) {
				orderitemService.updatereutrnitemCase(orderItems);
			} else if ("returnsucess".equals(actionType)) {
				orderitemService.updatesucessitemCase(orderItems);
			} else if ("returncancel".equals(actionType)) {
				orderitemService.updatecancelitemCase(orderItems);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown action type");
			}
			return ResponseEntity.ok("처리가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("처리 중 오류가 발생했습니다.");
		}
	}

	@GetMapping("/preturn/reload")
	public String reloadpreturnList(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> o.getOrderitemCase().contains("반품"))
				.sorted(Comparator.comparing(o -> o.getOrderitemDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("orderitem", filteredOrderItems);
		// 필요한 추가 데이터들 로드
		model.addAttribute("returncount", orderitem.stream().filter(o -> "반품요청".equals(o.getOrderitemCase())).collect(Collectors.toList()));
		model.addAttribute("returningcount", orderitem.stream().filter(o -> "반품수거중".equals(o.getOrderitemCase())).collect(Collectors.toList()));
		model.addAttribute("returnsucesscount", orderitem.stream().filter(o -> "반품완료".equals(o.getOrderitemCase())).collect(Collectors.toList()));
		model.addAttribute("threecount", orderitem.stream().filter(o -> "반품완료".equals(o.getOrderitemCase()) && o.getOrderitemDate().isAfter(LocalDate.now().minusDays(3))).collect(Collectors.toList()));
		return "/seller/sm/preturn :: #exchangeSection"; // exchange.html의 특정 부분만 갱신
	}

	//////////////////////////////////교환 관리
	@RequestMapping("exchange")
	public String exchange(Model model) {

		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		// 최근 날짜순으로 불러오기
		// 전체적인 리스트 출력(orderitemCase가 취소이거나 취소승인인것만)
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> o.getOrderitemCase().contains("교환"))
				.sorted(Comparator.comparing(o -> o.getOrderitemDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("orderitem", filteredOrderItems);

		//교환요청
		List<Orderitem> returncount = orderitem.stream()
				.filter(o -> "교환요청".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("returncount", returncount);

		//교환수거중
		List<Orderitem> returningcount = orderitem.stream()
				.filter(o -> "교환수거중".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("returningcount", returningcount);

		//교환수거완료
		List<Orderitem> returnsucesscount = orderitem.stream()
				.filter(o -> "교환완료".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("returnsucesscount", returnsucesscount);

		//교환완료(최근3일)
		// 현재 날짜
		LocalDate currentDate = LocalDate.now();
		// 최근 3일 동안의 날짜 계산
		LocalDate threeDaysAgo = currentDate.minusDays(3);
		List<Orderitem> threecount = orderitem.stream()
				.filter(o -> "교환완료".equals(o.getOrderitemCase()))
				.filter(o -> o.getOrderitemDate().isAfter(threeDaysAgo))
				.collect(Collectors.toList());
		model.addAttribute("threecount", threecount);
		return "/seller/sm/exchange";
	}

	@PostMapping("/exchange/action")
	public ResponseEntity<?> handleexchangeItems(
			@RequestBody List<OrderitemDTO> orderItems,
			@RequestHeader("Action-Type") String actionType) {
		try {
			if ("exchange".equals(actionType)) {
				orderitemService.updatexchangeitemCase(orderItems);
			} else if ("exchangesucess".equals(actionType)) {
				orderitemService.updateexsucessitemCase(orderItems);
			} else if ("exchangefail".equals(actionType)) {
				orderitemService.updatefailitemCase(orderItems);
			} else if ("exchangetoreturn".equals(actionType)) {
				orderitemService.updatereturnCase(orderItems);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown action type");
			}
			return ResponseEntity.ok("처리가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("처리 중 오류가 발생했습니다.");
		}
	}

	@GetMapping("/exchange/reload")
	public String reloadExchangeList(Model model) {
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		List<Orderitem> filteredOrderItems = orderitem.stream()
				.filter(o -> o.getOrderitemCase().contains("교환"))
				.sorted(Comparator.comparing(o -> o.getOrderitemDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("orderitem", filteredOrderItems);
		// 필요한 추가 데이터들 로드
		model.addAttribute("returncount", orderitem.stream().filter(o -> "교환요청".equals(o.getOrderitemCase())).collect(Collectors.toList()));
		model.addAttribute("returningcount", orderitem.stream().filter(o -> "교환수거중".equals(o.getOrderitemCase())).collect(Collectors.toList()));
		model.addAttribute("returnsucesscount", orderitem.stream().filter(o -> "교환완료".equals(o.getOrderitemCase())).collect(Collectors.toList()));
		model.addAttribute("threecount", orderitem.stream().filter(o -> "교환완료".equals(o.getOrderitemCase()) && o.getOrderitemDate().isAfter(LocalDate.now().minusDays(3))).collect(Collectors.toList()));
		return "/seller/sm/exchange :: #exchangeSection"; // exchange.html의 특정 부분만 갱신
	}
}
