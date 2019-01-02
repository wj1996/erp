package com.wj.erp.biz.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.exception.ErpException;
import com.wj.erp.biz.interfaces.IEmpBiz;
import com.wj.erp.dao.interfaces.IEmpDao;
import com.wj.erp.dao.interfaces.IMenuDao;
import com.wj.erp.dao.interfaces.IRoleDao;
import com.wj.erp.entity.Emp;
import com.wj.erp.entity.Menu;
import com.wj.erp.entity.Role;
import com.wj.erp.entity.Tree;
/**
 * 员工业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class EmpBizImpl extends BaseBizImpl<Emp> implements IEmpBiz{
	
	private static int hashIteration = 2;
	
	private IEmpDao empDao;
	private IRoleDao roleDao;
	private IMenuDao menuDao;
	
	
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

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

	@Override
	public List<Tree> readEmpRoles(Long uuid) {
		List<Tree> treeList = new ArrayList<>();
		Emp emp = empDao.getById(uuid);
		List<Role> roles = emp.getRoles();
		List<Role> list = roleDao.getList(null, null, null);
		Tree t1 = null;
		for(Role role:list) {
			t1 = new Tree();
			t1.setId(String.valueOf(role.getUuid()));
			t1.setText(role.getName());
			if(roles.contains(role)) {
				t1.setChecked(true);
			}
			
			treeList.add(t1);
			
		}
		return treeList;
	}

	@Override
	public void updateEmpRole(Long uuid, String checkedStr) {
		Emp emp = empDao.getById(uuid);
		emp.setRoles(new ArrayList<>());
		String[] ids = checkedStr.split(",");
		for(String id:ids) {
			Role role = roleDao.getById(Long.valueOf(id));
			emp.getRoles().add(role);
		}
	}

	@Override
	public List<Menu> getMenusByEmpuuid(Long uuid) {
		return empDao.getMenusByEmpuuid(uuid);
	}

	@Override
	public Menu readMenusByEmpuuid(Long uuid) {
		//查询所有的菜单
		Menu root = menuDao.getById("0");
		List<Menu> list = root.getMenus();
		//根据用户编号查询所属菜单
		List<Menu> menus = empDao.getMenusByEmpuuid(uuid);
		Menu menu = cloneMenu(root);
		Menu m1 = null;
		Menu m2 = null;
		for(Menu menu1:list) {
			//复制一级菜单
			m1 = cloneMenu(menu1);
			//二级菜单
			for(Menu menu2:menu1.getMenus()) {
				if(menus.contains(menu2)) {
					m2 = cloneMenu(menu2);
					m1.getMenus().add(m2);
				}
			}
			
			if(m1.getMenus().size() > 0) {
				menu.getMenus().add(m1);
			}
			
		}
		
		
		
		return menu;
	}
	
	
	public Menu cloneMenu(Menu srcMenu) {
		Menu menu = new Menu();
		menu.setMenuid(srcMenu.getMenuid());
		menu.setIcon(srcMenu.getIcon());
		menu.setMenuname(srcMenu.getMenuname());
		menu.setUrl(srcMenu.getUrl());
		menu.setMenus(new ArrayList<>());
		return menu;
	}
	

}
