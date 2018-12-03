package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IInventoryBiz;
import com.wj.erp.entity.Inventory;
/**
 * 盘盈盘亏action
 * @author [author]
 *
 */
public class InventoryAction extends BaseAction<Inventory> {

	private IInventoryBiz inventoryBiz;

	public void setInventoryBiz(IInventoryBiz inventoryBiz) {
		this.inventoryBiz = inventoryBiz;
		super.setBaseBiz(this.inventoryBiz);
	}
}
