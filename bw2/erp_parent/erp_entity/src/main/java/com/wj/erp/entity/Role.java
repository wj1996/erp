package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 角色
 * @author [author]
 *
 */
public class Role implements Serializable{

	private Long uuid; //编号
	private String name; //名称
	
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
	
}
