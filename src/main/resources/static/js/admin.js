// ### 현재 페이지 설정하는 온로드 함수 ###
$(function() {
  console.log( "이전 주소 : " + document.referrer);
  let currentUrl = currentPath().split('?');
  let pageNameArray = [];
  switch ( currentUrl[0] ) {
    case 'member_list': pageNameArray = ["관리자 페이지", "회원 관리"];
      break;
    case 'product_list': pageNameArray = ["관리자 페이지", "상품 관리"];
      highlightButton();
      break;
    case 'order_list': pageNameArray= ["관리자 페이지", "주문 관리"];
      break;
    case 'review_list': pageNameArray = ["관리자 페이지", "회원리뷰 관리"];
      break;
    case 'notice_list': pageNameArray = ["관리자 페이지", "공지사항 관리"];
      break;
    case 'notice_list/writing': pageNameArray = ["관리자 페이지", "공지사항 관리", "글 쓰기"];
      createCkeditor5();
      break;
    case 'notice_list/detail': pageNameArray = ["관리자 페이지", "공지사항 관리", "글 보기"];
      break;
    case 'notice_list/edit': pageNameArray = ["관리자 페이지", "공지사항 관리", "글 수정"];
      createCkeditor5();
      break;
    case 'product_inquiry_list': pageNameArray = ["관리자 페이지", "상품문의 관리"];
      createCkeditor5ExcludeImage();
      break;
    case 'one2one_inquiry_list': pageNameArray = ["관리자 페이지", "1:1문의 관리"];
      createCkeditor5ExcludeImage();
      break;
    default: pageNameArray = ["관리자 페이지", "기타"];
      break;
  }
  createPageSign( '.main__navigation', pageNameArray );
  highlightCurrentMenu( pageNameArray[1] );
  let logText = "# ----- 페이지 불러오기 성공 ----- #" + "\n"
              + "현재 위치 : " + currentUrl + "\n";
  console.log(logText);
});

// ### 현재 페이지에서 ckeditor클래스 개수만큼  CKEditor 생성 ###
function createCkeditor5() {
  window.editors = {};
  $('.ckeditor').each( function( index ) {
    ClassicEditor
    .create( this, {
      extraPlugins: [ MyCustomUploadAdapterPlugin ],
      toolbar: {
        items: [
          'heading', '|', 'fontFamily', 'fontSize', 'fontColor', 'fontBackgroundColor', '|', 
          'bold', 'italic', 'underline', 'alignment', 'horizontalLine', '|', 'link', 'mediaEmbed', 'imageUpload',
          '|', 'undo', 'redo'		
        ]
      },
      language: 'ko',
      fontSize: { 
        options: [ '8px', '12px', '14px', 'default', '18px', '20px', '22px', '24px', '26px', '28px', '30px', '32px'] 
      },
      mediaEmbed: {
        previewsInData: true
      }
    } )
    .then( editor => {
      window.editors[ index ] = editor;
    })
    .catch(error => {
      console.error(error);
    });
    console.log( index + "번째 글쓰기 에디터(CKEditor5) 생성" );
  });
}

function createCkeditor5ExcludeImage() {
  window.editors = {};
  $('.ckeditor').each( function( index ) {
    ClassicEditor
    .create( this, {
      toolbar: {
        items: [
          'heading', '|', 'fontFamily', 'fontSize', 'fontColor', 'fontBackgroundColor', '|', 
          'bold', 'italic', 'underline', 'alignment', 'horizontalLine', '|', 'link', 'mediaEmbed',
          '|', 'undo', 'redo'		
        ]
      },
      language: 'ko',
      fontSize: { 
        options: [ '8px', '12px', '14px', 'default', '18px', '20px', '22px', '24px', '26px', '28px', '30px', '32px'] 
      },
      mediaEmbed: {
        previewsInData: true
      }
    } )
    .then( editor => {
      window.editors[ index ] = editor;
    })
    .catch(error => {
      console.error(error);
    });
    console.log( index + "번째 글쓰기 에디터(CKEditor5) 생성" );
  });
}

