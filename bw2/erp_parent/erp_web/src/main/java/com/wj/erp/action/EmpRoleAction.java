package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IEmpRoleBiz;
import com.wj.erp.entity.EmpRole;
/**
 * 员工角色action
 * @author [author]
 *
 */
public class EmpRoleAction extends BaseAction<EmpRole> {

	private IEmpRoleBiz empRoleBiz;

	public void setEmpRoleBiz(IEmpRoleBiz empRoleBiz) {
		this.empRoleBiz = empRoleBiz;
		super.setBaseBiz(this.empRoleBiz);
	}
}
