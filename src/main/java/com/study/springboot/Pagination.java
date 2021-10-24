package com.study.springboot;

public class Pagination {
	private int currentPage; // 현재페이지
	private int cntPerPage; // 페이지당 출력할 페이지 갯수
	private int pageSize; // 화면 하단 페이지 사이즈 1~10, 10~20 20~30 ...
	private int totalRecordCount; // 전체 데이터 개수
	private int totalPageCount; // 전체 페이지 개수
	private int firstPage; // 페이지 리스트의 첫 페이지 번호
	private int lastPage; // 페이지 리스트의 마지막 페이지 번호
	private int firstRecordIndex; // SQL의 조건절에 사용되는 첫 RNUM
	private int lastRecordIndex; // SQL의 조건절에 사용되는 마지막 RNUM
	private boolean hasPreviousPage; // 이전 페이지 존재 여부
	private boolean hasNextPage; // 다음 페이지 존재 여부
	
	public Pagination(int currentPage, int cntPerPage) {
		// 강제입력방지
		currentPage = 
				(currentPage < 1)
				? 1 : currentPage;

//		// 10,20,30개 단위 이외 처리 방지
		cntPerPage = 
				(cntPerPage % 10 != 0)
				? 10 : cntPerPage;

//		// 하단에 보여주는 페이지를 최대 10개로 제한
		pageSize = 
				(pageSize != 10)
				? 10 : pageSize;
		
		this.currentPage = currentPage;
		this.cntPerPage = cntPerPage;
	}
	private void calculation() {

		// 전체 페이지 수 (현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장)
		totalPageCount = (totalRecordCount - 1) / cntPerPage + 1;
		currentPage = (currentPage > totalPageCount)
				? totalPageCount : currentPage;

		// 페이지 리스트의 첫 페이지 번호
		firstPage = ((this.getCurrentPage() - 1) / this.getPageSize()) 
				* this.getPageSize() + 1;

		// 페이지 리스트의 마지막 페이지 번호 (마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장)
		lastPage = firstPage + this.getPageSize() - 1;
		
		lastPage = (lastPage > totalPageCount)
				? totalPageCount : lastPage;

		// SQL의 조건절에 사용되는 첫 RNUM
		firstRecordIndex = (this.getCurrentPage() - 1) * this.getCntPerPage();

		// SQL의 조건절에 사용되는 마지막 RNUM
		lastRecordIndex = this.getCurrentPage() * this.getCntPerPage();

		// 이전 페이지 존재 여부
		hasPreviousPage = (firstPage == 1) ? false : true;
		if (hasPreviousPage == false) {
			hasPreviousPage = (currentPage != firstPage)
					? true:false;
		}

		// 다음 페이지 존재 여부
		hasNextPage = (lastPage * this.getCntPerPage()) >= totalRecordCount
				? false : true;
		// 마지막 페이지에서 현재페이지가 마지막 페이지가 아닌경우 next처리
		if (hasNextPage == false) {
			hasNextPage = (currentPage != lastPage)
					? true : false;
		}
	}

	// ########## GETTER SETTER ##########
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPageCount() {
		return (totalPageCount < 1)? 1 : totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getFirstRecordIndex() {
		return firstRecordIndex + 1;
	}

	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	public int getLastRecordIndex() {
		return lastRecordIndex;
	}

	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
		if (totalRecordCount > 0) calculation();
	}
}