// ### 현재 페이지 표시 생성 ###
function createPageSign( divSelector, pageNameArray ) {
  let createNextSign = '<img class="generated_next_sign"></img>';
  let createPageName = '<span class="generated_page_sign"></span>';

  if( pageNameArray[0] ) {
    $( divSelector ).html( createPageName );
    $( divSelector ).find('.generated_page_sign').last().html( pageNameArray[0] );
  }
  if( pageNameArray[1] ) {
    $( divSelector ).append( createNextSign );
    $( divSelector ).append( createPageName );
    $( divSelector ).find('.generated_page_sign').last().html( pageNameArray[1] );
  }
  if( pageNameArray[2] ) {
    $( divSelector ).append( createNextSign );
    $( divSelector ).append( createPageName );
    $( divSelector ).find('.generated_page_sign').last().html( pageNameArray[2] );
  }
}

// ### 현재 메뉴를 강조하는 함수 ###
function highlightCurrentMenu(pageName) {
  $('#menu').find('li').each(function (index) {
    if($(this).html() == pageName) {
      $(this).addClass('selected');
      $(this).parent().addClass('selected');
      currentMenuIndex = index;
    } else {
      $(this).removeClass('selected');
    }
  });
}

function currentPath() {
  let fullPath = window.location.href;
  let index = fullPath.indexOf("admin/", 0);
  let currentAdress = fullPath.substring(index + 6);
  return currentAdress;
}

function confirmSendEmail(userId, userName, userEmail) {
  if ( confirm( 
      "사용자 : " + userId + "(" + userName + ")" + "\n"
      + "이메일 : " + userEmail + "\n\n"
      + "확인을 누르면 사용자의 이메일로 바로 전송됩니다." + "\n"
      + "정말로 임시 비밀번호를 발급하시겠습니까?"
    ) ) 
  {
    createTemporaryPassword(userId, userName, userEmail);
    showLoadingScreen(true);
  } else {
    let logText = "# ----- 이메일 전송 취소 ----- #" + "\n"
                + "이메일 전송을 취소하였습니다." + "\n";
    let alertText = "취소 하였습니다.";
    console.log(logText);
    alert(alertText);
  }
}

function MyCustomUploadAdapterPlugin(editor) {
  editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
      return new UploadAdapter(loader)
  }
}

// ### 임시 비밀번호 발급을 위한 함수 ###
function createTemporaryPassword(userId, userName, userEmail) {
$.ajax({
  type: 'POST',
  url : 'createTemporaryPassword',
  data: {    
          "userId"     : userId,
          "userName"   : userName, 
          "userEmail"  : userEmail
  }, success: function (data) {
    let logText = "# ----- 이메일 전송 성공 ----- #" + "\n"
                + userEmail + "으로 임시 비밀번호를 전송하였습니다." + "\n";
    let alertText = userEmail + "으로 임시 비밀번호를 발급하였습니다.";
    showLoadingScreen(false);
    console.log(logText);
    alert(alertText);
  }, error: function (request, status, error) {
    let alertText = "오류! 이메일 전송에 실패하였습니다." + "\n"
                  + "자세한 오류 내용은 로그를 참조해주십시오."
    let logText = "# ----- 이메일 전송 실패 ----- #" + "\n"
                + "code : " + request.status + "\n" 
                + "status : " + status + "\n"
                + "error : " + error + "\n"
                + "message : " + request.responseText + "\n";
    showLoadingScreen(false);
    alert(alertText);
    console.log(logText);
  }
});
}

// ### 로딩화면 설정 함수 ###
function showLoadingScreen(loadingCheck) {
  (loadingCheck)
  ? $(".loading_screen_background").show()
  : $(".loading_screen_background").hide();
}

