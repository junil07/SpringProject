package com.example.demo.admin.controller;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.admin.model.buyer.Activation;
import com.example.demo.admin.model.buyer.Grade;
import com.example.demo.admin.service.AdminServiceImple;
import com.example.demo.admin.service.BuyerServiceImple;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	private HttpSession httpSession;
	private AdminServiceImple adminService;
	private BuyerServiceImple buyerService;

	public AdminController(AdminServiceImple adminService, BuyerServiceImple buyerService) {
		this.adminService = adminService;
		this.buyerService = buyerService;
	}

	// 메인 페이지
	@RequestMapping("main")
	public String index(Model model) {
		model.addAttribute("test", "testbaby");
		System.out.println("메인 페이지");
		String adminSession = (String) httpSession.getAttribute("adminSession");
		String adminName = (String) httpSession.getAttribute("adminName");
		model.addAttribute("adminSession", adminSession);
		model.addAttribute("adminName", adminName);
		return "admin/index";
	}

	// 로그인 페이지로 이동
	@RequestMapping("login")
	public String loginPage(Model model) {
		System.out.println("로그인 페이지 이동");
		String page = "admin/login";
		if (adminService.isLoggedIn()) {
			System.out.println("###########################################");
			System.out.println("로그인 페이지 입장 중 관리자 로그인 된 거 확인");
			System.out.println("###########################################");
			//model.addAttribute("alert", "님 이미 로그인 되셨어요");
			page = "redirect:/admin/proc/alreadyloggedin";
		}
		System.out.println(page);
		return page;
	}

	// 로그인 이미 되었음
	@RequestMapping("proc/alreadyloggedin")
	public String alreadyLoggedIn() {

		return "/admin/proc/alreadyloggedin";
	}

	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("adminId") String adminId, @RequestParam("adminPassword") String adminPassword, Model model) {
		if (adminService.isLoggedIn()) {
			System.out.println("넌 이미 관리자 로그인 되어있다. !!!");
			return "redirect:/admin/main";
		} else if (adminService.login(adminId, adminPassword)) {
			System.out.println("로그인 되었다");
			System.out.println((String) httpSession.getAttribute("adminSession"));
			// model.addAttribute("adminSession", adminSession);
			return "redirect:/admin/main";
		} else {
			model.addAttribute("error", "안됨 ㅋㅋ");
			System.out.println("아이디 비밀번호 일치하지 않다");
			return "admin/login";
		}
	}

	// 로그아웃
	@RequestMapping("logout")
	public String logout() {
		adminService.logout();
		System.out.println("로그아웃 되었다");
		return "redirect:/admin/main";
	}

	// 상품 등록 승인
	@RequestMapping("approval")
	public String approval() {
		return "admin/menu/approval";
	}

	// 구매자 관리
	@RequestMapping("buyermanagement")
	public String buyermanagement(Model model) {
		String[] column = {"아이디", "이름", "생일", "이메일", "주소", "전화번호", "계정 활성화 여부", "마지막 로그인", "등급"};
		List columnName = new ArrayList<>();
		for (int i = 0; i < column.length; i++) {
			columnName.add(column[i]);
		}
		List<Buyer> buyerList = buyerService.getBuyerList();

		Grade grade = new Grade();
		Activation activation = new Activation();

		grade.setGradeUpdate(123);
		activation.setActivationUpdate(456);

		model.addAttribute("columnName", columnName);
		model.addAttribute("buyerList", buyerList);
		model.addAttribute("grade", grade);
		model.addAttribute("activation", activation);

		return "admin/menu/buyermanagement";
	}

	// 판매자 관리
	@RequestMapping("sellermanagement")
	public String sellermanagement() {
		return "admin/menu/sellermanagement";
	}

	// 구매자 - 계정 활성화 변경
	@RequestMapping("ActivationUpdate")
	public String activationUpdate(Activation activation, Model model) {

		List<String> checkList = buyerService.updateActivation(activation.getActivationId(), (short) activation.getActivationUpdate());

		if (checkList.contains("1") && !checkList.contains("0")) {
			model.addAttribute("error", "변경하였습니다");
		} else if (checkList.contains("1") && checkList.contains("0")) {
			model.addAttribute("error", "중간에 오류가 발생하였습니다");
		} else {
			model.addAttribute("error", "변경에 실패하였습니다");
		}

		return "admin/proc/buyerupdatecheck";
	}

	// 구매자 - 등급 변경
	@RequestMapping("GradeUpdate")
	public String gradeUpdate(Grade grade, Model model) {

		List<String> checkList = buyerService.updateGrade(grade.getGradeId(), (short) grade.getGradeUpdate());

		if (checkList.contains("1") && !checkList.contains("0")) {
			model.addAttribute("error", "변경하였습니다");
		} else if (checkList.contains("1") && checkList.contains("0")) {
			model.addAttribute("error", "중간에 오류가 발생하였습니다");
		} else {
			model.addAttribute("error", "변경에 실패하였습니다");
		}

		return "admin/proc/buyerupdatecheck";
	}

}
