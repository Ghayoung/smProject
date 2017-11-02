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

}
