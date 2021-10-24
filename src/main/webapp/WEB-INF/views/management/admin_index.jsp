<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>관리자 페이지</title>
  <link rel="stylesheet" href="/css/admin.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script type="module" src="/js/Ckeditor.js"></script>
  <script type="text/javascript" src="/js/admin.js"></script>
  <script type="text/javascript" src="/js/UploadAdapter.js"></script>
</head>
<body>

  <!-- ### 메일 전송 시 로딩 화면 ### -->
  <div class="loading_screen_background">
    <div class="loading_screen_container">
      <img src="/images/management/sending_email.gif" alt="loading">
      <h2>메일을 보내는 중...</h2>
    </div>
  </div>

  <!-- ### 탑 헤더 ### -->
  <div class="top_header_background">
    <header class="top_header_container max-browser-width">
      <a href="/"><img src="/images/management/main-logo.png" class="company_logo" alt="메인 로고"></a>
      <span class="title">관리자 화면</span>
      <span>
        <a href="/logout" class="exit">로그아웃</a>
        <span class="separator">|</span>
        <a href="/index" class="exit">메인페이지</a>
      </span>
    </header>
  </div>

  <!-- ### 메인 ### -->
  <div class="max-browser-width">
    <main class="main">
      <header class="main__title">
        <h1>관리자 페이지</h1>
        <nav class="main__navigation">
        </nav>
      </header>
      <hr class="main__hr brand-pink">
      <div class="main__row-wrap">
        <!-- ### 네비 ### -->
        <nav class="menu" id="menu">
          <ul>
            <a href="/admin/member_list"><li>회원 관리</li></a>
            <a href="/admin/product_list"><li>상품 관리</li></a>
            <a href="/admin/order_list"><li>주문 관리</li></a>
            <a href="/admin/review_list"><li>회원리뷰 관리</li></a>
            <a href="/admin/notice_list"><li>공지사항 관리</li></a>
            <a href="/admin/product_inquiry_list"><li>상품문의 관리</li></a>
            <a href="/admin/one2one_inquiry_list"><li>1:1문의 관리</li></a>
          </ul>
        </nav>
      
        <!-- ### 콘텐츠 ### -->
        <article class="contents-wrap" id="contents-wrap">
          <c:import url="${ contents }" />
        </article>
      </div>
    </main>
  </div>

  <!-- ############################ 푸터 ############################ -->
  <div class="footer__background">
    <footer class="footer max-browser-width">

    </footer>
  </div>
</body>
</html>