package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IRoleBiz;
import com.wj.erp.dao.interfaces.IRoleDao;
import com.wj.erp.entity.Role;
/**
 * 角色业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class RoleBizImpl extends BaseBizImpl<Role> implements IRoleBiz{
	
	private IRoleDao roleDao;
	
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
		super.setBaseDao(roleDao);
	}

	

}
