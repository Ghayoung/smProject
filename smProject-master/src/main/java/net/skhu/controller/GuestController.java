package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.skhu.dto.Setting;
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

	@RequestMapping("manager")
	public String manager() {
		return "guest/manager";
	}

	@RequestMapping("login")
	public String login() {
		return "guest/login";
	}


    @RequestMapping("board")
    public String board() {
        return "guest/board";
    }

    @RequestMapping("board_detail")
    public String board_detail() {
        return "guest/board_detail";
    }

    @RequestMapping("board_create")
    public String board_create() {
        return "guest/board_create";
    }

    @RequestMapping("menteeapply")
    public String menteeapply() {
        return "guest/menteeapply";
    }

    @RequestMapping("menteeapply_detail")
    public String menteeapply_detail() {
        return "guest/menteeapply_detail";
    }

    @RequestMapping("question")
    public String question() {
        return "guest/question";
    }

    @RequestMapping("question_detail")
    public String question_detail() {
        return "guest/question_detail";
    }

    @RequestMapping("question_create")
    public String question_create() {
        return "guest/question_create";
    }

    @RequestMapping("timetable")
    public String timetable() {
        return "guest/timetable";
    }

    @RequestMapping("report")
    public String report() {
        return "guest/report";
    }

    @RequestMapping("report_detail")
    public String report_detail() {
        return "guest/report_detail";
    }

    @RequestMapping("report_create")
    public String report_create() {
        return "guest/report_create";
    }

    @RequestMapping("mypost")
    public String mypost() {
        return "guest/mypost";
    }

    @RequestMapping("sendEmail")
    public String sendEmail() {
        return "guest/sendEmail";
    }

    @RequestMapping("meminfo")
    public String meminfo() {
        return "guest/meminfo";
    }

    @RequestMapping("m_introduce_modi")
    public String m_introduce_modi() {
        return "guest/m_introduce_modi";
    }

    @RequestMapping("m_register")
    public String m_register() {
        return "guest/m_register";
    }

    @RequestMapping("m_contact")
    public String m_contact() {
        return "guest/m_contact";
    }

    @RequestMapping("m_contact_detail")
    public String m_contact_detail() {
        return "guest/m_contact_detail";
    }

    @RequestMapping("m_userManage")
    public String m_userManage() {
        return "guest/m_userManage";
    }

    @RequestMapping("m_mentoringManage")
    public String m_mentoringManage() {
        return "guest/m_mentoringManage";
    }

    @RequestMapping("mentoring_info")
    public String mentoring_info() {
        return "guest/mentoring_info";
    }

    @RequestMapping("m_reportManage")
    public String m_reportManage() {
        return "guest/m_reportManage";
    }

    @RequestMapping(value="m_setting", method=RequestMethod.GET)
	public String m_setting(Model model) {
		Setting setting = new Setting();
		model.addAttribute("setting", setting);
		return "guest/m_setting";
	}

	@RequestMapping(value="m_setting", method=RequestMethod.POST)
	public String m_setting(Model model, Setting setting) {
		System.out.println(setting.getMax_mentee());
		System.out.println(setting.getMax_mentor());
		System.out.println(setting.getMin_mentee());
		System.out.println(setting.getStudy_count());
		System.out.println(setting.getReport_deadline());
		System.out.println(setting.getMentor_start_date());
		System.out.println(setting.getMentor_expire_date());
		System.out.println(setting.getMentee_start_date());
		System.out.println(setting.getMentee_expire_date());

		userMapper.m_setting(setting);
		model.addAttribute("setting", setting);
		return "guest/m_setting";

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
