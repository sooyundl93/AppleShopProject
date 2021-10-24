<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <title>상품 상세 정보</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
    function updateThisRecord( productCode ) {
      $.ajax({
        type: 'POST',
        url : './detail_modity',
        data: {
          "product_code"  : productCode,
        },
        success: function (data) {
          let logText = "# ----- 수정 폼으로 이동 ----- #" + "\n";
          console.log(logText);
          $( '#product_info_wrap' ).html( data );
        },
        error: function (request, status, error) {
          let alertText = "오류! 수정 폼으로 이동하지 못하였습니다." + "\n"
                        + "자세한 오류 내용은 로그를 참조해주십시오."
          let logText = "# ----- 수정 폼으로 이동 실패 ----- #" + "\n"
                      + "code : " + request.status + "\n" 
                      + "status : " + status + "\n"
                      + "error : " + error + "\n"
                      + "message : " + request.responseText + "\n";
          alert(alertText);
          console.log(logText);
        }
      });
    }
  </script>

  <link rel="stylesheet" href="/css/common.css">
  <link rel="stylesheet" href="/css/cscenter.css">
</head>

<body>

  <div class="popup order" id="product_info_wrap">
    <div class="page_title">
        <h2>상품정보</h2>
    </div>

    <!-- top -->
    <div class="table">
      <div class="tr">
        <div class="th product_no">상품코드</div>
        <div class="th first_cate">상위 카테고리</div>
        <div class="th second_cate">하위 카테고리</div>
        <div class="th insert_date">추가날짜</div>
        <div class="th made">제조사</div>
      </div>

      <div class="tr">
        <!-- 상품코드 -->
        <div class="td product_no">
          <c:out value="${ detail.productCode }" />
        </div>

        <!-- 상위 카테고리 -->
        <div class="td first_cate">
          <c:out value="${ detail.parentCategory }" />
        </div>

        <!-- 하위 카테고리 -->
        <div class="td second_cate">
          <c:out value="${ detail.childCategory }" />
        </div>

        <!-- 추가 날짜 -->
        <div class="td insert_date">
          <c:out value="${ detail.addedDate }" />
        </div>

        <!-- 제조사 -->
        <div class="td made">
          <c:out value="${ detail.manufacturer }" />
        </div>
      </div>
    </div>

    <!-- middle -->
    <div class="table">
      <div class="tr">
        <div class="th name">상품명</div>
        <div class="th price">상품 가격</div>
        <div class="th shipping_cost">배송 비용</div>
        <div class="th sell_count">판매 수량</div>
        <div class="th stock_count">재고 수량</div>
      </div>

      <div class="tr">
        <!-- 상품명 -->
        <div class="td name">
          <c:out value="${ detail.productName }" />
        </div>

        <!-- 상품 가격 -->
        <div class="td price">
          <c:out value="${ detail.productPrice }" />
        </div>

        <!-- 배송비 -->
        <div class="td sell_count">
          <c:out value="${ detail.shippingCost }" />
        </div>

        <!-- 판매수량 -->
        <div class="td sell_count">
          <c:out value="${ detail.numberOfSales }" />
        </div>

        <!-- 재고수량 -->
        <div class="td stock_count">
          <c:out value="${ detail.numberOfStock }" />
        </div>
      </div>
    </div>

    <div class="table">
      <div class="tr">
        <div class="th info_text">상품 설명</div>
      </div>

      <div class="tr">
        <!-- 상품설명 -->
        <div class="td info_content">
          ${ detail.productDescription }
        </div>
      </div>
    </div>

    <!-- bottom -->
    <div class="table">
      <div class="tr">
        <div class="th img">상품정보 이미지</div>
      </div>

      <div class="tr">
        <div class="td img_container">
        <!-- 이미지 -->
          <c:forEach var="productList" items="${ detail.imageFileList }" varStatus="status">
            <img class="img_size" src="/products/<c:out value='${ detail.imageFileList[status.index] }'/>" alt="상품 이미지" loading="lazy" />
          </c:forEach>
        </div>
      </div>
    </div>   

    <div class="table" id="button">
        <button 
          class="btn_pink" 
          type="button"
          onclick="updateThisRecord( `<c:out value='${ detail.productCode }' />` )">
          수정
        </button>
    </div>
  </div>
  
</body>
</html>