// ### 상세페이지 팝업 열어주는 함수 ###
function openNewPage(url, width, height) {
  let popupX = (window.screen.width / 2) - (height / 2);
  let popupY= (window.screen.height / 3) - (width / 3);
  let name = "_black";
  let option = "width=" + width + ", height=" + height 
            + ", top=" + popupY + ", left=" + popupX;
  window.open(url, name, option);
}

function getValueOfSelector(selector) {
  return $(selector).val();
}

function changeHiddenValue( viewForm, currentPage ) {
  $('#view_mode').val( viewForm );
  getListContents( currentPage );
}

// ### 테이블만 새로고침하는 함수 ###
function getListContents( currentPage, targetUrl, searchOption, searchKeyword ) {
  let numberOfRecordsPerPage = $('#numberOfRecordsPerPage').val();
  let currentSort = $('#current_sort').val();
  let parentCategory = $('#parent_category').val();
  let childCategory = $('#child_category').val();
  let toThisDate = $('#to-this-date').val();
  let fromThisDate = $('#from-this-date').val();
  let viewMode = $('#view_mode').val();
  if( targetUrl == undefined ) targetUrl = currentPath();
  if( searchOption == undefined ) searchOption = $('#search_option').val();
  if( searchKeyword == undefined ) searchKeyword = $('#search_keyword').val();
  $.ajax({
    type: 'POST',
    url : targetUrl,  //전송할 url
    data: {               // 전송할 데이터
      "current_page"                : currentPage,
      "number_of_records_per_page"  : numberOfRecordsPerPage, 
      "search_option"               : searchOption,
      "search_keyword"              : searchKeyword,
      "current_sort"                : currentSort,
      "parent_category"             : parentCategory,
      "child_category"              : childCategory,
      "from_this_date"              : fromThisDate,
      "to_this_date"                : toThisDate,
      "view_mode"                   : viewMode
    }, success: function (data) {
      let findTable = $("#contents-wrap").html(data).find('#contents-wrap');
      $('#contents-wrap').html(findTable);
      let logText = "# ----- 콘텐츠 불러오기 성공 ----- #" + "\n"
                  + "현재 위치 : " + targetUrl + ", " + currentPage + "페이지\n"
                  + "페이지 당 레코드 수 : " + numberOfRecordsPerPage + "\n"
                  + "검색 옵션 : " + searchOption + "\n"
                  + "검색 키워드 : " + searchKeyword + "\n"
                  + "현재 정렬 : " + currentSort + "\n";
      if( parentCategory != undefined ) {
        logText += "현재 카테고리 : " + parentCategory + "/" +  childCategory + "\n";
      }
      if( toThisDate != undefined ) {
        logText += "조회 날짜 : " + fromThisDate + " ~ " + toThisDate + "\n";
      }
      if( viewMode != undefined ) {
        logText += "뷰모드 : " + viewMode + "\n";
      }
      console.log(logText);
      history.pushState(null, null, '/admin/'+ targetUrl);
    },
    error: function (request, status, error) {
      let logText = "# ----- 콘텐츠 불러오기 실패 ----- #" + "\n"
                  + "code : " + request.status + "\n" 
                  + "status : " + status + "\n"
                  + "error : " + error + "\n"
                  + "message : " + request.responseText + "\n";
      console.log(logText);
    }
  });
}

// ### 레코드 업데이트 함수 ###
function updateThisRecord( primaryKey, value, currentPage ) {
  let currentContent = currentPath();
  let logText = "# ----- 플래그 상태 스위칭 준비 ----- #" + "\n"
              + "현재 위치 : " + currentContent + "\n" 
              + "기본키 : " + primaryKey + "\n" 
              + "현재 값 : " + value;
  console.log(logText);
  $.ajax({
    type: 'POST',
    url : 'update_records',
    data: {
      "current_path"  : currentContent,
      "primary_key"   : primaryKey,
      "value"         : value
    }, success: function (data) {
      let logText = "# ----- 플래그 상태 스위칭 성공 ----- #" + "\n"
                  + "현재 위치 : " + currentContent + "\n" 
                  + "기본키 : " + primaryKey + "\n" 
                  + "현재 값 : " + value;
      console.log(logText);
    }, error: function (request, status, error) {
      let alertText = "오류! 플래그 상태를 전환하지 못하였습니다." + "\n"
                    + "자세한 오류 내용은 로그를 참조해주십시오."
      let logText = "# ----- 플래그 상태 스위칭 실패 ----- #" + "\n"
                  + "code : " + request.status + "\n" 
                  + "status : " + status + "\n"
                  + "error : " + error + "\n"
                  + "message : " + request.responseText + "\n";
      alert(alertText);
      console.log(logText);
    }, complete : function () {
      getListContents( currentPage );
    }
  });
}

