package com.wj.erp.action;

import com.opensymphony.xwork2.ActionContext;
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
	
	private String oldPwd;
	private String newPwd;

	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	public void updatePwd() {
		Emp loginUser = super.getLoginUser();
		if(null != loginUser) {
			try {
				empBiz.updatePwd(loginUser.getUuid(), oldPwd, newPwd);
				ajaxReturn(true,"修改密码成功");
			} catch (Exception e) {
				e.printStackTrace();
				ajaxReturn(false,"修改密码失败");
			}
		}else {
			ajaxReturn(false,"请先登录!");
		}
	}
	
	public void resetPwd() {
		try {
			empBiz.resetPwd(getId(),newPwd);
			ajaxReturn(true,"重置密码成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false,"重置密码失败");
		}
	}
	
}
