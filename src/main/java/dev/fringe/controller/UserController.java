package dev.fringe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.fringe.entity.User;
import dev.fringe.service.UserService;

/**
 * @author dev-frine
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public String user(Model m, User user) {
		m.addAllAttributes(userService.get(user));
		return "user";
	}
}
