package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IOrdersDao;
import com.wj.erp.entity.Orders;
/**
 * 订单数据访问层类
 * @author [author]
 *
 */
public class OrdersDaoImpl extends BaseDaoImpl<Orders> implements IOrdersDao{

	/**
	 * 查询公共代码抽取
	 * @param orders
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Orders orders,Orders orders2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Orders.class);
		if(null != orders) {
			
		}
		return dc;
	}


}
