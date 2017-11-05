package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.skhu.mapper.UserMapper;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Autowired UserMapper userMapper;




}