// ### 전체 체크박스 컨트롤 함수 ###
function controlOfAllCheckboxes() {
  if( $( '#all_checkboxes' ).is( ":checked" ) ) 
    $( '.checkbox' ).prop( "checked", true ); 
  else 
    $( '.checkbox' ).prop( "checked", false );
}

// ### 체크박스로 상품 여러개를 삭제하는 함수 ###
function deleteTheseRecords( currentPage ) {
  let listOfPrimaryKeys = [];
  let url = currentPath() + "/delete_records";
  //  체크된 레코드들의 기본키 값 
  $( ".checkbox:checked" ).each( function() {
    listOfPrimaryKeys.push( $(this).val() );
  });
  // 체크된 체크박스가 없을 때는 동작하지 않는다.
  if( listOfPrimaryKeys.length == 0 ) {
    return 0;
  }
  if ( confirm( 
      "레코드 " + listOfPrimaryKeys.length + "건을 정말로 삭제하시겠습니까?"
    ) ) 
  {
    $.ajax({
      type: 'POST',
      url : url,
      data: { 
        "key"  : listOfPrimaryKeys 
      }, success: function () {
        let logText = "# ----- 레코드 삭제 성공 ----- #" + "\n"
                    + "레코드 " + listOfPrimaryKeys.length + "건에 대한 삭제 성공" + "\n";
        listOfPrimaryKeys.forEach( function(primaryKey) {
          logText += "기본 키 : " + primaryKey + "\n";
        });
        console.log(logText);
      }, error: function (request, status, error) {
        let alertText = "오류! 레코드 삭제에 실패하였습니다." + "\n"
                      + "자세한 오류 내용은 로그를 참조해주십시오."
        let logText = "# ----- 레코드 삭제 실패 ----- #" + "\n"
                    + "code : " + request.status + "\n" 
                    + "status : " + status + "\n"
                    + "error : " + error + "\n"
                    + "message : " + request.responseText + "\n";
        alert(alertText);
        console.log(logText);
      }, complete : function (data) {
        getListContents( currentPage );
      }
    });
  } else {
    let logText = "# ----- 레코드 삭제 취소 ----- #" + "\n"
                + "레코드 삭제를 취소하였습니다." + "\n";
    let alertText = "취소 하였습니다.";
    console.log(logText);
    alert(alertText);
  }
}

// ### 버튼으로 date타입 input태그를 설정하는 함수 ###
function setCalendar( dateToSubtract ) {
  let fromThisDate = $('#to-this-date').val();
  let dateArray = divideInputDate( fromThisDate );
  let date = dateConverter( dateArray[0], dateArray[1], dateArray[2] - dateToSubtract );
  $('#from-this-date').val(date);

  let logText = "# ----- 조회 기간 설정 ----- #" + "\n"
              + "날짜 간격 : " + dateToSubtract + "\n"
              + "시작 날짜 : " + date + "\n"
              + "종료 날짜 : " + fromThisDate + "\n";
  console.log(logText);
}

// ### date형 input태그의 값(0000-00-00)을 연, 월, 일로 나눈 뒤 정수 배열로 반환하는 함수 ###
function divideInputDate( date ) {
  let dateArray = new Array(3);
  dateArray[0] = Number( date.slice(0, 4) );
  dateArray[1] = Number( date.slice(5, 7) );
  dateArray[2] = Number( date.slice(8, 10) );
  return dateArray;
}

