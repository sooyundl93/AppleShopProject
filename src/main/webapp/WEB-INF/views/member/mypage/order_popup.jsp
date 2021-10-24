<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List"%>
<%@ page import="com.study.springboot.dto.Order_infoDto" %>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>내일의 집:주문 내역</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous">
    </script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/cscenter.css">
    
</head>

<body>
    <div class="popup order">
    
        <div class="page_title">
            <h2>주문목록<br>-</h2>
        </div>
        
        <div class="table" id="table1">
            <div class="tr"> 
                <div class="th">주문일</div>
                <div class="th">주문번호</div>
                <div class="th">주문상태</div>       
            </div>
            <div class="tr">  
                <div class="td"><fmt:formatDate value="${ dto2.order_date }" pattern="yyyy.MM.dd"/></div>
                <div class="td">${ dto2.order_id }</div>  
                <div class="td os">${ dto2.order_status }</div>
            </div>		
        </div>
		
        <div class="table" id="table2">
            <div class="tr">  
                <div class="th">가격</div>  
                <div class="th">배송비</div>    
                <div class="th">총결제금액</div>   
                <div class="th">적용 마일리지</div>
                <div class="th">적립될 마일리지</div>
            </div>
            <div class="tr">
                <div class="td"><fmt:formatNumber value="${ only_products_total_price }" pattern="#,###" />원</div>
                <div class="td"><fmt:formatNumber value="${ dto2.total_shipping_cost }" pattern="#,###" />원</div>
                <div class="td"><fmt:formatNumber value="${ dto2.total_price }" pattern="#,###" />원</div>
                <div class="td">-<fmt:formatNumber value="${ dto2.used_mileage }" pattern="#,###" />원</div>
                <div class="td"><fmt:formatNumber value="${ dto2.accrued_mileage }" pattern="#,###" />원</div>
            </div>
            <div class="tr">
                <div class="th">실결제금액</div>
                <div class="td"><fmt:formatNumber value="${ dto2.discounted_price }" pattern="#,###" /></div>
                <div class="th">결제수단</div>
                <div class="td pm">${ dto2.payment_method }</div>
            </div>
        </div>
            
        <div class="table" id="table3">
            <div class="tr">
                <div class="th">수령인</div>
                <div class="td">${ dto2.recipient_name }</div>
                <div class="th">연락처</div>
                <div class="td">${ dto2.phone_number }</div>
            </div>
            
            <div class="tr">
                <div class="th">주소</div>
            </div>
            <div class="tr">
                <div class="td">
                    ${ dto2.postal_code }&emsp;
                    ${ dto2.address }, ${ dto2.detailed_address }
                </div>
            </div>
            
            <div class="tr">
                <div class="th">배송메모</div>
            </div>
            <div class="tr">
                <div class="td">
                    ${ dto2.shipping_memo }
                </div>
            </div>
        </div>
        
        <div class = "table" id = "table4">
        	<c:forEach var="list" items="${list}">
        		<div class = "tr">
        			<div class = "th">
        				상품명
        			</div>
        			
        			<div class = "td">
        				${ list.product_name }
        			</div>
        		</div>
        	</c:forEach>        
        </div>
	</div>
	
<script>
	var os = $('.os');
	
	for(var i=0; i < os.length; i++) { 
		
		if(os[i].innerText=='1'){
			var result = os[i].innerText.replace('1','입금대기')
			os[i].innerText = result
		}
		else if(os[i].innerText=='2'){
			var result = os[i].innerText.replace('2','입금완료')
			os[i].innerText = result
		}
		else if(os[i].innerText=='3'){
			var result = os[i].innerText.replace('3','배송완료')
			os[i].innerText = result
		}
		else if(os[i].innerText=='4'){
			var result = os[i].innerText.replace('4','취소')
			os[i].innerText = result
		}
		else if(os[i].innerText=='5'){
			var result = os[i].innerText.replace('5','교환')
			os[i].innerText = result
		}
		else if(os[i].innerText=='6'){
			var result = os[i].innerText.replace('6','반품')
			os[i].innerText = result
		}
	 	
	}

	var pm = $('.pm');
	
	for(var i=0; i < pm.length; i++) { 
			
		if(pm[i].innerText=='1'){
			var result = pm[i].innerText.replace('1','신용카드')
			pm[i].innerText = result
		}
		else if(pm[i].innerText=='2'){
			var result = pm[i].innerText.replace('2','카카오페이')
			pm[i].innerText = result
		}
		else if(pm[i].innerText=='3'){
			var result = pm[i].innerText.replace('3','무통장입금')
			pm[i].innerText = result
		}
	
	}

</script>

</body>

</html>