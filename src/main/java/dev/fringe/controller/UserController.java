package dev.fringe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.fringe.entity.Person;
import dev.fringe.entity.User;
import dev.fringe.service.UserLoginService;
import dev.fringe.service.UserService;

/**
 * @author dev-frine
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

    @Autowired
    private UserLoginService userLoginService;

	@GetMapping("/user")
	public String user(Model m, User user) {
		m.addAllAttributes(userService.get(user));
		return "user";
	}
	
	@GetMapping("/register")
    public String register(ModelMap model)
    {
        model.addAttribute("userAttribute", new User());
        model.addAttribute("roles",userLoginService.getRoles());
        return "register";
    }
	
    @RequestMapping(value = "/register_new", method = RequestMethod.POST)
    public String Add(@ModelAttribute("userAttribute") User user)
    {
    	System.out.println(233);
    	userService.add(user);
        return "redirect:/";
    }
}
