package com.study.springboot.dto;

// 상품 이미지 정보 product_image_info 테이블 Dto

public class Product_image_infoDto {
	
	private int    image_no;		// 이미지 식별번호
	private String product_code;	// 이미지가 보여질 상품번호
	private String image_file_name; // 이미지 파일 명
	
	public Product_image_infoDto() {
		super();
	}

	public Product_image_infoDto(int image_no, String product_code, String image_file_name) {
		super();
		this.image_no = image_no;
		this.product_code = product_code;
		this.image_file_name = image_file_name;
	}

	public int getImage_no() {
		return image_no;
	}

	public void setImage_no(int image_no) {
		this.image_no = image_no;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getImage_file_name() {
		return image_file_name;
	}

	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
	}

}