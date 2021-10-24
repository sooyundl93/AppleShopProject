<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<div class="paginate">
  <button 
    class="paging-direction first"
    type="button"
    onclick="getListContents( 1 )"
    <c:if test="${pagination.hasPreviousPage == false}">disabled</c:if>>
    처음
  </button>

  <button 
    class="paging-direction prev"
    type="button"
    onclick="getListContents(
        <c:out value='${pagination.currentPage}' />
        <c:if test='${pagination.hasPreviousPage == true}'>-1</c:if>
      )"
    <c:if test="${pagination.hasPreviousPage == false}">disabled</c:if>>
    이전
  </button>

  <c:forEach
    begin="${pagination.firstPage}" 
    end="${pagination.lastPage}" 
    var="index">
    <button 
      type="button"
      class="page-number <c:if test="${pagination.currentPage == index}">selected</c:if>"
      onclick="getListContents( <c:out value='${index}' /> )" 
      <c:if test="${pagination.currentPage eq index}">
        disabled
      </c:if>
    > 
      <c:choose>
        <c:when test="${index == 0}">
          1
        </c:when>
        
        <c:when test="${index >= 1}">
          ${index}
        </c:when>
      </c:choose>
    </button>
  </c:forEach>

  <button 
    class="paging-direction next" 
    type="button"
    onclick="
      getListContents(
        <c:out value='${pagination.currentPage}' />
        <c:if test='${pagination.hasNextPage == true}'>+1</c:if>
      )"
    <c:if test="${pagination.hasNextPage == false}">
      disabled
    </c:if>
  >
    다음
  </button> 

  <button 
    class="paging-direction end" 
    type="button"
    onclick="getListContents( <c:out value='${pagination.totalNumberOfPages}'/> )"
    <c:if test="${pagination.hasNextPage == false}">
      disabled
    </c:if>
  >
    마지막
  </button>
</div>
