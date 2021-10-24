<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>내일의 집</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
        crossorigin="anonymous" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <link rel="stylesheet" href="/css/header.css" />

    <script>
        function tick() {
            $("#ticker li:first").slideUp(function () {
                $(this).appendTo($("#ticker")).slideDown();
            });
        }

        setInterval(function () {
            tick();
        }, 5000);

        function press(f) {
            if (f.keyCode == 13) {
                formname.submit();
            }
        }
    </script>
    <script>
        // ### 현재 페이지의 URI를 구하는 함수 ###
        function currentURI() {
            <c:set var="URI" value="${pageContext.request.requestURI}" />;
            return "${URI}";
        }

        // ### 현재 페이지의 이름을 출력하는 함수 ###
        function currentPageName() {
            let uri = currentURI(); // 현재 URI
            let page_name; // 페이지 이름 ex) 회원 관리, 상품 관리...

            // uri 문자열에서 indexOf()함수로 해당 문자열을 찾아서 문자 위치를 리턴한다. 없으면 -1을 리턴
            if (uri.indexOf("member-list") > 0) page_name = "회원 관리";
            else if (uri.indexOf("product-list") > 0) page_name = "상품 관리";
            else if (uri.indexOf("order-list") > 0) page_name = "주문 관리";
            else if (uri.indexOf("review-list") > 0) page_name = "회원리뷰 관리";
            else if (uri.indexOf("notice-list") > 0) page_name = "공지사항 관리";
            else if (uri.indexOf("product-inquiry-list") > 0)
                page_name = "상품문의 관리";
            else if (uri.indexOf("one2one-inquiry-list") > 0)
                page_name = "1:1문의 관리";
            else page_name = "기타"; // 위 조건 중에 없으면 기타로 출력

            console.log("*** 경로 표시 설정 ***");
            console.log("URI : " + uri);
            console.log("Page name : " + page_name);

            return page_name;
        }

        // ### 현재 메뉴를 강조하는 함수
        function highlightCurrentMenu(page_name) {
            // .menu_uri는 a태그 메뉴의 클래스
            $(".menu_uri").each(function (index, item) {
                // a태그 클래스 중에 내용이 page_name과 같으면 해당 선택자에 current클래스를 추가
                if ($(this).html() == page_name) {
                    $(this).addClass("current");
                    console.log("*** 하이라이트 메뉴 설정 ***");
                    console.log("index : " + index);
                    console.log("html : " + $(this).html());
                }
            });
        }

        // ### 페이지 설정 함수 ###
        // 페이지가 로드되면 아래 함수를 실행한다.
        $(function () {
            let page_name = currentPageName();
            $("#page_name").html(page_name);
            highlightCurrentMenu(page_name);
        });
        
     
        // ### 선택자의 Value를 가져오는 함수 ###
        function getValueOfSelector(selector) {
        return $(selector).val();
        }

        // ### 테이블만 새로고침하는 함수 ###
        function getListContents(
        currentPage,
        cntPerPage,
        search_option,
        search_keyword
        ) {
            $.ajax({
            type: 'POST',
            url: 'noticelist', //전송할 url
            data: { // 전송할 데이터
                    "currentPage":currentPage,
                    "cntPerPage":cntPerPage, 
                    "search_option":search_option,
                    "search_keyword":search_keyword
            },
            success: function (data) {
                let findTable = $("#contents").html(data).find('#contents');
                $('#contents').html(findTable);
                console.log("# 콘텐츠 새로고침: 성공 #");
            },
            error: function (request, status, error) {
                console.log("# 콘텐츠 새로고침: 실패 #");
                console.log("code : " + request.status + "\n" 
                        + "message : " + request.responseText + "\n" 
                        + "error : " + error );
            }
            });
        }
    </script>
</head>

