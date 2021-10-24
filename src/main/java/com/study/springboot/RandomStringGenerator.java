package com.study.springboot;
import java.security.SecureRandom;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class RandomStringGenerator {
	
	public String getRamdomString(int stringLength) { 
		char[] characterSet = new char[] { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
				'!', '@', '#', '$', '%', '^', '&' 
			}; 
		
		StringBuffer stringBuffer = new StringBuffer(); 
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.setSeed(new Date().getTime()); 
		
		int index = 0;
		int len = characterSet.length; 

		for (int i = 0; i < stringLength; i++) { 
			index = secureRandom.nextInt(len); 
			stringBuffer.append(characterSet[index]); 
		} 
		return stringBuffer.toString(); 
	}
}
