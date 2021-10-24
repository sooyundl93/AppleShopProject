<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<section class="post_view">
  <div class="post_title">
    <span>
      <c:choose>
        <c:when test="${ data.important_flag eq 'Y'}">
          [중요]
        </c:when>
        <c:otherwise>
          [일반]
        </c:otherwise>
      </c:choose>  
      <c:out value="${ data.post_title }" />
    </span>
    <span class="post_info">
      <span>글쓴이 : <c:out value="${ data.user_id }" /></span>
      <span>글번호 : <c:out value="${ data.post_no }" /></span>
      <span>조회수 : <c:out value="${ data.post_hits }" /></span>
    </span>
  </div>
  <div class="post_content">
    <figure class="media"><oembed url="https://youtu.be/CscNLbjRFgo"></oembed></figure>
    ${ data.post_content }
  </div>
  <div class="button_container">
    <span>
      <button type="button" class="brand-pink" onclick="location.href='./edit?post_no=${ data.post_no }'">
        수정
      </button>
      <button type="button" class="brand-pink" onclick="location.href='./delete_post?post_no=${ data.post_no }'">
        삭제
      </button>
    </span>

    <button type="button" class="brand-mint" onclick="location.href='../notice_list'">
      목록
    </button>
  </div>
  <div class="nearby_posts">
    <div class="prev_post">
      <span><img class="generated_next_sign"></img>이전글</span>
      <c:choose>
        <c:when test="${ not empty nearbyPosts.PREV_NO }">
          <a href="./detail?post_no=${ nearbyPosts.PREV_NO }">${ nearbyPosts.PREV_TITLE }</a>
        </c:when>
        <c:otherwise>
          <span class="empty">이전 글이 없습니다.</span>
        </c:otherwise>
      </c:choose>
    </div>
    
    <div class="next_post">
      <span><img class="generated_next_sign"></img>다음글</span>
      <c:choose>
        <c:when test="${ not empty nearbyPosts.NEXT_NO }">
          <a href="./detail?post_no=${ nearbyPosts.NEXT_NO }">${ nearbyPosts.NEXT_TITLE }</a>
        </c:when>
        <c:otherwise>
          <span class="empty">다음 글이 없습니다.</span>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</section>
