package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Team;


@Mapper
public interface TeamMapper {

	List<Team> findAll();
//	User findOneByUser_id(String user_id);
//	User findOneById(int id);
//	List<User> findAllManager();
//	List<User> findAllMentor();
//	List<User> findAllMentee();
//	List<User> findByName(String keyword);
//	void update(User user);
//	void delete(int id);
//	void insertWithDep(User user);
//	void insertWithMinor(User user);
//	void insertWithDouble(User user);
//	void insertWithExcel(User user);
//	void m_setting(Setting setting);
//    List<Report> findAllWithReports();
//    List<Report> findAllCondition();
//    int findStudyCount();
//    String findStartSM();
//    Report findOneReport(int id);
//    void auth_update(int id);
	
}
