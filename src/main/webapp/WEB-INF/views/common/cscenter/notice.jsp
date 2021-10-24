<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page trimDirectiveWhitespaces="true" %> 

<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/cscenter.css">

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
        <div class="board notice">  
        
            <!-- 검색 form -->              
            <form class="search" id="search_data" onsubmit="return false">
                <!-- 검색 옵션 -->
                <select id="search_option" name="search_option" class="drop-down">
                    <option value="all">전체</option>
                    <option value="post_title">제목</option>
                    <option value="post_content">내용</option>
                </select>
                <!-- 검색어 -->
                <input 
	                type="search" 
	                id="search_keyword" 
	                name="search_keyword" 
	                class="search_keyword input-form"
	                onKeypress="javascript:if(event.keyCode==13) {
	                    getListContents(
	                    <c:out value="${pagination.currentPage}" />,
	                    getValueOfSelector('#cntPerPage'),
	                    getValueOfSelector('#search_option'),
	                    getValueOfSelector('#search_keyword')
                )}">
                <!-- 검색 버튼 -->  
                <button 
	                class="button" type="button" 
	                onclick="getListContents(
	                <c:out value="${pagination.currentPage}" />,
	                    getValueOfSelector('#cntPerPage'),
	                    getValueOfSelector('#search_option'),
	                    getValueOfSelector('#search_keyword')
	                )">
	                검색
                </button>
            </form>
            
            <!-- 공지사항 목록 -->
            <div class="contents" id="contents"> 

                <!-- 카운트 -->                 
                <div class="table_count">
                    <div class="table_caption" id="table_caption">
                        공지사항 &nbsp;<strong class="count_number"><c:out value="${ noticeCount }" /></strong>건
                    </div>
                    
                    <select 
                        class="drop-down"
                        id="cntPerPage"
                        form="search_data"
                        name="cntPerPage"
                        onchange="getListContents(
                            <c:out value="${pagination.currentPage}" />,
                            getValueOfSelector('#cntPerPage'),
                            '<c:out value="${search_option}" />',
                            '<c:out value="${search_keyword}" />'
                        )">
                        
                        <option value="10"
                            <c:if test="${pagination.cntPerPage == '10'}">selected</c:if>>
                            10개씩
                        </option>
                        
                        <option value="20"
                            <c:if test="${pagination.cntPerPage == '20'}">selected</c:if>>
                            20개씩
                        </option>
                            
                        <option value="30"
                            <c:if test="${pagination.cntPerPage == '30'}">selected</c:if>>
                            30개씩
                        </option>
                    
                    </select>
                </div>

                <!-- 목록 -->      
                <div class="table">
                    <div class="th">
                        <div class="number">번호</div>
                        <div class="subject">제목</div>
                        <div class="date">날짜</div>
                        <div class="hit">조회</div>
                    </div>      
                    <c:forEach var="notice_flag" items="${notice_flag}">       
                        <div class="td">                                
                            <!-- 공지사항 번호 -->
                            <div class="number">
                                중요
                            </div>                                
                            <!-- 공지사항 제목 -->
                            <div class="subject">
                                <a href="noticeView?post_no=${notice_flag.post_no}">
                                    ${notice_flag.post_title}
                                </a>
                            </div>                                
                            <!-- 작성 날짜 -->
                            <div class="date">
                                ${ notice_flag.post_date }      
                            </div>                                
                            <!-- 조회수 -->
                            <div class="hit">${notice_flag.post_hits}</div>                                
                        </div>  
                    </c:forEach>
                    
                    <c:forEach var="notice_list" items="${noticeList}"> 
                        <div class="td">                                
                            <!-- 공지사항 번호 -->
                            <div class="number">
                                ${ notice_list.post_no }
                            </div>                                
                            <!-- 공지사항 제목 -->
                            <div class="subject">
                                <a href="noticeView?post_no=${notice_list.post_no}">
                                    ${notice_list.post_title}
                                </a>
                            </div>                                
                            <!-- 작성 날짜 -->
                            <div class="date">
                                ${ notice_list.post_date }                               
                            </div>                                
                            <!-- 조회수 -->
                            <div class="hit">${notice_list.post_hits}</div>                                
                        </div>
                    </c:forEach>    
                    
                    <c:if test="${ noticeList == null or fn:length( noticeList ) == 0 }">
                        <div class="td">
                            <div class="subject">
                                검색된 글이 없습니다.
                            </div>
                        </div>
                    </c:if>              
                </div>
            
                <!-- 페이징 -->      
                <div class="paging_button">   
                    <button 
                        class="button paging-direction first"
                        type="button"
                        onclick="getListContents(
                        1,
                        getValueOfSelector('#cntPerPage'),
                        '<c:out value="${search_option}" />',
                        '<c:out value="${search_keyword}" />'
                        )">
                        처음
                    </button>                                    
                    <button 
                        class="button paging-direction prev"
                        type="button"
                        onclick="getListContents(
                        <c:out value="${pagination.currentPage}" />
                        <c:if test="${pagination.hasPreviousPage == true}">-1</c:if>,
                        getValueOfSelector('#cntPerPage'),
                        '<c:out value="${search_option}" />',
                        '<c:out value="${search_keyword}" />'
                        )">
                        이전
                    </button>
                    <c:forEach 
                        begin="${pagination.firstPage}" 
                        end="${pagination.lastPage}" 
                        var="index">
                        <button 
                        class="button"
                        type="button"
                        onclick="getListContents(
                            <c:out value="${index}" />, 
                            getValueOfSelector('#cntPerPage'),
                            '<c:out value="${search_option}" />',
                            '<c:out value="${search_keyword}" />'
                        )" 
                        style="color:<c:out value="${pagination.currentPage == index ? '#cc0000; ' : ''}"/> ">
                        <c:choose>
                            <c:when test="${index == 0}">1</c:when>
                            <c:when test="${index >= 1}">${index}</c:when>
                        </c:choose>
                        </button>
                    </c:forEach>
                    <button 
                        class="button paging-direction next"
                        type="button"
                        onclick="getListContents(
                        <c:out value="${pagination.currentPage}" />
                        <c:if test="${pagination.hasNextPage == true}">+1</c:if>,
                        getValueOfSelector('#cntPerPage'),
                        '<c:out value="${search_option}" />',
                        '<c:out value="${search_keyword}" />'
                        )">
                        다음
                    </button> 
                    <button 
                        class="button paging-direction end"
                        type="button"
                        onclick="getListContents(
                        <c:out value="${pagination.lastPage}" />,
                        getValueOfSelector('#cntPerPage'),
                        '<c:out value="${search_option}" />',
                        '<c:out value="${search_keyword}" />'
                        )">
                        마지막
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>