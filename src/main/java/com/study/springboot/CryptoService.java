package com.study.springboot;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

/******************************************************/
/*                	  암호화 API               		  */
/******************************************************/
/* cryptoAPI.crypto(암호화 할 메세지 변수) 			  */
/*                                         			  */
/* 성공 : return (String)결과값 		   			  */
/* 실패 : return "fail_MD5"     - MD5 암호화 실패     */
/*        return "fail_SHA-256" - SHA-256 암호화 실패 */
/*                                         			  */
/* ex) cryptoAPI.crypto(password)          			  */
/*										   			  */
/* 작성자 : 김남권						   			  */
/******************************************************/

@Service
public class CryptoService {

	public String crypto(String msg) {
		
		String forCryptoMsg = msg;		 // 암호화 할 문자열
		String md5CompleteMsg = null;	 // MD5 암호화 된 문자열
		String cryptoCompleteMsg = null; // SHA-256 암호화 된 문자열

		/* MD5 암호화 */
		System.out.println("MD5 : " + forCryptoMsg);
		
		try {
			md5CompleteMsg = md5(forCryptoMsg);
			//System.out.println("MD5 : " + forCryptoMsg + " - " + md5(forCryptoMsg));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("MD5 암호화 실패!");
			return "fail_MD5";
		}

        
        /* SHA-256 암호화 */
        System.out.println("SHA-256 : " + md5CompleteMsg);
        
        try {
        	cryptoCompleteMsg = sha256(md5CompleteMsg);
			//System.out.println("SHA-256 : " + md5CompleteMsg + " - " + sha256(md5CompleteMsg));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("SHA-256 암호화 실패!");
			return "fail_SHA-256";
		}
        
        return cryptoCompleteMsg;

	}

	// MD5 해시 암호화
	public String md5(String msg) throws NoSuchAlgorithmException {

	    MessageDigest md = MessageDigest.getInstance("MD5");

	    md.update(msg.getBytes());

	    return byteToHexString(md.digest());

	}

	// SHA-256 해시 암호화
	public String sha256(String msg)  throws NoSuchAlgorithmException {

	    MessageDigest md = MessageDigest.getInstance("SHA-256");

	    md.update(msg.getBytes());

	    return byteToHexString(md.digest());

	}

	// Byte 배열 => HEX 문자열 변환
	public String byteToHexString(byte[] data) {

	    StringBuilder sb = new StringBuilder();

	    for(byte b : data) {
	        sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	    }

	    return sb.toString();

	}
}
