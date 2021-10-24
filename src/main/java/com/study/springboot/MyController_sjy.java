package com.study.springboot;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.springboot.dao.ICommonDao;
import com.study.springboot.dao.IInquiryDao;
import com.study.springboot.dao.IMemberInfoDao;
import com.study.springboot.dao.IMemberReviewDao;
import com.study.springboot.dao.INoticeDao;
import com.study.springboot.dao.IOrderInfoDao;
import com.study.springboot.dao.IOrderProductInfoDao;
import com.study.springboot.dao.IProductInfoDao;
import com.study.springboot.dao.IProduct_image_infoDao;
import com.study.springboot.dao.IProduct_infoDao;
import com.study.springboot.dao.IReplyDao;
import com.study.springboot.dao.ImageInfoDao;
import com.study.springboot.dto.InquiryDto;
import com.study.springboot.dto.MemberInfoDto;
import com.study.springboot.dto.MemberReviewDto;
import com.study.springboot.dto.NoticeDto;
import com.study.springboot.dto.OrderInfoDto;
import com.study.springboot.dto.OrderProductInfoDto;
import com.study.springboot.dto.ProductInfoDto;
import com.study.springboot.dto.ReplyDto;

/**********************************************
*				 내일의 집  				  *
***********************************************
*	MyController_sjy.java					  *
*											  *
*	작업자 : 심준엽		 					  *
*   										  *
*   JSP에서 요청한 URI 분기 처리			  *
**********************************************/
@Controller
public class MyController_sjy {
	
	// ##################################################
	// # 												#
	// # 												#
	// # 					관리자 페이지 				#
	// # 												#
	// # 												#
	// ##################################################
	
	@Autowired
	private IMemberInfoDao memberInfoDao;

	@Autowired
	private IProductInfoDao productInfoDao;
	
	@Autowired
	private IOrderInfoDao orderInfoDao;
	
	@Autowired
	private IOrderProductInfoDao orderProductInfoDao;
	
	@Autowired
	private IMemberReviewDao memberReviewDao;
	
	@Autowired
	private INoticeDao noticeDao;
	
	@Autowired
	private IInquiryDao inquiryDao;
	
	@Autowired
	private IReplyDao replyDao;
	
	@Autowired
	private ImageInfoDao imageInfoDao;
	
	@Autowired
	private ICommonDao commonDao;
	
	@Autowired
    private HttpSession session;
	
	@Autowired
	private PaginationService pagination;
	
	@Autowired
	private CryptoService cryptoAPI;
	
	@Autowired
	private RandomStringGenerator randomPassword;
	
	@Autowired
	private EmailSenderService emailSender;
	
	@Autowired
	private FileUploadService fileUploadAPI;
		
	@Autowired
	private IProduct_infoDao product_infoDao;
	
	@Autowired
	private IProduct_image_infoDao product_image_infoDao;
	
	// ##################################################
	// # 												#
	// # 					레코드 삭제 				#
	// # 												#
	// ##################################################	
	@Transactional
	@ResponseBody
	@RequestMapping( "admin/*/delete_records" )
	public void delete_records( Model model, HttpServletRequest request,
								@RequestParam( "key[]" ) List<String> listOfPrimaryKeys
							  ) throws Exception {
		
		String tableName, primaryKeyColumn;
		int sqlResult = 0;
		
		System.out.println( "위치 : " + request.getRequestURI() );
			
//		##### 현재 위치 #####
		if( request.getRequestURI().contains("product_list") ) {
			tableName = "product_info";
			primaryKeyColumn = "product_code";
		} else if ( request.getRequestURI().contains("review_list") ) {
			tableName = "member_review";
			primaryKeyColumn = "post_no";
		} else if ( request.getRequestURI().contains("notice_list") ) { 
			tableName = "notice";
			primaryKeyColumn = "post_no";
		} else {
			throw new RuntimeException("레코드 삭제 실패. 현재 위치를 알 수 없습니다.");
		}
		
//		##### 레코드 삭제 #####	    
		for( String primaryKey : listOfPrimaryKeys ) {
			sqlResult += commonDao.deleteRecords( tableName, primaryKeyColumn, primaryKey );
		}
		if( sqlResult == 0 ) throw new RuntimeException("레코드 업데이트 실패. 결과가 없습니다.");
		
	}
	
	// ##################################################
	// # 												#
	// # 					레코드 수정 				#
	// # 												#
	// ##################################################
	@Transactional
	@ResponseBody
	@RequestMapping( "admin/update_records" )
	public void update_records( Model model,
								@RequestParam( "current_path" ) String currentPath,
								@RequestParam( "primary_key" ) String primaryKey,
								@RequestParam( "value" ) String value
							  ) throws Exception {
		
		String nameOfTable, column, primaryKeyColumn;
		int sqlResult = 0;
		
//		##### 현재 위치 #####
		if ( currentPath.equals("notice_list") ) {
			nameOfTable = "notice";
			primaryKeyColumn = "post_no";
			column = "important_flag";
			
			if( value.equals("Y") ) value = "N";
			else value = "Y";
		} else if ( currentPath.equals("order_list") ) {	
			nameOfTable = "order_info";
			primaryKeyColumn = "order_id";
			column = "order_status";
			
			if( value.equals("입금대기") ) value = "02";
			else if( value.equals("입금완료") ) value = "03";
			else if( value.equals("배송완료") ) value = "04";
			else if( value.equals("취소") ) value = "05";
			else if( value.equals("반품") ) value = "06";
			else value = "01";
			
		} else {
			throw new RuntimeException("레코드 업데이트 실패. 현재 위치를 알 수 없습니다.");
		}
		
//		##### 레코드 업데이트 #####	    
		sqlResult = commonDao.updateRecord( nameOfTable, column, value, primaryKeyColumn, primaryKey );
		if( sqlResult == 0 ) throw new RuntimeException("레코드 업데이트 실패. 업데이트된 결과가 없습니다.");

	}

