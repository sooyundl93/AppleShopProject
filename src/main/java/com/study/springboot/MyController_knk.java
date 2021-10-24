package com.study.springboot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.springboot.dao.IMember_infoDao;
import com.study.springboot.dao.IMember_reviewDao;
import com.study.springboot.dao.INoticeDao;
import com.study.springboot.dao.IOrder_id_seqDao;
import com.study.springboot.dao.IOrder_infoDao;
import com.study.springboot.dao.IOrder_product_infoDao;
import com.study.springboot.dao.IProduct_and_imageDao;
import com.study.springboot.dao.IProduct_and_reviewDao;
import com.study.springboot.dao.IProduct_image_infoDao;
import com.study.springboot.dao.IProduct_infoDao;
import com.study.springboot.dao.IProduct_info_and_shopping_cartDao;
import com.study.springboot.dao.IProduct_inquiryDao;
import com.study.springboot.dao.IProduct_inquiry_and_replyDao;
import com.study.springboot.dao.IReview_and_imageDao;
import com.study.springboot.dao.IShopping_cartDao;
import com.study.springboot.dto.Member_infoDto;
import com.study.springboot.dto.Order_infoDto;
import com.study.springboot.dto.Order_product_infoDto;
import com.study.springboot.dto.Product_and_reviewDto;
import com.study.springboot.dto.Product_infoDto;
import com.study.springboot.dto.Product_info_and_shopping_cartDto;
import com.study.springboot.dto.Product_inquiry_and_replyDto;
import com.study.springboot.dto.Review_and_imageDto;
import com.study.springboot.dto.Shopping_cartDto;

/**********************************************
*				 내일의 집  				  *
***********************************************
*	MyController_knk.java					  *
*											  *
*	작업자 : 김남권		 					  *
*   										  *
*   JSP에서 요청한 URI 분기 처리			  *
**********************************************/
@Controller
public class MyController_knk {

	/**************/
	/* Dao 초기화 */
	/**************/	
	@Autowired
	private IMember_infoDao member_infoDao;
	
	@Autowired
	private IMember_reviewDao member_ReviewDao;
		
	@Autowired
	private INoticeDao noticeDao;
		
	@Autowired
	private IOrder_infoDao order_infoDao;
	
	@Autowired
	private IOrder_product_infoDao order_product_infoDao;
	
	@Autowired
	private IProduct_image_infoDao product_image_infoDao;
	
	@Autowired
	private IProduct_infoDao product_infoDao;
	
	@Autowired
	private IProduct_inquiryDao product_inquiryDao;
	
	@Autowired
	private IShopping_cartDao shoppingCartDao;
	
	@Autowired
	private IProduct_and_imageDao product_and_imageDao;
	
	@Autowired
	private IProduct_and_reviewDao product_and_reviewDao;
	
	@Autowired
	private IReview_and_imageDao review_and_imageDao;
	
	@Autowired
	private IProduct_inquiry_and_replyDao product_inquiry_and_replyDao;
	
	@Autowired
	private IProduct_info_and_shopping_cartDao product_info_and_shopping_cartDao;
	
	@Autowired
	private IOrder_id_seqDao order_id_seqDao;
		
	
	/* 트랜잭션 매니저 초기화1 */
	@Autowired
	PlatformTransactionManager transactionManager;
	
	/* 트랜잭션 매니저 초기화2 */
	@Autowired
	TransactionDefinition definition;
	
	/* 암호화 API 초기화 */
	@Autowired
	CryptoService cryptoAPI;
	
	/* 로그 생성 API 초기화 */
	@Autowired
	LogMaker logMaker;
	
	/* 카카오페이 API 초기화 */
	@Autowired
	KakaopayService kakaoPayAPI;
	
	/* 파일 업로드 API 초기화 */
	@Autowired
	FileUploadService fileUploadAPI;
		
	/********************************************/
	/*            요청 URI 분기 시작            */
	/********************************************/
	/*************/
	/*			 */
	/* 메인 화면 */
	/*			 */
	/*************/
	@RequestMapping("/")
	public String root( RedirectAttributes redirect ) throws Exception {
		return "redirect:index";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk : index]");
		
		model.addAttribute("top5List", product_and_imageDao.top5List());
		model.addAttribute("new8List", product_and_imageDao.new8List());
		model.addAttribute("new8ReviewList", product_and_reviewDao.new8ReviewList());
		model.addAttribute("important_list", noticeDao.important_list());
		