// ### JS의 Date형 데이터를 HTML input태그의 date 데이트 형에 맞게 포맷 ###
function dateConverter( year, month, day ) {
  let date = new Date( year, month - 1, day + 1 );
  return date.toISOString().slice(0, 10);
}

// ### 조회기간 시작날짜와 종료날짜 검토 ###
function dateValiation( currentPage ) {
  let fromThisDateArray = divideInputDate( $('#from-this-date').val() );
  let toThisDateArray = divideInputDate($('#to-this-date').val() );
  let fromThisDate = new Date( fromThisDateArray[0], fromThisDateArray[1], fromThisDateArray[2] );
  let toThisDate = new Date( toThisDateArray[0], toThisDateArray[1], toThisDateArray[2] );
  let maximumDate = new Date( toThisDateArray[0], toThisDateArray[1], toThisDateArray[2] - 183 );
  let result;
  if( fromThisDate > toThisDate ) {
    $('#from-this-date').val( $('#to-this-date').val( ) );
    result = "시작 날짜 값이 더 큼. 시작 날짜 값에 종료 날짜 값을 넣음.";
  } else if( maximumDate > fromThisDate ) {
    $('#from-this-date').val( dateConverter( toThisDateArray[0], toThisDateArray[1], toThisDateArray[2] - 183 ) );
    result = "시작 날짜가 최대 간격인 183일 보다 큼. 최대치로 수정함.";
  } else {
    result = "이상 없음.";
  }
  let logText = "# ----- 조회 기간 검토 ----- #" + "\n"
              + "시작 : " + fromThisDate + "\n"
              + "종료 : " + toThisDate + "\n" 
              + "결과 : " + result  + "\n";
  console.log( logText );
  getListContents( currentPage );
}

function highlightButton() {
  if( $( '#view_mode' ).val() == 'normal' ) {
    $( '#normal' ).addClass('selected_button');
  } else {
    $( '#extended' ).addClass('selected_button');
  }
}

// ### 글 내용 업로드 ###
function writingAction( url, key ) {
  let title = $('#post_title').val();
  let content = window.editors[0].getData();
  let importantFlag = $('#post_category').val();
  let logText = "글 제목 : " + title + "\n"
              + "글 내용 : " + content + "\n"
              + "글 내용 : " + importantFlag + "\n";
  if( title == '' ) {
    alert("제목을 입력해주십시오.");
  } else if( content == '' ) {
    alert("글 내용을 입력해주십시오.");
  } else if( content.length > 1000 ) {
    alert("글 내용을 1000자 이내로 작성하십시오.");
  } else {
    $.ajax({
      type: 'POST',
      url : url,
      data: {
        "post_title"     : title,
        "post_content"   : content,
        "important_flag" : importantFlag,
        "post_no"        : key
      }, success: function () {
        logText = "# ----- 글 업로드 성공 ----- #" + "\n" + logText;
        console.log(logText);
        location.href = '../notice_list';
      }, error: function (request, status, error) {
        logText = "# ----- 글 업로드 실패 ----- #" + "\n" + logText
                + "code : " + request.status + "\n" 
                + "status : " + status + "\n"
                + "error : " + error + "\n"
                + "message : " + request.responseText + "\n";
        console.log(logText);
      }
    });
  }
}

