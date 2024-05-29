package com.example.demo.admin.controller;

import com.example.demo.admin.service.AdminServiceImple;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	private AdminServiceImple adminService;

	public AdminController(AdminServiceImple adminService) {
		this.adminService = adminService;
	}

	// 메인 페이지
	@RequestMapping("*")
	public String index(Model model) {
		model.addAttribute("test", "testbaby");
		System.out.println("메인 페이지");
		return "admin/index";
	}

	// 로그인 페이지로 이동
	@RequestMapping("login")
	public String loginPage(Model model) {
		System.out.println("로그인 페이지 이동");
		if (adminService.isLoggedIn()) {
			System.out.println("로그인 페이지 입장 중 관리자 로그인 된 거 확인");
			model.addAttribute("alert", "님 이미 로그인 되셨어요");
		}
		return "admin/login";
	}

	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("adminId") String adminId, @RequestParam("adminPassword") String adminPassword, Model model) {
		if (adminService.isLoggedIn()) {
			System.out.println("넌 이미 관리자 로그인 되어있다. !!!");
			return "redirect:/admin/main";
		} else if (adminService.login(adminId, adminPassword)) {
			System.out.println("로그인 되었다");
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
		return "redirect:/admin/index";
	}

}
