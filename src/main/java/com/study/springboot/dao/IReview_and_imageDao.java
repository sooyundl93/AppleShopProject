package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Review_and_imageDto;

// 리뷰 member_review AND review_image_info 조인 테이블 Dao

@Mapper
public interface IReview_and_imageDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 상품 단건 리뷰 정보 조회
	public ArrayList<Review_and_imageDto> product_review(@Param("product_code") String product_code,
														 @Param("firstRecordIndex") int firstRecordIndex,
														 @Param("lastRecordIndex") int lastRecordIndex);
	
}