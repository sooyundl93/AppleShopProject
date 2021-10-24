package com.study.springboot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.springboot.dao.IInquiryDao;
import com.study.springboot.dao.IProductInfoDao;
import com.study.springboot.dto.InquiryDto;

@Controller
public class product_inquiry {

	@Autowired
	private IInquiryDao inquiryDao;

	@Autowired
	private IProductInfoDao productInfoDao;

	@Autowired
	private PaginationService pagination;
	
	// ********************************************
	// *               상품문의                   *
	// ********************************************
	@RequestMapping("/order_list")
	public String order_list( RedirectAttributes redirect,HttpServletRequest request )throws Exception {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [product_inquiry] order_list");
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login";
		}else {			
			return "redirect:check_order_list";
		}
	}
	
	// ********************************************
	// *              상품문의-페이징             *
	// ********************************************
	@RequestMapping("/check_order_list")
	public String order_list_check( Model model,
			@RequestParam( name = "current_page", defaultValue = "1" ) int currentPage,
			@RequestParam( name = "number_of_records_per_page", defaultValue = "10" ) int numberOfRecordsPerPage,
			@RequestParam( name = "search_option", defaultValue = "전체" ) String currentOption,
			@RequestParam( name = "search_keyword", defaultValue = "" ) String currentKeyword,
			@RequestParam( name = "current_sort", defaultValue = "작성일 최신 순" ) String currentSort,
			@RequestParam( name = "from_this_date", required = false ) String fromThisDate,
			@RequestParam( name = "to_this_date", required = false ) String toThisDate
			  )throws Exception {
		
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
		model.addAttribute( "mainPage", "member/mypage/product_inquiry.jsp");
		
		return "index";
	}

}
