package com.study.springboot.dao;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.study.springboot.dto.ProductInfoDto;

@Mapper
public interface IProductInfoDao {
	
	// 상품 목록
	public ArrayList<ProductInfoDto> relationInstance( 
			@Param("firstRecordIndex") int firstRecordIndex,
			@Param("lastRecordIndex") int lastRecordIndex,
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword,
			@Param("sortRecords") String sortRecords,
			@Param("parentCategory") String parentCategory,
			@Param("childCategory") String childCategory
		) throws Exception;
	
	// 레코드 수
	public int countRecords(
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword,
			@Param("parentCategory") String parentCategory,
			@Param("childCategory") String childCategory
		) throws Exception;
	
	// 상품이름 가져오기
	public String getProductName(
			@Param("product_code") String productCode
		) throws Exception;
	
	// 상품 상세정보 가져오기
	public ProductInfoDto productInfoDetail( 
			@Param("product_code") String productCode
		) throws Exception;
	
	// 상품 정보 수정
	public int productInfoUpdate( 
			@Param("product_code") String productCode,
			@Param("parent_category") String parentCategory,
			@Param("child_category") String childCategory,
			@Param("manufacturer") String manufacturer,
			@Param("product_name") String productName,
			@Param("product_price") int productPrice,
			@Param("shipping_cost") int shippingCost,
			@Param("number_of_stock") int numberOfStock,
			@Param("product_description") String productDescription
		) throws Exception;
	
	// 상품 정보 추가
	public int productInfoAdd( 
			@Param("parent_category") String parentCategory,
			@Param("child_category") String childCategory,
			@Param("manufacturer") String manufacturer,
			@Param("product_name") String productName,
			@Param("product_price") int productPrice,
			@Param("shipping_cost") int shippingCost,
			@Param("number_of_stock") int numberOfStock,
			@Param("product_description") String productDescription
		) throws Exception;
}