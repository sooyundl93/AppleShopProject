package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.One2one_inquiry_replyDto;

// 1:1 문의 답변 글 one2one_inquiry_reply 테이블 Dao

@Mapper
public interface IOne2one_inquiry_replyDao {

	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	//1:1문의 답변 상세보기
	public One2one_inquiry_replyDto content(int inquiry_no);
	
}