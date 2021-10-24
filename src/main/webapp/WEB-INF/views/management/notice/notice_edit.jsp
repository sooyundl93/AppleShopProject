<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<section class="post_writing">
  <div class="post_title_container">
    <select class="post_category" id="post_category">
      <option value="N">일반 공지</option>

      <c:choose>
        <c:when test="${ data.important_flag eq 'Y'}">
          <option value="Y" selected>중요 공지</option>
        </c:when>
        <c:otherwise>
          <option value="Y">중요 공지</option>
        </c:otherwise>
      </c:choose>
        
    </select>

    <input 
      type="text" id="post_title" class="post_title"
      placeholder="제목을 입력해주세요." maxlength="16" required 
      value="<c:out value='${ data.post_title }' />"/>
  </div>
    
  <textarea class="ckeditor" placeholder="내용을 입력하세요.">${ data.post_content }</textarea>
  
  <div class="button_container">
    <button 
      type="button" 
      class="brand-pink" 
      onclick="writingAction( 'editAction', '${ data.post_no }' )">
      확인
    </button>

    <button type="button" class="brand-mint" onclick="history.back()">
      취소
    </button>
  </div>
</section>
