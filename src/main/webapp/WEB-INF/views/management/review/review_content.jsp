<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<section class="review_content">
  <div class="review_text">
    <div class="subject">리뷰 내용</div>
    <div class="content">${ content.post_content }</div>
  </div>
  <div class="review_images">
    <div class="subject">리뷰 이미지</div>
    <div class="content">
      <c:forEach var="imageFileName" items="${ content.imageFileList }" varStatus="loopStatus">
        <img src="/review/<c:out value='${ imageFileName }'/>" alt="리뷰 이미지">
      </c:forEach>
      <c:if test="${ empty content.imageFileList }">
        <div class="empty">
          [ 등록된 이미지가 없습니다. ]
        </div>
      </c:if>
    </div>
  </div>
</section>
