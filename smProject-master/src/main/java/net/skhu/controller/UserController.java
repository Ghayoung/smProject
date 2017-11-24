package net.skhu.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.skhu.dto.Article;
import net.skhu.dto.Comment;
import net.skhu.dto.Mentor;
import net.skhu.dto.Report;
import net.skhu.dto.Team;
import net.skhu.dto.User;
import net.skhu.mapper.ArticleMapper;
import net.skhu.mapper.BoardMapper;
import net.skhu.mapper.CommentMapper;
import net.skhu.mapper.DepartmentMapper;
import net.skhu.mapper.FileMapper;
import net.skhu.mapper.MentorMapper;
import net.skhu.mapper.TeamMapper;
import net.skhu.mapper.UserMapper;
import net.skhu.model.Pagination;
import net.skhu.service.ArticleService;
import net.skhu.service.FileService;
import net.skhu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserMapper userMapper;
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	MentorMapper mentorMapper;
	@Autowired
	FileMapper fileMapper;
	@Autowired
	TeamMapper teamMapper;
	@Autowired
	CommentMapper commentMapper;
	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;
	@Autowired
	FileService fileService;
	Report report = new Report();

	@RequestMapping(value = "board", method = RequestMethod.GET)
	public String board(Model model, Pagination pagination) {
		model.addAttribute("board", boardMapper.findOne(pagination.getBd()).getB_name());
		model.addAttribute("article", userService.findAll(pagination));
		return "user/board";
	}

	@RequestMapping("board_detail")
	public String board_detail(Model model, @RequestParam(value = "id") int id, Pagination pagination) {
		Article article = articleMapper.findOne(id);
		Comment newComment = new Comment();
		model.addAttribute("board", boardMapper.findOne(pagination.getBd()).getB_name());
		model.addAttribute("article", article);
		model.addAttribute("comments", commentMapper.findAllByArticle(id));
		model.addAttribute("newComment", newComment);
		model.addAttribute("user", UserService.getCurrentUser().getId());
		return "user/board_detail";
	}

	@RequestMapping(value = "board_create", method = RequestMethod.GET)
	public String board_create(Model model, Pagination pagination) {
		Article article = new Article();
		model.addAttribute("article", article);
		model.addAttribute("board", boardMapper.findOne(pagination.getBd()).getB_name());
		return "user/board_create";
	}

	@RequestMapping(value = "board_create", method = RequestMethod.POST)
	public String board_create(Model model, Article article, Pagination pagination, @RequestBody MultipartFile file,
			HttpServletRequest request) {
		int id = pagination.getBd();
		userService.createArticle(article, id, file, request);
		long recordCount = articleMapper.count(pagination);
		long pageCount = (recordCount + pagination.getSz() - 1) / pagination.getSz();
		return "redirect:board?bd=" + id + "&pg=" + pageCount;
	}

	@RequestMapping(value = "board_edit", method = RequestMethod.GET)
	public String board_edit(Model model, @RequestParam(value = "id") int id, Pagination pagination) {
		model.addAttribute("article", articleMapper.findOne(id));
		model.addAttribute("board", boardMapper.findOne(pagination.getBd()).getB_name());
		return "user/board_create";
	}

	@RequestMapping(value = "board_edit", method = RequestMethod.POST)
	public String board_edit(Model model, Article article, Pagination pagination, @RequestParam(value = "id") int id,
			@RequestBody MultipartFile file, HttpServletRequest request) {
		userService.editArticle(article, file, request);
		return "redirect:board_detail?id=" + id + "&" + pagination.getQueryString();
	}

	@RequestMapping("board_delete")
	public String board_delete(Model model, @RequestParam(value = "id") int id, Pagination pagination) {
		articleMapper.delete(id);
		return "redirect:board?" + pagination.getQueryString();
	}

	@RequestMapping(value = "comment_edit", method = RequestMethod.POST)
	public String comment_edit(Model model, @RequestParam(value = "cid") int cid, HttpServletRequest request,
			@RequestParam(value = "id") int id, Pagination pagination) {
		userService.editComment(request, cid);
		return "redirect:board_detail?id=" + id + "&" + pagination.getQueryString();
	}

	@RequestMapping(value = "comment_create", method = RequestMethod.POST)
	public String comment_create(Model model, Comment newComment, @RequestParam(value = "id") int id,
			Pagination pagination) {
		userService.addComment(newComment, id);
		return "redirect:board_detail?id=" + id + "&" + pagination.getQueryString();
	}

	@RequestMapping("comment_delete")
	public String comment_delete(Model model, @RequestParam(value = "cid") int cid, @RequestParam(value = "id") int id,
			Pagination pagination) {
		commentMapper.delete(cid);
		return "redirect:board_detail?id=" + id + "&" + pagination.getQueryString();
	}

	@RequestMapping("question")
	public String question(Model model, Pagination pagination) {
		model.addAttribute("board", boardMapper.findOne(pagination.getBd()).getB_name());
		model.addAttribute("article", userService.findAll(pagination));
		return "user/question";
	}

	/* 합격 멘토 신청서 목록 출력 */
	@RequestMapping("menteeapply")
	public String menteeapply(Model model) {
		boolean b = false;
		List<Mentor> mentors = mentorMapper.findMentor();
		User user = UserService.getCurrentUser();
		for (int i = 0; i < mentors.size(); i++) {
			mentors.get(i).setState(2);
			if (user.getType() != 4) {
				mentors.get(i).setState(0);
			} else if (user.getType() == 4) {
				List<Team> teams = teamMapper.findTeamByMentor(mentors.get(i).getId());
				for (int j = 0; j < teams.size(); j++) {
					System.out.println("j: " + teams.get(j).getGroup_mentee_id() + " " + user.getId());
					if (user.getId() == teams.get(j).getGroup_mentee_id()) {
						mentors.get(i).setState(1);
						b = true;
					}
				}
			}
			if (!b && mentors.get(i).getMentee_count() == mentors.get(i).getCount())
				mentors.get(i).setState(2);
		}
		model.addAttribute("mentors", mentors);
		return "user/menteeapply";
	}

	@RequestMapping("menteeapply_detail")
	public String menteeapply_detail(Model model, @RequestParam(value = "id") int id) {
		boolean b = false;
		Mentor mentor = mentorMapper.findOne(id);
		User user = UserService.getCurrentUser();
		if (user.getType() != 4) {
			mentor.setState(0);
		} else if (user.getType() == 4) {
			List<Team> teams = teamMapper.findTeamByMentor(mentor.getId());
			for (int j = 0; j < teams.size(); j++) {
				if (user.getId() == teams.get(j).getGroup_mentee_id())
					b = true;
			}
			if (b)
				mentor.setState(1);
			else
				mentor.setState(2);
		}
		if (!b && mentor.getMentee_count() == mentor.getCount()) {
			mentor.setState(2);
		}
		model.addAttribute("mentor", mentor);
		return "user/menteeapply_detail";
	}

	/* 멘티신청 */
	@RequestMapping("mentee_update")
	public String mentee_update(Model model, @RequestParam(value = "id") int id) {
		Team team = new Team();
		User user = UserService.getCurrentUser();
		if (user.getType() != 4) {
			user.setType(4);
			team.setGroup_m_apply_id(id);
			team.setGroup_mentee_id(user.getId());
			teamMapper.insert(team);
		} else if (user.getType() == 4) {
			user.setType(1);
			teamMapper.deleteMentee(user.getId());
		}
		userMapper.type_update(user);
		return "redirect:menteeapply";
	}

	@RequestMapping("mentee_update_detail")
	public String mentee_update_detail(Model model, @RequestParam(value = "id") int id) {
		Team team = new Team();
		User user = UserService.getCurrentUser();
		if (user.getType() != 4) {
			user.setType(4);
			team.setGroup_m_apply_id(id);
			team.setGroup_mentee_id(user.getId());
			teamMapper.insert(team);
		} else if (user.getType() == 4) {
			user.setType(1);
			teamMapper.deleteMentee(user.getId());
		}
		userMapper.type_update(user);
		return "redirect:menteeapply_detail?id=" + id;
	}

	@RequestMapping("timetable")
	public String timetable() {
		return "user/timetable";
	}

	@RequestMapping(value = "report", method = RequestMethod.GET)
	public String report(Model model) {
		User user = UserService.getCurrentUser();

		List<Report> teamReports = userMapper.findAllReportsById(user.getId());
		Report conditionReports = userMapper.findAllConditionById(user.getId());

		int totalReport = userMapper.findStudyCount();

		model.addAttribute("teamReports", teamReports);
		model.addAttribute("conditionReports", conditionReports);
		model.addAttribute("totalReport", totalReport);
		return "user/report";
	}

	@RequestMapping("report_detail")
	public String report_detail(Model model, @RequestParam("id") int id) {
		Report report = userMapper.findOneReport(id);
		model.addAttribute("report", report);
		return "user/report_detail";
	}

	@RequestMapping("report_create")
	public String report_create() {
		return "user/report_create";
	}

	@RequestMapping(value = "report_create", method = RequestMethod.GET)
	public String mentorapply(Model model) {
		Report report = new Report();
		model.addAttribute("report", report);
		return "user/report_create";
	}

	@RequestMapping(value = "report_create", method = RequestMethod.POST)
	public String mentorapply(Model model, HttpServletRequest request, @RequestBody MultipartFile file1,
			@RequestBody MultipartFile file2) {

		User user = UserService.getCurrentUser();
		report.setRep_u_id(user.getId());

		report.setSubject(request.getParameter("subject"));
		report.setPlace(request.getParameter("place"));
		report.setDay(request.getParameter("day"));
		report.setStart_time(request.getParameter("start_time"));
		report.setEnd_time(request.getParameter("end_time"));
		report.setStudy_content(request.getParameter("study_content"));

		if (file1 != null && file2 != null) {
			int f_photo_fk = fileService.fileUpload(file1);
			int f_study_fk = fileService.fileUpload(file2);

			report.setRep_f_photo_id(f_photo_fk);
			report.setRep_f_study_id(f_study_fk);
		}
		mentorMapper.insert_report(report);

		return "user/report_create";
	}

	@RequestMapping("mypost")
	public String mypost(Model model) {
		model.addAttribute("board", "내가 쓴 글");
		model.addAttribute("postBoards", userService.findAllArticleBydUser());
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

	@RequestMapping(value = "meminfo", method = RequestMethod.GET)
	public String meminfo(Model model) {
		model.addAttribute("board", "회원정보 수정");
		model.addAttribute("user", UserService.getCurrentUser());
		return "user/meminfo";
	}

	@RequestMapping(value = "meminfo_processing", method = RequestMethod.POST)
	public String meminfo_processing(Model model, HttpServletRequest request, HttpServletResponse response) {
		User user = userService.changeMeminfo(request);
		if (user == null)
			return "redirect:meminfo?error";
		if (!request.getParameter("newPw").equals("")) {
			UserService.logout(request, response);
			return "redirect:/mybatisEx/guest/login";
		}

		return "redirect:meminfo";
	}

	@RequestMapping(value = "memDrop", method = RequestMethod.GET)
	public String memDrop(Model model, HttpServletRequest request, HttpServletResponse response) {
		userService.memDrop();
		UserService.logout(request, response);
		return "redirect:/mybatisEx/guest/login";
	}

	@RequestMapping("file/download")
	public void download(@RequestParam("id") int id, HttpServletResponse response) throws Exception {
		fileService.fileDownload(id, response);
	}

	@RequestMapping(value = "getImage")
	public ResponseEntity<byte[]> getImage(@RequestParam("id") int id) {

		String fileName = fileMapper.getImage(id);

		Path path = Paths.get(fileName);

		byte[] image = null;
		try {
			image = Files.readAllBytes(path);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
		}
	}

}