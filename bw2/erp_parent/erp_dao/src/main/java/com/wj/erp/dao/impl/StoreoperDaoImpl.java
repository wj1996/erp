package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IStoreoperDao;
import com.wj.erp.entity.Storeoper;
/**
 * 仓库操作记录数据访问层类
 * @author [author]
 *
 */
public class StoreoperDaoImpl extends BaseDaoImpl<Storeoper> implements IStoreoperDao{

	/**
	 * 查询公共代码抽取
	 * @param storeoper
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Storeoper storeoper,Storeoper storeoper2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Storeoper.class);
		if(null != storeoper) {
			
		}
		return dc;
	}


}
