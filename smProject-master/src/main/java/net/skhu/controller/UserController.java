package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.skhu.dto.User;
import net.skhu.mapper.UserMapper;
import net.skhu.service.UserService;

@Controller
@RequestMapping("/guest")
public class UserController {

	@Autowired UserMapper userMapper;
	@Autowired UserService userService;

	/*
	@RequestMapping(value="meminfo", method=RequestMethod.GET)
	public String meminfo(Model model, HttpSession session) {
		User user = userMapper.findOneById((int)session.getAttribute("id"));
		user.setPw("");
		model.addAttribute("user",user);
		model.addAttribute("board", "회원정보 수정");
		return "meminfo";
	}

	@RequestMapping(value="meminfo", method=RequestMethod.POST)
	public String meminfo(Model model, User user, HttpSession session) {
		model.addAttribute("board", "회원정보 수정");
		User findUser = userMapper.findOneById((int)session.getAttribute("id"));
		if(user.getPw().equals(findUser.getPw())) {
			if(user.getNewPw()!=null) {
				if(!user.getNewPw().equals(user.getNewPw2())) {
					model.addAttribute("error","새 비밀번호가 일치하지 않습니다.");
				}
				else {
					user.setPw(user.getNewPw());
				}
			}
			user.setId(findUser.getId());
			userMapper.update(user);
		}
		else {
			model.addAttribute("error","비밀번호를 다시 입력해주세요.");
		}
		return "meminfo";
	}
	*/

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
