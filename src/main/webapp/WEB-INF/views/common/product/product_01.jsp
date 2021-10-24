<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List"%>
<%@ page import="com.study.springboot.dto.Product_infoDto_Product_image_infoDto_Member_reviewDto" %>
<%@ page import="java.util.ArrayList"%>

<%
	String product_code = request.getParameter("product_code");
%>

<link rel="stylesheet" href="/css/product.css">
<link rel="stylesheet" href="/css/common.css">


<!--------------------------------------- MAIN -------------------------------------->
<div id="container">    
    <!-- URI -->
    <div class="location_select">
        <div class="home">HOME</div>            
        <div class="next">></div>            
        <div class="select"></div>            
        <div class="next">></div>            
        <div class="select"></div>
    </div>
    
    <!-- 카테고리 -->
    <div class="product_gallery_title">
        <div class="head" id="head"></div>            
        <div class="category"></div>
    </div>
    
    <!-- 상품 개수 & 정렬 옵션 -->
    <div class="product_sort">        
        <div class="total">
            총 <span class="count">${ count2 }</span>개 의 상품이 있습니다
        </div>            
        <!-- 정렬 옵션 -->
        <div class="pso">
            <div class="product_sort_option">
                <a href="#" onclick="newest();">최신순</a>
            </div>                    
            <div class="product_sort_option">
                <a href="#" onclick="star();">별점순</a>                    
            </div>                
            <div class="product_sort_option">
                <a href="#" onclick="low();">가격 낮은 순</a>
            </div>                
            <div class="product_sort_option">
                <a href="#" onclick="high();">가격 높은 순</a>
            </div>
        </div>            
    </div>
    
    <div class="section">    
        <!-- 상품 목록 -->    
        <c:forEach var="dto" items="${ list }"> 
            <div class="product_item">               
                <a href="productDetail?product_code=${ dto.product_code }">
                    <div class="product_item_image">
                        <!-- 이미지 -->
                        <img src="products/${ dto.image_file_name }" alt="상품이미지">                                
                    </div>
                    <div class="product_about">
                        <!-- 제조사 -->
                        <div class="product_maker">${ dto.manufacturer }</div>      

                        <!-- 상품 이름 -->
                        <div class="product_name">${ dto.product_name }</div>                                
                        <input type="hidden" value="${ dto.parent_category }" class="parent">
                        <input type="hidden" value="${ dto.child_category }" class="child">
                        
                        <!-- 상품 가격 -->
                        <div class="product_price">
                            <fmt:formatNumber value="${ dto.product_price }" pattern="#,###" />원
                        </div>
                    </div>    
                    <!-- 리뷰 & 별점 -->
                    <div class="product_review">
                        리뷰 ${ dto.count_of_review } 
                        <div class="product_star">
                            <img src="images/main/star.png" alt="별점 이미지" /> 
                            ${ dto.average_review_score }
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>            
    </div>
    
    <div class="product_gallery">        
        <c:forEach var="i" begin="1" end="${ count }">
            <script>
                function paging${i}()
                {
                    var url = window.location.href;
                    var params;
                    
                    // url에서 '?' 문자 이후의 파라미터 문자열까지 자르기
                        params = url.substring( url.lastIndexOf('/')+1, url.indexOf('?') );
                    
                    // 파라미터 구분자("&") 로 분리
                    params = params.split("&");
                        
                    document.location.href = "/" + params + "?page=${i}&parent_category=${parent_category}&child_category=${child_category}"
                }
            </script>                
            <a href="#" onclick="paging${i}();"class="paging">${i}</a>                
        </c:forEach>            
    </div> 
</div>

