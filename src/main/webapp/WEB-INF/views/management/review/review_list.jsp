<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
  <!-- ################################ 조회 옵션 ################################ -->
  <section class="search_bar">
    <div class="col">
      <div class="row">
        <div class="cell">
          <label for="search_keyword" class="label-text">조회 옵션</label>
          <select id="search_option" name="search_option" class="drop-down">
            <option value="전체" <c:if test="${ search_option eq '전체' }">selected</c:if>>
              전체
            </option>

            <option value="아이디" <c:if test="${ search_option eq '아이디' }">selected</c:if>>
              아이디
            </option>

            <option value="상품코드" <c:if test="${ search_option eq '상품코드' }">selected</c:if>>
              상품코드
            </option>

            <option value="내용" <c:if test="${ search_option eq '내용' }">selected</c:if>>
              내용
            </option>
          </select>
          
          <input 
            type="search" 
            id="search_keyword" 
            name="search_keyword" 
            class="search_keyword"
            onKeypress="javascript:if( event.keyCode == 13 ) { getListContents( <c:out value='${ pagination.currentPage }' />) }"
            maxlength="30"
            value="<c:out value='${ search_keyword }' />">

          <!-- ##### 검색 키워드 :: 버튼 ##### -->
          <button 
            class="brand-mint" 
            type="button" 
            onclick="getListContents( <c:out value='${ pagination.currentPage }'/> )">
              조 회
          </button>
        </div>
      </div>

      <!-- ################################ 조회 기간 ################################ -->
      <div class="row">
        <div class="cell">
          <label class="label-text">조회 기간</label>
          <span class="date-interval">
            <button 
              class="date-interval__btn" 
              onclick="
                setCalendar( 183 ); 
                getListContents( <c:out value='${ pagination.currentPage }' /> ); ">
              6개월
            </button>  

            <button 
              class="date-interval__btn" 
              onclick="
                setCalendar( 91 ); 
                getListContents( <c:out value='${ pagination.currentPage }' /> ); ">
              3개월
            </button>

            <button 
              class="date-interval__btn" 
              onclick="
                setCalendar( 30 ); 
                getListContents( <c:out value='${ pagination.currentPage }' /> ); ">
              1개월
            </button>

            <button 
              class="date-interval__btn" 
              onclick="
                setCalendar( 7 ); 
                getListContents( <c:out value='${ pagination.currentPage }' /> ); ">
              일주일
            </button>

            <button 
              class="date-interval__btn" 
              onclick="
                setCalendar( 1 ); 
                getListContents( <c:out value='${ pagination.currentPage }' /> ); ">
              하루
            </button>
          </span>

          <span class="calendar_container">
            <input 
              type="date" 
              class="calendar" 
              id="from-this-date"
              <c:if test="${ not empty fromThisDate }">
                value="<c:out value='${ fromThisDate }' />"
              </c:if> 
              onchange="dateValiation( <c:out value='${ pagination.currentPage }' /> )"/>
            <span>~</span>
            <input 
              type="date" 
              class="calendar"
              id="to-this-date"
              <c:if test="${ not empty toThisDate }">
                value="<c:out value='${ toThisDate }' />"
              </c:if>
              onchange="dateValiation( <c:out value='${ pagination.currentPage }' /> )"/>
          </span>
        </div>
      </div>

      <div class="row advanced_search">
        <div class="cell">
          <label for="search-category" class="label-text">리뷰 정렬</label>
          <select 
            class="drop-down"
            id="current_sort"
            name="current_sort"
            onchange="getListContents( <c:out value='${ pagination.currentPage }'/> ) ">
            <option value="작성일 최신 순" <c:if test="${ current_sort eq '작성일 최신 순' }">selected</c:if>>
              작성일 최신 순
            </option>

            <option value="작성일 오래된 순" <c:if test="${ current_sort eq '작성일 오래된 순' }">selected</c:if>>
              작성일 오래된 순
            </option>

            <option value="별점 높은 순" <c:if test="${ current_sort eq '별점 높은 순' }">selected</c:if>>
              별점 높은 순
            </option>

            <option value="별점 낮은 순" <c:if test="${ current_sort eq '별점 낮은 순' }">selected</c:if>>
              별점 낮은 순
            </option>

            <option value="상품번호 오름차순" <c:if test="${ current_sort eq '상품번호 오름차순' }">selected</c:if>>
              상품번호 오름차순
            </option>

            <option value="상품번호 내림차순" <c:if test="${ current_sort eq '상품번호 내림차순' }">selected</c:if>>
              상품번호 내림차순
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
    <!-- ##### 테이블 상단 섹션 ##### -->
    <section class="table_top">
      <!-- ##### 인스턴스 레코드의 수 ##### -->
      <span class="caption">
        리뷰 목록 <c:out value="${ numberOfRecords }" />건
      </span>

      <!-- ##### 한번에 보여줄 레코드의 최대 개수 :: 드롭다운 ##### -->
      <select 
        class="drop-down"
        id="numberOfRecordsPerPage"
        name="numberOfRecordsPerPage"
        onchange="getListContents( <c:out value='${ pagination.currentPage }'/> )">
        <option value="10" <c:if test="${ pagination.numberOfRecordsPerPage == '10' }">selected</c:if>>
          10개씩 보기
        </option>

        <option value="20" <c:if test="${ pagination.numberOfRecordsPerPage == '20' }">selected</c:if>>
          20개씩 보기
        </option>

        <option value="30" <c:if test="${ pagination.numberOfRecordsPerPage == '30' }">selected</c:if>>
          30개씩 보기
        </option>
      </select>
    </section>

    <!-- ################################ 테이블 ################################ -->
    <section class="table review-list">
      <div class="row-group header-group">
        <div class="header">
          <span class="cell">
            <input type="checkbox" id="all_checkboxes" onchange="controlOfAllCheckboxes()" />
          </span>
          <span class="cell">번호</span>
          <span class="cell">아이디</span>
          <span class="cell">상품코드</span>
          <span class="cell">상품명</span>
          <span class="cell">내용</span>
          <span class="cell">별점</span>
          <span class="cell">작성일</span>
        </div>
      </div>

      <c:forEach var="item" items="${ relationInstance }">
        <div class="row-group">
          <input type="hidden" id="targetUrl" value="review_content" />
          <input type="hidden" id="index" value="${ item.post_no }" />
          <div class="row">
            <span class="cell">
              <input type="checkbox" class="checkbox" value="${ item.post_no }">
            </span>

            <span class="cell">
              <button 
                type="button" 
                class="accordion_button"
                onclick="openAccordion('review_content', '${ item.post_no }' )">
                ${ item.post_no }
              </button>
            </span>

            <span class="cell">
              <button 
                type="button" 
                class="accordion_button ellipsis"
                onclick="openAccordion('review_content', '${ item.post_no }' )">
                <c:out value="${ item.user_id }" />
              </button>
            </span>

            <span class="cell">
              <button 
                type="button" 
                class="accordion_button"
                onclick="openAccordion('review_content', '${ item.post_no }' )">
                <c:out value="${ item.product_code }" />
              </button>
            </span>

            <span class="cell">
              <button 
                type="button" 
                class="accordion_button ellipsis"
                onclick="openAccordion('review_content', '${ item.post_no }' )">
                <c:choose>
                  <c:when test="${ empty item.product_name }">
                    삭제된 상품
                  </c:when>
                  <c:otherwise>
                    <c:out value="${ item.product_name }" />
                  </c:otherwise>
                </c:choose>  
              </button>
            </span>

            <span class="cell">
              <button 
                type="button" 
                class="accordion_button ellipsis"
                onclick="openAccordion('review_content', '${ item.post_no }' )">
                <c:out value="${ item.post_content }" />
              </button>
            </span>

            <span class="cell">
              <button 
                type="button" 
                class="accordion_button"
                onclick="openAccordion('review_content', '${ item.post_no }' )">
                <c:out value="${ item.review_score }" />
              </button>
            </span>

            <span class="cell">
              <button 
                type="button" 
                class="accordion_button"
                onclick="openAccordion('review_content', '${ item.post_no }' )">
                <c:out value="${ item.post_date }" />
              </button>
            </span>
          </div>
        </div>
        <form class="accordion close" id="accordion_${ item.post_no }">
          <div id="content_${ item.post_no }">
            <c:import url="./review/review_content.jsp" />
          </div>
        </form>
      </c:forEach>
      
      <c:if test="${ relationInstance == null or fn:length( relationInstance ) == 0 }">
        <div class="row-group">
          <div class="cell">검색된 리뷰가 없습니다.</div>
        </div>
      </c:if>
    </section>

    <!-- ##### 테이블 하단 섹션 ##### -->
    <section class="table_bottom">
      <div class="button-placement">
        <button class="brand-pink" onclick="deleteTheseRecords()">선택항목 삭제</button>

        <!-- ##### 페이지 매김 기능 ##### --> 
        <c:import url="./paginate.jsp" />
        <span></span>
      </div>
    </section>
  </div>