package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Comment;
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
	List<User> findAllUser();
	List<User> findByName(String keyword);
	void update(User user);
	void type_update(User user);
	void delete(int id);
	void insertWithDep(User user);
	void insertWithMinor(User user);
	void insertWithDouble(User user);
	void insertWithExcel(User user);
	void m_setting(Setting setting);
    List<Report> findAllWithReports();
    List<Report> findAllCondition();
    List<Report> findAllReportsById(int id);
    List<Report> findAllReportsByWriter(int id);
    List<Comment> findAllCommentsByWriter(int id);
    Report findAllConditionById(int id);
    int findStudyCount();
    int findType(int id);
    String findStartSM();
    Report findOneReport(int id);
    void auth_update(int type, int id);
    List<User> findManagerByTerm(int year);
    List<User> findMentorByTerm(int year);
    List<User> findMenteeByTerm(int year);
    List<User> findUserByTerm(int year);
    void dropUser(int id);
}
