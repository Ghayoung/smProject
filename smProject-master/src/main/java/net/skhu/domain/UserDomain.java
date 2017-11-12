package net.skhu.domain;

import org.apache.poi.ss.usermodel.Row;

import net.skhu.dto.User;

public class UserDomain{

    public static User rowOf(Row row) {
    	User user = new User();

    	user.setUser_id(row.getCell(0).getStringCellValue());
    	user.setPw(row.getCell(1).getStringCellValue());
    	user.setName(row.getCell(2).getStringCellValue());
    	user.setEmail(row.getCell(3).getStringCellValue());
    	user.setPhone(row.getCell(4).getStringCellValue());
    	user.setType((int)row.getCell(5).getNumericCellValue());
    	user.setMajor_id((int)row.getCell(6).getNumericCellValue());
    	user.setMinor_id((int)row.getCell(7).getNumericCellValue());
    	user.setDouble_id((int)row.getCell(8).getNumericCellValue());
    	user.setStatus_id((int)row.getCell(9).getNumericCellValue());

    	return user;
    }
}
