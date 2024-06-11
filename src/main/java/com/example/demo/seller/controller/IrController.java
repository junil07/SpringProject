package com.example.demo.seller.controller;

import com.example.demo.admin.Entity.Seller;
import com.example.demo.admin.security.SecurityServiceImple;
import com.example.demo.admin.service.SellerServiceImple;
import com.example.demo.buyer.DTO.BuyerDTO;
import com.example.demo.buyer.entity.Review;
import com.example.demo.buyer.service.ReviewService;
import com.example.demo.seller.DTO.OrderitemDTO;
import com.example.demo.seller.DTO.SellerDTO;
import com.example.demo.seller.domain.Orderitem;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.service.OrderitemService;
import com.example.demo.seller.service.ProductService;
import com.example.demo.seller.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller/ir")
public class IrController {

	private OrderitemService orderitemService;
	private ProductService productService;
	private ReviewService reviewService;
	private SellerService sellerService;
	private SellerServiceImple sellerServiceImple;
	public IrController(OrderitemService orderitemService,
						ProductService productService,
						ReviewService reviewService,
						SellerService sellerService,
						SellerServiceImple sellerServiceImple){
		this.orderitemService = orderitemService;
		this.productService = productService;
		this.reviewService = reviewService;
		this.sellerService = sellerService;
		this.sellerServiceImple = sellerServiceImple;
	}

	//문의/리뷰 관리 페이지 메핑
	//문의 관리
	@RequestMapping("inquiries")
	public String inquiries(Model model) {
		return "/seller/ir/inquiries";
	}
	
	//리뷰 관리
	@RequestMapping("reviews")
	public String reviews(@AuthenticationPrincipal User user,Model model) {
		var sellerId = user.getUsername();
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

		List<Product> Productlist = productService.getProductList(sellerId);
		List<Product> reivewlist = Productlist.stream()
				.filter(p -> user.getUsername().equals(p.getSeller().getSellerId()))
				.collect(Collectors.toList());
		model.addAttribute("reivewlist", reivewlist);

		List<Review> reivew = reviewService.getreivewlist();
		List<Review> privatereivew = reivew.stream()
				.filter(r -> user.getUsername().equals(r.getProduct().getSeller().getSellerId()))
				.sorted(Comparator.comparing(r -> r.getReviewDate(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		model.addAttribute("privatereivew", privatereivew);
		return "/seller/ir/reviews";
	}
}
