package com.study.springboot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.springboot.dao.IMember_infoDao;
import com.study.springboot.dao.IMember_reviewDao;
import com.study.springboot.dao.IOne2one_inquiryDao;
import com.study.springboot.dao.IOne2one_inquiry_replyDao;
import com.study.springboot.dao.IOrder_infoDao;
import com.study.springboot.dao.IOrder_infoDao_IOrder_product_infoDao;
import com.study.springboot.dao.IOrder_product_infoDao;
import com.study.springboot.dao.IProduct_infoDao;
import com.study.springboot.dao.IProduct_infoDao_IProduct_image_infoDao_IMember_reviewDao;
import com.study.springboot.dao.IProduct_inquiryDao;
import com.study.springboot.dao.IProduct_inquiry_replyDao;
import com.study.springboot.dao.IReview_image_infoDao;
import com.study.springboot.dao.IReview_image_infoDao_IMember_reviewDao;
import com.study.springboot.dao.IShopping_cartDao;
import com.study.springboot.dao.IShopping_cartDao_iProduct_infoDao_iProductimage_infoDao;
import com.study.springboot.dao.Imember_reviewDao_Iproduct_infoDao;
import com.study.springboot.dao.Iproduct_info_Iproduct_inquiry;
import com.study.springboot.dto.Member_infoDto;
import com.study.springboot.dto.Member_reviewDto;
import com.study.springboot.dto.Order_infoDto;
import com.study.springboot.dto.Product_infoDto;
import com.study.springboot.dto.Product_inquiryDto;
import com.study.springboot.dto.Review_image_infoDto;
import com.study.springboot.dto.Review_image_infoDto_Member_review_Dto;
import com.study.springboot.dto.Shopping_cartDto_product_info_Dto_product_image_infoDto;

/**********************************************
*				 ????????? ???  				  *
***********************************************
*	MyController_ydh.java					  *
*											  *
*	????????? : ?????????		 					  *
*   										  *
*   JSP?????? ????????? URI ?????? ??????			  *
**********************************************/
@Controller
public class MyController_ydh {

	/**************/
	/* Dao ????????? */
	/**************/
	@Autowired
	private IMember_infoDao member_InfoDao;

	@Autowired
	private IMember_reviewDao member_ReviewDao;

	@Autowired
	private IOne2one_inquiry_replyDao one2one_inquiry_replyDao;

	@Autowired
	private IOne2one_inquiryDao one2one_inquiryDao;

	@Autowired
	private IOrder_infoDao order_infoDao;

	@Autowired
	private IOrder_product_infoDao order_listDao;

	@Autowired
	private IProduct_inquiry_replyDao product_inquiry_replyDao;

	@Autowired
	private IProduct_inquiryDao product_inquiryDao;

	@Autowired
	private IReview_image_infoDao review_image_infoDao;

	@Autowired
	private IShopping_cartDao shoppingCartDao;
	
	@Autowired
	private IOrder_infoDao_IOrder_product_infoDao order_info_dao_product_infodao;
	
	@Autowired
	private Iproduct_info_Iproduct_inquiry product_info_product_inquiry;
	
	@Autowired
	private Imember_reviewDao_Iproduct_infoDao member_review_product_info;
	
	@Autowired
	private IShopping_cartDao_iProduct_infoDao_iProductimage_infoDao shopping_cart_product_info_productimage_info;
	
	@Autowired
	private IProduct_infoDao_IProduct_image_infoDao_IMember_reviewDao product_info_product_image_info_member_review;
	
	@Autowired
	private IReview_image_infoDao_IMember_reviewDao review_image_info_member_review;
	
	@Autowired
	private IProduct_infoDao product_infoDao;
	
	/* ???????????? ????????? ?????????1 */
	@Autowired
	PlatformTransactionManager transactionManager;

	/* ???????????? ????????? ?????????2 */
	@Autowired
	TransactionDefinition definition;

	/* ????????? API ????????? */
	@Autowired
	CryptoService cryptoAPI;

	/* ?????? ?????? API ????????? */
	@Autowired
	LogMaker logMaker;

	/* ??????????????? API ????????? */
	@Autowired
	KakaopayService kakaoPayAPI;

