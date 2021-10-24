<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<input type="hidden" class="numberOfReplies" value="${ numberOfReplies }">
<input type="hidden" class="firstIndex" value="${ firstIndex }">
<input type="hidden" class="lastIndex" value="${ lastIndex }">
<c:choose>
  <c:when test="${ not empty reply }">
    <div class="container" id="list">
      <div class="info">
        <span>
          <c:out value="${ reply.user_id }" />
        </span>
        <span>
          <c:out value="${ reply.reply_date }" />
          <button type="button" class="brand-pink" onclick="deleteReply( '${ indexOfInquiry }', '${ reply.reply_no }' )">
            삭 제
          </button>
        </span>
      </div>
      <div class="content">${ reply.reply_content }</div>
    </div>
  </c:when>
  <c:otherwise>
    <div class="empty">
      [ 등록된 답글이 없습니다. ]
    </div>
  </c:otherwise>
</c:choose>
