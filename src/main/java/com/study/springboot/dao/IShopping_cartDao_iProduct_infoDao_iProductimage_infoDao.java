package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.Shopping_cartDto_product_info_Dto_product_image_infoDto;

// 장바구니 목록 shopping_cart 테이블 Dao

@Mapper
public interface IShopping_cartDao_iProduct_infoDao_iProductimage_infoDao {
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	// 전체 조회
	public ArrayList<Shopping_cartDto_product_info_Dto_product_image_infoDto> list(String uid);
	
	//장바구니 비우기
	public int cart_delete_action(String product_code,String user_id);
	
}