package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 供应商
 * @author [author]
 *
 */
public class Supplier implements Serializable{

	private String address; //联系地址
	private String contact; //联系人
	private String name; //名称
	private String tele; //联系电话
	private String type; //1：供应商 2：客户
	private Long uuid; //编号
	private String email; //邮件地址
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
