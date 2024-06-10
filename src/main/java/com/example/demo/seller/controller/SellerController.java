package com.example.demo.seller.controller;

import com.example.demo.admin.Entity.Seller;
import com.example.demo.admin.security.SecurityServiceImple;
import com.example.demo.admin.service.SellerServiceImple;
import com.example.demo.admin.service.SendMessageService;
import com.example.demo.buyer.DTO.BuyerDTO;
import com.example.demo.seller.DTO.OrderitemDTO;
import com.example.demo.seller.DTO.SellerDTO;
import com.example.demo.seller.domain.Orderitem;
import com.example.demo.seller.service.OrderitemService;
import com.example.demo.seller.service.ProductService;
import com.example.demo.seller.service.SellerService;
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