package net.skhu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.skhu.dto.Mentor;
import net.skhu.dto.Team;
import net.skhu.dto.User;
import net.skhu.mapper.MentorMapper;
import net.skhu.mapper.TeamMapper;
import net.skhu.mapper.UserMapper;
import net.skhu.service.FileService;
import net.skhu.service.UserService;

@Controller
public class MentorController {
	@Autowired
	MentorMapper mentorMapper;
	@Autowired
	UserService userService;
	@Autowired
	FileService fileService;
	Mentor mentor = new Mentor();

	/* 멘토 신청서 목록 출력 */
	 @RequestMapping("manager/m_contact")
	 public String m_contact(Model model) {
		 List<Mentor> mentors = mentorMapper.findAll();
		 model.addAttribute("mentors", mentors);
		 return "manager/m_contact";
	 }

	 /* 멘토 선정 여부 업데이트 */
	 /* mentor_apply테이블의 condition을 m_condition으로 변경 */
	 /* 멘토 선정됨: m_condition=0 멘토 탈락됨: m_condition=1 */
	 @RequestMapping("manager/mentor_update")
	 public String mentor_update(Model model, @RequestParam(value="id") int id) {
		 Mentor mentor = mentorMapper.findOne(id);
		 if(mentor.getM_condition()==0)
			 mentor.setM_condition(1);
		 else if(mentor.getM_condition()==1)
			 mentor.setM_condition(0);
		 mentorMapper.update_m_condition(mentor);
		 return "redirect:m_contact";
	 }

	 @RequestMapping("manager/m_contact_detail")
	 public String m_contact_detail(Model model, @RequestParam(value="id") int id) {
	     model.addAttribute("mentor", mentorMapper.findOne(id));
	     return "manager/m_contact_detail";
	 }

	 @RequestMapping("manager/mentor_detail_update")
	 public String mentor_detail_update(Model model, @RequestParam(value="id") int id) {
		 Mentor mentor = mentorMapper.findOne(id);
		 if(mentor.getM_condition()==0)
			 mentor.setM_condition(1);
		 else if(mentor.getM_condition()==1)
			 mentor.setM_condition(0);
		 mentorMapper.update_m_condition(mentor);
	     return "redirect:m_contact_detail?id="+mentor.getId();
	 }


	/* 멘토링 신청서 작성 */
	 @RequestMapping(value = "user/mentorapply", method = RequestMethod.GET)
	   public String mentorapply(Model model) {
	      Mentor mentor = new Mentor();
	      model.addAttribute("mentor", mentor);
	      return "user/mentorapply";
	   }

	   @RequestMapping(value = "user/mentorapply", method = RequestMethod.POST)
	   public String mentorapply(Model model, HttpServletRequest request, @RequestBody MultipartFile file1,
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

	      return "user/mentorapply";
	   }

	/* 멘토가 신청서 수정 - 수정필요 */
	/*
	 * @RequestMapping(value="mypost", method=RequestMethod.GET) public String
	 * mypost(Model model, @RequestParam("id") int id) { Mentor mentor =
	 * mentorMapper.findOne(id); model.addAttribute("mentor", mentor); return
	 * "mypost"; }
	 *
	 * @RequestMapping(value="mypost", method=RequestMethod.POST) public String
	 * mypost(Model model, Mentor mentor) { mentorMapper.update(mentor); return
	 * "mypost"; }
	 */

	/* 멘토가 신청서 삭제 - 수정필요 */
	/*
	 * @RequestMapping("delete") public String delete(Model
	 * model, @RequestParam("id") int id) { mentorMapper.delete(id); return
	 * "mypost"; }
	 */
}