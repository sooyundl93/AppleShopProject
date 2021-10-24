package com.study.springboot.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.NoticeDto;

// 공지사항 notice 테이블 Dao

@Mapper
public interface INoticeDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 중요 공지 조회
	public ArrayList<NoticeDto> important_list();
	
	//*****************************************************************
	//                                                작업자 : 장수연 *
	//*****************************************************************
	
	// 공지사항 전체 조회
    // public List<NoticeDto> noticeList();
    public List<NoticeDto> noticeList(@Param("firstRecordIndex") int firstRecordIndex,
								      @Param("lastRecordIndex") int lastRecordIndex,
								      @Param("search_option") String search_option, 
								      @Param("search_keyword") String search_keyword
    								 ) throws Exception;

    // 검색을 통해 공지사항 목록에 표시할 수 있는 갯수를 구함
    public int noticeCount(@Param("search_option") String search_option, 
            			   @Param("search_keyword") String search_keyword
    					  ) throws Exception;

    // 공지사항 상세 조회
    public NoticeDto noticeView(int post_no);

    // 공지사항 화면에서 보여줄 정보 조회
    public NoticeDto noticeFlag(int post_no);

    // 공지사항 조회수 +1 UPDATE
    public int hit(int post_no);
    
	//*****************************************************************
	//                                                작업자 : 심준엽 *
	//*****************************************************************
    
	// 레코드 수
	public int countRecords(
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword
		) throws Exception;
	
	// 공지글 목록
	public ArrayList<NoticeDto> relationInstance( 
			@Param("firstRecordIndex") int firstRecordIndex,
			@Param("lastRecordIndex") int lastRecordIndex,
			@Param("search_option") String search_option, 
			@Param("search_keyword") String search_keyword,
			@Param("sortRecords") String sortRecords
		) throws Exception;
	
	// 공지글 업로드
	public int insertRecord( 
			@Param("user_id") String userId,
			@Param("post_title") String postTitle,
			@Param("post_content") String postCotent,
			@Param("important_flag") String importantFlag
		) throws Exception;
	
	// 공지글 조회
	public NoticeDto detail( 
			@Param("post_no") int postNo
		) throws Exception;
	
	// 공지글 업로드
	public int updateRecord( 
			@Param("post_no") int postNo,
			@Param("post_title") String postTitle,
			@Param("post_content") String postCotent,
			@Param("important_flag") String importantFlag
		) throws Exception;
	
	// 이전, 다음 글 검색
	public HashMap<String, String> nearbyRecords(
			@Param("post_no") int postNo
		) throws Exception;
	
}