	// ##################################################
	// # 												#
	// # 					회원 관리 					#
	// # 												#
	// ##################################################
	@Transactional( readOnly = true )
	@RequestMapping( "admin/member_list" )
	public String member_list( Model model,
							   @RequestParam( name = "current_page", required = false, defaultValue = "1" ) int currentPage,
							   @RequestParam( name = "number_of_records_per_page", required = false, defaultValue = "10" ) int numberOfRecordsPerPage,
							   @RequestParam( name = "search_option", required = false, defaultValue = "all" ) String currentOption,
							   @RequestParam( name = "search_keyword", required = false, defaultValue = "" ) String currentKeyword,
							   @RequestParam( name = "current_sort", required = false, defaultValue = "아이디 오름차 순" ) String currentSort,
							   @RequestParam( name = "view_mode", required = false, defaultValue = "normal" ) String viewMode
							 ) throws Exception {

//		##### 검색키워드 대소문자 구분없애기 위해 소문자로 통일 #####
		currentKeyword = currentKeyword.toLowerCase();
		
//		##### 검색옵션 SQL문 필터링 #####
		String searchOption;
		if( currentOption.equals("전체") ) searchOption = "all";
		else if( currentOption.equals("아이디") ) searchOption = "user_id";
		else if( currentOption.equals("이름") ) searchOption = "user_name";
		else if( currentOption.equals("이메일") ) searchOption = "email";
		else if( currentOption.equals("전화번호") ) searchOption = "phone_number";
		else searchOption = "address";
		
//		##### 정렬 SQL문 필터링 #####
		String sortRecords;
		if( currentSort.equals("아이디 오름차 순") ) 
			sortRecords = "user_id DESC";
		else if( currentSort.equals("아이디 내림차 순") ) 
			sortRecords = "user_id ASC";
		else if( currentSort.equals("이름 오름차 순") ) 
			sortRecords = "user_name DESC";
		else if( currentSort.equals("이름 내림차 순") ) 
			sortRecords = "user_name ASC";
		else if( currentSort.equals("이메일 오름차 순") ) 
			sortRecords = "email DESC";
		else if( currentSort.equals("이메일 내림차 순") ) 
			sortRecords = "email ASC";
		else if( currentSort.equals("연락처 오름차 순") ) 
			sortRecords = "phone_number DESC";
		else if( currentSort.equals("연락처 내림차 순") )
			sortRecords = "phone_number ASC";
		else if( currentSort.equals("주소 오름차 순") )
			sortRecords = "address DESC";
		else 
			sortRecords = "address ASC";
		
//		##### 페이지 매김을 위한 레코드 수 구하기 #####
		int numberOfRecords = memberInfoDao.countRecords( searchOption, currentKeyword );
		
//		##### pagination Bean 설정 #####	
		pagination.setPagination( numberOfRecordsPerPage, currentPage, numberOfRecords, 5 );

//		##### 페이지 매김 정보 가져와서 DB에 저장 #####
		int firstRecordIndex = pagination.getFirstRecordIndex();
		int lastRecordIndex = pagination.getLastRecordIndex();
		List<MemberInfoDto> relationInstance = 
				memberInfoDao.relationInstance( firstRecordIndex, lastRecordIndex, searchOption, currentKeyword, sortRecords );
		
		for( MemberInfoDto member : relationInstance ) {
			String userId = member.getUserId();
			
			Integer totalOrderPrice = orderInfoDao.totalOrderPrice( userId );
			member.setTotalOrderPrice( totalOrderPrice );
	
			int numberOfProductsOrderd = orderProductInfoDao.countOfProductsOrdered( userId );
			member.setNumberOfProductsOrderd( numberOfProductsOrderd );
		
			int numberOfMemberReviews = memberReviewDao.countOfMemberReviews( userId );
			member.setNumberOfMemberReviews( numberOfMemberReviews );
			
			Float averageOfReviewScores = memberReviewDao.averageOfReviewScores( userId );
			member.setAverageOfReviewScores(averageOfReviewScores);
			
			int shippingStatus = orderInfoDao.shippingStatus( userId, 2 );
			member.setShippingStatus( 2, shippingStatus );
		}	

		model.addAttribute( "numberOfRecords", numberOfRecords );
		model.addAttribute( "relationInstance", relationInstance );
		model.addAttribute( "pagination", pagination );
		model.addAttribute( "search_option", currentOption );
		model.addAttribute( "search_keyword", currentKeyword );
		model.addAttribute( "current_sort", currentSort );
		model.addAttribute( "view_mode", viewMode );
		model.addAttribute( "contents", "./member/member-list.jsp");
		
		return "management/admin_index";
	}
	
	// ##################################################
	// # 												#
	// # 					회원 상세정보 				#
	// # 												#
	// ##################################################
	@Transactional( readOnly = true )
	@RequestMapping( "/admin/member_list/detail" )
	public String member_detail( Model model,
								 @RequestParam( name = "userId" ) String userId
							   ) throws Exception {
		
//		##### 회원 상세 정보 #####
		MemberInfoDto memberInfoDetail = memberInfoDao.memberInfoDetail( userId );
		
//		##### 회원의 총 구매 금액 #####
		Integer totalOrderPrice = orderInfoDao.totalOrderPrice( userId );
		memberInfoDetail.setTotalOrderPrice( totalOrderPrice );

//		##### 회원의 주문 상품 개수 #####
		int numberOfProductsOrderd = orderProductInfoDao.countOfProductsOrdered( userId );
		memberInfoDetail.setNumberOfProductsOrderd( numberOfProductsOrderd );
	
//		##### 회원의 리뷰 개수 #####	
		int numberOfMemberReviews = memberReviewDao.countOfMemberReviews( userId );
		memberInfoDetail.setNumberOfMemberReviews( numberOfMemberReviews );

//		##### 각 주문 상태 건수 #####
		for( int index=0; index<6; index++ ) {
			int shippingStatus = orderInfoDao.shippingStatus( userId, index+1 );
			memberInfoDetail.setShippingStatus( index, shippingStatus );
		}
		
//  	##### 회원이 작성한 각 문의 글의 수 #####
		int numberOfProductInquiries = inquiryDao.countOfrecords( "product_inquiry", userId );
		int numberOfOne2oneInquiries = inquiryDao.countOfrecords( "one2one_inquiry", userId );
		memberInfoDetail.setNumberOfProductInquiries( numberOfProductInquiries );
		memberInfoDetail.setNumberOfOne2oneInquiries( numberOfOne2oneInquiries );
		
//  	##### 답변받지 못한 각 문의 글의 수 #####
		int numberOfUnrepliedProductInquiries = inquiryDao.countOfUnrepliedInquiries( "product_inquiry", userId );
		int numberOfUnrepliedOne2oneInquiries = inquiryDao.countOfUnrepliedInquiries( "one2one_inquiry", userId );
		memberInfoDetail.setNumberOfUnrepliedProductInquiries( numberOfUnrepliedProductInquiries );
		memberInfoDetail.setNumberOfUnrepliedOne2oneInquiries( numberOfUnrepliedOne2oneInquiries );
		
//  	##### 회원의 평균 리뷰 점수 #####	
		Float averageOfReviewScores = memberReviewDao.averageOfReviewScores( userId );
		memberInfoDetail.setAverageOfReviewScores(averageOfReviewScores);
		
		model.addAttribute( "detail", memberInfoDetail );
		
		return "management/member/member_info_detail";
	}
	
