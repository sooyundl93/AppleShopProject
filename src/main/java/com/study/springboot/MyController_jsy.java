package com.study.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.springboot.dao.INoticeDao;
import com.study.springboot.dao.INotice_image_infoDao;
import com.study.springboot.dto.NoticeDto;

/**********************************************
*				 내일의 집  				  *
***********************************************
*	MyController_jsy.java					  *
*											  *
*	작업자 : 장수연		 					  *
*   										  *
*   JSP에서 요청한 URI 분기 처리			  *
**********************************************/

@Controller
public class MyController_jsy {

	/**************/
	/* Dao 초기화 */
	/**************/	
	@Autowired
	private INotice_image_infoDao notice_image_infoDao;
	
	@Autowired
	private INoticeDao noticeDao;	
	
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
	/*											*/
	/*            요청 URI 분기 시작            */
	/*											*/
	/********************************************/
	
	// *****************
	// * 공지사항 목록 *
	// *****************
	@RequestMapping("noticelist")
	public String noticelist( Model model,
							  @RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage,
							  @RequestParam(name = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
							  @RequestParam(name = "search_option", required = false, defaultValue = "all") String search_option,
							  @RequestParam(name = "search_keyword", required = false, defaultValue = "") String search_keyword
							)throws Exception {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_jsy] noticelist");
		
		// DB에서 페이징을 하기 전에 공지사항 갯수를 먼저 구해야한다.
		int noticeCount = noticeDao.noticeCount(search_option, search_keyword); 		

		Pagination pagination = new Pagination(currentPage, cntPerPage);

		pagination.setTotalRecordCount(noticeCount);				

		int firstRecordIndex = pagination.getFirstRecordIndex();
		int lastRecordIndex = pagination.getLastRecordIndex();
		int total_page_count = pagination.getTotalPageCount();
		int noticelist_on_current_page = 
				(noticeCount >= lastRecordIndex)
				? cntPerPage : noticeCount % cntPerPage;

		System.out.println("########### 공지사항 관리 ###########");
		System.out.println("검색 옵션 및 키워드: " + search_option + ", \"" + search_keyword + "\"");
		System.out.println("페이지 당 리스트 수: " + cntPerPage);
		System.out.println("현재 옵션 공지사항 갯수: " + noticeCount);
		System.out.println("현재/총 페이지: " + pagination.getCurrentPage() + "/" + total_page_count);
		System.out.println("현재 페이지 공지사항 갯수: " + noticelist_on_current_page);
		System.out.print("현재 페이지 시작/끝 인덱스: ");
		System.out.println(firstRecordIndex + "/" + (firstRecordIndex + noticelist_on_current_page - 1));
		System.out.println();

		List<NoticeDto> noticeList = 
				noticeDao.noticeList(firstRecordIndex, lastRecordIndex, search_option, search_keyword);

		model.addAttribute("noticeCount", noticeCount);		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("search_option", search_option);
		model.addAttribute("search_keyword", search_keyword);	
		
		model.addAttribute("notice_flag", noticeDao.important_list());

		model.addAttribute("mainPage", "common/cscenter/notice.jsp");		
		return "index";
	}

	// **********************
	// * 공지사항 상세 조회 *
	// **********************
	@RequestMapping("noticeView")
	public String noticeView( @RequestParam("post_no") int post_no, Model model ) throws Exception {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_jsy] noticeView");
		
		//조회수 +1 UPDATE
		noticeDao.hit( post_no );
		
		model.addAttribute("notice_view_image", notice_image_infoDao.notice_view_image(post_no));

		model.addAttribute("notice_view", noticeDao.noticeView(post_no));
		
		model.addAttribute("notice_flag", noticeDao.noticeFlag(post_no));

		model.addAttribute("mainPage", "common/cscenter/notice_view.jsp");		
		return "index";
		
	}	

	// **********************
	// *        faq         *
	// **********************
	@RequestMapping("faqList")
	public String faqList( Model model ) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_jsy] faqList");
		
		model.addAttribute("mainPage", "common/cscenter/faq.jsp");

		return "index";
		
	}

	// **********************
	// *      회사소개       *
	// **********************
	@RequestMapping("company")
	public String company( Model model ) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_jsy] company");
		
		model.addAttribute("mainPage", "common/cscenter/company.jsp");

		return "index";
		
	}

}







