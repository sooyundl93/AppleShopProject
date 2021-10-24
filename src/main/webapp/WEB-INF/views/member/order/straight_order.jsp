<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    

<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/login.css">
<link rel="stylesheet" href="/css/order.css">
       
<!--------------------------------------- MAIN -------------------------------------->
<div id="container">
    <!---------------------------------- page_title ------------------------------------>
    <div class="page_title">                    
        <h2>주문/결제</h2>
        <div class="breadcrumb">
            <div class="location_content">
                <span>장바구니</span>       
                <span class="location_arrow">&gt; </span>
                <span class="current">주문서작성/결제</span>
                <span class="location_arrow">&gt; </span>
                <span>주문완료</span>
            </div>
        </div>
        <div class="line"></div>
    </div> 
    
    <form action="straight_order_action" method = "post" class="join2">
        <!--*****************-->
        <!--* 주문정보 메인 *-->
        <!--*****************-->    
        <div class="item_table">
        
            <!-- 주문한 상품 정보 -->
            <div class="item_tr">
                
                    <div class="item_td">
                        <img src="/products/${ product_image.image_file_name }" alt="상품 이미지">
                    </div>
                    
                    <div class="item_text">
                        <div class="item_td">
                            ${ product_name }
                        </div>
                        <div class="item_td">
                            ${ number_of_products }개
                        </div>
                        <div class="item_td">
                            <fmt:formatNumber value = "${ total_price }" pattern = "#,###"/>원
                        </div>
                    </div>
                
                    <input type="hidden" name = "product_code" value = "${ product_code }"/>
                    <input type="hidden" name = "number_of_products" value = "${ number_of_products }"/>
                
            </div>
        </div>
    
        <!--************-->
        <!--*주문자 정보*-->
        <!--************-->  
        <div class="table">
            <!-- header -->
            <div class="head">배송지</div>   

            <!-- 수령인 -->
            <div class="tr">
                <div class="th address">수령인</div>                
                <div class="td">
                    <input type="text" class="length1" name="user_name" value="${ member_info.user_name }">
                </div>
            </div>
            
            <!-- 연락처 -->
            <div class="tr">
                <div class="th address">연락처</div>                
                <div class="td">
                    <input type="number" class="length1" name="phone_number" value="${ member_info.phone_number }">
                </div>
            </div>            

            <!-- 주소 -->
            <div class="tr">
                <div class="th">주소</div>                
                <div class="td">
                    <div class="upper">
                        <input type="text" id="sample6_postcode" name="address" value="${ member_info.address }">
                        <a href="javascript:sample6_execDaumPostcode()">주소 검색</a>
                    </div>
                    
                    <input type="text" id="sample6_address"  name="postal_code" value="${ member_info.postal_code }" readonly>                
                    <input type="hidden" id="sample6_extraAddress" readonly>
                    
                    <input type="text" id="sample6_detailAddress" name="detailed_address" value="${ member_info.detailed_address }">
                </div>
            </div>
            
            <!-- 배송 메모 -->
            <div class="tr">
                <div class="th address">배송메모</div>
                
                <div class="td">
                    <input type="text" class="length1" name = "shipping_memo">
                </div>
            </div>            
        </div>        
        
        <!--************-->
        <!--* 마일리지 *-->
        <!--************-->
        <div class="table">
            <div class="head">마일리지</div>  
            <div class="tr">
                <div class="th">마일리지 적용</div>
                <div class="td">
                    사용 가능 마일리지 ${ member_info.mileage }
                    <div class="upper">
                        <input type="text" id="mileage" value="0">
                        <input type="hidden" id="all_mileage" value="${ member_info.mileage }" />
                        <a onclick="use_mileage()">확인</a>
                        <a onclick="use_all_mileage()" class="btn_line">전체적용</a>
                    </div>
                </div>
            </div>        
        </div>
        
        <!--******************-->
        <!--* 결제 금액 정보 *-->
        <!--******************-->
        <div class="table order_table">
            <div class="tr">
                <div class="order_th">총 상품 금액</div>
                <div class="order_tr">
                    <fmt:formatNumber value="${ total_price }" pattern="#,###"/>
                    <input type="hidden" id="total_price" name="total_price" value="${ total_price }"/>
                </div>
            </div>
            
            <div class="tr">
                <div class="order_th">배송비</div>
                <div class="order_tr">
                    <fmt:formatNumber value="${ shipping_cost }" pattern="#,###"/>
                    <input type="hidden" id="shipping_cost" name="shipping_cost" value="${ shipping_cost }"/>
                </div>
            </div>
            
            <div class="tr">
                <div class="order_th">적용 마일리지</div>
                <div class="order_tr">
                    <span id="used_mileage"></span>
                    <input type="hidden" id="input_used_mileage" name="input_used_mileage"/>
                </div>
            </div>
            
            <div class="tr">
                <div class="order_th">총 결제 금액</div>
                <div class="order_tr">
                    <span id="discounted_price"></span>
                    <input type="hidden" id="input_discounted_price" name="input_discounted_price"/>     
                </div>
            </div>
        </div>
        
        <!--************-->
        <!--* 결제수단 *-->
        <!--************-->
        <div class="table payment_table">
            
            <!-- 결제 수단 선택 -->
            <div class="payment_method">
                <div class="th">결제수단</div>
                <div class="td">
                    <!-- 신용카드 -->
                    <div class="payment_button">
                        <label class="box-radio-input">
                            <input type="radio" name="payment_method" value="1" checked="checked">
                            <span>신용카드</span>
                        </label>
                    </div>
                    
                    <!-- 카카오페이 -->
                    <div class="payment_button">
                        <label class="box-radio-input">
                            <input type="radio" name="payment_method" value="2">
                            <span><img src="/Images/main/kakao_icon.png" alt="카카오페이 이미지">카카오페이</span>
                        </label>
                    </div>
                    
                    <!-- 무통장입금 -->
                    <div class="payment_button">
                        <label class="box-radio-input">
                            <input type="radio" name="payment_method" value="3">
                            <span>무통장입금</span>
                        </label>
                    </div>
                </div>                
            </div>
        </div>

        <!--********************-->
        <!--* 결제 Action 버튼 *-->
        <!--********************-->
        <div class="confirm">
            <div class="agree">위 주문 내용을 확인하였으며, 회원 본인은 결제에 동의합니다.</div>
            <div class="finish_button">
                <button type="submit" class="btn">
                    <span id="discounted_price_btn"></span>원 결제하기
                </button>
            </div>
        </div>
    </form>
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- 우편주소 API 스크립트 -->
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function (data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if (data.userSelectedType === 'R') {
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;

                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>

<!-- 총 결제 금액 계산 스크립트 -->
<script>

    var total_price 		   = document.getElementById('total_price');
    var mileage 			   = document.getElementById('mileage');
    var all_mileage 		   = document.getElementById('all_mileage');
    var shipping_cost		   = document.getElementById('shipping_cost');
    var used_mileage 		   = document.getElementById('used_mileage');
    
    var input_discounted_price = document.getElementById('input_discounted_price');
    
    used_mileage.innerText = numberWithCommas(mileage.value);
    
    var default_sum = Number(total_price.value) + Number(shipping_cost.value) - Number(mileage.value);
    
    discounted_price.innerText = numberWithCommas(default_sum);
    discounted_price_btn.innerText = numberWithCommas(default_sum);
    
    document.getElementById('input_used_mileage').value = Number(mileage.value);
    document.getElementById('input_discounted_price').value = Number(default_sum);
    
    /* 마일리지 일부 사용 */
    function use_mileage(){    		
        if(Number(mileage.value) > Number(all_mileage.value)){
            alert("소유한 마일리지보다 큰 값을 넣을 수 없습니다.");
            console.log(mileage.value);
            console.log(all_mileage.value);
            return;
        }
        
        used_mileage.innerText = numberWithCommas(mileage.value);
        
        var sum = Number(total_price.value) + Number(shipping_cost.value) - Number(mileage.value);
        
        discounted_price.innerText = numberWithCommas(sum);
        discounted_price_btn.innerText = numberWithCommas(sum);
        
        document.getElementById('input_used_mileage').value = Number(mileage.value);
        document.getElementById('input_discounted_price').value = Number(sum);
    }
    
    /* 마일리지 전부 사용 */
    function use_all_mileage(){
        mileage.value = all_mileage.value;
        used_mileage.innerText = numberWithCommas(all_mileage.value);
        
        var sum = Number(total_price.value) + Number(shipping_cost.value) - Number(all_mileage.value);
                    
        discounted_price.innerText = numberWithCommas(sum);
        discounted_price_btn.innerText = numberWithCommas(sum);

        document.getElementById('input_used_mileage').value = Number(all_mileage.value);
        document.getElementById('input_discounted_price').value = Number(sum);
    }
    
    /* 3자리 마다 콤마 찍기 */
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    
</script>