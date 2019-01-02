package com.wj.erp.entity;

import java.util.ArrayList;
import java.util.List;

public class Tree {

	/**
	 * id:节点ID，对加载远程数据
	 * text：显示节点文本
	 * checked:表示该节点是否被选中
	 * children：一个节点数组声明了若干节点
	 */
	private String id;
	private String text;
	private boolean checked;
	private List<Tree> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<Tree> getChildren() {
		if(null == children) {
			children = new ArrayList<>();
		}
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	
	
}
