package net.skhu.dto;

public class Team {
	int id;
	int group_m_apply_id;
	int group_mentee_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroup_m_apply_id() {
		return group_m_apply_id;
	}

	public void setGroup_m_apply_id(int group_m_apply_id) {
		this.group_m_apply_id = group_m_apply_id;
	}

	public int getGroup_mentee_id() {
		return group_mentee_id;
	}

	public void setGroup_mentee_id(int group_mentee_id) {
		this.group_mentee_id = group_mentee_id;
	}
}