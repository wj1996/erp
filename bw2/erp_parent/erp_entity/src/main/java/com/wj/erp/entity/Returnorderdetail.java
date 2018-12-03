package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 退货订单明细
 * @author [author]
 *
 */
public class Returnorderdetail implements Serializable{

	private Long uuid; //编号
	private Long goodsuuid; //商品编号
	private String goodsname; //商品名称
	private Long price; //价格
	private Long num; //数量
	private Long money; //金额
	private java.util.Date endtime; //结束日期
	private Long ender; //库管员
	private Long storeuuid; //仓库编号
	private String state; //状态
	private Long ordersuuid; //退货订单编号
	
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
	
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	
	public java.util.Date getEndtime() {
		return endtime;
	}
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	
	public Long getEnder() {
		return ender;
	}
	public void setEnder(Long ender) {
		this.ender = ender;
	}
	
	public Long getStoreuuid() {
		return storeuuid;
	}
	public void setStoreuuid(Long storeuuid) {
		this.storeuuid = storeuuid;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Long getOrdersuuid() {
		return ordersuuid;
	}
	public void setOrdersuuid(Long ordersuuid) {
		this.ordersuuid = ordersuuid;
	}
	
}
