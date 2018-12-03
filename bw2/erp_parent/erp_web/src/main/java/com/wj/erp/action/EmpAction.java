package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IEmpBiz;
import com.wj.erp.entity.Emp;
/**
 * 员工action
 * @author [author]
 *
 */
public class EmpAction extends BaseAction<Emp> {

	private IEmpBiz empBiz;

	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
		super.setBaseBiz(this.empBiz);
	}
}
