package com.example.demo.seller.controller;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.admin.Entity.Seller;
import com.example.demo.admin.security.SecurityServiceImple;
import com.example.demo.admin.service.BuyerService;
import com.example.demo.admin.service.BuyerServiceImple;
import com.example.demo.admin.service.SellerServiceImple;
import com.example.demo.admin.service.SendMessageService;
import com.example.demo.buyer.DTO.BuyerDTO;
import com.example.demo.seller.DTO.OrderitemDTO;
import com.example.demo.seller.DTO.SellerDTO;
import com.example.demo.seller.domain.Orderitem;
import com.example.demo.seller.service.OrderitemService;
import com.example.demo.seller.service.ProductService;
import com.example.demo.seller.service.SellerService;

import org.springframework.beans.factory.annotation.Autowired;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.security.Principal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/seller")
public class SellerController {

	private OrderitemService orderitemService;
	private SellerService sellerService;
	private SellerServiceImple sellerServiceImple;
	private ProductService productService;
	private BuyerDTO buyerDTO;
	private SellerDTO sellerDTO;
	private SecurityServiceImple securityService;
	private PasswordEncoder passwordEncoder;
	private SendMessageService sendMessageService;
//	OrderitemService orderitemService
	public SellerController(OrderitemService orderitemService, SellerService sellerService,
							ProductService productService, BuyerDTO buyerDTO
							,SellerDTO sellerDTO, SecurityServiceImple securityService,
							SellerServiceImple sellerServiceImple, PasswordEncoder passwordEncoder,
							SendMessageService sendMessageService){
		this.orderitemService = orderitemService;
		this.sellerService = sellerService;
		this.productService = productService;
		this.buyerDTO = buyerDTO;
		this.sellerDTO = sellerDTO;
		this.securityService = securityService;
		this.sellerServiceImple = sellerServiceImple;
		this.passwordEncoder = passwordEncoder;
		this.sendMessageService = sendMessageService;
	}

