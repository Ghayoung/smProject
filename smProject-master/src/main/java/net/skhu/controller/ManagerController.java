package net.skhu.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
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

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import net.skhu.domain.UserDomain;
import net.skhu.dto.FileDTO;
import net.skhu.dto.Introduce;
import net.skhu.dto.Mentor;
import net.skhu.dto.Report;
import net.skhu.dto.Setting;
import net.skhu.dto.Team;
import net.skhu.dto.User;
import net.skhu.mapper.CommentMapper;
import net.skhu.mapper.FileMapper;
import net.skhu.mapper.IntroduceMapper;
import net.skhu.mapper.MentorMapper;
import net.skhu.mapper.TeamMapper;
import net.skhu.mapper.UserMapper;
import net.skhu.model.Pagination;
import net.skhu.service.ExcelReadService;
import net.skhu.service.FileService;
import net.skhu.service.ManagerService;
import net.skhu.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	UserMapper userMapper;
	@Autowired
	FileMapper fileMapper;
	@Autowired
	MentorMapper mentorMapper;
	@Autowired
	IntroduceMapper introduceMapper;
	@Autowired
	TeamMapper teamMapper;
	@Autowired
	ManagerService managerService;
	@Autowired
	FileService fileservice;
	@Autowired
	ExcelReadService excelReadService;
	@Autowired
	UserService userService;
	@Autowired
	CommentMapper commentMapper;

	@RequestMapping(value = "m_introduce_modi", method = RequestMethod.GET)
	public String m_introduce_modi(Model model) {
		List<Introduce> introduces = introduceMapper.findAll();
		model.addAttribute("introduces", introduces);
		model.addAttribute("board", "사업소개 수정");
		return "manager/m_introduce_modi";
	}

	@RequestMapping(value = "m_introduce_modi", method = RequestMethod.POST)
	public String introduce_edit(Model model, @RequestParam(value = "id") int id, HttpServletRequest request) {
		managerService.introduce_edit(id, request);
		return "redirect:m_introduce_modi";
	}

	@RequestMapping("introduce_delete")
	public String introduce_delete(Model model, @RequestParam(value = "id") int id) {
		introduceMapper.delete(id);
		return "redirect:m_introduce_modi";
	}

	/* 신편입생 등록, 작성자-남하영 */
	@RequestMapping("m_register")
	public String m_register() {
		return "manager/m_register";
	}

	@RequestMapping("m_post")
	public String m_post(Model model) {
		model.addAttribute("board", "내가 쓴 글");
		model.addAttribute("postBoards", userService.findAllArticleBydUser());
		model.addAttribute("postReports", userService.findAllReportByUser());
		model.addAttribute("postComments", userService.findAllCommentByUser());
		return "manager/m_post";
	}

	@RequestMapping("comment_delete")
	public String comment_delete(Model model, @RequestParam(value = "cid") int cid,Pagination pagination) {
		commentMapper.delete(cid);
		return "redirect:m_post#fh5co-tab-feature-vertical4com";
	}

	@RequestMapping(value = "m_register", method = RequestMethod.POST)
	public String m_register(@RequestBody MultipartFile file)
			throws IOException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		List<User> users = excelReadService.readExcelToList(file, UserDomain::rowOf);
		for (int i = 0; i < users.size(); i++) {
			userMapper.insertWithExcel(users.get(i));
			System.out.println("유저" + i + " 업로드 완료");
		}
		return "manager/m_register";
	}

	/* 멘토 신청서 목록 출력 , 작성자-남하영 */
	@RequestMapping("m_contact")
	public String m_contact(Model model) {
		List<Mentor> mentors = mentorMapper.findAll();
		model.addAttribute("mentors", mentors);
		return "manager/m_contact";
	}

	/* 멘토 선정 여부 업데이트, 작성자-남하영 */
	/* mentor_apply테이블의 condition을 m_condition으로 변경, team.mentee_id NN 해제 */
	/* 선정된 유저 타입 3으로 변경, 탈락된 유저 타입 1으로 변경, 그룹 생성 */
	@RequestMapping("mentor_update")
	public String mentor_update(Model model, @RequestParam(value = "id") int id) {
		Mentor mentor = mentorMapper.findOne(id);
		User user = userMapper.findOneById(mentor.getMentor_u_id());
		if (mentor.getType() == 1) {
			user.setType(3);
			Team team = new Team();
			team.setGroup_m_apply_id(mentor.getId());
			team.setGroup_mentee_id(mentor.getMentor_u_id());
			teamMapper.insert(team);
		} else if (mentor.getType() == 3) {
			user.setType(1);
			teamMapper.delete(mentor.getId());
		}
		userMapper.type_update(user);
		return "redirect:m_contact";
	}

	@RequestMapping("m_contact_detail")
	public String m_contact_detail(Model model, @RequestParam(value = "id") int id) {
		model.addAttribute("mentor", mentorMapper.findOne(id));
		return "manager/m_contact_detail";
	}

	@RequestMapping("mentor_detail_update")
	public String mentor_detail_update(Model model, @RequestParam(value = "id") int id) {
		Mentor mentor = mentorMapper.findOne(id);
		User user = userMapper.findOneById(mentor.getMentor_u_id());
		if (mentor.getType() == 1) {
			user.setType(3);
			Team team = new Team();
			team.setGroup_m_apply_id(mentor.getId());
			team.setGroup_mentee_id(mentor.getMentor_u_id());
			teamMapper.insert(team);
		} else if (mentor.getType() == 3) {
			user.setType(1);
			teamMapper.delete(mentor.getId());
		}
		userMapper.type_update(user);
		return "redirect:m_contact_detail?id=" + mentor.getId();
	}
	/* 멘토 선정 여부 업데이트 끝 */

	@RequestMapping(value = "m_userManage", method = RequestMethod.GET)
	public String m_userManage(Model model) {
		List<User> managers = userMapper.findAllManager();
		List<User> mentors = userMapper.findAllMentor();
		List<User> mentees = userMapper.findAllMentee();
		List<User> users = userMapper.findAllUser();

		model.addAttribute("managers", managers);
		model.addAttribute("mentors", mentors);
		model.addAttribute("mentees", mentees);
		model.addAttribute("users", users);

		return "manager/m_userManage";
	}

	@RequestMapping(value = "m_userManage", method = RequestMethod.POST)
	// public String m_userManage(Model model,@RequestParam(required=false,
	// name="keyword") String keyword){
	public String m_userManage(Model model, HttpServletRequest request) {

		String keyword = request.getParameter("search");
		List<User> SearchUsers = userMapper.findByName(keyword);
		model.addAttribute("SearchUsers", SearchUsers);
		model.addAttribute("keyword", keyword);

		List<User> managers = userMapper.findAllManager();
		List<User> mentors = userMapper.findAllMentor();
		List<User> mentees = userMapper.findAllMentee();
		List<User> users = userMapper.findAllUser();
		model.addAttribute("managers", managers);
		model.addAttribute("mentors", mentors);
		model.addAttribute("mentees", mentees);
		model.addAttribute("users", users);

		return "manager/m_userManage";

	}

	@RequestMapping("auth_update")
	public String auth_update(@RequestParam("id") int id) {
		int type=userMapper.findType(id);
		userMapper.auth_update(type, id);
		return "redirect:m_userManage";
	}

	@RequestMapping(value = "term_search_user", method = RequestMethod.POST)
	public String term_search_user(Model model, HttpServletRequest request) {

		int year = Integer.parseInt(request.getParameter("search_year"));
		model.addAttribute("year", year);

		List<User> TermSearchManagers = userMapper.findManagerByTerm(year);
		List<User> TermSearchMentors = userMapper.findMentorByTerm(year);
		List<User> TermSearchMentees = userMapper.findMenteeByTerm(year);
		List<User> TermSearchUsers = userMapper.findUserByTerm(year);

		model.addAttribute("TermSearchManagers", TermSearchManagers);
		model.addAttribute("TermSearchMentors", TermSearchMentors);
		model.addAttribute("TermSearchMentees", TermSearchMentees);
		model.addAttribute("TermSearchUsers", TermSearchUsers);


		//
		// List<User> managers= userMapper.findAllManager();
		// List<User> mentors= userMapper.findAllMentor();
		// List<User> mentees= userMapper.findAllMentee();
		// model.addAttribute("managers", managers);
		// model.addAttribute("mentors", mentors);
		// model.addAttribute("mentees", mentees);
		//
		return "manager/m_userManage";
	}

	@RequestMapping(value = "m_mentoringManage", method = RequestMethod.GET)
	public String m_montoringManage(Model model) {

		List<Team> teams = teamMapper.findAll();
		model.addAttribute("teams", teams);
		return "manager/m_mentoringManage";
	}

	@RequestMapping(value = "m_mentoringManage", method = RequestMethod.POST)
	public String m_mentoringManage(Model model, HttpServletRequest request) {

		String keyword = request.getParameter("mentoringSearch");
		System.out.println(keyword);
		List<Team> searchTeams = teamMapper.findMentoringByName(keyword);
		model.addAttribute("SearchTeams", searchTeams);
		model.addAttribute("keyword", keyword);

		List<Team> teams = teamMapper.findAll();
		model.addAttribute("teams", teams);

		return "manager/m_mentoringManage";

	}

	@RequestMapping(value = "m_reportManage", method = RequestMethod.GET)
	public String m_reportManage(Model model) {
		List<Report> teamReports = userMapper.findAllWithReports();
		List<Report> conditionReports = userMapper.findAllCondition();

		int totalReport = userMapper.findStudyCount();
		String startSM = (userMapper.findStartSM()).replaceAll("-", "");

		model.addAttribute("teamReports", teamReports);
		model.addAttribute("conditionReports", conditionReports);
		model.addAttribute("totalReport", totalReport);
		model.addAttribute("startSM", startSM);
		return "manager/m_reportManage";
	}

	@RequestMapping(value = "report_detail", method = RequestMethod.GET)
	public String report_detail(Model model, @RequestParam("id") int id, HttpServletRequest request) {
		String old_url = request.getHeader("referer");
		Report report = userMapper.findOneReport(id);
		model.addAttribute("report", report);
		model.addAttribute("url", old_url);
		return "manager/m_report_detail";
	}

	@RequestMapping("file/download")
	public void download(@RequestParam("id") int id, HttpServletResponse response) throws Exception {
		FileDTO uploadedfile = fileMapper.findOne(id);
		if (uploadedfile == null)
			return;
		String fileName = (uploadedfile.getPath()).substring(11);

		String filePath = (uploadedfile.getPath()).substring(0, 11);

		filePath += fileName;

		Path path = Paths.get(filePath);

		uploadedfile.setData(Files.readAllBytes(path));

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ";");
		try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
			output.write(uploadedfile.getData());
		}
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

	@RequestMapping(value = "m_setting", method = RequestMethod.GET)
	public String m_setting(Model model) {
		Setting setting = new Setting();
		model.addAttribute("setting", setting);
		return "manager/m_setting";
	}

	@RequestMapping(value = "m_setting", method = RequestMethod.POST)
	public String m_setting(Model model, Setting setting) {

		userMapper.m_setting(setting);
		model.addAttribute("setting", setting);
		return "manager/m_setting";

	}

	@RequestMapping(value = "excelDownload", method = RequestMethod.GET)
	public String excelDownload(Model model, @RequestParam("id") int id) {
		Report report = userMapper.findOneReport(id);
		model.addAttribute("report", report);
		return "m_excel2";
	}

	@RequestMapping(value = "excel", method = RequestMethod.POST)
	public String excel(Model model) {
		return "manager/excel3";
	}
}