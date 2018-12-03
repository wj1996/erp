package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IDepDao;
import com.wj.erp.entity.Dep;
/**
 * 部门数据访问层类
 * @author [author]
 *
 */
public class DepDaoImpl extends BaseDaoImpl<Dep> implements IDepDao{

	/**
	 * 查询公共代码抽取
	 * @param dep
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Dep dep,Dep dep2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		if(null != dep) {
			
		}
		return dc;
	}


}
