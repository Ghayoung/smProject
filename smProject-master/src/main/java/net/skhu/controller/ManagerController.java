package net.skhu.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
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
import net.skhu.dto.Report;
import net.skhu.dto.Setting;
import net.skhu.dto.User;
import net.skhu.mapper.FileMapper;
import net.skhu.mapper.IntroduceMapper;
import net.skhu.mapper.UserMapper;
import net.skhu.service.ExcelReadService;
import net.skhu.service.FileService;
import net.skhu.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
   @Autowired
   UserMapper userMapper;
   @Autowired
   FileMapper fileMapper;
   @Autowired
   IntroduceMapper introduceMapper;
   @Autowired
   ManagerService managerService;
   @Autowired
   FileService fileservice;
   @Autowired
   ExcelReadService excelReadService;
   @Autowired
   private ServletContext servletContext;

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

   /* 신편입생 등록 */
   @RequestMapping("m_register")
   public String m_register() {
      return "manager/m_register";
   }

   @RequestMapping(value = "m_register", method = RequestMethod.POST)
   public String m_register(@RequestBody MultipartFile file) throws IOException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
      List<User> users = excelReadService.readExcelToList(file, UserDomain::rowOf);
      for(int i=0; i<users.size(); i++) {
         userMapper.insertWithExcel(users.get(i));
         System.out.println("유저"+i+" 업로드 완료");
      }
      return "manager/m_register";
   }

   /*
    * @RequestMapping("m_contact") public String m_contact() { return
    * "manager/m_contact"; }
    *
    * @RequestMapping("m_contact_detail") public String m_contact_detail() {
    * return "manager/m_contact_detail"; }
    */

   @RequestMapping(value="m_userManage", method=RequestMethod.GET)
   public String m_userManage(Model model) {
   	List<User> managers= userMapper.findAllManager();
   	List<User> mentors= userMapper.findAllMentor();
   	List<User> mentees= userMapper.findAllMentee();
		model.addAttribute("managers", managers);
		model.addAttribute("mentors", mentors);
		model.addAttribute("mentees", mentees);

       return "manager/m_userManage";
	}

   @RequestMapping(value="m_userManage", method=RequestMethod.POST)
   //public String m_userManage(Model model,@RequestParam(required=false, name="keyword") String keyword){
   public String m_userManage(Model model,HttpServletRequest request){

	   String keyword = request.getParameter("search");
	   List<User> SearchUsers= userMapper.findByName(keyword);
	   model.addAttribute("SearchUsers", SearchUsers);
	   model.addAttribute("keyword", keyword);


		List<User> managers= userMapper.findAllManager();
		List<User> mentors= userMapper.findAllMentor();
		List<User> mentees= userMapper.findAllMentee();
		model.addAttribute("managers", managers);
		model.addAttribute("mentors", mentors);
		model.addAttribute("mentees", mentees);
	    return "manager/m_userManage";

   }

   @RequestMapping(value = "m_mentoringManage", method = RequestMethod.GET)
   public String m_mentoringManage(Model model) {

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
   public String report_detail(Model model, @RequestParam("id") int id) {
      Report report = userMapper.findOneReport(id);
      model.addAttribute("report", report);
      return "user/report_detail";
   }

   @RequestMapping("file/download")
   public void download(@RequestParam("id") int id, HttpServletResponse response) throws Exception {
      FileDTO uploadedfile = fileMapper.findOne(id);
      if (uploadedfile == null)
         return;
      String fileName=(uploadedfile.getPath()).substring(11);

      String filePath = (uploadedfile.getPath()).substring(0, 11);

      filePath+=fileName;

      Path path = Paths.get(filePath);

      uploadedfile.setData(Files.readAllBytes(path));

      response.setContentType("application/octet-stream");
      response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8") + ";");
      try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
         output.write(uploadedfile.getData());
      }
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
      return "manager/excel";
   }
}