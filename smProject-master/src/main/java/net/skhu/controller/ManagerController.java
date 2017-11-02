package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.skhu.dto.Setting;
import net.skhu.mapper.UserMapper;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Autowired UserMapper userMapper;

	@RequestMapping(value="m_setting", method=RequestMethod.GET)
	public String meminfo() {
		return "manager/m_setting";
	}

	@RequestMapping(value="m_setting", method=RequestMethod.POST)
	public String meminfo(Model model, Setting setting) {
		userMapper.insertSet(setting);
		return "manager/m_setting";
	}

	@RequestMapping("m_contact_detail")
	public String m_contact_detail() {
		return "manager/m_contact_detail";
	}

	@RequestMapping("m_contact")
	public String m_contact() {
		return "manager/m_contact";
	}

	@RequestMapping("m_introduce_modi")
	public String m_introduce_modi() {
		return "manager/m_introduce_modi";
	}

	@RequestMapping("m_mentoringManage")
	public String m_mentoringManage() {
		return "manager/m_mentoringManage";
	}

	@RequestMapping("m_register")
	public String m_register() {
		return "manager/m_register";
	}

	@RequestMapping("m_reportManage")
	public String m_reportManage() {
		return "manager/m_reportManage";
	}

	@RequestMapping("m_userMAnage")
	public String m_userMAnage() {
		return "manager/m_userMAnage";
	}


}
