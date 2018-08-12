package dev.fringe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dev-frine
 */
@Controller
public class LoginController {

//	private SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logoutSuccess")
	public String logoutSuccess() {
		return "/logoutSuccess";
	}

//	@PostMapping
//	public void authenticate(@RequestParam Map<String, String> map, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		Authentication result = new UsernamePasswordAuthenticationToken(map.get("username"), "N/A",
//				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
//		SecurityContextHolder.getContext().setAuthentication(result);
//		handler.onAuthenticationSuccess(request, response, result);
//	}
}
