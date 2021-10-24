<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/css/login.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/order.css">
<link rel="stylesheet" href="/css/cscenter.css">

<!--------------------------------------- MAIN -------------------------------------->
<div id="container">
    <!---------------------------------- page_title ------------------------------------>
    <div class="page_title">           
        <h2>장바구니</h2>
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
    
    <div class="content">        
        <!-- 사이드바 -->
        <div class="aside">
            <div class="aside_menu"><a href="order_list" >주문목록/배송조회</a></div>
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
            <div class="join3 section">
                <!-- 빈 장바구니 이미지 -->
                <div class="character">
                    <img src="Images/main/cart_noting.png" alt="빈 장바구니 이미지">
                </div>
                <h2>장바구니에 담긴 상품이 없습니다.</h2>
                <h5>마음에 드는 상품을 장바구니에 추가해보세요!</h5>

                <div class="blank_space"></div>
            
                <div class="finish_button">
                    <button class="btn" onclick = "location.href='product_list'">쇼핑하러 가기</button>
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
