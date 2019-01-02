package com.wj.erp.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.wj.erp.biz.interfaces.IEmpBiz;
import com.wj.erp.entity.Emp;
import com.wj.erp.entity.Menu;

public class ErpRealm extends AuthorizingRealm{
	
	
	private IEmpBiz empBiz;
	

	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行了授权方法");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.addStringPermission("部门");
		//获取当前登录用户的菜单权限
		Emp emp = (Emp) principals.getPrimaryPrincipal();
		List<Menu> list = empBiz.getMenusByEmpuuid(emp.getUuid());
		for(Menu menu:list) {
			//使用menuname来作为授权里面的key值，配置授权访问的URL=的右边是菜单名称
			info.addStringPermission(menu.getMenuname());
		}
		return info;
	}

	/**
	 * 认证
	 * @return null:认证失败，AuThenticationInfo实现类，认证成功
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("执行了认证方法");
		//用户名和密码
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		String pwd = new String(upt.getPassword());
		Emp emp = empBiz.getByUsernameAndPwd(upt.getUsername(),pwd);
		if(null != emp) {
			//构造参数1：主角=登录用户 构造参数2：授权码 构造参数3：realm的名称
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(emp,pwd,getName());
			return info;
		}
		return null;
	}

}
