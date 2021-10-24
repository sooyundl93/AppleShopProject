package com.study.springboot.dto;

// 상품 정보 product_info 테이블 Dto

//*****************************************************************
//                                                작업자 : 김남권 *
//*****************************************************************

public class Product_and_imageDto {
	
	private String product_code;		 // 상품번호
	private String product_name;		 // 상품이름
	private String manufacturer; 		 // 제조사
	private int    product_price;		 // 상품가격
	private String image_file_name;		 // 이미지 이름
	
	public Product_and_imageDto() {
		super();
	}

	public Product_and_imageDto(String product_code, String product_name, String manufacturer, int product_price,
			String image_file_name) {
		super();
		this.product_code = product_code;
		this.product_name = product_name;
		this.manufacturer = manufacturer;
		this.product_price = product_price;
		this.image_file_name = image_file_name;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public String getImage_file_name() {
		return image_file_name;
	}

	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
	}

}
