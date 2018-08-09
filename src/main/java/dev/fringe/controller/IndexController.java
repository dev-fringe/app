package dev.fringe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.fringe.service.UserService;

/**
 * @author dev-frine
 */
@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public String index() {
//		userService.save();
		return "index";
	}
	
	
	@GetMapping("/hello")
	public  @ResponseBody String hello() {
		return "Hello world!";
	}
}
