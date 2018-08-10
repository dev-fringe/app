package dev.fringe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.fringe.entity.User;
import dev.fringe.service.LoginService;
import dev.fringe.service.UserService;

/**
 * @author dev-frine
 */
@Controller
public class UserController {

	@Autowired
	private UserService user;

	@Autowired
	private LoginService login;

	@RequestMapping(value = "/user", method = {RequestMethod.GET , RequestMethod.POST})
	public String get(Model m, User u) {
		System.out.println(u);
		m.addAllAttributes(user.get(u));
		return "user";
	}
	
	@GetMapping("/register")
	public String form(Model m) {
		m.addAttribute("userAttribute", new User());
		m.addAttribute("roles", login.getRoles());
		return "register";
	}

	@PostMapping("/register")
	public String add(User u) {
		user.add(u);
		return "redirect:/";
	}
}
