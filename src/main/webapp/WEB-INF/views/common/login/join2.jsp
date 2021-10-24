<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %> 

<link rel="stylesheet" href="/css/login.css">
<link rel="stylesheet" href="/css/common.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<!--------------------------------------- MAIN -------------------------------------->
<div id="container">
    <!---------------------------------- page_title ------------------------------------>
    <div class="page_title">                    
        <h2>회원가입</h2>
        <div class="breadcrumb">
            <div class="location_content">
                <span>약관동의</span>       
                <span class="location_arrow">&gt; </span>
                <span class="current">정보입력</span>
                <span class="location_arrow">&gt; </span>
                <span>가입완료</span>
            </div>
        </div>
        <div class="line"></div>
    </div>

    <!-- 회원가입 -->
    <form action="joinAction" method="post" name="userInfo" 
    class="join2"
    onsubmit="return checkValue(); return false;">
        <!-- 아이디 -->
        <div class="tr">
            <div class="th">아이디</div>                
            <div class="td">                
                <div class="upper">
                    <input type="text" name="user_id" id="user_id"
                    placeholder="6-20자리이내" onchange="chkID();" />
                    <input type="hidden" id="user_id_check" value="0">
                    <a href="javascript:idCheck()">중복확인</a>
                </div>

                <!-- "아이디를 입력해주세요 / 중복된 아이디입니다" 메세지 -->
                <div id="idDuplicate"></div>
            </div>
        </div>    

        <!-- 비밀번호 -->
        <div class="tr">
            <div class="th">비밀번호</div>                
            <div class="td">
                <input type="password" name="user_password" id="password1" 
                onchange="chkPW();" onkeyup="passwordCheck();" 
                placeholder="8~20자 숫자와 대소문자,특수문자를 포함해주세요" />
                   <input type="hidden" id="password_check1" value="0">
                <div id="pwDuplicate1"></div>  
            </div>
        </div>   

        <!-- 비밀번호 확인 -->
        <div class="tr">
            <div class="th">비밀번호 확인</div>                
            <div class="td">
                <input type="password" id="password2" onkeyup="passwordCheck();" />
                <input type="hidden" id="password_check2" value="0">
                <div id="pwDuplicate2"></div>  
            </div>
        </div>   

        <!-- 이름 -->
        <div class="tr">
            <div class="th">이름</div>                
            <div class="td">
                <input type="text" name="user_name" id="user_name" onchange="chkName();" />
                <input type="hidden" id="user_name_check" value="0">
            </div>
        </div>
        
        <!-- 성별 -->
		<div class="tr radio">
			<div class="th">성별</div>			
			<div class="td">
				<input type="radio" name="gender" id="gender1" value="남" onclick="genderCheck();" /><label for="gender">남자</label>
				<input type="radio" name="gender" id="gender2" value="여" onclick="genderCheck();" /><label for="gender">여자</label>
                <input type="hidden" id="gender_check" value="0">
                <div id="genderCheckMessage"></div>  
			</div>
		</div>

        <!-- 생년월일 -->
        <div class="tr">
            <div class="th">생년월일</div>            
            <div class="td">
                <input type="date" name="birthday" id="birthday" />
            </div>
        </div>
        
        <!-- 이메일 -->
        <div class="tr">
            <div class="th">이메일</div>                
            <div class="td">
                <div class="upper">
                    <input type="text" name="email" id="email" onchange="chkMail();" /> 
                    <input type="hidden" id="user_email_check" value="0">
                    <a href="javascript:emailCheck()">중복확인</a>
                </div>

                <!-- "이메일을 입력해주세요 / 중복된 이메일입니다" 메세지 -->
                <div id="emailDuplicate"></div>                

                <label for="email_recieve">
                    <input type="checkbox" id="email_recieve" name="receive_email_flag" value="Y">
                    정보/이벤트 메일 수신에 동의합니다. (미동의시에도 배송,주문,알림정보는 발송됩니다.)
                </label>
            </div>               
        </div>
        
        <!-- 연락처 -->
        <div class="tr">
            <div class="th">연락처</div>                
            <div class="td">
                <input type="text" name="phone_number" id="phone_number" maxlength="11" onKeyup="this.value=this.value.replace(/[^0-9]/gi,'');" onkeydown="return chkNumber();" placeholder="-을 제외하고 입력해주세요" />    
                <input type="hidden" id="phone_number_check" value="0" />            
                <label for="phone_receive">
                    <input type="checkbox" id="phone_receive" name="receive_messages_flag" value="Y" />
                    정보/이벤트 sms 수신에 동의합니다. (미동의시에도 배송,주문 알림정보는 발송됩니다)
                </label>
            </div>
        </div>
        
        <!-- 주소 -->
        <div class="tr">
            <div class="th">주소</div>                
            <div class="td">
                <div class="upper">
                    <input type="text" id="sample6_postcode" name="postal_code" required/>
                    <a href="javascript:sample6_execDaumPostcode()">주소 검색</a>
                </div>
                
                <input type="text" id="sample6_address"  name="address" />                
                <input type="hidden" id="sample6_extraAddress">
                
                <input type="text" id="sample6_detailAddress" name="detailed_address" placeholder="상세주소" />      
            </div>
        </div>
        
        <!-- 비밀번호 찾기 질문 -->
        <div class="tr select">
            <div class="th">비밀번호 찾기 질문</div>                
            <div class="td">
                <select id="pw_question" name="pw_question">
                    <option value="01">내 보물 1호는?</option>
                    <option value="02">내가 가장 좋아하는 영화 제목은?</option>
                    <option value="03">첫 애완동물 이름은?</option>
                    <option value="04">내가 가장 존경하는 인물은?</option>
                    <option value="05">가장 기억에 남는 선생님 성함은?</option>
                    <option value="06">내가 졸업한 초등학교 이름은?</option>
                    <option value="07">내가 졸업한 고등학교 이름은?</option>
                    <option value="08">내가 처음 사용한 핸드폰 번호는?</option>
                    <option value="09">아버지 성함은?</option>
                    <option value="10">어머니 성함은?</option>
                </select>
                <div>
                    <input type="text" id="pw_answer" name="pw_answer" required/>         
             		<input type="hidden" id="answer_check" value="0">
                </div>
            </div>
        </div>

        <div class="finish_button">
            <input type="submit" name="submit" id="submit" class="btn" value="확인">
        </div>
        
    </form>