<body>
    <!--------------------------------------- header -------------------------------------->
    <div id="header" class="sticky box_shadow">
        <!-- top -->
        <div class="top">
            <!-- 로고 -->
            <div class="logo_area">
                <a href="index">
                    <img src="/Images/main/logo.png" alt="로고" />
                </a>
            </div>
            <!-- 검색바 -->
            <div class="search_area">
                <form action="product_search" class="searching_box" name="formname">
                    <input type="text" placeholder="검색어 입력" class="input_text" name="searchWord">
                    <button class="input_search_button">
                        <img src="/Images/main/search_button.png" alt="검색버튼" />
                    </button>
                </form>
            </div>            
            <!-- 로그인 상태 -->
            <div class="util_area">
                <div class="util_container">
                    <c:choose>
                        <c:when test="${ user_role eq 'ROLE_USER'}">
                            <!-- 일반회원 로그인 -->
                            <a class="util">
                                <c:out value="${user_name}"/>님
                            </a>
                            <div class="dropdown-menu">
                                <a href="cart" class="dropdown-item">장바구니</a>
                                <a href="order_list" class="dropdown-item">마이페이지</a>
                                <a href="noticelist" class="dropdown-item">고객센터</a>
                                <a href="logout" class="dropdown-item">로그아웃</a>
                            </div>
                        </c:when>
                        <c:when test="${ user_role eq 'ROLE_ADMIN' }">
                            <!-- 관리자 로그인 -->
                            <a class="util">
                                <c:out value="${user_name}"/>님
                            </a>
                            <div class="dropdown-menu">
                                <!-- 관리자 페이지 이동 -->
                                <a href="admin" class="dropdown-item">관리자 페이지</a>
                                <a href="logout" class="dropdown-item">로그아웃</a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <!-- 로그아웃 상태  -->
                            <a href="loginForm" class="util">로그인</a>
                            <span class="small">|</span>
                            <a href="join1" class="util">회원가입</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="under_line"></div>

        <!-- nav -->
        <nav class="navbar navbar-expand-lg navbar-light menu">
            <div class="container-xl">
                <a class="navbar-brand-m m-hide" href="index"><img src="Images/main/m_logo.png" alt="로고" /></a>
                <!-- 검색바 -->
                <div class="search_area m-hide">
                    <form action="product_search" class="searching_box" name="formname">
                        <input type="text" placeholder="검색어 입력" class="input_text" name="searchWord" />    
                        <button class="input_search_button">
                            <img src="/Images/main/btn_top_search.png" alt="검색버튼" />
                        </button>
                    </form>
                </div>
                <!-- 햄버거 메뉴 -->
                <button class="navbar-toggler m-hide" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <!-- <span class="navbar-toggler-icon"></span> -->
                    <img src="Images/main/menu_icon_hamburger.png" alt="메뉴">
                </button>
                <!-- collapse -->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <!-- 로그인 상태 -->
                    <div class="mj-menu02 m-hide">
                        <div class="m-loginmenu">   
                            <c:choose>
                                <c:when test="${ user_role eq 'ROLE_USER' }">
                                    <!-- 일반회원 로그인 -->
                                    <span><a href="cart" class="dropdown-item">장바구니</a></span>
                                    <span class="small">|</span>
                                    <span><a href="order_list" class="dropdown-item">마이페이지</a></span>
                                    <span class="small">|</span>
                                    <span><a href="faqList" class="dropdown-item">고객센터</a></span>
                                    <span class="small">|</span>
                                    <span><a href="logout" class="dropdown-item">로그아웃</a></span>
                                </c:when>
                                <c:when test="${ user_role eq 'ROLE_ADMIN' }">
                                    <!-- 관리자 로그인 -->
                                    <c:out value="${user_id}"/>님
                                    <span class="small">|</span>
                                    <!-- 관리자 페이지 이동 -->
                                    <a href="admin" class="util">관리자 페이지</a>
                                    <span class="small">|</span>
                                    <a href="logout" class="util">로그아웃</a>
                                </c:when>
                                <c:otherwise>
                                    <!-- 로그아웃 상태  -->
                                    <a href="loginForm">로그인</a>
                                    <span class="small">|</span>
                                    <a href="join1">회원가입</a>
                                </c:otherwise>
                            </c:choose>   
                        </div>
                    </div>
                    <div class="navbar-nav me-auto mb-2 mb-lg-0">
                        <!-- ALL -->
                        <div class="nav-item dropdown">
                            <a class="menu_uri nav-link dropdown-toggle" href="product_list" id="navbarDropdown">
                                ALL
                            </a>
                        </div>
                        <!-- 거실 -->
                        <div class="nav-item dropdown">
                            <a class="menu_uri nav-link dropdown-toggle" 
                            	href="category?parent_category=거실&child_category=쇼파" id="navbarDropdown">
                                거실
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="category?parent_category=거실&child_category=쇼파">쇼파</a></li>
                                <li><a class="dropdown-item" href="category?parent_category=거실&child_category=테이블">테이블</a></li>
                            </ul>
                        </div>
                        <!-- 주방 -->
                        <div class="nav-item dropdown">
                            <a class="menu_uri nav-link dropdown-toggle" 
                            	href="category?parent_category=주방&child_category=테이블" id="navbarDropdown">
                                주방
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="category?parent_category=주방&child_category=테이블">테이블</a></li>
                                <li><a class="dropdown-item" href="category?parent_category=주방&child_category=의자">의자</a></li>
                                <li><a class="dropdown-item" href="category?parent_category=주방&child_category=수납장">수납장</a></li>
                            </ul>
                        </div>
                        <!-- 침실 -->
                        <div class="nav-item dropdown">
                            <a class="menu_uri nav-link dropdown-toggle" 
                            	href="category?parent_category=침실&child_category=침대" id="navbarDropdown">
                                침실
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="category?parent_category=침실&child_category=침대">침대</a></li>
                                <li><a class="dropdown-item" href="category?parent_category=침실&child_category=수납장">수납장</a></li>
                                <li><a class="dropdown-item" href="category?parent_category=침실&child_category=화장대">화장대</a></li>
                            </ul>
                        </div>
                        <!-- 서재 -->
                        <div class="nav-item dropdown">
                            <a class="menu_uri nav-link dropdown-toggle" 
                            	href="category?parent_category=서재&child_category=책상" id="navbarDropdown">
                                서재
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="category?parent_category=서재&child_category=책상">책상</a></li>
                                <li><a class="dropdown-item" href="category?parent_category=서재&child_category=책장">책장</a></li>
                            </ul>
                        </div>
                        <!-- 인테리어 -->
                        <div class="nav-item dropdown">
                            <a class="menu_uri nav-link dropdown-toggle" href="interior" id="navbarDropdown">
                                인테리어
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</body>

</html>