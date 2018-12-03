package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 仓库
 * @author [author]
 *
 */
public class Store implements Serializable{

	private Long empuuid; //员工编号
	private String name; //名称
	private Long uuid; //编号
	
	public Long getEmpuuid() {
		return empuuid;
	}
	public void setEmpuuid(Long empuuid) {
		this.empuuid = empuuid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	
}
