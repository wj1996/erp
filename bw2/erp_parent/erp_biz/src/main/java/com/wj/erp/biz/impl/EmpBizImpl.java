package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IEmpBiz;
import com.wj.erp.dao.interfaces.IEmpDao;
import com.wj.erp.entity.Emp;
/**
 * 员工业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class EmpBizImpl extends BaseBizImpl<Emp> implements IEmpBiz{
	
	private IEmpDao empDao;
	
	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
		super.setBaseDao(empDao);
	}

	@Override
	public Emp getByUsernameAndPwd(String username, String pwd) {
		return empDao.getByUsernameAndPwd(username, pwd);
	}

	

}
