<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>내일의 집:회원탈퇴</title>
    
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/common.css">    
    
</head>

<body>
    <div id="container">
        <!-- 회원탈퇴 폼 -->
        <form action="withDrawAction">    
            <div class="login password_change_table">  
                <div class="page_title">
                    <img src="Images/main/logo_icon.png" alt="로고아이콘">
                    <h2>회원탈퇴</h2>
                </div>     

                <!-- 비밀번호 -->
                <div class="login_form">
                    <div class="th">비밀번호</div>
                    <div class="td">
                        <div class="upper">
                            <input type="password" name="user_password" id="">
                            <input type="hidden" value="${user_id}" name="user_id" readonly>
                        </div>
                        <!-- 회원탈퇴 동의 확인 -->	           
                        <label>                             
                            <input type="checkbox" name="" id="" required>
                            &nbsp;탈퇴하시면 적립 마일리지는 모두 소멸됩니다.<br>
                            &emsp;&nbsp;정말 탈퇴하시겠습니까?
                        </label>
                    </div>                        
                </div>

                <!-- 변경 버튼 -->
                <div class="finish_button">
                    <input type="submit" class="btn" id="submit" value="회원탈퇴">
                </div>   
            </div>
        </form>     
    </div>

</body>

</html>