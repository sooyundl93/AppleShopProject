package com.study.springboot.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Order_infoDto;

// 주문 정보 order_info 테이블 Dao

@Mapper
public interface IOrder_infoDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 주문정보 INSERT
	public int add_order_info(@Param("order_id") String order_id, 
							  @Param("user_id") String user_id, 
							  @Param("recipient_name") String recipient_name,
							  @Param("phone_number") String phone_number,
							  @Param("postal_code") String postal_code,
							  @Param("address") String address,
							  @Param("detailed_address") String detailed_address,
							  @Param("shipping_memo") String shipping_memo,
							  @Param("total_price") int total_price,
							  @Param("used_mileage") int used_mileage,
							  @Param("discounted_price") int discounted_price,
							  @Param("total_shipping_cost") int total_shipping_cost,
							  @Param("payment_method") String payment_method,
							  @Param("accrued_mileage") int accrued_mileage
			);
	
	// 카카오페이 tid 저장
	public int update_kakao_tid(@Param("tid") String tid,
								@Param("order_id") String order_id);
	
	// 주문 번호로 주문 정보 조회
	public Order_infoDto get_order_info(@Param("order_id") String order_id);
	
	// 주문 상태 업데이트
	public int update_order_status(@Param("order_id") String order_id,
								   @Param("order_status") int order_status);
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	// 사용자 주문 목록 전체 조회
	public ArrayList<Order_infoDto> list(String user_id);
	
	// 주문 목록 단건 조회
	public Order_infoDto content(String order_id);
	
	// 날짜 조회
	public ArrayList<Order_infoDto> date_search(Date sdd,Date edd,String user_id);
	
	// 기간 조회
	public List<Order_infoDto> listPageDao(String start, String end, String user_id,Date sdd,Date edd);
	
	// 조회
	public List<Order_infoDto>listPageDao2(String start, String end, String user_id);
	
	// 사용자 주문 수 조회
	public int count(String user_id);
	
	// 사용자 주문 수 조회
	public int count_date(String user_id,Date sdd,Date edd);
	
	// 사용자 주문 정보 조회
	public Order_infoDto member_info(String uid);
	
	// 주문상태 1 - 입금대기
	public int order_status1(String user_id);
	
	//주문상태 2 - 입금완료
	public int order_status2(String user_id);
	
	//주문상태 3 - 배송완료
	public int order_status3(String user_id);
	
	//주문상태 4 - 취소
	public int order_status4(String user_id);
	
	//주문상태 5 - 교환
	public int order_status5(String user_id);
	
	//주문상태 6 - 반품
	public int order_status6(String user_id);
	
}