package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IStoreDao;
import com.wj.erp.entity.Store;
/**
 * 仓库数据访问层类
 * @author [author]
 *
 */
public class StoreDaoImpl extends BaseDaoImpl<Store> implements IStoreDao{

	/**
	 * 查询公共代码抽取
	 * @param store
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Store store,Store store2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Store.class);
		if(null != store) {
			
			//根据员工编号查询
			if(null != store.getEmpuuid()) {
				dc.add(Restrictions.eq("empuuid", store.getUuid()));
			}
		}
		return dc;
	}


}
