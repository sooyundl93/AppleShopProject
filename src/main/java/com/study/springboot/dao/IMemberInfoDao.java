package com.study.springboot.dao;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.study.springboot.dto.MemberInfoDto;

@Mapper
public interface IMemberInfoDao {
	
	// 회원 목록
	public ArrayList<MemberInfoDto> relationInstance( 
			@Param("firstRecordIndex") int firstRecordIndex,
			@Param("lastRecordIndex") int lastRecordIndex,
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword,
			@Param("sortRecords") String sortRecords
		) throws Exception;
	
	// 레코드 수
	public int countRecords(
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword
		) throws Exception;

	// 회원의 자세한 정보
	public MemberInfoDto memberInfoDetail(
			@Param("user_id") String user_id
		) throws Exception;
	
	// 비밀번호 변경
	public int updatePassword(
			@Param("user_id") String user_id,
			@Param("user_password") String user_password
		) throws Exception;
}