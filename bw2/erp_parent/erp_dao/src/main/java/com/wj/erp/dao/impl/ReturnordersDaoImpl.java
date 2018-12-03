package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IReturnordersDao;
import com.wj.erp.entity.Returnorders;
/**
 * 退货订单数据访问层类
 * @author [author]
 *
 */
public class ReturnordersDaoImpl extends BaseDaoImpl<Returnorders> implements IReturnordersDao{

	/**
	 * 查询公共代码抽取
	 * @param returnorders
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Returnorders returnorders,Returnorders returnorders2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Returnorders.class);
		if(null != returnorders) {
			
		}
		return dc;
	}


}
