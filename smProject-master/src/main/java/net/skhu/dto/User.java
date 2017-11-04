package net.skhu.dto;

public class User {
	int id;
	int user_id;
	String pw;
	String newPw;
	String newPw2;
	String name;
	String email;
	String phone;
	String office;
	String office_tel;
	int type;
	int major_id;
	int minor_id;
	int double_id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getOffice_tel() {
		return office_tel;
	}
	public void setOffice_tel(String office_tel) {
		this.office_tel = office_tel;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getNewPw() {
		return newPw;
	}
	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}
	public String getNewPw2() {
		return newPw2;
	}
	public void setNewPw2(String newPw2) {
		this.newPw2 = newPw2;
	}
	public int getMajor_id() {
		return major_id;
	}
	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}
	public int getMinor_id() {
		return minor_id;
	}
	public void setMinor_id(int minor_id) {
		this.minor_id = minor_id;
	}
	public int getDouble_id() {
		return double_id;
	}
	public void setDouble_id(int double_id) {
		this.double_id = double_id;
	}


}
