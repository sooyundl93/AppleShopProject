package com.study.springboot.dao;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.study.springboot.dto.OrderInfoDto;

@Mapper
public interface IOrderInfoDao {

	// 레코드 수
	public int countRecords(
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword,
			@Param("from_this_date") String fromThisDate,
			@Param("to_this_date") String toThisDate
		) throws Exception;
	
	// 주문 목록
	public ArrayList<OrderInfoDto> relationInstance( 
			@Param("firstRecordIndex") int firstRecordIndex,
			@Param("lastRecordIndex") int lastRecordIndex,
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword,
			@Param("sortRecords") String sortRecords,
			@Param("from_this_date") String fromThisDate,
			@Param("to_this_date") String toThisDate
		) throws Exception;
	
	// 해당 회원의 총 주문 금액
	public Integer totalOrderPrice(
			@Param("user_id") String user_id
		) throws Exception;
	
	// 해당 회원의 주문 상태
	public int shippingStatus(
			@Param("user_id") String user_id,
			@Param("order_status") int order_status
		) throws Exception;
	
	// 해당 회원의 주문 상태
	public OrderInfoDto detail(
			@Param("order_id") String orderId
		) throws Exception;
}