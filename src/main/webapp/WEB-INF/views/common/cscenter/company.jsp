<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %> 

<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/cscenter.css">


<!--------------------------------------- MAIN -------------------------------------->
<div id="container">
    <!---------------------------------- page_title ------------------------------------>
    <div class="page_title">                    
        <h2>회사소개</h2>
        <div class="breadcrumb">
            <div class="location_content">
                <span>고객센터</span>       
                <span class="location_arrow">&gt; </span>
                <span class="current">회사소개</span>
            </div>
        </div>
        <div class="line"></div>
    </div>

    <!-------------------------------------- content ------------------------------------->
    <div class="content">          
        <div class="aside">
            <div class="aside_menu"><a href="noticelist">공지사항</a></div>
            <div class="aside_menu"><a href="faqList">FAQ</a></div>
            <div class="aside_menu"><a href="company" class="current">회사소개</a></div>
        </div>

        <!--------------------------------------- MAIN -------------------------------------->

        <div class="board company">

            <div class="section1">
                <div class="image"><img src="Images/main/logo_full.png" alt="내일의 집 로고"></div>
                <div class="text">
                    <p>
                        안녕하세요 <br><br>
                        (주)내일의 집은 디자인과 실용성을 모두 갖춘 <br>
                        가구들을 판매하고 있습니다. <br>
                        내일, 더 예쁜 내 집을 꿈 꾸며 방문해주신 <br>
                        모든 고객님께서 꿈을 이루실 때 까지 <br>
                        내일의 집이 함께합니다.
                    </p>
                </div>
            </div>

            <hr>
            <div class="section2">
                <p>2021 (주)내일의 집 퍼블리싱</p>
            </div>
            <hr>

            <div class="section3">
                <h2>Eunoia, 유노이아, 아름다운 생각이라는 뜻의 단어입니다.</h2> <br>
                <p>
                    팀 이름처럼 아름다운 생각을 바탕으로 <br>
                    세상을 조금 더 좋은 방향으로 이끌어 나가고 싶습니다. <br><br>
                    아무것도 몰랐던 한 사람, 한 사람이 모여 만든 팀은 <br>
                    지금 첫 발자국을 나아가고 있습니다. <br>
                    한 발자국이 쌓이는 동안 옆을 바라보면 <br>
                    같은 방향을 향하는 발자국이 함께하고 있으며 <br>
                    뒤를 돌아보면 수많은 걸음을 걸어왔음을, <br>
                    그리고 그 과정이 어느 것 하나 결코 의미 없지 않았음을.<br>
                </p>
                <div>
                    <h2>For.Team_Eunoia</h2>
                </div>
            </div>

            <hr>
            <!-- <div id="map_canvas"style="width:1200px; height:600px;"></div> -->
            <div class="map">
                <div class="map-responsive flexboxAlign">
			            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1579.3598310997272!2d127.06181142156862!3d37.65579429017764!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357cb941d9c739c5%3A0x8ecd52574c78556d!2z7J207KCg7JWE7Lm0642w66-47Lu07ZOo7YSw7ZWZ7JuQIOuFuOybkOy6oO2NvOyKpA!5e0!3m2!1sko!2skr!4v1632470524131!5m2!1sko!2skr" 
			            width="1000" height="400" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
		        </div>
                <p>주소 : 서울특별시 노원구 상계로 64 화랑빌딩 4F 401호 (주)내일의 집</p> <br>
                <p>연락처 : 1688-0000  FAX : 031-0001000</p>
            </div>

        </div>
    </div>
</div>


<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBkpMLu3fkde8sFmhZ7z0J2nFlpgvV0RdU&callback=initMap&region=kr"></script>

<script>
    var map;
    function initMap() {
        var cityHall = { lat: 37.65594597103316, lng: 127.0624363275041 };

        var defaultOptions = {
            zoom: 18,
            center: cityHall,
            disableDefaultUI: true,
            zoomControl: true
        };
        map = new google.maps.Map(document.getElementById('map'), defaultOptions);

        var marker = new google.maps.Marker({ position: cityHall, map: map });
    }
</script>