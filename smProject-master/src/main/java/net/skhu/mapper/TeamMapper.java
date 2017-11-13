package net.skhu.mapper;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Team;

@Mapper
public interface TeamMapper {
	void insertMentor(Team team);
	void insertMentee(Team team);
	void delete(int id);
}