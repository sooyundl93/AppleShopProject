<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kor">
<head>
  <title>회원 상세 조회</title>
  <style>
    * {
      margin: 0; padding: 0;
      box-sizing: border-box;
      /* border: 1px solid red; */
    }
    body{ 
      -ms-overflow-style: none; 
    } 
    ::-webkit-scrollbar { 
      display: none; 
    }
    article {
      min-width: 730px; height: 100vh;
      display: flex;
      flex-flow: column nowrap;
      gap: 6px;
      padding: 6px ;
    }
    .subject:not(:first-child) {
      margin-top: 10px; 
    }
    .row {
      display: flex;
      flex-flow: row nowrap;
    }
    .col {
      width: 100%;
      display: flex;
      flex-flow: row nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    .col:first-of-type > .cell {
      min-width: 300px;
      flex: 1 0 300px;
      border-right-color: transparent;
    }
    .col:last-of-type > .cell {
      min-width: 150px;
      flex: 1 0 150px;
    }
    .header {
      width: 130px;
      background-color: rgb(255, 250, 230); 
      border: 1px solid rgb(150, 150, 150);
      display: flex;
      justify-content: center; align-items: center;
      flex: 0 0 130px;
    }
    .cell {
      padding: 8px 6px;
      background-color: white;
      border: 1px solid rgb(230, 230, 230);
      border-left: transparent;
      display: flex;
      flex-flow: column nowrap;
      justify-content: center;
    }
    .row:not(:last-of-type) .header,
    .row:not(:last-of-type) .cell {
      border-bottom-color: transparent;
    }
    .row:first-of-type .col:first-of-type .header {
      border-top-left-radius: 3px;
    }
    .row:first-of-type .col:last-of-type .cell {
      border-top-right-radius: 3px;
    }
    .row:last-of-type .col:first-of-type .header {
      border-bottom-left-radius: 3px;
    }
    .row:last-of-type .col:last-of-type .cell {
      border-bottom-right-radius: 3px;
    }
    .fixed-box {
      width: 100%;
      position: fixed;
      bottom: 0;
      padding: 6px;
    }
    .brand-pink {
      background-color: rgb(254, 79, 121);
    }
    .button {
      width: 100%; 
      font-size: 20px;
      font-weight: bold;
      color: rgb(250, 250, 250);
      border-color: transparent; 
      border-radius: 3px;
      word-spacing: 6px;
      padding: 2px 8px;
    }
  </style>
</head>
<body>
  <article class="member-info-detail__wrap">

    <h4 class="subject">
      ㆍ회원 상세 정보
    </h4>

    <section>
      <div class="row">
        <div class="col">
          <div class="header">아이디</div>
          <div class="cell">
            <span>${detail.userId}</span>
          </div>
        </div>
        <div class="col">
          <div class="header">회원 구분</div>
          <div class="cell">
            <c:choose>
              <c:when test="${detail.userRole eq 'ROLE_TEMPORARY_USER'}">임시 회원</c:when>
              <c:when test="${detail.userRole eq 'ROLE_USER'}">일반 회원</c:when>
              <c:when test="${detail.userRole eq 'ROLE_ADMIN'}">관리자</c:when>
            </c:choose>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <div class="header">이름</div>
          <div class="cell">${detail.userName}</div>
        </div>
        <div class="col">
          <div class="header">성별</div>
          <div class="cell">${detail.gender}성</div>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <div class="header">생년월일</div>
          <div class="cell">
            <span>${detail.birthday}</span>
          </div>
        </div>
        <div class="col">
          <div class="header">나이</div>
          <div class="cell">${detail.age}세</div>
        </div>
      </div>
    </section>

    <section>
      <div class="row">
        <div class="col">
          <div class="header">주소</div>
          <div class="cell">
            <span>${detail.address}</span>
            <span>${detail.detailedAddress}</span>
          </div>
        </div>
        <div class="col">
          <div class="header">우편번호</div>
          <div class="cell">${detail.postalCode}</div>
        </div>
      </div>
    </section>

    <section>
      <div class="row">
        <div class="col">
          <div class="header">연락처</div>
          <div class="cell">${detail.phoneNumber}</div>
        </div>
        <div class="col">
          <div class="header">문자 수신</div>
          <div class="cell">
            <c:choose>
              <c:when test="${detail.receiveMessagesFlag == 'Y'}">동의함</c:when>
              <c:otherwise>동의하지않음</c:otherwise>
            </c:choose>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <div class="header">이메일</div>
          <div class="cell">${detail.email}</div>
        </div>
        <div class="col">
          <div class="header">메일 수신</div>
          <div class="cell">
            <c:choose>
              <c:when test="${detail.receiveEmailFlag == 'Y'}">동의함</c:when>
              <c:otherwise>동의하지않음</c:otherwise>
            </c:choose>
          </div>
        </div>
      </div>
    </section>

    <section>
      <div class="row">
        <div class="col">
          <div class="header">비밀번호 질문</div>
          <div class="cell">${detail.pwQuestion}</div>
        </div>
        <div class="col">
          <div class="header">비밀번호 답변</div>
          <div class="cell">${detail.pwAnswer}</div>
        </div>
      </div>
    </section>

    <section>
      <div class="row">  
        <div class="col">
          <div class="header">가입일</div>
          <div class="cell">${detail.memberSince}</div>
        </div>
        <div class="col">
          <div class="header">방문 횟수</div>
          <div class="cell">${detail.numberOfVisits}회</div>
        </div>
      </div>
    </section>

    <h4 class="subject">
      ㆍ서비스 이용 정보
    </h4>

    <section>
      <div class="row">
        <div class="col">
          <div class="header">총결제금액</div>
          <div class="cell">${detail.totalOrderPrice}원</div>
        </div>
        <div class="col">
          <div class="header">보유 마일리지</div>
          <div class="cell">${detail.mileage}원</div>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <div class="header">상품 주문 건수</div>
          <div class="cell">${detail.numberOfProductsOrderd}건</div>
        </div>
        <div class="col">
          <div class="header">리뷰/평균 평점</div>
          <div class="cell">${detail.numberOfMemberReviews}건 / ${detail.averageOfReviewScores}점</div>
        </div>
      </div>
    </section>

    <section>
      <div class="row">
        <div class="col">
          <div class="header">입금 대기</div>
          <div class="cell">${detail.shippingStatus[0]}건</div>
        </div>
        <div class="col">
          <div class="header">입금 완료</div>
          <div class="cell">${detail.shippingStatus[1]}건</div>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <div class="header">배송 완료</div>
          <div class="cell">${detail.shippingStatus[2]}건</div>
        </div>
        <div class="col">
          <div class="header">취소</div>
          <div class="cell">${detail.shippingStatus[3]}건</div>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <div class="header">반품</div>
          <div class="cell">${detail.shippingStatus[4]}건</div>
        </div>
        <div class="col">
          <div class="header">교환</div>
          <div class="cell">${detail.shippingStatus[5]}건</div>
        </div>
      </div>
    </section>

    <section>
      <div class="row">
        <div class="col">
          <div class="header">상품 문의 글 수</div>
          <div class="cell">${detail.numberOfProductInquiries}건</div>
        </div>
        <div class="col">
          <div class="header">답변 대기 중</div>
          <div class="cell">${detail.numberOfUnrepliedProductInquiries}건</div>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <div class="header">1:1 문의 글 수</div>
          <div class="cell">${detail.numberOfOne2oneInquiries}건</div>
        </div>
        <div class="col">
          <div class="header">답변 대기 중</div>
          <div class="cell">${detail.numberOfUnrepliedOne2oneInquiries}건</div>
        </div>
      </div>
    </section>  
  </article>

  <div class="fixed-box">
    <button class="brand-pink button" onclick="window.close();">닫 기</button>
  </div>

</body>
</html>