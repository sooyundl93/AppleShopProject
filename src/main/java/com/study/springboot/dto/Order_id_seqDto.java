package com.study.springboot.dto;

// 주문 번호 시퀀스 Dto

//*****************************************************************
//                                                작업자 : 김남권 *
//*****************************************************************

public class Order_id_seqDto {
	
	private String order_id_seq;
	
	public Order_id_seqDto() {
		super();
	}

	public Order_id_seqDto(String order_id_seq) {
		super();
		this.order_id_seq = order_id_seq;
	}

	public String getOrder_id_seq() {
		return order_id_seq;
	}

	public void setOrder_id_seq(String order_id_seq) {
		this.order_id_seq = order_id_seq;
	}
		
}
