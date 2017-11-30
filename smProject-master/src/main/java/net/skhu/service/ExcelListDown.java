package net.skhu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.mapper.UserMapper;

@Service
public class ExcelListDown {

	@Autowired
	UserMapper userMapper;

	public String Down(int id) {
		return "report/excel-xls?id="+id;
	}
}
