package com.study.springboot.dao;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.study.springboot.dto.MemberReviewDto;

@Mapper
public interface IMemberReviewDao {
	
	// 레코드 수
	public int countRecords(
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword,
			@Param("from_this_date") String fromThisDate,
			@Param("to_this_date") String toThisDate
		) throws Exception;
	
	// 리뷰 목록
	public ArrayList<MemberReviewDto> relationInstance( 
			@Param("firstRecordIndex") int firstRecordIndex,
			@Param("lastRecordIndex") int lastRecordIndex,
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword,
			@Param("sortRecords") String sortRecords,
			@Param("from_this_date") String fromThisDate,
			@Param("to_this_date") String toThisDate
		) throws Exception;
	
	// 해당 회원의 총 주문 금액
	public int countOfMemberReviews(
			@Param("userId") String userId
		) throws Exception;
	
	// 해당 회원의 평균 리뷰 점수
	public Float averageOfReviewScores(
			@Param("userId") String userId
		) throws Exception;
	
	// 리뷰 내용
	public MemberReviewDto getContent(
			@Param("table_name") String tableName,
			@Param("post_no") int postNo
		) throws Exception;
}