	/* ?????? ????????? API ????????? */
	@Autowired
	FileUploadService fileUploadAPI;
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(200000000);
		multipartResolver.setMaxInMemorySize(200000000);
		multipartResolver.setDefaultEncoding("utf-8");
		return multipartResolver;
	}
	
	/********************************************/
	/*											*/
	/* 			?????? URI ?????? ?????? 				*/
	/*											*/
	/********************************************/
	
	// ********************************************
	// *                   ?????? ??????              *
	// ********************************************
	@RequestMapping("/product_search")
	public String product_search(@RequestParam(value="searchWord",required=false) String searchWord,
								 RedirectAttributes redirect) {
		
		redirect.addAttribute("page", "1");
		redirect.addAttribute("searchWord", searchWord);
		
		return "redirect:product_search2";
		
	}
	
	// ********************************************
    // *               ?????? ?????? ?????????           *
	// ********************************************
	@RequestMapping("/product_search2")
	public String product_search2(@RequestParam(value="searchWord",required=false) String searchWord,
								  Model model,
								  HttpServletRequest request,
								  RedirectAttributes redirect) {
		
		redirect.addAttribute("page", "1");
		
		String page = request.getParameter("page");
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		if(product_info_product_image_info_member_review.count_search(searchWord)%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.count_search(searchWord)/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.count_search(searchWord))/12+1);
		}
		
		System.out.println(searchWord);
		
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("count2",product_info_product_image_info_member_review.count_search(searchWord));
		model.addAttribute("list2", product_info_product_image_info_member_review.allSearch(String.valueOf(startRowNum), String.valueOf(endRowNum),searchWord));
		
		System.out.println(product_info_product_image_info_member_review.allSearch(String.valueOf(startRowNum), String.valueOf(endRowNum),searchWord));
		
		model.addAttribute("mainPage", "common/product/product_02.jsp");		
		return "index";
		
	}
	
	// ********************************************
	// *        ????????????1(?????? ??????)              *
	// ********************************************
	@RequestMapping("/join1")
	public String join1(Model model) {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] join1");
		
		model.addAttribute("mainPage", "common/login/join1.jsp");
		return "index";
		
	}

	// ********************************************
	// *        ????????????2(??????????????????)           *
	// ********************************************
	@RequestMapping("/join2")
	public String join2(Model model) {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] join2");
		
		model.addAttribute("mainPage", "common/login/join2.jsp");		
		return "index";
		
	}

	// ********************************************
	// *               ??????????????????               *
	// ********************************************
	@RequestMapping("/joinAction")
	public String joinAction(@RequestParam("user_id") String user_id,
							 @RequestParam("user_password") String user_password, 
							 @RequestParam("user_name") String user_name,
							 @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
							 @RequestParam("email") String email,
							 @RequestParam(value = "receive_email_flag", required = false) String receive_email_flag,
							 @RequestParam("phone_number") String phone_number,
							 @RequestParam(value = "receive_messages_flag", required = false) String receive_messages_flag,
							 @RequestParam("postal_code") String postal_code, 
							 @RequestParam("address") String address,
							 @RequestParam("detailed_address") String detailed_address, 
							 @RequestParam("pw_question") String pw_question,
							 @RequestParam("pw_answer") String pw_answer, 
							 @RequestParam("gender") String gender,
							 Model model) {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] joinAction");
		
		int result = 0;

		// ???????????? ?????????
		String crypto_pw = cryptoAPI.crypto(user_password);
		System.out.println(user_password);
		System.out.println(crypto_pw);
		
		// ???????????? null??? ???????????? "N" ?????? ??????
		String email_receive = null;

		if (receive_email_flag == null) {
			email_receive = "N";
		} else {
			email_receive = "Y";
		}

		String phone_receive = null;
		
		if (receive_messages_flag == null) {
			phone_receive = "N";
		} else {
			phone_receive = "Y";
		}

		System.out.println("email_receive:" + email_receive);
		System.out.println("phone_receive:" + phone_receive);

		// ???????????? DB ??????
		result = member_InfoDao.joinAction(user_id, crypto_pw, user_name, birthday, email, email_receive, phone_number,
				phone_receive, postal_code, address, detailed_address, pw_question, pw_answer, gender);

		System.out.println(result);
		
		logMaker.logWriter("??????????????????", user_id, user_name, email, email_receive, phone_number, 
						   phone_receive, postal_code, postal_code, address, detailed_address,
						   pw_question, pw_answer, gender);
		
		if (result == 1) {
			model.addAttribute("mainPage", "common/login/join3.jsp");			
			return "index";
		} else {
			model.addAttribute("mainPage", "common/login/join2.jsp");			
			return "index";
		}

	}

	// ********************************************
	// *         ????????????3(??????????????????)          *
	// ********************************************
	@RequestMapping("/join3")
	public String join3(Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_yhd] join3");
		
		model.addAttribute("mainPage", "common/login/join3.jsp");			
		return "index";
		
	}

	// ********************************************
	// *              ????????? ????????????             *
	// ********************************************
	@RequestMapping("/idCheck")
	@ResponseBody
	public String idCheck(@RequestParam("user_id") String user_id) {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_yhd] idCheck");
		
		String user_id_check=user_id.toLowerCase();
		
		if(user_id_check.contains("admin")){
			return("1");
		}else {
			int result = member_InfoDao.idCheck(user_id);
			System.out.println("result:" + result);

			if (result >= 1) { // ????????? ?????????
				return ("1");
			} else {
				return ("0");
			}
		}
		
	}
	// ********************************************
	// *              ????????? ????????????             *
	// ********************************************
	@RequestMapping("/emailCheck")
	@ResponseBody
	public String emailCheck(@RequestParam("email") String email) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_yhd] emailCheck");

		String email_check=email.toLowerCase();
		
		if(email_check.contains("admin")){
			return("1");
		}else {
			int result = member_InfoDao.emailCheck(email);
			System.out.println("result:" + result);

			if (result >= 1) { // ????????? ?????????
				return ("1");
			} else {
				return ("0");
			}
		}
	
	}
	
	// ********************************************
	// *        ????????? ????????????2 (????????????)       *
	// ********************************************
	@RequestMapping("/emailCheck2")
	@ResponseBody
	public String emailCheck2(@RequestParam("email") String email, HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_yhd] emailCheck2");

		String uid = (String) request.getSession().getAttribute("user_id");
		Member_infoDto dto = member_InfoDao.change(uid);
		String existing = dto.getEmail();
		
		System.out.println(existing);
		System.out.println(email);
		
		if(email.equals(existing)) {
			return("0");
		}else{
			int result = member_InfoDao.emailCheck(email);
		
			System.out.println("result:" + result);

			if (result >= 1) { // ????????? ?????????
				return ("1");
			} else {
				return ("0");
			}
		}
		
	}

	// ********************************************
	// *              ???????????????                  *
	// ********************************************
	@RequestMapping("loginForm")
	public String loginForm(Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] loginForm");
		
		model.addAttribute("mainPage", "common/login/login.jsp");			
		return "index";
		
	}

	// ********************************************
	// *             ????????? ??????                  *
	// ********************************************
	@RequestMapping("/loginAction")
	public String loginAction(@RequestParam("user_id") String user_id,
							  @RequestParam("user_password") String user_password, 
							  HttpServletRequest request, 
							  Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] loginAction");
		
		String crypto_pw = cryptoAPI.crypto(user_password);
		
		int result = member_InfoDao.loginAction(user_id, crypto_pw);
		System.out.println("result:" + result);
		
		// result 1 : ????????? ?????? / result 0 : ????????? ??????
		if (result == 1) {
			System.out.println("alert:" + "????????????????????????.");

			Member_infoDto mDto = member_InfoDao.get_member(user_id);
			String user_role = mDto.getUser_role();
			String user_name = mDto.getUser_name();
			// ?????? ????????? ??? ?????? Setting
			HttpSession session = request.getSession();
			session.setAttribute("user_id", user_id);
            session.setAttribute("user_role", user_role);
            session.setAttribute("user_name", user_name);
			
			System.out.println("user_id:" + user_id);
			System.out.println(session.getAttribute("user_id"));
            System.out.println(user_role);
            
            logMaker.logWriter("?????????", "?????? : " + Integer.toString(result), user_id, user_role, user_name);
			
			return "redirect:index";
		} else {
			System.out.println("alert:" + "????????? ?????????????????????.");

			model.addAttribute("alert", "????????? ?????????????????????.");

			return "common/login/loginFail";
		}
	}

	// ********************************************
	// *              ????????? ??????                 *
	// ********************************************
	@RequestMapping("/idFind")
	public String idFind(HttpServletRequest request, Model model) {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] idFind");
		
		return "common/login/id_find";
		
	}

	// ********************************************
	// *            ????????? ?????? ??????              *
	// ********************************************
	@RequestMapping("idFindAction")
	public String idFindAction(@RequestParam("user_name") String user_name, 
							   @RequestParam("email") String email,
							   Model model) {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] idFindAction");
		
		System.out.println(member_InfoDao.idFindAction(user_name, email));
		
		model.addAttribute("dto5", member_InfoDao.idFindAction(user_name, email));
		
		logMaker.logWriter("????????? ??????", user_name, email);
		
		// ??????, ???????????? ??????????????? ????????? id_findFail.jsp ??????
		if (member_InfoDao.idFindAction(user_name, email) != null) {
			return "common/login/id_findSuccess";
		} else {
			return "common/login/id_findFail";
		}
	}

	// ********************************************
	// *           ???????????? ??????                  *
	// ********************************************
	@RequestMapping("/passwordFind")
	public String passwordFind(HttpServletRequest request, Model model) {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] passwordFind");
		
		return "common/login/password_find";
		
	}

	// ********************************************
	// *            ???????????? ?????? ??????            *
	// ********************************************
	@RequestMapping("/passwordFindAction")
	public String passwordFindAction(@RequestParam("user_id") String user_id,
									 @RequestParam("pw_question") String pw_question, 
									 @RequestParam("pw_answer") String pw_answer, 
									 HttpServletRequest request, 
									 Model model) {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] passwordFindAction");
		
		System.out.println(member_InfoDao.passwordFindAction(user_id, pw_question, pw_answer));
		
		model.addAttribute("dto6", member_InfoDao.passwordFindAction(user_id, pw_question, pw_answer));
		
		logMaker.logWriter("???????????? ??????", user_id, pw_question, pw_answer);
		
		if (member_InfoDao.passwordFindAction(user_id, pw_question, pw_answer) != null) {
			System.out.println("alert:" + "????????????????????????.");
			
			HttpSession session = request.getSession();
			session.setAttribute("user_id", user_id);
			
			System.out.println("user_id:" + user_id);
			System.out.println(session.getAttribute("user_id"));
			
			model.addAttribute("mainPage", "member/mypage/password_right.jsp");			
			return "index";
		} else {
			return "common/login/password_findFail";
		}
		
	}
	
	// ********************************************
	// *              ?????????????????? ???             *
	// ********************************************
	@RequestMapping("/password_change")
	public String password_change(HttpServletRequest request, Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] password_change");

		String uid = (String) request.getSession().getAttribute("user_id");

		model.addAttribute("dto2", member_InfoDao.change(uid));
		System.out.println(uid);

		model.addAttribute("mainPage", "member/mypage/password_change.jsp");			
		return "index";
		
	}
	
	// ********************************************
	// *              ????????????????????????            *
	// ********************************************
	@RequestMapping("/password_change_action")
	public String password_change_action(@RequestParam("user_password") String user_password,
										 HttpServletRequest request, Model model) {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] password_change_action");
		
		String uid = (String) request.getSession().getAttribute("user_id");
		String crypto_pw = cryptoAPI.crypto(user_password);
		
		logMaker.logWriter("???????????? ??????", uid);
		
		model.addAttribute("dto2", member_InfoDao.passwordchangeAction(crypto_pw,uid));
		
		System.out.println(uid);

		return "member/mypage/password_changeOK";
		
	}

	// ********************************************
	// *              ??????????????????                *
	// ********************************************
	@RequestMapping("/mypage_change")
	public String mypage_change(HttpServletRequest request, Model model) {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] mypage_change");
		
		String uid = (String) request.getSession().getAttribute("user_id");
		if(uid==null) {	
			model.addAttribute("mainPage", "member/order/need_login.jsp");			
			return "index";
		}else {
			model.addAttribute("dto2", member_InfoDao.change(uid));
			System.out.println(uid);
	
			model.addAttribute("mainPage", "member/mypage/mypage_change.jsp");			
			return "index";
		}
		
	}

	// ********************************************
	// *             ?????????????????? ??????            *
	// ********************************************
	@RequestMapping("/changeAction")
	public String changeAction(	@RequestParam("email") String email,
								@RequestParam(value = "receive_email_flag", required = false) String receive_email_flag,
								@RequestParam("phone_number") String phone_number,
								@RequestParam(value = "receive_messages_flag", required = false) String receive_messages_flag,
								@RequestParam("postal_code") String postal_code, 
								@RequestParam("address") String address,
								@RequestParam("detailed_address") String detailed_address, 
								@RequestParam("pw_question") String pw_question,
								@RequestParam("pw_answer") String pw_answer, 
								@RequestParam("user_id") String user_id,
								@RequestParam("user_password") String user_password, 
								Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] changeAction");
		
		String crypto_pw = cryptoAPI.crypto(user_password);
		
		int result = 0;
		
		// ????????? ?????? null??? ???????????? "N"?????? ????????????.
		String email_receive = null;

		if (receive_email_flag == null) {
			email_receive = "N";
		} else {
			email_receive = "Y";
		}

		String phone_receive = null;
		
		if (receive_messages_flag == null) {
			phone_receive = "N";
		} else {
			phone_receive = "Y";
		}

		System.out.println("email_receive:" + email_receive);
		System.out.println("phone_receive:" + phone_receive);

		// ?????? ?????? DB UPDATE
		result = member_InfoDao.changeAction(email, email_receive, phone_number, phone_receive, postal_code, address,
											 detailed_address, pw_question, pw_answer, user_id, crypto_pw);

		System.out.println(result);
		
		logMaker.logWriter("??????????????????", "?????? : " + Integer.toString(result), user_id, email, email_receive, phone_number, phone_receive,
							postal_code, address, detailed_address, pw_question, pw_answer);
		
		if (result == 1) {
			return "/member/mypage/ChangeOK_refresh";
		} else {
			return "/member/mypage/withDrawFail";
		}
		
	}

	// ********************************************
	// *              ???????????? ???                 *
	// ********************************************
	@RequestMapping("/withDraw")
	public String withDraw(Model model, HttpServletRequest request) {

		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] withDraw");
		String uid = (String) request.getSession().getAttribute("user_id");

		if(uid==null) {	
			return "/member/order/need_login";
		}else {
			System.out.println(uid);
					
			return "member/mypage/withdraw";
		}
		
	}

	// ********************************************
	// *             ???????????? ??????                *
	// ********************************************
	@RequestMapping("/withDrawAction")
	public String withDrawAction(@RequestParam(value = "user_id", required = false) String user_id,
								 @RequestParam("user_password") String user_password, 
								 HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] withDrawAction");
		
		String crypto_pw = cryptoAPI.crypto(user_password);
		
		int result = 0;
		
		result = member_InfoDao.withDrawAction(user_id, crypto_pw);
		System.out.println("result:" + result);
		
		logMaker.logWriter("????????????", "?????? : " + Integer.toString(result), user_id);
		
		if (result == 1) {
			// ?????? ?????? ?????? ?????? ??? ?????? ??????
			HttpSession session = request.getSession();
			session.invalidate();
			
			return "/member/mypage/withDrawOK";
		} else {
			return "/member/mypage/withDrawFail";
		}

	}

	// ********************************************
	// *                ????????????                  *
	// ********************************************
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] logout");
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:index";
		
	}
	
	// ********************************************
	// *               1:1??????                    *
	// ********************************************
	@RequestMapping("/one2one_inquiry")
	public String one2one_inquiry1( RedirectAttributes redirect,
									HttpServletRequest request,
									Model model) throws Exception {		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] one2one_inquiry");
		
		redirect.addAttribute("page", "1");
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			model.addAttribute("mainPage", "member/order/need_login.jsp");			
			return "index";
		}else {
			return "redirect:one2one_inquiry2";
		}
		
	}
		
	// ********************************************
	// *         1:1?????? ????????? ??????              *
	// ********************************************
	@RequestMapping("/one2one_inquiry2")
	public String one2one_inquiry2(RedirectAttributes redirect,
								   HttpServletRequest request, 
								   Model model,
								   @RequestParam(value="start_date", required=false) String start_date, 
								   @RequestParam(value="end_date", required=false) String end_date) throws ParseException {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] one2one_inquiry2");
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		int result = one2one_inquiryDao.count(uid);
		System.out.println("result:"+result);
		
		if(one2one_inquiryDao.count(uid)%10==0) {
			model.addAttribute("count",(one2one_inquiryDao.count(uid)/10));
		}else {
			model.addAttribute("count",(one2one_inquiryDao.count(uid)/10)+1);
		}
		if (one2one_inquiryDao.count(uid)>=10) {
			model.addAttribute("count2",10);
		}else {
			model.addAttribute("count2",one2one_inquiryDao.count(uid));
		}
		
		model.addAttribute("startdate","");
		model.addAttribute("enddate","");
		model.addAttribute("dto3", member_InfoDao.change(uid));
		
		System.out.println(uid);
		
		redirect.addAttribute("page", "1");
		
		String page = request.getParameter("page");
		System.out.println( "page:" + page);
		model.addAttribute("page", page);
		
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 10; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		model.addAttribute("list", one2one_inquiryDao.listPageDao2(String.valueOf(startRowNum), String.valueOf(endRowNum),uid));  //????????? 10???

		model.addAttribute("mainPage", "member/mypage/mypage_one2one.jsp");
		return "index"; //list.jsp ??????????????? "list"????????? ?????????.	
	}
	
	// ********************************************
	// *           1:1?????? - ????????????             *
	// ********************************************
	@RequestMapping("one2one_date_search")
	public String one2one_date_search(@RequestParam("page") String page,RedirectAttributes redirect,
									  @RequestParam("start_date") String start_date,
									  @RequestParam("end_date") String end_date,
									  Model model,
									  HttpServletRequest request) throws ParseException {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] one2one_date_search");
		
		String sd = start_date;

		SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy-MM-dd");

		Date sdd = transFormat1.parse(sd);
		
		String ed = end_date;

		SimpleDateFormat transFormat2 = new SimpleDateFormat("yyyy-MM-dd");

		Date edd = transFormat2.parse(ed);

		model.addAttribute("startdate",start_date);
		model.addAttribute("enddate",end_date);
		
		System.out.println( "page:" + page);
		model.addAttribute("page", page);
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(one2one_inquiryDao.count(uid)%10==0) {
			model.addAttribute("count",(one2one_inquiryDao.count_date(uid,sdd,edd)/10));
		}else {
			model.addAttribute("count",(one2one_inquiryDao.count_date(uid,sdd,edd)/10)+1);
		}
		
		model.addAttribute("count2",one2one_inquiryDao.count_date(uid,sdd,edd));
		model.addAttribute("dto3", member_InfoDao.change(uid));
		
		System.out.println(uid);
		
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 10; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);

		model.addAttribute("list", one2one_inquiryDao.listPageDao(String.valueOf(startRowNum), String.valueOf(endRowNum),uid,sdd,edd));  //????????? 10???
		
		model.addAttribute("mainPage", "member/mypage/mypage_one2one.jsp");
		return "index"; //list.jsp ??????????????? "list"????????? ?????????.		
	}
	
	
	// ********************************************
	// *       1???1(?????????)?????? - ????????? ??????     *
	// ********************************************
	@RequestMapping("/one2one_popup")
	public String one2one_popup(@RequestParam("inquiry_no") int inquiry_no, Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] one2one_popup");
		
		model.addAttribute("dto2",one2one_inquiryDao.content(inquiry_no));
		model.addAttribute("dto",one2one_inquiry_replyDao.content(inquiry_no));
		
		return "member/mypage/one2one_popup";
		
	}
	
	// ********************************************
	// *       1???1(?????????)?????? - ???????????????      *
	// ********************************************
	@RequestMapping("/one2one_write_popup")
	public String one2one_popup_write() {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] one2one_write_popup");
		
		return "member/mypage/one2one_popup_write";
		
	}
	
	// ********************************************
	// *       1???1(?????????)?????? - ???????????????      *
	// ********************************************
	@RequestMapping("/one2one_write_popup_Action")
	public String one2one_write_popup_Action(@RequestParam("inquiry_title") String inquiry_title,
											 @RequestParam("inquiry_content") String inquiry_content,
											 HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] one2one_write_popup_Action");
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		int result = one2one_inquiryDao.one2one_write_popup_Action(uid, inquiry_title, inquiry_content);
		
		System.out.println(result);
		System.out.println(inquiry_title);
		System.out.println(inquiry_content);
		System.out.println(uid);
		
		logMaker.logWriter("1:1????????? ??????", "?????? : " + Integer.toString(result), uid, inquiry_title, inquiry_content);

		if(result==1) {
			return "member/mypage/writeOK";
		}else {
			return "member/mypage/one2one_write_popup";
		}
		
	}
	
	// ********************************************
	// *        1???1(?????????)?????? - ?????????         *
	// ********************************************
	@RequestMapping("/one2one_delete")
	public String one2one_delete(@RequestParam("inquiry_no") int inquiry_no) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] one2one_delete");
		
		int result = one2one_inquiryDao.delete(inquiry_no);
		
		if (result==1) {
			return "redirect:one2one_inquiry";
		}else {
			return "member/mypage/mypage_one2one";
		}
		
	}

	// ********************************************
	// *     1???1(?????????)?????? - ????????? ?????????     *
	// ********************************************
	@RequestMapping("/one2one_delete2")
	public String one2one_delete2(@RequestParam("inquiry_no") int inquiry_no) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] one2one_delete2");
		
		int result = one2one_inquiryDao.delete(inquiry_no);
		
		if (result==1) {
			return "member/mypage/deleteOK2";
		}else {
			return "member/mypage/mypage_one2one";
		}
		
	}
	

	// ********************************************
	// *              ???????????? ??????               *
	// ********************************************
	@RequestMapping("/product_inquiry")
	public /*@ResponseBody*/ String product_inquiry1( RedirectAttributes redirect, 
													  HttpServletRequest request,
													  Model model) throws Exception {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_inquiry");
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login";
		}else {
			redirect.addAttribute("page", "1");
		
			return "redirect:product_inquiry2";
		}
		
	}
	
	// ********************************************
	// *           ???????????? ????????? ??????           *
	// ********************************************
	@RequestMapping("/product_inquiry2")
	public String product_inquiry(RedirectAttributes redirect,
								  HttpServletRequest request, 
								  Model model,
								  @RequestParam(value="start_date", required=false) String start_date,
								  @RequestParam(value="end_date", required=false) String end_date) throws ParseException {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_inquiry2");
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		int result = product_inquiryDao.count(uid);
		
		System.out.println("result:"+result);
		
		if(product_inquiryDao.count(uid)%10==0) {
			model.addAttribute("count",(product_inquiryDao.count(uid)/10));
		}else {
			model.addAttribute("count",(product_inquiryDao.count(uid)/10)+1);
		}
		if(product_inquiryDao.count(uid)>=10) {
			model.addAttribute("count2",10);
		}else {
			model.addAttribute("count2",product_inquiryDao.count(uid));
		}
		model.addAttribute("startdate","");
		model.addAttribute("enddate","");
		model.addAttribute("dto3", member_InfoDao.change(uid));
		
		System.out.println(uid);
		
		redirect.addAttribute("page", "1");
		String page = request.getParameter("page");
		System.out.println( "page:" + page);
		
		model.addAttribute("page", page);
		
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 10; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);

		model.addAttribute("list", product_info_product_inquiry.listPageDao2(String.valueOf(startRowNum), String.valueOf(endRowNum),uid));  //????????? 10???
		System.out.println("list:"+product_info_product_inquiry.listPageDao2(String.valueOf(startRowNum), String.valueOf(endRowNum),uid));
			
		model.addAttribute("mainPage", "member/mypage/mypage_inquiry.jsp");
		return "index"; //list.jsp ??????????????? "list"????????? ?????????.	
		
	}
	
	// ********************************************
	// *           ???????????? - ????????????            *
	// ********************************************
	@RequestMapping("product_date_search")
	public String product_date_search(@RequestParam("page") String page,
									  RedirectAttributes redirect,
									  @RequestParam("start_date") String start_date,
									  @RequestParam("end_date") String end_date,
									  Model model,HttpServletRequest request) throws ParseException {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_date_search");
		
		String sd = start_date;

		SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy-MM-dd");

		Date sdd = transFormat1.parse(sd);
		
		String ed = end_date;

		SimpleDateFormat transFormat2 = new SimpleDateFormat("yyyy-MM-dd");

		Date edd = transFormat2.parse(ed);

		model.addAttribute("startdate",start_date);
		model.addAttribute("enddate",end_date);
		
		System.out.println( "page:" + page);
		model.addAttribute("page", page);
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(product_inquiryDao.count(uid)%10==0) {
			model.addAttribute("count",(product_inquiryDao.count_date(uid,sdd,edd)/10));
		}else {
			model.addAttribute("count",(product_inquiryDao.count_date(uid,sdd,edd)/10)+1);
		}
		
		model.addAttribute("count2",product_inquiryDao.count_date(uid,sdd,edd));
		model.addAttribute("dto3", member_InfoDao.change(uid));
		
		System.out.println(uid);
		
		
		
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 10; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		model.addAttribute("list", product_info_product_inquiry.listPageDao(String.valueOf(startRowNum), String.valueOf(endRowNum),uid,sdd,edd));  //????????? 10???
		
		model.addAttribute("mainPage", "member/mypage/mypage_inquiry.jsp");
		return "index"; //list.jsp ??????????????? "list"????????? ?????????.
		
	}
	
	// ********************************************
	// *            ???????????? - ?????????             *
	// ********************************************
	@RequestMapping("/product_inquiry_delete")
	public String product_inquiry_delete(@RequestParam("inquiry_no") int inquiry_no) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_inquiry_delete");
		
		int result = product_inquiryDao.delete(inquiry_no);
		
		if (result==1) {
			return "redirect:product_inquiry";
		}else {
			return "member/mypage/mypage_inquiry";
		}
		
	}

	// ********************************************
	// *            ???????????? - ????????? ?????????      *
	// ********************************************
	@RequestMapping("/product_inquiry_delete2")
	public String product_inquiry_delete2(@RequestParam("inquiry_no") int inquiry_no) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_inquiry_delete2");
		
		int result = product_inquiryDao.delete(inquiry_no);
		
		if (result==1) {
			return "member/mypage/deleteOK2";
		}else {
			return "member/mypage/mypage_inquiry";
		}
		
	}
	
	// ********************************************
	// *           ???????????? - ??????????????????        *
	// ********************************************
	@RequestMapping("/product_inquiry_popup")
	public String product_inquiry_popup(@RequestParam("inquiry_no") int inquiry_no, Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_inquiry_popup");
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login";
		}else {
			model.addAttribute("dto2",product_info_product_inquiry.content(inquiry_no));
			model.addAttribute("dto",product_inquiry_replyDao.content(inquiry_no));
			
			return "member/mypage/product_popup";		
		}
		
	}
	
	// ********************************************
	// *           ???????????? - ???????????????          *
	// ********************************************
	@RequestMapping("/product_inquiry_popup_edit")
	public String product_inquiry_popup_edit(@RequestParam("inquiry_no") int inquiry_no, Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_inquiry_popup_edit");
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login";
		}else {			
			model.addAttribute("dto2",product_inquiryDao.content(inquiry_no));
			
			// ?????? ????????? ????????? ???????????? ?????????
			Product_inquiryDto piDto = product_inquiryDao.content(inquiry_no);
			String product_code = piDto.getProduct_code();
			
			Product_infoDto prodDto = product_infoDao.product_info(product_code);
			
			model.addAttribute("product_name", prodDto.getProduct_name());
			
			return "member/mypage/product_popup_edit";
		}
		
	}
	
	// ********************************************
	// *            ???????????? - ???????????????         *
	// ********************************************
	@RequestMapping("/product_inquiry_edit_action")
	public String product_inquiry_edit_action(@RequestParam("inquiry_no") int inquiry_no,
											  @RequestParam("inquiry_content") String inquiry_content,
											  @RequestParam(value = "private_flag", required = false) String private_flag,
											  HttpServletRequest request,Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_inquiry_edit_action");
		
		model.addAttribute("dto2",product_inquiryDao.content(inquiry_no));
		String uid = (String) request.getSession().getAttribute("user_id");

		if (private_flag == null) {
			private_flag = "N";
		} else {
			private_flag = "Y";
		}
		
		int result = product_inquiryDao.product_inquiry_edit_action(inquiry_content,private_flag,inquiry_no,uid);
		System.out.println(result);
		System.out.println(inquiry_content);
		System.out.println(uid);
		
		logMaker.logWriter("??????????????? ??????", "?????? : " + Integer.toString(result), uid, inquiry_content);
		
		if(result==1) {
			return "member/mypage/updateOK";
		}else {
			return "member/mypage/product_popup_edit";
		}
		
	}
		
	// ********************************************
	// *            ???????????? - ???????????????         *
	// ********************************************
	@RequestMapping("/product_inquiry_popup_write")
	public String product_inquiry_popup_write(@RequestParam("product_code") String product_code,
											  Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_inquiry_popup_write");
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login2";
		}else {
			model.addAttribute("product_code",product_code);
			
			// ?????? ????????? ????????? ???????????? ?????????
			Product_infoDto prodDto = product_infoDao.product_info(product_code);
			
			model.addAttribute("product_name", prodDto.getProduct_name());
			
			return "member/mypage/product_popup_write";
		}
		
	}
	
	// ********************************************
	// *            ???????????? - ???????????????         *
	// ********************************************
	@RequestMapping("/product_inquiry_write_action")
	public String product_inquiry_write_action(@RequestParam("product_code") String product_code,
											   @RequestParam("inquiry_content") String inquiry_content,
											   @RequestParam(value = "private_flag", required = false) String private_flag,
											   HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_inquiry_write_action");
		
		String uid = (String) request.getSession().getAttribute("user_id");

		if (private_flag == null) {
			private_flag = "N";
		} else {
			private_flag = "Y";
		}
		
		if(uid==null) {
			return "member/order/need_login";
		}else {
			System.out.println(private_flag);
			int result = product_inquiryDao.product_inquiry_write_action(product_code,uid,inquiry_content,private_flag);
			System.out.println(result);
			System.out.println(inquiry_content);
			System.out.println(uid);
			
			logMaker.logWriter("??????????????? ??????", "?????? : " + Integer.toString(result), uid, inquiry_content);
			
			if(result==1) {
				return "member/mypage/writeOK";
			}else {
				return "member/mypage/product_popup_write";
			}
			
		}
		
	}
	