	// ##################################################
	// # 												#
	// # 				이메일 송신			 			#
	// # 												#
	// ##################################################
	@Transactional
	@ResponseBody
	@RequestMapping("/admin/createTemporaryPassword")
	public void createTemporaryPassword( Model model,
										 @RequestParam(name = "userId") String userId,
										 @RequestParam(name = "userName") String userName,
										 @RequestParam(name = "userEmail") String userEmail
									   ) throws Exception {

//		##### 정상작동 체크용 변수들 #####
		int successCheck = 0;			// 1: 정상,		그외: 오류
		Boolean emailSendCheck = false;	// true: 송신완료,	false: 송신실패
		
//		##### 임시 비밀번호 생성 및 암호화 #####
	    String temporaryPassword = randomPassword.getRamdomString(8); 	// 비밀번호 랜덤생성
	    String cryptoPassword = cryptoAPI.crypto(temporaryPassword);	// 비밀번호 암호화
	    
//		##### 임시 비밀번호를 사용자 DB에 저장 #####
	    successCheck = memberInfoDao.updatePassword(userId, cryptoPassword);
	        
//		##### 임시 비밀번호를 사용자 이메일로 보낼 준비 #####
	    String adminEmail = "tomorrowhomemanager@gmail.com"; // 보내는 관리자 이메일
	    String subject = "[내일의 집] 임시 비밀번호가 발급 안내"; // 이메일 제목
	    String contents = userId + "(" + userName + ")님의 임시 비밀번호 입니다." + "\n" // 이메일 내용
			    			+ "반드시 로그인 후 비밀번호를 변경해주십시오." + "\n"
			    			+ "임시 비밀번호 : " + temporaryPassword; 
	    
	    emailSender.setSendMail(adminEmail, userEmail, subject, contents);
	
//		##### 메일 송신 #####
	    if(successCheck == 1) {
	    	emailSendCheck = emailSender.sendMail();
	    }
//	    ##### DB 이상 체크 #####
	    else {
	    	throw new RuntimeException("DB 적용 실패");
	    }
//	    ##### 메일 송신 체크 #####
	    if(emailSendCheck == false) {
	    	throw new RuntimeException("이메일 전송 실패");
	    }
	}
	
	// ##################################################
	// # 												#
	// # 					상품 관리 					#
	// # 												#
	// ##################################################	
	@Transactional( readOnly = true )
	@RequestMapping( "admin/product_list" )
	public String product_list( Model model,
								@RequestParam( name = "current_page", required = false, defaultValue = "1" ) int currentPage,
								@RequestParam( name = "number_of_records_per_page", required = false, defaultValue = "10" ) int numberOfRecordsPerPage,
								@RequestParam( name = "search_option", required = false, defaultValue = "전체" ) String currentOption,
								@RequestParam( name = "search_keyword", required = false, defaultValue = "" ) String currentKeyword,
								@RequestParam( name = "current_sort", required = false, defaultValue = "최신 등록일 순" ) String currentSort,
								@RequestParam( name = "parent_category", required = false, defaultValue = "전체" ) String parentCategory,
								@RequestParam( name = "child_category", required = false, defaultValue = "전체" ) String childCategory,
								@RequestParam( name = "view_mode", required = false, defaultValue = "normal" ) String viewMode
							  ) throws Exception {
		
//		##### 검색키워드 대소문자 구분없애기 위해 소문자로 통일 #####
		currentKeyword = currentKeyword.toLowerCase();
		
//		##### 검색옵션 SQL문 필터링 #####
		String searchOption;
		if( currentOption.equals("전체") ) 
			searchOption = "all";
		else if( currentOption.equals("상품명") ) 
			searchOption = "product_name";
		else if( currentOption.equals("상품코드") ) 
			searchOption = "product_code";
		else 
			searchOption = "product_price";
		
//		##### 정렬 SQL문 필터링 #####
		String sortRecords;
		if( currentSort.equals("최신 등록일 순") ) 
			sortRecords = "added_date DESC";
		else if( currentSort.equals("오래된 등록일 순") ) 
			sortRecords = "added_date ASC";
		else if( currentSort.equals("판매가 높은 순") ) 
			sortRecords = "product_price DESC";
		else if( currentSort.equals("판매가 낮은 순") ) 
			sortRecords = "product_price ASC";
		else if( currentSort.equals("재고량 많은 순") ) 
			sortRecords = "number_of_stock DESC";
		else 
			sortRecords = "number_of_stock ASC";

//		##### 페이지 매김을 위한 레코드 수 구하기 #####
		int numberOfRecords = productInfoDao.countRecords( searchOption, currentKeyword, parentCategory, childCategory );
		
//		##### pagination Bean 설정 #####	
		pagination.setPagination( numberOfRecordsPerPage, currentPage, numberOfRecords, 5 );

//		##### 페이지 매김 정보 가져와서 DB에 저장 #####
		int firstRecordIndex = pagination.getFirstRecordIndex();
		int lastRecordIndex = pagination.getLastRecordIndex();
		List<ProductInfoDto> relationInstance = 
				productInfoDao.relationInstance( 
					firstRecordIndex, lastRecordIndex, searchOption, currentKeyword, 
					sortRecords, parentCategory, childCategory 
				);
		
//		##### 이미지 테이블에서 이미지 파일명 가져오기 #####
		for( ProductInfoDto product : relationInstance ) {
//			##### 회원의 총 구매 금액 #####
			String productCode = product.getProductCode();
			ArrayList<String> imageFileList = imageInfoDao.getImageFileName( "product_image_info", "product_code", productCode );
			product.setImageFileList( imageFileList );
		}

		model.addAttribute( "numberOfRecords", numberOfRecords );
		model.addAttribute( "relationInstance", relationInstance );
		model.addAttribute( "pagination", pagination );
		model.addAttribute( "search_option", currentOption );
		model.addAttribute( "search_keyword", currentKeyword );
		model.addAttribute( "current_sort", currentSort );
		model.addAttribute( "parent_category", parentCategory );
		model.addAttribute( "child_category", childCategory );
		model.addAttribute( "view_mode", viewMode );
		model.addAttribute( "contents", "./product/product-list.jsp");
		
		return "management/admin_index";
	}
	
	// ##################################################
	// # 												#
	// # 					상품 등록 폼				#
	// # 												#
	// ##################################################
	@Transactional
	@RequestMapping( "admin/add_product" )
	public String add_product() {
		
		return "management/product/add_product";
		
	}
	
	// ##################################################
	// # 												#
	// # 					상품 상세정보 				#
	// # 												#
	// ##################################################
	@Transactional( readOnly = true )
	@RequestMapping( "/admin/product_list/detail" )
	public String product_detail( Model model,
								  @RequestParam( name = "productCode" ) String productCode
							    ) throws Exception {
			
//		##### 상품 상세 정보 #####
		ProductInfoDto productInfoDetail = productInfoDao.productInfoDetail( productCode );

//		##### 이미지 테이블에서 이미지 파일명 가져오기 #####
		ArrayList<String> imageFileList = imageInfoDao.getImageFileName( "product_image_info", "product_code", productCode );
		productInfoDetail.setImageFileList( imageFileList );

		System.out.println( "########### 상품 상세정보 ###########" );
		System.out.println( "상품코드 : " + productInfoDetail.getProductCode() );
		
		model.addAttribute( "detail", productInfoDetail );
		
		return "management/product/product_info_detail";
	}
	
	// ##################################################
	// # 												#
	// # 				상품 상세정보 수정 폼 			#
	// # 												#
	// ##################################################
	@Transactional
	@RequestMapping( "/admin/product_list/detail_modity" )
	public String product_detail_modity( Model model,
										 @RequestParam( name = "product_code" ) String productCode
									   ) throws Exception {
	
//		##### 상품 상세 정보 #####
		ProductInfoDto productInfoDetail = productInfoDao.productInfoDetail( productCode );
		
//		##### 이미지 테이블에서 이미지 파일명 가져오기 #####
		ArrayList<String> imageFileList = imageInfoDao.getImageFileName( "product_image_info", "product_code", productCode );
		productInfoDetail.setImageFileList( imageFileList );

		System.out.println( "########### 상품 상세정보 수정 ###########" );
		System.out.println( "상품코드 : " + productInfoDetail.getProductCode() );
		
		model.addAttribute( "detail", productInfoDetail );
		
		return "management/product/product_info_detail_modify";
	}
	
