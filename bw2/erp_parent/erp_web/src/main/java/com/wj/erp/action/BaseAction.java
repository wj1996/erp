package com.wj.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionContext;
import com.wj.erp.biz.interfaces.IBaseBiz;
import com.wj.erp.biz.interfaces.IDepBiz;
import com.wj.erp.entity.Emp;
import com.wj.erp.entity.PageBean;

public class BaseAction<T> {

	private IBaseBiz<T> baseBiz;
	private T t1;
	private T t2; // 用来承载多余的变量
	// 定义分页相关变量,page代表第几页，rows代表一页有几行
	private Integer page;
	private Integer rows;
	private Object param;

	public void setBaseBiz(IBaseBiz<T> baseBiz) {
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
		List<T> list = baseBiz.getList(t1, t2, param);
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
		PageBean<T> pageBean = baseBiz.getListByPage(t1, t2, param, page, rows);
//		System.out.println(JSON.toJSONString(pageBean));
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", pageBean.getList());
		jsonMap.put("total", pageBean.getTotalSize());
//		jsonMap.put("total", 15);
//		this.write(JSON.toJSONString(jsonMap));
		//,SerializerFeature.DisableCircularReferenceDetect
		this.write(JSON.toJSONString(jsonMap,SerializerFeature.DisableCircularReferenceDetect));
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
			//跨域设置（IE10以下版本无法使用）
//			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("text/json;charset=utf-8");
			if (StringUtils.isNotBlank(jsonp)) {
				response.getWriter().write(jsonp + "(" + jsonString + ")");
			} else {
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

	public void setT(T t) {
		this.t = t;
	}

	public void add() {
		try {
			baseBiz.add(t);
			this.ajaxReturn(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxReturn(false, "增加失败");
		}

	}

	/**
	 * 删除方法
	 */
	public void delete() {
		try {
			baseBiz.delete(t);
			this.ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxReturn(true, "删除失败");
		}

	}

	private Long id;
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	
	public void getData() {
		T tt = baseBiz.get(id);
		String jsonString = JSON.toJSONStringWithDateFormat(tt, "yyyy-MM-dd");
//		jsonString = JSON.toJSONString(tt);
		System.out.println(jsonString);
		this.write(mapData(jsonString, "t"));
	}

	/**
	 * 修改方法
	 */
	public void update() {
		try {
			baseBiz.update(t);
			this.ajaxReturn(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxReturn(false, "修改失败");
		}
	}
	
	/**
	 * 返回前端操作结果
	 */
	public void ajaxReturn(boolean success,String message) {
		Map<String,Object> rtn = new HashMap<>();
		rtn.put("success", success);
		rtn.put("message", message);
		this.write(JSON.toJSONString(rtn));
	}
	
	/**
	 * 转换
	 */
	public String mapData(String jsonString,String prefix) {
		Map<String,Object> map = JSON.parseObject(jsonString);
		Map<String,Object> rtn = new HashMap<>();
		for(String key:map.keySet()) {
			if(map.get(key) instanceof Map) {
				Map<String,Object> m2 = (Map<String, Object>) map.get(key);
				for(String key2:m2.keySet()) {
					rtn.put(prefix + "." + key + "." + key2, m2.get(key2));
				}
			}else {
				rtn.put(prefix + "." + key, map.get(key));
			}
		}
		
		return JSON.toJSONString(rtn);
	}
	
	public Emp getLoginUser() {
		return (Emp) ActionContext.getContext().getSession().get("loginUser");
	}
}
