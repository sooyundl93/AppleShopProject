<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %> 

<link rel="stylesheet" href="/css/cscenter.css">
<link rel="stylesheet" href="/css/common.css">


<!--------------------------------------- MAIN -------------------------------------->
<div id="container">
    <!---------------------------------- page_title ------------------------------------>
    <div class="page_title">                    
        <h2>공지사항</h2>
        <div class="breadcrumb">
            <div class="location_content">
                <span>고객센터</span>       
                <span class="location_arrow">&gt; </span>
                <span class="current">공지사항</span>
            </div>
        </div>
        <div class="line"></div>
    </div>

    <!-------------------------------------- content ------------------------------------->
    <div class="content">                       
        <div class="aside">
            <div class="aside_menu"><a href="noticelist" class="current">공지사항</a></div>
            <div class="aside_menu"><a href="faqList">FAQ</a></div>
            <div class="aside_menu"><a href="company">회사소개</a></div>
        </div>
            
        <!--------------------------------------- board -------------------------------------->
        <div class="board notice_view">          
        
            <!-- 공지사항 컨텐츠 -->
            <div class="table">                    
                <!-- top_title -->
                <div class="th">
                    <div class="number">번호</div>
                    <div class="subject">제목</div>
                    <div class="date">날짜</div>
                    <div class="hit">조회</div>
                </div>                        
                <!-- top_content -->
                <div class="td">                            
                    <!-- 공지사항 번호 -->
                    <div class="number">
                        <c:if test="${ notice_flag.important_flag eq 'Y' }" >
                            중요
                        </c:if>
                        <c:if test="${ notice_flag.important_flag eq 'N' }" >
                            ${ notice_view.post_no }
                        </c:if>
                    </div>                        
                    <!-- 공지사항 제목 -->
                    <div class="subject">${ notice_view.post_title }</div>                            
                    <!-- 작성 날짜 -->
                    <div class="date">
                        ${ notice_view.post_date }
                    </div>                            
                    <!-- 조회수 -->
                    <div class="hit">${ notice_view.post_hits }</div>
                </div>							
            </div>                         
            <!-- 공지사항 내용 -->
            <div class="td_content">
                <!-- 공지사항 이미지 -->
                <c:if test="${ notice_view_image.image_file_name != null }" >
                    <div class="td_content_img">
                        <img src="/notice/${notice_view_image.image_file_name}" alt="게시글이미지">
                    </div>
                </c:if>
                ${ notice_view.post_content }
            </div>
            
            <!-- 목록 버튼 -->
            <div class="table_button">       
                <input type="button" class="button" value="목록" onClick="location.href='noticelist'" />
            </div>   

        </div>                
    </div>      
</div>