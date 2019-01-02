package com.wj.erp.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IRoleBiz;
import com.wj.erp.dao.interfaces.IMenuDao;
import com.wj.erp.dao.interfaces.IRoleDao;
import com.wj.erp.entity.Menu;
import com.wj.erp.entity.Role;
import com.wj.erp.entity.Tree;
/**
 * 角色业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class RoleBizImpl extends BaseBizImpl<Role> implements IRoleBiz{
	
	private IRoleDao roleDao;
	private IMenuDao menuDao;
	
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
		super.setBaseDao(roleDao);
	}
	
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<Tree> readRoleMenus(Long uuid) {
		List<Tree> treeList = new ArrayList<>();
		Menu root = menuDao.getById("0");
		//获取角色菜单
		Role role = roleDao.getById(uuid);
		List<Menu> roleMenus = role.getMenus();
		Tree t1 = null;
		Tree t2 = null;
		for(Menu m:root.getMenus()) {
			t1 = new Tree();
			t1.setId(m.getMenuid());
			t1.setText(m.getMenuname());
			//二级菜单
			for(Menu m2:m.getMenus()) {
				t2 = new Tree();
				t2.setId(m2.getMenuid());
				t2.setText(m2.getMenuname());
				if(roleMenus.contains(m2)) {
					t2.setChecked(true);
				}
				t1.getChildren().add(t2);
			}
			treeList.add(t1);
		}
		return treeList;
	}

	@Override
	public void updateRoleMenus(Long uuid, String checkedStr) {
		Role role = roleDao.getById(uuid);
		role.setMenus(new ArrayList<>());
		
		String[] ids = checkedStr.split(",");
		Menu menu = null;
		for(String id:ids) {
			menu = menuDao.getById(id);
			role.getMenus().add(menu);
		}
		
	}

	

}
