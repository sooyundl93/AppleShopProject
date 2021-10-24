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
    <!---------------------------------- page_title ------------------------------------>
    <div class="page_title">                    
        <h2>회원가입</h2>
        <div class="breadcrumb">
            <div class="location_content">
                <span class="current">약관동의</span>       
                <span class="location_arrow">&gt; </span>
                <span>정보입력</span>
                <span class="location_arrow">&gt; </span>
                <span>가입완료</span>
            </div>
        </div>
        <div class="line"></div>
    </div>
    
    <form action="/join2" name="checkCheck">
        <!-- 이용약관 -->
        <div class="section">
            <p>이용약관</p>
            <div class="member_border">
                <c:import url="./common/login/agree1.jsp" />
            </div>                     
            <input type="checkbox" name="checkbox1" id="checkbox1">
            <label for="checkbox1">위 약관에 동의합니다.</label>
        </div>            

        <!-- 개인정보보호정책 -->
        <div class="section">
            <p>개인정보보호정책</p>        
            <div class="member_border">
                <c:import url="./common/login/agree2.jsp" />
            </div>                
            <input type="checkbox" name="checkbox2" id="checkbox2">
            <label for="checkbox2">위 개인정보취급방침에 동의합니다.</label>
        </div>            

        <!-- 개인정보 수집 및 이용목적 -->
        <div class="section">
            <p>개인정보의 수집 및 이용목적</p>
            <div class="member_border">
                <c:import url="./common/login/agree3.jsp" />
            </div>                
            <input type="checkbox" name="checkbox3" id="checkbox3">
            <label for="checkbox3">위 개인정보의 수집 및 이용목적에 동의합니다.</label>
        </div>            
        <input type="checkbox" id="check_all">        
        <label for="check_all">모든 약관에 동의합니다</label>
        <div class="finish_button">
            <input type="submit" class="btn" id="nextBtn" value="확인">
        </div>                  
    </form>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        $("#nextBtn").click(function () {
            if ($("#checkbox1").is(":checked") == false) {
                alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다.");
                return false;
            } else if ($("#checkbox2").is(":checked") == false) {
                alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다.");
                return false;
            } else if ($("#checkbox3").is(":checked") == false) {
                alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다.");
                return false;
            } else {
                $("#join").submit();
            }
        });
    });
    $(function(){  $("#check_all").click(function(){  
        if($("#check_all").prop("checked")) {  
            $("input[type=checkbox]").prop("checked",true);  
        } else {  $("input[type=checkbox]").prop("checked",false);}
    }) });

</script>