package com.wj.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wj.erp.biz.interfaces.IDepBiz;
import com.wj.erp.entity.Dep;
import com.wj.erp.entity.PageBean;

public class DepAction {

	private IDepBiz depBiz;
	private Dep dep1;
	private Dep dep2; //用来承载多余的变量
	//定义分页相关变量,page代表第几页，rows代表一页有几行
	private Integer page;
	private Integer rows;
	private Object param;

	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
	}
	
	public Dep getDep1() {
		return dep1;
	}
	
	public void setDep1(Dep dep1) {
		this.dep1 = dep1;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public void setParam(Object param) {
		this.param = param;
	}

	public void getList() {
		List<Dep> list = depBiz.getList(dep1,dep2,param);
		String jsonString = JSON.toJSONString(list);
		this.write(jsonString);
	}
	
	public Dep getDep2() {
		return dep2;
	}

	public void setDep2(Dep dep2) {
		this.dep2 = dep2;
	}

	/**
	 * 分页查询
	 */
	public void getListByPage() {
		PageBean<Dep> pageBean = depBiz.getListByPage(dep1,dep2,param,page,rows);
		System.out.println(JSON.toJSONString(pageBean));
		Map<String,Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", pageBean.getList());
		jsonMap.put("total", pageBean.getTotalSize());
//		jsonMap.put("total", 15);
		this.write(JSON.toJSONString(jsonMap));
//		this.write(JSON.toJSONString(pageBean.getList()));
	}
	

	public void list() {
		List<Dep> list = depBiz.getList();
		String jsonString = JSON.toJSONString(list);
		this.write(jsonString);
	}
	
	public void write(String jsonString) {
		try {
			String jsonp = ServletActionContext.getRequest().getParameter("callback");
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/json;charset=utf-8");
			if(StringUtils.isNotBlank(jsonp)) {
				response.getWriter().write(jsonp + "(" + jsonString + ")");	
			}else {
				response.getWriter().write(jsonString);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 新增
	 */
	private Dep dep;
	
	public Dep getDep() {
		return dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}

	public void add() {
		Map<String,Object> rtn = new HashMap<String,Object>();
		try {
			depBiz.add(dep);
			rtn.put("success", true);
			rtn.put("message", "保存成功");
		} catch (Exception e) {
			rtn.put("success", false);
			rtn.put("message", "保存失败");
		}
		
		this.write(JSON.toJSONString(rtn));
	}
	
	/**
	 * 删除方法
	 */
	public void delete() {
		Map<String,Object> rtn = new HashMap<String,Object>();
		try {
			depBiz.delete(dep);
			rtn.put("success", true);
			rtn.put("message", "删除成功");
		} catch (Exception e) {
			rtn.put("success", false);
			rtn.put("message", "删除失败");
		}
		
		this.write(JSON.toJSONString(rtn));
	}
	
	public void getData() {
		if(null != dep && null != dep.getUuid()) {
			dep = depBiz.get(dep);
			this.write(JSON.toJSONString(dep));
		}
		
	}
	
	
}
