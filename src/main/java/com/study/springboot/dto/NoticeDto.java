package com.study.springboot.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

// 공지사항 notice 테이블 Dto

public class NoticeDto {
	
	private int    post_no;		   // 글 식별번호
	private String user_id;		   // 작성자(관리자) ID
	private String post_title;	   // 제목
	private String post_content;   // 내용
	private Date   post_date;	   // 작성날짜
	private int    post_hits;	   // 조회수
	private String important_flag; // 중요 공지 플래그
	private SimpleDateFormat detailedTimeFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분"); // 연월일 시간 포맷
	
	public NoticeDto() {
		super();
	}

	public NoticeDto(int post_no, String user_id, String post_title, String post_content, Date post_date, int post_hits,
			String important_flag) {
		super();
		this.post_no = post_no;
		this.user_id = user_id;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_date = post_date;
		this.post_hits = post_hits;
		this.important_flag = important_flag;
	}

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public String getPost_date() {
		return detailedTimeFormat.format(post_date);
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

	public int getPost_hits() {
		return post_hits;
	}

	public void setPost_hits(int post_hits) {
		this.post_hits = post_hits;
	}

	public String getImportant_flag() {
		return important_flag;
	}

	public void setImportant_flag(String important_flag) {
		this.important_flag = important_flag;
	}
			
}
