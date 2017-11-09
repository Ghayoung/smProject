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
import net.skhu.dto.User;
import net.skhu.mapper.MentorMapper;
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

	/* 멘토 신청서 목록 출력 - 수정필요 */
	 @RequestMapping(value = "manager/m_contact", method = RequestMethod.GET)
	 public String m_contact(Model model) {
		 List<Mentor> mentors = mentorMapper.findAll();
		 model.addAttribute("mentors", mentors);
		 return "manager/m_contact";
	 }

	 @RequestMapping("manager/m_contact_detail")
	 public String m_contact_detail(Model model, @RequestParam(value="id") int id) {
	    model.addAttribute("mentor", mentorMapper.findOne(id));
	       return "manager/m_contact_detail";
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
	         int t_fk = fileService.fileUpload(file1);
	         int intro_fk = fileService.fileUpload(file2);
	         int doc_fk = fileService.fileUpload(file3);

	         System.out.println("t_fk:"+t_fk);
	         System.out.println("intro_fk:"+intro_fk);
	         System.out.println("doc_fk:"+doc_fk);

	         mentor.setApply_f_time_id(t_fk);
	         mentor.setApply_f_intro_fk(intro_fk);
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