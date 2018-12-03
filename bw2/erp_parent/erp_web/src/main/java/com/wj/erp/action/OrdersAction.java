package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IOrdersBiz;
import com.wj.erp.entity.Orders;
/**
 * 订单action
 * @author [author]
 *
 */
public class OrdersAction extends BaseAction<Orders> {

	private IOrdersBiz ordersBiz;

	public void setOrdersBiz(IOrdersBiz ordersBiz) {
		this.ordersBiz = ordersBiz;
		super.setBaseBiz(this.ordersBiz);
	}
}
