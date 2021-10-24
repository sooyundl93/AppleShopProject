package com.study.springboot.dao;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageInfoDao {
	public ArrayList<String> getImageFileName(
			@Param("table_name") String tableName,
			@Param("pk_column") String pkColumn,
			@Param("pk_value") String pkValue
		) throws Exception;
}
