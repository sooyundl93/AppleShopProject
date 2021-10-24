package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Order_id_seqDto;

// 주문번호 시퀀스 Dao

@Mapper
public interface IOrder_id_seqDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 주문번호 시퀀스 조회
	public Order_id_seqDto get_order_seq(@Param("today") String today);
}