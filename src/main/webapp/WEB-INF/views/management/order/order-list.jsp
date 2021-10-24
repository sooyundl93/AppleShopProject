<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page import="java.util.ArrayList"%>

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

            <option value="주문번호" <c:if test="${ search_option eq '주문번호' }">selected</c:if>>
              주문번호
            </option>

            <option value="아이디" <c:if test="${ search_option eq '아이디' }">selected</c:if>>
              아이디
            </option>
          </select>
          
          <input 
            type="search" 
            id="search_keyword" 
            name="search_keyword" 
            class="search_keyword"
            onKeypress="javascript:if( event.keyCode == 13 ) { getListContents( <c:out value='${ pagination.currentPage }' /> ) }"
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
          <label for="search-category" class="label-text">상품 정렬</label>
          <select 
            class="drop-down"
            id="current_sort"
            name="current_sort"
            onchange="getListContents( <c:out value='${ pagination.currentPage }'/> )">
            <option value="주문일 최신 순" <c:if test="${ current_sort eq '주문일 최신 순' }">selected</c:if>>
              주문일 최신 순
            </option>

            <option value="주문일 오래된 순" <c:if test="${ current_sort eq '주문일 오래된 순' }">selected</c:if>>
              주문일 오래된 순
            </option>

            <option value="결제금액 높은 순" <c:if test="${ current_sort eq '결제금액 높은 순' }">selected</c:if>>
              결제금액 높은 순
            </option>

            <option value="결제금액 낮은 순" <c:if test="${ current_sort eq '결제금액 낮은 순' }">selected</c:if>>
              결제금액 낮은 순
            </option>

            <option value="주문 상태 오름차순" <c:if test="${ current_sort eq '주문 상태 오름차순' }">selected</c:if>>
              주문 상태 오름차순
            </option>

            <option value="주문 상태 내림차순" <c:if test="${ current_sort eq '주문 상태 내림차순' }">selected</c:if>>
              주문 상태 내림차순
            </option>
          </select>
        </div>
        <div class="cell">

        </div>
      </div>
    </div>
  </section>
  <!-- ################################ 조회 기간 ################################ -->
  
  
  <!-- ################################ 테이블 캡션 & 정렬 ################################ -->
  <div class="contents" id="contents">
    <!-- ##### 테이블 상단 섹션 ##### -->
    <section class="table_top">
      <!-- ##### 인스턴스 레코드의 수 ##### -->
      <span class="caption">
        주문 목록 <c:out value="${ numberOfRecords }" />건
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

    <!-- ############################### 테이블 ################################ -->
    <section class="table order-list">
      <div class="row-group header-group">
        <div class="header">
          <span class="cell">주문번호</span>
          <span class="cell">아이디</span>   
          <span class="cell">상품명</span>
          <span class="cell">실 결제금액</span>
          <span class="cell">결제수단</span>
          <span class="cell">주문 상태</span>
          <span class="cell">주문일</span>
        </div>
      </div>
      <c:forEach var="orderList" items="${ relationInstance }">
        <div class="row-group">
          <div class="row">
            <span class="cell">
              <button type="button" onclick="openNewPage( `./order_list/detail?order_id=<c:out value='${ orderList.orderId }'/>`, 730, 780 )">
                <c:out value="${ orderList.orderId }" />
              </button>
            </span>

            <span class="cell">
              <button type="button" class="ellipsis" onclick="openNewPage( `./order_list/detail?order_id=<c:out value='${ orderList.orderId }'/>`, 730, 780 )">
                <c:out value="${ orderList.userId }" />
              </button>
            </span>

            <span class="cell">
              <button type="button" class="ellipsis" onclick="openNewPage( `./order_list/detail?order_id=<c:out value='${ orderList.orderId }'/>`, 730, 780 )">
                <c:choose>
                  <c:when test="${ fn:length( orderList.orderProducts ) > 0 }">
                    <c:out value="${ orderList.orderProducts[0].productName } ${ orderList.orderProducts[0].numberOfOrders }개" />
                    <c:if test="${ fn:length( orderList.orderProducts ) > 1 }">
                      외 <c:out value="${ fn:length( orderList.orderProducts ) - 1 }" />건
                    </c:if>
                  </c:when>
                  <c:otherwise>
                    주문 상품이 없습니다.
                  </c:otherwise>
                </c:choose>
              </button>
            </span>

            <span class="cell">
              <button type="button" class="ellipsis" onclick="openNewPage( `./order_list/detail?order_id=<c:out value='${ orderList.orderId }'/>`, 730, 780 )">
                <c:out value="${ orderList.totalPrice }" />원
              </button>
            </span>

            <span class="cell">
              <button type="button" onclick="openNewPage( `./order_list/detail?order_id=<c:out value='${ orderList.orderId }'/>`, 730, 780 )">
                <c:out value="${ orderList.paymentMethod  }" />
              </button>
            </span>

            <span class="cell">
              <button type="button" onclick="openNewPage( `./order_list/detail?order_id=<c:out value='${ orderList.orderId }'/>`, 730, 780 )">
                <c:out value="${ orderList.orderStatus }" />
              </button>
            </span>

            <span class="cell">
              <button type="button" onclick="openNewPage( `./order_list/detail?order_id=<c:out value='${ orderList.orderId }'/>`, 730, 780 )">
                <c:out value="${ orderList.orderDate }" />
              </button>
            </span>
          </div>
        </div>
      </c:forEach>

      <c:if test="${ relationInstance == null or fn:length( relationInstance ) == 0 }">
        <div class="row-group">
          <div class="cell">검색된 회원이 없습니다.</div>
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