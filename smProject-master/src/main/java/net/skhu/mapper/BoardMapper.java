package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Board;

@Mapper
public interface BoardMapper {
	List<Board> findAll();
	List<Board> findAllNoManager();
	Board findOne(int id);
}
