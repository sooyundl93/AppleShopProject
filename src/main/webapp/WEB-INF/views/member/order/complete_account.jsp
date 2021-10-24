<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/order.css">
<link rel="stylesheet" href="/css/login.css">
        

<!--------------------------------------- MAIN -------------------------------------->
<div id="container">
    <!---------------------------------- page_title ------------------------------------>
    <div class="page_title">                    
        <h2>주문완료</h2>
        <div class="breadcrumb">
            <div class="location_content">
                <span>장바구니</span>       
                <span class="location_arrow">&gt; </span>
                <span>주문서작성/결제</span>
                <span class="location_arrow">&gt; </span>
                <span class="current">주문완료</span>
            </div>
        </div>
        <div class="line"></div>
    </div> 
        
    <!--********-->
    <!--* Main *-->
    <!--********-->
    <div class="item_table">
        <div class="head">주문이 완료되었습니다</div>
    
        <!--******************-->
        <!--*  주문 상품 정보 *-->
        <!--******************-->
        <c:forEach var = "indexList" items = "${ indexList }">
            <div class="item_tr">

                <!-- 이미지 -->
                <div class="item_td">
                    <c:forEach var = "imageList" items = "${ imageList }" begin = "${ indexList }" end = "${ indexList }">
                        <img src="/Products/${ imageList.image_file_name }" alt="주문한 상품 이미지">
                    </c:forEach>
                </div>

                <!-- 텍스트 -->
                <div class="item_text">

                    <c:forEach var = "orderProductList" items = "${ orderProductList }" begin = "${ indexList }" end = "${ indexList }">
                        <!-- 상품명 -->
                        <div class = "item_td">
                            ${ orderProductList.product_name }
                        </div>

                        <!-- 구입 개수 -->
                        <div class = "item_td">
                            ${ orderProductList.number_of_orders }개
                        </div>
                    </c:forEach>

                        <!-- 상품 가격 -->
                    <div class = "item_td">
                        <c:forEach var = "prodTotalPriceList"    items = "${prodTotalPriceList}" begin = "${ indexList }" end = "${ indexList }">
                            <fmt:formatNumber value="${ prodTotalPriceList }" pattern = "#,###"/>원
                        </c:forEach>
                    </div>

                </div>
            </div>
        </c:forEach>
        
        <!-- 주문 정보 -->
        <div class="order">
            <div class="order_table complete">
                
                <!-- 주문 번호 -->
                <div class="order_tr">
                    <div class="order_th">주문번호</div>
                    <div class="order_td">${ orderInfo.order_id }</div>
                </div>
                
                <!-- 주문 일자 -->
                <div class="order_tr">
                    <div class="order_th">주문일자</div>
                    
                    <div class="order_td">
                        <fmt:formatDate value="${ orderInfo.order_date }" type = "both" dateStyle = "full"/>
                    </div>
                </div>
                
                <!-- 결제 수단 -->
                <div class="order_tr">
                    <div class="order_th">결제수단</div>
                    
                    <div class="order_td">
                        <div id = "payment_method">무통장입금</div>
                    </div>
                </div>
                
                <!-- 결제 금액 -->
                <div class="order_tr">
                    <div class="order_th">결제금액</div>
                    
                    <div class="order_td">
                        <fmt:formatNumber value = "${ orderInfo.discounted_price }" pattern = "#,###"/>원
                    </div>
                </div>
            </div>
        </div>
                
        <!-- 무통장 : 입금 계좌 정보 -->
        <div class="order_table complete">
            <div class="order_th">입금계좌</div>
            
            <div class="order_td accounts">
                <strong>우체국 100000-10-100000 (주)내일의집</strong>&emsp;
                <small>*주문일로부터 2일 이내에 입금해주세요</small>
            </div>
        </div>
        
        <!-- 버튼 -->
        <div class="buttons">
            <button class="white" onclick="location.href='product_list'">쇼핑 계속하기</button>
            <button class="white" onclick="location.href='order_list'">주문목록</button>
            <button class="skyblue" onclick="location.href='index'">메인</button>
        </div>
                    
    </div>
</div>