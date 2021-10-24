<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.List"%>
<%@ page import="com.study.springboot.dto.Order_infoDto" %>
<%@ page import="java.util.ArrayList"%>

<%
	List<Order_infoDto> list = (List<Order_infoDto>)request.getAttribute("list");
	System.out.println( "list count:" + list.size() );
	
	String list_page = (String)request.getAttribute("page");
	System.out.println( "list_page:" + list_page );
	
	String page1_active = "";
	String page2_active = "";
	String page3_active = "";
	
	if(list_page.equals("1")) 
		page1_active = "active";
	if(list_page.equals("2")) 
		page2_active = "active";
	if(list_page.equals("3")) 
		page3_active = "active";
%>
	
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/cscenter.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		
<!--------------------------------------- MAIN -------------------------------------->
<div id="container">

    <!---------------------------------- page_title ------------------------------------>
    <div class="page_title">                    
        <h2>마이페이지</h2>
        <div class="breadcrumb">
            <div class="location_content">
                <!-- <span>${ uri_name}</span> -->
                <span>마이페이지</span>       
                <span class="location_arrow">&gt; </span>
                <span class="current">주문목록/배송조회</span>
            </div>
        </div>
        <div class="line"></div>
    </div>
    
    <div class="content">
        
        <!-- 사이드바 -->
        <div class="aside">
            <div class="aside_menu"><a href="order_list" class="current">주문목록/배송조회</a></div>
            <div class="aside_menu"><a href="product_inquiry">상품문의</a></div>
            <div class="aside_menu"><a href="product_review">상품리뷰</a></div>
            <div class="aside_menu"><a href="cart">장바구니</a></div>
            <div class="aside_menu"><a href="one2one_inquiry">1:1문의</a></div>
            <div class="aside_menu"><a href="mypage_change">회원정보변경</a></div>
            <div class="aside_menu"><a href="password_change">비밀번호변경</a></div>
            <div class="aside_menu"><a href="#" class="withdraw" onclick="javascript:withDraw();">회원탈퇴</a></div>
        </div>
        
        <!--------------------------------------- board -------------------------------------->
        <div class="board order">      
                            
            <!-- 프로필 -->
            <div class="profile_container">
                <div class="profile">
                    <div class="profile_img">
                        <img src="Images/main/profile_icon.png" alt="프로필 이미지">
                    </div>                    
                    <div class="profile_text">
                        ${ dto3.user_name }님
                    </div>
                </div>
                
                <!-- 보유 마일리지 -->
                <div class="profile">
                    <div class="profile_img">
                        <img src="Images/main/icon_mileage.png" alt="마일리지 이미지">
                    </div>
                    <div class="profile_text">
                        마일리지 ${ dto3.mileage }원
                    </div>
                </div>
            </div>

            <!-- 주문 상태 -->
            <div class="order_table">
                <div class="order_tr">
                    <div class="order_th">입금대기</div>
                    <div class="order_td status">${ order_status1 }</div>
                </div>
                
                <div class="order_tr">
                    <div class="order_th">입금완료</div>
                    <div class="order_td status">${ order_status2 }</div>
                </div>
                
                <div class="order_tr">
                    <div class="order_th">배송완료</div>
                    <div class="order_td status">${ order_status3 }</div>
                </div>
                
                <div class="order_tr">
                    <div class="order_th">취소</div>
                    <div class="order_td status">${ order_status4 }</div>
                </div>
                
                <div class="order_tr">
                    <div class="order_th">교환</div>
                    <div class="order_td status">${ order_status5 }</div>
                </div>
                
                <div class="order_tr">
                    <div class="order_th">반품</div>
                    <div class="order_td status">${ order_status6 }</div>
                </div>
            </div>
                        
            <!-- 기간별 조회 -->
            <form action="order_list_search" method="post">
                <div class="search_date">
                    <div class="date_title">조회기간</div>
                    
                    <div class="period">
                        <button type="button" data-value="0" id="r_today" name="today">오늘</button>
                        <button type="button" data-value="7" id="r_week" name="week">일주일</button>
                        <button type="button" data-value="15" id="r_last15" name="last15">15일</button>
                        <button type="button" data-value="30" id="r_month" name="month">1개월</button>
                        <button type="button" data-value="90" id="r_month3" name="month3">3개월</button>
                        <button type="button" data-value="365" id="r_lastyear" name="lastyear">1년</button>
                    </div>
                    
                    <div class="datepicker">
                        <input type="hidden" name="page" value="1" >
                        <input type="text" id="start_datepicker" name="start_date" required>
                        <span>~</span>
                        <input type="text" name="end_date"  id="end_datepicker" required>
                        <!-- 조회 버튼 -->  
                        <input type="submit" name="" id="submit" class="button" value="조회">
                    </div>
                </div>
            </form>
            
            <!-- 주문 내역 -->
            <div class="contents" id="contents">  
                <!-- 목록 카운트 -->  
                <div class="table_count">
                    <div class="table_caption" id="table_caption">
                        주문목록/배송조회 &nbsp;<strong class="count_number"><c:out value="${ count2 }"/></strong>건
                    </div>              
                </div>
                
                <div class="table">
                    <div class="th">
                        <div class="order_date">날짜</div>
                        <div class="order_number">주문번호</div>
                        <div class="order_product">상품명</div>
                        <div class="order_price">가격</div>
                        <div class="order_status">상태</div>
                        <div class="order_review">리뷰</div>
                    </div>
                    <div id="none" style="display:flex;justify-content:center;">
                    </div>
                    <c:forEach var="dto2" items="${ list }">
                        <div class="td">                            
                            <!-- 주문 날짜 -->
                            <div class="order_date">
                                <fmt:formatDate value="${ dto2.order_date }" pattern="yyyy.MM.dd"/>
                            </div>   

                            <!-- 주문 번호 -->
                            <div class="order_number">
                                <a href="#"
                                onclick="window.open('order_detail?order_id=${dto2.order_id}','window_name', 'width=800,height=500,location=no,status=no,scrollbars=yes');">${ dto2.order_id }</a>
                            </div>
                            
                            <!-- 상품명 -->
                            <div class="order_product">${ dto2.product_name }</div>
                            
                            <!-- 실제 결제 금액 -->
                            <div class="order_price">
                                <fmt:formatNumber value="${ dto2.discounted_price }" pattern="#,###" />원
                            </div>
                            
                            <!-- 주문 상태 -->
                            <div class="order_status">${ dto2.order_status }</div>
                            
                            <!-- 리뷰 작성 -->
                            <div class="order_review">
                                <c:choose>
                                    <c:when test="${ dto2.wrote_review_flag eq 'Y' }">
                                        리뷰작성완료
                                    </c:when>
                                    <c:when test="${ dto2.wrote_review_flag eq 'N' && dto2.order_status eq '3' }">
                                        <button class="review_button"
                                        onclick="window.open('product_review_popup_write?product_code=${dto2.product_code}&order_id=${dto2.order_id}','window_name','width=800,height=500,location=no,status=no,scrollbars=yes');">리뷰작성</button>
                                    </c:when>
                                </c:choose>
                            </div>
                            
                        </div>
                    </c:forEach>
                </div>
                
                <div class="product_gallery">
                    <c:forEach var="i" begin="1" end="${ count }">
                        <a href="order_list_search?page=${i}&start_date=${startdate}&end_date=${enddate}" class="paging">${ i }</a>&nbsp;&nbsp;
                    </c:forEach>
                </div>
            </div>            
        </div>        
    </div>    
