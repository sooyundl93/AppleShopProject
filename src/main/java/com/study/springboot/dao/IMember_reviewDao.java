package com.study.springboot.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Member_reviewDto;

// 회원 상품 리뷰 member_review 테이블 Dao

@Mapper
public interface IMember_reviewDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// product_code에 대한 리뷰 수 조회
	public int countOfReview(@Param("product_code") String product_code);
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	// 전체 조회
	public ArrayList<Member_reviewDto> list(String user_id);
	
	// 작성 리뷰 단건 조회
	public Member_reviewDto content(int post_no);
	
	// 리뷰 작성
	public int product_review_write_action(String post_content,String user_id,String product_code,double num);
	
	// 리뷰 수정
	public int product_review_edit_action(String post_content,int post_no,String user_id, double num);
	
	// 리뷰 삭제
	public int delete(int post_no);
	
	// 날짜 조회
	public ArrayList<Member_reviewDto> date_search(Date sdd,Date edd,String user_id);
	
	// 기간 조회
	public List<Member_reviewDto> listPageDao(String start, String end, String user_id,Date sdd,Date edd);
	
	// 조회
	public List<Member_reviewDto>listPageDao2(String start, String end, String user_id);
	
	// 사용자 작성 리뷰 수 조회
	public int count(String user_id);
	
	// 사용자 작성 리뷰 수 조회
	public int count_date(String user_id,Date sdd,Date edd);

	
}