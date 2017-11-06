package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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




}
