<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<!--*************************-->
	<!--* 상품 문의글 조회 팝업 *-->
	<!--*************************-->
    <div class="popup one2one">

        <div class="page_title">
            <h2>상품문의<br>-</h2>
        </div>

    	<!--********-->
    	<!--* 문의 *-->
    	<!--********-->
        <div class="table">
            <div class="tr">
                <div class="th">상품명</div>
                <div class="td">${ dto2.product_name }</div>
            </div>
            
            <div class="tr">
                <div class="th">작성자</div>
                <div class="td">${ dto2.user_id }</div>
            </div>
            
            <div class="tr">
                <div class="th">날짜</div>
                <div class="td">
                	<fmt:formatDate value="${ dto2.inquiry_date }" pattern="yyyy.MM.dd"/>
               	</div>
            </div>
            
            <div class="tr">
                <div class="th">내용</div>
                <div class="td">${ dto2.inquiry_content }</div>
            </div>
        </div>

		<!--********-->
		<!--* 답변 *-->
		<!--********-->
        <div class="table" id="table2">
            
            <div class="tr">
                <div class="th">작성자</div>
                <div class="td" id="admin">${ dto.user_id }</div>
            </div>
            
            <div class="tr">
                <div class="th">날짜</div>
                <div class="td">
                    <fmt:formatDate value="${ dto.reply_date }" pattern="yyyy.MM.dd"/>
                </div>
            </div>
            
            <div class="tr">
                <div class="th">내용</div>
                <div class="td">
                	${ dto.reply_content }
                </div>
            </div>
        </div>
        
        <!-- 비밀글 설정 -->
        <div class="secret">
            <input type="checkbox" name="secret_flag" id="secret_flag" value="Y">
            <label for="secret_flag">비밀글 설정</label>
        </div>
       	
        <!-- 수정 버튼 -->
        <div class="submit_button">
        	<button 
	            onclick="location.href='product_inquiry_popup_edit?inquiry_no=${ dto2.inquiry_no }'" 
	            class="btn_blue" id="modify">
                수정
            </button>
            <button 
	            onclick="location.href='product_inquiry_delete2?inquiry_no=${ dto2.inquiry_no }'" 
	            class="btn_line" id="delete">
                삭제
            </button>
       	</div>

    </div>
    
    <script>
		if(document.getElementById('admin').innerText==""){
			document.getElementById('table2').style.visibility = "collapse";
		}else { 
			// document.getElementById('delete').style.visibility = "collapse"; 
        }
	</script>      
</body>

</html>