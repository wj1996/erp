package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 部门
 * @author [author]
 *
 */
public class Dep implements Serializable{

	private Long uuid; //编号
	private String name; //部门名称
	private String tele; //联系电话
	
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
