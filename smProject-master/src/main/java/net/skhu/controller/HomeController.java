package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//컨트롤러 만들었으면 중복된것 지우기
@Controller
public class HomeController {
	@RequestMapping({"introduce"})
    public String introduce() {
        return "introduce";
    }

    @RequestMapping("board")
    public String board() {
        return "board";
    }

    @RequestMapping("board_detail")
    public String board_detail() {
        return "board_detail";
    }

    @RequestMapping("board_create")
    public String board_create() {
        return "board_create";
    }

    @RequestMapping("mentorapply")
    public String mentorapply() {
        return "mentorapply";
    }

    @RequestMapping("menteeapply")
    public String menteeapply() {
        return "menteeapply";
    }

    @RequestMapping("menteeapply_detail")
    public String menteeapply_detail() {
        return "menteeapply_detail";
    }

    @RequestMapping("question")
    public String question() {
        return "question";
    }

    @RequestMapping("question_detail")
    public String question_detail() {
        return "question_detail";
    }

    @RequestMapping("question_create")
    public String question_create() {
        return "question_create";
    }

    @RequestMapping("timetable")
    public String timetable() {
        return "timetable";
    }

    @RequestMapping("report")
    public String report() {
        return "report";
    }

    @RequestMapping("report_detail")
    public String report_detail() {
        return "report_detail";
    }

    @RequestMapping("report_create")
    public String report_create() {
        return "report_create";
    }

    @RequestMapping("mypost")
    public String mypost() {
        return "mypost";
    }

    @RequestMapping("sendEmail")
    public String sendEmail() {
        return "sendEmail";
    }

    @RequestMapping("meminfo")
    public String meminfo() {
        return "meminfo";
    }

    @RequestMapping("m_introduce_modi")
    public String m_introduce_modi() {
        return "m_introduce_modi";
    }

    @RequestMapping("m_register")
    public String m_register() {
        return "m_register";
    }

    @RequestMapping("m_contact")
    public String m_contact() {
        return "m_contact";
    }

    @RequestMapping("m_contact_detail")
    public String m_contact_detail() {
        return "m_contact_detail";
    }

    @RequestMapping("m_userManage")
    public String m_userManage() {
        return "m_userManage";
    }

    @RequestMapping("m_mentoringManage")
    public String m_mentoringManage() {
        return "m_mentoringManage";
    }

    @RequestMapping("mentoring_info")
    public String mentoring_info() {
        return "mentoring_info";
    }

    @RequestMapping("m_reportManage")
    public String m_reportManage() {
        return "m_reportManage";
    }

    @RequestMapping("m_setting")
    public String m_setting() {
        return "m_setting";
    }
}

