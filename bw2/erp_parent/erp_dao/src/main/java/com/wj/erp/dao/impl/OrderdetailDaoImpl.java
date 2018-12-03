package com.wj.erp.dao.impl;

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
			
		}
		return dc;
	}


}
