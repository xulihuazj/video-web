package com.cf.login.boot.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cf.login.constant.KeyName;
import com.cf.utils.security.SecurityHelper;
import com.cf.utils.web.WebHelper;

@Component
public class UUIDInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 取出uuid
		String uuid = WebHelper.getCookie(request, KeyName.UUID_KEY_NAME);
		// 如果没有则创建一个新的,已经存在则不用处理
		if(!(StringUtils.isNotBlank(uuid) && uuid.length() == 32)){
			// 产生一个32位长度的uuid
			uuid = SecurityHelper.MD5(System.currentTimeMillis()+"");
			// 写入cookie
			WebHelper.setCookie(response, KeyName.UUID_KEY_NAME, uuid, 315360000);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
