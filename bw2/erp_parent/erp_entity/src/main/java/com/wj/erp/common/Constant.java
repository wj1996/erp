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
	
	/** 未出库 **/
	public final static String ORDERS_STATE_NOT_OUT = "0";
	/** 已出库 **/
	public final static String ORDERS_STATE_OUT = "1";
	
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
	
	//销售订单的状态
	/** 未出库 **/
	public final static String STATE_NOT_OUT = "0";
	/** 已出库 **/
	public final static String STATE_OUT = "1";
	
	
	//仓库状态
	public final static String STOREOPER_TYPE_IN = "1";
	public final static String STOREOPER_TYPE_OUT = "1";
	
	//
	public final static String TYPE_SUPPLIER = "1";
	public final static String TYPE_CUSTOMER = "2";
	
}
