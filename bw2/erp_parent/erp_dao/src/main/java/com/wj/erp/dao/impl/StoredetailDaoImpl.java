package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IStoredetailDao;
import com.wj.erp.entity.Storedetail;
/**
 * 仓库库存数据访问层类
 * @author [author]
 *
 */
public class StoredetailDaoImpl extends BaseDaoImpl<Storedetail> implements IStoredetailDao{

	/**
	 * 查询公共代码抽取
	 * @param storedetail
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Storedetail storedetail,Storedetail storedetail2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Storedetail.class);
		if(null != storedetail) {
			
		}
		return dc;
	}


}
