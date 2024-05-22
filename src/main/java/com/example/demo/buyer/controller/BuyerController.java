package com.example.demo.buyer.controller;

import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.entity.CategoryPath;
import com.example.demo.buyer.service.CategoryPathServiceImple;
import com.example.demo.buyer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class BuyerController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("buyer/{subPath}")
	public String main
			(@PathVariable("subPath") String subPath, Model model) {
		List<Category> categories = categoryService.getRows();
		model.addAttribute("categories", categories);
		return "buyer/"+subPath;
	}
}
