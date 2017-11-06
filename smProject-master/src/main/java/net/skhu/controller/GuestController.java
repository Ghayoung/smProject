package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.skhu.dto.User;
import net.skhu.mapper.UserMapper;
import net.skhu.service.UserService;

//컨트롤러 만들었으면 중복된것 지우기
@Controller
@RequestMapping("/guest")
public class GuestController {

	@Autowired UserMapper userMapper;
	@Autowired UserService userService;

	@RequestMapping("main")
	public String main() {
		return "guest/main";
	}

	@RequestMapping("introduce")
	public String introduce() {
		return "guest/introduce";
	}

	@RequestMapping("login")
	public String login(Model model) {
		model.addAttribute("board", "로그인");
		return "guest/login";
	}

	@RequestMapping(value="join", method=RequestMethod.GET)
	public String join(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("board", "회원가입");
		return "guest/join";
	}

	@RequestMapping(value="join", method=RequestMethod.POST)
	public String join(Model model, User user) {
		userService.join(user);
		model.addAttribute("board", "회원가입");
		return "guest/login";

	}
}
