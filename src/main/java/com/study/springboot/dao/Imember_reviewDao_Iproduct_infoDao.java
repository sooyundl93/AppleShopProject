package com.study.springboot.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.member_review_product_infoDto;

@Mapper
public interface Imember_reviewDao_Iproduct_infoDao {
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	//리뷰 상세보기
	public member_review_product_infoDto content(String order_id);
	
	//리뷰 기간조회
	public List<member_review_product_infoDto> listPageDao(String start, String end, String user_id,Date sdd,Date edd);
	
	//리뷰 조회
	public List<member_review_product_infoDto>listPageDao2(String start, String end, String user_id);

}