</div>


<script>   
	function chkID(){
		if(!document.getElementById("user_id").value){
			$("#idDuplicate").html("아이디를 입력해주세요").css('color', '#FE4F79');	
			document.getElementById("user_id_check").value = "0";		
		}else{
			$("#idDuplicate").html("아이디 중복체크를 해주세요").css('color', '#FE4F79');	
			document.getElementById("user_id_check").value = "0";				
		}
	}
	
    function idCheck(){		
		var user_id = $('#user_id').val();		
        var pattern = user_id.search(/[a-zA-Z0-9]/);
        var kor = user_id.search(/[가-힣]/g);
        var spe = user_id.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		$.ajax({
			url: 'http://localhost:8090/idCheck?user_id='+ user_id,
			type: 'get',
			success: function(data){
				console.log("1:중복됨, 0:중복안됨 data:"+data);				
				if(data == 1){
					//아이디 중복됨			
           			alert("중복되는 아이디입니다");
					$("#idDuplicate").html("중복되는 아이디입니다").css('color', '#FE4F79');
					document.getElementById("user_id_check").value = "0";
				}
                else if(user_id ==""){				
           			alert("아이디를 입력해주세요");
					$("#idDuplicate").html("아이디를 입력해주세요").css('color', '#FE4F79');
					document.getElementById("user_id_check").value = "0";
				}
                else if (pattern != 0 || spe > 0 || kor > 0) {
           			alert("아이디를 다시 입력해주세요");
					$("#idDuplicate").html("아이디를 다시 입력해주세요").css('color', '#FE4F79');
					document.getElementById("user_id_check").value = "0";
                }
                else if (user_id.length < 6 || user_id.length > 20) {
           			alert("아이디를 다시 입력해주세요");
					$("#idDuplicate").html("아이디를 다시 입력해주세요").css('color', '#FE4F79');
					document.getElementById("user_id_check").value = "0";
                }
                else if (user_id.search(/\s/) != -1) {
           			alert("아이디를 다시 입력해주세요");
					$("#idDuplicate").html("아이디를 다시 입력해주세요").css('color', '#FE4F79');
					document.getElementById("user_id_check").value = "0";
                }else{
					//아이디 중복 안됨.		
           			alert("사용가능한 아이디입니다");
					$("#idDuplicate").html("사용가능한 아이디입니다").css('color', '#30CED8');
					 document.getElementById("user_id_check").value = "1";
				}
			},
			error: function(){
       			alert("아이디를 다시 입력해주세요");
			}				
		});
	}

    function passwordCheck(event) {
        var p1 = $("#password1").val();
        var p2 = $("#password2").val();  
        if (p1 == p2) { 
            document.getElementById("password_check2").value = "1";
            $("#pwDuplicate2").html("비밀번호가 일치합니다.").css('color', '#30CED8');
        }
        else { 
            document.getElementById("password_check2").value = "0";
            $("#pwDuplicate2").html("비밀번호가 일치하지 않습니다").css('color', '#FE4F79');
        }
    }
    
    function chkPW() {
        var pw = $("#password1").val();
        var num = pw.search(/[0-9]/g);
        var eng = pw.search(/[a-z]/ig);
        var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

        if (pw.length < 8 || pw.length > 20) {
            document.getElementById("password_check1").value = "0";
            $("#pwDuplicate1").html("비밀번호는 8자리 ~ 20자리 이내로 입력해주세요").css('color', '#FE4F79');
            alert("비밀번호는 8자리 ~ 20자리 이내로 입력해주세요");
            
        } else if (pw.search(/\s/) != -1) {
            document.getElementById("password_check1").value = "0";
            $("#pwDuplicate1").html("비밀번호는 공백없이 입력해주세요").css('color', '#FE4F79');
            alert("비밀번호는 공백없이 입력해주세요");
            
        } else if (num < 0 || eng < 0 || spe < 0) {
            document.getElementById("password_check1").value = "0";
            $("#pwDuplicate1").html("비밀번호는 영문,숫자,특수문자를 혼합하여 입력해주세요").css('color', '#FE4F79');
            alert("비밀번호는 영문,숫자,특수문자를 혼합하여 입력해주세요");
            
        } else {
            document.getElementById("password_check1").value = "1";
            $("#pwDuplicate1").html("사용가능한 비밀번호입니다").css('color', '#30CED8');
        }
    }
    
    function chkName() {
        var name = $("#user_name").val();
        var kor = name.search(/[가-힣]/g);
        var num = name.search(/[0-9]/g);
        var eng = name.search(/[a-z]/ig);
        var spe = name.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

        if (name.length < 2 || name.length > 4) {
            alert("이름은 2~4자 이내로 입력해주세요.");
            document.getElementById("user_name_check").value = "0";
        } else if (name.search(/\s/) != -1) {
            alert("이름은 공백 없이 입력해주세요.");
            document.getElementById("user_name_check").value = "0";
        } else if (kor < 0 || num > 0 || eng > 0 || spe > 0) {
            alert("이름은 한글로만 입력해주세요.");
            document.getElementById("user_name_check").value = "0";
        }else{
            document.getElementById("user_name_check").value = "1";        	
        }
    }    
    
    function genderCheck(){
    	var gender = $("input[name=gender]:checked").val();
    	if(gender =="남" || gender == "여"){
    		$("#genderCheckMessage").html("");   
            document.getElementById("gender_check").value = "1";   
            return true;
    	}else{
            document.getElementById("gender_check").value = "0";       		
    	}
    }
    
    function chkMail(){
		if(!document.getElementById("email").value){
			$("#emailDuplicate").html("이메일 주소를 입력해주세요").css('color', '#FE4F79');	
			document.getElementById("user_email_check").value = "0";		
		}else{
			$("#emailDuplicate").html("이메일 중복체크를 해주세요").css('color', '#FE4F79');	
			document.getElementById("user_email_check").value = "0";				
		}    	
    }
    
	function emailCheck(){		
		var email = $('#email').val();		
        var kor = email.search(/[가-힣]/g);
        var num = email.search(/[0-9]/g);
        var eng = email.search(/[a-z]/ig);
        var spe = email.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		$.ajax({
			url: 'http://localhost:8090/emailCheck?email='+ email,
			type: 'get',
			success: function(data){
				console.log("1:중복됨, 0:중복안됨 data:"+data);
				
				if(data == 1){
					//아이디 중복됨
					alert('중복되는 이메일입니다');					
					$("#emailDuplicate").html("중복되는 이메일입니다").css('color', '#FE4F79');
					document.getElementById("user_email_check").value = "0";
				}
                else if(email ==""){
					alert('이메일을 입력해주세요');					
					$("#emailDuplicate").html("이메일을 입력해주세요").css('color', '#FE4F79');
					document.getElementById("user_email_check").value = "0";
				}
                else if (email.search(/\s/) != -1) {
                    alert("이메일은 공백 없이 입력해주세요");
					$("#emailDuplicate").html("이메일은 공백 없이 입력해주세요").css('color', '#FE4F79');
					document.getElementById("user_email_check").value = "0";
                } 
                else if (email.search(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/gi)) {
                    alert("이메일 주소를 확인해주세요");
					$("#emailDuplicate").html("이메일 주소를 확인해주세요").css('color', '#FE4F79');
					document.getElementById("user_email_check").value = "0";
                }else{					
					alert('사용가능한 이메일입니다');		
					$("#emailDuplicate").html("사용가능한 이메일입니다").css('color', '#30CED8');
					document.getElementById("user_email_check").value = "1";
				}
			},
			error: function(){
                alert("이메일 주소를 확인해주세요");
			}				
		});
	}
	   
    function chkNumber(event) {
        if((event.keyCode > 48 && event.keyCode < 57 ) 
   	      || event.keyCode == 8 //backspace
   	      || event.keyCode == 37 
   	      || event.keyCode == 39 //방향키 →, ←
   	      || event.keyCode == 46 //delete키
   	      || event.keyCode == 39){
   	   }else{
			event.returnValue=false;
   	   }
    }
    

	function checkValue(){
		if (document.getElementById("user_id_check").value =="1" 
        && (document.getElementById("password_check1").value =="1" && document.getElementById("password_check2").value =="1")
        && document.getElementById("user_name_check").value =="1"
        && document.getElementById("user_email_check").value =="1"
       	&& document.getElementById("answer_check").value =="1"){
			return true;
		}else if (!document.getElementById("user_id").value){
			alert("아이디를 입력해주세요");
			$("#idDuplicate").html("test 아이디를 입력해주세요").css('color', '#FE4F79');
			setTimeout( function(){ $("#user_id").focus(); }, 20 );
			return false;
		}else if (document.getElementById("user_id_check").value=="0"){
			alert("아이디 중복체크를 해주세요");
			$("#idDuplicate").html("test 아이디 중복체크를 해주세요").css('color', '#FE4F79');
			setTimeout( function(){ $("#user_id").focus(); }, 20 );
			return false;
		}else if (!document.getElementById("password1").value){
			alert("비밀번호를 입력해주세요");
			$("#pwDuplicate").html("test 비밀번호를 입력해주세요").css('color', '#FE4F79');
			setTimeout( function(){ $("#password1").focus(); }, 20 );
			return false;
		}else if (document.getElementById("password_check1").value=="0"){
			alert("비밀번호를 확인해주세요");
			setTimeout( function(){ $("#password1").focus(); }, 20 );
			return false;
		}else if (document.getElementById("password_check2").value=="0"){
			alert("비밀번호를 확인해주세요");
			setTimeout( function(){ $("#password2").focus(); }, 20 );
			return false;
		}else if (!document.getElementById("password2").value){
			alert("비밀번호 확인을 입력해주세요");
			setTimeout( function(){ $("#password2").focus(); }, 20 );
			return false;
		}else if (!document.getElementById("user_name").value){
			alert("이름을 입력해주세요");
			setTimeout( function(){ $("#user_name").focus(); }, 20 );
			return false;
		}else if (document.getElementById("user_name_check").value=="0"){
			alert("이름을 다시 입력해주세요");
			setTimeout( function(){ $("#user_name").focus(); }, 20 );
			return false;
		}else if (document.getElementById("gender_check").value =="0"){
			alert("성별을 선택해주세요");
			setTimeout( function(){ $("#gender1").focus(); }, 20 );
			return false;
		}else if (!document.getElementById("birthday").value){
			alert("생년월일을 입력해주세요");
			setTimeout( function(){ $("#birthday").focus(); }, 20 );
			return false;            
		}else if (!document.getElementById("email").value){
			alert("이메일 주소를 입력해주세요");
			setTimeout( function(){ $("#email").focus(); }, 20 );
			return false;
		}else if (document.getElementById("user_email_check").value =="0"){
			alert("이메일 중복체크를 해주세요");
			setTimeout( function(){ $("#email").focus(); }, 20 );
			return false;
		}else if (!document.getElementById("phone_number").value){
			alert("연락처를 입력해주세요");
			setTimeout( function(){ $("#phone_number").focus(); }, 20 );
			return false;
        }else if (!document.getElementById("sample6_address").value){
            alert("주소를 입력해주세요");
            setTimeout( function(){ $("#sample6_address").focus(); }, 20 );
            return false;
        }else if (!document.getElementById("sample6_detailAddress").value){
            alert("상세주소를 입력해주세요");
            setTimeout( function(){ $("#sample6_detailAddress").focus(); }, 20 );
            return false;
        }
	}
    
	window.onload = function () {
		$("#idDuplicate").html("아이디 중복체크를 해주세요.").css('color', '#FE4F79');
		$("#genderCheckMessage").html("성별을 선택해주세요.").css('color', '#FE4F79');
		$("#emailDuplicate").html("이메일 중복체크를 해주세요.").css('color', '#FE4F79');
	}
</script>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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