<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<link rel="stylesheet" href="/css/product.css">
<link rel="stylesheet" href="/css/common.css">

<!--------------------------------------- MAIN -------------------------------------->
<div id="container">
	<!-- URI -->
	<div class="location">
		<div>${ product_info.parent_category }</div>        
		<div class="next">></div>    
		<div>${ product_info.child_category }</div>
	</div>

	<!--********************-->
	<!--* 상품 정보 썸네일 *-->
	<!--********************-->
	<div class="section1" id="section1">
		<div class="product_image_info">
			<c:forEach var="product_image_info" items="${ product_image_info }" begin="0" end="0">
				<img src="/products/${ product_image_info.image_file_name }" alt="상품 이미지" class="img">
			</c:forEach>
		</div>

		<div class="product_table_info">
			<div class="info_title">
				<!-- 제조사 -->
				<div class="th">${ product_info.manufacturer }</div>	

				<!-- 상품명 -->
				<div class="th">${ product_info.product_name }</div>	

				<!-- 상품 가격 -->
				<div class="th">
					<fmt:formatNumber value="${ product_info.product_price }" pattern="#,###"/>원
				</div>
			</div>

			<div class="line"></div>

			<div class="info_sub">
				<!-- 평균 별점 -->
				<div class="tr">
					<div class="th">별점</div>
					<div class="td star">
						${ product_and_review.average_review_score }점
						<img src="Images/main/${ avg_star_img }" alt="평균 별점 이미지">
					</div>
				</div>

				<!-- 적립될 마일리지 -->
				<div class="tr">
					<div class="th">적립 마일리지</div>
					<div class="td">
						<fmt:formatNumber value="${ accrued_mileage }" pattern="#,###"/>
					</div>
				</div>

				<!-- 배송비 -->	
				<div class="tr">
					<div class="th">배송비</div>	
					<div class="td">
						<div><fmt:formatNumber value="${ product_info.shipping_cost }" pattern="#,###"/></div>
						<div>상품배송정보 참고</div>
					</div>
				</div>
				
				<!-- 갯수선택 -->
				<form>
					<div class="tr">
						<div class="th">구매수량</div>					
						<div class="td">
							<select name="number_of_products">
								<option value="1">1개</option>
								<option value="2">2개</option>
								<option value="3">3개</option>
								<option value="4">4개</option>
								<option value="5">5개</option>
								<option value="6">6개</option>
								<option value="7">7개</option>
								<option value="8">8개</option>
								<option value="9">9개</option>
								<option value="10">10개</option>
							</select>
						</div>
					</div>
					<div class="submit_button">
						<!-- 장바구니, 바로구매 버튼 -->
						<input type="hidden" id="product_code" name="product_code" value="${ product_info.product_code }">
						<input type="submit" class="btn_line" value="장바구니" formaction="cart_insert_action">				
						<input type="submit" class="btn_blue" value="바로구매" formaction="straight_order">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div id="container_section2">
	<!--*************-->
	<!--* Inner Nav *-->
	<!--*************-->
	<div class="section2">
		<div class="menu_location">
			<div class="location_container">
				<a href="#section3">상품정보</a>
				<a href="#section4">리뷰</a>
				<a href="#section5">상품문의</a>
				<a href="#section6">배송/환불</a>
			</div>
		</div>

		<!--******************-->
		<!--* 상품 정보 본문 *-->
		<!--******************-->
		<div id="section3"></div>
		<div class="section3">
			<c:forEach var="product_image_info" items="${ product_image_info }" begin="1" end="1">
				<img src="/products/${ product_image_info.image_file_name }" alt="상품 이미지">
			</c:forEach>		
			<div>${ product_info.product_description }</div>
		</div>

		<!--*************-->
		<!--* 리뷰 섹션 *-->
		<!--*************-->
		<div id="section4"></div>
		<div class="section4" id="section_review">	
			<!-- 리뷰 건수 & 평균 -->
			<div class="table_count">
				<div class="inquiry_header">
					리뷰 &nbsp;<span class="count_number"><c:out value="${ product_and_review.count_of_review }"/></span>건
				</div>			
				<div class="review_star star">
					<img src="/Images/main/${ avg_star_img }" alt="평균 별점"> 
					${ product_and_review.average_review_score }
				</div>     
			</div>
			
			<div class="review_table">			
				<!-- 리뷰 컨텐츠 -->
				<c:forEach var="member_review" items="${ member_review }">
					<div class="review_tr">
						<div class="review_section">                    	
							<!-- 리뷰 이미지 -->
							<div class="review_image">
								<img src="/review/${ member_review.image_file_name }" alt="리뷰 이미지" class="review_image">
							</div>
							
							<div class="review_wrap">
								<div class="review_information">
									<div class="review_id">${ member_review.user_id }</div>         
									
									<!-- 별점 -->
									<div class="star">
										${ member_review.review_score }
										<img src="/Images/main/star_${ member_review.review_score }_img.png" alt="별점">
									</div>					
									
									<div class="review_date">
										<fmt:formatDate value = "${ member_review.post_date }" pattern="yyyy-MM-dd"/>
									</div>
								</div>

								<div class="review_content">${ member_review.post_content }</div>                            
							</div>
						</div>
					</div>
				</c:forEach>
				
				<!-- 데이터가 없을 때 표시할 텍스트 -->
				<c:if test="${ member_review == null or fn:length( member_review ) == 0 }">
					<div class="row-group">
						<div class="cell">
							작성된 리뷰가 없습니다.
						</div>
					</div>
				</c:if>
				
				<!-- 리뷰 페이징 인덱스 -->
				<div class="paging_button">
					<button class="button paging-direction" type="button" onclick="getListOfReview( 1 )"
						<c:if test="${reviewPagination.hasPreviousPage == false}">disabled</c:if>>
						처음
					</button>
					
					<button class="button paging-direction" type="button"	onclick="getListOfReview(
						<c:out value='${reviewPagination.currentPage}' />
						<c:if test='${reviewPagination.hasPreviousPage == true}'>-1</c:if>
						)"
						<c:if test="${reviewPagination.hasPreviousPage == false}">disabled</c:if>>
						이전
					</button>
					
					<c:forEach
						begin="${reviewPagination.firstPage}" 
						end="${reviewPagination.lastPage}" 
						var="index">
						
						<button type="button" class="button page-number <c:if test="${reviewPagination.currentPage == index}">selected</c:if>"
							onclick="getListOfReview( <c:out value='${index}' /> )" 
							<c:if test="${reviewPagination.currentPage eq index}">
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

					<button class="button paging-direction" type="button" onclick="getListOfReview(
						<c:out value='${reviewPagination.currentPage}' />
						<c:if test='${reviewPagination.hasNextPage == true}'>+1</c:if>
						)"
						<c:if test="${reviewPagination.hasNextPage == false}">
							disabled
						</c:if>>
						다음
					</button> 

					<button class="button paging-direction" type="button" onclick="getListOfReview( 
						<c:out value='${reviewPagination.totalNumberOfPages}'/> )"
						<c:if test="${reviewPagination.hasNextPage == false}">
							disabled
						</c:if>>
						마지막
					</button>
				</div>
				
			</div>
		</div>

		<!--*************-->
		<!--* 문의 섹션 *-->
		<!--*************-->
		<div id="section5"></div>
		<div class="section5" id="section_inquiry">	
			<!-- 문의 건수 & 작성하기 버튼 -->
			<div class="table_count">
				<div class="inquiry_header">
					상품문의 &nbsp;<span class="count_number"><c:out value="${ totalInquiryCount }"/></span>건
				</div>
				
				<!-- 문의하기 버튼 -->  
				<button type="button" class="inquiry_button"
				onclick="javascript:inquiry_button();">문의하기</button> 
			</div>
			
			<div class="inquiry_table">	
				<!-- 문의 컨텐츠 -->	
				<c:forEach var = "product_qna" items = "${ product_qna }" varStatus="status">
					<div class="accordion-item">
						<!-- 문의 아코디언 -->
						<h2 class="accordion-header" id="heading${status.index}">
							<button class="accordion-button" type="button" data-bs-toggle="collapse"
								data-bs-target="#collapse${status.index}" aria-expanded="true" aria-controls="collapse${status.index}">
								<div class="inquiry_tr">
									<div class="inquiry_td">
										${ product_qna.user_id }&emsp;											
										<fmt:formatDate value="${ product_qna.inquiry_date }" pattern="yyyy-MM-dd"/>
									</div>
									<div class="inquiry_content">${ product_qna.inquiry_content }</div>
								</div>
							</button>
						</h2>

						<c:if test="${ product_qna.admin_id != null  }">						
							<!-- 답변 아코디언 -->
							<div id="collapse${status.index}" class="accordion-collapse collapse" aria-labelledby="heading${status.index}"
								data-bs-parent="#accordionExample">
								<div class="accordion-body">
									<div class="inquiry_tr">
										<div class="inquiry_td">
											${ product_qna.admin_id }&emsp;
											<fmt:formatDate value="${ product_qna.reply_date }" pattern="yyyy-MM-dd"/>
										</div>
										<div class="inquiry_content">${ product_qna.reply_content }</div>
									</div>
								</div>
							</div>
						</c:if>	
					</div>
				</c:forEach>
			</div>
				
			<!-- 데이터가 없을 때 표시할 테스트 -->
			<c:if test="${ product_qna == null or fn:length( product_qna ) == 0 }">
				<div class="row-group">
					<div class="cell">
						작성된 상품문의가 없습니다.
					</div>
				</div>
			</c:if>
			
			<!-- 문의 페이징 인덱스 -->
			<div class="paging_button">
				<button class="button paging-direction" type="button" onclick="getListOfInquiry( 1 )"
					<c:if test="${inquiryPagination.hasPreviousPage == false}">disabled</c:if>>
					처음
				</button>
				
				<button class="button paging-direction" type="button"	onclick="getListOfInquiry(
					<c:out value='${inquiryPagination.currentPage}' />
					<c:if test='${inquiryPagination.hasPreviousPage == true}'>-1</c:if>
					)"
					<c:if test="${inquiryPagination.hasPreviousPage == false}">disabled</c:if>>
					이전
				</button>
				
				<c:forEach
					begin="${inquiryPagination.firstPage}" 
					end="${inquiryPagination.lastPage}" 
					var="index">
					
					<button type="button" class="button page-number <c:if test="${inquiryPagination.currentPage == index}">selected</c:if>"
						onclick="getListOfInquiry( <c:out value='${index}' /> )" 
						<c:if test="${inquiryPagination.currentPage eq index}">
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

				<button class="button paging-direction" type="button" onclick="getListOfInquiry(
					<c:out value='${inquiryPagination.currentPage}' />
					<c:if test='${inquiryPagination.hasNextPage == true}'>+1</c:if>
					)"
					<c:if test="${inquiryPagination.hasNextPage == false}">
						disabled
					</c:if>>
					다음
				</button> 

				<button class="button paging-direction" type="button" onclick="getListOfInquiry( 
					<c:out value='${inquiryPagination.totalNumberOfPages}'/> )"
					<c:if test="${inquiryPagination.hasNextPage == false}">
						disabled
					</c:if>>
					마지막
				</button>
			</div>
				
		</div>		
		
		<!--***********************-->
		<!--* 배송/교환/환불 섹션 * -->
		<!--***********************-->
		<div id="section6"></div>
		<div class="section6">
			<h3>배송/교환/환불</h3>
			<div>
				[배송] <br>
				배송 일반택배
				<br>
				배송비 각 상품별 상이
				<br>
				배송불가지역 배송 불가 지역이 없습니다.
				<br>
				비례배송비 주문 상품 개수에 비례하여 배송비 부과
				
				<br>
				<br>
				[교환/환불]
				<br>
				반품 배송비 - 각 상품별 배송비
				<br>
				교환 배송비 - 각 상품별 배송비
				<br>
				보내실 곳 서울 특별시 노원구 상계로 64 화랑빌딩 4F 401호
			</div>
		</div>
	</div>
