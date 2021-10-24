package com.study.springboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.study.springboot.dto.Order_infoDto;

/*****************************************************************************/
/*           		       카카오페이 연동 결제 API             			 */
/*****************************************************************************/
/* kakaoPayAPI.kakaoPay(주문번호, 주문자ID, 상품명, 구매수량,	  			 */
/*						실 결제금액, 부가세(실 결제금액/10), tid 			 */
/*																			 */
/*	결제 요청 => 카카오페이 url 실행(결제 수행) => 결제 승인 요청			 */
/*																			 */
/*                      *tid : 카카오페이 결제 트랜잭션 후 반환값      		 */
/*								결제요청시엔 null값으로 주면 됩니다.         */
/* 										   						  			 */
/* return url        : 결제 요청 성공 => 카카오페이 url          			 */
/* return success    : 결제 승인 성공 => 완료                 				 */
/* return fail       : 해당 트랜잭션 Error									 */
/*										   						  			 */
/* 작성자 : 김남권						   						  			 */
/*****************************************************************************/

@Service
public class KakaopayService {
	
	// 결제 준비 요청
	public String kakaoPay(String partner_order_id, String partner_user_id, String item_name, 
				  		   int iQuantity, int iTotal_amount, int iTax_free_amount, Order_infoDto oiDto) {
		
		String quantity = Integer.toString(iQuantity);
		String total_amount = Integer.toString(iTotal_amount);
		String tax_free_amount = Integer.toString(iTax_free_amount);
		
		String url = null;
		
		/* 결제 준비 */
		url = kakaoPayReady(partner_order_id, partner_user_id, item_name, quantity, total_amount, tax_free_amount, oiDto);
						
		return url;
		
	}
	
	// 결제 승인 요청
	public String kakaoPay(String tid, String partner_order_id, String partner_user_id, String pg_token) {
		
		/* 결제 승인 */
		String result = kakaoPaySuccess(tid, partner_order_id, partner_user_id, pg_token);
		
		return result;
		
	}
	
	/*******************/
	/* 결제 요청(준비) */
	/*******************/
	public String kakaoPayReady(String partner_order_id, String partner_user_id, String item_name, 
								String quantity, String total_amount, String tax_free_amount, Order_infoDto oiDto) {

		URL url = null;
		
		/* URL 통신 환경 설정 */
		try {
			url = new URL("https://kapi.kakao.com/v1/payment/ready");
		} catch (MalformedURLException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] url define Error");
			
			return "fail";
		}
		
		HttpURLConnection conn;
		
		try {
			conn = (HttpURLConnection)url.openConnection();
		} catch (IOException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] url open Connection Error");

			return "fail";
		}
		
		try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] setReqMethod Error");

			return "fail";
		}
		conn.setRequestProperty("Authorization", "KakaoAK 965dc406135f0cc134807f18823dd898");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		/* 데이터 세팅 */
		Map<String, String> params = new HashMap<String, String>();
		params.put("cid", "TC0ONETIME");
		params.put("partner_order_id", partner_order_id);
		params.put("partner_user_id", partner_user_id);
		params.put("item_name", item_name);
		params.put("item_code", "");
		params.put("quantity", quantity);
		params.put("total_amount", total_amount);
		params.put("tax_free_amount", tax_free_amount);
		params.put("approval_url", "http://localhost:8090/kakaopayConfirmAction?order_id="+partner_order_id+"&");
		params.put("cancel_url", "http://localhost:8090/kakaopayCancel.jsp");
		params.put("fail_url", "http://localhost:8090/kakaopayFail.jsp");
		
		String string_params = new String();
		
		for(Map.Entry<String, String> elem : params.entrySet()) {
			string_params += (elem.getKey() + "=" + elem.getValue() + "&");
		}
		
		try {
			conn.getOutputStream().write(string_params.getBytes());
		} catch (IOException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] write Error");

			return "fail";
		}
		
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (IOException e1) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] br Error");

			return "fail";
		}
		
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject)parser.parse(br);
		} catch (IOException | ParseException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] parser Error");

			return "fail";
		}
		
		String successUrl = (String)obj.get("next_redirect_pc_url");
		String tid = (String)obj.get("tid");
		System.out.println("[kakaoPay] : successUrl - " + successUrl);
		System.out.println("[kakaoPay] : tid - " + tid);
				
		oiDto.setPayment_id(tid);
		
		return successUrl;
		
	}
	
	/*************/
	/* 결제 완료 */
	/*************/
	public String kakaoPaySuccess(String tid, String partner_order_id, String partner_user_id, String pg_token) {
		
		URL url = null;
		
		/* URL 통신 환경 설정 */
		try {
			url = new URL("https://kapi.kakao.com/v1/payment/approve");
		} catch (MalformedURLException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] url define Error");
			return "fail";
		}
		
		HttpURLConnection conn;
		
		try {
			conn = (HttpURLConnection)url.openConnection();
		} catch (IOException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] url open Connection Error");
			return "fail";
		}
		
		try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] setReqMethod Error");
			return "fail";
		}
		conn.setRequestProperty("Authorization", "KakaoAK 965dc406135f0cc134807f18823dd898");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		/* 데이터 세팅 */
		Map<String, String> params = new HashMap<String, String>();
		params.put("cid", "TC0ONETIME");
		params.put("tid", tid);
		params.put("partner_order_id", partner_order_id);
		params.put("partner_user_id", partner_user_id);
		params.put("pg_token", pg_token);
				
		String string_params = new String();
		
		for(Map.Entry<String, String> elem : params.entrySet()) {
			string_params += (elem.getKey() + "=" + elem.getValue() + "&");
		}
		
		try {
			conn.getOutputStream().write(string_params.getBytes());
		} catch (IOException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] write Error");
			return "fail";
		}

		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (IOException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] br Error");
			e.printStackTrace();
			return "fail";
		}
		
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject)parser.parse(br);
		} catch (IOException | ParseException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [kakaoPay] parser Error");
			return "fail";
		}
		
		String created_at = (String)obj.get("created_at");
		String approved_at = (String)obj.get("approved_at");
		System.out.println("[kakaoPay] : created_at - " + created_at);
		System.out.println("[kakaoPay] : approved_at - " + approved_at);
		
		if(created_at != null && approved_at != null) {
			return "success";
		}else {
			return "fail";
		}
		
	}
	
}
