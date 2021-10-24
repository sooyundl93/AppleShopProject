<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %> 

<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/cscenter.css">

<!--------------------------------------- MAIN -------------------------------------->
<div id="container">
    <!---------------------------------- page_title ------------------------------------>
    <div class="page_title">                    
        <h2>FAQ</h2>
        <div class="breadcrumb">
            <div class="location_content">
                <span>고객센터</span>       
                <span class="location_arrow">&gt; </span>
                <span class="current">FAQ</span>
            </div>
        </div>
        <div class="line"></div>
    </div>

    <!-------------------------------------- content ------------------------------------->
    <div class="content"> 
        <div class="aside">
            <div class="aside_menu"><a href="noticelist">공지사항</a></div>
            <div class="aside_menu"><a href="faqList" class="current">FAQ</a></div>
            <div class="aside_menu"><a href="company">회사소개</a></div>
        </div>

        <!--------------------------------------- board -------------------------------------->
        <div class="board faq">           
            <div class="table">    
                <div class="nav th">
                    <div class="tab_link display_on" data-tab="tab_1">전체</div>
                    <div class="tab_link" data-tab="tab_2">주문/결제</div>
                    <div class="tab_link" data-tab="tab_3">배송</div>
                    <div class="tab_link" data-tab="tab_4">환불/교환</div>
                    <div class="tab_link" data-tab="tab_5">회원정보변경</div>
                    <div class="tab_link" data-tab="tab_6">서비스/기타</div>
                </div>
                <!--------------------------------------- accordion -------------------------------------->
                <div class="section">
                    <div class="tab_content display_on" id="tab_1">
                        <div class="accordion" id="accordionExample">
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingOne">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                        Q. 배송은 얼마나 걸리나요?
                                    </button>
                                </h2>
                                <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                                    data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        A. 평균 2~3일 정도 소요됩니다.
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingTwo">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                        Q. 마일리지는 어떻게 사용하나요?
                                    </button>
                                </h2>
                                <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                                    data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        A. 적립된 마일리지는 적립된 시점부터 10원 단위로 사용가능합니다.?
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingThree">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                        Q. 자주하는 질문3
                                    </button>
                                </h2>
                                <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                                    data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        A. 답변3
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingFour">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                        Q. 자주하는 질문4
                                    </button>
                                </h2>
                                <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour"
                                    data-bs-parent="#accordionExample">
                                    <div class="accordion-body">                                        
                                        A. 답변4
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingFive">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                        Q. 자주하는 질문5
                                    </button>
                                </h2>
                                <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive"
                                    data-bs-parent="#accordionExample">
                                    <div class="accordion-body">                                        
                                        A. 답변5
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>  
                    <div class="tab_content" id="tab_2">
                        <div class="accordion" id="accordionExample">
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingOne">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                        Q. 배송은 얼마나 걸리나요?
                                    </button>
                                </h2>
                                <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                                    data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        A. 평균 2~3일 정도 소요됩니다.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>  
                    <div class="tab_content" id="tab_3">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingTwo">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Q. 마일리지는 어떻게 사용하나요?
                                </button>
                            </h2>
                            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    A. 적립된 마일리지는 적립된 시점부터 10원 단위로 사용가능합니다.?
                                </div>
                            </div>
                        </div>
                    </div>    
                    <div class="tab_content" id="tab_4">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingThree">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    Q. 자주하는 질문3
                                </button>
                            </h2>
                            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    A. 답변3
                                </div>
                            </div>
                        </div>
                    </div>    
                    <div class="tab_content" id="tab_5">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingFour">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    Q. 자주하는 질문4
                                </button>
                            </h2>
                            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">                                        
                                    A. 답변4
                                </div>
                            </div>
                        </div>
                    </div>    
                    <div class="tab_content" id="tab_6">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingFive">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                    Q. 자주하는 질문5
                                </button>
                            </h2>
                            <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">                                        
                                    A. 답변5
                                </div>
                            </div>
                        </div>
                    </div>  
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('.tab_link').click(function () {

        var tab_id = $(this).attr('data-tab');

        $('.tab_link').removeClass('display_on');
        $('.tab_content').removeClass('display_on');

        $(this).addClass('display_on');
        $("#" + tab_id).addClass('display_on');

    });
</script>