package com.study.springboot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

/*****************************************************************************/
/*           		          파일연동 로그 API             			  	 */
/*****************************************************************************/
/* logMaker.logWriter(변수1, 변수2, ...);  						  			 */
/*                                         						  			 */
/* 	로그 파일 경로	-	Windows - c:\\TmrLog\\로그 파일				  		 */
/*  				-	macOS   - /Users/daewoo/Documents/TmrLog/로그 파일	 */
/* 										   						  			 */
/* return 없음			                   						  			 */
/*                                         						  			 */
/* ex) int price   = 39900;				   						  			 */
/*     float score = 4.5				   						  			 */
/*	   String name = "네이비 쇼파"		   						  			 */
/*                                         						  			 */
/* logMaker.logWriter(Integer.toString(price), Float.toString(score), name); */
/*										   						  			 */
/* 넣을 데이터가 없으면 => logMaker.logWriter();				  			 */
/*										   						  			 */
/********[파라미터 개수는 상관없으나 꼭 String타입으로 변환해주세요.]*********/
/*																  			 */
/* 작성자 : 김남권						   						  			 */
/*****************************************************************************/

@Service
public class LogMaker {
	
	public void logWriter(String... param) {
		
		String user_ip      = null; // 사용자 ip 주소
		String user_mac     = null; // 사용자 mac 주소
		String calledMethod = null; // logWriter를 호출한 메소드 정보
		String dateYmd      = null; // 날짜 - yyyyMMdd
		String dateYmdHms   = null; // 날짜 - yyyy-MM-dd HH:mm:ss
		String logFileName  = null; // 로그 파일 이름
		String logContent   = null; // 실 로그 텍스트
		String filePath 	= null; // 로그 파일 경로
		
		user_ip = getLocalIpAddress();		
		user_mac = getLocalMacAddress();
		calledMethod = getCalledMethod();
		dateYmd = getOnlyDate();
		dateYmdHms = getDateAndTime();
		
		
		/* 추출 실패 시 "param[null]" 초기화   */
		/* => 추출에 실패해도 로그에 기록한다. */
		// ip get
		if(user_ip.equals("fail")) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [LogMaker] getLocalIpAddress Error");
			user_ip = "user_ip[null]";
		}
		
		
		// mac get
		if(user_mac.equals("fail")) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [LogMaker] getLocalMacAddress Error");
			user_mac = "user_mac[null]";
		}
		
		
		// 1차 조합(API에서 추출한 정보 추가)
		logContent = "TMR_SYSTEM_LOG : " + dateYmdHms + " " + calledMethod + " " + user_ip + " " + user_mac + " ";
		
		// 2차 조합(호출시 파라미터로 넣은 값 추가)
		for(String data : param) {
			logContent += data + " ";
		}
		
		logContent += "\n";
				
		logFileName = "tmr_" + dateYmd + "_log.txt";
		
		// OS 확인
		String osName = System.getProperty("os.name");
		
		
		// 디렉토리 생성
		if(osName.contains("Windows")) {
			filePath = win_mkDir();
		}else {
			filePath = mac_mkDir();
		}
		
		// 파일 생성
		try {
			FileWriter fileWriter = new FileWriter(filePath + logFileName, true);
			fileWriter.write(logContent);
			
			// 파일 작업이 끝나면 반드시 close(); !!
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [LogMaker] FileWriter Error");
			e.printStackTrace();
		}
		
	}
	
	/******************/
	/* IPv4 주소 추출 */
	/******************/
	public String getLocalIpAddress() {
		String ipAddress = null;
		
		try {
			ipAddress = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "fail";
		}
		
		return ipAddress;
		
	}
	
	/*****************/
	/* MAC 주소 추출 */
	/*****************/
	public String getLocalMacAddress() {
		String result = "";
		InetAddress ip;
		
		try {
			
			ip = InetAddress.getLocalHost();
			   
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			
			byte[] mac = network.getHardwareAddress();
			   
			StringBuilder sb = new StringBuilder();
		
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			
			result = sb.toString();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "fail";
		} catch (SocketException e){
			e.printStackTrace();
			return "fail";
		}
			    
		return result;
	}

	/**************************************/
	/* LogMaker를 호출한 메소드 정보 추출 */
	/**************************************/
	public String getCalledMethod() {
		
		String calledMethod = null;
		
		StackTraceElement[] ste = new Throwable().getStackTrace();
				
		calledMethod = ste[2].toString();
		
		return calledMethod;
		
	}
	
	/**********************/
	/* 현재 날짜 yyyyMMdd */
	/**********************/
	public String getOnlyDate() {
		
		String day = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date time = new Date();
		
		day = sdf.format(time);
		
		return day;
		
	}
	
	/*********************************/
	/* 현재 날짜 yyyy-MM-dd HH:mm:ss */
	/*********************************/
	public String getDateAndTime() {
		
		String day = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		
		day = sdf.format(time);
		
		return day;
		
	}
	
	/******************************/
	/* Windows 파일 디렉토리 생성 */
	/******************************/
	public String win_mkDir() {
		
		String win_path = "C:\\TmrLog";
		
		File parent_folder = new File(win_path);
		
		if(!parent_folder.exists()) {
			parent_folder.mkdir();
		}
		
		win_path += "\\";
		
		return win_path;
		
	}
	
	/****************************/
	/* macOS 파일 디렉토리 생성 */
	/****************************/
	public String mac_mkDir() {
		
		String mac_path = "/Users/daewoo/Documents/TmrLog";
		
		File parent_folder = new File(mac_path);
		
		if(!parent_folder.exists()) {
			parent_folder.mkdir();
		}
		
		mac_path += "/";
		
		return mac_path;
		
	}
	
}