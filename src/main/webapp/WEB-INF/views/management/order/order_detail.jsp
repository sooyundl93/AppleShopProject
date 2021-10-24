<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>내일의 집:주문 내역</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
    crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
    crossorigin="anonymous">
    </script>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

  <link rel="stylesheet" href="/css/common.css">
  <link rel="stylesheet" href="/css/cscenter.css">
  <style>
    .popup {
      display: flex;
      justify-content: center;
      align-items: center;
    }
    button {
      min-width: 80px; height: 30px;
      font-size: 16px;
      padding: 6px 8px;
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: transparent;
    }
    .button_container {
      width: 88%;
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 12px;
      margin-bottom: 20px;
    }
    .button_container button {
      width: 100%; height: 50px;
    }
  </style>
  <script>
    function confirmPayment( orderId ) {
      // 컨트롤에서 order_id를 키 주문상태를 update한 뒤
      // 화면을 새로고침한다.
      let logText = "주문아이디 : " + orderId;
      $.ajax({
        type: 'POST',
        url : '/confirm_payment',  // 컨트롤 url
        data: {
          "order_id"  : orderId   
        }, success: function (data) {
          location.reload();      // 팝업창 새로고침
          logText = "# ----- 업데이트 성공 ----- #" + "\n" + logText;
        }, error: function (request, status, error) {
          logText = "# ----- 업데이트 실패 ----- #" + "\n" + logText
                  + "code : " + request.status + "\n" 
                  + "status : " + status + "\n" 
                  + "error : " + error + "\n" 
                  + "message : " + request.responseText + "\n";
        }, complete : function () {
          console.log(logText);
        }
      });
    }
    
    function orderStateChange( orderId, orderStatus ) {
        // 컨트롤에서 order_id를 키 주문상태를 update한 뒤
        // 화면을 새로고침한다.
        let logText = "주문아이디 : " + orderId;
        $.ajax({
          type: 'POST',
          url : '/order_status_change',  // 컨트롤 url
          data: {
            "order_id"      : orderId,
            "order_status"  : orderStatus
          }, success: function (data) {
            location.reload();      // 팝업창 새로고침
            logText = "# ----- 업데이트 성공 ----- #" + "\n" + logText;
          }, error: function (request, status, error) {
            logText = "# ----- 업데이트 실패 ----- #" + "\n" + logText
                    + "code : " + request.status + "\n" 
                    + "status : " + status + "\n" 
                    + "error : " + error + "\n" 
                    + "message : " + request.responseText + "\n";
          }, complete : function () {
            console.log(logText);
          }
        });
      }
  </script>
</head>

