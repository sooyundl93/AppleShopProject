package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.Product_inquiry_replyDto;

// 상품 문의 답변 글 product_inquiry_reply 테이블 Dao

@Mapper
public interface IProduct_inquiry_replyDao {
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	//상품문의 답변 상세보기
	public Product_inquiry_replyDto content(int inquiry_no);
	
}