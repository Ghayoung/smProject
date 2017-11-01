package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestController {

	@RequestMapping({"/", "guest/main"})
	public String main() {
		return "guest/main";
	}

	@RequestMapping("guest/login")
	public String login() {
		return "guest/login";
	}
}