// ### 아코디언 열기 ###
function openAccordion( url, index ) {
  let container = $('#container_' + index);
  let accordion = $('#accordion_' + index);
  let logText = "인덱스 : " + index + "\n" + "주소 : " + url + "\n";
  if( accordion.hasClass('close') ) {
    $.ajax({
      type: 'POST', 
      url : url, 
      data: { "index" : index
      }, success: function( data ) {
        $('#content_' + index).html( data );
        logText = "# ----- 아코디언 내용 불러오기 성공 ----- #" + "\n" + logText
      }, error: function( request, status, error ) {
        logText = "# ----- 아코디언 내용 불러오기 실패 ----- #" + "\n" + logText
          + "code : " + request.status + "\n" + "status : " + status + "\n"
          + "error : " + error + "\n" + "message : " + request.responseText + "\n"
      }, complete: function() {
        console.log(logText);
      }
    });
    if ( container.hasClass('last') ) {
      container.css({'border-bottom-left-radius': '0px', 'border-bottom-right-radius': '0px'});
    }
    accordion.slideDown(300);
    accordion.removeClass("close");
  } else {
    accordion.slideUp(300, 'swing', function() {
      if ( container.hasClass('last') ) {
        container.css({'border-bottom-left-radius': '3px', 'border-bottom-right-radius': '3px'});
      }
      accordion.addClass("close");
    });
  }
}

// ### 답글 업로드 ###
function uploadReply( url, index, indexOfLoopStatus ) {
  let content = window.editors[ indexOfLoopStatus ].getData();
  let logText, numberOfReplies;
  replyCheck = Number($('#numberOfRecords_' + index).html());
  if( replyCheck > 0 ) {
    alert("이미 답변되었습니다.");
  }
  else if( !content ) {
    alert("글 내용을 입력해주십시오.");
  } else if( content.length > 1000 ) {
    alert("글 내용을 1000자 이내로 작성하십시오.");
  }  else {
    $.ajax({
      type: 'POST',
      url : url,
      data: {
        'index'     : index,
        'content'   : content,
      }, success: function ( data ) {
        $('#content_' + index ).html( data );
        numberOfReplies = $('#content_' + index).find('.numberOfReplies').last().val();
        $('#numberOfRecords_' + index).html( numberOfReplies );
        window.editors[ indexOfLoopStatus ].setData('');
        logText = "# ----- 업로드 성공 ----- #" + "\n";
      }, error: function (request, status, error) {
        logText = "# ----- 업로드 실패 ----- #" + "\n"; 
                + "code : " + request.status + "\n" 
                + "status : " + status + "\n" 
                + "error : " + error + "\n" 
                + "message : " + request.responseText + "\n";
      }, complete: function() {
        logText = logText 
                + "인덱스 : " + index + "\n"
                + "내용 : " + content + "\n"
                + "내용 길이 : " + content.length + "\n"
                + "답글 수 : " + numberOfReplies + "\n"
                + "테스트 : " + $('#numberOfRecords_' + index).html();
        console.log(logText);
      }
    });
  }
}

// ### 답글 삭제 ###
function deleteReply( indexOfInquiry, indexOfreply ) {
  let url = $('#container_' + indexOfInquiry).find('.url').val() + "_delete";
  let numberOfReplies, logText;
  if( confirm("답글을 삭제하시겠습니까?") ) {
    $.ajax({
      type: 'POST',
      url : url,
      data: {
        'indexOfInquiry'  : indexOfInquiry,
        'indexOfreply'    : indexOfreply,
      }, success: function ( data ) {
        $('#content_' + indexOfInquiry ).html( data );
        numberOfReplies = $('#content_' + indexOfInquiry).find('.numberOfReplies').last().val();
        $('#numberOfRecords_' + indexOfInquiry).html( numberOfReplies );
        logText = "# ----- 삭제 성공 ----- #" + "\n";
      }, error: function (request, status, error) {
        logText = "# ----- 삭제 실패 ----- #" + "\n" 
                + "code : " + request.status + "\n" 
                + "status : " + status + "\n" 
                + "error : " + error + "\n" 
                + "message : " + request.responseText + "\n";
      }, complete: function() {
        logText = logText 
                + "답글 인덱스 : " + indexOfreply + "\n"
                + "답글 수 : " + numberOfReplies + "\n"
                + "테스트 : " + $('#numberOfRecords_' + indexOfInquiry).html();
        console.log(logText);
      }
    });
  }  
}