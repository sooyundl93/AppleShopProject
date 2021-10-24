package com.study.springboot.dao;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Member_infoDto;

// 회원 정보 member_info 테이블 Dao

@Mapper
public interface IMember_infoDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 마일리지 조회를 위한 사용자 정보 조회
	public Member_infoDto get_member(@Param("user_id") String user_id);
	
	// 사용자 마일리지 업데이트
	public int update_mileage(@Param("user_id") String user_id,
							  @Param("mileage") int mileage);
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	// 회원가입
	public int joinAction(String user_id,String user_password, String user_name, Date birthday,String email, 
						  String receive_email_flag,String phone_number,String receive_messages_flag, String postal_code, 
						  String address, String detailed_address, String pw_question, String pw_answer, String gender);
	
	// 아이디 중복확인
	public int idCheck(String user_id);
	
	// 이메일 중복확인
	public int emailCheck(String email);
	
	// 로그인
	public int loginAction(String user_id, String user_password);
	
	// 아이디 찾기
	public Member_infoDto idFindAction(String user_name,String email);
	
	// 비밀번호 변경(정보 조회)
	public Member_infoDto passwordFindAction (String user_id,String pw_question,String pw_answer);
	
	// 마이페이지 화면에서 보여줄 정보 조회
	public Member_infoDto change(String uid);
	
	// 회원 정보 변경
	public int changeAction(String email, String receive_email_flag,String phone_number,String receive_messages_flag, 
							String postal_code,String address, String detailed_address, String pw_question, 
							String pw_answer,String user_id,String user_password);
	
	// 회원 탈퇴
	public int withDrawAction(String user_id, String user_password);
	
	// 비밀번호 변경
	public int passwordchangeAction(String user_password, String uid);
	
	//*****************************************************************
	//                                                작업자 : 심준엽 *
	//*****************************************************************
	
	// 페이징, 검색을 통해 회원목록에서 필요한 정보만 가져옴
	public ArrayList<Member_infoDto> memberList( 
			@Param("firstRecordIndex") int firstRecordIndex,
			@Param("lastRecordIndex") int lastRecordIndex,
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword
		) throws Exception;
	
	// 검색을 통해 회원목록에 표시할 수 있는 레코드의 수를 구함
	public int memberCount(
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword
		) throws Exception;

	// 아이디를 통해 회원의 자세한 정보를 가져옴
	public Member_infoDto memberInfoDetail(
			@Param("user_id") String user_id
		) throws Exception;
	
}