package com.wj.erp.entity;

import java.io.Serializable;

/**
 * ����
 * @author 24253
 *
 */
public class Dep implements Serializable{

	/*
	 * ���ű��
	 */
	private Long uuid;
	/*
	 * ��������
	 */
	private String name;
	/*
	 * ��ϵ�˵绰
	 */
	private String tele;
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	
	
	
}
