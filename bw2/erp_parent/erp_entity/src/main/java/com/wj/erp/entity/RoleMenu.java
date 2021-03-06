package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 角色菜单
 * @author [author]
 *
 */
public class RoleMenu implements Serializable{

	private Long uuid; //编号
	private Long roleuuid; //角色编号
	private String menuuuid; //菜单编号
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	
	public Long getRoleuuid() {
		return roleuuid;
	}
	public void setRoleuuid(Long roleuuid) {
		this.roleuuid = roleuuid;
	}
	
	public String getMenuuuid() {
		return menuuuid;
	}
	public void setMenuuuid(String menuuuid) {
		this.menuuuid = menuuuid;
	}
	
}
