<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/login.css">

<!--------------------------------------- MAIN -------------------------------------->
<div id="container">
  <!---------------------------------- page_title ------------------------------------>
  <div class="page_title">
  </div>
  <div class="join3 section">
    <div class="character">
        <img src="Images/main/join_finish_icon.png" alt="회원가입 완료 이미지">
    </div>
    <h2>
      <c:out value="${ guide }" />
    </h2>
    <div class="finish_button">
        <button class="btn" onclick = "location.href = 'index'">홈으로</button>
    </div>
  </div>
</div>