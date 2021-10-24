package com.study.springboot.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.Order_infoDto;
import com.study.springboot.dto.Order_infoDto_Order_product_infoDto;


@Mapper
public interface IOrder_infoDao_IOrder_product_infoDao {
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	public List<Order_infoDto_Order_product_infoDto> content(String order_id);
	public List<Order_infoDto> listPageDao(String start, String end, String user_id,Date sdd,Date edd);
	public List<Order_infoDto>listPageDao2(String start, String end, String user_id);

}