	//메인 페이지 메핑
	@RequestMapping({"/", "index"})
	public String index(@AuthenticationPrincipal User user, Model model) {
		String userId = user.getUsername();
		// 그래프 데이터 추가
		model.addAttribute("count", orderitemService.findTotalCountByUserId(userId));
		model.addAttribute("price", orderitemService.getTotalPricePerDate(userId));
		model.addAttribute("buyer", orderitemService.findDisBuyer(userId));
		model.addAttribute("product", orderitemService.countsellproduct(userId));
		//////////////
		// 현재 날짜
		LocalDate currentDate = LocalDate.now();
		// 최근 3일 동안의 날짜 계산
		LocalDate threeDaysAgo = currentDate.minusDays(3);
		//전체리스트 출력
		List<Orderitem> orderitem = orderitemService.getOrderitemList();

		//유저 이름 출력 필요요소
		List<Seller> sellers = sellerServiceImple.getSellerList();
		List<Seller> myaccount = sellers.stream()
				.filter(s -> userId.equals(s.getSellerId()))
				.collect((Collectors.toList()));
		model.addAttribute("myaccount",myaccount);

		//개인 아이디로만 출력
		List<Orderitem> privateorder = orderitem.stream()
				.filter(o -> user.getUsername().equals(o.getProduct().getSeller().getSellerId()))
				.collect(Collectors.toList());
		model.addAttribute("privateorder", privateorder);

		//결제대기(결제 전인것만 출력)
		List<Orderitem> paybeforecount = privateorder.stream()
				.filter(o -> "결제전".equals(o.getOrderitemPstatus()))
				.collect(Collectors.toList());
		model.addAttribute("paybeforecount", paybeforecount);

		//신규주문(최근 3일내 주문 들어온것만 출력)
		List<Orderitem> threecount = privateorder.stream()
				.filter(o -> o.getOrderlist().getOrderlistDate().isEqual(threeDaysAgo))
				.collect(Collectors.toList());
		model.addAttribute("threecount", threecount);
		//오늘출발(오늘날짜로 배송중 인것만 출력)

		List<Orderitem> nowdaycount = privateorder.stream()
				.filter(o -> "배송중".equals(o.getOrderitemDstatus())&&
						currentDate.equals(o.getOrderitemDate()))
				.collect(Collectors.toList());
		model.addAttribute("nowdaycount", nowdaycount);
		//배송준비(배송전 인것만 출력)
		List<Orderitem> deliverycount = privateorder.stream()
				.filter(o -> "배송전".equals(o.getOrderitemDstatus()))
				.collect(Collectors.toList());
		model.addAttribute("deliverycount", deliverycount);
		//배송중
		List<Orderitem> deliveryingcount = privateorder.stream()
				.filter(o -> "배송중".equals(o.getOrderitemDstatus()))
				.collect(Collectors.toList());
		model.addAttribute("deliveryingcount", deliveryingcount);
		//배송완료
		List<Orderitem> deliverysucesscount = privateorder.stream()
				.filter(o -> "배송완료".equals(o.getOrderitemDstatus()))
				.collect(Collectors.toList());
		model.addAttribute("deliverysucesscount", deliverysucesscount);
		//취소요청
		List<Orderitem> filtercancelitem = privateorder.stream()
				.filter(o -> "취소".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("cancelcount", filtercancelitem);
		//반품요청
		List<Orderitem> returncount = privateorder.stream()
				.filter(o -> "반품요청".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("returncount", returncount);
		//교환요청
		List<Orderitem> exchangecount = privateorder.stream()
				.filter(o -> "교환요청".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("exchangecount", exchangecount);
		//구매확정
		List<Orderitem> buyend = privateorder.stream()
				.filter(o -> "구매확정".equals(o.getOrderitemCase()))
				.collect(Collectors.toList());
		model.addAttribute("buyend", buyend);
		//오늘정산
		List<Orderitem> todayend = privateorder.stream()
				.filter(o -> "구매확정".equals(o.getOrderitemCase()))
				.filter(o -> o.getOrderitemDate().isAfter(currentDate))
				.collect(Collectors.toList());
		model.addAttribute("todayend", todayend);
//		//////////////
//		List<Seller> sellers = sellerService.getAllSellers();
//		model.addAttribute("sellers", sellers);
		System.out.println(user.getUsername());
		model.addAttribute("user", user.getUsername());
		return "seller/index";
	}

	//로그인 페이지 메핑
	@RequestMapping("login")
	public String login(@AuthenticationPrincipal User user, Principal principal, Model model) {

		System.out.println("판매자 로그인 버튼으로 이동");
		String alert = "";

		if (principal != null) {
			if (securityService.hasRole(user, "ROLE_BUYER")) {
				alert = "이미 구매자 로그인이 되어있습니다.";
			} else if (securityService.hasRole(user, "ROLE_SELLER")) {
				alert = "이미 판매자 로그인이 되어있습니다.";
			}
			// System.out.println(user.getUsername());
			// ㄴ 현재 로그인 되어있는 사람의 아이디를 반환
		}

		model.addAttribute("alert", alert);
		model.addAttribute("checkTest", "2");
		model.addAttribute("buyerDTO", buyerDTO);
		model.addAttribute("sellerDTO", sellerDTO);

		return "seller/login";
	}

	// ㄹㅇ 로그인
	@RequestMapping("reallogin")
	public String realLogin(Model model, SellerDTO sellerDTO) {
		model.addAttribute("sellerDTO", sellerDTO);
		return "seller/reallogin";
	}

	//회원가입 페이지 메핑
	@RequestMapping("signup")
	public String signup(Model model) {
		model.addAttribute("seller", new Seller());
		return "seller/signup";
	}



	// 마이페이지 매핑
	@RequestMapping("mypage")
	public String mypage(@AuthenticationPrincipal User user, Model model) {
		String sellerId = user.getUsername();
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		//유저 이름 출력 필요요소
		List<Seller> seller = sellerServiceImple.getSellerList();
		List<Seller> myaccount = seller.stream()
				.filter(s -> sellerId.equals(s.getSellerId()))
				.collect((Collectors.toList()));
		model.addAttribute("myaccount",myaccount);
		//개인 아이디로만 출력
		List<Orderitem> privateorder = orderitem.stream()
				.filter(o -> user.getUsername().equals(o.getProduct().getSeller().getSellerId()))
				.collect(Collectors.toList());
		model.addAttribute("privateorder", privateorder);
		List<Seller> sellers = sellerServiceImple.getSellersList(sellerId);
		model.addAttribute("sellers", sellers);
		return "/seller/mypage";
	}

	@PostMapping("/mypage/action")
	public ResponseEntity<?> updatemypage(
			@RequestBody List<SellerDTO> sellers,
			@RequestHeader("Action-Type") String actionType,
			@AuthenticationPrincipal User user) {
		String userId = user.getUsername();
		try {
			System.out.println("Received sellers: " + sellers); // 로그 추가
			System.out.println("Action-Type: " + actionType); // 로그 추가

			if ("mypage".equals(actionType)) {
				sellerServiceImple.updateMypage(userId, sellers);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Unknown action type\"}");
			}
			return ResponseEntity.ok("{\"message\":\"처리가 성공적으로 완료되었습니다.\"}");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"처리 중 오류가 발생했습니다: " + e.getMessage() + "\"}");
		}
	}

	@GetMapping("mypage/reload")
	public String reladmypage(@AuthenticationPrincipal User user, Model model){
		String sellerId = user.getUsername();
		List<Orderitem> orderitem = orderitemService.getOrderitemList();

		//개인 아이디로만 출력
		List<Orderitem> privateorder = orderitem.stream()
				.filter(o -> user.getUsername().equals(o.getProduct().getSeller().getSellerId()))
				.collect(Collectors.toList());
		model.addAttribute("privateorder", privateorder);
		List<Seller> sellers = sellerServiceImple.getSellersList(sellerId);
		model.addAttribute("sellers", sellers);
		return "/seller/mypage :: #exchangeSection";
	}

	//faq페이지 메핑
	@RequestMapping("faq")
	public String faq(@AuthenticationPrincipal User user,Model model) {
		String userId = user.getUsername();
		List<Orderitem> orderitem = orderitemService.getOrderitemList();
		//유저 이름 출력 필요요소
		List<Seller> sellers = sellerServiceImple.getSellerList();
		List<Seller> myaccount = sellers.stream()
				.filter(s -> userId.equals(s.getSellerId()))
				.collect((Collectors.toList()));
		model.addAttribute("myaccount",myaccount);
		//개인 아이디로만 출력
		List<Orderitem> privateorder = orderitem.stream()
				.filter(o -> user.getUsername().equals(o.getProduct().getSeller().getSellerId()))
				.collect(Collectors.toList());
		model.addAttribute("privateorder", privateorder);
		return "/seller/faq";
	}

	//회원가입 페이지 메핑
	@RequestMapping("test")
	public String test(Model model) {
		return "seller/test";
	}

	// 회원가입 진행
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signUpProc(Seller seller) {
		seller.setSellerPassword(passwordEncoder.encode(seller.getSellerPassword()));
		seller.setSellerActivation((short) 1);
		sellerServiceImple.register(seller);
		return "redirect:/seller/login";
	}

	// ID 중복확인 - ajax
	@RequestMapping("idCheck")
	@ResponseBody
	public int idDuplicate(@RequestBody String sellerId) {
		int idCheck = sellerServiceImple.idCheck(sellerId);
		return idCheck;
	}

	// 이름과 이메일로 사용자 있는지 확인 - ajax
	@RequestMapping("idFind")
	@ResponseBody
	public Seller findId(@RequestBody SellerDTO request) {
		Seller seller = sellerServiceImple.idFind(request.getSellerName(), request.getSellerEmail());
		System.out.println("나 호출 되었소");
		return seller;
	}

	// 아이디와 이메일로 있는지 확인 - ajax
	@RequestMapping("sellerFind")
	@ResponseBody
	public Map<String, String> findSeller(@RequestBody SellerDTO request) {
		String verificationCode = "";
		Map<String, Object> responseData = new HashMap<>();

		Seller seller = sellerServiceImple.sellerFind(request.getSellerId(), request.getSellerEmail());

		if (seller != null) {
			Random random = new Random();
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < 6; i++) {
				int digit = random.nextInt(10);
				sb.append(digit);
			}
			verificationCode = sb.toString();
			String messageText = "인증 번호는 [" + verificationCode + "] 입니다.";
			// 메세지 보내는거 일단 잘못보내는거 무서워서 내 번호로 고정해놓음
			sendMessageService.sendMessage("tetest", messageText);
		}

		Map<String, String> response = new HashMap<>();
		response.put("verificationCode", verificationCode);
		System.out.println("호출 되었다");
		System.out.println(verificationCode);
		return response;
	}

	// 새 비밀번호 설정 - ajax
	@RequestMapping("newPwd")
	@ResponseBody
	public int newPwd(@RequestBody SellerDTO request) {
		int result = 0;
		boolean flag = sellerServiceImple.sellerPwdUpdate(request.getSellerId(), request.getSellerPassword());

		if (flag) {
			result = 1;
		}
		return result;
	}

	// 메세지 요청 - ajax
	/*
	@RequestMapping(value = "/seller/sendSMS", method = RequestMethod.POST)
	@ResponseBody
	public SingleMessageSentResponse sendSMS() {
		return "ss";
	}
	 */

}