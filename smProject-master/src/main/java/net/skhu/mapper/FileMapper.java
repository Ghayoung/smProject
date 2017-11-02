package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.File;

@Mapper
public interface FileMapper {
	File findOne(int id);
	List<File> findAll();
	void insert(File file);
	void update(File file);
	void delete(int id);
}