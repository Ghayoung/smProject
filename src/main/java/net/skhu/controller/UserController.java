package net.skhu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.skhu.dto.User;
import net.skhu.mapper.UserMapper;

@Controller
public class UserController {

	@Autowired UserMapper userMapper;

	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		model.addAttribute("board", "로그인");		//게시판 이름 넣기
		return "login";
	}

	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(Model model, User user, HttpSession session) {
		model.addAttribute("board", "로그인");
		if(user.getUser_id()==null) {
			model.addAttribute("error","아이디를 입력해주세요.");
			return "login";
		}
		if(user.getPw()==null) {
			model.addAttribute("error","비밀번호를 입력해주세요.");
			return "login";
		}
		User findUser = userMapper.findOne(user.getUser_id());
		if(findUser==null) {
			model.addAttribute("error","아이디가 존재하지 않습니다.");
			return "login";
		}
		else if(findUser.getPw().equals(user.getPw())) {
			session.setAttribute("id", findUser.getId());
			session.setAttribute("user_id", findUser.getUser_id());
			session.setAttribute("type", findUser.getType());
			model.addAttribute("board", "");
			return "main";
		}
		else {
			model.addAttribute("error","아이디와 비밀번호가 일치하지 않습니다.");
			return "login";
		}
	}

	@RequestMapping("logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		model.addAttribute("logout","로그아웃되었습니다.");
		return "main";
	}

	@RequestMapping(value="meminfo", method=RequestMethod.GET)
	public String meminfo(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		model.addAttribute("board", "회원정보 수정");
		return "meminfo";
	}

	@RequestMapping(value="meminfo", method=RequestMethod.POST)
	public String meminfo(Model model, User user, HttpSession session) {
		model.addAttribute("board", "회원정보 수정");
		User findUser = userMapper.findOneById((int)session.getAttribute("id"));
	}

}
