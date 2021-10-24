package com.study.springboot.dto;

import java.util.Date;

// 회원 정보 member_info 테이블 Dto

public class Member_infoDto {
	
	private String user_id;				  // ID
	private String user_password;		  // 비밀번호
	private String user_name;			  // 이름
	private String gender;			  	  // 성별
	private Date   birthday;			  // 생년월일
	private String email;				  // 이메일
	private String receive_email_flag;	  // 이메일 수신여부
	private String phone_number;		  // 연락처
	private String receive_messages_flag; // 문자 수신 여부
	private String postal_code;			  // 우편번호
	private String address;				  // 주소
	private String detailed_address;	  // 상세주소
	private String pw_question;			  // 비밀번호 질문
	private String pw_answer;			  // 비밀번호 질문 답
	private Date   member_since;		  // 가입일
	private String user_role;		  	  // 관리자 계정 여부
	private int    mileage;				  // 보유한 마일리지
	private int	   number_of_visits;	  // 일일 방문 횟수
	
	public Member_infoDto() {
		super();
	}

	public Member_infoDto(String user_id, String user_password, String user_name, String gender, Date birthday,
			String email, String receive_email_flag, String phone_number, String receive_messages_flag,
			String postal_code, String address, String detailed_address, String pw_question, String pw_answer,
			Date member_since, String user_role, int mileage, int number_of_visits) {
		super();
		this.user_id = user_id;
		this.user_password = user_password;
		this.user_name = user_name;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.receive_email_flag = receive_email_flag;
		this.phone_number = phone_number;
		this.receive_messages_flag = receive_messages_flag;
		this.postal_code = postal_code;
		this.address = address;
		this.detailed_address = detailed_address;
		this.pw_question = pw_question;
		this.pw_answer = pw_answer;
		this.member_since = member_since;
		this.user_role = user_role;
		this.mileage = mileage;
		this.number_of_visits = number_of_visits;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReceive_email_flag() {
		return receive_email_flag;
	}

	public void setReceive_email_flag(String receive_email_flag) {
		this.receive_email_flag = receive_email_flag;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getReceive_messages_flag() {
		return receive_messages_flag;
	}

	public void setReceive_messages_flag(String receive_messages_flag) {
		this.receive_messages_flag = receive_messages_flag;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailed_address() {
		return detailed_address;
	}

	public void setDetailed_address(String detailed_address) {
		this.detailed_address = detailed_address;
	}

	public String getPw_question() {
		return pw_question;
	}

	public void setPw_question(String pw_question) {
		this.pw_question = pw_question;
	}

	public String getPw_answer() {
		return pw_answer;
	}

	public void setPw_answer(String pw_answer) {
		this.pw_answer = pw_answer;
	}

	public Date getMember_since() {
		return member_since;
	}

	public void setMember_since(Date member_since) {
		this.member_since = member_since;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getNumber_of_visits() {
		return number_of_visits;
	}

	public void setNumber_of_visits(int number_of_visits) {
		this.number_of_visits = number_of_visits;
	}

}
