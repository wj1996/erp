package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.ISupplierDao;
import com.wj.erp.entity.Supplier;
/**
 * 供应商数据访问层类
 * @author [author]
 *
 */
public class SupplierDaoImpl extends BaseDaoImpl<Supplier> implements ISupplierDao{

	/**
	 * 查询公共代码抽取
	 * @param supplier
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Supplier supplier,Supplier supplier2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Supplier.class);
		if(null != supplier) {
			
		}
		return dc;
	}


}
