package com.wj.erp.biz.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.exception.ErpException;
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
	
	private static int hashIteration = 2;
	
	private IEmpDao empDao;
	
	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
		super.setBaseDao(empDao);
	}

	@Override
	public Emp getByUsernameAndPwd(String username, String pwd) {
		return empDao.getByUsernameAndPwd(username, encrypt(pwd,username));
	}

	@Override
	public void updatePwd(Long uuid, String oldPwd,String newPwd) {
		Emp emp = empDao.getById(uuid);
		String encrypt = encrypt(oldPwd,emp.getUsername());
		if(null != emp.getPwd() && emp.getPwd().equals(encrypt)) {
			
		}else {
			//自定义异常
			throw new ErpException("旧密码不正确");
		}
		
		empDao.updatePwd(uuid, encrypt(newPwd,emp.getUsername()));
	}
	
	@Override
		public void add(Emp t) {
//			t.setPwd(encrypt(t.getPwd(), t.getUsername()));
			//设置初始密码
			t.setPwd(encrypt(t.getUsername(),t.getUsername()));
			super.add(t);
		}

	private String encrypt(String source,String salt) {
		//2：散列次数
		Md5Hash md5 = new Md5Hash(source,salt,hashIteration);
		System.out.println(md5.toString());
		return md5.toString();
	}

	@Override
	public void resetPwd(Long uuid, String newPwd) {
		Emp emp = empDao.getById(uuid);
		empDao.updatePwd(uuid, encrypt(newPwd,emp.getUsername()));
	}
	

}
