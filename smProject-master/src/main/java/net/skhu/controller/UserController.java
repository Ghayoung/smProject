package net.skhu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.dto.Article;
import net.skhu.dto.User;
import net.skhu.mapper.ArticleMapper;
import net.skhu.mapper.BoardMapper;
import net.skhu.mapper.DepartmentMapper;
import net.skhu.mapper.UserMapper;
import net.skhu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired UserMapper userMapper;
	@Autowired DepartmentMapper departmentMapper;
	@Autowired BoardMapper boardMapper;
	@Autowired ArticleMapper articleMapper;
	@Autowired UserService userService;

	@RequestMapping(value="board", method=RequestMethod.GET)
    public String board(Model model, @RequestParam(value="type", defaultValue="0") int type) {
		model.addAttribute("board", boardMapper.findOne(type).getB_name());
		model.addAttribute("article", articleMapper.findAllByBoard(type));
        return "user/board";
    }

    @RequestMapping("board_detail")
    public String board_detail(Model model, @RequestParam(value="type", defaultValue="0") int type, @RequestParam(value="id") int id) {
    	model.addAttribute("board", boardMapper.findOne(type).getB_name());
    	model.addAttribute("article", articleMapper.findOne(id));
        return "user/board_detail";
    }

    @RequestMapping(value="board_create", method=RequestMethod.GET)
    public String board_create(Model model, @RequestParam(value="type", defaultValue="0") int type) {
    	Article article = new Article();
    	model.addAttribute("article", article);
    	model.addAttribute("board", boardMapper.findOne(type).getB_name());
    	return "user/board_create";
    }

    @RequestMapping(value="board_create", method=RequestMethod.POST)
    public String board_create(Model model, Article article, @RequestParam(value="type", defaultValue="0") int type) {
    	userService.createArticle(article, type);
    	return "redirect:board?type="+type;
    }

    @RequestMapping("question")
    public String question(Model model) {
    	model.addAttribute("board", boardMapper.findOne(3).getB_name());
		model.addAttribute("article", articleMapper.findAllByBoard(3));
        return "user/question";
    }

    @RequestMapping("menteeapply")
    public String menteeapply() {
        return "user/menteeapply";
    }

    @RequestMapping("menteeapply_detail")
    public String menteeapply_detail() {
        return "user/menteeapply_detail";
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
		model.addAttribute("board", "회원정보 수정");
		model.addAttribute("user", UserService.getCurrentUser());
		return "user/meminfo";
	}

	@RequestMapping(value="meminfo_processing", method=RequestMethod.POST)
	public String meminfo_processing(Model model, HttpServletRequest request) {
		User user=userService.changeMeminfo(request);
		model.addAttribute("board", "회원정보 수정");
		model.addAttribute("user", UserService.getCurrentUser());
		if(user==null) return "user/meminfo?error";
		return "user/meminfo";
	}



}
