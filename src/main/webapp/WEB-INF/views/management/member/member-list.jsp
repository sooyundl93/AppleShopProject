<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

  <!-- ##### 검색 바 섹션 ##### -->
  <section class="search_bar">
    <div class="col">
      <div class="row">
        <div class="cell">
          <label for="search_option" class="label-text">
            조회 옵션
          </label>

          <!-- ##### 검색 옵션 :: 드롭다운 ##### -->
          <select id="search_option" name="search_option" class="drop-down">
            <option value="전체" <c:if test="${search_option eq '전체'}">selected</c:if>>
              전체
            </option>

            <option value="아이디" <c:if test="${search_option eq '아이디'}">selected</c:if>>
              아이디
            </option>

            <option value="이름" <c:if test="${search_option eq '이름'}">selected</c:if>>
              이름
            </option>

            <option value="이메일" <c:if test="${search_option eq '이메일'}">selected</c:if>>
              이메일
            </option>

            <option value="전화번호" <c:if test="${search_option eq '전화번호'}">selected</c:if>>
              전화번호
            </option>

            <option value="주소" <c:if test="${search_option eq '주소'}">selected</c:if>>
              주소
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
            value="<c:out value="${search_keyword}" />">

          <!-- ##### 검색 키워드 :: 버튼 ##### -->
          <button 
            class="brand-mint" 
            type="button" 
            onclick="getListContents( <c:out value='${pagination.currentPage}'/> )">
            조 회
          </button>
        </div>
      </div>
      
      <div class="row advanced_search">
        <div class="cell">
          <label for="current_sort" class="label-text">
            회원 정렬
          </label>

          <select 
            class="drop-down" 
            id="current_sort" 
            name="current_sort"
            onchange="getListContents( <c:out value='${pagination.currentPage}'/> )">
            <option value="아이디 오름차 순" <c:if test="${current_sort eq '아이디 오름차 순'}">selected</c:if>>
              아이디 오름차 순
            </option>

            <option value="아이디 내림차 순" <c:if test="${current_sort eq '아이디 내림차 순'}">selected</c:if>>
              아이디 내림차 순
            </option>

            <option value="이름 오름차 순" <c:if test="${current_sort eq '이름 오름차 순'}">selected</c:if>>
              이름 오름차 순
            </option>

            <option value="이름 내림차 순" <c:if test="${current_sort eq '이름 내림차 순'}">selected</c:if>>
              이름 내림차 순
            </option>

            <option value="이메일 오름차 순" <c:if test="${current_sort eq '이메일 오름차 순'}">selected</c:if>>
              이메일 오름차 순
            </option>

            <option value="이메일 내림차 순" <c:if test="${current_sort eq '이메일 내림차 순'}">selected</c:if>>
              이메일 내림차 순
            </option>

            <option value="연락처 오름차 순" <c:if test="${current_sort eq '연락처 오름차 순'}">selected</c:if>>
              연락처 오름차 순
            </option>

            <option value="연락처 내림차 순" <c:if test="${current_sort eq '연락처 내림차 순'}">selected</c:if>>
              연락처 내림차 순
            </option>

            <option value="주소 오름차 순" <c:if test="${current_sort eq '주소 오름차 순'}">selected</c:if>>
              주소 오름차 순
            </option>

            <option value="주소 내림차 순" <c:if test="${current_sort eq '주소 내림차 순'}">selected</c:if>>
              주소 내림차 순
            </option>
          </select>
        </div>
        <div class="cell">

        </div>
      </div>
    </div>
  </section>

  <div class="contents" id="contents">
    <!-- ##### 테이블 상단 섹션 ##### -->
    <section class="table_top">
      <!-- ##### 인스턴스 레코드의 수 ##### -->
      <span class="caption">
        회원 목록 <c:out value="${ numberOfRecords }" />건
      </span>

      <!-- ##### 한번에 보여줄 레코드의 최대 개수 :: 드롭다운 ##### -->
      <div class="table_top_right">
        <c:choose>
          <c:when test="${view_mode eq 'normal'}">
            <button
              id="normal"
              class="more_button"
              onclick="
                changeHiddenValue( 'extended', <c:out value='${pagination.currentPage}'/> )">
              확장 보기
          </button>
            
          </c:when>
          <c:otherwise>
            <button 
              id="extended"
              class="more_button"
              onclick="
                changeHiddenValue( 'normal', <c:out value='${pagination.currentPage}'/> )">
              기본 보기
          </button>
          </c:otherwise>
        </c:choose>

        <input type="hidden" value="<c:out value='${ view_mode }' />" id="view_mode" />

        <select 
          class="drop-down"
          id="numberOfRecordsPerPage"
          name="numberOfRecordsPerPage"
          onchange="getListContents( <c:out value='${pagination.currentPage}' /> ) ">
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
      </div>
    </section>

    <!-- ##### 테이블 섹션 ##### -->
    <c:choose>
        <c:when test="${view_mode eq 'normal'}" >
          <section class="table member-list">
            <div class="row-group header-group">
              <!-- ##### 리스트 :: 헤더 ##### -->
              <div class="header">
                <span class="cell">아이디</span>
                <span class="cell">성명</span>
                <span class="cell">이메일</span>
                <span class="cell">연락처</span>
                <span class="cell">주소</span>
                <span class="cell">배송 대기 중</span>
                <span class="cell">임시 비밀번호</span>
              </div>
            </div>

            <!-- ##### 리스트 :: 데이터 ##### -->
            <c:forEach var="memberList" items="${ relationInstance }">
              <div class="row-group">
                <div class="row">
                  <span class="cell ellipsis">
                    <button type="button" class="ellipsis" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.userId }" />
                    </button>
                  </span>

                  <span class="cell">
                    <button type="button" class="ellipsis" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.userName }" />
                    </button>
                  </span>

                  <span class="cell">
                    <button type="button" class="ellipsis" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.email }" />
                    </button>
                  </span>

                  <span class="cell">
                    <button type="button" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.phoneNumber }" />
                    </button>
                  </span>

                  <span class="cell">
                    <button type="button" class="ellipsis" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.address }" />
                    </button>
                  </span>

                  <span class="cell">
                    <c:choose>
                      <c:when test="${ memberList.shippingStatus[2] > 0 }">
                        <button 
                          class="brand-pink" 
                          onclick="getListContents( <c:out value='${pagination.currentPage}'/>, 'order_list', '아이디', `<c:out value='${ memberList.userId }' />` )">
                          <c:out value="${ memberList.shippingStatus[2] }" />건
                        </button>
                      </c:when>
                      <c:otherwise>
                        없음
                      </c:otherwise>
                    </c:choose>
                  </span>

                  <span class="cell">
                    <!-- ##### 임시 이메일 발급 :: 버튼 ##### -->
                    <button class="brand-mint" 
                      onclick="confirmSendEmail( <c:out value='`${ memberList.userId }`, `${ memberList.userName }`, `${ memberList.email }`'/> )">
                      이메일 보내기
                    </button>
                  </span>

                </div>
              </div>
            </c:forEach>
        </c:when>

        <c:otherwise>
          <section class="table member-list-extended">
            <div class="row-group header-group">
              <!-- ##### 리스트 :: 헤더 ##### -->
              <div class="header">
                <span class="cell">아이디</span>
                <span class="cell">성별</span>
                <span class="cell">나이</span>
                <span class="cell">주문 수</span>
                <span class="cell">총 결제 금액</span>
                <span class="cell">보유 마일리지</span>
                <span class="cell">리뷰 수</span>
                <span class="cell">리뷰 평점</span>
                <span class="cell">방문 수</span>
                <span class="cell">가입일</span>
              </div>
            </div>
            
            <!-- ##### 리스트 :: 데이터 ##### -->
            <c:forEach var="memberList" items="${ relationInstance }">
              <div class="row-group">
                <div class="row">
                  <span class="cell">
                    <button type="button" class="ellipsis" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.userId }" />
                    </button>
                  </span>
      
                  <span class="cell">
                    <button type="button" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.gender }" />성
                    </button>
                  </span>
      
                  <span class="cell">
                    <button type="button" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.age }" />세
                    </button>
                  </span>
      
                  <span class="cell">
                    <button type="button" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.numberOfProductsOrderd }" />건
                    </button>
                  </span>
      
                  <span class="cell">
                    <button type="button" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.totalOrderPrice }" />원
                    </button>
                  </span>

                  <span class="cell">
                    <button type="button" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.mileage }" />원
                    </button>
                  </span>

                  <span class="cell">
                    <button type="button" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.numberOfMemberReviews }" />회
                    </button>
                  </span>
      
                  <span class="cell">
                    <button type="button" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.averageOfReviewScores }" />점
                    </button>
                  </span>
      
                  <span class="cell">
                    <button type="button" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.numberOfVisits }" />회
                    </button>
                  </span>
      
                  <span class="cell">
                    <button type="button" onclick="openNewPage( `/admin/member_list/detail?userId=<c:out value='${ memberList.userId }' />`, 1000, 780 )">
                      <c:out value="${ memberList.memberSince }" />
                    </button>
                  </span>
                </div>
              </div>
            </c:forEach>
        </c:otherwise>
      </c:choose>

      <!-- ##### 데이터가 없을 때 표시할 테스트 ##### -->
      <c:if test="${ relationInstance == null or fn:length( relationInstance ) == 0 }">
        <div class="row-group">
          <div class="cell">
            검색된 회원이 없습니다.
          </div>
        </div>
      </c:if>
    </section>
    
    <!-- ##### 테이블 하단 섹션 ##### -->
    <section class="table_bottom">
      <div class="button-placement">
        <!-- ##### 페이지 매김 기능 ##### -->
        <c:import url="./paginate.jsp" />
      </div>
    </section>
  </div>