package com.wj.erp.biz.interfaces;

import java.util.List;

import com.wj.erp.entity.Role;
import com.wj.erp.entity.Tree;

/**
 * 角色业务逻辑层
 * @author [author]
 *
 */
public interface IRoleBiz extends IBaseBiz<Role>{

	/**
	 * 获取所有菜单权限
	 * @param uuid 
	 * @return
	 */
	List<Tree> readRoleMenus(Long uuid);
	
	/**
	 * 更新角色权限
	 * @param uuid 角色编号
	 * @param checkedStr 
	 */
	public void updateRoleMenus(Long uuid,String checkedStr);
}
