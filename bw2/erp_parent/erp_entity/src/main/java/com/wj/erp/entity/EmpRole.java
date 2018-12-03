package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 员工角色
 * @author [author]
 *
 */
public class EmpRole implements Serializable{

	private Long empuuid; //员工编号
	private Long roleuuid; //角色编号
	private Long uuid; //编号
	
	public Long getEmpuuid() {
		return empuuid;
	}
	public void setEmpuuid(Long empuuid) {
		this.empuuid = empuuid;
	}
	
	public Long getRoleuuid() {
		return roleuuid;
	}
	public void setRoleuuid(Long roleuuid) {
		this.roleuuid = roleuuid;
	}
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	
}
