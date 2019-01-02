package com.wj.erp.action;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.wj.erp.biz.interfaces.IEmpBiz;
import com.wj.erp.entity.Emp;
import com.wj.erp.entity.Menu;
import com.wj.erp.entity.Tree;
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
	
	
	
	public void readEmpRoles() {
		List<Tree> list = empBiz.readEmpRoles(getId());
		write(JSON.toJSONString(list));
	}
	
	private String checkedStr;
	
	
	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}
	public void updateEmpRole() {
		try {
			empBiz.updateEmpRole(getId(), checkedStr);
			ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			ajaxReturn(false, "更新失败");
			e.printStackTrace();
		}
	}
	
	
	public void getMenusByEmpuuid(){
		if(null != getLoginUser()) {
			List<Menu> list = empBiz.getMenusByEmpuuid(getLoginUser().getUuid());
			write(JSON.toJSONString(list));
		}
		
	}
	
	public void readMenusByEmpuuid() {
		if(null != getLoginUser()) {
			Menu menu = empBiz.readMenusByEmpuuid(getLoginUser().getUuid());
			write(JSON.toJSONString(menu));
		}
	}
	
}
