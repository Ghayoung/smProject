package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.skhu.mapper.UserMapper;

@Controller
@Secured("ROLE_MANAGER")
@RequestMapping("/manager")
public class ManagerController {
	@Autowired UserMapper userMapper;

	@RequestMapping("m_introduce_modi")
    public String m_introduce_modi() {
        return "manager/m_introduce_modi";
    }

    @RequestMapping("m_register")
    public String m_register() {
        return "manager/m_register";
    }

    @RequestMapping("m_contact")
    public String m_contact() {
        return "manager/m_contact";
    }

    @RequestMapping("m_contact_detail")
    public String m_contact_detail() {
        return "manager/m_contact_detail";
    }

    @RequestMapping("m_userManage")
    public String m_userManage() {
        return "manager/m_userManage";
    }

    @RequestMapping("m_mentoringManage")
    public String m_mentoringManage() {
        return "manager/m_mentoringManage";
    }

    @RequestMapping("m_reportManage")
    public String m_reportManage() {
        return "manager/m_reportManage";
    }

    /*
    @RequestMapping(value="m_setting", method=RequestMethod.GET)
	public String m_setting(Model model) {
		Setting setting = new Setting();
		model.addAttribute("setting", setting);
		return "guest/m_setting";
	}

	@RequestMapping(value="m_setting", method=RequestMethod.POST)
	public String m_setting(Model model, Setting setting) {
		System.out.println(setting.getMax_mentee());
		System.out.println(setting.getMax_mentor());
		System.out.println(setting.getMin_mentee());
		System.out.println(setting.getStudy_count());
		System.out.println(setting.getReport_deadline());
		System.out.println(setting.getMentor_start_date());
		System.out.println(setting.getMentor_expire_date());
		System.out.println(setting.getMentee_start_date());
		System.out.println(setting.getMentee_expire_date());

		userMapper.m_setting(setting);
		model.addAttribute("setting", setting);
		return "guest/m_setting";

	}
	*/


}
