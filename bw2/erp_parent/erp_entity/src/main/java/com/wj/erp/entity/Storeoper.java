package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 仓库操作记录
 * @author [author]
 *
 */
public class Storeoper implements Serializable{

	private Long storeuuid; //仓库编号
	private Long empuuid; //操作员工编号
	private Long num; //数量
	private java.util.Date opertime; //操作日期
	private String type; //1：入库 2：出库
	private Long uuid; //编号
	private Long goodsuuid; //商品编号
	
	public Long getStoreuuid() {
		return storeuuid;
	}
	public void setStoreuuid(Long storeuuid) {
		this.storeuuid = storeuuid;
	}
	
	public Long getEmpuuid() {
		return empuuid;
	}
	public void setEmpuuid(Long empuuid) {
		this.empuuid = empuuid;
	}
	
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	
	public java.util.Date getOpertime() {
		return opertime;
	}
	public void setOpertime(java.util.Date opertime) {
		this.opertime = opertime;
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
	
	public Long getGoodsuuid() {
		return goodsuuid;
	}
	public void setGoodsuuid(Long goodsuuid) {
		this.goodsuuid = goodsuuid;
	}
	
}
