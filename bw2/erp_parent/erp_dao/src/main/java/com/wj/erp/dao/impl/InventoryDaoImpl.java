package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IInventoryDao;
import com.wj.erp.entity.Inventory;
/**
 * 盘盈盘亏数据访问层类
 * @author [author]
 *
 */
public class InventoryDaoImpl extends BaseDaoImpl<Inventory> implements IInventoryDao{

	/**
	 * 查询公共代码抽取
	 * @param inventory
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Inventory inventory,Inventory inventory2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Inventory.class);
		if(null != inventory) {
			
		}
		return dc;
	}


}
