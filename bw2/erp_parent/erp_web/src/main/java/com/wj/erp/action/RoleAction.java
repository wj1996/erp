package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IRoleBiz;
import com.wj.erp.entity.Role;
/**
 * 角色action
 * @author [author]
 *
 */
public class RoleAction extends BaseAction<Role> {

	private IRoleBiz roleBiz;

	public void setRoleBiz(IRoleBiz roleBiz) {
		this.roleBiz = roleBiz;
		super.setBaseBiz(this.roleBiz);
	}
}
