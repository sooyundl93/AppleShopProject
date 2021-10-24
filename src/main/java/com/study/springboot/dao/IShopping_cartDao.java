package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Shopping_cartDto;

// 장바구니 목록 shopping_cart 테이블 Dao

@Mapper
public interface IShopping_cartDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 사용자 장바구니 조회
	public ArrayList<Shopping_cartDto> user_cart_list(@Param("user_id") String user_id );
	
	// 사용자 장바구니 전체 삭제
	public int delete_user_cart(@Param("user_id") String user_id);
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	// 장바구니 삭제
	public int cart_delete_action(String product_code,String user_id);
	
	//장바구니 넣기 액션
	public int cart_insert_action(String user_id,String product_code,int number_of_products);
	
	//장바구니 이미 있는지 여부
	public int cart_count(String product_code, String user_id);
	
}