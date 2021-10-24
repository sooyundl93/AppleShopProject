package com.study.springboot.dto;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplyDto {
	
	private int row_num;
	private int reply_no;
	private int inquiry_no;
	private String user_id;
	private String reply_content;
	private Date reply_date;
	private SimpleDateFormat detailedTimeFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분"); // 연월일 시간 포맷
	
	public int getRow_num() {
		return row_num;
	}
	
	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}
	
	public int getReply_no() {
		return reply_no;
	}
	
	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}
	
	public int getInquiry_no() {
		return inquiry_no;
	}
	
	public void setInquiry_no(int inquiry_no) {
		this.inquiry_no = inquiry_no;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getReply_content() {
		return reply_content;
	}
	
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	
	public String getReply_date() {
		return detailedTimeFormat.format(reply_date);
	}
	
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	
}
