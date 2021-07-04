package com.xan.model;

import java.util.List;

public class Page {
	private int pageNo;
	private int pageSize;
	private int totalCount;
	private int totalPage;
	
	private List<Object> list;
	
	public void setPageSizeAndTotalCount(int pageSize, int totalCount) {
		this.pageSize=pageSize;
		this.totalCount=totalCount;		
		totalPage = (int)Math.ceil((double)totalCount/pageSize);
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNumber) {
		this.pageNo = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
	
	
}