	// ##################################################
	// # 												#
	// # 				상품 상세정보 수정 폼 			#
	// # 												#
	// ##################################################
	@Transactional
	@RequestMapping( "/admin/product_list/detail_modity_action" )
	public String product_detail_modity_action( Model model,
												@RequestParam( name = "product_code" ) String productCode,
												@RequestParam( name = "parent_category" ) String parentCategory,
												@RequestParam( name = "child_category" ) String childCategory,
												@RequestParam( name = "manufacturer" ) String manufacturer,
												@RequestParam( name = "product_name" ) String productName,
												@RequestParam( name = "product_price" ) int productPrice,
												@RequestParam( name = "shipping_cost" ) int shippingCost,
												@RequestParam( name = "number_of_stock" ) int numberOfStock,
												@RequestParam( name = "product_description" ) String productDescription,
												@RequestParam( name = "file1", required = false ) MultipartFile file1,
												@RequestParam( name = "file2", required = false ) MultipartFile file2
											  ) throws Exception {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_sjy : product_detail_modity_action]");
				
		int result = productInfoDao.productInfoUpdate( productCode, parentCategory, childCategory, manufacturer, productName, productPrice, shippingCost, numberOfStock, productDescription );
		
		if(file1.getSize() != 0 || file2.getSize() != 0) {
			ArrayList<String> convImageName = fileUploadAPI.upload("P", file1, file2);
			ArrayList<Integer> imageNumber = product_image_infoDao.get_image_no(productCode);
						
			product_image_infoDao.update_product_image(imageNumber.get(0), convImageName.get(0));
			product_image_infoDao.update_product_image(imageNumber.get(1), convImageName.get(1));
		}
		
		//##### 상품 상세 정보 #####
		ProductInfoDto productInfoDetail = productInfoDao.productInfoDetail( productCode );
		
		//##### 이미지 테이블에서 이미지 파일명 가져오기 #####
		ArrayList<String> imageFileList = imageInfoDao.getImageFileName( "product_image_info", "product_code", productCode );
		productInfoDetail.setImageFileList( imageFileList );
		 
		System.out.println( "########### 상품 상세정보 ###########" ); 
		System.out.println( "상품코드 : " + productInfoDetail.getProductCode() ); 
		System.out.println(	"업데이트 결과 : " + result );
		 
		model.addAttribute( "detail", productInfoDetail );
		 