</div>

<script>

    function inquiry_button(){  
        window.open("product_inquiry_popup_write?product_code=${product_info.product_code}", "1:1문의", "width=500, height=500, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );  
    }
    
	// 리뷰 페이징 - 리뷰 테이블만 새로고침
	function getListOfReview(reviewCurrentPage) {
	
		var product_code = $('#product_code').val();
	 
		$.ajax({
			type: 'POST',
			url : "/productDetail?product_code=" + product_code, //전송할 url
			data: {
				"reviewCurrentPage" : reviewCurrentPage // 전송할 데이터
	    	},
			success: function (data) {
				let findTable = $("#section_review").html(data).find('#section_review');
				$('#section_review').html(findTable);
	      
				history.pushState(null, null, "productDetail");
			}
		});
	}
	
	// 문의 페이징 - 문의 테이블만 새로고침
	function getListOfInquiry(inquiryCurrentPage) {
	
		var product_code = $('#product_code').val();
	 	
		$.ajax({
			type: 'POST',
			url : "/productDetail?product_code=" + product_code, //전송할 url
			data: {
				"inquiryCurrentPage" : inquiryCurrentPage // 전송할 데이터
			},
			success: function (data) {
				let findTable = $("#section_inquiry").html(data).find('#section_inquiry');
				$('#section_inquiry').html(findTable);
				
				history.pushState(null, null, "productDetail");
			}
		});
	}	
</script>