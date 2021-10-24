<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>내일의 집:1:1문의</title>
    
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
    <div class="popup write">

        <div class="page_title">
            <h2>1:1문의<br>-</h2>
        </div>

    	<form action="one2one_write_popup_Action" method="post">
	        <div class="table">
	            <div class="tr">
	                <div class="th">제목</div>	                
	                <div class="td">
                        <textarea 
							class="title"
	                        id="inquiry_title" 
	                        name="inquiry_title" 
	                        cols="40" rows="1" maxlength="16"
	                        placeholder="제목을 입력해주세요" required></textarea>
                        <input type="hidden" value="${product_code}" />
                	</div>                	
	            </div>
	            
	            <div class="tr">
	                <div class="th">내용</div>	                
	                <div class="td">
	                	<textarea 
	                        id="inquiry_content" 
	                        name="inquiry_content" 
	                        cols="40" rows="10" maxlength="1000"
	                        placeholder="1000자 이내로 입력해주세요" required></textarea>
	                </div>
	            </div>
	        </div>
        	
        	<!-- 확인 버튼 -->
	        <div class="submit_button">
	        	<input type="submit" class="btn_blue" value="확 인">
        	</div>
		</form>
		
    </div>
    
</body>

</html>