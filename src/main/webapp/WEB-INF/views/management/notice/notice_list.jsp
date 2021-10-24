<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  <!-- ##### 검색 바 섹션 ##### -->
  <section class="search_bar">
    <div class="col">
      <div class="row">
        <div class="cell">
          <label for="search_keyword" class="label-text">
            조회 옵션
          </label>

          <!-- ##### 검색 옵션 :: 드롭다운 ##### -->
          <select id="search_option" name="search_option" class="drop-down">
            <option value="전체" <c:if test="${search_option eq '전체'}">selected</c:if>>
              전체
            </option>

            <option value="제목" <c:if test="${search_option eq '제목'}">selected</c:if>>
              제목
            </option>

            <option value="내용" <c:if test="${search_option eq '내용'}">selected</c:if>>
              내용
            </option>

            <option value="글 번호" <c:if test="${search_option eq '글 번호'}">selected</c:if>>
              글 번호
            </option>

            <option value="아이디" <c:if test="${search_option eq '아이디'}">selected</c:if>>
              아이디
            </option>
          </select>
          
          <!-- ##### 검색 키워드 :: 입력 ##### -->
          <input 
            type="search" 
            id="search_keyword" 
            name="search_keyword" 
            class="search_keyword"
            onKeypress="javascript:if( event.keyCode == 13 ) { getListContents( <c:out value='${pagination.currentPage}'/> ) }"
            maxlength="30"
            value="<c:out value='${search_keyword}' />">

          <!-- ##### 검색 키워드 :: 버튼 ##### -->
          <button 
            class="brand-mint" 
            type="button" 
            onclick="getListContents( <c:out value='${pagination.currentPage}'/> )">
            조 회
          </button>
        </div>
      </div>

      <!-- ################################ 조회 정렬 ################################ -->
      <div class="row advanced_search">
        <div class="cell">
          <label for="search-category" class="label-text">
            공지 정렬
          </label>

          <select 
            class="drop-down" 
            id="current_sort" 
            name="current_sort"
            onchange="getListContents( <c:out value='${pagination.currentPage}'/> )">
            <option value="최신 등록일 순" <c:if test="${current_sort eq '최신 등록일 순'}">selected</c:if>>
              최신 등록일 순
            </option>

            <option value="오래된 등록일 순" <c:if test="${current_sort eq '오래된 등록일 순'}">selected</c:if>>
              오래된 등록일 순
            </option>

            <option value="중요 공지" <c:if test="${current_sort eq '중요 공지'}">selected</c:if>>
              중요 공지
            </option>

            <option value="일반 공지" <c:if test="${current_sort eq '일반 공지'}">selected</c:if>>
              일반 공지
            </option>
          </select>
        </div>
        <div class="cell">

        </div>
      </div>
    </div>
  </section>

  <!-- ################################ 테이블 캡션 & 정렬 ################################ -->
  <div class="contents" id="contents">
    <section class="table_top">
      <span class="caption">
        공지 목록 <c:out value="${ numberOfRecords }" />건
      </span>

      <select 
        class="drop-down"
        id="numberOfRecordsPerPage"
        name="numberOfRecordsPerPage"
        onchange="getListContents( <c:out value='${pagination.currentPage}'/> )">
        <option value="10" <c:if test="${pagination.numberOfRecordsPerPage == '10'}">selected</c:if>>
          10개씩 보기
        </option>

        <option value="20" <c:if test="${pagination.numberOfRecordsPerPage == '20'}">selected</c:if>>
          20개씩 보기
        </option>

        <option value="30" <c:if test="${pagination.numberOfRecordsPerPage == '30'}">selected</c:if>>
          30개씩 보기
        </option>
      </select>
    </section>

    <!-- ##### 테이블 섹션 ##### -->
    <section class="table notice-list">
      <div class="row-group header-group">
        <!-- ##### 리스트 :: 헤더 ##### -->
        <div class="header">
          <span class="cell">
            <input type="checkbox" id="all_checkboxes" onchange="controlOfAllCheckboxes()" />
          </span>
          <span class="cell">글 번호</span>
          <span class="cell">제목</span>
          <span class="cell">글쓴이</span>
          <span class="cell">작성일</span>
          <span class="cell">조회 수</span>
          <span class="cell">중요 표시</span>
        </div>
      </div>

      <!-- ##### 리스트 :: 데이터 ##### -->
      <c:forEach var="noticeList" items="${ relationInstance }">
        <div class="row-group">
          <div class="row">
            <span class="cell">
              <input type="checkbox" class="checkbox" value="<c:out value='${ noticeList.post_no }'/>">
            </span>

            <span class="cell">
              <a href="./notice_list/detail?post_no=<c:out value='${ noticeList.post_no }'/>">
                <c:out value="${ noticeList.post_no }" />
              </a>
            </span>

            <span class="cell">
              <a href="./notice_list/detail?post_no=<c:out value='${ noticeList.post_no }'/>">
                <c:out value="${ noticeList.post_title }" />
              </a>
            </span>

            <span class="cell">
              <a href="./notice_list/detail?post_no=<c:out value='${ noticeList.post_no }'/>">
                <c:out value="${ noticeList.user_id }" />
              </a>
            </span>

            <span class="cell">
              <a href="./notice_list/detail?post_no=<c:out value='${ noticeList.post_no }'/>">
                <c:out value="${ noticeList.post_date }" />
              </a>
            </span>

            <span class="cell">
              <a href="./notice_list/detail?post_no=<c:out value='${ noticeList.post_no }'/>">
                <c:out value="${ noticeList.post_hits }" />
              </a>
            </span>

            <span class="cell">
              <!-- ##### 임시 이메일 발급 :: 버튼 ##### -->
              <c:choose>
                <c:when test="${ noticeList.important_flag eq 'Y' }">
                  <button 
                    onclick="updateThisRecord( <c:out value='${noticeList.post_no}, `${noticeList.important_flag}`, ${ pagination.currentPage }' /> )"
                    class="brand-pink">
                    중요 공지
                  </button>
                </c:when>

                <c:otherwise>
                  <button 
                    onclick="updateThisRecord( <c:out value='${noticeList.post_no}, `${noticeList.important_flag}`, ${ pagination.currentPage }' /> )"
                    class="brand-mint">
                    일반 공지
                  </button>  
                </c:otherwise>
              </c:choose>
            </span>
          </div>
        </div>
      </c:forEach>

      <!-- ##### 데이터가 없을 때 표시할 테스트 ##### -->
      <c:if test="${ relationInstance == null or fn:length( relationInstance ) == 0 }">
        <div class="row-group">
          <div class="cell">
            검색된 공지글이 없습니다.
          </div>
        </div>
      </c:if>
    </section>
    
    <!-- ##### 테이블 하단 섹션 ##### -->
    <section class="table_bottom">
      <div class="button-placement">
        <button 
          class="brand-pink" 
          onclick="deleteTheseRecords( <c:out value='${pagination.currentPage}'/> )">
          선택항목 삭제
        </button>

        <!-- ##### 페이지 매김 기능 ##### -->
        <c:import url="./paginate.jsp" />

        <button 
          class="brand-mint" 
          onclick="location.href='./notice_list/writing';">
          공지글 쓰기
        </button>
      </div>
    </section>
  </div>