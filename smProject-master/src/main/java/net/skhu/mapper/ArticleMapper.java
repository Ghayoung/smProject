package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Article;
import net.skhu.model.Pagination;

@Mapper
public interface ArticleMapper {
	List<Article> findAllByBoard(Pagination pagination);
	List<Article> findByUser(int art_u_id);
	int count(Pagination pagination);
	Article findOne(int id);
	void insert(Article article);
	void insertNoFile(Article article);
	void update(Article article);
    void delete(int id);


}
