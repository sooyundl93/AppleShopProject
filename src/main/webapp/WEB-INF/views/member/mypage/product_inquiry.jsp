<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/cscenter.css">

<!--------------------------------------- MAIN -------------------------------------->
<div id="container">

	<!---------------------------------- page_title ------------------------------------>
	<div class="page_title">
		<h2>마이페이지</h2>
		<div class="breadcrumb">
			<div class="location_content">
				<!-- <span>${ uri_name}</span> -->
				<span>마이페이지</span> <span class="location_arrow">&gt; </span> <span
					class="current">상품문의</span>
			</div>
		</div>
		<div class="line"></div>
	</div>

	<div class="content">
		<div class="aside">
			<div class="aside_menu">
				<a href="order_list">주문목록/배송조회</a>
			</div>
			<div class="aside_menu">
				<a href="product_inquiry" class="current">상품문의</a>
			</div>
			<div class="aside_menu">
				<a href="product_review">상품리뷰</a>
			</div>
			<div class="aside_menu">
				<a href="cart">장바구니</a>
			</div>
			<div class="aside_menu">
				<a href="one2one_inquiry">1:1문의</a>
			</div>
			<div class="aside_menu">
				<a href="mypage_change">회원정보변경</a>
			</div>
			<div class="aside_menu">
				<a href="password_change">비밀번호변경</a>
			</div>
			<div class="aside_menu">
				<a href="#" class="withdraw" onclick="javascript:withDraw();">회원탈퇴</a>
			</div>
		</div>

		<!--------------------------------------- board -------------------------------------->
		<div class="board one2one">

			<!-- 프로필 -->
			<div class="profile_container">
				<div class="profile">
					<div class="profile_img">
						<img src="Images/main/profile_icon.png" alt="프로필 이미지">
					</div>
					<div class="profile_text">${ dto3.user_name }님</div>
				</div>

				<!-- 보유 마일리지 -->
				<div class="profile">
					<div class="profile_img">
						<img src="Images/main/icon_mileage.png" alt="마일리지 이미지">
					</div>
					<div class="profile_text">마일리지 ${ dto3.mileage }원</div>
				</div>
			</div>

			<!-- ################################ 검색 조희 ################################ -->
			<div class="search_date">
				<div class="date_title">
					<label for="search_keyword" class="label-text">조회 옵션</label>
				</div>

				<div class="period">
					<select id="search_option" name="search_option" class="drop-down">
						<option value="전체"
							<c:if test="${ search_option eq '전체' }">selected</c:if>>
							전체</option>

						<option value="문의 번호"
							<c:if test="${ search_option eq '문의 번호' }">selected</c:if>>
							문의 번호</option>

						<option value="아이디"
							<c:if test="${ search_option eq '아이디' }">selected</c:if>>
							아이디</option>

						<option value="상품 코드"
							<c:if test="${ search_option eq '상품 코드' }">selected</c:if>>
							상품 코드</option>

						<option value="내용"
							<c:if test="${ search_option eq '내용' }">selected</c:if>>
							내용</option>
					</select> <input type="search" id="search_keyword" name="search_keyword"
						class="search_keyword input-form"
						onKeypress="javascript:if( event.keyCode == 13 ) { getListContents( <c:out value='${ pagination.currentPage }' />) }"
						maxlength="30" value="<c:out value='${ search_keyword }' />">

					<!-- ##### 검색 키워드 :: 버튼 ##### -->
					<button type="button" class="button"
						onclick="getListContents( <c:out value='${ pagination.currentPage }'/> )">
						조 회</button>
				</div>
			</div>

			<!-- ################################ 기간 조회 ################################ -->
			<div class="search_date">
				<div class="date_title">조회기간</div>
				<div class="period">
					<button type="button"
						onclick="
		               setCalendar( 183 ); 
		               getListContents( <c:out value='${ pagination.currentPage }' /> ); ">
						6개월</button>
					<button type="button"
						onclick="
		               setCalendar( 91 ); 
		               getListContents( <c:out value='${ pagination.currentPage }' /> ); ">
						3개월</button>
					<button type="button"
						onclick="
		               setCalendar( 30 ); 
		               getListContents( <c:out value='${ pagination.currentPage }' /> ); ">
						1개월</button>
					<button type="button"
						onclick="
		               setCalendar( 7 ); 
		               getListContents( <c:out value='${ pagination.currentPage }' /> ); ">
						일주일</button>
					<button type="button"
						onclick="
		               setCalendar( 1 ); 
		               getListContents( <c:out value='${ pagination.currentPage }' /> ); ">
						하루</button>
				</div>

				<div class="datepicker">
					<input type="date" id="from-this-date"
						<c:if test="${ not empty fromThisDate }">
			               value="<c:out value='${ fromThisDate }' />"
			             </c:if>
						onchange="dateValiation( <c:out value='${ pagination.currentPage }' /> )" />
					<span>~</span> <input type="date" id="to-this-date"
						<c:if test="${ not empty toThisDate }">
			               value="<c:out value='${ toThisDate }' />"
			             </c:if>
						onchange="dateValiation( <c:out value='${ pagination.currentPage }' /> )" />
				</div>
			</div>

			<!-- ################################ 조회 정렬 ################################ -->
			<div class="search_date">
				<div class="date_title">
					<label for="search-category" class="label-text">상품정렬</label>
				</div>
				<div class="period">
					<select class="drop-down" id="current_sort" name="current_sort"
						onchange="getListContents( <c:out value='${ pagination.currentPage }'/> ) ">
						<option value="작성일 최신 순"
							<c:if test="${ current_sort eq '작성일 최신 순' }">selected</c:if>>
							작성일 최신 순</option>

						<option value="작성일 오래된 순"
							<c:if test="${ current_sort eq '작성일 오래된 순' }">selected</c:if>>
							작성일 오래된 순</option>

						<option value="상품 코드 오름차순"
							<c:if test="${ current_sort eq '상품 코드 오름차순' }">selected</c:if>>
							상품 코드 오름차순</option>

						<option value="상품 코드 내림차순"
							<c:if test="${ current_sort eq '상품 코드 내림차순' }">selected</c:if>>
							상품 코드 내림차순</option>

						<option value="많은 답글 순"
							<c:if test="${ current_sort eq '많은 답글 순' }">selected</c:if>>
							많은 답글 순</option>

						<option value="적은 답글 순"
							<c:if test="${ current_sort eq '적은 답글 순' }">selected</c:if>>
							적은 답글 순</option>
					</select>
				</div>
			</div>

			<!-- ################################ 문의 내역 테이블 ################################ -->
			<div class="contents" id="contents">
				<!-- 목록 카운트 -->
				<div class="table_count">
					<div class="table_caption" id="table_caption">
						상품문의 &nbsp;<strong class="count_number"><c:out
								value="${ numberOfRecords }" /></strong>건
					</div>
					<span>최근 30일 내에 문의하신 내역입니다</span>
					<!-- ##### 한번에 보여줄 레코드의 최대 개수 :: 드롭다운 ##### -->
					<select class="drop-down" id="numberOfRecordsPerPage"
						name="numberOfRecordsPerPage"
						onchange="getListContents( <c:out value='${ pagination.currentPage }'/> )">
						<option value="10"
							<c:if test="${ pagination.numberOfRecordsPerPage == '10' }">selected</c:if>>
							10개씩 보기</option>

						<option value="20"
							<c:if test="${ pagination.numberOfRecordsPerPage == '20' }">selected</c:if>>
							20개씩 보기</option>

						<option value="30"
							<c:if test="${ pagination.numberOfRecordsPerPage == '30' }">selected</c:if>>
							30개씩 보기</option>
					</select>
				</div>

				<!-- ############################### 테이블 ################################ -->
				<div class="table">
					<div class="th">
						<div class="inquiry_date">날짜</div>
						<div class="inquiry_subject">상품명</div>
						<div class="inquiry_content">내용</div>
						<div class="inquiry_answer">답변글수</div>
						<div class="inquiry_delete"></div>
					</div>
					<c:forEach var="item" items="${ relationInstance }"
						varStatus="loopStatus">
						<div
							class="row-group <c:if test='${ loopStatus.last }'>last</c:if>"
							id="container_${ item.inquiry_no }">

							<div class="td">

								<!-- 문의 날짜 -->
								<div class="inquiry_date">
									<c:out value="${ item.inquiry_date }" />									
								</div>

								<!-- 상품명 -->
								<div class="inquiry_subject">
									${ item.product_name }
								</div>

								<!-- 문의 내용 -->
								<div class="inquiry_content">
									<a href="#"
										onclick="window.open('product_inquiry_popup?inquiry_no=${ item.inquiry_no }','window_name',
                                		'width=480, height=680, location=no, status=no, scrollbars=yes');">${ item.inquiry_content }</a>
								</div>

								<!-- 문의글 답변 수 -->
								<div class="inquiry_answer">
                					<c:out value="${ item.number_of_replies }" />
								</div>

								<!-- 문의글 삭제 버튼 -->
								<div class="inquiry_delete delete">
									<a
										href="product_inquiry_delete?inquiry_no=${ item.inquiry_no }">
										<button class="button">삭제</button>
									</a>
								</div>
							</div>
						</div>
					</c:forEach>

					<c:if
						test="${ relationInstance == null or fn:length( relationInstance ) == 0 }">
						<div class="td">
							<div class="cell">검색된 문의 글이 없습니다.</div>
						</div>
					</c:if>

				</div>
				<!-- ##### 페이지 매김 기능 ##### -->
				<div class="product_gallery">
					<div class="paging_button">
						<button class="button paging-direction first" type="button"
							onclick="getListContents( 1 )"
							<c:if test="${pagination.hasPreviousPage == false}">disabled</c:if>>
							처음</button>

						<button class="button paging-direction prev" type="button"
							onclick="getListContents(
        <c:out value='${pagination.currentPage}' />
        <c:if test='${pagination.hasPreviousPage == true}'>-1</c:if>
      )"
							<c:if test="${pagination.hasPreviousPage == false}">disabled</c:if>>
							이전</button>

						<c:forEach begin="${pagination.firstPage}"
							end="${pagination.lastPage}" var="index">
							<button type="button"
								class="paging button page-number <c:if test="${pagination.currentPage == index}">selected</c:if>"
								onclick="getListContents( <c:out value='${index}' /> )"
								<c:if test="${pagination.currentPage eq index}">
        disabled
      </c:if>>
								<c:choose>
									<c:when test="${index == 0}">
          1
        </c:when>

									<c:when test="${index >= 1}">
          ${index}
        </c:when>
								</c:choose>
							</button>
						</c:forEach>

						<button class="button paging-direction next" type="button"
							onclick="
      getListContents(
        <c:out value='${pagination.currentPage}' />
        <c:if test='${pagination.hasNextPage == true}'>+1</c:if>
      )"
							<c:if test="${pagination.hasNextPage == false}">
      disabled
    </c:if>>
							다음</button>

						<button class="button paging-direction end" type="button"
							onclick="getListContents( <c:out value='${pagination.totalNumberOfPages}'/> )"
							<c:if test="${pagination.hasNextPage == false}">
      disabled
    </c:if>>
							마지막</button>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>

<script>
    function withDraw(){  
        window.open("withDraw", "회원탈퇴", "width=500, height=500, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );  
    }  
</script>