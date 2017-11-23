package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Comment;

@Mapper
public interface CommentMapper {
	List<Comment> findAllByArticle(int com_a_id);
	List<Comment> findAllByUser(int com_u_id);
	int countByArticle(int com_a_id);
	void insert(Comment comment);
	void update(Comment comment);
	void delete(int id);
}