//	// ********************************************
//	// *               ????????????                   *
//	// ********************************************
//	@RequestMapping("/order_list")
//	public String order_list( RedirectAttributes redirect,HttpServletRequest request ) throws Exception {
//		
//		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] order_list");
//		String uid = (String) request.getSession().getAttribute("user_id");
//		
//		if(uid==null) {
//			return "member/order/need_login";
//		}else {
//			redirect.addAttribute("page", "1");
//			
//			return "redirect:order_list2";
//		}
//		
//	}
//	
//	// ********************************************
//	// *        ???????????? ????????? ??????              *
//	// ********************************************
//	@RequestMapping("/order_list2")
//	public String order_list2(RedirectAttributes redirect,
//							  HttpServletRequest request, Model model,
//							  @RequestParam(value="start_date", required=false) String start_date,
//							  @RequestParam(value="end_date", required=false) String end_date) throws ParseException {
//		
//		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] order_list2");
//		
//		String uid = (String) request.getSession().getAttribute("user_id");
//		
//		int result = order_infoDao.count(uid);
//		
//		System.out.println("result:"+result);
//		
//		if(order_infoDao.count(uid)%10==0) {
//			model.addAttribute("count",(order_infoDao.count(uid)/10));
//		}else {
//			model.addAttribute("count",(order_infoDao.count(uid)/10)+1);
//		}
//		if(order_infoDao.count(uid)>=10) {
//			model.addAttribute("count2",10);
//		}else {
//			model.addAttribute("count2",order_infoDao.count(uid));
//		}
//		
//		model.addAttribute("startdate","");
//		model.addAttribute("enddate","");
//		model.addAttribute("dto3", member_InfoDao.change(uid));
//		model.addAttribute("order_status1",order_infoDao.order_status1(uid));
//		model.addAttribute("order_status2",order_infoDao.order_status2(uid));
//		model.addAttribute("order_status3",order_infoDao.order_status3(uid));
//		model.addAttribute("order_status4",order_infoDao.order_status4(uid));
//		model.addAttribute("order_status5",order_infoDao.order_status5(uid));
//		model.addAttribute("order_status6",order_infoDao.order_status6(uid));
//		model.addAttribute("order_status6",order_infoDao.order_status6(uid));
//		
//		System.out.println(uid);
//		
//		redirect.addAttribute("page", "1");
//		
//		String page = request.getParameter("page");
//		System.out.println( "page:" + page);
//		model.addAttribute("page", page);
//		
//		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
//		int num_page_size = 10; //??????????????? Row??????
//		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
//		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
//		
//		System.out.println("startRowNum:"+startRowNum);
//		System.out.println("endRowNum:"+endRowNum);
//		
//		model.addAttribute("list", order_info_dao_product_infodao.listPageDao2(String.valueOf(startRowNum), String.valueOf(endRowNum),uid));  //????????? 10???
//		System.out.println("list:"+order_info_dao_product_infodao.listPageDao2(String.valueOf(startRowNum), String.valueOf(endRowNum),uid));
//	
//		model.addAttribute("mainPage", "member/mypage/mypage_order.jsp");
//		return "index"; //list.jsp ??????????????? "list"????????? ?????????.
//		
//	}
//	
//	// ********************************************
//	// *           ????????????   - ????????????          *
//	// ********************************************
//	@RequestMapping("order_list_search")
//	public String order_list_search(@RequestParam("page") String page,
//									@RequestParam("start_date") String start_date,
//									@RequestParam("end_date") String end_date,
//									RedirectAttributes redirect,
//									Model model,
//									HttpServletRequest request) throws ParseException {
//		
//		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] order_list_search");
//		
//		String sd = start_date;
//
//		SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//
//		Date sdd = transFormat1.parse(sd);
//		
//		String ed = end_date;
//
//		SimpleDateFormat transFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//
//		Date edd = transFormat2.parse(ed);
//
//		model.addAttribute("startdate",start_date);
//		model.addAttribute("enddate",end_date);
//		
//		System.out.println( "page:" + page);
//		model.addAttribute("page", page);
//		
//		String uid = (String) request.getSession().getAttribute("user_id");
//		
//		if(order_infoDao.count(uid)%10==0) {
//			model.addAttribute("count",(order_infoDao.count_date(uid,sdd,edd)/10));
//		}else {
//			model.addAttribute("count",(order_infoDao.count_date(uid,sdd,edd)/10)+1);
//		}
//		
//		model.addAttribute("count2",order_infoDao.count_date(uid,sdd,edd));
//		model.addAttribute("dto3", member_InfoDao.change(uid));
//		model.addAttribute("order_status1",order_infoDao.order_status1(uid));
//		model.addAttribute("order_status2",order_infoDao.order_status2(uid));
//		model.addAttribute("order_status3",order_infoDao.order_status3(uid));
//		model.addAttribute("order_status4",order_infoDao.order_status4(uid));
//		model.addAttribute("order_status5",order_infoDao.order_status5(uid));
//		model.addAttribute("order_status6",order_infoDao.order_status6(uid));
//		System.out.println(uid);
//		
//		
//		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
//		int num_page_size = 10; //??????????????? Row??????
//		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
//		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
//		
//		System.out.println("startRowNum:"+startRowNum);
//		System.out.println("endRowNum:"+endRowNum);
//		
//		model.addAttribute("list", order_info_dao_product_infodao.listPageDao(String.valueOf(startRowNum), String.valueOf(endRowNum),uid,sdd,edd));  //????????? 10???
//		System.out.println("list:"+order_info_dao_product_infodao.listPageDao(String.valueOf(startRowNum), String.valueOf(endRowNum),uid,sdd,edd));
//		
//		model.addAttribute("mainPage", "member/mypage/mypage_order.jsp");
//		return "index"; //list.jsp ??????????????? "list"????????? ?????????.
//		
//	}
	
	// ********************************************
	// *            ???????????? - ??????????????????       *
	// ********************************************
	@RequestMapping("/order_detail")
	public String order_detail(@RequestParam("order_id") String order_id, Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] order_detail");
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login";
		}else {
			System.out.println(order_id);
			
			Order_infoDto oiDto = order_infoDao.get_order_info(order_id);
			
			int total_price = oiDto.getTotal_price();
			int total_shipping_cost = oiDto.getTotal_shipping_cost();
			
			int only_products_total_price = total_price - total_shipping_cost;
			
			System.out.println(order_info_dao_product_infodao.content(order_id));
			System.out.println(order_infoDao.get_order_info(order_id));
			

			model.addAttribute("list", order_info_dao_product_infodao.content(order_id));
			model.addAttribute("dto2", order_infoDao.get_order_info(order_id));
			model.addAttribute("only_products_total_price", only_products_total_price);
			
			return "member/mypage/order_popup";
		}
		
	}
				
	// ********************************************
	// *               ????????????                   *
	// ********************************************
	@RequestMapping("/product_review")
	public String product_review1( RedirectAttributes redirect, HttpServletRequest request ) throws Exception {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_review");
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login";
		}else {
			redirect.addAttribute("page", "1");
		
			return "redirect:product_review2";
		}		
	}
	
	// ********************************************
	// *         ???????????? ????????? ??????             *
	// ********************************************			
	@RequestMapping("/product_review2")
	public String product_review2(RedirectAttributes redirect,
								  HttpServletRequest request, 
								  Model model,
								  @RequestParam(value="start_date", required=false) String start_date,
								  @RequestParam(value="end_date", required=false) String end_date) throws ParseException {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_review2");
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		int result = product_inquiryDao.count(uid);
		System.out.println("result:"+result);
		
		if(member_ReviewDao.count(uid)%10==0) {
			model.addAttribute("count",(member_ReviewDao.count(uid)/10));
		}else {
			model.addAttribute("count",(member_ReviewDao.count(uid)/10)+1);
		}
		if(member_ReviewDao.count(uid)>=10) {
			model.addAttribute("count2",10);
		}else {
			model.addAttribute("count2",member_ReviewDao.count(uid));
		}
		model.addAttribute("startdate","");
		model.addAttribute("enddate","");
		model.addAttribute("dto3", member_InfoDao.change(uid));
		
		System.out.println(uid);
		redirect.addAttribute("page", "1");
		
		String page = request.getParameter("page");
		System.out.println( "page:" + page);
		model.addAttribute("page", page);
		
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 10; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		model.addAttribute("list", member_review_product_info.listPageDao2(String.valueOf(startRowNum), String.valueOf(endRowNum),uid));  //????????? 10???
		
		model.addAttribute("mainPage", "member/mypage/mypage_review.jsp");
		return "index"; //list.jsp ??????????????? "list"????????? ?????????.
		
	}
	
	// ********************************************
	// *           ???????????? - ????????????            *
	// ********************************************
	@RequestMapping("review_date_search")
	public String review_date_search(@RequestParam("page") String page,
									 RedirectAttributes redirect,
									 @RequestParam("start_date") String start_date,
									 @RequestParam("end_date") String end_date,
									 Model model,
									 HttpServletRequest request) throws ParseException {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] review_date_search");
		
		String sd = start_date;

		SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy-MM-dd");

		Date sdd = transFormat1.parse(sd);
		
		String ed = end_date;

		SimpleDateFormat transFormat2 = new SimpleDateFormat("yyyy-MM-dd");

		Date edd = transFormat2.parse(ed);

		model.addAttribute("startdate",start_date);
		model.addAttribute("enddate",end_date);
		
		System.out.println( "page:" + page);
		model.addAttribute("page", page);
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(member_ReviewDao.count(uid)%10==0) {
			model.addAttribute("count",(member_ReviewDao.count_date(uid,sdd,edd)/10));
		}else {
			model.addAttribute("count",(member_ReviewDao.count_date(uid,sdd,edd)/10)+1);
		}
		
		model.addAttribute("count2",member_ReviewDao.count_date(uid,sdd,edd));
		model.addAttribute("dto3", member_InfoDao.change(uid));
		System.out.println(uid);
		
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 10; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		model.addAttribute("list", member_review_product_info.listPageDao(String.valueOf(startRowNum), String.valueOf(endRowNum),uid,sdd,edd));  //????????? 10???
		
	    model.addAttribute("mainPage", "member/mypage/mypage_review.jsp");
        return "index";
	}
	
	// ********************************************
	// *            ???????????? - ??????????????????       *
	// ********************************************
	@RequestMapping("/product_review_popup")
	public String product_review_popup(@RequestParam("post_no") int post_no, Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_review_popup");
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login";
		}else {
			
			//model.addAttribute("dto3", dto3);
			
			model.addAttribute("dto2",member_ReviewDao.content(post_no));
			
			model.addAttribute("list",review_image_infoDao.list(post_no));
			
			for(Review_image_infoDto dto:review_image_infoDao.list(post_no)) {
				System.out.println(dto.getImage_file_name());
			}
			
			return "member/mypage/review_popup";
		}
		
	}
	
	// ********************************************
	// *            ???????????? - ???????????????         *
	// ********************************************
	@RequestMapping("/product_review_popup_edit")
	public String product_review_popup_edit(@RequestParam("post_no") int post_no, Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_review_popup_edit");
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login";
		}else {
			model.addAttribute("dto2",member_ReviewDao.content(post_no));
			model.addAttribute("list",review_image_infoDao.list(post_no));
			
			Member_reviewDto mrDto = member_ReviewDao.content(post_no);
			
			Product_infoDto piDto = product_infoDao.product_info(mrDto.getProduct_code());
			
			model.addAttribute("product_name", piDto.getProduct_name());
			
			return "member/mypage/review_popup_edit";
		}
		
	}
	
	// ********************************************
	// *      ???????????? - ???????????????-????????????      *
	// ********************************************
	@RequestMapping("/review_image_deleteAction")
	public String review_image_deleteAction(@RequestParam("post_no") int post_no,
											@RequestParam("image_no") int image_no,
											Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] review_image_deleteAction");
		
		model.addAttribute("dto2",member_ReviewDao.content(post_no));
		model.addAttribute("list",review_image_infoDao.list(post_no));
		
		review_image_infoDao.imageDelete(image_no);
		
		return "member/mypage/deleteOK_refresh";
		
	}
	
	// ********************************************
	// *            ???????????? - ???????????????         *
	// ********************************************
	@RequestMapping("/product_review_edit_action")
	public String product_review_edit_action(@RequestParam("post_no") int post_no,
											 @RequestParam("post_content") String post_content,
											 @RequestParam("review_score") String review_score,
											 HttpServletRequest request,
											 Model model,
											 @RequestParam("file1") MultipartFile file1,					
											 @RequestParam("file2") MultipartFile file2,							
											 @RequestParam("file3") MultipartFile file3,                     
											 @RequestParam("file4") MultipartFile file4	) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_review_edit_action");
		
		model.addAttribute("dto2",member_ReviewDao.content(post_no));
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		double num = Double.parseDouble((review_score));
		
		int result = review_image_info_member_review.product_review_edit_action(post_content, post_no, uid, num);
		
		ArrayList<String> fileNameList = fileUploadAPI.upload("R", file1, file2, file3, file4);

		int fileCheck1 = (int) file1.getSize();

        if(fileCheck1 != 0) {
            review_image_info_member_review.image_info(post_no, fileNameList.get(0));
        }

        int fileCheck2 = (int) file2.getSize();

        if(fileCheck2 != 0) {
            review_image_info_member_review.image_info(post_no, fileNameList.get(1));
        }

        int fileCheck3 = (int) file3.getSize();

        if(fileCheck3 != 0) {
            review_image_info_member_review.image_info(post_no, fileNameList.get(2));
        }

        int fileCheck4 = (int) file4.getSize();

        if(fileCheck4 != 0) {
            int fileinsert = review_image_info_member_review.image_info(post_no, fileNameList.get(3));
            System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] fff Result" + fileinsert);
        }

		
		System.out.println(result);
		System.out.println(post_content);
		System.out.println(uid);
		
		logMaker.logWriter("???????????? ??????", "?????? : " + Integer.toString(result), uid, post_content);
		
		if(result==1) {
			return "member/mypage/updateOK";
		}else {
			return "member/mypage/review_popup_edit";
		}
		
	}
	
	// ********************************************
	// *            ???????????? - ?????????             *
	// ********************************************
	@RequestMapping("/product_review_delete")
	public String product_review_delete(@RequestParam("post_no") int post_no) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_review_delete");
		
		int result = member_ReviewDao.delete(post_no);
		
		if (result==1) {
			return "redirect:product_review";
		}else {
			return "member/mypage/mypage_review";
		}
		
	}
	
	// ********************************************
	// *         ???????????? - ????????? ?????????         *
	// ********************************************
	@RequestMapping("/product_review_delete2")
	public String product_review_delete2(@RequestParam("post_no") int post_no) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_review_delete2");
		
		int result = member_ReviewDao.delete(post_no);
		
		if (result==1) {
			return "member/mypage/deleteOK2";
		}else {
			return "member/mypage/mypage_review";
		}
		
	}
	
	// ********************************************
	// *            ???????????? - ???????????????         *
	// ********************************************
	@RequestMapping("/product_review_popup_write")
	public String product_review_popup_write(@RequestParam("product_code") String product_code,
											 @RequestParam("order_id") String order_id,											 
											 HttpServletRequest request,
											 Model model) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_review_popup_write");
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login";
		}else {
			Product_infoDto piDto = product_infoDao.product_info(product_code);
			
			model.addAttribute("product_name", piDto.getProduct_name());
			model.addAttribute("order_id",order_id);
			model.addAttribute("product_code",product_code);

			return "member/mypage/review_popup_write";
		}
		
	}
	
	// ********************************************
	// *            ???????????? - ???????????????         *
	// ********************************************
	@RequestMapping("/product_review_write_action")
	public String product_review_write_action(@RequestParam("product_code") String product_code,
											  @RequestParam("post_content") String post_content,
											  @RequestParam("review_score") String review_score,
											  @RequestParam("order_id") String order_id,
											  @RequestParam(name = "file1", required = false) MultipartFile file1,				
											  @RequestParam(name = "file2", required = false) MultipartFile file2,	
											  @RequestParam(name = "file3", required = false) MultipartFile file3,                     
											  @RequestParam(name = "file4", required = false) MultipartFile file4,
											  Model model,
											  HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_review_write_action");
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		double num = Double.parseDouble((review_score));
		
		int result = review_image_info_member_review.writeAction(post_content, uid, product_code, num);
		
		fileUploadAPI.upload("R",file1, file2, file3, file4);
		
		Review_image_infoDto_Member_review_Dto dto= review_image_info_member_review.post_no(uid);
		
		int postno = dto.getPost_no();
		
		ArrayList<String> fileNameList = fileUploadAPI.upload("R", file1, file2, file3, file4);

		int fileCheck1 = (int) file1.getSize();

        if(fileCheck1 != 0) {
            review_image_info_member_review.image_info(postno, fileNameList.get(0));
        }
		
        int fileCheck2 = (int) file2.getSize();

        if(fileCheck2 != 0) {
            review_image_info_member_review.image_info(postno, fileNameList.get(1));
        }

        int fileCheck3 = (int) file3.getSize();

        if(fileCheck3 != 0) {
            review_image_info_member_review.image_info(postno, fileNameList.get(2));
        }

        int fileCheck4 = (int) file4.getSize();

        if(fileCheck4 != 0) {
            int fileinsert = review_image_info_member_review.image_info(postno, fileNameList.get(3));
            System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] fff Result" + fileinsert);
        }
		
        order_listDao.wrote_review_flag(product_code,order_id);
		System.out.println("postno:"+postno);
		System.out.println(result);
		System.out.println(post_content);
		System.out.println(uid);

		logMaker.logWriter("???????????? ??????", "?????? : " + Integer.toString(result), uid, post_content);
			
		if(result==1) {
			return "member/mypage/writeOK";
		}else {
			return "member/mypage/review_popup_write";
		}
		
	}
	
	// ********************************************
	// *                  ????????????                *
	// ********************************************
	@RequestMapping("/cart")
	public String cart(Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] cart");
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login";
		}else {
			ArrayList<Integer> total_price_list = new ArrayList<>(); 
			
			ArrayList<Integer> mileage_list = new ArrayList<>();
			
			ArrayList<Integer> post_price_list = new ArrayList<>();
			
			int total_price = 0;
			int finally_total_price = 0;
			int finally_total_mileage = 0;
			
			int mileage = 0;
			
			for(Shopping_cartDto_product_info_Dto_product_image_infoDto Sdto:shopping_cart_product_info_productimage_info.list(uid)) {
				
				System.out.println(Sdto.getProduct_price());
			    System.out.println(Sdto.getNumber_of_products());
			    
			    int post_price= Sdto.getShipping_cost()*Sdto.getNumber_of_products();
			    total_price = Sdto.getProduct_price()*Sdto.getNumber_of_products();
			    
			    finally_total_price+= total_price ;
			    
			    finally_total_price+=post_price;
			    
			    finally_total_mileage = (int) (finally_total_price*0.05);
			    
			    mileage = total_price / 20;
			    
			    mileage_list.add(mileage);
			    total_price_list.add(total_price);
			    post_price_list.add(post_price);
			}
			
			for(int var:total_price_list) {
				System.out.println("total_price_list:"+var);
			}
			
			for(int var:mileage_list) {
				System.out.println("mileage_list:"+var);
			}
		
			System.out.println(finally_total_price);
			
			model.addAttribute("list",shopping_cart_product_info_productimage_info.list(uid));
			model.addAttribute("total_price_list",total_price_list);
			model.addAttribute("mileage_list",mileage_list);
			model.addAttribute("finally_total_price",finally_total_price);
			model.addAttribute("finally_total_mileage",finally_total_mileage);
			model.addAttribute("post_price_list",post_price_list);
			
			model.addAttribute("mainPage", "member/order/cart.jsp");
			return "index";
			
		}
	}
	
	// ********************************************
	// *              ??????????????? ???               *
	// ********************************************
	@RequestMapping("/emptyCart")
	public String emptyCart(Model model, HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] emptyCart");
		String uid = (String) request.getSession().getAttribute("user_id");
		
		if(uid==null) {
			return "member/order/need_login";
		}else {
			model.addAttribute("mainPage", "member/order/cart_empty.jsp");
			return "index";
			
		}
		
	}
	// ********************************************
	// *            ???????????? - ??????               *
	// ********************************************
	@RequestMapping("/cart_insert_action")
	public String cart_insert_action(@RequestParam("product_code") String product_code,
									 @RequestParam("number_of_products") int number_of_products,
									 HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] cart_insert_action");
		
		String uid = (String) request.getSession().getAttribute("user_id");
		//????????? ?????? ??????????????? ???????????????
		
		if(shoppingCartDao.cart_count(product_code,uid)==1) {
			return "member/order/already";
		//???????????? ?????????
			
		}else if(uid==null){
			return "member/order/need_login";
		}else {
			int result = shoppingCartDao.cart_insert_action(uid, product_code, number_of_products);
			System.out.println(result);
			
			logMaker.logWriter("???????????? ??????", "?????? : " + Integer.toString(result), uid, product_code, Integer.toString(number_of_products));
			
			return "member/order/insertOK";
		}
		
	}
	
	// ********************************************
	// *            ???????????? - ??????               *
	// ********************************************
	@RequestMapping("/cart_delete_action")
	public String cart_delete_action(@RequestParam("product_code") List<String> product_codes,
									 HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] cart_delete_action");
		
		String uid = (String) request.getSession().getAttribute("user_id");
		
		System.out.println(product_codes);
		
		for(String product_code : product_codes) {
			shoppingCartDao.cart_delete_action(product_code,uid);
		}
		
		return "redirect:/cart";
		
	}
			
	// ********************************************
	// *             ???????????? ??????                *
	// ********************************************
	@RequestMapping("/product_list")
	public String product_list(RedirectAttributes redirect) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_list");
		
		redirect.addAttribute("page", "1");
		
		return "redirect:product_list2";
		
	}
	
	// ********************************************
	// *        ???????????? ?????? ????????? ??????         *
	// ********************************************
	@RequestMapping("/product_list2")
	public String product_list2(RedirectAttributes redirect,
								Model model,
								HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_list2");
		
		String page = request.getParameter("page");
		
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		if(product_info_product_image_info_member_review.count()%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.count()/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.count())/12+1);
		}
		
		model.addAttribute("count2",product_info_product_image_info_member_review.count());
		
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.listPageDao2(String.valueOf(startRowNum), String.valueOf(endRowNum)));
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
	
    // ********************************************
	// *             ???????????? ?????????              *
	// ********************************************
	@RequestMapping("/product_list_star")
	public String product_list_star(RedirectAttributes redirect) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_list");
		
		redirect.addAttribute("page", "1");
		
		return "redirect:product_list2_star";
		
	}
		
	// ********************************************
	// *        ???????????? ????????? ????????? ??????       *
	// ********************************************
	@RequestMapping("/product_list2_star")
	public String product_list2_star(RedirectAttributes redirect,
								Model model,
								HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_list2");
		
		String page = request.getParameter("page");
		
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		if(product_info_product_image_info_member_review.count()%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.count()/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.count())/12+1);
		}
		
		model.addAttribute("count2",product_info_product_image_info_member_review.count());
		
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.listPageDao2_star(String.valueOf(startRowNum), String.valueOf(endRowNum)));
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
	
	// ********************************************
	// *          ???????????? ??????-???????????????        *
	// ********************************************
	@RequestMapping("/product_list_low")
	public String product_list_low(RedirectAttributes redirect) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_list_low");
		
		redirect.addAttribute("page", "1");
		
		return "redirect:product_list2_low";
		
	}
	
	// ********************************************
	// *     ???????????? ??????-??????????????? ?????????      *
	// ********************************************
	@RequestMapping("/product_list2_low")
	public String product_list2_low(RedirectAttributes redirect,Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_list2_low");
		
		String page = request.getParameter("page");
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		if(product_info_product_image_info_member_review.count()%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.count()/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.count())/12+1);
		}
		
		model.addAttribute("count2",product_info_product_image_info_member_review.count());
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.listPageDao2_low(String.valueOf(startRowNum), String.valueOf(endRowNum)));
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
			
	// ********************************************
	// *          ???????????? ??????-???????????????        *
	// ********************************************
	@RequestMapping("/product_list_high")
	public String product_list_high(RedirectAttributes redirect) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_list_high");
		
		redirect.addAttribute("page", "1");
		
		return "redirect:product_list2_high";
		
	}
	
	// ********************************************
	// *     ???????????? ??????-??????????????? ?????????      *
	// ********************************************
	@RequestMapping("/product_list2_high")
	public String product_list2_high(RedirectAttributes redirect,Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] product_list2_high");
		
		String page = request.getParameter("page");
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		if(product_info_product_image_info_member_review.count()%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.count()/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.count())/12+1);
		}
		
		model.addAttribute("count2",product_info_product_image_info_member_review.count());
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.listPageDao2_high(String.valueOf(startRowNum), String.valueOf(endRowNum)));
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
			
	// ********************************************
	// *                ????????????                  *
	// ********************************************
	@RequestMapping("category")
	public String category(RedirectAttributes redirect,Model model,HttpServletRequest request,
													  @RequestParam("parent_category") String parent_category,
													  @RequestParam("child_category") String child_category) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] livingroom_couch");
		
		redirect.addAttribute("page", "1");
		redirect.addAttribute("parent_category",parent_category);
		redirect.addAttribute("child_category",child_category);

		return "redirect:category2";
		
	}
	
	// ********************************************
	// *           ????????????    -?????????            *
	// ********************************************
	@RequestMapping("category2")
	public String category2(RedirectAttributes redirect,
									Model model,
									HttpServletRequest request,
									@RequestParam("parent_category") String parent_category,
									@RequestParam("child_category") String child_category
									) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] livingroom_couch2");
		
		redirect.addAttribute("page", "1");
		model.addAttribute("parent_category",parent_category);
		model.addAttribute("child_category",child_category);
		
		String page = request.getParameter("page");
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		if(product_info_product_image_info_member_review.category_count(parent_category,child_category)%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.category_count(parent_category,child_category)/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.category_count(parent_category,child_category))/12+1);
		}
		
		model.addAttribute("count2",product_info_product_image_info_member_review.category_count(parent_category,child_category));
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.category(String.valueOf(startRowNum), String.valueOf(endRowNum),parent_category,child_category));
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
	// ********************************************
	// *                ????????????      ?????????      *
	// ********************************************
	@RequestMapping("category_star")
	public String category_star(RedirectAttributes redirect,
										Model model,
										HttpServletRequest request,
										@RequestParam("parent_category") String parent_category,
										@RequestParam("child_category") String child_category) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] livingroom_couch");
		
		redirect.addAttribute("page", "1");
		redirect.addAttribute("parent_category", parent_category);
		redirect.addAttribute("child_category", child_category);
		return "redirect:category2_star";
		
	}
	
	// ********************************************
	// *          ???????????? ????????? ?????????          *
	// ********************************************
	@RequestMapping("category2_star")
	public String category2_star(RedirectAttributes redirect,
									Model model,
									HttpServletRequest request,
									@RequestParam("parent_category") String parent_category,
									@RequestParam("child_category") String child_category) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] livingroom_couch2");
		
		redirect.addAttribute("page", "1");
		model.addAttribute("parent_category",parent_category);
		model.addAttribute("child_category",child_category);
		
		String page = request.getParameter("page");
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		if(product_info_product_image_info_member_review.category_count(parent_category,child_category)%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.category_count(parent_category,child_category)/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.category_count(parent_category,child_category))/12+1);
		}
		
		model.addAttribute("count2",product_info_product_image_info_member_review.category_count(parent_category,child_category));
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.category_star(String.valueOf(startRowNum), String.valueOf(endRowNum),parent_category,child_category));
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
		
	// ********************************************
	// *            ????????????-???????????????           *
	// ********************************************
	@RequestMapping("category_low")
	public String category_low(RedirectAttributes redirect,
									   Model model,
									   HttpServletRequest request,
									   @RequestParam("parent_category") String parent_category,
									   @RequestParam("child_category") String child_category) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] livingroom_couch_low");
		
		redirect.addAttribute("page", "1");
		redirect.addAttribute("parent_category",parent_category);
		redirect.addAttribute("child_category",child_category);
		
		return "redirect:category2_low";
		
	}
	
	// ********************************************
	// *       ???????????? ??????????????? ?????????         *
	// ********************************************
	@RequestMapping("category2_low")
	public String category2_low(RedirectAttributes redirect,
										Model model,
										HttpServletRequest request,
										@RequestParam("parent_category") String parent_category,
									    @RequestParam("child_category") String child_category
										) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] livingroom_couch2_low");
		
		redirect.addAttribute("page", "1");
		model.addAttribute("parent_category",parent_category);
		model.addAttribute("child_category",child_category);
		
		String page = request.getParameter("page");
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		if(product_info_product_image_info_member_review.category_count(parent_category,child_category)%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.category_count(parent_category,child_category)/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.category_count(parent_category,child_category))/12+1);
		}
		
		model.addAttribute("count2",product_info_product_image_info_member_review.category_count(parent_category,child_category));
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.category_low(String.valueOf(startRowNum), String.valueOf(endRowNum),parent_category,child_category));
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
			
	// ********************************************
	// *            ???????????? -???????????????          *
	// ********************************************
	@RequestMapping("category_high")
	public String livingroom_couch_high(RedirectAttributes redirect,
										Model model,
										HttpServletRequest request,
										@RequestParam("parent_category") String parent_category,
									    @RequestParam("child_category") String child_category
										) {
									
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] livingroom_couch_high");
		
		redirect.addAttribute("page", "1");
		redirect.addAttribute("parent_category",parent_category);
		redirect.addAttribute("child_category",child_category);
		
		return "redirect:category2_high";
		
	}
	
	// ********************************************
	// *       ????????????-??????????????? ?????????         *
	// ********************************************
	@RequestMapping("category2_high")
	public String category2_high(RedirectAttributes redirect,
										Model model,
										HttpServletRequest request,
										@RequestParam("parent_category") String parent_category,
										@RequestParam("child_category") String child_category
										) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] livingroom_couch2_high");
		
		redirect.addAttribute("page", "1");
		model.addAttribute("parent_category",parent_category);
		model.addAttribute("child_category",child_category);
		
		String page = request.getParameter("page");
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		if(product_info_product_image_info_member_review.category_count(parent_category,child_category)%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.category_count(parent_category,child_category)/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.category_count(parent_category,child_category))/12+1);
		}
		
		model.addAttribute("count2",product_info_product_image_info_member_review.category_count(parent_category,child_category));
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.category_high(String.valueOf(startRowNum), String.valueOf(endRowNum),parent_category,child_category));
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
	
	// ********************************************
	// *                ????????????                  *
	// ********************************************
	@RequestMapping("interior")
	public String interior(RedirectAttributes redirect,Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] interior");
		
		redirect.addAttribute("page", "1");
	
		return "redirect:interior2";
		
	}
	
	// ********************************************
	// *            ???????????? ?????????               *
	// ********************************************
	@RequestMapping("interior2")
	public String interior2(RedirectAttributes redirect,Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] interior2");
		
		redirect.addAttribute("page", "1");
		
		String page = request.getParameter("page");
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		if(product_info_product_image_info_member_review.interior_count()%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.interior_count()/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.interior_count())/12+1);
		}
		
		model.addAttribute("count2",product_info_product_image_info_member_review.interior_count());
		
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.interior(String.valueOf(startRowNum), String.valueOf(endRowNum)));
		
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
	
	// ********************************************
	// *                ????????????       ?????????     *
	// ********************************************
	@RequestMapping("interior_star")
	public String interior_star(RedirectAttributes redirect,Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] interior");
		
		redirect.addAttribute("page", "1");
	
		return "redirect:interior2_star";
		
	}
	
	// ********************************************
	// *            ???????????? ?????????     ?????????    *
	// ********************************************
	@RequestMapping("interior2_star")
	public String interior2_star(RedirectAttributes redirect,Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] interior2");
		
		redirect.addAttribute("page", "1");
		
		String page = request.getParameter("page");
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);
		
		if(product_info_product_image_info_member_review.interior_count()%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.interior_count()/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.interior_count())/12+1);
		}
		
		model.addAttribute("count2",product_info_product_image_info_member_review.interior_count());
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.interior_star(String.valueOf(startRowNum), String.valueOf(endRowNum)));
		
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
			
	// ********************************************
	// *           ???????????? ???????????????            *
	// ********************************************
	@RequestMapping("interior_low")
	public String interior_low(RedirectAttributes redirect,Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] interior_low");
		
		redirect.addAttribute("page", "1");
	
		return "redirect:interior2_low";
		
	}
	
	// ********************************************
	// *       ???????????? ??????????????? ?????????         *
	// ********************************************
	@RequestMapping("interior2_low")
	public String interior2_low(RedirectAttributes redirect,Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] interior2_low");
		
		redirect.addAttribute("page", "1");
		
		String page = request.getParameter("page");
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);

		if(product_info_product_image_info_member_review.interior_count()%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.interior_count()/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.interior_count())/12+1);
		}
		model.addAttribute("count2",product_info_product_image_info_member_review.interior_count());
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.interior_low(String.valueOf(startRowNum), String.valueOf(endRowNum)));
		
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
			
	// ********************************************
	// *            ???????????? ???????????????           *
	// ********************************************
	@RequestMapping("interior_high")
	public String interior_high(RedirectAttributes redirect,Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] interior_high");
		
		redirect.addAttribute("page", "1");
	
		return "redirect:interior2_high";
		
	}
	
	// ********************************************
	// *        ???????????? ??????????????? ?????????        *
	// ********************************************
	@RequestMapping("interior2_high")
	public String interior2_high(RedirectAttributes redirect,Model model,HttpServletRequest request) {
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [MyController_ydh] interior2_high");
		
		redirect.addAttribute("page", "1");
		
		String page = request.getParameter("page");
		int num_page_no = Integer.parseInt( page ); //page?????? 1,2,3,4
		int num_page_size = 12; //??????????????? Row??????
		int startRowNum = (num_page_no - 1) * num_page_size + 1; // 1, 6, 11 ????????? ?????? ?????????
		int endRowNum = (num_page_no * num_page_size); //5, 10, 15 ????????? ??? ?????????
		
		System.out.println("startRowNum:"+startRowNum);
		System.out.println("endRowNum:"+endRowNum);

		if(product_info_product_image_info_member_review.interior_count()%12==0) {
			model.addAttribute("count",product_info_product_image_info_member_review.interior_count()/12);
		}else {
			model.addAttribute("count",(product_info_product_image_info_member_review.interior_count())/12+1);
		}
		model.addAttribute("count2",product_info_product_image_info_member_review.interior_count());
		
		redirect.addAttribute("page", "1");
		
		model.addAttribute("list",product_info_product_image_info_member_review.interior_high(String.valueOf(startRowNum), String.valueOf(endRowNum)));
		
		
		model.addAttribute("mainPage", "common/product/product_01.jsp");
		return "index";
		
	}
			
}


