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
    
    <title>내일의 집:아이디 찾기</title>

    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/common.css">    
</head>

<body>
    <div class="popup">
        <!--------------------------------------- MAIN -------------------------------------->  
        <form action="idFindAction">
            <div class="page_title">
                <img src="Images/main/logo_icon.png" alt="로고아이콘">
                <h2>아이디 찾기</h2>
            </div>            

            <!-- 이름 입력 -->
            <div class="login_form">
                <input type="text" name="user_name" placeholder="이름">
            </div>
            
            <!-- 이메일 입력 -->
            <div class="login_form">
                <input type="text" name="email" placeholder="이메일">
            </div>
            
            <!-- 로그인 버튼 -->
            <div class="finish_button">
                <input type="submit" class="btn_blue" value="아이디 찾기">
            </div>  
        </form>
    </div>        
</body>

</html>
                    
