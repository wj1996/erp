package com.wj.erp.entity;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable{
	
	private Integer page;
	private Integer rows;
	private List<T> list;
	//总共多少页
	private Integer totalPage;
	//总条数
	private Integer totalSize;
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	
	

}
