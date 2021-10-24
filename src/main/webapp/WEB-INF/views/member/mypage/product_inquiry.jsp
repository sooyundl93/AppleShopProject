<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List"%>

<%@ page import="com.study.springboot.dto.Product_inquiryDto" %>
<%@ page import="java.util.ArrayList"%>

<%
	List<Product_inquiryDto> list = (List<Product_inquiryDto>)request.getAttribute("list");
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
                <span class="current">상품문의</span>
            </div>
        </div>
        <div class="line"></div>
    </div>
    
    <div class="content">
        <div class="aside">
            <div class="aside_menu"><a href="order_list">주문목록/배송조회</a></div>
            <div class="aside_menu"><a href="product_inquiry" class="current">상품문의</a></div>
            <div class="aside_menu"><a href="product_review">상품리뷰</a></div>
            <div class="aside_menu"><a href="cart">장바구니</a></div>
            <div class="aside_menu"><a href="one2one_inquiry">1:1문의</a></div>
            <div class="aside_menu"><a href="mypage_change">회원정보변경</a></div>
            <div class="aside_menu"><a href="password_change">비밀번호변경</a></div>
            <div class="aside_menu"><a href="#" class="withdraw" onclick="javascript:withDraw();">회원탈퇴</a></div>
        </div>

        <!--------------------------------------- board -------------------------------------->
        <div class="board one2one">      
                            
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
            
            <!-- 기간별 조회 -->
            <form action="product_date_search" method="post">
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
                        <input type="text" id="start_datepicker" name="start_date" required >
                        <span>~</span>
                        <input type="text" name="end_date"  id="end_datepicker" required >
                        <!-- 조회 버튼 -->  
                        <input type="submit" name="" id="submit" class="button" value="조회">
                    </div>
                </div>
            </form>
                   
            <!-- 문의 내역 -->
            <div class="contents" id="contents">  
                <!-- 목록 카운트 -->  
                <div class="table_count">
                    <div class="table_caption" id="table_caption">
                        상품문의 &nbsp;<strong class="count_number"><c:out value="${ count2 }"/></strong>건
                    </div>
                    <span>최근 30일 내에 문의하신 내역입니다</span>            
                </div>
                    
                <div class="table">
                    <div class="th">
                        <div class="inquiry_date">날짜</div>
                        <div class="inquiry_subject">상품명</div>
                        <div class="inquiry_content">내용</div>
                        <div class="inquiry_answer">답변글수</div>
                        <div class="inquiry_delete"></div>
                    </div>
                    <div id="none" style="display:flex;justify-content:center;">
                    </div>
                    
                    <c:forEach var="dto2" items="${ list }">
                        <div class="td">
                        
                            <!-- 문의 날짜 -->
                            <div class="inquiry_date">
                                <fmt:formatDate value="${ dto2.inquiry_date }" pattern="yyyy.MM.dd"/>
                            </div>

                            <!-- 상품명 -->
                            <div class="inquiry_subject">${ dto2.product_name }</div>
                        
                            <!-- 문의 내용 -->
                            <div class="inquiry_content">        
                                <a href="#" 
                                onclick="window.open('product_inquiry_popup?inquiry_no=${ dto2.inquiry_no }','window_name',
                                'width=480, height=680, location=no, status=no, scrollbars=yes');">${ dto2.inquiry_content }</a>
                            </div>
                            
                            <!-- 문의글 답변 수 -->
                            <div class="inquiry_answer">${ dto2.number_of_replies }</div>
                            
                            <!-- 문의글 삭제 버튼 -->
                            <div class="inquiry_delete delete">
                                <a href="product_inquiry_delete?inquiry_no=${ dto2.inquiry_no }">
                                    <button class="button">삭제</button>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="product_gallery">
                    <c:forEach var="i" begin="1" end="${ count }">
                        <a href="product_date_search?page=${ i }&start_date=${ startdate }&end_date=${ enddate }" class="paging">${ i }</a>&nbsp;&nbsp;
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
    
    function last15() {
        var d = new Date()
        var dayOfMonth = d.getDate()
        d.setDate(dayOfMonth - 15)
        return getDateStr(d)
    }
    
    /* 오늘로부터 1개월전 날짜 반환 */
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
    
    /* 오늘로부터 1년전 날짜 반환 */
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
        }
        else {
            $("#start_datepicker").val(lastMonth())
        }
    })
    
	var is = $(".count_number");
    var none = $("#none")
	if(is[0].innerText=="0"){
		none[0].innerText = "검색결과가 없습니다."
	}
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