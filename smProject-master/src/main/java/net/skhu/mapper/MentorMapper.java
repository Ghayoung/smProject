package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Mentor;

@Mapper
public interface MentorMapper {
	Mentor findOne(int id);
	Mentor findByMentor_u_id(int mentor_u_id);
	List<Mentor> findAll();
	void insert(Mentor mentor);
	void update(Mentor mentor);
	void delete(int id);
}