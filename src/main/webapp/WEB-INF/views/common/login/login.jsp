<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %> 

<link rel="stylesheet" href="/css/login.css">
<link rel="stylesheet" href="/css/common.css">
        
<!--------------------------------------- MAIN -------------------------------------->
<div id="container">    
    <form action="loginAction" method="post">
        <div class="login">        
            <div class="page_title">
                <img src="Images/main/logo_icon.png" alt="로고아이콘">
                <h2>로그인</h2>
            </div>

            <!-- 아이디 입력 -->
            <div class="login_form">
                <input type="text" name="user_id" placeholder="아이디">
            </div>
            
            <!-- 비밀번호 입력 -->
            <div class="login_form">
                <input type="password" name="user_password" placeholder="비밀번호">
            </div>
            
            <!-- 아이디/비밀번호 찾기 -->
            <div class="login_find">
                <span><a href="#" onclick="javascript:idFind();">아이디 찾기</a></span>
                <span><a href="#" onclick="javascript:passwordFind();">비밀번호 찾기</a></span>
            </div>
            
            <!-- 로그인 버튼 -->
            <div class="finish_button">
                <input type="submit" class="btn" name="submit" value="로그인">
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
    function idFind(){  
        window.open("idFind", "아이디찾기", "width=440, height=640, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );  
    }  
            
    function passwordFind(){  
        window.open("passwordFind", "비밀번호찾기", "width=440, height=640, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );  
    }  
</script>