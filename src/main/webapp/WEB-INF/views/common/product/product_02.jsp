<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %> 

<%@ page import="java.util.List"%>
<%@ page import="com.study.springboot.dto.Product_infoDto_Product_image_infoDto_Member_reviewDto" %>

<%
	String product_code = request.getParameter("product_code");
%>

<link rel="stylesheet" href="/css/login.css">
<link rel="stylesheet" href="/css/product.css">
<link rel="stylesheet" href="/css/cscenter.css">
<link rel="stylesheet" href="/css/common.css">


<!--------------------------------------- MAIN -------------------------------------->
<div id="container">    

    <!---------------------------------- page_title ------------------------------------>
    <div class="page_title">                    
        <h2>'${searchWord}' 검색결과</h2>
        <div class="line"></div>
    </div>
            
    <!-- 상품 개수 & 정렬 옵션 -->
    <div class="product_sort">
        <div class="total">
            총 <span class="count">${ count2 }</span>개 의 상품이 있습니다
        </div> 
    </div>

    <div class="section">
        <!-- 상품 목록 -->            
        <c:forEach var="dto" items="${list2}">
            <div class="product_item">                    
                <a href="productDetail?product_code=${ dto.product_code }">
                    <div class="product_item_image">        
                        <!-- 이미지 -->
                        <img src="products/${ dto.image_file_name }" alt="상품이미지">                                
                    </div>
                    <div class="product_about">
                        <!-- 제조사 -->
                        <div class="product_maker">${ dto.manufacturer }</div>
                        
                        <!-- 상품 이름 -->
                        <div class="product_name">${ dto.product_name }</div>                            
                        <input type="hidden" value="${ dto.parent_category }" class="parent">
                        <input type="hidden" value="${ dto.child_category }" class="child">
                        
                        <!-- 상품 가격 -->
                        <div class="product_price">
                            <fmt:formatNumber value="${ dto.product_price }" pattern="#,###" />원
                        </div>
                        
                        <!-- 리뷰 & 별점 -->
                        <div class="product_review">
                            리뷰 ${ dto.count_of_review }
                            <div class="product_star">
                                <img src="/Images/main/star.png" alt="별점" />
                                ${ dto.average_review_score }
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>            
    </div>
            
    <div class="product_gallery">        
        <c:forEach var="i" begin="1" end="${ count }">
            <script>
                function paging${i}() {
                    var url = window.location.href;
                    var params;
                    
                    // url에서 '?' 문자 이후의 파라미터 문자열까지 자르기
                    params = url.substring( url.lastIndexOf('/')+1, url.indexOf('?')+1 );
                    
                    // 파라미터 구분자("&") 로 분리
                    params = params.split("&");
                    
                    document.location.href = "/" + params + "page=${i}&searchWord=${searchWord}"
                }
            </script>
            
            <a href="#" onclick="paging${i}();"class="paging">${i} </a>
            
        </c:forEach>        
    </div>
</div>