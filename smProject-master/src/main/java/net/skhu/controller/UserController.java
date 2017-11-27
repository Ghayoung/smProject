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
	Mentor mentor = new Mentor();

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

	/* 합격 멘토 신청서 목록 출력, 작성자-남하영 */
	@RequestMapping("menteeapply")
	public String menteeapply(Model model) {
		boolean b = false;
		List<Mentor> mentors = mentorMapper.findMentor();
		User user = UserService.getCurrentUser();
		for (int i = 0; i < mentors.size(); i++) {
			mentors.get(i).setState(2); // 신청불가
			if (user.getType() == 1) {
				mentors.get(i).setState(0); // 신청가능
			} else if (user.getType() == 4) {
				List<Team> teams = teamMapper.findTeamByMentor(mentors.get(i).getId());
				for (int j = 0; j < teams.size(); j++) {
					if (user.getId() == teams.get(j).getGroup_mentee_id()) {
						mentors.get(i).setState(1); // 신청취소
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
		mentor.setState(2); // 신청불가
		if (user.getType() == 1) {
			mentor.setState(0); // 신청가능
		} else if (user.getType() == 4) {
			List<Team> teams = teamMapper.findTeamByMentor(mentor.getId());
			for (int j = 0; j < teams.size(); j++) {
				if (user.getId() == teams.get(j).getGroup_mentee_id()) {
					b = true;
					mentor.setState(1); // 신청취소
				}
			}
		}
		if (!b && mentor.getMentee_count() == mentor.getCount()) {
			mentor.setState(2);
		}
		model.addAttribute("mentor", mentor);
		return "user/menteeapply_detail";
	}

	@RequestMapping("mentorapply_detail")
	public String mentorapply_detail(Model model, @RequestParam(value = "id") int id) {
		model.addAttribute("mentor", mentorMapper.findOne(id));
		return "user/mentorapply_detail";
	}

	@RequestMapping("mentorapply_submit")
	public String mentorapply_submit() {
		return "user/mentorapply_submit";
	}

	/* 멘토링 신청서 작성, 작성자-남하영 */
	@RequestMapping(value = "mentorapply", method = RequestMethod.GET)
	public String mentorapply_submit(Model model) {
		User user = UserService.getCurrentUser();
		Mentor mentor = mentorMapper.findByMentor_u_id(user.getId());
		if (mentor == null)
			return "user/mentorapply";
		else
			return "user/mentorapply_submit";
	}

	@RequestMapping(value = "mentorapply", method = RequestMethod.POST)
	public String mentorapply_submit(Model model, HttpServletRequest request, @RequestBody MultipartFile file1,
			@RequestBody MultipartFile file2, @RequestBody MultipartFile file3) {

		User user = UserService.getCurrentUser();
		mentor.setMentor_u_id(user.getId());
		int c = Integer.parseInt(request.getParameter("count"));
		mentor.setCount(c);
		mentor.setGrade(request.getParameter("grade"));
		mentor.setGroup_name(request.getParameter("group_name"));
		mentor.setStudy_content(request.getParameter("study_content"));
		mentor.setStudy_method(request.getParameter("study_method"));
		mentor.setStudy_purpose(request.getParameter("study_purpose"));
		int y = Integer.parseInt(request.getParameter("year"));
		mentor.setYear(y);
		mentor.setSubject(request.getParameter("subject"));

		if (file1 != null && file2 != null && file3 != null) {
			int intro_fk = fileService.fileUpload(file1);
			int t_fk = fileService.fileUpload(file2);
			int doc_fk = fileService.fileUpload(file3);

			mentor.setApply_f_intro_fk(intro_fk);
			mentor.setApply_f_time_id(t_fk);
			mentor.setApply_f_doc_fk(doc_fk);
		}
		mentorMapper.insert_apply(mentor);

		return "user/mentorapply_submit";
	}

	/* 멘토링 신청서 수정, 작성자-남하영 */
	@RequestMapping("mentorapply_edit")
	public String mentorapply_edit(Model model, @RequestParam(value = "id") int id) {
		model.addAttribute("mentor", mentorMapper.findOne(id));
		return "user/mentorapply_edit";
	}

	@RequestMapping(value = "mentorapply_edit", method = RequestMethod.POST)
	public String mentorapply_edit(Model model, HttpServletRequest request, @RequestBody MultipartFile e_file1,
			@RequestBody MultipartFile e_file2, @RequestBody MultipartFile e_file3) {
		User user = UserService.getCurrentUser();
		Mentor myMentor = mentorMapper.findByMentor_u_id(user.getId());
		mentor.setMentor_u_id(user.getId());
		int c = Integer.parseInt(request.getParameter("count"));
		mentor.setCount(c);
		mentor.setGrade(request.getParameter("grade"));
		mentor.setGroup_name(request.getParameter("group_name"));
		mentor.setStudy_content(request.getParameter("study_content"));
		mentor.setStudy_method(request.getParameter("study_method"));
		mentor.setStudy_purpose(request.getParameter("study_purpose"));
		int y = Integer.parseInt(request.getParameter("year"));
		mentor.setYear(y);
		mentor.setSubject(request.getParameter("subject"));
		if(e_file1.getSize() == 0) {
			mentor.setApply_f_intro_fk(myMentor.getApply_f_intro_fk());
		}
		else {
			int intro_fk = fileService.fileUpload(e_file1);
			mentor.setApply_f_intro_fk(intro_fk);
		}
		if(e_file2.getSize() == 0)
			mentor.setApply_f_time_id(myMentor.getApply_f_time_id());
		else {
			int t_fk = fileService.fileUpload(e_file2);
			mentor.setApply_f_time_id(t_fk);
		}
		if(e_file3.getSize() == 0)
			mentor.setApply_f_doc_fk(myMentor.getApply_f_doc_fk());
		else {
			int doc_fk = fileService.fileUpload(e_file3);
			mentor.setApply_f_doc_fk(doc_fk);
		}
		mentorMapper.update(mentor);
		return "user/mentorapply_submit";
	}

	/* 멘토링 신청서 삭제, 작성자-남하영 */
	@RequestMapping("mentorapply_delete")
	public String mentorapply_delete(@RequestParam(value = "id") int id) {
		mentorMapper.delete(id);
		return "user/mypost";
	}

	/* 멘티신청, 작성자-남하영 */
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

	@RequestMapping("mentee_update_mypost")
	public String mentee_update_mypost(Model model, @RequestParam(value = "id") int id) {
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
		return "user/mypost";
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
		model.addAttribute("userType", user.getType());
		return "user/report";
	}

	@RequestMapping("report_detail")
	public String report_detail(Model model, @RequestParam("id") int id, HttpServletRequest request) {
		String old_url = request.getHeader("referer");
		Report report = userMapper.findOneReport(id);
		model.addAttribute("report", report);
		model.addAttribute("url", old_url);
		return "user/report_detail";
	}

	@RequestMapping(value = "report_create", method = RequestMethod.GET)
	public String mentorapply(Model model) {
		Report report = new Report();
		model.addAttribute("report", report);
		return "user/report_create";
	}

	@RequestMapping(value = "report_create", method = RequestMethod.POST)
	public String mentorapply(Model model, HttpServletRequest request, @RequestBody MultipartFile file3,
			@RequestBody MultipartFile file4) {

		User user = UserService.getCurrentUser();
		report.setRep_u_id(user.getId());

		report.setSubject(request.getParameter("subject"));
		report.setPlace(request.getParameter("place"));
		report.setDay(request.getParameter("day"));
		report.setStart_time(request.getParameter("start_time"));
		report.setEnd_time(request.getParameter("end_time"));
		report.setStudy_content(request.getParameter("study_content"));


		if (!file3.isEmpty() && !file4.isEmpty()) {
			int f_photo_fk = fileService.fileUpload(file3);
			int f_study_fk = fileService.fileUpload(file4);

			report.setRep_f_photo_id(f_photo_fk);
			report.setRep_f_study_id(f_study_fk);
		}
		mentorMapper.insert_report(report);

		return "redirect:report#report";
	}

	@RequestMapping("mypost")
	public String mypost(Model model) {
		model.addAttribute("board", "내가 쓴 글");
		model.addAttribute("postBoards", userService.findAllArticleBydUser());
		model.addAttribute("postReports", userService.findAllReportByUser());

		// 하영
		User user = UserService.getCurrentUser();
		Mentor mentor = mentorMapper.findByMentor_u_id(user.getId());
		if (mentor != null)
			mentor.setType(user.getType());
		else if (user.getType() == 4) {
			Team team = teamMapper.findTeamByMember(user.getId());
			mentor = mentorMapper.findOne(team.getGroup_m_apply_id());
			mentor.setType(user.getType());
		}
		model.addAttribute("mentor", mentor);
		model.addAttribute("userType", user.getType());

		return "user/mypost";
	}

	@RequestMapping(value = "modifyMyReport", method = RequestMethod.GET)
	public String modifyMyReport(Model model, @RequestParam("id") int id) {
		Report report = userMapper.findOneReport(id);
		model.addAttribute("report", report);
		return "user/report_create";
	}

	@RequestMapping(value="modifyMyReport", method=RequestMethod.POST)
	public String modifyMyReport(Model model, @RequestParam("id") int id, HttpServletRequest request, @RequestBody MultipartFile file1,
			@RequestBody MultipartFile file2) {

		report.setSubject(request.getParameter("subject"));
		report.setPlace(request.getParameter("place"));
		report.setDay(request.getParameter("day"));
		report.setStart_time(request.getParameter("start_time"));
		report.setEnd_time(request.getParameter("end_time"));
		report.setStudy_content(request.getParameter("study_content"));

		int f_photo_fk;
		int f_study_fk;

		if (!file1.isEmpty()) {
			f_photo_fk = fileService.fileUpload(file1);
			report.setRep_f_photo_id(f_photo_fk);
		}
		if(!file2.isEmpty()){
			f_study_fk = fileService.fileUpload(file2);
			report.setRep_f_study_id(f_study_fk);
		}

		mentorMapper.update_report(id, report);

		return "redirect:mypost";
	}

	@RequestMapping(value = "deleteMyReport", method = RequestMethod.GET)
	public String deleteMyReport(@RequestParam("id") int id) {
		System.out.println(id);
		mentorMapper.deleteMyReport(id);
		return "redirect:mypost";
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