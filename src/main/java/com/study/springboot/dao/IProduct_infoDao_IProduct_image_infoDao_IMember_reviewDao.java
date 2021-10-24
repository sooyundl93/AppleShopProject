package com.study.springboot.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Product_infoDto_Product_image_infoDto_Member_reviewDto;

// 상품 정보 product_info 테이블 Dao

@Mapper
public interface IProduct_infoDao_IProduct_image_infoDao_IMember_reviewDao {
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	// 상품 검색
	public ArrayList<Product_infoDto_Product_image_infoDto_Member_reviewDto> allSearch(String start, String end,@Param("searchWord")String searchWord);
	
	// 검색 갯수세기
	public int count_search(@Param("searchWord") String searchWord); //검색 갯수세기
	
	//***************************
	// 				  상품 전체 *
	//***************************
	
	// 전체 조회
	public ArrayList<Product_infoDto_Product_image_infoDto_Member_reviewDto> list();
	
	// 전체상품조회
	public List<Product_infoDto_Product_image_infoDto_Member_reviewDto>listPageDao2(String start, String end);
	
	// 별점순
	public List<Product_infoDto_Product_image_infoDto_Member_reviewDto>listPageDao2_star(String start, String end);
	
	// 가격낮은순
	public List<Product_infoDto_Product_image_infoDto_Member_reviewDto>listPageDao2_low(String start, String end);
	
	// 가격높은순
	public List<Product_infoDto_Product_image_infoDto_Member_reviewDto>listPageDao2_high(String start, String end);
	
	// 갯수세기
	public int count();

	//***************************
	// 				   카테고리 *
	//***************************
	
	//카테고리 조회
	public  List<Product_infoDto_Product_image_infoDto_Member_reviewDto> category(String start, String end,String parent_category,String child_category);
	
	//카테고리 별점순 조회
	public  List<Product_infoDto_Product_image_infoDto_Member_reviewDto> category_star(String start, String end,String parent_category,String child_category);
	
	//가격낮은순
	public  List<Product_infoDto_Product_image_infoDto_Member_reviewDto> category_low(String start, String end,String parent_category,String child_category);
	
	//가격높은순
	public  List<Product_infoDto_Product_image_infoDto_Member_reviewDto> category_high(String start, String end,String parent_category,String child_category);
	
	//갯수세기
	public int category_count(String parent_category,String child_category);
	
	//***************************
	// 				   인테리어 *
	//***************************
	
	//인테리어 조회
	public  List<Product_infoDto_Product_image_infoDto_Member_reviewDto> interior (String start, String end);
	
	//별점순
	public  List<Product_infoDto_Product_image_infoDto_Member_reviewDto> interior_star (String start, String end);
	
	//가격 낮은순
	public  List<Product_infoDto_Product_image_infoDto_Member_reviewDto> interior_low (String start, String end);
	
	//가격높은순
	public  List<Product_infoDto_Product_image_infoDto_Member_reviewDto> interior_high (String start, String end);
	
	//갯수세기
	public int interior_count();
	
}