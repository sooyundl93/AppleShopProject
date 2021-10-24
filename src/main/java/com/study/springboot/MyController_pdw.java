package com.study.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**********************************************
*				 내일의 집  				  *
***********************************************
*	MyController_pdw.java					  *
*											  *
*	작업자 : 박대우		 					  *
*   										  *
*   JSP에서 요청한 URI 분기 처리			  *
**********************************************/
@Controller
public class MyController_pdw {

	/**************/
	/* Dao 초기화 */
	/**************/
	
	
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

	
}







