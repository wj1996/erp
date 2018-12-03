package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IRoleMenuBiz;
import com.wj.erp.dao.interfaces.IRoleMenuDao;
import com.wj.erp.entity.RoleMenu;
/**
 * 角色菜单业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class RoleMenuBizImpl extends BaseBizImpl<RoleMenu> implements IRoleMenuBiz{
	
	private IRoleMenuDao roleMenuDao;
	
	public void setRoleMenuDao(IRoleMenuDao roleMenuDao) {
		this.roleMenuDao = roleMenuDao;
		super.setBaseDao(roleMenuDao);
	}

	

}
