package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Team;

@Mapper
public interface TeamMapper {
	void insert(Team team);
	void delete(int id);
	void deleteMentee(int id);
	Team findTeamByMentor(int id);
	List<Team> findAll();
	List<Team> findMentoringByName(String keyword);
}