package com.study.springboot.dto;

public class OrderProductInfoDto {
	private int itemNo;
	private String orderId;
	private String productCode;
	private String productName;
	private String parentCategory;
	private String childCategory;
	private String manufacturer;
	private int productPrice;
	private int shippingCost;
	private int numberOfOrders;
	private char wroteReviewFlag;
	
	public OrderProductInfoDto(String productName, int numberOfOrders) {
		this.productName = productName;
		this.numberOfOrders = numberOfOrders;
	}
	
	public OrderProductInfoDto(int itemNo, String orderId, String productCode,
			String productName, String parentCategory, String childCategory,
			String manufacturer, int productPrice, int shippingCost,
			int numberOfOrders, char wroteReviewFlag) {
		this.itemNo = itemNo;
		this.orderId = orderId;
		this.productCode = productCode;
		this.productName = productName;
		this.parentCategory = parentCategory;
		this.childCategory = childCategory;
		this.manufacturer = manufacturer;
		this.productPrice = productPrice;
		this.shippingCost = shippingCost;
		this.numberOfOrders = numberOfOrders;
		this.wroteReviewFlag = wroteReviewFlag;
	}

	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return (productName == null)? "상품 없음" : productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(String childCategory) {
		this.childCategory = childCategory;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(int shippingCost) {
		this.shippingCost = shippingCost;
	}

	public int getNumberOfOrders() {
		return numberOfOrders;
	}

	public void setNumberOfOrders(int numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}

	public char getWroteReviewFlag() {
		return wroteReviewFlag;
	}

	public void setWroteReviewFlag(char wroteReviewFlag) {
		this.wroteReviewFlag = wroteReviewFlag;
	}
}
