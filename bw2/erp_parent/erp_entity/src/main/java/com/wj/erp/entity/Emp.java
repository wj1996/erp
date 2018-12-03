package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 员工
 * @author [author]
 *
 */
public class Emp implements Serializable{

	private java.util.Date birthday; //出生年月日
	private String address; //联系地址
//	private Long depuuid; //部门编号
	private Long gender; //性别
	private String name; //真实姓名
	private String pwd; //登陆密码
	private String tele; //联系电话
	private Long uuid; //编号
	
	private Dep dep;
	
	private String email; //邮件地址
	private String username; //登陆名
	
	
	public Dep getDep() {
		return dep;
	}
	public void setDep(Dep dep) {
		this.dep = dep;
	}
	public java.util.Date getBirthday() {
		return birthday;
	}
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/*public Long getDepuuid() {
		return depuuid;
	}
	public void setDepuuid(Long depuuid) {
		this.depuuid = depuuid;
	}*/
	
	public Long getGender() {
		return gender;
	}
	public void setGender(Long gender) {
		this.gender = gender;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
