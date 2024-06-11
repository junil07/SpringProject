package com.example.demo.buyer.controller;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.admin.repository.BuyerRepository1;
import com.example.demo.admin.security.SecurityServiceImple;
import com.example.demo.admin.service.BuyerServiceImple;
import com.example.demo.admin.service.SendMessageService;
import com.example.demo.buyer.DTO.BuyerDTO;
import com.example.demo.buyer.entity.*;
import com.example.demo.buyer.repository.CartRepository;
import com.example.demo.buyer.repository.ProductViewRepository;
import com.example.demo.buyer.repository.ReviewRepository;
import com.example.demo.buyer.service.*;
import com.example.demo.buyer.service.CategoryService;
import com.example.demo.buyer.service.CustomProductService;
import com.example.demo.buyer.service.ProductSizeimple;
import com.example.demo.seller.DTO.SellerDTO;
import com.example.demo.seller.domain.Orderitem;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.ProductImage;
import com.example.demo.seller.domain.ProductRelation;
import com.example.demo.seller.repository.OrderitemRepository;
import com.example.demo.seller.service.ProductImageService;
import com.example.demo.seller.service.ProductRelationService;
import com.example.demo.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
public class BuyerController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CustomProductService customProductService;

	@Autowired
	private ProductViewRepository productViewRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ProductSizeimple productSizeimple;

	@Autowired
	private BuyerDTO buyerDTO;

	@Autowired
	private SellerDTO sellerDTO;

	@Autowired
	private SecurityServiceImple securityService;

	@Autowired
	private StockService stockService;

	@Autowired
	private BuyerServiceImple buyerService1;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SendMessageService sendMessageService;
  
  @Autowired
	private BuyerRepository1 buyerRepository1;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductImageService productImageService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderitemRepository orderitemRepository;

	@Autowired
	private ProductRelationService productRelationService;

	@RequestMapping("buyer/index")
	public String main
			(@AuthenticationPrincipal User user, Principal principal,Model model) {
		String buyerId="";
		if (principal != null) { // 로그인이 되었을 때 이야기임 로그인이 안되었을 때 예외는 if else 밖에
			if (securityService.hasRole(user, "ROLE_BUYER")) { // 만약 '구매자'의 권한을 갖고 있다면
				buyerId = user.getUsername();
			} else { // 다른 권한을 갖고 있거나
			}
		}else{
			buyerId=null;
		}
		List<Category> categories = categoryService.getRows();
		List<Map<String, Object>> productSummaryList = customProductService.getProductSummary();
		model.addAttribute("buyerId",buyerId);
		model.addAttribute("categories", categories);
		model.addAttribute("productSummaryList", productSummaryList);
		return "buyer/index";
	}

	@RequestMapping("buyer/detail")
	public String detail(@AuthenticationPrincipal User user, Principal principal, Model model, @RequestParam("productId") Integer productId){
		System.out.print(productId);
		List<Category> categories = categoryService.getRows();
		List<ProductView> productDetail = productViewRepository.findByProductId(productId);
		List<String> productSize = productSizeimple.getRowParamOne(productId);
		List<Stock> stockList = stockService.getStockList(productId);
		List<Review> reviewList = reviewRepository.findByProductProductIdOrderByReviewIdDesc(productId);
		String buyerId="";
		if (principal != null) { // 로그인이 되었을 때 이야기임 로그인이 안되었을 때 예외는 if else 밖에
			if (securityService.hasRole(user, "ROLE_BUYER")) { // 만약 '구매자'의 권한을 갖고 있다면
				buyerId = user.getUsername();
			} else { // 다른 권한을 갖고 있거나
			}
		}else{
			buyerId=null;
		}

		model.addAttribute("buyerId", buyerId);
		model.addAttribute("categories", categories);
		model.addAttribute("productDetail", productDetail);
		model.addAttribute("productSize", productSize);
		model.addAttribute("stockList", stockList);
		model.addAttribute("reviewList", reviewList);

		//연관상품 사진
		//해당 상품의 연관 상품 리스트
		List<Product> productList = productRelationService.getRPListProduct(productId);
		if(productList != null) {
			model.addAttribute("productList", productList);
			//해당 상품의 연관 상품 이미지 리스트
			List<ProductImage> productImageList = productImageService.getProductImageList(productList);
			model.addAttribute("productImageList", productImageList);
		}
		return "buyer/product_detail";
	}

	// 로그인 페이지로 이동
	@RequestMapping("/buyer/login")
	public String buyerLogin(@AuthenticationPrincipal User user, Principal principal, Model model) {

		System.out.println("구매자 로그인 버튼으로 이동");
		String alert = "";

		if (principal != null) {
			if (securityService.hasRole(user, "ROLE_BUYER")) {
				alert = "이미 구매자 로그인이 되어있습니다.";
			} else if (securityService.hasRole(user, "ROLE_SELLER")) {
				alert = "이미 판매자 로그인이 되어있습니다.";
			}
			/*
			System.out.println(user.getUsername());
			ㄴ 현재 유지되고 있는 세션의 아이디를 반환
			System.out.println(user.getAuthorities());
			*/
		}

		model.addAttribute("alert", alert);
		model.addAttribute("checkTest", "1");
		model.addAttribute("buyerDTO", buyerDTO);
		model.addAttribute("sellerDTO", sellerDTO);

		return "seller/login";
	}

	// ㄹㅇ 로그인
	@RequestMapping("/buyer/reallogin")
	public String buyerRealLogin(BuyerDTO buyerDTO, Model model) {
		model.addAttribute("buyerDTO", buyerDTO);
		return "buyer/reallogin";
	}

	// 구매자 회원가입
	@RequestMapping("/buyer/register")
	public String signUp(Model model) {
		model.addAttribute("buyer", new Buyer());
		return "/buyer/signup";
	}

	// 회원가입 진행
	@RequestMapping(value = "/buyer/register", method = RequestMethod.POST)
	public String signUpProc(Buyer buyer) {
		// 암호화
		buyer.setBuyerPassword(passwordEncoder.encode(buyer.getBuyerPassword()));
		buyer.setBuyerActivation((short) 1);
		buyer.setBuyerGrade((short) 1);
		buyerService1.register(buyer);
		return "redirect:/buyer/login";
	}

	// ID 중복확인 - ajax
	@RequestMapping("/buyer/idCheck")
	@ResponseBody
	public int idDuplicate(@RequestBody String buyerId) {
		int idCheck = buyerService1.idCheck(buyerId);
		return idCheck;
	}

	// 이름과 이메일로 사용자 있는지 찾기 - ajax
	@RequestMapping("/buyer/idFind")
	@ResponseBody
	public Buyer findId(@RequestBody BuyerDTO request) {
		Buyer buyer = buyerService1.idFind(request.getBuyerName(), request.getBuyerEmail());
		System.out.println("호출 확인" + " " + request.getBuyerName() + " " + request.getBuyerEmail());
		return buyer;
	}

	// 아이디와 이메일로 있는지 확인 - ajax
	@RequestMapping("/buyer/buyerFind")
	@ResponseBody
	public Map<String, String> findBuyer(@RequestBody BuyerDTO request) {
		String verificationCode = "";
		Map<String, Object> responseDate = new HashMap<>();

		Buyer buyer = buyerService1.buyerFind(request.getBuyerId(), request.getBuyerEmail());

		if (buyer != null) {
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
		System.out.println("호출 되었다1");
		System.out.println(verificationCode);
		return response;
	}

	// 새 비밀번호 설정 - ajax
	@RequestMapping("/buyer/newPwd")
	@ResponseBody
	public int newPwd(@RequestBody BuyerDTO request) {
		int result = 0;
		boolean flag = buyerService1.buyerPwdUpdate(request.getBuyerId(), request.getBuyerPassword());

		if (flag) {
			result = 1;
		}

		return result;
	}



  @RequestMapping(value = {"/buyer/productBuy", "/buyer/productBuy"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String productBuy(@AuthenticationPrincipal User user,Principal principal,@RequestParam("cartIds") List<Integer> cartIds,Model model){
		List<Category> categories = categoryService.getRows();
		List<List<Cart>> cartList = new ArrayList<>();
		List<ProductImage> productImages = new ArrayList<>();
		String buyerId="";
		if (principal != null) { // 로그인이 되었을 때 이야기임 로그인이 안되었을 때 예외는 if else 밖에
				if (securityService.hasRole(user, "ROLE_BUYER")) { // 만약 '구매자'의 권한을 갖고 있다면
					buyerId = user.getUsername();
				} else { // 다른 권한을 갖고 있거나
			}
		}else{
			buyerId=null;
		}

		for(int cartId : cartIds){
			List<Cart> cart = cartRepository.findByCartId(cartId);

			cartList.add(cart);
		}
		for (List<Cart> productCart : cartList){
			for(Cart cart : productCart){
				Product product=cart.getProduct();

				ProductImage images = productImageService.getProductImage(product);

				productImages.add(images);
			}
		}
		Optional<Buyer> buyerOptional = buyerRepository1.findBybuyerId(buyerId);
		if (buyerOptional.isPresent()) {
			model.addAttribute("buyer", buyerOptional.get());
		} else {
			// Buyer 정보가 없는 경우 처리
			model.addAttribute("buyer", new Buyer()); // 예시: 빈 Buyer 객체 추가
		}
		model.addAttribute("buyerId", buyerId);
		model.addAttribute("categories", categories);
		model.addAttribute("productImages",productImages);
		model.addAttribute("cartList",cartList);
		return "buyer/productBuy";
	}

	@RequestMapping("/buyer/productBuyList")
	public String productBuyList(@AuthenticationPrincipal User user,Principal principal,Model model){
		List<Category> categories = categoryService.getRows();
		List<ProductImage> productImages = new ArrayList<>();
		String buyerId="";
		if (principal != null) { // 로그인이 되었을 때 이야기임 로그인이 안되었을 때 예외는 if else 밖에
			if (securityService.hasRole(user, "ROLE_BUYER")) { // 만약 '구매자'의 권한을 갖고 있다면
				buyerId = user.getUsername();
			} else { // 다른 권한을 갖고 있거나
			}
		}else{
			buyerId=null;
		}

		List<Orderitem> orderList = orderitemRepository.findOrderitemsByBuyerId(buyerId);


		for(Orderitem orderitems : orderList){
			Product product=orderitems.getProduct();

			ProductImage images = productImageService.getProductImage(product);

			productImages.add(images);
			System.out.println(productImages);
		}

		System.out.println(orderList.size());
		System.out.println(productImages.size());
		model.addAttribute("buyerId", buyerId);
		model.addAttribute("productImages", productImages);
		model.addAttribute("orderList" , orderList);
		model.addAttribute("categories", categories);
		return "/buyer/productBuyList";
	}

	@GetMapping("/buyer/productList/{categoryName}")
	public String productList(@AuthenticationPrincipal User user, Principal principal,@PathVariable("categoryName") String categoryName,Model model){
		List<Product> product = productService.getProductsByCategoryName(categoryName);
		List<ProductImage> productImages = productService.getProductImagesByCategoryName(categoryName);
		List<Category> categories = categoryService.getRows();
		String buyerId="";
		if (principal != null) { // 로그인이 되었을 때 이야기임 로그인이 안되었을 때 예외는 if else 밖에
			if (securityService.hasRole(user, "ROLE_BUYER")) { // 만약 '구매자'의 권한을 갖고 있다면
				buyerId = user.getUsername();
			} else { // 다른 권한을 갖고 있거나
			}
		}else{
			buyerId=null;
		}
		// System.out.println을 사용하여 출력
		System.out.println("Products:");
		for (Product p : product) {
			System.out.println("Product ID: " + p.getProductId());
			System.out.println("Product Code: " + p.getProductCode());
			System.out.println("Product Name: " + p.getProductName());
			System.out.println("Product Explain: " + p.getProductExplain());
			System.out.println("Product Price: " + p.getProductPrice());
			System.out.println("Product Discount: " + p.getProductDiscount());
			System.out.println("Product Hashtag: " + p.getProductHashtag());
			System.out.println("Product Activation: " + p.getProductActivation());
			System.out.println("Seller: " + (p.getSeller() != null ? p.getSeller().getSellerId() : "N/A"));
			System.out.println("Category: " + (p.getCategory() != null ? p.getCategory().getCategoryId() : "N/A"));
			System.out.println("--------------------------------------------------");
		}

		System.out.println("Product Images:");
		for (ProductImage pi : productImages) {
			System.out.println("Image Sname: " + pi.getProductImageSname());
			System.out.println("Product ID: " + (pi.getProduct() != null ? pi.getProduct().getProductId() : "N/A"));
			System.out.println("Image Name: " + pi.getProductImageName());
			System.out.println("Image Extension: " + pi.getProductImageExtension());
			System.out.println("Image Size: " + pi.getProductImageSize());
			System.out.println("--------------------------------------------------");
		}
		model.addAttribute("buyerId", buyerId);
		model.addAttribute("categories", categories);
		model.addAttribute("categoryName",categoryName);
		model.addAttribute("productList",product);
		model.addAttribute("productImage",productImages);
		return "/buyer/productList";
	}

	@GetMapping("buyer/productSearch")
	public String searchProducts(@AuthenticationPrincipal User user, Principal principal,@RequestParam("site_search") String keyword, Model model){
		List<Product> productList = productService.getProductByHashtagName(keyword);
		List<ProductImage> productImageList = new ArrayList<>();
		String buyerId="";
		String productflag="";
		for(Product productIndex : productList){
			ProductImage images = productImageService.getProductImage(productIndex);

			productImageList.add(images);
		}
		System.out.println(keyword);
		if (principal != null) { // 로그인이 되었을 때 이야기임 로그인이 안되었을 때 예외는 if else 밖에
			if (securityService.hasRole(user, "ROLE_BUYER")) { // 만약 '구매자'의 권한을 갖고 있다면
				buyerId = user.getUsername();
			} else { // 다른 권한을 갖고 있거나
			}
		}else{
			buyerId=null;
		}
		if(productList.size()==0){
			productflag = "false";
		}

		model.addAttribute("productflag",productflag);
		model.addAttribute("buyerId",buyerId);
		model.addAttribute("productList",productList);
		model.addAttribute("productImage",productImageList);
		model.addAttribute("hashtag", keyword);
		return "buyer/productSearch";
	}
}
