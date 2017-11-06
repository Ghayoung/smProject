package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import net.skhu.dto.Mentor;
import net.skhu.dto.User;
import net.skhu.mapper.MentorMapper;
import net.skhu.service.FileService;
import net.skhu.service.UserService;

@Controller
@Secured("ROLE_MENTOR")
@RequestMapping("/user")
public class MentorController {
	@Autowired
	MentorMapper mentorMapper;
	@Autowired
	UserService userService;
	@Autowired
	FileService fileService;
	Mentor mentor = new Mentor();

	/* 멘토 신청서 목록 출력 - 수정필요 */
	/*
	 * @RequestMapping("m_mentoringManage") public String
	 * m_mentoringManage(Model model) { List<Mentor> mentors =
	 * mentorMapper.findAll(); model.addAttribute("mentors", mentors); return
	 * "m_mentoringManage"; }
	 */

	/* 멘토링 신청서 작성 */
	/*
	 * mentorapply 테이블의 open_date와 apply_f_* NN 옵션 해제, mentee_num(희망 멘티 인원) 칼럼
	 * 추가해야 작동
	 */
	@RequestMapping(value = "mentorapply", method = RequestMethod.GET)
	public String mentorapply(Model model) {
		Mentor mentor = new Mentor();
		model.addAttribute("mentor", mentor);
		return "user/mentorapply";
	}

	@RequestMapping(value = "mentorapply", method = RequestMethod.POST)
	public String mentorapply(Model model, @RequestBody String subject, @RequestBody String group_name,
			@RequestBody String year, @RequestBody String grade, @RequestBody String count,
			@RequestBody String study_purpose, @RequestBody String study_content, @RequestBody String study_method,
			@RequestBody MultipartFile file1, @RequestBody MultipartFile file2, @RequestBody MultipartFile file3) {

		User user = UserService.getCurrentUser();
		mentor.setMentor_u_id(user.getId());
		int c = Integer.parseInt(count);
		mentor.setCount(c);
		mentor.setGrade(grade);
		mentor.setGroup_name(group_name);
		mentor.setStudy_content(study_content);
		mentor.setStudy_method(study_method);
		mentor.setStudy_purpose(study_purpose);
		int y = Integer.parseInt(year);
		mentor.setYear(y);
		mentor.setSubject(subject);

		mentorMapper.insert_apply(mentor);

		fileService.fileUpload(file1);
		fileService.fileUpload(file2);
		fileService.fileUpload(file3);
		System.out.println(file1);
		System.out.println(file2);
		System.out.println(file3);

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