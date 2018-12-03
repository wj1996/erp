package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 菜单
 * @author [author]
 *
 */
public class Menu implements Serializable{

	private String menuname; //菜单名称
	private String icon; //图标
	private String menuid; //菜单ID
	private String pid; //上级菜单ID
	private String url; //URL
	
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
