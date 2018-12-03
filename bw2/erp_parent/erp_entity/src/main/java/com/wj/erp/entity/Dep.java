package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 部门
 * @author [author]
 *
 */
public class Dep implements Serializable{

	private String name; //部门名称
	private String tele; //联系电话
	private Long uuid; //编号
	
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
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	
}
