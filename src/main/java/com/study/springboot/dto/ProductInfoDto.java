package com.study.springboot.dto;
//import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProductInfoDto {
	
	private String product_code;				// 상품번호
	private String product_name;				// 상품이름
	private String parent_category;				// 상위 카테고리
	private String child_category;				// 하위 카테고리
	private Date added_date;					// 추가된 날짜
	private String manufacturer;				// 제조사
	private int product_price;					// 상품가격
	private String product_description; 		// 상품설명
	private int number_of_sales;				// 판매된 수량
	private int shipping_cost;					// 배송비용
	private int number_of_stock;				// 재고 수량
	private ArrayList<String> imageFileList;	// 이미지 파일명
	private SimpleDateFormat detailedTimeFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분"); // 연월일 시간 포맷		
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일"); // 연월일 포맷
	
	public String getProductCode() {
		return product_code;
	}

	public void setProductCode(String productCode) {
		this.product_code = productCode;
	}

	public String getProductName() {
		return product_name;
	}

	public void setProductName(String productName) {
		this.product_name = productName;
	}

	public String getParentCategory() {
		return parent_category;
	}

	public void setParentCategory(String parentCategory) {
		this.parent_category = parentCategory;
	}

	public String getChildCategory() {
		return child_category;
	}

	public void setChildCategory(String childCategory) {
		this.child_category = childCategory;
	}

	public String getAddedTime() {
		return detailedTimeFormat.format(added_date);
	}
	
	public String getAddedDate() {
		return dateFormat.format(added_date);
	}

	public void setAddedDate(Date addedDate) {
		this.added_date = addedDate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getProductPrice() {
		return product_price;
	}

	public void setProductPrice(int productPrice) {
		this.product_price = productPrice;
	}

	public String getProductDescription() {
		return product_description;
	}

	public void setProductDescription(String productDescription) {
		this.product_description = productDescription;
	}

	public int getNumberOfSales() {
		return number_of_sales;
	}

	public void setNumberOfSales(int numberOfSales) {
		this.number_of_sales = numberOfSales;
	}
	
	public int getShippingCost() {
		return shipping_cost;
	}
	
	public void setShippingCost(int shipping_cost) {
		this.shipping_cost = shipping_cost;
	}	
	
	public int getNumberOfStock() {
		return number_of_stock;
	}
	
	public void setNumberOfStock(int numberOfStock) {
		this.number_of_stock = numberOfStock;
	}
	
	public ArrayList<String> getImageFileList() {
		return imageFileList;
	}
	
	public void setImageFileList(ArrayList<String> imageFileList) {
		this.imageFileList = imageFileList;
	}
	
}
