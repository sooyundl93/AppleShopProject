package com.study.springboot.dao;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.study.springboot.dto.OrderProductInfoDto;

@Mapper
public interface IOrderProductInfoDao {
	
	// 목록에 표시할 레코드의 수
	public int countOfProductsOrdered(
			@Param("user_id") String user_id
		) throws Exception;
	
	// 주문 상품 목록
	public ArrayList<OrderProductInfoDto> listOfOrderedProducts(
			@Param("order_id") String orderId
		) throws Exception;
}
