package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//컨트롤러 만들었으면 중복된것 지우기
@Controller
@RequestMapping("/user")
public class UserController {

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

    @RequestMapping("mentorapply")
    public String mentorapply() {
        return "user/mentorapply";
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
}
