package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 订单
 * @author [author]
 *
 */
public class Orders implements Serializable{

	private java.util.Date createtime; //生成日期
	private Long starter; //采购员
	private java.util.Date checktime; //审核日期
	private java.util.Date endtime; //入库或出库日期
	private Long ender; //库管员
	private java.util.Date starttime; //确认日期
	private Long checker; //审核员
	private Long totalmoney; //合计金额
	private String type; //1:采购 2:销售
	private Long uuid; //编号
	private Long supplieruuid; //供应商或客户
	private Long creater; //下单员
	private Long waybillsn; //运单号
	private String state; //采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库
	
	public java.util.Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	
	public Long getStarter() {
		return starter;
	}
	public void setStarter(Long starter) {
		this.starter = starter;
	}
	
	public java.util.Date getChecktime() {
		return checktime;
	}
	public void setChecktime(java.util.Date checktime) {
		this.checktime = checktime;
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
	
	public java.util.Date getStarttime() {
		return starttime;
	}
	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}
	
	public Long getChecker() {
		return checker;
	}
	public void setChecker(Long checker) {
		this.checker = checker;
	}
	
	public Long getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(Long totalmoney) {
		this.totalmoney = totalmoney;
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
	
	public Long getSupplieruuid() {
		return supplieruuid;
	}
	public void setSupplieruuid(Long supplieruuid) {
		this.supplieruuid = supplieruuid;
	}
	
	public Long getCreater() {
		return creater;
	}
	public void setCreater(Long creater) {
		this.creater = creater;
	}
	
	public Long getWaybillsn() {
		return waybillsn;
	}
	public void setWaybillsn(Long waybillsn) {
		this.waybillsn = waybillsn;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
