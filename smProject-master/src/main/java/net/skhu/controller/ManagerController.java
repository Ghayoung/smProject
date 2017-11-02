package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.skhu.dto.Setting;
import net.skhu.mapper.UserMapper;

@Controller
public class ManagerController {
	@Autowired UserMapper userMapper;

	@RequestMapping(value="m_setting", method=RequestMethod.GET)
	public String meminfo() {
		return "m_setting";
	}

	@RequestMapping(value="m_setting", method=RequestMethod.POST)
	public String meminfo(Model model, Setting setting) {
		userMapper.insertSet(setting);
		return "m_setting";
	}
}
