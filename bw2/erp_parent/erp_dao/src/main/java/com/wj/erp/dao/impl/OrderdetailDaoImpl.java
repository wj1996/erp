package com.wj.erp.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IOrderdetailDao;
import com.wj.erp.entity.Orderdetail;
/**
 * 订单明细数据访问层类
 * @author [author]
 *
 */
public class OrderdetailDaoImpl extends BaseDaoImpl<Orderdetail> implements IOrderdetailDao{

	/**
	 * 查询公共代码抽取
	 * @param orderdetail
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Orderdetail orderdetail,Orderdetail orderdetail2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Orderdetail.class);
		if(null != orderdetail) {
			
			//根据订单状态查询
			if(StringUtils.isNotBlank(orderdetail.getState())) {
				dc.add(Restrictions.eq("state", orderdetail.getState()));
			}
			
			//根据订单查询
			if(null != orderdetail.getOrders() && null != orderdetail.getOrders().getUuid()) {
				dc.add(Restrictions.eq("orders", orderdetail.getOrders()));
			}
		}
		return dc;
	}


}
