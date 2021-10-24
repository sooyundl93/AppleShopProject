<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <!--********************-->
        <!--* 상품 문의글 수정 *-->
        <!--********************-->
        <form action="product_inquiry_edit_action">
            <div class="table">
                
                <div class="tr">
                    <div class="th">상품명</div>
                    <div class="td">${ product_name }</div>
                    <input type="hidden" name="inquiry_no" value="${ dto2.inquiry_no }" />
                    <input type="hidden" name="user_id"  value="$ { dto2.user_id }" />
                </div>
                
                <div class="tr">
                    <div class="th">내용</div>
                    <div class="td">
                        <textarea id="" 
	                        cols="40" rows="15"
	                        name="inquiry_content" maxlength="1000"
	                        placeholder="1000자 이내로 입력해주세요" 
	                        required>
	                        ${ dto2.inquiry_content }
                        </textarea>
                    </div>
                </div>
                
            </div>
	        
	        <!-- 비밀글 설정 -->
	        <div class="secret">
	        	<input type="checkbox" name="private_flag" id="" value="Y">
	        	<label for="">비밀글 설정</label>
        	</div>
            
        	<!-- 수정 버튼 -->
            <div class="submit_button">
                <input type="submit" class="btn_blue" value="수정">
            </div>
        </form>
    </div>    
</body>

</html>