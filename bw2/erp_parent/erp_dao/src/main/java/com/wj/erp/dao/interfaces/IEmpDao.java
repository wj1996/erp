package com.wj.erp.dao.interfaces;

import java.util.List;

import com.wj.erp.entity.Emp;
import com.wj.erp.entity.Menu;
/**
 * 员工dao层接口
 * @author [author]
 *
 */
public interface IEmpDao extends IBaseDao<Emp>{

	
	Emp getByUsernameAndPwd(String username,String pwd);
	
	void updatePwd(Long uuid,String pwd);
	
	/**
	 * 查询用户下的菜单
	 * @param uuid
	 * @return
	 */
	List<Menu> getMenusByEmpuuid(Long uuid);
}
