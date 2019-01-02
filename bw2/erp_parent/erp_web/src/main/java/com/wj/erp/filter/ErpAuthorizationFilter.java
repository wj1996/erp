package com.wj.erp.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
/**
 * 自定义授权过滤器
 * @author 24253
 *
 */
public class ErpAuthorizationFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		
		Subject subject = getSubject(request, response);
		String[] perms  = (String[]) mappedValue;
		if(null == perms || perms.length == 0) {
			return true;
		}
		if(null != perms && perms.length > 0) {
			for(String perm:perms) {
				if(subject.isPermitted(perm)) {
					return true;
				}
			}
		}
		return false;
	}

}
