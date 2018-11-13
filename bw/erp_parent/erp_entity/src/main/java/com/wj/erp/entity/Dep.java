package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 部门
 * @author 24253
 *
 */
public class Dep implements Serializable{

	/*
	 * 部门编号
	 */
	private Long uuid;
	/*
	 * 部门名称
	 */
	private String name;
	/*
	 * 联系人电话
	 */
	private String tele;
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	
	
	
}
