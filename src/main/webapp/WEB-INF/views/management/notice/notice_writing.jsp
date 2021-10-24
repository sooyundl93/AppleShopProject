<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="post_writing">
  <div class="post_title_container">
    <select class="post_category" id="post_category">
      <option value="N">일반 공지</option>
      <option value="Y">중요 공지</option>
    </select>
    <input 
      type="text" id="post_title" class="post_title" 
      placeholder="제목을 입력해주세요." maxlength="16" required 
      value="<c:out value='${ data.post_title }' />"/>
  </div>
  <textarea class="ckeditor" placeholder="내용을 입력하세요."></textarea>
  <div class="button_container">
    <button type="button" class="brand-pink" onclick="writingAction( 'writingAction' )">
      등록
    </button>
    <button type="button" class="brand-mint" onclick="location.href='../notice_list'">
      취소
    </button>
  </div>
</section>