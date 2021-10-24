<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.List"%>
<%@ page import="com.study.springboot.dto.Review_image_infoDto" %>
<%@ page import="java.util.ArrayList"%>

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

	<!--******************-->
	<!--* 리뷰 조회 팝업 *-->
	<!--******************-->
    <div class="review_popup">
        
        <div class="page_title">
            <h2>상품리뷰<br>-</h2>
        </div>

        <div class="review">
    	
            <!-- 이미지 -->
            <div class="imgwrap">
                <div class="review_img">
                    <c:forEach var="dto3" items="${ list }">
                        <img src="review/${ dto3.image_file_name }" alt="리뷰 이미지">
                    </c:forEach>
                </div>
            </div>
            
            <div class="textwrap">
                <div class="text_title">
                    <!-- 아이디 -->
                    <div class="text_id">${ dto2.user_id }</div>                        
                    <!-- 별점 -->
                    <div class="star">${ dto2.review_score }</div>                        
                    <!-- 작성 날짜 -->
                    <div class="text_date">
                        <fmt:formatDate value="${ dto2.post_date }" pattern="yyyy.MM.dd"/>
                    </div>
                </div>
                
                <!-- 리뷰 내용 -->
                <div class="text_content">
                    ${ dto2.post_content }
                </div>
                
                <!-- 글 수정/삭제 버튼 -->
                <div class="popup">
                    <div class="review_button">
                        <button onclick="location.href='product_review_popup_edit?post_no=${ dto2.post_no }'" 
                        class="btn_blue">글수정</button>
                        <button onclick="location.href='product_review_delete2?post_no=${ dto2.post_no }'" 
                        class="btn_line">삭제</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
	    
	<script>
		var star = $('.star');
		
		if(star[0].innerText=='0.5'){
			var result = star[0].innerHTML.replace('0.5','<img src="images/main/star_0.5_img.png">')
			
			star[0].innerHTML = result
		} 	 
		 else if(star[0].innerText=='1.0'){
			var result = star[0].innerHTML.replace('1.0','<img src="images/main/star_1.0_img.png">')
			
			star[0].innerHTML = result
		}
		else if(star[0].innerText=='1.5'){
			var result = star[0].innerHTML.replace('1.5','<img src="images/main/star_1.5_img.png">')
			
			star[0].innerHTML = result
		}
		else if(star[0].innerText=='2.0'){
			var result = star[0].innerHTML.replace('2.0','<img src="images/main/star_2.0_img.png">')
			
			star[0].innerHTML = result
		}
		else if(star[0].innerText=='2.5'){
			var result = star[0].innerHTML.replace('2.5','<img src="images/main/star_2.5_img.png">')
			
			star[0].innerHTML = result
		}
		else if(star[0].innerText=='3.0'){
			var result = star[0].innerHTML.replace('3.0','<img src="images/main/star_3.0_img.png">')
			
			star[0].innerHTML = result
		}
		else if(star[0].innerText=='3.5'){
			var result = star[0].innerHTML.replace('3.5','<img src="images/main/star_3.5_img.png">')
			
			star[0].innerHTML = result
		}
		else if(star[0].innerText=='4.0'){
			var result = star[0].innerHTML.replace('4.0','<img src="images/main/star_4.0_img.png">')
			
			star[0].innerHTML = result
		}
		else if(star[0].innerText=='4.5'){
			var result = star[0].innerHTML.replace('4.5','<img src="images/main/star_4.5_img.png">')
			
			star[0].innerHTML = result
		}
		else if(star[0].innerText=='5.0'){
			var result = star[0].innerHTML.replace('5.0','<img src="images/main/star_5.0_img.png">')
			
			star[0].innerHTML = result
		}
	</script>
	
</body>

</html>