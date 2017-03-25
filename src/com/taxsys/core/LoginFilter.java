package com.taxsys.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.taxsys.core.constant.Constant;
import com.taxsys.core.permission.PermissionCheck;
import com.taxsys.nsfw.user.entity.User;

/**
 * 登錄過濾器---不允許未登錄用戶訪問系統中任何功能
 * @author zhuxiaodong
 *
 */
public class LoginFilter implements Filter{
	private FilterConfig config;
	
	@Override
	public void destroy() {
		this.config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//取出用戶的請求URI
		String uri = httpRequest.getRequestURI();
		//判斷用戶請求是否是登錄請求
		if (!uri.contains("sys/login_")) {
			//不是登陸請求需要跳轉到登陸頁面
			if (httpRequest.getSession().getAttribute(Constant.SYS_USER)!= null) {
				//對於已經登錄系統的用戶需要判斷它的權限,如果有相應的權限才可以直接放行
				//訪問納稅服務子系統
				if (uri.contains("/nsfw/")) {
					//在Session中取出用戶信息
					User user = (User) httpRequest.getSession().getAttribute(Constant.SYS_USER);
					//從應用服務器啟動好的那個IOC容器中取出ApplicationContext
					WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(httpRequest.getSession().getServletContext());
					PermissionCheck check = (PermissionCheck) context.getBean("permissionCheck");
					//對納稅服務權限的檢查
					if (check.isAccessible(user, "nsfw")) {
						//具有相應的權限，則直接放行
						chain.doFilter(httpRequest, httpResponse);
					} else {
						//沒有權限，需要跳轉到沒有權限提示頁面
						httpResponse.sendRedirect(httpRequest.getContextPath()+"/sys/login_toNoPermissionUI.action");
					}
				} else {
					//不是訪問納稅服務子系統模塊的直接放行
					chain.doFilter(httpRequest, httpResponse);
				}
			} else {
				//未登錄想使用系統需跳轉到登錄頁面
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/sys/login_toLoginUI.action");
			}
		} else {
			//是登陸請求，直接放行
			chain.doFilter(httpRequest, httpResponse);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
}
