<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page trimDirectiveWhitespaces="true" %> 

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>내일의 집:비밀번호 찾기</title>

    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/common.css">    
</head>
<body>
    <div class="popup">
        <!--------------------------------------- MAIN -------------------------------------->  
        <form action="passwordFindAction">
            <div class="page_title">
                <img src="Images/main/logo_icon.png" alt="로고아이콘">
                <h2>비밀번호 찾기</h2>
            </div>   
            
            <!-- 아이디 입력 -->
            <div class="find_form">
                <div class="form_label">아이디</div>                
                <div class="login_form">
                    <input type="text" name="user_id">
                </div>
            </div>    
    
            <!-- 비밀번호 질문 선택 -->
            <div class="find_form">
                <div class="form_label">비밀번호 찾기 질문</div>                
                <div class="login_form">
                    <select name="pw_question">
                        <option value="01">내 보물 1호는?</option>
                        <option value="02">내가 가장 좋아하는 영화 제목은?</option>
                        <option value="03">첫 애완동물 이름은?</option>
                        <option value="04">내가 가장 존경하는 인물은?</option>
                        <option value="05">가장 기억에 남는 선생님 성함은?</option>
                        <option value="06">내가 졸업한 초등학교 이름은?</option>
                        <option value="07">내가 졸업한 고등학교 이름은?</option>
                        <option value="08">내가 처음 사용한 핸드폰 번호는?</option>
                        <option value="09">아버지 성함은?</option>
                        <option value="10">어머니 성함은?</option>
                    </select>
                </div>
            </div>  

            <!-- 답 입력 -->
            <div class="find_form">
                <div class="form_label">비밀번호 찾기 답</div>                    
                <div class="login_form">
                    <input type="text" name="pw_answer">
                </div>
            </div>

            <!-- 비밀번호 찾기 버튼 -->
            <div class="finish_button">
                <input type="submit" formaction="passwordFindAction" class="btn_blue" value="비밀번호 찾기">
            </div>
        </form>
    </div>        
	
</body>

</html>