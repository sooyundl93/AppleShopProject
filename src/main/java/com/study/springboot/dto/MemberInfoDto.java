package com.study.springboot.dto;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberInfoDto {
	
	private String 	user_id;				 	 		// ID
	private String 	user_password;		 	 			// 비밀번호
	private String 	user_name;			  				// 이름
	private String 	gender;				 	 			// 성별
	private Date 	birthday;			 	 			// 생년월일
	private String 	email;				 	 			// 이메일
	private String 	receive_email_flag;	  				// 이메일 수신여부
	private String 	phone_number;		  				// 연락처
	private String 	receive_messages_flag; 				// 문자 수신 여부
	private String 	postal_code;			  			// 우편번호
	private String 	address;				 	 		// 주소
	private String 	detailed_address;	  				// 상세주소
	private String 	pw_question;			  			// 비밀번호 질문
	private String 	pw_answer;			 	 			// 비밀번호 질문 답
	private Date 	member_since;		  				// 가입일
	private String 	user_role;		  	  				// 관리자 계정 여부
	private int 	mileage;				  			// 보유한 마일리지
	private int		number_of_visits;	  				// 일일 방문 횟수
	private Integer total_order_price;	  				// 총 주문 금액
	private int 	number_of_products_orderd;			// 상품 주문 건수
	private int 	numberOfMemberReviews;				// 회원의 리뷰 건수
	private Float 	averageOfReviewScores;				// 회원의 평균 리뷰점수
	private int[] 	shippingStatus = new int[6];		// 회원의 주문 상태 건수 ([1]: 입금대기, [2]: 입금완료(배송 대기 중), [3]: 배송완료, [4]: 취소, [5]: 반품, [6]: 교환)
	private int 	numberOfProductInquiries;			// 회원의 상품문의글 건수
	private int 	numberOfOne2oneInquiries;			// 회원의 1:1문의글 건수
	private int 	numberOfUnrepliedProductInquiries;	// 답변받지 못한 상품문의 글 건수
	private int 	numberOfUnrepliedOne2oneInquiries;	// 답변받지 못한 1:1문의 글 건수
	
	private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy"); // 연도 포맷
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일"); // 연월일 포맷
	private SimpleDateFormat detailedTimeFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분"); // 연월일 시간 포맷		
	private DecimalFormat thousandsSeparator = new DecimalFormat("#,##0"); // 천단위 콤마 포맷
	private String[] phoneNumberFormat = new String[3];	// 전화번호 포맷
	
	public String getUserId() {
		return user_id;
	}
	
	public void setUserId(String userId) {
		this.user_id = userId;
	}
	
	public String getUserPassword() {
		return user_password;
	}

	public void setUserPassword(String userPassword) {
		this.user_password = userPassword;
	}

	public String getUserName() {
		return user_name;
	}

	public void setUserName(String userName) {
		this.user_name = userName;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return dateFormat.format(birthday);
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		int yearOfBirth = Integer.parseInt(yearFormat.format(birthday));
		int thisYear = Integer.parseInt(yearFormat.format(new Date()));
		return thisYear - yearOfBirth + 1;
	}	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReceiveEmailFlag() {
		return receive_email_flag;
	}

	public void setReceiveEmailFlag(String receive_email_flag) {
		this.receive_email_flag = receive_email_flag;
	}

	public String getPhoneNumber() {
		phoneNumberFormat[0] = phone_number.substring(0, 3);
		phoneNumberFormat[1] = phone_number.substring(3, 7);
		phoneNumberFormat[2] = phone_number.substring(7, 11);
		
		return phoneNumberFormat[0] + "-" + phoneNumberFormat[1] + "-" + phoneNumberFormat[2];
	}

	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getReceiveMessagesFlag() {
		return receive_messages_flag;
	}

	public void setReceiveMessagesFlag(String receive_messages_flag) {
		this.receive_messages_flag = receive_messages_flag;
	}

	public String getPostalCode() {
		return postal_code;
	}

	public void setPostalCode(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailedAddress() {
		return detailed_address;
	}

	public void setDetailedAddress(String detailed_address) {
		this.detailed_address = detailed_address;
	}

	public String getPwQuestion() {
		if(pw_question == null) 
			return "질문 선택 없음";
		
		switch(pw_question) {
			case "1": return "내 보물 1호는?";
			case "2": return "내가 가장 좋아하는 영화 제목은?";
			case "3": return "첫 애완동물 이름은?";
			case "4": return "내가 가장 존경하는 인물은?";
			case "5": return "가장 기억에 남는 선생님 성함은?";
			case "6": return "내가 졸업한 초등학교 이름은?";
			case "7": return "내가 졸업한 고등학교 이름은?";
			case "8": return "내가 처음 사용한 핸드폰 번호는?";
			case "9": return "아버지 성함은?";
			case "10": return "어머니 성함은?";
			default: return "질문 선택 없음";
		}
	}

	public void setPwQuestion(String pw_question) {
		this.pw_question = pw_question;
	}

	public String getPwAnswer() {
		return (pw_answer == null) ? "없음" : pw_answer;
	}

	public void setPwAnswer(String pw_answer) {
		this.pw_answer = pw_answer;
	}

	public String getMemberSince() {		
		return detailedTimeFormat.format(member_since);
	}

	public void setMemberSince(Date member_since) {
		this.member_since = member_since;
	}

	public String getUserRole() {
		return user_role;
	}

	public void setUserRole(String user_role) {
		this.user_role = user_role;
	}

	public String getMileage() {
		return thousandsSeparator.format(mileage);
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getNumberOfVisits() {
		return thousandsSeparator.format(number_of_visits);
	}

	public void setNumberOfVisits(int number_of_visits) {
		this.number_of_visits = number_of_visits;
	}
	
	public String getTotalOrderPrice() {	
		return thousandsSeparator.format(total_order_price);
	}

	public void setTotalOrderPrice(Integer total_order_price) {	
		this.total_order_price = (total_order_price == null)
				? 0 : total_order_price;
	}
	
	public int getNumberOfProductsOrderd() {
		return number_of_products_orderd;
	}

	public void setNumberOfProductsOrderd(int number_of_products_orderd) {
		this.number_of_products_orderd = number_of_products_orderd;
	}
	
	public int getNumberOfMemberReviews() {
		return numberOfMemberReviews;
	}

	public void setNumberOfMemberReviews(int numberOfMemberReviews) {
		this.numberOfMemberReviews = numberOfMemberReviews;
	}
	
	public Float getAverageOfReviewScores() {
		return (averageOfReviewScores == null)? 0 : (float)Math.round(averageOfReviewScores * 10) / 10;
	}

	public void setAverageOfReviewScores(Float averageOfReviewScores) {
		this.averageOfReviewScores = averageOfReviewScores;
	}
	
	public int[] getShippingStatus() {
		return shippingStatus;
	}
	
	public void setShippingStatus(int index, int statusValue) {
		this.shippingStatus[index] = statusValue;
	}
	
	public int getNumberOfProductInquiries() {
		return numberOfProductInquiries;
	}

	public void setNumberOfProductInquiries(int numberOfProductInquiries) {
		this.numberOfProductInquiries = numberOfProductInquiries;
	}

	public int getNumberOfOne2oneInquiries() {
		return numberOfOne2oneInquiries;
	}

	public void setNumberOfOne2oneInquiries(int numberOfOne2oneInquiries) {
		this.numberOfOne2oneInquiries = numberOfOne2oneInquiries;
	}
	
	public int getNumberOfUnrepliedProductInquiries() {
		return numberOfUnrepliedProductInquiries;
	}

	public void setNumberOfUnrepliedProductInquiries(
			int numberOfUnrepliedProductInquiries) {
		this.numberOfUnrepliedProductInquiries = numberOfUnrepliedProductInquiries;
	}

	public int getNumberOfUnrepliedOne2oneInquiries() {
		return numberOfUnrepliedOne2oneInquiries;
	}

	public void setNumberOfUnrepliedOne2oneInquiries(
			int numberOfUnrepliedOne2oneInquiries) {
		this.numberOfUnrepliedOne2oneInquiries = numberOfUnrepliedOne2oneInquiries;
	}
}
