package com.wj.erp.biz.interfaces;

import com.wj.erp.entity.Orders;

/**
 * 订单业务逻辑层
 * @author [author]
 *
 */
public interface IOrdersBiz extends IBaseBiz<Orders>{

	/**
	 * 审核
	 * @param uuid（订单编号）
	 * @param empUuid（员工编号）
	 */
	void doCheck(Long uuid,Long empUuid);
}
