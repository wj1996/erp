package com.wj.erp.biz.impl;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IOrdersBiz;
import com.wj.erp.common.Constant;
import com.wj.erp.dao.interfaces.IOrdersDao;
import com.wj.erp.entity.Orderdetail;
import com.wj.erp.entity.Orders;

/**
 * 订单业务逻辑层
 * 
 * @author [author]
 *
 */
@Transactional
public class OrdersBizImpl extends BaseBizImpl<Orders> implements IOrdersBiz {

	private IOrdersDao ordersDao;

	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
		super.setBaseDao(ordersDao);
	}

	@Override
	public void add(Orders orders) {
		orders.setState(Constant.STATE_CREATE);
		orders.setType(Constant.TYPE_IN);
		orders.setCreatetime(new Date());
		
		double total = 0;
		for(Orderdetail detail:orders.getOrderDetails()) {
			total += detail.getMoney();
			//明细的状态
			detail.setState(Constant.STATE_NOT_IN);
			//设置跟订单的关系
			detail.setOrders(orders);
		}
		//设置订单总金额
		orders.setTotalmoney(total);
		ordersDao.add(orders);
	}

}
