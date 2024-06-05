package com.example.demo.seller.controller;

import com.example.demo.admin.Entity.Seller;
import com.example.demo.seller.DTO.OrderitemDTO;
import com.example.demo.seller.domain.Orderitem;
import com.example.demo.seller.service.OrderitemService;
import com.example.demo.seller.service.ProductService;
import com.example.demo.seller.service.SellerService;
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
@RequestMapping("/seller")
public class SellerController {

	private OrderitemService orderitemService;
	private SellerService sellerService;
	private ProductService productService;
//	OrderitemService orderitemService
	public SellerController(OrderitemService orderitemService,
							SellerService sellerService,
							ProductService productService){
		this.orderitemService = orderitemService;
		this.sellerService = sellerService;
		this.productService = productService;
	}

	//메인 페이지 메핑
	@RequestMapping({"/", "index"})
	public String index(Model model) {
		//그래프 출력//
		Map<String, Integer> count = orderitemService.findTotalCount();
		model.addAttribute("count", count);
		Map<String, Integer> price = orderitemService.getTotalPricePerDate();
		model.addAttribute("price", price);
		Map<String, Integer> buyer = orderitemService.findDisBuyer();
		model.addAttribute("buyer", buyer);
		Map<String, Integer> product = orderitemService.countsellproduct();
		model.addAttribute("product", product);
		//////////////
		// 현재 날짜
		LocalDate currentDate = LocalDate.now();
		// 최근 3일 동안의 날짜 계산
		LocalDate threeDaysAgo = currentDate.minusDays(3);
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		model.addAttribute("orderitem", orderitem);
		//결제대기(결제 전인것만 출력)
		List<Orderitem> paybeforecount = orderitem.stream()
				.filter(o -> "결제전".equals(o.getOrderitemPstatus()))
				.collect(Collectors.toList());
		model.addAttribute("paybeforecount", paybeforecount);
		//신규주문(최근 3일내 주문 들어온것만 출력)
		List<Orderitem> threecount = orderitem.stream()
				.filter(o -> o.getOrderitemDate().isAfter(threeDaysAgo))
				.collect(Collectors.toList());
		model.addAttribute("threecount", threecount);
		//오늘출발(오늘날짜로 배송중 인것만 출력)
		List<Orderitem> nowdaycount = orderitem.stream()
				.filter(o -> "배송중".equals(o.getOrderitemDstatus()))
				.filter(o -> o.getOrderitemDate().isAfter(currentDate))
				.collect(Collectors.toList());
		model.addAttribute("nowdaycount", nowdaycount);
		//배송준비(배송전 인것만 출력)
		List<Orderitem> deliverycount = orderitem.stream()
				.filter(o -> "배송전".equals(o.getOrderitemDstatus()))
				.collect(Collectors.toList());
		model.addAttribute("deliverycount", deliverycount);
		//배송중
		List<Orderitem> deliveryingcount = orderitem.stream()
				.filter(o -> "배송중".equals(o.getOrderitemDstatus()))
				.collect(Collectors.toList());
		model.addAttribute("deliveryingcount", deliveryingcount);
		//배송완료
		List<Orderitem> deliverysucesscount = orderitem.stream()
				.filter(o -> "배송완료".equals(o.getOrderitemDstatus()))
				.collect(Collectors.toList());
		model.addAttribute("deliverysucesscount", deliverysucesscount);
		//취소요청
		List<Orderitem> filtercancelitem = orderitem.stream()
				.filter(o -> "취소".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("cancelcount", filtercancelitem);
		//반품요청
		List<Orderitem> returncount = orderitem.stream()
				.filter(o -> "반품요청".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("returncount", returncount);
		//교환요청
		List<Orderitem> exchangecount = orderitem.stream()
				.filter(o -> "교환요청".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("exchangecount", exchangecount);
		//구매확정
		List<Orderitem> buyend = orderitem.stream()
				.filter(o -> "구매확정".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("buyend", buyend);
		//오늘정산
		List<Orderitem> todayend = orderitem.stream()
				.filter(o -> "구매확정".equals(o.getOrderitemCase()))
				.filter(o -> o.getOrderitemDate().isAfter(currentDate))
				.collect(Collectors.toList());
		model.addAttribute("todayend", todayend);
		//////////////
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

	//마이페이지 메핑
	@RequestMapping("mypage")
	public String mypage(Model model){
		return "/seller/mypage";
	}

	//faq페이지 메핑
	@RequestMapping("faq")
	public String faq(Model model) {
		return "/seller/faq";
	}

	//회원가입 페이지 메핑
	@RequestMapping("test")
	public String test(Model model) {
		return "seller/test";
	}


}