</div>

<script>
    $(function () {
        $('#start_datepicker').datepicker({
            lang: 'ko',
            dateFormat: 'yy-mm-dd',
            monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], //달력의 월 부분 텍스트
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], //달력의 월 부분 Tooltip
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], //달력의 요일 텍스트
            dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'] //달력의 요일 Tooltip
        });
    })
    
    $(function () {
        $('#end_datepicker').datepicker({
            lang: 'ko',
            dateFormat: 'yy-mm-dd',
            monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], //달력의 월 부분 텍스트
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], //달력의 월 부분 Tooltip
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], //달력의 요일 텍스트
            dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'] //달력의 요일 Tooltip
        });
    })
</script>
<script>
    window.onload = function () {
        if("${startdate}"==""){
            $(".paging").hide();
        }
    }
    function withDraw(){  
        window.open("withDraw", "회원탈퇴", "width=500, height=500, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );  
    }  
</script>    

<script>

    var sat = $('.order_status');
    
    for(var i=0; i < sat.length; i++) { 
        if(sat[i].innerText=='1'){
            var result = sat[i].innerText.replace('1','입금대기')
            sat[i].innerText = result
        }
        else if(sat[i].innerText=='2'){
            var result = sat[i].innerText.replace('2','입금완료')
            sat[i].innerText = result
        }
        else if(sat[i].innerText=='3'){
            var result = sat[i].innerText.replace('3','배송완료')
            sat[i].innerText = result
        }
        else if(sat[i].innerText=='4'){
            var result = sat[i].innerText.replace('4','취소')
            sat[i].innerText = result
        }
        else if(sat[i].innerText=='5'){
            var result = sat[i].innerText.replace('5','교환')
            sat[i].innerText = result
        }
        else if(sat[i].innerText=='6'){
            var result = sat[i].innerText.replace('6','반품')
            sat[i].innerText = result
        }
    }

    /* 날짜 객체 받아서 문자열로 리턴하는 함수 */
    function getDateStr(myDate) {
        return (myDate.getFullYear() + '-' + (myDate.getMonth() + 1) + '-' + myDate.getDate())
    }

    /* 오늘 날짜를 문자열로 반환 */
    function today() {
        var d = new Date()
        
        return getDateStr(d)
    }

    /* 오늘로부터 1주일전 날짜 반환 */
    function lastWeek() {
        var d = new Date()
        var dayOfMonth = d.getDate()
        
        d.setDate(dayOfMonth - 7)
        
        return getDateStr(d)
    }

    /* 오늘로부터 1개월전 날짜 반환 */
    function last15() {
        var d = new Date()
        var dayOfMonth = d.getDate()
        
        d.setDate(dayOfMonth - 15)
        
        return getDateStr(d)
    }

    function lastMonth() {
        var d = new Date()
        var monthOfYear = d.getMonth()
        
        d.setMonth(monthOfYear - 1)
        
        return getDateStr(d)
    }
    
    function Month3() {
        var d = new Date()
        var monthOfYear = d.getMonth()
        
        d.setMonth(monthOfYear - 3)
        
        return getDateStr(d)
    }
    
    function lastyear() {
        var d = new Date()
        var monthOfYear = d.getMonth()
        
        d.setMonth(monthOfYear - 12)
        
        return getDateStr(d)
    }
    
    $(".search_date :button").click(function () {
        var rname = $(this).attr("name")
        $("#end_datepicker").val(today())
        if (rname == "today") {
            $("#start_datepicker").val(today())
        } else if (rname == "week") {
            $("#start_datepicker").val(lastWeek())
        } else if (rname == "last15") {
            $("#start_datepicker").val(last15())
        } else if (rname == "month3") {
            $("#start_datepicker").val(Month3())
        } else if (rname == "lastyear") {
            $("#start_datepicker").val(lastyear())
        } else {
            $("#start_datepicker").val(lastMonth())
        }
    })
    
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    var is = $(".count_number");
    var none = $("#none")
	if(is[0].innerText=="0"){
		none[0].innerText = "검색결과가 없습니다."
	}
</script>