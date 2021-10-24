package com.study.springboot.dao;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.study.springboot.dto.InquiryDto;

@Mapper
public interface IInquiryDao {

//	해당 유저의 문의글 수 카운트
	public int countOfrecords(
			@Param("table") String table,
			@Param("user_id") String userId
		) throws Exception;
	
//	답글이 달리지 않은 문의글
	public int countOfUnrepliedInquiries(
			@Param("inquiry_table") String table,
			@Param("user_id") String userId
		) throws Exception;
	
//	문의글 수 카운트
	public int countRecords(
			@Param("table_name") String tableName,
			@Param("all_search_options") String[] allSearchOptions,
			@Param("current_option") String current_option, 
			@Param("current_keyword") String current_keyword,
			@Param("from_this_date") String fromThisDate,
			@Param("to_this_date") String toThisDate
		) throws Exception;
	
//	목록 생성
	public ArrayList<InquiryDto> relationInstance( 
			@Param("table_name") String tableName,
			@Param("column_array") String[] columnArray,
			@Param("all_search_options") String[] allSearchOptions,
			@Param("firstRecordIndex") int firstRecordIndex,
			@Param("lastRecordIndex") int lastRecordIndex,
			@Param("current_option") String current_option, 
			@Param("current_keyword") String current_keyword,
			@Param("sortRecords") String sortRecords,
			@Param("from_this_date") String fromThisDate,
			@Param("to_this_date") String toThisDate
		) throws Exception;
	
//	답글 수 업데이트
	public int UpdateNumberOfReplies(
			@Param("table_name") String tableName,
			@Param("index") int index,
			@Param("number_of_records") int numberOfRecords
		) throws Exception; 
}
