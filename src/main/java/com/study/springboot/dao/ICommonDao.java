package com.study.springboot.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ICommonDao {

	// 레코드 삭제
	public int deleteRecords(
			@Param("table_name") String tableName,
			@Param("primary_key") String primaryKey,
			@Param("record_id") String recordId
		) throws Exception;
	
	// 레코드 업데이트
	public int updateRecord(
			@Param("table_name") String nameOfTable,
			@Param("column") String column,
			@Param("value") String value,
			@Param("primary_key_column") String primaryKeyColumn,
			@Param("primary_key") String primaryKey
		) throws Exception;
	
	// 레코드 카운트
	public int countRecords(
			@Param("table_name") String nameOfTable,
			@Param("column") String column,
			@Param("value") String value
		) throws Exception;
}
