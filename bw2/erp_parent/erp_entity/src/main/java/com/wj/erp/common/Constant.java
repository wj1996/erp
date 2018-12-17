package com.wj.erp.common;

public class Constant {

	
	//订单状态
	
	/** 未审核 **/
	public final static String STATE_CREATE = "0";
	/** 已审核 **/
	public final static String STATE_CHECK = "1";
	/** 已确认 **/
	public final static String STATE_START = "2";
	/** 已入库 **/
	public final static String STATE_END = "3";
	
	//订单类型
	/** 采购订单 **/
	public final static String TYPE_IN = "1";
	/** 销售订单 **/
	public final static String TYPE_OUT = "2";
	
	//明细的状态
	/** 未入库 **/
	public final static String STATE_NOT_IN = "0";
	/** 已入库 **/
	public final static String STATE_IN = "1";
	
}
