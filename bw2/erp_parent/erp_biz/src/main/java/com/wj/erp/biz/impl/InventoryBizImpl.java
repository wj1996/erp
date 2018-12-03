package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IInventoryBiz;
import com.wj.erp.dao.interfaces.IInventoryDao;
import com.wj.erp.entity.Inventory;
/**
 * 盘盈盘亏业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class InventoryBizImpl extends BaseBizImpl<Inventory> implements IInventoryBiz{
	
	private IInventoryDao inventoryDao;
	
	public void setInventoryDao(IInventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
		super.setBaseDao(inventoryDao);
	}

	

}
