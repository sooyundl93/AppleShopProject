<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page trimDirectiveWhitespaces="true" %> 

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>내일의 집</title>
	
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

	<!--********************-->
	<!--* 상품 문의글 작성 *-->
	<!--********************-->
    <div class="popup write">

        <div class="page_title">
            <h2>상품문의<br>-</h2>
        </div>

    	<form action="product_inquiry_write_action" method="post">
    	
	        <div class="table">
	        
	            <div class="tr">
	                <div class="th top">상품명</div>
	                <div class="td top">${ product_name }</div>
	            </div>
	            
	            <div class="tr">
	                <div class="th">내용</div>
	                <div class="td td_content">
	                	<textarea id="inquiry_content" name="inquiry_content" cols="40" rows="10" maxlength="1000"
                        placeholder="1000자 이내로 입력해주세요" required></textarea>
	                </div>
	            </div>
	        </div>
	        
	        <!-- 비밀글 설정 -->
	        <div class="secret">
	        	<input type="checkbox" name="private_flag" id="" value="Y">
	        	<label for="">비밀글 설정</label>
        	</div>
        	
        	<input type="hidden" id="product_code" name="product_code" value="${ product_code }"/>
        	
        	<!-- 글쓰기 버튼 -->
	        <div class="submit_button">
	        	<input type="submit" class="btn_blue" value="글쓰기">
        	</div>
	        
		</form>
    </div>
    
</body>

</html>