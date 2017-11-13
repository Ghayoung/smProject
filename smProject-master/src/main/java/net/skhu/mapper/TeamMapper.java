package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Team;

@Mapper
public interface TeamMapper {
	void insertMentor(Team team);
	void insertMentee(Team team);
	void delete(int id);
	Team findTeamByMentor(int id);
	List<Team> findAll();
	List<Team> findMentoringByName(String keyword);
}