package net.skhu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.skhu.dto.User;
import net.skhu.mapper.UserMapper;
import net.skhu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired UserMapper userMapper;
	@Autowired UserService userService;

	@RequestMapping("board")
    public String board() {
        return "user/board";
    }

    @RequestMapping("board_detail")
    public String board_detail() {
        return "user/board_detail";
    }

    @RequestMapping("board_create")
    public String board_create() {
        return "user/board_create";
    }

    @RequestMapping("menteeapply")
    public String menteeapply() {
        return "user/menteeapply";
    }

    @RequestMapping("menteeapply_detail")
    public String menteeapply_detail() {
        return "user/menteeapply_detail";
    }

    @RequestMapping("question")
    public String question() {
        return "user/question";
    }

    @RequestMapping("question_detail")
    public String question_detail() {
        return "user/question_detail";
    }

    @RequestMapping("question_create")
    public String question_create() {
        return "user/question_create";
    }

    @RequestMapping("timetable")
    public String timetable() {
        return "user/timetable";
    }

    @RequestMapping("report")
    public String report() {
        return "user/report";
    }

    @RequestMapping("report_detail")
    public String report_detail() {
        return "user/report_detail";
    }

    @RequestMapping("report_create")
    public String report_create() {
        return "user/report_create";
    }

    @RequestMapping("mypost")
    public String mypost() {
        return "user/mypost";
    }

    @RequestMapping("sendEmail")
    public String sendEmail() {
        return "user/sendEmail";
    }

    @RequestMapping("meminfo")
    public String meminfo() {
        return "user/meminfo";
    }

    @RequestMapping("mentoring_info")
    public String mentoring_info() {
        return "user/mentoring_info";
    }

	@RequestMapping(value="meminfo", method=RequestMethod.GET)
	public String meminfo(Model model) {
		model.addAttribute("user", UserService.getCurrentUser());
		return "user/meminfo";
	}

	@RequestMapping(value="meminfo", method=RequestMethod.POST)
	public String meminfo(Model model, HttpServletRequest request) {
		model.addAttribute("board", "회원정보 수정");
		User user = UserService.getCurrentUser();
		if(user.getPw() != request.getParameter("pw")) {
			return "user/meminfo?error";
		}
		if(request.getParameter("newPw") != null) {
			if(request.getParameter("newPw") != request.getParameter("newPw2")) {
				return "user/meminfo?error";
			}
			user.setPw(request.getParameter("newPw"));
		}
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		userMapper.update(user);
		return "user/meminfo";
	}



}
