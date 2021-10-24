package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.Review_image_infoDto_Member_review_Dto;

// 리뷰 이미지 정보 review_image_info 테이블 Dao

@Mapper
public interface IReview_image_infoDao_IMember_reviewDao {
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	// 전체 조회
	public ArrayList<Review_image_infoDto_Member_review_Dto> list(int post_no);
	
	// 리뷰 상세보기
	public Review_image_infoDto_Member_review_Dto content(int post_no);
	
	//리뷰 쓰기 액션
	public int writeAction(String post_content,String user_id,String product_code,double num);
	
	//리뷰 글번호 받기
	public Review_image_infoDto_Member_review_Dto post_no(String user_id);
	
	// 리뷰 이미지 정보 받기
	public int image_info(int post_no,String image_file_name);
	
	//리뷰 수정 액션
	public int product_review_edit_action(String post_content,int post_no,String user_id,double num);
	
}