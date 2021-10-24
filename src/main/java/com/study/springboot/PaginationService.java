package com.study.springboot;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {

	private int currentPage; 				// 현재 페이지
	private int numberOfRecordsPerPage; 	// 페이지 당 보여줄 페이지의 개수
	private int scopeOfPagination; 			// 화면 하단에 보여줄 페이지 리스트의 범위 1~10, 10~20 20~30 ...
	
	private int totalNumberOfRecords; 		// 전체 레코드의 개수
	private int numberOfCurrentPageRecords; // 현재 페이지에 있는 레코드의 개수

	private int firstPage; 					// 페이지 리스트의 첫번째 페이지 번호
	private int lastPage; 					// 페이지 리스트의 마지막 페이지 번호
	private int totalNumberOfPages; 			// 전체 페이지의 개수
	
	private int firstRecordIndex; 			// SQL의 조건절에 사용되는 첫번째 RNUM
	private int lastRecordIndex; 			// SQL의 조건절에 사용되는 마지막 RNUM
	
	private boolean hasPreviousPage; 		// 이전 페이지 존재 여부
	private boolean hasNextPage; 			// 다음 페이지 존재 여부

	public void setPagination(
			int numberOfRecordsPerPage, 
			int currentPage, 
			int totalNumberOfRecords, 
			int scopeOfPagination
		) 
	{
		this.numberOfRecordsPerPage = numberOfRecordsPerPage;
		this.currentPage = (currentPage < 1)
				? 1 : currentPage;	
		this.totalNumberOfRecords = totalNumberOfRecords;
		this.scopeOfPagination = scopeOfPagination;
		if (totalNumberOfRecords > 0) {
			calculation();
		}
	}
	
	private void calculation() {
		
		if (totalNumberOfRecords > 0) {
			
			// 전체 페이지 수 (전체 페이지가 0보다 작으면 1로 설정)
			totalNumberOfPages = (totalNumberOfRecords - 1) / numberOfRecordsPerPage + 1;
			
			// 현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장
			currentPage = (currentPage > totalNumberOfPages) 
					? totalNumberOfPages : currentPage;

			// 페이지 리스트의 첫 페이지 번호
			firstPage = ((currentPage - 1) / scopeOfPagination) * scopeOfPagination + 1;

			// 페이지 리스트의 마지막 페이지 번호 (마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장)
			lastPage = firstPage + scopeOfPagination - 1;
			lastPage = (lastPage > totalNumberOfPages)
					? totalNumberOfPages : lastPage;
			
			// 전체 페이지가 1보다 작으면 1로 설정
			totalNumberOfPages = (totalNumberOfPages < 1)? 1 : totalNumberOfPages;
			
			// SQL의 조건절에 사용되는 첫 RNUM
			firstRecordIndex = (currentPage - 1) * numberOfRecordsPerPage + 1;

			// SQL의 조건절에 사용되는 마지막 RNUM
			lastRecordIndex = currentPage * numberOfRecordsPerPage;

			// 이전 페이지 존재 여부
			hasPreviousPage = (firstPage == 1) 
					? false : true;
			if (hasPreviousPage == false) {
				hasPreviousPage = (currentPage != firstPage)
						? true : false;
			}

			// 다음 페이지 존재 여부
			hasNextPage = (lastPage * numberOfRecordsPerPage) >= totalNumberOfRecords
					? false : true;
			
			// 마지막 페이지에서 현재페이지가 마지막 페이지가 아닌경우 next처리
			if (hasNextPage == false) {
				hasNextPage = (currentPage != lastPage)
						? true : false;
			}
			
			// 현재 페이지에서 보여주는 레코드의 수 계산
			numberOfCurrentPageRecords = (totalNumberOfRecords >= lastRecordIndex)
					? numberOfRecordsPerPage 
					: totalNumberOfRecords % numberOfRecordsPerPage;
		}
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumberOfRecordsPerPage() {
		return numberOfRecordsPerPage;
	}

	public int getScopeOfPagination() {
		return scopeOfPagination;
	}

	public int getTotalNumberOfPages() {
		return totalNumberOfPages;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}

	public int getLastRecordIndex() {
		return lastRecordIndex;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public int getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}
	
	public int getNumberOfCurrentPageRecords() {
		return numberOfCurrentPageRecords;
	}
}