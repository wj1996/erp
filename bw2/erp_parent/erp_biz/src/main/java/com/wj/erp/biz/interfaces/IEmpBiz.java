package com.wj.erp.biz.interfaces;

import java.util.List;

import com.wj.erp.entity.Emp;
import com.wj.erp.entity.Menu;
import com.wj.erp.entity.Tree;

/**
 * 员工业务逻辑层
 * @author [author]
 *
 */
public interface IEmpBiz extends IBaseBiz<Emp>{

	
	Emp getByUsernameAndPwd(String username,String pwd);
	void updatePwd(Long uuid,String oldPwd,String newpwd);
	
	/**
	 * 重置密码
	 * @param uuid
	 * @param newPwd
	 */
	void resetPwd(Long uuid,String newPwd);
	
	/**
	 * 更新用户角色
	 * @param uuid
	 * @return
	 */
	List<Tree> readEmpRoles(Long uuid);
	
	/**
	 * 更新用户角色
	 * @param uuid
	 * @param checkedStr
	 */
	public void updateEmpRole(Long uuid,String checkedStr);
	
	/**
	 * 查询用户下的菜单
	 * @param uuid
	 * @return
	 */
	List<Menu> getMenusByEmpuuid(Long uuid);
	
	public Menu readMenusByEmpuuid(Long uuid);
}
