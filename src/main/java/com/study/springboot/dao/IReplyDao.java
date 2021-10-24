package com.study.springboot.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.study.springboot.dto.ReplyDto;

@Mapper
public interface IReplyDao {
	
	public int countRecords(
			@Param("table_name") String tableName,
			@Param("inquiry_no") int inquiryNo
		) throws Exception;
	
	public ReplyDto relationInstance( 
			@Param("table_name") String tableName,
			@Param("inquiry_no") int inquiryNo
		) throws Exception;

	public int insertRecord(
			@Param("table_name") String tableName,
			@Param("inquiry_no") int inquiryNo,
			@Param("user_id") String userId, 
			@Param("reply_content") String replyContent
		) throws Exception;
	
	public int deleteRecord(
			@Param("table_name") String tableName,
			@Param("reply_no") int replyNo
		) throws Exception;
}
