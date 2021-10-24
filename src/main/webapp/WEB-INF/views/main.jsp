<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/main.css">

<!--------------------------------------- MAIN -------------------------------------->

<div class="wrap">   
    <div id="container">
        <div class="main_visual">
        
            <!--***************-->
            <!--* 메인 캐러셀 * -->
            <!--***************-->
            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">

                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0"
                        class="active" aria-current="true" aria-label="Slide 1">
                    </button>                    
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                        aria-label="Slide 2">
                    </button>                    
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                        aria-label="Slide 3">
                    </button>
                </div>
                
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="/Images/main/slide_1.jpg" class="d-block w-100" alt="첫번째 캐러셀">
                    </div>                    
                    <div class="carousel-item ">
                        <img src="/Images/main/slide_2.jpg" class="d-block w-100" alt="두번째 캐러셀">
                    </div>                    
                    <div class="carousel-item ">
                        <img src="/Images/main/slide_3.jpg" class="d-block w-100" alt="세번째 캐러셀">
                    </div>
                </div>
                
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>  

            </div>
            
            <!--***************-->
            <!--* 공지 캐러셀 * -->
            <!--***************-->
            <div class="notice">
                <div id="page">
                    <ul id="ticker" class="ticker">
                        <c:forEach var="important_list" items="${ important_list }">
                            <li>                                  
                                <a href="noticeView?post_no=${ important_list.post_no }">
                                    <img src="/Images/main/speaker.png" alt="스피커">
                                    ${ important_list.post_title }
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            
            <!--********-->
            <!--* BEST *-->
            <!--********-->
            <div class="section">
                <div class="title">BEST</div>
                <div class="section_best">
                    <div class="image_container">
                        <div class="image_posiRel big_image">
                            <c:forEach var="top5List" items="${top5List}" begin="0" end="0" step="1">
                                <a href="productDetail?product_code=${ top5List.product_code }" class="topATag">
                                    <div class="product_image">
                                        <img src="/products/${ top5List.image_file_name }" alt="BEST제품1">
                                        
                                        <div class="product_info flex_align_center">
                                            <div><input type="hidden" value="${ top5List.product_code }"/></div>
                                            <div>${ top5List.manufacturer }</div>
                                            <div>${ top5List.product_name }</div>
                                            <div><fmt:formatNumber value="${ top5List.product_price }" pattern="#,###"/>원</div>
                                        </div>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                        
                        <div class="image_posiRel small_image">
                            <div class="image_wrap">
                                <c:forEach var="top5List" items="${top5List}" begin="1" end="1" step="1">
                                    <a href="productDetail?product_code=${ top5List.product_code }" class="topATag">
                                        <div class="product_image">
                                            <img src="/products/${ top5List.image_file_name }" alt="BEST제품2">             
                                                                
                                            <div class="product_info flex_align_center">
                                                <div><input type="hidden" value="${ top5List.product_code }"/></div>
                                                <div>${ top5List.manufacturer }</div>
                                                <div>${ top5List.product_name }</div>
                                                <div><fmt:formatNumber value="${ top5List.product_price }" pattern="#,###"/>원</div>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                            <div class="image_wrap">
                                <c:forEach var="top5List" items="${top5List}" begin="2" end="2" step="1">
                                    <a href="productDetail?product_code=${ top5List.product_code }" class="topATag">
                                        <div class="product_image">
                                            <img src="/products/${ top5List.image_file_name }" alt="BEST제품3">             
                                                                
                                            <div class="product_info flex_align_center">
                                                <div><input type="hidden" value="${ top5List.product_code }"/></div>
                                                <div>${ top5List.manufacturer }</div>
                                                <div>${ top5List.product_name }</div>
                                                <div><fmt:formatNumber value="${ top5List.product_price }" pattern="#,###"/>원</div>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                            <div class="image_wrap">
                                <c:forEach var="top5List" items="${top5List}" begin="3" end="3" step="1">
                                    <a href="productDetail?product_code=${ top5List.product_code }" class="topATag">
                                        <div class="product_image">
                                            <img src="/products/${ top5List.image_file_name }" alt="BEST제품4">             
                                                                
                                            <div class="product_info flex_align_center">
                                                <div><input type="hidden" value="${ top5List.product_code }"/></div>
                                                <div>${ top5List.manufacturer }</div>
                                                <div>${ top5List.product_name }</div>
                                                <div><fmt:formatNumber value="${ top5List.product_price }" pattern="#,###"/>원</div>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                            <div class="image_wrap">
                                <c:forEach var="top5List" items="${top5List}" begin="4" end="4" step="1">
                                    <a href="productDetail?product_code=${ top5List.product_code }" class="topATag">
                                        <div class="product_image">
                                            <img src="/products/${ top5List.image_file_name }" alt="BEST제품5">             
                                                                
                                            <div class="product_info flex_align_center">
                                                <div><input type="hidden" value="${ top5List.product_code }"/></div>
                                                <div>${ top5List.manufacturer }</div>
                                                <div>${ top5List.product_name }</div>
                                                <div><fmt:formatNumber value="${ top5List.product_price }" pattern="#,###"/>원</div>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>	
                        </div>
                        
                    </div>
                </div>
            </div>
            
            <!--*******-->
            <!--* NEW *-->
            <!--*******-->
            <div class="section">
                <div class="title">NEW</div>            
                <div class="moreBtn">
                    <button onclick="location.href='product_list'">+더보기</button>
                </div>
                
                <div class="product_item_container">  
                    <div class="product_item_contents">
                    
                        <!-- NEW 1열 -->
                        <div class="product_item">
                            <c:forEach var="new8List" items="${new8List}" begin="0" end="0" step="1">
                                <a href="/productDetail?product_code=${ new8List.product_code }">
                                    <div class="product_image_new">
                                        <img src="/products/${ new8List.image_file_name }" alt="상품 이미지">                            
                                    </div>
                                    <div class="product_about">
                                        <div class="product_maker">${ new8List.manufacturer }</div>                                    
                                        <div class="product_name">${ new8List.product_name }</div>                                    
                                        <div class="product_price">
                                            <fmt:formatNumber value="${ new8List.product_price }" pattern="#,###"/>원
                                        </div>
                                    </div>                                
                                    <input type="hidden" value="${ new8List.product_code }">
                                </a>
                            </c:forEach>
                                    
                            <c:forEach var="new8ReviewList" items="${ new8ReviewList }" begin="0" end="0" step="1">
                                <div class="product_review">
                                    리뷰 ${ new8ReviewList.count_of_review }
                                    <div class="product_star">
                                        <img src="/Images/main/star.png" alt="별점">${ new8ReviewList.average_review_score }
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        
                        <div class="product_item">
                            <c:forEach var="new8List" items="${new8List}" begin="1" end="1" step="1">
                                <a href="/productDetail?product_code=${ new8List.product_code }">
                                    <div class="product_image_new">
                                        <img src="/products/${ new8List.image_file_name }" alt="상품 이미지">                                
                                    </div>
                                    <div class="product_about">
                                        <div class="product_maker">${ new8List.manufacturer }</div>                                    
                                        <div class="product_name">${ new8List.product_name }</div>                                    
                                        <div class="product_price">
                                            <fmt:formatNumber value="${ new8List.product_price }" pattern="#,###"/>원
                                        </div>
                                    </div>                                
                                    <input type="hidden" value="${ new8List.product_code }">
                                </a>
                            </c:forEach>
                            
                            <c:forEach var="new8ReviewList" items="${ new8ReviewList }" begin="1" end="1" step="1">
                                <div class="product_review">
                                    리뷰 ${ new8ReviewList.count_of_review }
                                    <div class="product_star">
                                        <img src="/Images/main/star.png" alt="별점">${ new8ReviewList.average_review_score }
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        
                        <div class="product_item">
                            <c:forEach var="new8List" items="${new8List}" begin="2" end="2" step="1">
                                <a href="/productDetail?product_code=${ new8List.product_code }">
                                    <div class="product_image_new">
                                        <img src="/products/${ new8List.image_file_name }" alt="상품 이미지">
                                    </div>
                                    <div class="product_about">
                                        <div class="product_maker">${ new8List.manufacturer }</div>                                    
                                        <div class="product_name">${ new8List.product_name }</div>                                    
                                        <div class="product_price">
                                            <fmt:formatNumber value="${ new8List.product_price }" pattern="#,###"/>원
                                        </div>
                                    </div>                                
                                    <input type="hidden" value="${ new8List.product_code }">
                                </a>
                            </c:forEach>
                            
                            <c:forEach var="new8ReviewList" items="${ new8ReviewList }" begin="2" end="2" step="1">
                                <div class="product_review">
                                    리뷰 ${ new8ReviewList.count_of_review }
                                    <div class="product_star">
                                        <img src="/Images/main/star.png" alt="별점">${ new8ReviewList.average_review_score }
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        
                        <div class="product_item">
                            <c:forEach var="new8List" items="${new8List}" begin="3" end="3" step="1">
                                <a href="/productDetail?product_code=${ new8List.product_code }">
                                <div class="product_image_new">
                                    <img src="/products/${ new8List.image_file_name }" alt="상품 이미지">                            
                                </div>
                                <div class="product_about">
                                    <div class="product_maker">${ new8List.manufacturer }</div>                                    
                                    <div class="product_name">${ new8List.product_name }</div>                                    
                                    <div class="product_price">
                                        <fmt:formatNumber value="${ new8List.product_price }" pattern="#,###"/>원
                                    </div>
                                </div>                                
                                <input type="hidden" value="${ new8List.product_code }">
                                </a>
                            </c:forEach>
                                
                            <c:forEach var="new8ReviewList" items="${ new8ReviewList }" begin="3" end="3" step="1">
                                <div class="product_review">
                                    리뷰 ${ new8ReviewList.count_of_review }
                                    <div class="product_star">
                                        <img src="/Images/main/star.png" alt="별점">${ new8ReviewList.average_review_score }
                                    </div>
                                </div>
                            </c:forEach>
                        </div>                    
                    </div>
                    
                    <div class="product_item_contents">
                    
                        <!-- NEW 2열 -->
                        <div class="product_item">
                            <c:forEach var="new8List" items="${new8List}" begin="4" end="4" step="1">
                                <a href="/productDetail?product_code=${ new8List.product_code }">
                                    <div class="product_image_new">
                                        <img src="/products/${ new8List.image_file_name }" alt="상품 이미지">                            
                                    </div>
                                    <div class="product_about">
                                        <div class="product_maker">${ new8List.manufacturer }</div>                                    
                                        <div class="product_name">${ new8List.product_name }</div>                                    
                                        <div class="product_price">
                                            <fmt:formatNumber value="${ new8List.product_price }" pattern="#,###"/>원
                                        </div>
                                    </div>                                
                                    <input type="hidden" value="${ new8List.product_code }">
                                </a>
                            </c:forEach>
                            
                            <c:forEach var="new8ReviewList" items="${ new8ReviewList }" begin="4" end="4" step="1">
                                <div class="product_review">
                                    리뷰 ${ new8ReviewList.count_of_review }
                                    <div class="product_star">
                                        <img src="/Images/main/star.png" alt="별점">${ new8ReviewList.average_review_score }
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        
                        <div class="product_item">
                            <c:forEach var="new8List" items="${new8List}" begin="5" end="5" step="1">
                                <a href="/productDetail?product_code=${ new8List.product_code }">
                                    <div class="product_image_new">
                                        <img src="/products/${ new8List.image_file_name }" alt="상품 이미지">                            
                                    </div>
                                    <div class="product_about">
                                        <div class="product_maker">${ new8List.manufacturer }</div>                                    
                                        <div class="product_name">${ new8List.product_name }</div>                                    
                                        <div class="product_price">
                                            <fmt:formatNumber value="${ new8List.product_price }" pattern="#,###"/>원
                                        </div>
                                    </div>                                
                                    <input type="hidden" value="${ new8List.product_code }">
                                </a>
                            </c:forEach>
                            
                            <c:forEach var="new8ReviewList" items="${ new8ReviewList }" begin="5" end="5" step="1">
                                <div class="product_review">
                                    리뷰 ${ new8ReviewList.count_of_review }
                                    <div class="product_star">
                                        <img src="/Images/main/star.png" alt="별점">${ new8ReviewList.average_review_score }
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        
                        <div class="product_item">
                            <c:forEach var="new8List" items="${new8List}" begin="6" end="6" step="1">
                                <a href="/productDetail?product_code=${ new8List.product_code }">
                                    <div class="product_image_new">
                                        <img src="/products/${ new8List.image_file_name }" alt="상품 이미지">                            
                                    </div>
                                    <div class="product_about">
                                        <div class="product_maker">${ new8List.manufacturer }</div>                                    
                                        <div class="product_name">${ new8List.product_name }</div>                                    
                                        <div class="product_price">
                                            <fmt:formatNumber value="${ new8List.product_price }" pattern="#,###"/>원
                                        </div>
                                    </div>                                
                                    <input type="hidden" value="${ new8List.product_code }">
                                </a>
                            </c:forEach>
                            
                            <c:forEach var="new8ReviewList" items="${ new8ReviewList }" begin="6" end="6" step="1">
                                <div class="product_review">
                                    리뷰 ${ new8ReviewList.count_of_review }
                                    <div class="product_star">
                                        <img src="/Images/main/star.png" alt="별점">${ new8ReviewList.average_review_score }
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        
                        <div class="product_item">
                            <c:forEach var="new8List" items="${new8List}" begin="7" end="7" step="1">
                                <a href="/productDetail?product_code=${ new8List.product_code }">
                                    <div class="product_image_new">
                                        <img src="/products/${ new8List.image_file_name }" alt="상품 이미지">                            
                                    </div>
                                    <div class="product_about">
                                        <div class="product_maker">${ new8List.manufacturer }</div>                                    
                                        <div class="product_name">${ new8List.product_name }</div>                                    
                                        <div class="product_price">
                                            <fmt:formatNumber value="${ new8List.product_price }" pattern="#,###"/>원
                                        </div>
                                    </div>                                
                                    <input type="hidden" value="${ new8List.product_code }">
                                </a>
                            </c:forEach>
                            
                            <c:forEach var="new8ReviewList" items="${ new8ReviewList }" begin="7" end="7" step="1">
                                <div class="product_review">
                                    리뷰 ${ new8ReviewList.count_of_review }
                                    <div class="product_star">
                                        <img src="/Images/main/star.png" alt="별점">${ new8ReviewList.average_review_score }
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>     
            </div>   
        </div>
    </div>
</div>
                    
<!-- 너굴맨 배너 -->
<div class="banner">
    <img src="/Images/main/V2_mint50.jpg" alt="너굴맨배너"><img src="/Images/main/V2_red50.jpg" alt="너굴맨배너">
</div> 

<script>
    function tick() {
        $('#ticker li:first').slideUp(function () { $(this).appendTo($('#ticker')).slideDown(); });
    }
    setInterval(function () { tick() }, 5000);
</script>