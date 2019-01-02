package com.wj.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.wj.erp.biz.interfaces.IEmpBiz;
import com.wj.erp.entity.Emp;

public class LoginAction {

	private String username; // 登陆用户名
	private String pwd; // 密码

	/*
	 * private IEmpBiz empBiz;
	 * 
	 * 
	 * public void setEmpBiz(IEmpBiz empBiz) { this.empBiz = empBiz; }
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void checkUser() {
		try {
			/*
			 * Emp loginUser = empBiz.getByUsernameAndPwd(username, pwd); if(null !=
			 * loginUser) { ActionContext.getContext().getSession().put("loginUser",
			 * loginUser); ajaxReturn(true,""); }else { ajaxReturn(false,"用户名或密码不正确"); }
			 */

			// 改使用shiro框架来实现
			// 1.创建令牌，身份认证
			UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
			// 2.获取主题subject：封装用户的一些操作
			Subject subject = SecurityUtils.getSubject();
			// 3.执行login
			subject.login(token);
			ajaxReturn(true,"");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "用户名或密码不正确");
		}
	}

	public void showName() {
		/* Emp emp = (Emp) ActionContext.getContext().getSession().get("loginUser"); */
		// 获取主题
		Subject subject = SecurityUtils.getSubject();
		// 提取主角，拿到emp
		Emp emp = (Emp) subject.getPrincipal();
		if (null != emp) {
			ajaxReturn(true, emp.getUsername());
		} else {
			ajaxReturn(false, "");
		}
	}

	public void loginOut() {
//		ActionContext.getContext().getSession().remove("loginUser");
		SecurityUtils.getSubject().logout();
	}

	/**
	 * 返回前端操作结果
	 */
	public void ajaxReturn(boolean success, String message) {
		Map<String, Object> rtn = new HashMap<>();
		rtn.put("success", success);
		rtn.put("message", message);
		this.write(JSON.toJSONString(rtn));
	}

	public void write(String jsonString) {
		try {
			String jsonp = ServletActionContext.getRequest().getParameter("callback");
			HttpServletResponse response = ServletActionContext.getResponse();
			// 跨域设置（IE10以下版本无法使用）
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
}