<script>
    //url
    function newest() {
        var url = window.location.href;
        var params;
        // url에서 '?' 문자 이후의 파라미터 문자열까지 자르기
            params = url.substring( url.lastIndexOf('/')+1, url.indexOf('2') );
        // 파라미터 구분자("&") 로 분리
        params = params.split("&");
            
        document.location.href = "/" + params + "?page=1&parent_category=${parent_category}&child_category=${child_category}"
    }
    
    function star() {
        var url = window.location.href;
        var params;
        // url에서 '?' 문자 이후의 파라미터 문자열까지 자르기
            params = url.substring( url.lastIndexOf('/')+1, url.indexOf('2') );
        // 파라미터 구분자("&") 로 분리
        params = params.split("&");
            
        document.location.href = "/" + params + "_star?page=1&parent_category=${parent_category}&child_category=${child_category}"
    } 

    function low() {
        var url = window.location.href;
        var params;
        // url에서 '?' 문자 이후의 파라미터 문자열까지 자르기
            params = url.substring( url.lastIndexOf('/')+1, url.indexOf('2') );
        // 파라미터 구분자("&") 로 분리
        params = params.split("&");
        
        document.location.href = "/" + params + "_low?page=1&parent_category=${parent_category}&child_category=${child_category}"
    }
        
    function high() {
        var url = window.location.href;
        var params;
        // url에서 '?' 문자 이후의 파라미터 문자열까지 자르기
            params = url.substring( url.lastIndexOf('/')+1, url.indexOf('2') );
        // 파라미터 구분자("&") 로 분리
        params = params.split("&");
        
        document.location.href = "/" + params + "_high?page=1&parent_category=${parent_category}&child_category=${child_category}"
    }

    var url = window.location.href;
    var params;
    
    // url에서 '?' 문자 이후의 파라미터 문자열까지 자르기
        params = url.substring( url.lastIndexOf('/')+1, url.indexOf('?')+1 );
    
    // 파라미터 구분자("&") 로 분리
    params = params.split("&");
    
    var parent = $('.parent');
    var child = $('.child');
    var head = $('#head');
    var category = $('.category');
    var livingroom_count = 0;
    var kitchen_count = 0;
    var bedroom_count = 0;
    var library_count = 0;
    var interior_count = 0;
    var select = $('.select');
    var pso = $('.pso');
    var next = $('.next');
    
    var livingroom_couch_count = 0;
    var livingroom_table_count = 0;
    var kitchen_table_count = 0;
    var kitchen_chair_count = 0;
    var kitchen_storage_closet_count = 0;
    var bedroom_bed_count = 0;
    var bedroom_storage_closet_count = 0;
    var bedroom_dressing_table_count = 0;
    var library_desk_count = 0;
    var library_bookshelf_count = 0;
    var interior_count = 0;
    
    //정렬 옵션
    if(String(params).includes('high')){
        pso[0].innerHTML='<div class="product_sort_option"><a href="#" onclick="newest();">최신순</a></div><div class="product_sort_option"><a href="#" onclick="star();">별점순</a></div><div class="product_sort_option"><a href="#" onclick="low();">가격 낮은 순</a></div><div class="product_sort_option active"><a href="#" onclick="high();">가격 높은 순</a></div>'
    }
    else if(String(params).includes('low')){
        pso[0].innerHTML='<div class="product_sort_option"><a href="#" onclick="newest();">최신순</a></div><div class="product_sort_option"><a href="#" onclick="star();">별점순</a></div><div class="product_sort_option active"><a href="#" onclick="low();">가격 낮은 순</a></div><div class="product_sort_option"><a href="#" onclick="high();">가격 높은 순</a></div>'	
    }
    else if(String(params).includes('star')){
        pso[0].innerHTML='<div class="product_sort_option"><a href="#" onclick="newest();">최신순</a></div><div class="product_sort_option active"><a href="#" onclick="star();">별점순</a></div><div class="product_sort_option"><a href="#" onclick="low();">가격 낮은 순</a></div><div class="product_sort_option"><a href="#" onclick="high();">가격 높은 순</a></div>'
    }
    else{
        pso[0].innerHTML='<div class="product_sort_option active"><a href="#" onclick="newest();">최신순</a></div><div class="product_sort_option"><a href="#" onclick="star();">별점순</a></div><div class="product_sort_option"><a href="#" onclick="low();">가격 낮은 순</a></div><div class="product_sort_option"><a href="#" onclick="high();">가격 높은 순</a></div>'
    }

    // 카테고리 대분류
    for(var i = 0; i<parent.length; i++){
        if(parent[i].value=='거실'){
            livingroom_count++
        }
        else if(parent[i].value=='주방'){
            kitchen_count++
        }
        else if(parent[i].value=='침실'){
            bedroom_count++
        }
        else if(parent[i].value=='서재'){
            library_count++
        }
        else if(parent[i].value=='인테리어'){
            interior_count++
        }
    }

    // 카테고리 소분류
    for(var i = 0; i<child.length; i++){
        if(parent[i].value=='거실' && child[i].value=='쇼파'){
            livingroom_couch_count++
        }
        else if(parent[i].value=='거실' && child[i].value=='테이블'){
            livingroom_table_count++
        }
        else if(parent[i].value=='주방' && child[i].value=='테이블'){
            kitchen_table_count++
        }
        else if(parent[i].value=='주방' && child[i].value=='의자'){
            kitchen_chair_count++
        }
        else if(parent[i].value=='주방' && child[i].value=='수납장'){
            kitchen_storage_closet_count++
        }
        else if(parent[i].value=='침실' && child[i].value=='침대'){
            bedroom_bed_count++
        }
        else if(parent[i].value=='침실' && child[i].value=='수납장'){
            bedroom_storage_closet_count++
        }
        else if(parent[i].value=='침실' && child[i].value=='화장대'){
            bedroom_dressing_table_count++
        }
        else if(parent[i].value=='서재' && child[i].value=='책상'){
            library_desk_count++
        }
        else if(parent[i].value=='서재' && child[i].value=='책장'){
            library_bookshelf_count++
        }
        else if(parent[i].value=='인테리어'){
            interior_count++
        }
    }
    
    //거실
    if(livingroom_couch_count>0){ //쇼파
        category[0].innerHTML='<div class="list_item active">쇼파</div><div class="list_item"><a href="category2?page=1&parent_category=거실&child_category=테이블">테이블</a></div>'
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/interior2?page=1&parent_category=인테리어">인테리어</option><option value="/product_list">ALL</option></select>'
        select[1].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=거실&child_category=쇼파">쇼파</option><option value="/category2?page=1&parent_category=거실&child_category=테이블">테이블</option></select>'
    }    
    if(livingroom_table_count>0){ //테이블
        category[0].innerHTML='<div class="list_item"><a href="category2?page=1&parent_category=거실&child_category=쇼파">쇼파</a></div><div class="list_item active">테이블</div>'
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/interior2?page=1&parent_category=인테리어">인테리어</option><option value="/product_list">ALL</option></select>'
        select[1].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=거실&child_category=테이블">테이블</option><option value="/category2?page=1&parent_category=거실&child_category=쇼파">쇼파</option></select>'
    }

    //주방
    if(kitchen_table_count>0){ //테이블
        category[0].innerHTML='<div class="list_item active">테이블</div><div class="list_item"><a href="category2?page=1&parent_category=주방&child_category=의자">의자</a></div><div class="list_item"><a href="category2?page=1&parent_category=주방&child_category=수납장">수납장</a></div>'
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/interior2?page=1&parent_category=인테리어">인테리어</option><option value="/product_list">ALL</option></select>'
        select[1].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=주방&child_category=테이블">테이블</option><option value="/category2?page=1&parent_category=주방&child_category=의자">의자</option><option value="/category2?page=1&parent_category=주방&child_category=수납장">수납장</option></select>'
    }
    if(kitchen_chair_count>0){ //의자
        category[0].innerHTML='<div class="list_item"><a href="category2?page=1&parent_category=주방&child_category=테이블">테이블</a></div><div class="list_item active">의자</div><div class="list_item"><a href="category2?page=1&parent_category=주방&child_category=수납장">수납장</a></div>'
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/interior2?page=1&parent_category=인테리어">인테리어</option><option value="/product_list">ALL</option></select>'
        select[1].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=주방&child_category="의자">의자</option><option value="/category2?page=1&parent_category=주방&child_category=테이블">테이블</option><option value="/category2?page=1&parent_category=주방&child_category=수납장">수납장</option></select>'
    }
    if(kitchen_storage_closet_count>0){ //수납장
        category[0].innerHTML='<div class="list_item"><a href="category2?page=1&parent_category=주방&child_category=테이블">테이블</a></div><div class="list_item"><a href="category2?page=1&parent_category=주방&child_category=의자">의자</a></div><div class="list_item active">수납장</div>'
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/interior2?page=1&parent_category=인테리어">인테리어</option><option value="/product_list">ALL</option></select>'
        select[1].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=주방&child_category=수납장">수납장</option><option value="/category2?page=1&parent_category=주방&child_category=테이블">테이블</option><option value="/category2?page=1&parent_category=주방&child_category=의자">의자</option></select>'
    }

    //침실
    if(bedroom_bed_count>0){ //침대
        category[0].innerHTML='<div class="list_item active">침대</div><div class="list_item"><a href="category2?page=1&parent_category=침실&child_category=수납장">수납장</a></div><div class="list_item"><a href="category2?page=1&parent_category=침실&child_category=화장대">화장대</a></div>'
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/interior2?page=1&parent_category=인테리어">인테리어</option><option value="/product_list">ALL</option></select>'
        select[1].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=침실&child_category=침대">침대</option><option value="/category2?page=1&parent_category=침실&child_category=수납장">수납장</option><option value="/category2?page=1&parent_category=침실&child_category=화장대">화장대</option></select>'
    }
    if(bedroom_storage_closet_count>0){ //수납장
        category[0].innerHTML='<div class="list_item"><a href="category2?page=1&parent_category=침실&child_category=침대">침대</a></div><div class="list_item active">수납장</div><div class="list_item"><a href="category2?page=1&parent_category=침실&child_category=화장대">화장대</a></div>'
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/interior2?page=1&parent_category=인테리어">인테리어</option><option value="/product_list">ALL</option></select>'
        select[1].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=침실&child_category=수납장">수납장</option><option value="/category2?page=1&parent_category=침실&child_category=침대">침대</option><option value="/category2?page=1&parent_category=침실&child_category=화장대">화장대</option></select>'
    }
    if(bedroom_dressing_table_count>0){ //화장대
        category[0].innerHTML='<div class="list_item"><a href="category2?page=1&parent_category=침실&child_category=침대">침대</a></div><div class="list_item"><a href="category2?page=1&parent_category=침실&child_category=수납장">수납장</a></div><div class="list_item active">화장대</div>'
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/interior2?page=1&parent_category=인테리어">인테리어</option><option value="/product_list">ALL</option></select>'
        select[1].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=침실&child_category=화장대">화장대</option></option><option value="/category2?page=1&parent_category=침실&child_category=침대">침대</option><option value="/category2?page=1&parent_category=침실&child_category=수납장">수납장</select>'
    }

    //서재
    if(library_desk_count>0){ //책상
        category[0].innerHTML='<div class="list_item active">책상</div><div class="list_item"><a href="category2?page=1&parent_category=서재&child_category=책장">책장</a></div>'
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/interior2?page=1&parent_category=인테리어">인테리어</option><option value="/product_list">ALL</option></select>'
        select[1].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=서재&child_category=책상">책상</option><option value="/category2?page=1&parent_category=서재&child_category=책장">책장</option</select>'
    }
    if(library_bookshelf_count>0){ //책장
        category[0].innerHTML='<div class="list_item"><a href="category2?page=1&parent_category=서재&child_category=책상">책상</a></div><div class="list_item active">책장</div>'
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/interior2?page=1&parent_category=인테리어">인테리어</option><option value="/product_list">ALL</option></select>'
        select[1].innerHTML='<select onchange="location.href=this.value"><option value="/category2?page=1&parent_category=서재&child_category=책장">책장</option><option value="/category2?page=1&parent_category=서재&child_category=책상">책상</option></select>'
    }

    //인테리어
    if(interior_count>0){
        category[0].innerHTML=''
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/interior2?page=1&parent_category=인테리어">인테리어</option><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/product_list">ALL</option></select>'
        select[1].style.display='none'
        next[1].style.display='none'
    }

    //ALL
    if(String(params).includes('product_list')){
        head[0].innerText='ALL'
        category[0].innerHTML=''
        select[0].innerHTML='<select onchange="location.href=this.value"><option value="/product_list">ALL</option><option value="/category2?page=1&parent_category=거실&child_category=쇼파">거실</option><option value="/category2?page=1&parent_category=주방&child_category=테이블">주방</option><option value="/category2?page=1&parent_category=침실&child_category=침대">침실</option><option value="/category2?page=1&parent_category=서재&child_category=책상">서재</option><option value="/interior2?page=1&parent_category=인테리어">인테리어</option></select>'
        select[1].innerHTML=''
    }

    else if(livingroom_count>0){
        head[0].innerHTML='거실'
    }
    else if(bedroom_count>0){
        head[0].innerHTML='침실'
    }
    else if(kitchen_count>0){
        head[0].innerHTML='주방'
    }
    else if(library_count>0){
        head[0].innerHTML='서재'
    }
    else if(interior_count>0){
        head[0].innerHTML='인테리어'
    }    
</script>