package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Report;
import net.skhu.dto.Setting;
import net.skhu.dto.User;

@Mapper
public interface UserMapper {
	User findOneByUser_id(String user_id);
	User findOneById(int id);
	List<User> findAll();
	List<User> findAllManager();
	List<User> findAllMentor();
	List<User> findAllMentee();
	List<User> findByName(String name);
	void update(User user);
	void delete(int id);
	void insertWithDep(User user);
	void insertWithMinor(User user);
	void insertWithDouble(User user);
	void m_setting(Setting setting);
	List<Report> findAllReport();
    List<Report> findAllWithReports();
    List<Report> findAllCondition();
    int findStudyCount();
}
