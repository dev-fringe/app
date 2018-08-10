package dev.fringe.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.fringe.entity.support.AuthUser;

/**
 * @author dev-frine
 */
@Controller
public class IndexController {

//	@Autowired
//	private UserService userService;
	
	
	@GetMapping("/")
	public String index() {
		AuthUser principal = (AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(principal);

//		userService.save();
		return "index";
	}
	
	
	@GetMapping("/hello")
	public  @ResponseBody String hello() {
		return "Hello world!";
	}
}