		model.addAttribute("mainPage", "main.jsp");	
		return "index";
	}
	
	/******************/
	/*				  */
	/* 상품 상세 조회 */
	/*				  */
	/******************/
	@RequestMapping("/productDetail")
	public String productListForm(@RequestParam("product_code") String product_code,
								  @RequestParam( name = "reviewCurrentPage", required = false, defaultValue = "1" ) int reviewCurrentPage,
								  @RequestParam( name = "inquiryCurrentPage", required = false, defaultValue = "1" ) int inquiryCurrentPage,
								  HttpServletRequest request, Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk] productDetail");
		System.out.println("product_code : " + product_code);
		System.out.println("reviewCurrentPage : " + reviewCurrentPage);
		System.out.println("inquiryCurrentPage : " + inquiryCurrentPage);
		
		// Session
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");
		
		// 세션 체크
		if(user_id == null) {
			user_id = "";
		}
		
		/********************/
		/* 상품 정보 썸네일 */
		/********************/		
		// 상품정보 조회 결과 get
		Product_infoDto product_info = product_infoDao.product_info(product_code);
		
		// 적립 예정 마일리지 계산
		int accrued_mileage = product_info.getProduct_price() / 20;
				
		// 리뷰 개수&평균 별점 get
		Product_and_reviewDto parDto = product_and_reviewDao.product_score(product_code);
		
		// 별점 이미지 세팅
		float avg_score = parDto.getAverage_review_score();
		String star_img = null;
		
		if(avg_score <= 0.5) {
			star_img = "star_0.5_img.png";
		}else if(avg_score <= 1) {
			star_img = "star_1.0_img.png";
		}else if(avg_score <= 1.5) {
			star_img = "star_1.5_img.png";
		}else if(avg_score <= 2) {
			star_img = "star_2.0_img.png";
		}else if(avg_score <= 2.5) {
			star_img = "star_2.5_img.png";
		}else if(avg_score <= 3) {
			star_img = "star_3.0_img.png";
		}else if(avg_score <= 3.5) {
			star_img = "star_3.5_img.png";
		}else if(avg_score <= 4) {
			star_img = "star_4.0_img.png";
		}else if(avg_score <= 4.5) {
			star_img = "star_4.5_img.png";
		}else {
			star_img = "star_5.0_img.png";
		}
		
		// 문의 전체 수
		int totalInquiryCount = product_inquiryDao.countOfInquiry(product_code);
		
		
		/***************/
		/* 리뷰 페이징 */
		/***************/
		
		PaginationService pagiOfReview = new PaginationService();
		
		// 페이지 매김을 위한 레코드 수 구하기
		int reviewNumberOfRecords = member_ReviewDao.countOfReview(product_code);
		
		// pagination Bean 설정	
		pagiOfReview.setPagination( 3, reviewCurrentPage, reviewNumberOfRecords, 5 );
		
		// ##### 페이지 매김 정보 가져와서 DB에 저장 #####
		int firstRecordIndex = pagiOfReview.getFirstRecordIndex();
		int lastRecordIndex = pagiOfReview.getLastRecordIndex();
		
		ArrayList<Review_and_imageDto> member_review = review_and_imageDao.product_review(product_code, firstRecordIndex, lastRecordIndex);
		
		// 페이지 정보
		model.addAttribute("reviewPagination", pagiOfReview);
		
		
		/***************/
		/* 문의 페이징 */
		/***************/
		
		PaginationService pagiOfInquiry = new PaginationService();
		
		// 페이지 매김을 위한 레코드 수 구하기
		int inquiryNumberOfRecords = product_inquiry_and_replyDao.product_qna_count(product_code, user_id);
		
		// pagination Bean 설정	
		pagiOfInquiry.setPagination( 3, inquiryCurrentPage, inquiryNumberOfRecords, 5 );
		
		// ##### 페이지 매김 정보 가져와서 DB에 저장 #####
		firstRecordIndex = pagiOfInquiry.getFirstRecordIndex();
		lastRecordIndex = pagiOfInquiry.getLastRecordIndex();

		ArrayList<Product_inquiry_and_replyDto> product_qna = 
				product_inquiry_and_replyDao.product_qna(product_code, user_id, firstRecordIndex, lastRecordIndex);

		
		// 페이지 정보
		model.addAttribute("inquiryPagination", pagiOfInquiry);
				
		
		/**********************/
		/* model.addAttribute */
		/**********************/
		// 상품 정보
		model.addAttribute("product_info", product_infoDao.product_info(product_code));
		
		// 적립 예정 마일리지
		model.addAttribute("accrued_mileage", accrued_mileage);
		
		// 상품 이미지
		model.addAttribute("product_image_info", product_image_infoDao.product_image(product_code));
		
		// 리뷰 개수 & 평균 별점
		model.addAttribute("product_and_review", product_and_reviewDao.product_score(product_code));
		
		// 점수와 매칭되는 별 이미지
		model.addAttribute("avg_star_img", star_img);
		
		// 문의 전체 개수
		model.addAttribute("totalInquiryCount", totalInquiryCount);
		
		// 리뷰 내용 & 이미지
		model.addAttribute("member_review", member_review);
				
		// 상품 문의 목록
		model.addAttribute("product_qna", product_qna);
		
		model.addAttribute("mainPage", "common/product/product_detail.jsp");
		return "index";	
	}
	
	/****************/
	/*				*/
	/* 주문 결제 폼 */
	/*				*/
	/****************/
	@RequestMapping("/orderAndBuyForm")
	public String orderAndBuyForm(HttpServletRequest request, Model model) {
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk : orderAndBuy]");
		
		// Session
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");
		
		// 세션 체크
		if(user_id == null) {
			System.out.println("미로그인 사용자 접근");
			model.addAttribute("alert", "로그인 후 이용 가능한 서비스입니다.");
			return "redirect:loginForm";
		}
		
		/***************************************/
		/* 장바구니에 있는 주문 상품 정보 조회 */
		/***************************************/		
		int total_price = 0;
		int shipping_cost = 0;
		
		/****************/
		/* 총 주문 금액 */
		/****************/
		ArrayList<Product_info_and_shopping_cartDto> ucList = product_info_and_shopping_cartDao.user_cart_list(user_id);
		
		for(Product_info_and_shopping_cartDto list : ucList) {
			total_price += list.getProduct_total_price();
			shipping_cost += list.getProduct_shipping_cost();
		}
				
		/**********************/
		/* model.addAttribute */
		/**********************/
		// 배송지 세팅을 위한 회원 정보 조회
		model.addAttribute("member_info", member_infoDao.get_member(user_id));		
		// 주문 상품 정보
		model.addAttribute("user_cart_list", product_info_and_shopping_cartDao.user_cart_list(user_id));
		// 주문 총 금액
		model.addAttribute("total_price", total_price);
		// 배송비
		model.addAttribute("shipping_cost", shipping_cost);
		
		model.addAttribute("mainPage", "member/order/order.jsp");
		return "index";
	}
	
	/******************/
	/*				  */
	/* 주문 결제 액션 */
	/*				  */
	/******************/
	@RequestMapping("/orderAction")
	public String orderAction(@RequestParam("user_name") 			  String recipient_name,
							  @RequestParam("phone_number") 		  String phone_number,
							  @RequestParam("postal_code") 			  String postal_code,
							  @RequestParam("address") 				  String address,
							  @RequestParam("detailed_address") 	  String detailed_address,
							  @RequestParam("shipping_memo") 		  String shipping_memo,
							  @RequestParam("total_price") 			  int total_price_without_shipping_cost,
							  @RequestParam("shipping_cost") 		  int total_shipping_cost,
							  @RequestParam("input_used_mileage")     int used_mileage,
							  @RequestParam("input_discounted_price") int discounted_price,
							  @RequestParam("payment_method")         String payment_method,
							  HttpServletRequest request, 
							  Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk] orderAction");
		System.out.println("discounted_price : " + discounted_price);
		
		// 트랜잭션 매니저 초기화
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		// Session
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");

		// 세션 체크
		if(user_id == null) {
			System.out.println("미로그인 사용자 접근");
			model.addAttribute("alert", "로그인 후 이용 가능한 서비스입니다.");
			return "redirect:loginForm";
		}
		
		// 총 상품가격 + 배송비
		int total_price = total_price_without_shipping_cost + total_shipping_cost;
		
		int kakao_product_count = 0;
		
		
		/*********************/
		/* order_info INSERT */
		/*********************/
		// 주문번호 Merge 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		
		// 주문번호 앞 날짜		
		String dateOfOrderId = sdf.format(cal.getTime());
		// 주문번호 뒤 시퀀스
		String seqOfOrderId = order_id_seqDao.get_order_seq(dateOfOrderId).getOrder_id_seq();
		
		// 주문번호 생성		
		String order_id = "";
		order_id += dateOfOrderId;
		order_id += seqOfOrderId;
		
		// 적립 예정 마일리지
		int accrued_mileage = discounted_price / 20;
		
		// order_info INSERT query
		int result = order_infoDao.add_order_info(order_id, 
												  user_id, 
												  recipient_name, 
												  phone_number, 
												  postal_code, 
												  address, 
												  detailed_address, 
												  shipping_memo, 
												  total_price, 
												  used_mileage, 
												  discounted_price, 
												  total_shipping_cost, 
												  payment_method, 
												  accrued_mileage);
		
		// insert fail : ROLLBACK	
		if(result <= 0) {
			transactionManager.rollback(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction add_order_info [!!! ROLLBACK !!!]");
			model.addAttribute("alert", "ERROR-12301 서비스 이용에 불편을 드려 죄송합니다.");
			return "redirect:index";
		}
				
		
		/******************/
		/* mileage UPDATE */
		/******************/
		Member_infoDto miDto = member_infoDao.get_member(user_id);
		
		int mileage = miDto.getMileage();
		
		mileage -= used_mileage;
		
		result = member_infoDao.update_mileage(user_id, mileage);
		
		if(result <= 0) {
			transactionManager.rollback(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction update_mileage [!!! ROLLBACK !!!]");
			model.addAttribute("alert", "ERROR-12201 서비스 이용에 불편을 드려 죄송합니다.");
			return "redirect:index";
		}
		
		
		/************************************************/
		/* order_product_info INSERT 					*/
		/* AND product_info - number_of_products UPDATE */
		/************************************************/
		for(Shopping_cartDto scList : shoppingCartDao.user_cart_list(user_id)) {
			
			//**************************************
			// First : order_product_info INSERT !!!
			//**************************************
			String product_code = scList.getProduct_code();
			int number_of_products = scList.getNumber_of_products();
			
			Product_infoDto piDto  = product_infoDao.product_info(product_code);
			String product_name    = piDto.getProduct_name();
			String parent_category = piDto.getParent_category();
			String child_category  = piDto.getChild_category();
			String manufacturer    = piDto.getManufacturer();
			int    product_price   = piDto.getProduct_price();
			
			Product_info_and_shopping_cartDto psDto = product_info_and_shopping_cartDao.for_shipping_cost(user_id, product_code);
			int shipping_cost = psDto.getProduct_shipping_cost();
			
			kakao_product_count += number_of_products;
			
			int resultOfAddOrderProductInfo = 0;
			
			resultOfAddOrderProductInfo = order_product_infoDao.add_order_product_info(order_id, 
																					   product_code, 
																					   product_name, 
																					   parent_category, 
																					   child_category, 
																					   manufacturer, 
																					   product_price, 
																					   number_of_products,
																					   shipping_cost);
			
			// insert fail : ROLLBACK
			if(resultOfAddOrderProductInfo <= 0) {
				transactionManager.rollback(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction add_order_product_info [!!! ROLLBACK !!!]");
				model.addAttribute("alert", "ERROR-12302 서비스 이용에 불편을 드려 죄송합니다.");
				return "redirect:index";
			}

			//************************************************************
			// Second : product_info TABLE - number_of_products UPDATE !!!
			//************************************************************
			int number_of_sales = piDto.getNumber_of_sales();
			number_of_sales += 1;
			
			int updateNumberOfSales = 0;
			updateNumberOfSales = product_infoDao.update_number_of_sales(product_code, number_of_sales);
			
			// insert fail : ROLLBACK
			if(updateNumberOfSales <= 0) {
				transactionManager.rollback(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction update_number_of_sales [!!! ROLLBACK !!!]");
				model.addAttribute("alert", "ERROR-12202 서비스 이용에 불편을 드려 죄송합니다.");
				return "redirect:index";
			}
			
		}
		
		
		/*******************/
		/* 장바구니 DELETE */
		/*******************/
		result = shoppingCartDao.delete_user_cart(user_id);
		
		// delete fail : ROLLBACK
		if(result <= 0) {
			transactionManager.rollback(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction delete_user_cart [!!! ROLLBACK !!!]");
			model.addAttribute("alert", "ERROR-12401 서비스 이용에 불편을 드려 죄송합니다.");
			return "redirect:index";
		}
		
		
		/*************************/
		/* 결제 구분에 따른 분기 */
		/*************************/
		if(payment_method.equals("1")) {
			
			/*************/
			/* 카드 결제 */
			/*************/
			result = order_infoDao.update_order_status(order_id, 2);
			
			if(result <= 0) {
				transactionManager.rollback(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction update_order_status [!!! ROLLBACK !!!]");
				model.addAttribute("alert", "ERROR-12203 서비스 이용에 불편을 드려 죄송합니다.");
				return "redirect:index";
			}else {
				// delete success : COMMIT
				transactionManager.commit(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction Finally [COMMIT]");
			}
			
			logMaker.logWriter("주문결제", "결과 : " + Integer.toString(result), user_id, recipient_name, phone_number, postal_code, address,
					detailed_address, shipping_memo, Integer.toString(total_price_without_shipping_cost),
					Integer.toString(total_shipping_cost), Integer.toString(used_mileage),
					Integer.toString(discounted_price), "카드결제");
						
			return "redirect:orderCompleteCard?order_id=" + order_id;
			
		}else if(payment_method.equals("2")) {
			
			/***********************/
			/* 카카오페이 결제요청 */
			/***********************/
			String url = "";
			String tid = null;
			
			// 단건 결제를 위해 상품명은 회사명으로 세팅
			String kakao_product_name = "내일의집";
			
			// 카카오페이 필수 파라미터 부가세 세팅
			int tax_free_amount = discounted_price/10;
			
			// tid 저장을 위한 주문정보Dto
			// 결제 요청 후 tid가 발급되면 Dto에 넣어온다.
			Order_infoDto oiDto = new Order_infoDto();
			
			url = kakaoPayAPI.kakaoPay(order_id, user_id, 
									   kakao_product_name, 
									   kakao_product_count, 
									   discounted_price, 
									   tax_free_amount, 
									    oiDto);
			
			// kakaoPayAPI.kakaoPay에서 넣어놓은 tid값 가져오기
			tid = oiDto.getPayment_id();
			
			// tid 저장을 위해 order_info 테이블에 업데이트 
			result = order_infoDao.update_kakao_tid(tid, order_id);
			if(result <= 0) {
				// tid UPDATE fail : ROLLBACK
				transactionManager.rollback(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction update_kakao_tid [!!! ROLLBACK !!!]");
				model.addAttribute("alert", "ERROR-12204 서비스 이용에 불편을 드려 죄송합니다.");
				return "redirect:index";
			}else {
				// tid UPDATE success : COMMIT
				transactionManager.commit(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction update_kakao_tid [COMMIT]");
			}
			
			logMaker.logWriter("주문결제", "결과 : " + Integer.toString(result), user_id, recipient_name, phone_number, postal_code, address,
								detailed_address, shipping_memo, Integer.toString(total_price_without_shipping_cost),
								Integer.toString(total_shipping_cost), Integer.toString(used_mileage),
								Integer.toString(discounted_price), "카카오페이", tid);
			
			// 카카오페이 결제 요청 성공 시 반환받은 URL 이동
			return "redirect:" + url;
			
		}else if(payment_method.equals("3")) {
			
			/*******************/
			/* 무통장입금 결제 */
			/*******************/
			transactionManager.commit(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction Finally [COMMIT]");
			
			logMaker.logWriter("주문결제", "결과 : " + Integer.toString(result), user_id, recipient_name, phone_number, postal_code, address,
					detailed_address, shipping_memo, Integer.toString(total_price_without_shipping_cost),
					Integer.toString(total_shipping_cost), Integer.toString(used_mileage),
					Integer.toString(discounted_price), "무통장입금");
			
			return "redirect:orderCompleteAccount?order_id=" + order_id;
			
		}else {
			
			// 결제수단 에러!!!
			transactionManager.rollback(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction Finally [!!! ROLLBACK !!!]");
			model.addAttribute("alert", "ERROR-12501 서비스 이용에 불편을 드려 죄송합니다.");
			return "redirect:index";
			
		}
		
	}
	
	/************************/
	/*						*/
	/* 카카오페이 결제 승인 */
	/*						*/
	/************************/
	@RequestMapping("/kakaopayConfirmAction")
	public String kakaopayConfirm(@RequestParam("order_id") String order_id, 
								  @RequestParam("pg_token") String pg_token,
								  HttpServletRequest request, 
								  Model model) {
	
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk] kakaopayConfirmAction");
		
		// 트랜잭션 매니저 초기화
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		// 주문번호로 주문 정보 조회(저장했던 tid 획득을 위함) 
		Order_infoDto oiDto = order_infoDao.get_order_info(order_id);

		String tid = oiDto.getPayment_id();
		String user_id = oiDto.getUser_id();
		
		// 카카오API 결제 승인 요청
		String result = kakaoPayAPI.kakaoPay(tid, order_id, user_id, pg_token);
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [result : ]" + result);
		
		int resultOfUpdate = 0;
		
		// 승인 요청까지 성공시 COMMIT
		if(result.equals("success")) {
			resultOfUpdate = order_infoDao.update_order_status(order_id, 2);
			
			if(resultOfUpdate <= 0) {
				transactionManager.rollback(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " kakaopayConfirmAction update_order_status [!!! ROLLBACK !!!]");
				model.addAttribute("alert", "ERROR-12205 서비스 이용에 불편을 드려 죄송합니다.");
				return "redirect:index";
			}else {
				transactionManager.commit(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " kakaopayConfirmAction update_order_status Finally [COMMIT]");
			}
		}
		logMaker.logWriter("카카오페이 결제승인", "결과 : " + result, user_id, order_id, pg_token);
		return "redirect:orderCompleteCard?order_id="+order_id;
		
	}
	
	/**********************************/
	/*								  */
	/* 카드 & 카카오페이 결제 완료 폼 */
	/*								  */
	/**********************************/
	@RequestMapping("orderCompleteCard")
	public String orderCompleteCard(@RequestParam("order_id") String order_id, 
					  			    HttpServletRequest request, 
					  			    Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk] orderCompleteCard");
		
		String query = "";
		int countOfFor = 0;
		
		// 트랜잭션 매니저 초기화
		TransactionStatus status = transactionManager.getTransaction(definition);

		// Session
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");

		// 세션 체크
		if(user_id == null) {
			System.out.println("로그인 절차 필요");
			return "/common/login/login";
		}
		
		
		/**********************************************/
		/* mileage UPDATE 							  */
		/* 실제 결제가 끝난 시점에 마일리지 적립한다. */
		/**********************************************/
		Member_infoDto miDto = member_infoDao.get_member(user_id);
		
		int mileage = miDto.getMileage();
		
		Order_infoDto oiDto = order_infoDao.get_order_info(order_id);
		
		int used_mileage = oiDto.getDiscounted_price() / 20;
		
		mileage += used_mileage;
		
		int mileage_result = member_infoDao.update_mileage(user_id, mileage);
		
		if(mileage_result <= 0) {
			transactionManager.rollback(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderCompleteCard update_mileage [!!! ROLLBACK !!!]");
			model.addAttribute("alert", "ERROR-12206 서비스 이용에 불편을 드려 죄송합니다.");
			return "redirect:index";
		}else {
			transactionManager.commit(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderCompleteCard Finally [COMMIT]");
		}
		
		// 상품 개수 * 가격 저장할 ArrayList
		ArrayList<Integer> prodTotalPriceList = new ArrayList<>();
		
		for(Order_product_infoDto opDto : order_product_infoDao.user_order_list(order_id)) {
			
			//***************
			// 동적 쿼리 조합
			//***************
			countOfFor++;
			
			String product_code = opDto.getProduct_code();
			
			if(countOfFor == 1) {
				query += ("product_code = '" + product_code + "' ");
			}else {
				query += ("OR product_code = '" + product_code + "' ");
			}
			
			//***************************
			// 상품 개수 * 상품 가격 저장
			//***************************
			int number_of_orders = opDto.getNumber_of_orders();
			int product_price = opDto.getProduct_price();
			
			prodTotalPriceList.add(number_of_orders * product_price);
			
		}
		
		// 상품 이미지&정보 들을 forEach문으로 출력할 수 있게
		// 0 ~ 총 상품개수-1 (=인덱스)을 리스트로 뿌려줌 
		int sizeOfopList = order_product_infoDao.user_order_list(order_id).size();
		
		ArrayList<Integer> indexList = new ArrayList<>();
		
		for(int i = 0; i < sizeOfopList; i++) {
			indexList.add(i);
		}
		
		logMaker.logWriter("결제 완료", user_id, order_id);
		
		// 주문 상품 이미지
		model.addAttribute("imageList", product_image_infoDao.order_success_image(query));
		// 주문 상품 정보
		model.addAttribute("orderProductList", order_product_infoDao.user_order_list(order_id));
		// 주문 정보
		model.addAttribute("orderInfo", order_infoDao.get_order_info(order_id));
		// 상품 개수 인덱스
		model.addAttribute("indexList", indexList);
		// 상품 개수 * 가격 정보
		model.addAttribute("prodTotalPriceList", prodTotalPriceList);
		
		model.addAttribute("mainPage", "member/order/complete_card.jsp");
		return "index";
	}
	
	/***************************/
	/*						   */
	/* 무통장입금 결제 완료 폼 */
	/*						   */
	/***************************/
	@RequestMapping("orderCompleteAccount")
	public String orderCompleteAccount(@RequestParam("order_id") String order_id, 
					  			       HttpServletRequest request, 
					  			       Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk] orderCompleteAccount");
		
		// Session
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");

		String query = "";
		int countOfFor = 0;

		// 상품 개수 * 가격 저장할 ArrayList
		ArrayList<Integer> prodTotalPriceList = new ArrayList<>();
		
		for(Order_product_infoDto opDto : order_product_infoDao.user_order_list(order_id)) {
			
			//***************
			// 동적 쿼리 조합
			//***************
			countOfFor++;
			
			String product_code = opDto.getProduct_code();
			
			if(countOfFor == 1) {
				query += ("product_code = '" + product_code + "' ");
			}else {
				query += ("OR product_code = '" + product_code + "' ");
			}
			
			//***************************
			// 상품 개수 * 상품 가격 저장
			//***************************
			int number_of_orders = opDto.getNumber_of_orders();
			int product_price = opDto.getProduct_price();
			
			prodTotalPriceList.add(number_of_orders * product_price);
			
		}
		
		// 상품 이미지&정보 들을 forEach문으로 출력할 수 있게
		// 0 ~ 총 상품개수-1 (=인덱스)을 리스트로 뿌려줌 
		int sizeOfopList = order_product_infoDao.user_order_list(order_id).size();
		
		ArrayList<Integer> indexList = new ArrayList<>();
		
		for(int i = 0; i < sizeOfopList; i++) {
			indexList.add(i);
		}
		
		logMaker.logWriter("무통장입금 주문 완료", user_id, order_id);
		
		// 주문 상품 이미지
		model.addAttribute("imageList", product_image_infoDao.order_success_image(query));
		// 주문 상품 정보
		model.addAttribute("orderProductList", order_product_infoDao.user_order_list(order_id));
		// 주문 정보
		model.addAttribute("orderInfo", order_infoDao.get_order_info(order_id));
		// 상품 개수 인덱스
		model.addAttribute("indexList", indexList);
		// 상품 개수 * 가격 정보
		model.addAttribute("prodTotalPriceList", prodTotalPriceList);
		
		model.addAttribute("mainPage", "member/order/complete_account.jsp");
		return "index";
	}
	
	/***************/
	/*			   */
	/* 바로구매 폼 */
	/*			   */
	/***************/
	@RequestMapping("/straight_order")
	public String straight_order(@RequestParam("number_of_products") int number_of_products,
							     @RequestParam("product_code") String product_code,
							     HttpServletRequest request, 
							     Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk : straight_order]");
		
		// Session
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");
		
		// 세션 체크
		if(user_id == null) {
			System.out.println("미로그인 사용자 접근");
			model.addAttribute("alert", "로그인 후 이용 가능한 서비스입니다.");
			return "redirect:loginForm";
		}
		
		/****************/
		/* 총 주문 금액 */
		/****************/
		Product_infoDto piDto = product_infoDao.product_info(product_code);
		
		int shipping_cost = piDto.getShipping_cost() * number_of_products;
		int total_price = (piDto.getProduct_price() * number_of_products);
		String product_name = piDto.getProduct_name();
		
		/**********************/
		/* model.addAttribute */
		/**********************/
		// 배송지 세팅을 위한 회원 정보 조회
		model.addAttribute("member_info", member_infoDao.get_member(user_id));
		// 상품명
		model.addAttribute("product_name", product_name);
		// 주문 개수
		model.addAttribute("number_of_products", number_of_products);
		// 주문 총 금액
		model.addAttribute("total_price", total_price);
		// 배송비
		model.addAttribute("shipping_cost", shipping_cost);
		// 상품 이미지
		model.addAttribute("product_image", product_image_infoDao.product_image_one(product_code));
		// 상품 코드
		model.addAttribute("product_code", product_code);
		
		model.addAttribute("mainPage", "member/order/straight_order.jsp");
		return "index";
	}
	
	/*****************/
	/*				 */
	/* 바로구매 액션 */
	/*				 */
	/*****************/
	@RequestMapping("/straight_order_action")
	public String straight_order_action(@RequestParam("product_code") 		    String product_code,
									    @RequestParam("number_of_products")     int number_of_products,
									    @RequestParam("user_name") 			    String recipient_name,
									    @RequestParam("phone_number") 		    String phone_number,
									    @RequestParam("postal_code") 		    String postal_code,
									    @RequestParam("address") 			    String address,
									    @RequestParam("detailed_address") 	    String detailed_address,
									    @RequestParam("shipping_memo") 		    String shipping_memo,
									    @RequestParam("total_price") 		    int total_price_without_shipping_cost,
									    @RequestParam("shipping_cost") 		    int total_shipping_cost,
									    @RequestParam("input_used_mileage")     int used_mileage,
									    @RequestParam("input_discounted_price") int discounted_price,
									    @RequestParam("payment_method")         String payment_method,
									    HttpServletRequest request, 
									    Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk] straight_order_action");
		
		// 트랜잭션 매니저 초기화
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		// Session
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");
		
		// 세션 체크
		if(user_id == null) {
			System.out.println("미로그인 사용자 접근");
			model.addAttribute("alert", "로그인 후 이용 가능한 서비스입니다.");
			return "redirect:loginForm";
		}
				
		// 총 상품가격 + 배송비
		int total_price = total_price_without_shipping_cost + total_shipping_cost;
		
		int kakao_product_count = 0;
		
		/*********************/
		/* order_info INSERT */
		/*********************/
		// 주문번호 Merge 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		
		// 주문번호 앞 날짜		
		String dateOfOrderId = sdf.format(cal.getTime());
		// 주문번호 뒤 시퀀스
		String seqOfOrderId = order_id_seqDao.get_order_seq(dateOfOrderId).getOrder_id_seq();
		
		// 주문번호 생성		
		String order_id = "";
		order_id += dateOfOrderId;
		order_id += seqOfOrderId;
		
		// 적립 예정 마일리지
		int accrued_mileage = discounted_price / 20;
		
		// order_info INSERT query
		int result = order_infoDao.add_order_info(order_id, 
												  user_id, 
												  recipient_name, 
												  phone_number, 
												  postal_code, 
												  address, 
												  detailed_address, 
												  shipping_memo, 
												  total_price, 
												  used_mileage, 
												  discounted_price, 
												  total_shipping_cost, 
												  payment_method, 
												  accrued_mileage);
		
		// insert fail : ROLLBACK	
		if(result <= 0) {
			transactionManager.rollback(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction add_order_info [!!! ROLLBACK !!!]");
			model.addAttribute("alert", "ERROR-12301 서비스 이용에 불편을 드려 죄송합니다.");
			return "redirect:index";
		}
				
		/******************/
		/* mileage UPDATE */
		/******************/
		Member_infoDto miDto = member_infoDao.get_member(user_id);
		
		int mileage = miDto.getMileage();
		
		mileage -= used_mileage;
		
		result = member_infoDao.update_mileage(user_id, mileage);
		
		if(result <= 0) {
			transactionManager.rollback(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction update_mileage [!!! ROLLBACK !!!]");
			model.addAttribute("alert", "ERROR-12201 서비스 이용에 불편을 드려 죄송합니다.");
			return "redirect:index";
		}
		
		//**************************************
		// First : order_product_info INSERT !!!
		//**************************************
		Product_infoDto piDto  = product_infoDao.product_info(product_code);
		
		String product_name    = piDto.getProduct_name();
		String parent_category = piDto.getParent_category();
		String child_category  = piDto.getChild_category();
		String manufacturer    = piDto.getManufacturer();
		int    product_price   = piDto.getProduct_price();
				
		kakao_product_count = number_of_products;
		
		int resultOfAddOrderProductInfo = 0;
		
		resultOfAddOrderProductInfo = order_product_infoDao.add_order_product_info(order_id, 
																				   product_code, 
																				   product_name, 
																				   parent_category, 
																				   child_category, 
																				   manufacturer, 
																				   product_price, 
																				   number_of_products,
																				   total_shipping_cost);
		
		// insert fail : ROLLBACK
		if(resultOfAddOrderProductInfo <= 0) {
			transactionManager.rollback(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction add_order_product_info [!!! ROLLBACK !!!]");
			model.addAttribute("alert", "ERROR-12302 서비스 이용에 불편을 드려 죄송합니다.");
			return "redirect:index";
		}

		//************************************************************
		// Second : product_info TABLE - number_of_products UPDATE !!!
		//************************************************************
		int number_of_sales = piDto.getNumber_of_sales();
		number_of_sales += 1;
		
		int updateNumberOfSales = 0;
		updateNumberOfSales = product_infoDao.update_number_of_sales(product_code, number_of_sales);
		
		// insert fail : ROLLBACK
		if(updateNumberOfSales <= 0) {
			transactionManager.rollback(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction update_number_of_sales [!!! ROLLBACK !!!]");
			model.addAttribute("alert", "ERROR-12202 서비스 이용에 불편을 드려 죄송합니다.");
			return "redirect:index";
		}
		
		/*************************/
		/* 결제 구분에 따른 분기 */
		/*************************/
		if(payment_method.equals("1")) {
			
			/*************/
			/* 카드 결제 */
			/*************/
			result = order_infoDao.update_order_status(order_id, 2);
			
			if(result <= 0) {
				transactionManager.rollback(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction update_order_status [!!! ROLLBACK !!!]");
				model.addAttribute("alert", "ERROR-12203 서비스 이용에 불편을 드려 죄송합니다.");
				return "redirect:index";
			}else {
				// delete success : COMMIT
				transactionManager.commit(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction Finally [COMMIT]");
			}
			

			logMaker.logWriter("주문결제", "결과 : " + Integer.toString(result), user_id, recipient_name, phone_number, postal_code, address,
					detailed_address, shipping_memo, Integer.toString(total_price_without_shipping_cost),
					Integer.toString(total_shipping_cost), Integer.toString(used_mileage),
					Integer.toString(discounted_price), "카드결제");
						
			return "redirect:orderCompleteCard?order_id=" + order_id;
			
		}else if(payment_method.equals("2")) {
			
			/***********************/
			/* 카카오페이 결제요청 */
			/***********************/
			String url = "";
			String tid = null;
			
			// 단건 결제를 위해 상품명은 회사명으로 세팅
			String kakao_product_name = "내일의집";
			
			// 카카오페이 필수 파라미터 부가세 세팅
			int tax_free_amount = discounted_price/10;
			
			// tid 저장을 위한 주문정보Dto
			// 결제 요청 후 tid가 발급되면 Dto에 넣어온다.
			Order_infoDto oiDto = new Order_infoDto();
			
			url = kakaoPayAPI.kakaoPay(order_id, user_id, 
									   kakao_product_name, 
									   kakao_product_count, 
									   discounted_price, 
									   tax_free_amount, 
									    oiDto);
			
			// kakaoPayAPI.kakaoPay에서 넣어놓은 tid값 가져오기
			tid = oiDto.getPayment_id();
			
			// tid 저장을 위해 order_info 테이블에 업데이트 
			result = order_infoDao.update_kakao_tid(tid, order_id);
			if(result <= 0) {
				// tid UPDATE fail : ROLLBACK
				transactionManager.rollback(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction update_kakao_tid [!!! ROLLBACK !!!]");
				model.addAttribute("alert", "ERROR-12204 서비스 이용에 불편을 드려 죄송합니다.");
				return "redirect:index";
			}else {
				// tid UPDATE success : COMMIT
				transactionManager.commit(status);
				System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction update_kakao_tid [COMMIT]");
			}

			logMaker.logWriter("주문결제", "결과 : " + Integer.toString(result), user_id, recipient_name, phone_number, postal_code, address,
					detailed_address, shipping_memo, Integer.toString(total_price_without_shipping_cost),
					Integer.toString(total_shipping_cost), Integer.toString(used_mileage),
					Integer.toString(discounted_price), "카카오페이", tid);
			
			return "redirect:" + url;
			
		}else if(payment_method.equals("3")) {
			
			/*******************/
			/* 무통장입금 결제 */
			/*******************/
			transactionManager.commit(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction Finally [COMMIT]");

			logMaker.logWriter("주문결제", "결과 : " + Integer.toString(result), user_id, recipient_name, phone_number, postal_code, address,
					detailed_address, shipping_memo, Integer.toString(total_price_without_shipping_cost),
					Integer.toString(total_shipping_cost), Integer.toString(used_mileage),
					Integer.toString(discounted_price), "무통장입금");
			
			return "redirect:orderCompleteAccount?order_id=" + order_id;
			
		}else {
			// 결제수단 에러!!!
			transactionManager.rollback(status);
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " orderAction Finally [!!! ROLLBACK !!!]");
			model.addAttribute("alert", "ERROR-12501 서비스 이용에 불편을 드려 죄송합니다.");
			return "redirect:index";
		}
		
	}
	
	/************/
	/*			*/
	/* 이용약관 */
	/*			*/
	/************/
	@RequestMapping("/termsOfUse")
	public String termsOfUse(HttpServletRequest request, Model model) {
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk : termsOfUse]");
		
		model.addAttribute("mainPage", "common/cscenter/terms_of_use.jsp");
		return "index";
	}
	
	/********************/
	/*					*/
	/* 개인정보보호방침 */
	/*					*/
	/********************/
	@RequestMapping("/personalPolicy")
	public String personalPolicy(HttpServletRequest request, Model model) {
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk : personalPolicy]");
		
		model.addAttribute("mainPage", "common/cscenter/personal_policy.jsp");
		return "index";
	}
	
	/***********************/
	/*					   */
	/* 무통장입금 입금확인 */
	/*					   */
	/***********************/
	@RequestMapping("/confirm_payment")
	public String confirm_payment(HttpServletRequest request, Model model,
								  @RequestParam("order_id") String order_id) {
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk : confirm_payment]");
		
		System.out.println("order_id : " + order_id);
		
		// 트랜잭션 매니저 초기화
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		int updateResult = order_infoDao.update_order_status(order_id, 2);
		
		// !!!주문상태 업데이트 실패 시 롤백!!!
		if(updateResult <= 0) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " confirm_payment update_order_status [!!! ROLLBACK !!!]");
			transactionManager.rollback(status);
			model.addAttribute("alert", "ERROR-12203 서비스 이용에 불편을 드려 죄송합니다.(주문 상태 변경 실패)");
			return "redirect:/admin";
		}
		
		/* 무통장 결제의 경우 관리자가 '입금확인' 상태 변경 후 */
		/* 마일리지 적립이 이루어집니다. 					   */
		
		/* 주문 정보에서 사용자 ID와 적립예정 마일리지 GET */
		Order_infoDto oiDto = order_infoDao.get_order_info(order_id);
		String user_id = oiDto.getUser_id();
		int accrued_milege = oiDto.getAccrued_mileage();
		
		/* 위에서 가져온 ID로 고객 현재 마일리지 조회 */
		Member_infoDto miDto = member_infoDao.get_member(user_id);
		int memberMileage = miDto.getMileage();
				
		System.out.println("user_id : " + user_id);
		System.out.println("accrued_mileage : " + accrued_milege);
		System.out.println("nowMileage : " + memberMileage);
		
		memberMileage += accrued_milege;
		
		System.out.println("plus mileage : " + memberMileage);
		
		int mileageUpdateResult = member_infoDao.update_mileage(user_id, memberMileage);
		
		// !!!마일리지 업데이트 실패 시 롤백!!!		
		if(mileageUpdateResult <= 0) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " confirm_payment update_mileage [!!! ROLLBACK !!!]");
			transactionManager.rollback(status);
			model.addAttribute("alert", "ERROR-12206 서비스 이용에 불편을 드려 죄송합니다.(마일리지 적립 실패)");
			return "redirect:/admin";
		}else {
			// 모두 업데이트 성공 시 COMMIT ! 
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " confirm_payment Finally [COMMIT]");
			transactionManager.commit(status);
		}
		
		return "redirect:/admin/order_list/detail?order_id=" + order_id;
	}
	
	/******************/
	/*	              */
	/*  주문상태 변경 */
	/*		          */
	/******************/
	@RequestMapping("/order_status_change")
	public String order_status_change(HttpServletRequest request, Model model,
								  	  @RequestParam("order_id") String order_id,
								  	  @RequestParam("order_status") int order_status) {
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_knk : order_status_change]");
		
		System.out.println("order_id : " + order_id);
		System.out.println("order_status : " + order_status);
		
		int result = order_infoDao.update_order_status(order_id, order_status);
		
		if(result <= 0) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " order_status_change update_order_status [!!! FAIL !!!]");
			model.addAttribute("alert", "ERROR-12203 서비스 이용에 불편을 드려 죄송합니다.(주문 상태 변경 실패)");
			return "redirect:/admin";
		}
		
		return "redirect:/admin/order_list/detail?order_id=" + order_id;
	}
	
}