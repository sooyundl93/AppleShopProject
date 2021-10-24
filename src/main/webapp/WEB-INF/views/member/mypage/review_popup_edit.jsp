<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.List"%>
<%@ page import="com.study.springboot.dto.Review_image_infoDto_Member_review_Dto" %>
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
	<!--* 리뷰 수정 팝업 *-->
	<!--******************-->
    <div class="review_popup">    
        <div class="page_title">
            <h2>상품리뷰<br>-</h2>
        </div>

        <form action="product_review_edit_action" method="post" enctype="multipart/form-data">
            <div class="review">    	
                <!-- 이미지 -->
                <div class="imgwrap">  
                    <div class="imgtop">
	                    <c:forEach var="dto3" items="${ list }">
                            <input type="hidden" value="${ dto3.post_no }" class="abc">
                            <img src="review/${ dto3.image_file_name }" alt="이미지">
                            <a href="review_image_deleteAction?image_no=${ dto3.image_no }&post_no=${ dto3.post_no }">삭제</a>
                        </c:forEach>
	                </div>
	
                    <!-- 이미지 업로드 -->
                    <div class="imgbottom">
                        <input type="file" name="file1" id="file1" onchange="file1click()"><br />
                        <input type="file" name="file2" id="file2" onchange="file2click()"><br />
                        <input type="file" name="file3" id="file3" onchange="file3click()"><br />
                        <input type="file" name="file4" id="file4"><br />
                    </div>
                </div>
                
                <div class="textwrap">
                    <div class="text_title">
                        <!-- 아이디 -->
                        <div class="text_id">${ product_name }</div>                        
                        <!-- 별점 -->
                        <div class="star">
                            <select name="review_score" id="select_score" onChange="changeImage(this);">
                                <option value="5">5.0</option>
                                <option value="4.5">4.5</option>
                                <option value="4">4.0</option>
                                <option value="3.5">3.5</option>
                                <option value="3">3.0</option>
                                <option value="2.5">2.5</option>
                                <option value="2">2.0</option>
                                <option value="1.5">1.5</option>
                                <option value="1">1.0</option>
                                <option value="0.5">0.5</option>
                            </select>
                            <img src="Images/main/star_5_img.png" id="score_star">
                        </div>        
                        
                        <div class="tr">
                            <input type="hidden" name="post_no" value="${ dto2.post_no }">
                            <input type="hidden" name="user_id"  value="${ dto2.user_id }">
                        </div>

                        <!-- 작성 날짜 -->
                        <div class="text_date">
                            <fmt:formatDate value="${ dto2.post_date }" pattern="yyyy.MM.dd"/>
                        </div>
                    </div>
                    
                    <!-- 리뷰 내용 -->
                    <div class="text_content">
                        <textarea cols="58" rows="8" name="post_content" maxlength="500"
                        placeholder="500자 이내로 입력해주세요" required>${ dto2.post_content }</textarea>
                    </div>
                    
                    <!-- 글 수정/삭제 버튼 -->
                    <div class="popup">
                        <div class="review_button">
                            <!-- 리뷰 수정 버튼 -->
                            <!-- <input type="submit" class="btn_blue" value="수정하기" /> -->
                            <button onclick="location.href='product_review_popup_edit?post_no=${ dto2.post_no }'" 
                            class="btn_blue">수정완료</button>
                            <button onclick="location.href='product_review_delete2?post_no=${ dto2.post_no }'" 
                            class="btn_line">삭제</button>
                        </div>
                    </div>
                </div>
            </div>          
        </form>
    </div>	
    
	    
	<script>
        function changeImage(element) {
            document.querySelector("#score_star").src="/Images/main/star_"+element.value+"_img.png"
        }
	</script>
		
    <script>
        var file1 = $('#file1');
        var file2 = $('#file2');
        var file3 = $('#file3');
        var file4 = $('#file4');
        var abc =$('.abc');
        if(file1.value==null){
            file2.hide(); 
            file3.hide(); 
            file4.hide(); 
        }
        function file1click(){
            if(abc.length==3){
                file2.hide();
            }
            if(abc.length==2){
                file2.show();
            }
            if(abc.length==1){
                file2.show();
            }
            if(abc.length==0){
                file2.show();
            }
        }
        function file2click(){
            if(abc.length==3){
                file3.hide();
            }
            if(abc.length==2){
                file3.hide();
            }
            if(abc.length==1){
                file3.show();
            }
            if(abc.length==0){
                file3.show();
            }
        }
        function file3click(){
            if(abc.length==3){
                file4.hide();
            }
            if(abc.length==2){
                file4.hide();
            }
            if(abc.length==1){
                file4.hide();
            }
            if(abc.length==0){
                file4.show();
            }
        }

        if(abc.length == 4){
            file1.hide();
            file2.hide(); 
            file3.hide(); 
            file4.hide(); 
        }
        if(abc.length == 3){
            file1.show();
            file2.hide(); 
            file3.hide(); 
            file4.hide(); 
        }
        if(abc.length==2){
            file1.show();
            file2.hide(); 
            file3.hide(); 
            file4.hide(); 
        }

        if(abc.length==1){
            file1.show();
            file2.hide(); 
            file3.hide(); 
            file4.hide(); 
        }
    </script>
</body>

</html>