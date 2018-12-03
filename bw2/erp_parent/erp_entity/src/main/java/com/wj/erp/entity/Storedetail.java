package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 仓库库存
 * @author [author]
 *
 */
public class Storedetail implements Serializable{

	private Long storeuuid; //仓库编号
	private Long num; //数量
	private Long uuid; //编号
	private Long goodsuuid; //商品编号
	
	public Long getStoreuuid() {
		return storeuuid;
	}
	public void setStoreuuid(Long storeuuid) {
		this.storeuuid = storeuuid;
	}
	
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
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
