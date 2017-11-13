package net.skhu.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import net.skhu.dto.Mentor;
import net.skhu.dto.Report;
import net.skhu.dto.Team;
import net.skhu.dto.User;
import net.skhu.mapper.ArticleMapper;
import net.skhu.mapper.BoardMapper;
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
		model.addAttribute("board", boardMapper.findOne(pagination.getBd()).getB_name());
		model.addAttribute("article", articleMapper.findOne(id));
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
	public String board_create(Model model, Article article, Pagination pagination, @RequestBody MultipartFile file) {
		int id = pagination.getBd();
		userService.createArticle(article, id, file);
		return "redirect:board?id=" + id + "&" + pagination.getQueryString();
	}

	@RequestMapping(value = "board_edit", method = RequestMethod.GET)
	public String board_edit(Model model, @RequestParam(value = "id") int id, Pagination pagination) {
		model.addAttribute("article", articleMapper.findOne(id));
		model.addAttribute("board", boardMapper.findOne(pagination.getBd()).getB_name());
		return "user/board_create";
	}

	@RequestMapping(value = "board_edit", method = RequestMethod.POST)
	public String board_edit(Model model, Article article, Pagination pagination, @RequestParam(value = "id") int id,
			@RequestBody MultipartFile file) {
		userService.editArticle(article, file);
		return "redirect:board_detail?id=" + id + "&" + pagination.getQueryString();
	}

	@RequestMapping("board_delete")
	public String board_delete(Model model, @RequestParam(value = "id") int id,
			@RequestParam(value = "type", defaultValue = "0") int type) {
		articleMapper.delete(id);
		return "redirect:board?type=" + type;
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
		 boolean b=false;
		 List<Mentor> mentors = mentorMapper.findMentor();
		 User user = UserService.getCurrentUser();
		 for(int i=0; i<mentors.size(); i++) {
			 if(user.getType()!=4) {
				 mentors.get(i).setState(0);
			 } else if(user.getType()==4) {
				 List<Team> teams = teamMapper.findTeamByMentor(mentors.get(i).getId());
				 for(int j=0; j<teams.size(); j++) {
					 if(user.getId()==teams.get(j).getGroup_mentee_id())
						 b=true;
				 }
				 if(b) mentors.get(i).setState(1);
				 else mentors.get(i).setState(2);
			 }
			 if(!b&&mentors.get(i).getMentee_count()==mentors.get(i).getCount())
				 mentors.get(i).setState(2);
		 }
		 model.addAttribute("mentors", mentors);
		 return "user/menteeapply";
	 }

	 @RequestMapping("menteeapply_detail")
	 public String menteeapply_detail(Model model, @RequestParam(value="id") int id) {
	     boolean b=false;
	     Mentor mentor = mentorMapper.findOne(id);
		 User user = UserService.getCurrentUser();
		 if(user.getType()!=4) {
			 mentor.setState(0);
		 } else if(user.getType()==4) {
			 List<Team> teams = teamMapper.findTeamByMentor(mentor.getId());
			 for(int j=0; j<teams.size(); j++) {
				 if(user.getId()==teams.get(j).getGroup_mentee_id())
					 b=true;
			 }
			 if(b) mentor.setState(1);
			 else mentor.setState(2);
		 }
		 if(!b&&mentor.getMentee_count()==mentor.getCount()) {
			 mentor.setState(2);
		 }
		 model.addAttribute("mentor", mentor);
	     return "user/menteeapply_detail";
	 }

	 /* 멘티신청 */
	 @RequestMapping("mentee_update")
	 public String mentee_update(Model model, @RequestParam(value="id") int id) {
		 Team team = new Team();
		 User user = UserService.getCurrentUser();
		 if(user.getType()!=4) {
			 user.setType(4);
			 team.setGroup_m_apply_id(id);
			 team.setGroup_mentee_id(user.getId());
			 teamMapper.insert(team);
		 } else if(user.getType()==4) {
			 user.setType(1);
			 teamMapper.deleteMentee(user.getId());
		 }
		 userMapper.type_update(user);
		 return "redirect:menteeapply";
	 }

	 @RequestMapping("mentee_update_detail")
	 public String mentee_update_detail(Model model, @RequestParam(value="id") int id) {
		 Team team = new Team();
		 User user = UserService.getCurrentUser();
		 if(user.getType()!=4) {
			 user.setType(4);
			 team.setGroup_m_apply_id(id);
			 team.setGroup_mentee_id(user.getId());
			 teamMapper.insert(team);
		 } else if(user.getType()==4) {
			 user.setType(1);
			 teamMapper.deleteMentee(user.getId());
		 }
		 userMapper.type_update(user);
		 return "redirect:menteeapply_detail?id="+id;
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

	@RequestMapping(value = "meminfo", method = RequestMethod.GET)
	public String meminfo(Model model) {
		model.addAttribute("board", "회원정보 수정");
		model.addAttribute("user", UserService.getCurrentUser());
		return "user/meminfo";
	}

	@RequestMapping(value = "meminfo_processing", method = RequestMethod.POST)
	public String meminfo_processing(Model model, HttpServletRequest request) {
		User user = userService.changeMeminfo(request);
		if (user == null)
			return "redirect:meminfo?error";
		return "redirect:meminfo";
	}

	@RequestMapping(value = "getImage")
	public ResponseEntity<byte[]> getImage(@RequestParam("id") int id) {

		String fileName=fileMapper.getImage(id);

		Path path = Paths.get(fileName);

		byte[] image=null;
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
