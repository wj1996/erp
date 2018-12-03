package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IOrdersBiz;
import com.wj.erp.dao.interfaces.IOrdersDao;
import com.wj.erp.entity.Orders;
/**
 * 订单业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class OrdersBizImpl extends BaseBizImpl<Orders> implements IOrdersBiz{
	
	private IOrdersDao ordersDao;
	
	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
		super.setBaseDao(ordersDao);
	}

	

}