<body>
  <div class="popup order">

    <div class="page_title">
      <h2>주문정보<br>-</h2>
    </div>

    <div class="table" id="table1">
      <div class="tr">
        <div class="th">주문번호</div>
        <div class="td">${ data.orderId }</div>
        <div class="th">주문회원</div>
        <div class="td">${ data.userId }</div>
      </div>  
      <div class="tr">  
        <div class="th">주문날짜</div>
      </div>
      <div class="tr">
        <div class="td">${ data.orderDate }</div>
      </div>
    </div>

    <div class="table" id="table3">
      <div class="tr">
        <div class="th">수령인</div>
        <div class="td">${ data.recipientName }</div>
        <div class="th">연락처</div>
        <div class="td">${ data.phoneNumber }</div>
      </div>

      <div class="tr">
        <div class="th">주소</div>
      </div>
      <div class="tr">
        <div class="td">
          ${ data.postalCode }&emsp;
          ${ data.address }, ${ data.detailedAddress }
        </div>
      </div>

      <div class="tr">
        <div class="th">배송 메모</div>
      </div>
      <div class="tr">
        <div class="td">${ data.shippingMemo }</div>
      </div>
    </div>

    <div class="table" id="table2">
      <div class="tr">
        <div class="th">총 지불 금액</div>
        <div class="th">총 배송 금액</div>
        <div class="th">사용한 마일리지</div>
        <div class="th">할인받은 금액</div>
      </div>
      <div class="tr">
        <div class="td">${ data.totalPrice }</div>
        <div class="td">${ data.totalShippingCost }</div>
        <div class="td">${ data.usedMileage }</div>
        <div class="td">${ data.discountedPrice }</div>
      </div>
      <div class="tr">
        <div class="th">지불 방식</div>

        <c:choose>
          <c:when test="${ data.paymentMethod eq '무통장입금' }">
            <div class="th">무통장 입금 확인버튼</div>
          </c:when>
          <c:otherwise>
            <div class="th">Payment ID</div>
          </c:otherwise>
        </c:choose>

        <div class="th">적립될 마일리지</div>
        <div class="th">주문 상태</div>
      </div>
      <div class="tr">
        <div class="td">${ data.paymentMethod }</div>
        <c:choose>
          <c:when test="${ data.paymentMethod eq '무통장입금' and data.orderStatus eq '입금대기' }">
            <!-- 지불방식이 '무통장입금' 이면서 주문상태가 '입금대기' 일때 -->
            <div class="td">
              <button type="button" onclick="confirmPayment( '${ data.orderId }' )">
                입금확인
              </button>
            </div>
          </c:when>

          <c:when test="${ data.paymentMethod eq '무통장입금' and data.orderStatus ne '입금대기' }">
            <!-- 지불방식이 '무통장입금' 이면서 주문상태가 '입금대기' 가 >>아닐 때<< -->
            <div class="td">
              <button type="button" disabled>
                입금완료
              </button>
            </div>
          </c:when>
          <c:otherwise>
            <div class="td">${ data.paymentId }</div>
          </c:otherwise>
        </c:choose>
        <div class="td">${ data.accruedMileage }</div>
        <div class="td">${ data.orderStatus }</div>
      </div>
    </div>

    <c:if test="${ empty data.orderProducts }">
      <div class="table" id="table4"> 
        <div class="td">[ 주문 상품이 없습니다. ]</div>
      </div>
    </c:if>    
    <c:forEach var="product" items="${ data.orderProducts }">
      <div class="table" id="table4"> 
        <div class="tr title">
          <div class="th">상품 번호</div>
          <div class="th">상품 아이디</div>
          <div class="th">상품 이름</div>
          <div class="th">상품 분류</div>
        </div>
        <div class="tr title">
          <div class="td">${ product.itemNo }</div>
          <div class="td">${ product.productCode }</div> 
          <div class="td">${ product.productName }</div>
          <div class="td">${ product.parentCategory } / ${ product.childCategory }</div>
        </div>
        <div class="tr">
          <div class="th">제조사</div>
          <div class="th">가격</div>
          <div class="th">배송 비용</div>
          <div class="th">주문 개수</div>
          <div class="th">리뷰 작성</div>
        </div>
        <div class="tr">    
          <div class="td">${ product.manufacturer }</div> 
          <div class="td">${ product.productPrice }</div>
          <div class="td">${ product.shippingCost }</div>
          <div class="td">${ product.numberOfOrders }</div>
          <div class="td">${ product.wroteReviewFlag }</div>
        </div>
      </div>
    </c:forEach>
    <div class="button_container">
      <!-- -->
      <c:choose>
        <c:when test="${ data.orderStatus eq '입금대기' }">
          <!-- 입금 대기 일때 선택지 : 거래 취소 -->
          <button type="button" onclick="orderStateChange( '${ data.orderId }', '4' )">거래 취소</button> <!-- 4번 [취소]로 상태 변경 -->
        </c:when>
        <c:when test="${ data.orderStatus eq '입금완료' }">
          <!-- 입금 완료 일때 선택지 : 배송 시작, 환불(=반품) -->
          <button type="button" onclick="orderStateChange( '${ data.orderId }', '3' )">배송 시작</button> <!-- 3번 [배송 완료]로 상태 변경 -->
          <button type="button" onclick="orderStateChange( '${ data.orderId }', '5' )">환불</button> <!-- 5번 [반품]으로 상태 변경, 환불과 반품은 동일 한 로직(명칭만 다름) -->
        </c:when>
        <c:when test="${ data.orderStatus eq '배송완료' }">
          <!-- 배송 완료 일때 선택지 : 반품, 교환 -->
          <button type="button" onclick="orderStateChange( '${ data.orderId }', '5' )">반품</button> <!-- 5번 [반품]으로 상태 변경 -->
          <button type="button" onclick="orderStateChange( '${ data.orderId }', '6' )">교환</button> <!-- 6번 [교환]으로 상태 변경 -->
        </c:when>
        <c:when test="${ data.orderStatus eq '취소' }">
          <!-- 취소 일때 선택지 : 없음(거래 종료 됨) -->
          <button type="button" disabled>거래 취소 됨</button>
        </c:when>
        <c:when test="${ data.orderStatus eq '반품' }">
          <!-- 반품 일때 선택지 : 없음(거래 종료 됨) -->
          <button type="button" disabled>상품이 환불/반품 됨</button>
        </c:when>
        <c:when test="${ data.orderStatus eq '교환' }">
          <!-- 교환 일때 선택지 : 배송 시작, 환불 (교환을 신청했을때 다시 배송과 환불을 받을 수 있다.) -->
          <button type="button" onclick="orderStateChange( '${ data.orderId }', '3' )">배송 시작</button> <!-- 3번 [배송 완료]로 상태 변경 -->
          <button type="button" onclick="orderStateChange( '${ data.orderId }', '5' )">환불</button> <!-- 5번 [반품]으로 상태 변경 -->
        </c:when>
        <c:otherwise>
          <button type="button" disabled>오류</button>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</body>
</html>