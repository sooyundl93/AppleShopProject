package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Product_inquiry_and_replyDto;

// product_inquiry AND product_inquiry_reply 테이블 조인 Dao

@Mapper
public interface IProduct_inquiry_and_replyDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 페이징을 위한 문의 개수 조회
	public int product_qna_count(@Param("product_code") String product_code,
							   	 @Param("user_id") String user_id);
	
	// 상품 코드로 상품 문의글 & 답변 조회
	public ArrayList<Product_inquiry_and_replyDto> product_qna(@Param("product_code") String product_code,
															   @Param("user_id") String user_id,
															   @Param("firstRecordIndex") int firstRecordIndex,
															   @Param("lastRecordIndex") int lastRecordIndex);
	
}