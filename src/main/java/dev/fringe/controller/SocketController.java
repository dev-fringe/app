package dev.fringe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dev-frine
 */
@Controller
public class SocketController {

	@GetMapping("/socket")
	public String index() {
		return "socket";
	}
}
