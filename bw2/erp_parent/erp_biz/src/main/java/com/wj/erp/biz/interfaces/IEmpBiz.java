package com.wj.erp.biz.interfaces;

import com.wj.erp.entity.Emp;

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
}
