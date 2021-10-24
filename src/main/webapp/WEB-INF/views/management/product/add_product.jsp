<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- 상품정보 등록 팝업 -->
  <!-- 작업자 : 김남권    -->
  <title>상품 등록</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
    function updateThisRecord() {
      let manufacturer = $('#manufacturer').val();
      let productName = $('#product_name').val();
      let productPrice = $('#product_price').val();
      let shippingCost = $('#shipping_cost').val();
      let numberOfStock = $('#number_of_stock').val();
      let productDescription = $('#product_description').val();
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
      } else { 
    	  
    	var formData = new FormData($('#uploadForm')[0]);
    	    	
        $.ajax({
        type: 'POST',
        processData : false,
        contentType : false,
        url : './product_add_action',
        data: formData,
        success: function (data) {
          let logText = "# ----- 상품정보 입력 성공 ----- #" + "\n";
          console.log(logText);
          if( typeof(opener) != 'undefined' ) {
              opener.parent.location.reload();
            }
          window.close();
        },
        error: function (request, status, error) {
          let alertText = "오류! 상품정보 입력을 실패하였습니다." + "\n"
                        + "자세한 오류 내용은 로그를 참조해주십시오."
          let logText = "# ----- 상품정보 입력 실패 ----- #" + "\n"
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
          <h2>상품 등록</h2>
      </div>

      <!-- top -->
      <div class="table">
        <div class="tr">
          <div class="th first_cate">상위 카테고리</div>
          <!-- 상위 카테고리 DropBox -->
          <div class="td first_cate">
            <select id="parent_category" name="parent_category">
              <option value="거실">
                거실
              </option>
              <option value="주방">
                주방
              </option>
              <option value="침실">
                침실
              </option>
              <option value="서재">
                서재
              </option>
              <option value="인테리어">
                인테리어
              </option>
              <option value="미분류">
                미분류
              </option>
            </select>
          </div>
          <div class="th second_cate">하위 카테고리</div>
          <!-- 하위 카테고리 DropBox -->
          <div class="td second_cate">
            <select id="child_category" name="child_category">
              <option value="쇼파">
                쇼파
              </option>
              <option value="테이블">
                테이블
              </option>
              <option value="식탁">
                식탁
              </option>
              <option value="의자">
                의자
              </option>
              <option value="수납장">
                수납장
              </option>
              <option value="침대">
                침대
              </option>
              <option value="화장대">
                화장대
              </option>
              <option value="책상">
                책상
              </option>
              <option value="책장">
                책장
              </option>
              <option value="미분류">
                미분류
              </option>
            </select>
          </div>
          <!-- 제조사 -->
          <div class="th made">제조사</div>
          <div class="td made">
            <input type="text" id="manufacturer" name="manufacturer">
          </div>
        </div>
      </div>

      <!-- middle -->
      <div class="table">
        <div class="tr">
          <div class="th name">상품명</div>
          <div class="th price">상품 가격</div>
          <div class="th shipping_cost">배송 비용</div>
          <div class="th number_of_stock">재고 수량</div>
        </div>

        <div class="tr">
          <!-- 상품명 -->
          <div class="td name">
            <input class="" type="text" id="product_name" name="product_name">
          </div>

          <!-- 상품가격 -->
          <div class="td price">
            <input type="number" id="product_price" name="product_price">
          </div>

          <!-- 배송비 -->
          <div class="td shipping_cost">
            <input type="number" id="shipping_cost" name="shipping_cost">
          </div>

          <!-- 배송비 -->
          <div class="td number_of_stock">
            <input type="number" id="number_of_stock" name="number_of_stock">
          </div>
        </div>
      </div>

      <!-- bottom -->
      <div class="table">
        <div class="tr">
          <div class="th info_text">상품 설명</div>
        </div>

        <div class="tr">
          <!-- 상품설명 -->
          <div class="td info_content">
            <textarea class="info_textarea" id="product_description" name="product_description"></textarea>
          </div>
        </div>
      </div>

      <!-- bottom -->
      <div class="table">
        <div class="tr">
          <div class="th img">상품정보 이미지</div>
        </div>

        <div class="tr" id="img_container">
          <!-- 이미지 -->
          <div class="td img_container">
            <c:forEach var="productList" items="${ detail.imageFileList }" varStatus="status">
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