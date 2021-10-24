package com.study.springboot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

/*****************************************************************************/
/*           		        이미지 파일 업로드 API             			  	 */
/*****************************************************************************/
/* fileUploadAPI.upload(category_flag, MultipartFile 1, MultipartFile 2, ..) */
/*                                         						  			 */
/* return ArrayList<String> : 파일 개수만큼 변환된 파일명 반환				 */
/*																			 */
/* 사용 예)		[Controller 입력받은 파라미터]	 							 */
/*   @RequestParam("file1") MultipartFile file1,							 */
/*	 @RequestParam("file2") MultipartFile file2,							 */
/*	 @RequestParam("file3") MultipartFile file3,                       		 */
/*	 @RequestParam("file4") MultipartFile file4								 */
/*                                         						  			 */
/* 				[함수 호출부]												 */
/*   fileUploadAPI.upload( P/N/R, file1, file2, file3, file4);				 */
/*																			 */
/*	 	인풋이 4장까지 추가 가능하고 업로드하려는 파일은 2개 뿐인 경우도 	 */
/*	    파라미터는 전부 보내야합니다.										 */
/*																			 */
/*	category_flag : P - products / N - notice / R - review				 	 */
/*										   						  			 */
/* 		 !!!한번에 4장을 추가하는 폼이면 반드시 앞 순서부터 채울것!!!		 */
/*																			 */
/**************[파라미터 개수는 상관없으나 최대 4장까지 입니다.]**************/
/*																  			 */
/* 작성자 : 김남권						   						  			 */
/*																  			 */
/*****************************************************************************/

@Service
public class FileUploadService {
	
	private static String SAVE_PATH  = null; 
	
	public ArrayList<String> upload(String category_flag, MultipartFile... multipartFile) {
		
		ArrayList<String> fileNameList = new ArrayList<>();
		
		try {
			
			// C:\개인 프로젝트 파일 위치\TomorrowHomeProject\bin\main\static\ products  notice  review			
			// C:\개인 프로젝트 파일 위치\TomorrowHomeProject\src\main\resources\static\ products  notice  review
			
			// upload 함수 호출 시 파라미터 개수만큼 반복해서 이미지 업로드
			for(MultipartFile img : multipartFile) {
				
				// 파일 정보
				String fileName = img.getOriginalFilename();
				
				Long size = img.getSize();
				
				if(size == 0) {
					System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [FileUploadService] All img upload!");
					break;
				}
				
				// 경로 분기		
				String savepath = ResourceUtils.getFile("classpath:static/").toPath().toString();
				
				if(category_flag.equals("P")) {
					
					savepath = mkDir(savepath, "products");

				}else if(category_flag.equals("N")) {
					
					savepath = mkDir(savepath, "notice");

				}else if(category_flag.equals("R")) {
					
					savepath = mkDir(savepath, "review");

				}
				
				SAVE_PATH = savepath;
				
				String extName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				String convFileName = fileNameParsing(category_flag, extName);
				
				fileNameList.add(convFileName);
				
				System.out.println("fileName : " + fileName);
				System.out.println("convFileName : " + convFileName);
				System.out.println("size : " + size);
				writeFile(img, convFileName);
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		catch (IOException e) {
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [FileUploadService] image Write Fail");
		}
		
		System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [FileUploadService] image Write success");
				
		return fileNameList;
		
	}
	
	/**************/
	/* 파일 Write */
	/**************/
	private void writeFile(MultipartFile multipartFile, String fileName)
								throws IOException{
		
		byte[] data = multipartFile.getBytes();
		
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + fileName);
		fos.write(data);
		fos.close();
		
		return;
	}
	
	/*****************/
	/* 디렉토리 생성 */
	/*****************/
	public String mkDir(String path, String category) {
		
		String completePath = path + "\\" + category;
		
		completePath = completePath.replace("\\", "/");
		completePath = completePath.replace("/bin/main/static", "/src/main/resources/static");
		
		File parent_folder = new File(completePath);
		
		if(!parent_folder.exists()) {
			parent_folder.mkdir();
			System.out.println("  line: " + new Throwable().getStackTrace()[0].getLineNumber() + " [FileUpload_mkDir]" + category + "폴더 생성");
		}

		completePath += "/";
		
		return completePath;
		
	}
	
	/***************/
	/* 파일명 파싱 */
	/***************/
	public String fileNameParsing(String category_flag, String extName) {
		
		String fileName = "";
		
		if(category_flag.equals("P")) {
			fileName += "products_";
		}else if(category_flag.equals("N")) {
			fileName += "notice_";
		}else{
			fileName += "review_";
		}
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH) + 1;
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;
				
		return fileName;
		
	}
	
}