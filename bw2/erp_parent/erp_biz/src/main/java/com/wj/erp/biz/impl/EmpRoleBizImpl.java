package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IEmpRoleBiz;
import com.wj.erp.dao.interfaces.IEmpRoleDao;
import com.wj.erp.entity.EmpRole;
/**
 * 员工角色业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class EmpRoleBizImpl extends BaseBizImpl<EmpRole> implements IEmpRoleBiz{
	
	private IEmpRoleDao empRoleDao;
	
	public void setEmpRoleDao(IEmpRoleDao empRoleDao) {
		this.empRoleDao = empRoleDao;
		super.setBaseDao(empRoleDao);
	}

	

}
