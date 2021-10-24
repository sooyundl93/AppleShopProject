<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html lang="en">
<head>
  <title>상품 정보 수정</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
    function updateThisRecord() {
      let manufacturer = $('#manufacturer').val();
      let productName = $('#product_name').val();
      let productPrice = $('#product_price').val();
      let shippingCost = $('#shipping_cost').val();
      let numberOfStock = $('#number_of_stock').val();
      let productDescription = $('#product_description').val();
      let file1 = $('#file1').val();
      let file2 = $('#file2').val();
      if ( !manufacturer ) {
        alert("제조사를 입력해주십시오.");
      } else if ( !productName ) {
        alert("상품명을 입력해주십시오.");
      } else if ( !productPrice ) {
        alert("상품 가격을 입력해주십시오.");
      } else if ( !shippingCost ) {
        alert("배송비를 입력해주십시오.");
      } else if ( !numberOfStock ) {
        alert("재고 개수를 입력해주십시오.");
      } else if ( !productDescription ) {
        alert("상품 설명을 입력해주십시오.");
      } else if ( !file1 && file2 ) {
        alert("상품 이미지를 추가해주십시오.");
      } else if ( file1 && !file2 ) {
        alert("상품 정보 이미지를 추가십시오.");
      } else { 
        var formData = new FormData($('#uploadForm')[0]);
        $.ajax({
        type: 'POST',
        processData : false,
        contentType : false,
        url : './detail_modity_action',
        data: formData,
        success: function (data) {
          let logText = "# ----- 상품정보 수정 성공 ----- #" + "\n";
          console.log(logText);
          if( typeof(opener) != 'undefined' ) {
              opener.parent.location.reload();
            }
          $( '#product_info_wrap' ).html( data );
        },
        error: function (request, status, error) {
          let alertText = "오류! 상품정보 수정을 실패하였습니다." + "\n"
                        + "자세한 오류 내용은 로그를 참조해주십시오."
          let logText = "# ----- 상품정보 수정 실패 ----- #" + "\n"
                      + "code : " + request.status + "\n" 
                      + "status : " + status + "\n"
                      + "error : " + error + "\n"
                      + "message : " + request.responseText + "\n";
          alert(alertText);
          console.log(logText);
        }
      });
      }
    }    
  </script>

  <link rel="stylesheet" href="/css/common.css">
  <link rel="stylesheet" href="/css/cscenter.css">
</head>

