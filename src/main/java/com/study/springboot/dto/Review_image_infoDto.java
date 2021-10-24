package com.study.springboot.dto;

// 리뷰 이미지 정보 review_image_info 테이블 Dto

public class Review_image_infoDto {
	
	private int    image_no;		// 이미지 번호
	private int    post_no;			// 이미지가 보여질 글 번호
	private String image_file_name; // 이미지 파일 명
	
	public Review_image_infoDto() {
		super();
	}

	public Review_image_infoDto(int image_no, int post_no, String image_file_name) {
		super();
		this.image_no = image_no;
		this.post_no = post_no;
		this.image_file_name = image_file_name;
	}

	public int getImage_no() {
		return image_no;
	}

	public void setImage_no(int image_no) {
		this.image_no = image_no;
	}

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public String getImage_file_name() {
		return image_file_name;
	}

	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
	}
		
}
