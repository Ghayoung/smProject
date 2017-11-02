package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//컨트롤러 만들었으면 중복된것 지우기
@Controller
@RequestMapping("/guest")
public class HomeController {

	@RequestMapping("main")
	public String main() {
		return "guest/main";
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

    @RequestMapping("mentorapply")
    public String mentorapply() {
        return "guest/mentorapply";
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

    @RequestMapping("m_setting")
    public String m_setting() {
        return "guest/m_setting";
    }
}
