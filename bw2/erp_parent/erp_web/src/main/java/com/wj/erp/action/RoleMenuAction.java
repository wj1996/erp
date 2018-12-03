package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IRoleMenuBiz;
import com.wj.erp.entity.RoleMenu;
/**
 * 角色菜单action
 * @author [author]
 *
 */
public class RoleMenuAction extends BaseAction<RoleMenu> {

	private IRoleMenuBiz roleMenuBiz;

	public void setRoleMenuBiz(IRoleMenuBiz roleMenuBiz) {
		this.roleMenuBiz = roleMenuBiz;
		super.setBaseBiz(this.roleMenuBiz);
	}
}
