package com.wj.erp.dao.interfaces;

import java.util.List;

import com.wj.erp.entity.Emp;
/**
 * 员工dao层接口
 * @author [author]
 *
 */
public interface IEmpDao extends IBaseDao<Emp>{

	
	Emp getByUsernameAndPwd(String username,String pwd);
	
	void updatePwd(Long uuid,String pwd);
}
