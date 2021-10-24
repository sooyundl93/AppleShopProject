package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.Review_image_infoDto;

// 리뷰 이미지 정보 review_image_info 테이블 Dao

@Mapper
public interface IReview_image_infoDao {
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	public ArrayList<Review_image_infoDto> list(int post_no); // 전체 조회
	public Review_image_infoDto content(int post_no);
	public int imageUpload(int post_no);
	public int imageUpdate(int image_no,int post_no);
	public int imageDelete(int image_no);
	
}