<body>
  <form id="uploadForm" enctype="multipart/form-data">
    <div class="popup order" id="product_info_wrap"> 
      <div class="page_title">
          <h2>상품정보 수정</h2>
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
          <div class="td product_no">
            <input 
              type="text" 
              id="product_code"
              name="product_code"
              value="<c:out value='${ detail.productCode }' />"
              readonly />
          </div>

          <!-- 상위 카테고리 DropBox -->
          <div class="td first_cate">
            <select id="parent_category" name="parent_category">
              <option value="거실" <c:if test="${ detail.parentCategory eq '거실' }">selected</c:if>>
                거실
              </option>
              <option value="주방" <c:if test="${ detail.parentCategory eq '주방' }">selected</c:if>>
                주방
              </option>
              <option value="침실" <c:if test="${ detail.parentCategory eq '침실' }">selected</c:if>>
                침실
              </option>
              <option value="서재" <c:if test="${ detail.parentCategory eq '서재' }">selected</c:if>>
                서재
              </option>
              <option value="인테리어" <c:if test="${ detail.parentCategory eq '인테리어' }">selected</c:if>>
                인테리어
              </option>
              <option value="미분류" <c:if test="${ detail.parentCategory eq '미분류' }">selected</c:if>>
                미분류
              </option>
            </select>
          </div>

          <!-- 하위 카테고리 DropBox -->
          <div class="td second_cate">
            <select id="child_category" name="child_category">
              <option value="쇼파" <c:if test="${ detail.childCategory eq '쇼파' }">selected</c:if>>
                쇼파
              </option>
              <option value="테이블" <c:if test="${ detail.childCategory eq '테이블' }">selected</c:if>>
                테이블
              </option>
              <option value="식탁" <c:if test="${ detail.childCategory eq '식탁' }">selected</c:if>>
                식탁
              </option>
              <option value="의자" <c:if test="${ detail.childCategory eq '의자' }">selected</c:if>>
                의자
              </option>
              <option value="수납장" <c:if test="${ detail.childCategory eq '수납장' }">selected</c:if>>
                수납장
              </option>
              <option value="침대" <c:if test="${ detail.childCategory eq '침대' }">selected</c:if>>
                침대
              </option>
              <option value="화장대" <c:if test="${ detail.childCategory eq '화장대' }">selected</c:if>>
                화장대
              </option>
              <option value="책상" <c:if test="${ detail.childCategory eq '책상' }">selected</c:if>>
                책상
              </option>
              <option value="책장" <c:if test="${ detail.childCategory eq '책장' }">selected</c:if>>
                책장
              </option>
              <option value="미분류" <c:if test="${ detail.childCategory eq '미분류' }">selected</c:if>>
                미분류
              </option>
            </select>
          </div>

          <!-- 추가 날짜 -->
          <div class="td insert_date">
            <c:out value="${ detail.addedDate }" />
          </div>

          <!-- 제조사 -->
          <div class="td made">
            <input 
              type="text" 
              id="manufacturer"
              name="manufacturer"
              value="<c:out value='${ detail.manufacturer }' />">
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
            <input 
              type="text" 
              id="product_name"
              name="product_name"
              value="<c:out value='${ detail.productName }' />">
          </div>

          <!-- 상품 가격 -->
          <div class="td price">
            <input 
              type="text" 
              id="product_price"
              name="product_price"
              value="<c:out value='${ detail.productPrice }' />" />
          </div>

          <!-- 배송 비용 -->
          <div class="td price">
            <input 
              type="text" 
              id="shipping_cost"
              name="shipping_cost"
              value="<c:out value='${ detail.shippingCost }' />" />
          </div>

          <!-- 판매 수량 -->
          <div class="td sell_count">
            <c:out value="${ detail.numberOfSales }" />
          </div>

          <!-- 재고수량 -->
          <div class="td stock_count">
            <input 
              type="text" 
              id="number_of_stock"
              name="number_of_stock"
              value="<c:out value='${ detail.numberOfStock }' />" />
          </div>
        </div>
      </div>

      <!-- bottom -->
      <div class="table">
        <div class="tr">
          <div class="th info_text">상품설명</div>
        </div>

        <div class="tr">
          <!-- 상품설명 -->
          <div class="td info_content">
            <textarea class="info_textarea" id="product_description" name="product_description">${ detail.productDescription }</textarea>
          </div>
        </div>
      </div>

      <!-- bottom -->
      <div class="table">
        <div class="tr">
          <div class="th img">상품정보 이미지</div>
        </div>

        <div class="tr input" id="img_container">
          <!-- 이미지 -->
          <div class="td img_container">
              <c:forEach var="productList" items="${ detail.imageFileList }" begin="0" end="0" varStatus="status">
                <img class="img_size" src="/products/<c:out value='${ detail.imageFileList[status.index] }'/>" alt="상품 이미지" loading="lazy" />
              </c:forEach>
              
              <c:forEach var="productList" items="${ detail.imageFileList }" begin="1" end="1" varStatus="status">
                <img class="img_size" src="/products/<c:out value='${ detail.imageFileList[status.index] }'/>" alt="상품 이미지" loading="lazy" />
              </c:forEach>
          </div>
          <div class="td img_container">  
            <input type="file" id="file1" name="file1">
            <input type="file" id="file2" name="file2">
          </div>
        </div>
      </div> 

      <!-- '확인' 버튼 -->
      <div class="table" id="button">
        <button 
          class="btn_pink" 
          type="button"
          onclick="updateThisRecord()">
          확인
        </button>
      </div>
    </div>
  </form>
</body>
</html>