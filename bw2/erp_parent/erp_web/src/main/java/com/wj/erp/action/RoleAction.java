package com.wj.erp.action;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wj.erp.biz.interfaces.IRoleBiz;
import com.wj.erp.entity.Role;
import com.wj.erp.entity.Tree;
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
	
	/**
	 * 获取角色权限
	 */
	public void readRoleMenus() {
		List<Tree> list = roleBiz.readRoleMenus(getId());
		write(JSON.toJSONString(list));
	}
	
	
	private String checkedStr;
	
	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}

	public void updateRoleMenu() {
		try {
			roleBiz.updateRoleMenus(getId(), checkedStr);
			ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			ajaxReturn(false,"更新失败");
			e.printStackTrace();
		}
	}
}
