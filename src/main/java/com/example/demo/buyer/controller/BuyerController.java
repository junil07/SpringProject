package com.example.demo.buyer.controller;

import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BuyerController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("buyer/{subPath}")
	public String main
			(@PathVariable("subPath") String subPath, Model model) {
		List<Category> categories = categoryService.getAll();
		model.addAttribute("category", categories);
		return "buyer/"+subPath;
	}
}
