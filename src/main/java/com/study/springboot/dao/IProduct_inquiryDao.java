package com.study.springboot.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Product_inquiryDto;

// 상품 문의 글 product_inquiry 테이블 Dao

@Mapper
public interface IProduct_inquiryDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 상품에 대한 상품문의 개수 조회
	public int countOfInquiry(@Param("product_code") String product_code);
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	// 상품문의 상세보기
	public Product_inquiryDto content(int inquiry_no);
	
	// 상품문의 글쓰기 액션
	public int product_inquiry_write_action(String product_code,String user_id,String inquiry_content, String private_flags);
	
	// 상품문의 수정 액션
	public int product_inquiry_edit_action(String inquiry_content, String private_flags,int inquiry_no,String user_id);
	
	// 상품문의 삭제
	public int delete(int inquiry_no);
	
	// 상품문의 날짜조회
	public ArrayList<Product_inquiryDto> date_search(Date sdd,Date edd,String user_id);
	
	// 상품문의 기간조회
	public List<Product_inquiryDto> listPageDao(String start, String end, String user_id,Date sdd,Date edd);
	
	// 상품문의 조회
	public List<Product_inquiryDto>listPageDao2(String start, String end, String user_id);
	
	// 상품문의 갯수세기
	public int count(String user_id);
	
	// 상품문의 날짜 갯수세기
	public int count_date(String user_id,Date sdd,Date edd);
	
}