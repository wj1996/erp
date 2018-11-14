package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IDepDao;
import com.wj.erp.entity.Dep;

public class DepDaoImpl extends BaseDaoImpl<Dep> implements IDepDao{

	/**
	 * 查询公共代码抽取
	 * @param dep
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Dep dep) {
		DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		if(null != dep) {
			if(null != dep.getName() && dep.getName().trim().length() > 0) {
				dc.add(Restrictions.like("name", dep.getName(),MatchMode.ANYWHERE));
			}
			if(null != dep.getTele() && dep.getTele().trim().length() > 0) {
				dc.add(Restrictions.like("tele", dep.getTele(),MatchMode.ANYWHERE));
			}
			
			//根据id去查询最好不要这样写，利用hibernate的主键直接查询
			if(null != dep.getUuid()) {
				dc.add(Restrictions.eq("uuid", dep.getUuid()));
			}
		}
		dc.addOrder(Order.desc("uuid"));
		return dc;
	}


}