		return "management/product/product_info_detail";
		
	}

	// ##################################################
	// # 												#
	// # 				상품 상세정보 추가 액션			#
	// # 												#
	// ##################################################
	@Transactional
	@ResponseBody
	@RequestMapping( "/admin/product_add_action" )
	public void product_add_action( Model model,
									@RequestParam( name = "parent_category" ) String parentCategory,
									@RequestParam( name = "child_category" ) String childCategory,
									@RequestParam( name = "manufacturer" ) String manufacturer,
									@RequestParam( name = "product_name" ) String productName,
									@RequestParam( name = "product_price" ) int productPrice,
									@RequestParam( name = "shipping_cost" ) int shippingCost,
									@RequestParam( name = "number_of_stock" ) int numberOfStock,
									@RequestParam( name = "product_description" ) String productDescription,
									@RequestParam( name = "file1", required = false) MultipartFile file1,
									@RequestParam( name = "file2", required = false) MultipartFile file2
								  ) throws Exception {
	 
		int result = productInfoDao.productInfoAdd( 
				parentCategory, childCategory, manufacturer, productName, productPrice, shippingCost, numberOfStock, productDescription );
			
		System.out.println( "########### 상품 추가 ###########" );
		System.out.println( "추가 결과 : " + result );
		
		/* 이미지 추가 */
		System.out.println("file1 : " + file1.getOriginalFilename());
		System.out.println("file2 : " + file2.getOriginalFilename());
		ArrayList<String> convImageName = fileUploadAPI.upload("P",file1, file2);
		
		String product_code = product_infoDao.get_last_productCode();
		System.out.println("product_code : " + product_code);
		
		int fileCheck1 = (int) file1.getSize();
		
        if(fileCheck1 != 0) {
            product_image_infoDao.add_product_image(product_code, convImageName.get(0));
        }
		
        int fileCheck2 = (int) file2.getSize();

        if(fileCheck2 != 0) {
        	product_image_infoDao.add_product_image(product_code, convImageName.get(1));
        }
		
	}
	
	// ##################################################
	// # 												#
	// # 					주문 관리 					#
	// # 												#
	// ##################################################
	@RequestMapping("/admin/order_list")
	public String order_list( Model model,
							  @RequestParam( name = "current_page", defaultValue = "1" ) int currentPage,
							  @RequestParam( name = "number_of_records_per_page", defaultValue = "10" ) int numberOfRecordsPerPage,
							  @RequestParam( name = "search_option", defaultValue = "전체" ) String currentOption,
							  @RequestParam( name = "search_keyword", defaultValue = "" ) String currentKeyword,
							  @RequestParam( name = "current_sort", defaultValue = "주문일 최신 순" ) String currentSort,
							  @RequestParam( name = "from_this_date", required = false ) String fromThisDate,
							  @RequestParam( name = "to_this_date", required = false ) String toThisDate
							) throws Exception {
		
//		##### 검색키워드 대소문자 구분없애기 위해 소문자로 통일 #####
		currentKeyword = currentKeyword.toLowerCase();
		
//		##### 검색옵션 SQL문 필터링 #####
		String searchOption;
		if( currentOption.equals("전체") ) 
			searchOption = "all";
		else if( currentOption.equals("주문번호") ) 
			searchOption = "order_id";
		else 
			searchOption = "user_id";
		
//		##### 정렬 SQL문 필터링 #####
		String sortRecords;
		if( currentSort.equals("주문일 최신 순") ) 
			sortRecords = "order_date DESC";
		else if( currentSort.equals("주문일 오래된 순") )
			sortRecords = "order_date ASC";
		else if( currentSort.equals("결제금액 높은 순") ) 
			sortRecords = "total_price DESC";
		else if( currentSort.equals("결제금액 낮은 순") ) 
			sortRecords = "total_price ASC";
		else if( currentSort.equals("주문 상태 오름차순") ) 
			sortRecords = "order_status DESC";
		else 
			sortRecords = "order_status ASC";
			
//		##### 기간 검색 기본값 #####
		if( fromThisDate == null || toThisDate == null ) {
			LocalDateTime now = LocalDateTime.now();
			toThisDate = now.format( DateTimeFormatter.ofPattern("yyyy-MM-dd") );
			fromThisDate = now.minusDays( 183 ).format( DateTimeFormatter.ofPattern("yyyy-MM-dd") );
		}
		
//		##### 페이지 매김을 위한 레코드 수 구하기 #####
		int numberOfRecords = orderInfoDao.countRecords( searchOption, currentKeyword, fromThisDate, toThisDate );
		
//		##### pagination Bean 설정 #####	
		pagination.setPagination( numberOfRecordsPerPage, currentPage, numberOfRecords, 5 );

//		##### 페이지 매김 정보 가져와서 DB에 저장 #####
		int firstRecordIndex = pagination.getFirstRecordIndex();
		int lastRecordIndex = pagination.getLastRecordIndex();
		List<OrderInfoDto> relationInstance = 
				orderInfoDao.relationInstance( firstRecordIndex, lastRecordIndex, searchOption, currentKeyword, sortRecords, fromThisDate, toThisDate );
		
		ArrayList<OrderProductInfoDto> orderProductList;
		for( OrderInfoDto orderInfo : relationInstance ) {
			orderProductList = orderProductInfoDao.listOfOrderedProducts( orderInfo.getOrderId() );
			orderInfo.setOrderProducts( orderProductList );
		}
		
		model.addAttribute( "numberOfRecords", numberOfRecords );
		model.addAttribute( "relationInstance", relationInstance );
		model.addAttribute( "pagination", pagination );
		model.addAttribute( "search_option", currentOption );
		model.addAttribute( "search_keyword", currentKeyword );
		model.addAttribute( "current_sort", currentSort );
		model.addAttribute( "fromThisDate", fromThisDate );
		model.addAttribute( "toThisDate", toThisDate );
		model.addAttribute( "contents", "./order/order-list.jsp");
		
		return "management/admin_index";
	}

	// ##################################################
	// # 												#
	// # 					회원리뷰 관리 				#
	// # 												#
	// ##################################################
	
	@RequestMapping("/admin/review_list")
	public String review_list( Model model,
							   @RequestParam( name = "current_page", defaultValue = "1" ) int currentPage,
							   @RequestParam( name = "number_of_records_per_page", defaultValue = "10" ) int numberOfRecordsPerPage,
							   @RequestParam( name = "search_option", defaultValue = "전체" ) String currentOption,
							   @RequestParam( name = "search_keyword", defaultValue = "" ) String currentKeyword,
							   @RequestParam( name = "current_sort", defaultValue = "작성일 최신 순" ) String currentSort,
							   @RequestParam( name = "from_this_date", required = false ) String fromThisDate,
							   @RequestParam( name = "to_this_date", required = false ) String toThisDate
							 ) throws Exception {
		
//		##### 검색키워드 대소문자 구분없애기 위해 소문자로 통일 #####
		currentKeyword = currentKeyword.toLowerCase();
		
//		##### 검색옵션 SQL문 필터링 #####
		String searchOption;
		if( currentOption.equals("전체") ) 
			searchOption = "all";
		else if( currentOption.equals("아이디") ) 
			searchOption = "user_id";
		else if( currentOption.equals("상품코드") ) 
			searchOption = "product_code";
		else 
			searchOption = "post_content";
		
//		##### 정렬 SQL문 필터링 #####
		String sortRecords;
		if( currentSort.equals("작성일 최신 순") ) 
			sortRecords = "post_date DESC";
		else if( currentSort.equals("작성일 오래된 순") )
			sortRecords = "post_date ASC";
		else if( currentSort.equals("별점 높은 순") ) 
			sortRecords = "review_score DESC";
		else if( currentSort.equals("별점 낮은 순") ) 
			sortRecords = "review_score ASC";
		else if( currentSort.equals("상품번호 오름차순") ) 
			sortRecords = "product_code DESC";
		else 
			sortRecords = "product_code ASC";

//		##### 기간 검색 기본값 #####
		if( fromThisDate == null || toThisDate == null ) {
			LocalDateTime now = LocalDateTime.now();
			toThisDate = now.format( DateTimeFormatter.ofPattern("yyyy-MM-dd") );
			fromThisDate = now.minusDays( 183 ).format( DateTimeFormatter.ofPattern("yyyy-MM-dd") );
		}
		
//		##### 페이지 매김을 위한 레코드 수 구하기 #####
		int numberOfRecords = memberReviewDao.countRecords( searchOption, currentKeyword, fromThisDate, toThisDate );
		
//		##### pagination Bean 설정 #####	
		pagination.setPagination( numberOfRecordsPerPage, currentPage, numberOfRecords, 5 );

//		##### 페이지 매김 정보 가져와서 DB에 저장 #####
		int firstRecordIndex = pagination.getFirstRecordIndex();
		int lastRecordIndex = pagination.getLastRecordIndex();
		List<MemberReviewDto> relationInstance = 
				memberReviewDao.relationInstance( firstRecordIndex, lastRecordIndex, searchOption, currentKeyword, sortRecords, fromThisDate, toThisDate );
				
		
		for( MemberReviewDto review : relationInstance ) {
//			##### 회원의 총 구매 금액 #####
			String productCode = review.getProduct_code();
			
//	  		##### 회원의 평균 리뷰 점수 #####	
			String ProductName = productInfoDao.getProductName( productCode );
			review.setProduct_name( ProductName );		
		}
		
		model.addAttribute( "numberOfRecords", numberOfRecords );
		model.addAttribute( "relationInstance", relationInstance );
		model.addAttribute( "pagination", pagination );
		model.addAttribute( "search_option", currentOption );
		model.addAttribute( "search_keyword", currentKeyword );
		model.addAttribute( "current_sort", currentSort );
		model.addAttribute( "fromThisDate", fromThisDate );
		model.addAttribute( "toThisDate", toThisDate );
		model.addAttribute( "contents", "./review/review_list.jsp");
		
		return "management/admin_index";
	}

	// ##################################################
	// # 												#
	// # 					공지사항 관리				#
	// # 												#
	// ##################################################
	@Transactional( readOnly = true )
	@RequestMapping("/admin/notice_list")
	public String notice_list( Model model,
							   @RequestParam( name = "current_page", required = false, defaultValue = "1" ) int currentPage,
							   @RequestParam( name = "number_of_records_per_page", required = false, defaultValue = "10" ) int numberOfRecordsPerPage,
							   @RequestParam( name = "search_option", required = false, defaultValue = "all" ) String currentOption,
							   @RequestParam( name = "search_keyword", required = false, defaultValue = "" ) String currentKeyword,
							   @RequestParam( name = "current_sort", defaultValue = "최신 등록일 순" ) String currentSort
							 ) throws Exception {
		
//		##### 검색키워드 대소문자 구분없애기 위해 소문자로 통일 #####
		currentKeyword = currentKeyword.toLowerCase();
		
//		##### 검색옵션 SQL문 필터링 #####
		String searchOption;
		if( currentOption.equals("전체") ) searchOption = "all";
		else if( currentOption.equals("제목") ) searchOption = "post_title";
		else if( currentOption.equals("내용") ) searchOption = "post_content";
		else if( currentOption.equals("글 번호") ) searchOption = "post_no";
		else searchOption = "user_id";
		
//		##### 정렬 SQL문 필터링 #####
		String sortRecords;
		if( currentSort.equals("최신 등록일 순") ) 
			sortRecords = "post_no DESC";
		else if( currentSort.equals("오래된 등록일 순") )
			sortRecords = "post_no ASC";
		else if( currentSort.equals("중요 공지") ) 
			sortRecords = "important_flag DESC";
		else
			sortRecords = "important_flag ASC";
		
//		##### 페이지 매김을 위한 레코드 수 구하기 #####
		int numberOfRecords = noticeDao.countRecords( searchOption, currentKeyword );
		
//		##### pagination Bean 설정 #####	
		pagination.setPagination( numberOfRecordsPerPage, currentPage, numberOfRecords, 5 );

//		##### 페이지 매김 정보 가져와서 DB에 저장 #####
		int firstRecordIndex = pagination.getFirstRecordIndex();
		int lastRecordIndex = pagination.getLastRecordIndex();
		List<NoticeDto> relationInstance = 
				noticeDao.relationInstance( firstRecordIndex, lastRecordIndex, searchOption, currentKeyword, sortRecords );

		model.addAttribute( "numberOfRecords", numberOfRecords );
		model.addAttribute( "relationInstance", relationInstance );
		model.addAttribute( "pagination", pagination );
		model.addAttribute( "search_option", currentOption );
		model.addAttribute( "search_keyword", currentKeyword );
		model.addAttribute( "current_sort", currentSort );
		model.addAttribute( "contents", "./notice/notice_list.jsp");
		
		return "management/admin_index";
	}
	
	// ##################################################
	// # 												#
	// # 					공지 글 쓰기				#
	// # 												#
	// ##################################################
	@RequestMapping("/admin/notice_list/writing")
	public String writingForm( Model model ) throws Exception {

		model.addAttribute( "contents", "./notice/notice_writing.jsp");
		return "management/admin_index";
	}
	
	// ##################################################
	// # 												#
	// # 					공지 글 쓰기 액션			#
	// # 												#
	// ##################################################
	@Transactional
	@RequestMapping("/admin/notice_list/writingAction")
	public String writingAction( Model model,
								 @RequestParam( "post_title" ) String postTitle,
								 @RequestParam( "post_content" ) String postContents,
								 @RequestParam( "important_flag" ) String importantFlag
							   ) throws Exception {
		
		String userId = (String)session.getAttribute("user_id");
		
		int result = noticeDao.insertRecord( userId, postTitle, postContents, importantFlag );
		
		System.out.println( "########### 공지글 쓰기 ###########" );
		System.out.println( "글쓰기 결과 : " + result);
		
		model.addAttribute( "contents", "./notice/notice_list.jsp");
		
		return "management/admin_index";
	}
	
	// ##################################################
	// # 												#
	// # 					공지 글 조회				#
	// # 												#
	// ##################################################
	@RequestMapping("/admin/notice_list/detail")
	public String notice_detail( Model model,
								 @RequestParam( name = "post_no" ) int postNo
							   ) throws Exception {

		NoticeDto data = noticeDao.detail( postNo );
		HashMap<String, String> nearbyPosts = noticeDao.nearbyRecords( postNo );
		
		System.out.println( "########### 공지글 조회 ###########" );
		System.out.println( "주변 게시물 : " + nearbyPosts);
		
		model.addAttribute( "nearbyPosts", nearbyPosts );
		model.addAttribute( "data", data );
		model.addAttribute( "contents", "./notice/notice_detail.jsp");
		
		return "management/admin_index";
	}
	
	// ##################################################
	// # 												#
	// # 					공지 글 수정				#
	// # 												#
	// ##################################################
	@RequestMapping("/admin/notice_list/edit")
	public String edit( Model model,
						@RequestParam( name = "post_no" ) int postNo
					  ) throws Exception {

		NoticeDto data = noticeDao.detail( postNo );
		
		System.out.println( "########### 공지글 수정 폼 ###########" );
		
		model.addAttribute( "data", data );
		model.addAttribute( "contents", "./notice/notice_edit.jsp");
		
		return "management/admin_index";
	}
	
	// ##################################################
	// # 												#
	// # 					공지 글 수정 액션			#
	// # 												#
	// ##################################################
	@RequestMapping("/admin/notice_list/editAction")
	public String edit_notice_action( Model model,
									  @RequestParam( name = "post_no" ) int postNo,
									  @RequestParam( name = "post_title" ) String postTitle,
									  @RequestParam( name = "post_content" ) String postContent,
									  @RequestParam( "important_flag" ) String importantFlag
									) throws Exception {

		int result = noticeDao.updateRecord( postNo, postTitle, postContent, importantFlag );

		System.out.println( "########### 공지글 수정 ###########" );
		System.out.println( "글쓰기 결과 : " + result);
		
		return "redirect:/admin/notice_list/detail?post_no=" + postNo;
	}
	
	// ##################################################
	// # 												#
	// # 					상품문의 관리 				#
	// # 												#
	// ##################################################
	@Transactional( readOnly = true )
	@RequestMapping("/admin/product_inquiry_list")
	public String product_inquiry_list( Model model,
										@RequestParam( name = "current_page", defaultValue = "1" ) int currentPage,
										@RequestParam( name = "number_of_records_per_page", defaultValue = "10" ) int numberOfRecordsPerPage,
										@RequestParam( name = "search_option", defaultValue = "전체" ) String currentOption,
										@RequestParam( name = "search_keyword", defaultValue = "" ) String currentKeyword,
										@RequestParam( name = "current_sort", defaultValue = "작성일 최신 순" ) String currentSort,
										@RequestParam( name = "from_this_date", required = false ) String fromThisDate,
										@RequestParam( name = "to_this_date", required = false ) String toThisDate
									  ) throws Exception {
		
		String tableName = "product_inquiry";
		String[] columnArray = { "inquiry_no", "product_code", "user_id", "inquiry_content", "private_flag", "inquiry_date", "number_of_replies" };
		String[] allSearchOptions = { "inquiry_no", "user_id", "product_code", "inquiry_content" };
		
//		##### 검색키워드 대소문자 구분없애기 위해 소문자로 통일 #####
		currentKeyword = currentKeyword.toLowerCase();
		
//		##### 검색옵션 SQL문 필터링 #####
		String searchOption;
		if( currentOption.equals("전체") ) 
			searchOption = "all";
		else if( currentOption.equals("문의 번호") ) 
			searchOption = "inquiry_no";
		else if( currentOption.equals("아이디") ) 
			searchOption = "user_id";
		else if( currentOption.equals("상품 코드") ) 
			searchOption = "product_code";
		else 
			searchOption = "inquiry_content";
		
//		##### 정렬 SQL문 필터링 #####
		String sortRecords;
		if( currentSort.equals("작성일 최신 순") ) 
			sortRecords = "inquiry_date DESC";
		else if( currentSort.equals("작성일 오래된 순") )
			sortRecords = "inquiry_date ASC";
		else if( currentSort.equals("상품 코드 오름차순") ) 
			sortRecords = "product_code DESC";
		else if( currentSort.equals("상품 코드 내림차순") ) 
			sortRecords = "product_code ASC";
		else if( currentSort.equals("많은 답글 순") ) 
			sortRecords = "number_of_replies DESC";
		else 
			sortRecords = "number_of_replies ASC";

//		##### 기간 검색 기본값 #####
		if( fromThisDate == null || toThisDate == null ) {
			LocalDateTime now = LocalDateTime.now();
			toThisDate = now.format( DateTimeFormatter.ofPattern("yyyy-MM-dd") );
			fromThisDate = now.minusDays( 183 ).format( DateTimeFormatter.ofPattern("yyyy-MM-dd") );
		}
		
//		##### 페이지 매김을 위한 레코드 수 구하기 #####
		int numberOfRecords = inquiryDao.countRecords( tableName, allSearchOptions, searchOption, currentKeyword, fromThisDate, toThisDate );
		
//		##### pagination Bean 설정 #####	
		pagination.setPagination( numberOfRecordsPerPage, currentPage, numberOfRecords, 5 );

//		##### 페이지 매김 정보 가져와서 DB에 저장 #####
		int firstRecordIndex = pagination.getFirstRecordIndex();
		int lastRecordIndex = pagination.getLastRecordIndex();
		List<InquiryDto> relationInstance = 
				inquiryDao.relationInstance( "product_inquiry", columnArray, allSearchOptions, firstRecordIndex, lastRecordIndex, searchOption, currentKeyword, sortRecords, fromThisDate, toThisDate );
		
//		##### 상품코드로 일치하는 상품명 가져오기 #####
		for( InquiryDto productInquiry : relationInstance ) {
			String productCode = productInquiry.getProduct_code();
			String ProductName = productInfoDao.getProductName( productCode );
			productInquiry.setProduct_name( ProductName );		
		}
		
		model.addAttribute( "numberOfRecords", numberOfRecords );
		model.addAttribute( "relationInstance", relationInstance );
		model.addAttribute( "pagination", pagination );
		model.addAttribute( "search_option", currentOption );
		model.addAttribute( "search_keyword", currentKeyword );
		model.addAttribute( "current_sort", currentSort );
		model.addAttribute( "fromThisDate", fromThisDate );
		model.addAttribute( "toThisDate", toThisDate );
		model.addAttribute( "contents", "./inquiry/product-inquiry-list.jsp");
		
		return "management/admin_index";
	}
	
	// ##################################################
	// # 												#
	// # 					1:1문의 관리 				#
	// # 												#
	// ##################################################
	@Transactional( readOnly = true )
	@RequestMapping("/admin/one2one_inquiry_list")
	public String one2one_inquiry_list( Model model,
										@RequestParam( name = "current_page", defaultValue = "1" ) int currentPage,
										@RequestParam( name = "number_of_records_per_page", defaultValue = "10" ) int numberOfRecordsPerPage,
										@RequestParam( name = "search_option", defaultValue = "전체" ) String currentOption,
										@RequestParam( name = "search_keyword", defaultValue = "" ) String currentKeyword,
										@RequestParam( name = "current_sort", defaultValue = "작성일 최신 순" ) String currentSort,
										@RequestParam( name = "from_this_date", required = false ) String fromThisDate,
										@RequestParam( name = "to_this_date", required = false ) String toThisDate
									  ) throws Exception {
		
		String tableName = "one2one_inquiry";
		String[] columnArray = { "inquiry_no", "user_id", "inquiry_title", "inquiry_content", "inquiry_date", "number_of_replies" };
		String[] allSearchOptions = { "inquiry_no", "user_id", "inquiry_title", "inquiry_content" };
		
//		===== 검색키워드 대소문자 구분없애기 위해 소문자로 통일 =====
		currentKeyword = currentKeyword.toLowerCase();
		
//		===== 검색옵션 SQL문 필터링 =====
		String searchOption;
		if( currentOption.equals("전체") ) 
			searchOption = "all";
		else if( currentOption.equals("문의 번호") ) 
			searchOption = "inquiry_no";
		else if( currentOption.equals("아이디") ) 
			searchOption = "user_id";
		else if( currentOption.equals("제목") ) 
			searchOption = "inquiry_title";
		else 
			searchOption = "inquiry_content";
		
//		===== 정렬 SQL문 필터링 =====
		String sortRecords;
		if( currentSort.equals("작성일 최신 순") ) 
			sortRecords = "inquiry_date DESC";
		else if( currentSort.equals("작성일 오래된 순") )
			sortRecords = "inquiry_date ASC";
		else if( currentSort.equals("많은 답글 순") ) 
			sortRecords = "number_of_replies DESC";
		else 
			sortRecords = "number_of_replies ASC";

//		===== 기간 검색 기본값 =====
		if( fromThisDate == null || toThisDate == null ) {
			LocalDateTime now = LocalDateTime.now();
			toThisDate = now.format( DateTimeFormatter.ofPattern("yyyy-MM-dd") );
			fromThisDate = now.minusDays( 183 ).format( DateTimeFormatter.ofPattern("yyyy-MM-dd") );
		}
		
//		===== 페이지 매김을 위한 레코드 수 구하기 =====
		int numberOfRecords = inquiryDao.countRecords( tableName, allSearchOptions, searchOption, currentKeyword, fromThisDate, toThisDate );
		
//		===== pagination Bean 설정 =====
		pagination.setPagination( numberOfRecordsPerPage, currentPage, numberOfRecords, 5 );

//		===== 페이지 매김 정보 가져와서 DB에 저장 =====
		int firstRecordIndex = pagination.getFirstRecordIndex();
		int lastRecordIndex = pagination.getLastRecordIndex();
		List<InquiryDto> relationInstance = 
				inquiryDao.relationInstance( tableName, columnArray, allSearchOptions, firstRecordIndex, lastRecordIndex, searchOption, currentKeyword, sortRecords, fromThisDate, toThisDate );
	
		model.addAttribute( "numberOfRecords", numberOfRecords );
		model.addAttribute( "relationInstance", relationInstance );
		model.addAttribute( "pagination", pagination );
		model.addAttribute( "search_option", currentOption );
		model.addAttribute( "search_keyword", currentKeyword );
		model.addAttribute( "current_sort", currentSort );
		model.addAttribute( "fromThisDate", fromThisDate );
		model.addAttribute( "toThisDate", toThisDate );
		model.addAttribute( "contents", "./inquiry/one2one-inquiry-list.jsp");
		
		return "management/admin_index";
	}
	
	/* ==================== 리뷰 내용 가져오기 ==================== */
	@Transactional( readOnly = true )
	@RequestMapping("/admin/review_content")
	public String review_content( Model model,
								  @RequestParam( name = "index" ) int index
								) throws Exception {

		String tableName = "member_review";
		MemberReviewDto content = memberReviewDao.getContent( tableName, index );

		ArrayList<String> imageFileNames = imageInfoDao.getImageFileName( "review_image_info", "post_no", Integer.toString(index) );
		content.setImageFileList( imageFileNames );
		
		model.addAttribute( "content", content );
		
		return "management/review/review_content";
	}

	/* ==================== 주문 정보 조회 ==================== */
	@Transactional( readOnly = true )
	@RequestMapping( "/admin/order_list/detail" )
	public String order_detail( Model model,
								@RequestParam( name = "order_id" ) String orderId
							  ) throws Exception {

		OrderInfoDto data = orderInfoDao.detail( orderId );
		ArrayList<OrderProductInfoDto> productList = orderProductInfoDao.listOfOrderedProducts( orderId );
		data.setOrderProducts( productList );

		model.addAttribute( "data", data );
		
		return "management/order/order_detail";
	}
	
	/* ==================== 관계자 외 접근 시 이동하는 페이지 ==================== */
	@RequestMapping( "/information" )
	public String information_page( Model model,
									@RequestParam( name = "guide", required = false, defaultValue = "wrongApproach" ) String guide
								  ) {
		String guideText;
		
		switch( guide ) {
			case "forbidden" :
				guideText = "접근 권한이 없습니다.";
				break;
			default:
				guideText = "잘못된 접근입니다.";
				break;	
		}
		System.out.println( "  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_sjy] information" );
		System.out.println( "보여줄 문구 : " + guideText );
		
		model.addAttribute( "guide", guideText );
		model.addAttribute( "mainPage", "common/information.jsp" );
        return "index";
	}
	
	/* ==================== 관리자 페이지 이동 시 회원관리로 리다이렉트 ==================== */
	@RequestMapping( "/admin" )
	public String admin_root( HttpServletRequest request ) {
		return "redirect:/admin/member_list";
	}

	/* ==================== 공지사항 이미지 추가 ==================== */
	@Transactional
	@ResponseBody
	@RequestMapping( "admin/*/imageUpload" )
	public JSONObject imageUpload( Model model, HttpServletRequest request,
			@RequestParam("upload") MultipartFile... files
		) throws Exception {
		ArrayList<String> fileNameList = fileUploadAPI.upload( "N", files );
		HashMap<String, String> map = new HashMap<String, String>();	
		for( String fileName : fileNameList ) {
			map.put("url", "/notice/" + fileName );
		}
		return new JSONObject( map );
	}
	
	/* ==================== 입력한 문의 답글 저장하기 ==================== */
	@Transactional
	@RequestMapping( value = { "/admin/one2one_inquiry_reply_upload", "/admin/product_inquiry_reply_upload" } )
	public String inquiry_reply_upload( Model model, HttpServletRequest request,
										@RequestParam( name = "index" ) int index,
										@RequestParam( name = "content" ) String content
									  ) throws Exception {
		String nameOfInquiryTable;
		String nameOfReplyTable;
		String userId = (String)session.getAttribute("user_id");
		
		if( request.getRequestURI().equals("/admin/one2one_inquiry_reply_upload") ) {
			nameOfInquiryTable = "one2one_inquiry";
			nameOfReplyTable = "one2one_inquiry_reply";
		} 
		else if( request.getRequestURI().equals("/admin/product_inquiry_reply_upload") ) {
			nameOfInquiryTable = "product_inquiry";
			nameOfReplyTable = "product_inquiry_reply";
		} 
		else {
			throw new RuntimeException("답글 등록 실패. 현재 위치를 알 수 없습니다.");
		}
		
		replyDao.insertRecord( nameOfReplyTable, index, userId, content );
		
		int numberOfRecords = commonDao.countRecords( nameOfInquiryTable, "inquiry_no", Integer.toString( index ) );
		
		commonDao.updateRecord( nameOfInquiryTable, "number_of_replies", 
								Integer.toString(numberOfRecords + 1), "inquiry_no", Integer.toString( index ) );
		
		return inquiry_reply_list( model, request, index );
	}

	/* ==================== 답글 리스트 가져오기 ==================== */
	@Transactional( readOnly = true )
	@RequestMapping( value = { "/admin/one2one_inquiry_reply_list", "/admin/product_inquiry_reply_list" } )
	public String inquiry_reply_list( Model model, HttpServletRequest request,
									  @RequestParam( name = "index" ) int indexOfInquiry
								    ) throws Exception {
		
		String nameOfInquiryTable, nameOfReplyTable;
		
		if( request.getRequestURI().contains("one2one_inquiry") ) {
			nameOfInquiryTable = "one2one_inquiry";
			nameOfReplyTable = "one2one_inquiry_reply";
		} 
		else if( request.getRequestURI().contains("product_inquiry") ) {
			nameOfInquiryTable = "product_inquiry";
			nameOfReplyTable = "product_inquiry_reply";
		} 
		else {
			throw new RuntimeException("답글 가져오기 실패. 현재 위치를 알 수 없습니다.");
		}
		
		int numberOfRecords = replyDao.countRecords( nameOfReplyTable, indexOfInquiry );
		
		ReplyDto relationInstance = replyDao.relationInstance( nameOfReplyTable, indexOfInquiry );
		
		// 답글 수 저장
		inquiryDao.UpdateNumberOfReplies(nameOfInquiryTable, indexOfInquiry, numberOfRecords );
		
		model.addAttribute( "indexOfInquiry", indexOfInquiry );
		model.addAttribute( "numberOfReplies", numberOfRecords );
		model.addAttribute( "reply", relationInstance );
		
		return "management/inquiry/reply_list";
	}
	
	/* ==================== 문의 답글 삭제 ==================== */
	@Transactional
	@RequestMapping( value = { "/admin/one2one_inquiry_reply_delete", "/admin/product_inquiry_reply_delete" } )
	public String inquiry_reply_delete( Model model, HttpServletRequest request,
										@RequestParam( name = "indexOfInquiry" ) int indexOfInquiry,
										@RequestParam( name = "indexOfreply" ) int indexOfreply
									  ) throws Exception {
		
		String nameOfInquiryTable;
		String nameOfReplyTable;
		
		if( request.getRequestURI().equals("/admin/one2one_inquiry_reply_delete") ) {
			nameOfInquiryTable = "one2one_inquiry";
			nameOfReplyTable = "one2one_inquiry_reply";
		} 
		else if( request.getRequestURI().equals("/admin/product_inquiry_reply_delete") ) {
			nameOfInquiryTable = "product_inquiry";
			nameOfReplyTable = "product_inquiry_reply";
		} 
		else {
			throw new RuntimeException("답글 삭제 실패. 현재 위치를 알 수 없습니다.");
		}
		
		replyDao.deleteRecord( nameOfReplyTable, indexOfreply );
		
		int numberOfRecords = commonDao.countRecords( nameOfInquiryTable, "inquiry_no", Integer.toString( indexOfInquiry ) );
		
		commonDao.updateRecord( nameOfInquiryTable, "number_of_replies", 
								Integer.toString(numberOfRecords), "inquiry_no", Integer.toString( indexOfInquiry ) );
		
		return inquiry_reply_list( model, request, indexOfInquiry );
	}
	
	// ##################################################
	// # 												#
	// # 					공지 단건 삭제 				#
	// # 												#
	// ##################################################
	@Transactional
	@RequestMapping( "admin/notice_list/delete_post" )
	public String delete_post( Model model, HttpServletRequest request,
							   @RequestParam( "post_no" ) String value
							 ) throws Exception {
		 
//		##### 레코드 삭제 #####	    
		int sqlResult = commonDao.deleteRecords( "notice", "post_no", value );
		if( sqlResult == 0 ) throw new RuntimeException("레코드 업데이트 실패. 결과가 없습니다.");
		
//		##### 확인용 로그 #####	    
		System.out.println( "########### 상품 삭제 ###########" );
		System.out.println( "삭제된 레코드 : " + value );
		System.out.println( "결과 : " + sqlResult );
		
		return "redirect:/admin/notice_list";
	}

}
