package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.Notice_image_infoDto;


// 공지사항 이미지 정보 notice_image_info 테이블 Dao

@Mapper
public interface INotice_image_infoDao {

	//*****************************************************************
	//                                                작업자 : 장수연 *
	//*****************************************************************
	
	// 공지사항 이미지 조회
	public Notice_image_infoDto notice_view_image(int post_no);
	
}