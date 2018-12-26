package com.wj.erp.dao.impl;

import org.apache.commons.lang3.StringUtils;
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
			if(StringUtils.isNotBlank(supplier.getType())) {
				dc.add(Restrictions.eq("type", supplier.getType()));
			}
			
			if(StringUtils.isNotBlank(supplier.getName())) {
				dc.add(Restrictions.like("name", supplier.getName()));
			}
		}
		
		if(null != supplier2) {
			if(StringUtils.isNotBlank(supplier2.getName())) {
				dc.add(Restrictions.eq("name",supplier2.getName()));
			}
			if(StringUtils.isNotBlank(supplier2.getType())) {
				dc.add(Restrictions.eq("type",supplier2.getType()));
			}
		}
		
		
		return dc;
	}


}
