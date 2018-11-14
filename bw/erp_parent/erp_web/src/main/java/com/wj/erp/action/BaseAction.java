package com.wj.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.wj.erp.biz.interfaces.IBaseBiz;
import com.wj.erp.biz.interfaces.IDepBiz;
import com.wj.erp.entity.PageBean;

public class BaseAction<T> {

	private IBaseBiz baseBiz;
	private T t1;
	private T t2; //用来承载多余的变量
	//定义分页相关变量,page代表第几页，rows代表一页有几行
	private Integer page;
	private Integer rows;
	private Object param;

	public void setDepBiz(IDepBiz baseBiz) {
		this.baseBiz = baseBiz;
	}
	
	public T getT1() {
		return t1;
	}
	
	public void setT1(T t1) {
		this.t1 = t1;
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
		List<T> list = baseBiz.getList(t1,t2,param);
		String jsonString = JSON.toJSONString(list);
		this.write(jsonString);
	}
	
	public T getT2() {
		return t2;
	}

	public void setT2(T t2) {
		this.t2 = t2;
	}

	/**
	 * 分页查询
	 */
	public void getListByPage() {
		PageBean<T> pageBean = baseBiz.getListByPage(t1,t2,param,page,rows);
		System.out.println(JSON.toJSONString(pageBean));
		Map<String,Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", pageBean.getList());
		jsonMap.put("total", pageBean.getTotalSize());
//		jsonMap.put("total", 15);
		this.write(JSON.toJSONString(jsonMap));
//		this.write(JSON.toJSONString(pageBean.getList()));
	}
	

	public void list() {
		List<T> list = baseBiz.getList();
		String jsonString = JSON.toJSONString(list);
		this.write(jsonString);
	}
	
	public void write(String jsonString) {
		try {
			String jsonp = ServletActionContext.getRequest().getParameter("callback");
			HttpServletResponse response = ServletActionContext.getResponse();
//			response.addHeader("Access-Control-Allow-Origin", "*");
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
	private T t;
	
	public T getT() {
		return t;
	}

	public void setT(T T) {
		this.t = t;
	}

	public void add() {
		Map<String,Object> rtn = new HashMap<String,Object>();
		try {
			baseBiz.add(t);
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
			baseBiz.delete(t);
			rtn.put("success", true);
			rtn.put("message", "删除成功");
		} catch (Exception e) {
			rtn.put("success", false);
			rtn.put("message", "删除失败");
		}
		
		this.write(JSON.toJSONString(rtn));
	}
	
	public void getData() {
		//子类具体实现
	}
	
	/**
	 * 修改方法
	 */
	public void update() {
		//子类实现
	}
}
