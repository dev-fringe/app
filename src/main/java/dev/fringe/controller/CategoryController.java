package dev.fringe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.fringe.entity.Category;
import dev.fringe.service.CategoryService;

/**
 * @author dev-fringe
 */
@Controller
public class CategoryController {

	@Autowired
	private CategoryService category;

	@RequestMapping(value = "/category", method = {RequestMethod.GET , RequestMethod.POST})
	public String get(Model m, Category i) {
		m.addAllAttributes(category.get(i));
		return "category";
	}
	
	@GetMapping("/registerCategory")
	public String form(Model m) {
		m.addAttribute("category", new Category());
		return "registerCategory";
	}
	@PostMapping("/registerCategory")
	public String add(Category c) {
		category.add(c);
		return "redirect:/category";
	}
}
