<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<link rel="stylesheet" href="/css/footer.css">

<button onclick="topFunction()" id="topBtn" title="Go to top">
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-up" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
    </svg>
</button>


<!--------------------------------------- FOOTER -------------------------------------->
<div class="footer">
    <div class="footer_top">
        <span class="footer_menu"><a href="company">회사소개 </a></span>
        <span>|</span>
        <span class="footer_menu"><a href="termsOfUse"> 이용약관 </a></span>
        <span>|</span>
        <span class="footer_menu"><a href="personalPolicy"> 개인정보보호방침 </a></span>
        <span>|</span>
        <span class="footer_menu"><a href="faqList"> 이용안내 </a></span>
        <span>|</span>
        <span class="footer_menu"><a href="company"> 광고문의 </a></span>
    </div>

    <div class="under_line"></div>

    <div class="footer_bottom">
        <div class="footer_container">
            <!-- 로고 -->
            <div class="footer_logo">
                <a href="index"><img src="/Images/main/footer_logo.png" alt="로고" /></a>
            </div>

            <div class="footer_info">
                <p>
                    COMPANY: <span class="highlight">(주)내일의 집</span>&nbsp; 
                    &nbsp;|&nbsp; 
                    &nbsp;Team_Eunoia&nbsp;
                    &nbsp;|&nbsp;
                    &nbsp;BUSINESS LICENSE : 100-10-1000001<br />
                    <br />
                    ADDRESS : 서울특별시 노원구 상계로 64 화랑빌딩 4F 401호<br />
                    CHIEF PRIVACY OFFICE : 내일의 집 (tmrhome@tmrhome.com)<br />
                    CALL CENTER : 1688-0000 | FAX : 031-000-1000
                </p>
                
                <p class="footer_copyright">
                    @TomorrowHome.co.kr ALL RIGHT RESERVED
                </p>
            </div>
            
            <div class="footer_customer">
                <p><span class="highlight">고객센터</span></p>
                <p class="strong">1633.2931</p>
                <p>
                    평일 09:00 ~ 17:00 (주말 및 공휴일 휴무)<br />
                    <br />
                    <span class="highlight">배송기간</span><br />
                    수도권 2~3일 / 지방 5~7일(주말 및 공휴일제외)
                </p>
            </div>
        </div>        
    </div>
</div>

<script> 
//Get the button
var mybutton = document.getElementById("topBtn");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
	if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
		mybutton.style.display = "block";
	} else {
		mybutton.style.display = "none";
	}
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
	document.body.scrollTop = 0;
	document.documentElement.scrollTop = 0;
}   
</script>

</body>
</html>