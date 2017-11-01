package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.User;

@Mapper
public interface UserMapper {
	User findOneByUser_id(String user_id);
	User findOneById(int id);
	List<User> findAll();
	void update(User user);
	void delete(int id);
}
