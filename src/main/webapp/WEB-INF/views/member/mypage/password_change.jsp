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
    
    <title>내일의 집:비밀번호 변경</title>

    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/common.css">    
    
</head>

<body>            
    <div id="container">         
        <!-- 비밀번호 변경 폼 -->
        <form action="password_change_action" method="post" onsubmit="return checkValue(); return false;" >
            <div class="login password_change_table">        
                <div class="page_title">
                    <img src="Images/main/logo_icon.png" alt="로고아이콘">
                    <h2>비밀번호 변경</h2>
                </div>

                <!-- 비밀번호 입력 -->
                <div class="login_form">    
	                <div class="th">비밀번호</div>                         
                    <div class="td">
                        <input type="password" id="password1" 
	                        name="user_password" 
	                        placeholder="비밀번호" 
	                        onkeyup="passwordCheckFunction();"
	                        required pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}$">
                    </div>                    
	                <div class="td">
	                    <span>8~20자 숫자와 대소문자,특수문자를 포함해주세요</span>
	                </div>
                </div>
                
                <!-- 비밀번호 확인 입력 -->
                <div class="login_form">
	                <div class="th">비밀번호 확인</div>
	                <div class="td">
                        <input type="password" id="password2" 
                        placeholder="비밀번호 확인"
                        onkeyup="passwordCheckFunction();" required>
                    </div>
	                <div class="td">
                        <span id="checkMessage"></span>
	                </div>                    
                </div>

                <!-- 변경 버튼 -->
                <div class="finish_button">
                    <input type="submit" class="btn" id="submit" value="변경" onclick="chkPW()">
                </div>             
            </div>
        </form>   
    </div>
                    
    <!-- 너굴맨 배너 -->
    <div class="banner">
        <img src="/Images/main/V2_mint50.jpg" alt="너굴맨배너">
        <img src="/Images/main/V2_red50.jpg" alt="너굴맨배너">
    </div> 
    
    <script>
        function passwordCheckFunction() {
            var password1 = $("#password1").val();
            var password2 = $("#password2").val();
            
            if (password1 != password2) { 
                $("#checkMessage").html("비밀번호가 일치하지 않습니다.").css('color', '#30ced8');
                $("#submit").prop("disabled", true); 
            }else { 
                $("#checkMessage").html("비밀번호가 일치합니다.").css('color', '#333'); 
                $("#submit").prop("disabled", false);
            }        
        }     
    </script>

</body>

</html>

