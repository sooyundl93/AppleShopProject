package com.study.springboot.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.One2one_inquiryDto;

// 1:1 문의 글 one2one_inquiry 테이블 Dao

@Mapper
public interface IOne2one_inquiryDao {
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	// 전체 조회
	public ArrayList<One2one_inquiryDto> list(String user_id);
	
	// 1:1 문의글 단건 조회
	public One2one_inquiryDto content(int inquiry_no);
	
	// 1:1 문의 작성
	public int one2one_write_popup_Action(String user_id,String inquiry_title, String inquiry_content);
	
	// 1:1 문의글 삭제
	public int delete(int inquiry_no);
	
	// 날짜 조회
	public ArrayList<One2one_inquiryDto> date_search(Date sdd,Date edd,String user_id);
	
	// 기간 조회
	public List<One2one_inquiryDto> listPageDao(String start, String end, String user_id,Date sdd,Date edd);
	
	// 조회
	public List<One2one_inquiryDto> listPageDao2(String start, String end, String user_id);
	
	// 사용자 1:1 문의글 작성 수 조회
	public int count(String user_id);
	
	// 사용자 1:1 문의글 날짜 작성 수 조회
	public int count_date(String user_id,Date sdd,Date edd);
	
}