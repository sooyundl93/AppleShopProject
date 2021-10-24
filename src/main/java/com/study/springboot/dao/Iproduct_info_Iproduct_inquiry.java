package com.study.springboot.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.product_info_product_inquiryDto;


@Mapper
public interface Iproduct_info_Iproduct_inquiry {
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	//상품문의 상세조회
	public product_info_product_inquiryDto content(String order_id);
	
	//상품문의 기간조회
	public List<product_info_product_inquiryDto> listPageDao(String start, String end, String user_id,Date sdd,Date edd);
	
	//상품문의 조회
	public List<product_info_product_inquiryDto>listPageDao2(String start, String end, String user_id);
	
	//상품문의 상세조회
	public product_info_product_inquiryDto content(int inquiry_no);

}