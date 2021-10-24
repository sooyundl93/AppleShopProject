package com.study.springboot.dto;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderInfoDto {
	
	private String order_id;			// 주문 정보 식별자
	private String user_id;				// 사용자 식별자
	private Date   order_date;			// 주문 날짜
	private String recipient_name;		// 수령인
	private String phone_number;		// 연락처
	private String postal_code;			// 우편번호
	private String address;				// 주소
	private String detailed_address; 	// 상세주소
	private String shipping_memo;		// 배송 메모
	private int    total_price;			// 총 상품 금액
	private int    total_shipping_cost;	// 총배송비용
	private int    used_mileage;		// 사용한 마일리지
	private int    discounted_price;	// 할인된 실제 결제 금액
	private int    payment_method;		// 결제 수단
	private String payment_id;			// 결제 고유 번호
	private int    accrued_mileage;		// 적립된 마일리지
	private int    order_status;		// 주문상태
	private ArrayList<OrderProductInfoDto> orderProducts;
	
	private SimpleDateFormat detailedTimeFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분"); // 연월일 시간 포맷		
	private DecimalFormat thousandsSeparator = new DecimalFormat("#,##0"); // 천단위 콤마 포맷
	
	public String getOrderId() {
		return order_id;
	}
	public void setOrderId(String orderId) {
		this.order_id = orderId;
	}
	public String getUserId() {
		return user_id;
	}
	public void setUserId(String userId) {
		this.user_id = userId;
	}
	public String getOrderDate() {
		return detailedTimeFormat.format(order_date);
	}
	public void setOrderDate(Date orderDate) {
		this.order_date = orderDate;
	}
	public String getRecipientName() {
		return recipient_name;
	}
	public void setRecipientName(String recipientName) {
		this.recipient_name = recipientName;
	}
	public String getPhoneNumber() {
		return phone_number;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phone_number = phoneNumber;
	}
	public String getPostalCode() {
		return postal_code;
	}
	public void setPostalCode(String postalCode) {
		this.postal_code = postalCode;
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
	public void setDetailedAddress(String detailedAddress) {
		this.detailed_address = detailedAddress;
	}
	public String getShippingMemo() {
		return shipping_memo;
	}
	public void setShippingMemo(String shippingMemo) {
		this.shipping_memo = shippingMemo;
	}
	public String getTotalPrice() {
		return thousandsSeparator.format(total_price);
	}
	public void setTotalPrice(int totalPrice) {
		this.total_price = totalPrice;
	}
	public String getTotalShippingCost() {
		return thousandsSeparator.format(total_shipping_cost);
	}
	public void setTotalShippingCost(int totalShippingCost) {
		this.total_shipping_cost = totalShippingCost;
	}
	public String getUsedMileage() {
		return thousandsSeparator.format(used_mileage);
	}
	public void setUsedMileage(int usedMileage) {
		this.used_mileage = usedMileage;
	}
	public String getDiscountedPrice() {
		return thousandsSeparator.format(discounted_price);
	}
	public void setDiscountedPrice(int discountedPrice) {
		this.discounted_price = discountedPrice;
	}
	public String getPaymentMethod() {
		switch(payment_method) {
			case 1: return "신용카드";
			case 2: return "카카오페이";
			default: return "무통장입금";
		}
	}
	public void setPaymentMethod(int paymentMethod) {
		this.payment_method = paymentMethod;
	}
	public String getPaymentId() {
		return payment_id;
	}
	public void setPaymentId(String paymentId) {
		this.payment_id = paymentId;
	}
	public String getAccruedMileage() {
		return thousandsSeparator.format(accrued_mileage);
	}
	public void setAccruedMileage(int accruedMileage) {
		this.accrued_mileage = accruedMileage;
	}
	public String getOrderStatus() {
		switch(order_status) {
			case 1: return "입금대기";
			case 2: return "입금완료";
			case 3: return "배송완료";
			case 4: return "취소";
			case 5: return "반품";
			case 6: return "교환";
			default: return "오류";
		}
	}
	public void setOrderStatus(int orderStatus) {
		this.order_status = orderStatus;
	}
	public ArrayList<OrderProductInfoDto> getOrderProducts() {
		return orderProducts;
	}
	public void setOrderProducts(ArrayList<OrderProductInfoDto> orderProducts) {
		this.orderProducts = orderProducts;
	}
}
