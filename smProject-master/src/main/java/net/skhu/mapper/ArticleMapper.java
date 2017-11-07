package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Article;

@Mapper
public interface ArticleMapper {
	List<Article> findAllByBoard(int art_b_id);
	Article findOne(int id);
	void insert(Article article);
	void update(Article article);
    void delete(int id);
}
