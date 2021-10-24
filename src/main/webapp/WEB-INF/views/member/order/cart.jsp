<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.List"%>
<%@ page import="com.study.springboot.dto.Shopping_cartDto_product_info_Dto_product_image_infoDto" %>
<%@ page import="java.util.ArrayList"%>
	
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/order.css">
<link rel="stylesheet" href="/css/cscenter.css">

<!--------------------------------------- MAIN -------------------------------------->
<div id="container">
        
    <!---------------------------------- page_title ------------------------------------>
    <div class="page_title">                    
        <h2>마이페이지</h2>
        <div class="breadcrumb">
            <div class="location_content">
                <span>마이페이지</span>       
                <span class="location_arrow">&gt; </span>
                <span class="current">장바구니</span>
            </div>
        </div>
        <div class="line"></div>
    </div>
    
    <div class="content">        
        <!-- 사이드바 -->
        <div class="aside">
            <div class="aside_menu"><a href="order_list">주문목록/배송조회</a></div>
            <div class="aside_menu"><a href="product_inquiry">상품문의</a></div>
            <div class="aside_menu"><a href="product_review">상품리뷰</a></div>
            <div class="aside_menu"><a href="cart" class="current">장바구니</a></div>
            <div class="aside_menu"><a href="one2one_inquiry">1:1문의</a></div>
            <div class="aside_menu"><a href="mypage_change">회원정보변경</a></div>
            <div class="aside_menu"><a href="password_change">비밀번호변경</a></div>
            <div class="aside_menu"><a href="#" class="withdraw" onclick="javascript:withDraw();">회원탈퇴</a></div>
        </div>
        
        <!--------------------------------------- board -------------------------------------->
        <div class="board notice">          
            <form method="post" name="form">            
                <div class="table cart_table">                    
                    <div class="th">
                        <div class="check">
                            <input type="checkbox" name="allCheck" id="allCheck">
                        </div>
                        <div class="info">상품정보</div>
                        <div class="count">수량</div>
                        <div class="price">상품금액</div>
                        <div class="after_price">합계금액</div>
                        <div class="post">배송비</div>
                    </div>
                    
                    <c:forEach var="dto" items="${ list }" varStatus="status">
                        <div class="td">                        
                            <!-- 상품번호 -->
                            <div class="check">
                                <input type="checkbox" name="product_code" class="chBox" value="${dto.product_code }">
                            </div>
                            
                            <!-- 상품 -->
                            <div class="info">
                                <img src="products/${dto.image_file_name }" alt="상품 이미지">
                                <div class="product">${dto.product_name }</div>
                            </div>                            
                            
                            <!-- 구매 수량 -->
                            <div class="count" id="count">${dto.number_of_products }</div>
                            <input type="hidden" class="nop" value="${dto.number_of_products }">
                            
                            <!-- 상품 금액 -->
                            <div class="price" id="price">
                                <fmt:formatNumber value="${dto.product_price }" pattern="#,###" />
                            </div>
                            
                            <input type="hidden" class="pp" value="${dto.product_price }">
                        
                            <!-- 구매 수량 * 상품 금액 -->
                            <div class="after_price" id="after_price">
                                <fmt:formatNumber value="${total_price_list[status.index] }" pattern="#,###" />
                            </div>                            
                            <input type="hidden" class="ap" value="">
                            
                            <!-- 배송비 -->
                            <div class="post">
                                <fmt:formatNumber value="${post_price_list[status.index] }" pattern="#,###" />
                            </div>                            
                            <input type="hidden" class="postp" value="${post_price_list[status.index] }">
                            <input type="hidden" value="${dto.product_code }">                            
                        </div>
                    </c:forEach>                
                </div>
                
                <!-- 장바구니 정보 종합 -->
                <div class="total">
                    <div class="total_price">합계:
                        <span id="total_price">
                            <fmt:formatNumber value="${finally_total_price }" pattern="#,###" />
                        </span>
                    </div>                    
                    <input type="hidden" class="tp" value="0">
                    
                    <div class="total_mileage">적립예정 마일리지:
                        <span id="total_mileage">
                            <fmt:formatNumber value="${finally_total_mileage }" pattern="#,###" />
                        </span>
                    </div>
                    <input type="hidden" class="tm" value="0">
                </div>
                <div class="order_button">
                    <div class="left">                    
                        <input type="submit" class="white" onclick="javascript: form.action='cart_delete_action';" value="선택상품 삭제">                
                        <button type="button" class="white"  onClick="location.href='index'">메인화면</button>     
                        <button class="skyblue" onclick="javascript: form.action='product_list';">
                            쇼핑 계속하기
                        </button>
                    </div>
                    <div class="right">
                        <input type="submit" class="pink" onclick="javascript: form.action='orderAndBuyForm';" value="상품 주문">
                    </div>
                </div>
            
            </form>
        </div>
    </div>
</div>
    
<script>
    $("#allCheck").click(function(){
        var chk = $("#allCheck").prop("checked");
        
        if(chk) {
            $(".chBox").prop("checked", true);
        } else {
            $(".chBox").prop("checked", false);
        }
    });
    
    $(".chBox").click(function(){
        $("#allCheck").prop("checked", false);
    });
    
    var total_mileage = $('#total_mileage');
    
    if(total_mileage[0].innerText=='0'){
        location.href="emptyCart";
    }
    
    function withDraw(){  
        window.open("withDraw", "회원탈퇴", "width=500, height=500, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );  
    }  
    
    
</script>