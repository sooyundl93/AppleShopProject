<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

  <!-- ########## 조회 옵션 ########## -->
  <section class="search_bar">
    <div class="col">
      <div class="row">
        <div class="cell">
          <label for="search_option" class="label-text">
            조회 옵션
          </label>

          <select id="search_option" name="search_option" class="drop-down">
            <option value="전체" <c:if test="${search_option eq '전체'}">selected</c:if>>
              전체
            </option>

            <option value="상품명" <c:if test="${search_option eq '상품명'}">selected</c:if>>
              상품명
            </option>

            <option value="상품코드" <c:if test="${search_option eq '상품코드'}">selected</c:if>>
              상품코드
            </option>

            <option value="상품가격" <c:if test="${search_option eq '상품가격'}">selected</c:if>>
              상품가격
            </option>
          </select>
          
          <input 
            type="search" 
            id="search_keyword" 
            name="search_keyword" 
            class="search_keyword"
            onKeypress="javascript:if( event.keyCode == 13 ) { getListContents( <c:out value='${pagination.currentPage}'/> ) }"
            maxlength="30"
            value="<c:out value='${search_keyword}'/>"/>

          <button 
            class="brand-mint" 
            type="button" 
            onclick="getListContents(<c:out value='${pagination.currentPage}'/>)">
            조 회
          </button>
        </div>
      </div>
      
      <!-- ################################ 조회 정렬 ################################ -->
      <div class="row advanced_search">
        <div class="cell">
          <label for="search-category" class="label-text">
            상품 정렬
          </label>

          <select 
            class="drop-down" 
            id="current_sort" 
            name="current_sort"
            onchange="getListContents( <c:out value='${pagination.currentPage}'/> )">
            <option value="최신 등록일 순" <c:if test="${current_sort eq '최신 등록일 순'}">selected</c:if>>
              최신 등록일 순
            </option>

            <option value="오래된 등록일 순" <c:if test="${current_sort eq '오래된 등록일 순'}">selected</c:if>>
              오래된 등록일 순
            </option>

            <option value="판매가 높은 순" <c:if test="${current_sort eq '판매가 높은 순'}">selected</c:if>>
              판매가 높은 순
            </option>

            <option value="판매가 낮은 순" <c:if test="${current_sort eq '판매가 낮은 순'}">selected</c:if>>
              판매가 낮은 순
            </option>

            <option value="재고량 많은 순" <c:if test="${current_sort eq '재고량 많은 순'}">selected</c:if>>
              재고량 많은 순
            </option>

            <option value="재고량 적은 순" <c:if test="${current_sort eq '재고량 적은 순'}">selected</c:if>>
              재고량 적은 순
            </option>
          </select>
        </div>

        <!-- ################################ 카테고리 검색 ################################ -->
        <div class="cell">
          <label for="search-category" class="label-text">
            상품 분류
          </label>

          <select 
            class="drop-down" 
            id="parent_category" 
            name="parent_category"
            onchange="getListContents( <c:out value='${pagination.currentPage}'/> )">
            <option value="전체" <c:if test="${parent_category eq '전체'}">selected</c:if>>
              상위 분류 전체
            </option>

            <option value="거실" <c:if test="${parent_category eq '거실'}">selected</c:if>>
              거실
            </option>

            <option value="주방" <c:if test="${parent_category eq '주방'}">selected</c:if>>
              주방
            </option>

            <option value="침실" <c:if test="${parent_category eq '침실'}">selected</c:if>>
              침실
            </option>

            <option value="서재" <c:if test="${parent_category eq '서재'}">selected</c:if>>
              서재
            </option>

            <option value="인테리어"<c:if test="${parent_category eq '인테리어'}">selected</c:if>>
              인테리어
            </option>

            <option value="미분류"<c:if test="${parent_category eq '미분류'}">selected</c:if>>
              미분류
            </option>
          </select>

          <select 
            class="drop-down" 
            id="child_category" 
            name="child_category"
            onchange="getListContents( <c:out value='${pagination.currentPage}'/> )">
            <option value="전체" <c:if test="${child_category eq '전체'}">selected</c:if>>
              하위 분류 전체
            </option>

            <option value="쇼파" <c:if test="${child_category eq '쇼파'}">selected</c:if>>
              쇼파
            </option>

            <option value="테이블" <c:if test="${child_category eq '테이블'}">selected</c:if>>
              테이블
            </option>

            <option value="침대" <c:if test="${child_category eq '침대'}">selected</c:if>>
              침대
            </option>

            <option value="의자" <c:if test="${child_category eq '의자'}">selected</c:if>>
              의자
            </option>

            <option value="수납장" <c:if test="${child_category eq '수납장'}">selected</c:if>>
              수납장
            </option>

            <option value="화장대" <c:if test="${child_category eq '화장대'}">selected</c:if>>
              화장대
            </option>

            <option value="책상" <c:if test="${child_category eq '책상'}">selected</c:if>>
              책상
            </option>

            <option value="책장" <c:if test="${child_category eq '책장'}">selected</c:if>>
              책장
            </option>

            <option value="미분류" <c:if test="${child_category eq '미분류'}">selected</c:if>>
              미분류
            </option>
          </select>
        </div>
      </div>
    </div>
  </section>

  <!-- ################################ 테이블 캡션 & 정렬 ################################ -->
  <div class="contents" id="contents">
    <section class="table_top">
      <span class="caption">
        상품 목록 <c:out value="${ numberOfRecords }" />건
      </span>
      
      <div class="table_top_right">
        <button
          id="normal"
          class="grid_button"
          onclick="
            changeHiddenValue( 'normal', <c:out value='${pagination.currentPage}'/> )">
        </button>

        <button 
          id="extended"
          class="list_button"
          onclick="
            changeHiddenValue( 'extended', <c:out value='${pagination.currentPage}'/> )">
        </button>

        <input type="hidden" value="<c:out value='${ view_mode }' />" id="view_mode" />

        <select 
          class="drop-down"
          id="numberOfRecordsPerPage"
          name="numberOfRecordsPerPage"
          onchange="getListContents( <c:out value='${pagination.currentPage}' /> ) ">
          <option value="5" <c:if test="${pagination.numberOfRecordsPerPage == '5'}">selected</c:if>>
            5개씩 보기
          </option>

          <option value="10" <c:if test="${pagination.numberOfRecordsPerPage == '10'}">selected</c:if>>
            10개씩 보기
          </option>

          <option value="15" <c:if test="${pagination.numberOfRecordsPerPage == '15'}">selected</c:if>>
            15개 보기
          </option>

          <option value="20" <c:if test="${pagination.numberOfRecordsPerPage == '20'}">selected</c:if>>
            20개씩 보기
          </option>

          <option value="25" <c:if test="${pagination.numberOfRecordsPerPage == '25'}">selected</c:if>>
            25개씩 보기
          </option>

          <option value="30" <c:if test="${pagination.numberOfRecordsPerPage == '30'}">selected</c:if>>
            30개씩 보기
          </option>
        </select>
      </div>
    </section>

    <!-- ########## 테이블 ########## -->
    <c:choose>
      <c:when test="${view_mode eq 'extended'}">
        <section class="table product-list">
          <div class="row-group header-group">
            <div class="header">
              <span class="cell">
                <input type="checkbox" id="all_checkboxes" onchange="controlOfAllCheckboxes()" />
              </span>
              <span class="cell">상품 코드</span>
              <span class="cell">상위 분류</span>
              <span class="cell">하위 분류</span>   
              <span class="cell">상품명</span>
              <span class="cell">판매가</span>
              <span class="cell">재고 수량</span>
              <span class="cell">등록일</span>
            </div>
          </div>
    
          <c:forEach var="productList" items="${ relationInstance }">
            <div class="row-group">
              <div class="row">
                <span class="cell">
                  <input type="checkbox" class="checkbox" value="<c:out value='${ productList.productCode }'/>">
                </span>

                <span class="cell">
                  <button type="button" onclick="openNewPage( `./product_list/detail?productCode=<c:out value='${ productList.productCode }'/>`, 1200, 900 )">
                    <c:out value="${ productList.productCode }" />
                  </button>
                </span>
    
                <span class="cell">
                  <button type="button" onclick="openNewPage( `./product_list/detail?productCode=<c:out value='${ productList.productCode }'/>`, 1200, 900 )">
                    <c:out value="${ productList.parentCategory }" />
                  </button>
                </span>
    
                <span class="cell">
                  <button type="button" onclick="openNewPage( `./product_list/detail?productCode=<c:out value='${ productList.productCode }'/>`, 1200, 900 )">
                    <c:out value="${ productList.childCategory }" />
                  </button>
                </span>
    
                <span class="cell">
                  <button type="button" class="ellipsis" onclick="openNewPage( `./product_list/detail?productCode=<c:out value='${ productList.productCode }'/>`, 1200, 900 )">
                    <c:out value="${ productList.productName }" />
                  </button>
                </span>
    
                <span class="cell">
                  <button type="button" class="ellipsis" onclick="openNewPage( `./product_list/detail?productCode=<c:out value='${ productList.productCode }'/>`, 1200, 900 )">
                    <c:out value="${ productList.productPrice }" />원
                  </button>
                </span>
    
                <span class="cell">
                  <button type="button" onclick="openNewPage( `./product_list/detail?productCode=<c:out value='${ productList.productCode }'/>`, 1200, 900 )">
                    <c:out value="${ productList.numberOfStock  }" />개
                  </button>
                </span>
    
                <span class="cell">
                  <button type="button" onclick="openNewPage( `./product_list/detail?productCode=<c:out value='${ productList.productCode }'/>`, 1200, 900 )">
                    <c:out value="${ productList.addedTime }" />
                  </button>
                </span>
              </div>
            </div>
          </c:forEach>

          <c:if test="${ relationInstance == null or fn:length( relationInstance ) == 0 }">
            <div class="row-group">
              <div class="cell">검색된 상품이 없습니다.</div>
            </div>
          </c:if>
        </section>   
      </c:when>

      <c:otherwise>
        <section class="table product-list-extended">   
          <c:forEach var="productList" items="${ relationInstance }" varStatus="status">
            <c:if test="${ status.first }">
              <div class="row">
            </c:if>

            <div class="cell">
              <span class="cell_contents">
                <div class="cell_title">
                  <a href="javascript:openNewPage( `./product_list/detail?productCode=<c:out value='${ productList.productCode }'/>`, 1200, 900 )">
                    <c:out value="${ productList.productName }"/>
                  </a>
                  <input type="checkbox" class="checkbox" value="<c:out value='${ productList.productCode }'/>">
                </div>
                <a href="javascript:openNewPage( `./product_list/detail?productCode=<c:out value='${ productList.productCode }'/>`, 1200, 900 )">
                  <span class="cell_image <c:if test='${ empty productList.imageFileList[0]}'>empty</c:if>"> 
                      <c:choose>
                        <c:when test="${ empty productList.imageFileList[0] }">
                          이미지가 없습니다.
                        </c:when>
                        <c:otherwise>
                          <img src="/products/<c:out value='${ productList.imageFileList[0] }'/>" alt="상품 이미지" loading="lazy" />
                        </c:otherwise>
                      </c:choose>
                  </span>
                </a>
                <span>코드 : <c:out value="${ productList.productCode }"/></span>
                <span>분류 : <c:out value="${ productList.parentCategory }"/> / <c:out value="${ productList.childCategory }"/></span>
                <span>가격 : <c:out value="${ productList.productPrice }"/>원</span>
                <span>재고량 : <c:out value="${ productList.numberOfStock }"/>개</span>
                <span>등록일 : <c:out value="${ productList.addedDate }"/></span>
              </span>
            </div>

            <c:if test="${ status.count % 5 > 0 && status.last }">
              <c:forEach begin="1" end="${ 5 - status.count % 5 }">
                <div class="cell empty">상품이 없습니다.</div>
              </c:forEach>
            </c:if>

            <c:if test="${ status.count % 5 == 0 && !status.last }">
              </div>
              <div class="row">
            </c:if>

            <c:if test="${ status.last }">
              </div>
            </c:if>
          </c:forEach>

          <c:if test="${ relationInstance == null or fn:length( relationInstance ) == 0 }">
            <div class="row">
              <div class="cell empty">상품이 없습니다.</div>
              <div class="cell empty">상품이 없습니다.</div>
              <div class="cell empty">상품이 없습니다.</div>
              <div class="cell empty">상품이 없습니다.</div>
              <div class="cell empty">상품이 없습니다.</div>
            </div>
          </c:if>
        </section>
      </c:otherwise>
    </c:choose>

    <!-- ##### 테이블 하단 섹션 ##### -->
    <section class="table_bottom">
      <div class="button-placement">
        <button 
          class="brand-pink" 
          onclick="deleteTheseRecords( <c:out value='${pagination.currentPage}'/> )">
          선택항목 삭제
        </button>

        <!-- ##### 페이지 매김 기능 ##### --> 
        <c:import url="./paginate.jsp" />
        <button class="brand-mint" onclick="openNewPage( 'add_product', 1200, 900 )">상품 등록</button>
      </div>
    </section>
  </div>