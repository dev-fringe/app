package dev.fringe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dev-frine
 */
@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("/hello")
	public  @ResponseBody String hello() {
		return "Hello world!";
	}
}
