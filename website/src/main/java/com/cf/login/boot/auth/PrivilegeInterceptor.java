package com.cf.login.boot.auth;

import com.cf.api.dto.app.user.UserInfoDTO;
import com.cf.api.system.SystemType;
import com.cf.login.constant.KeyName;
import com.cf.login.website.BaseController;
import com.cf.utils.web.WebHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 权限验证
 */
@Component
public class PrivilegeInterceptor implements HandlerInterceptor {

	private Logger logger = LogManager.getLogger(PrivilegeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean bl;
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		SystemType annotationSystem = method.getAnnotation(SystemType.class);
		// 检查登录状态
		bl = checkLogin(request);
		if (annotationSystem != null) {
			// 直接放行
			bl = true;
		}
		if(annotationSystem == null && !WebHelper.isAjax(request)){
			String uri = request.getRequestURI();
			BaseController.SetBackurl(uri, response);
		}
		if(!bl){
			if (!WebHelper.isAjax(request)){
				// 跳转至登录页面
				String url = BaseController.getServerURL(request)+ "/login/page";
				response.sendRedirect(url);
			}else{
				// ajax请求 设置请求头为403
				response.setStatus(403);
			}
		}
		return bl;
	}

	private boolean checkLogin(HttpServletRequest httpRequest) {
		UserInfoDTO userInfo = BaseController.getUserInfo(httpRequest);
		if(userInfo != null){
			httpRequest.getSession().setAttribute(KeyName.SESSION_USER_NAME, userInfo);
		}
		return userInfo != null && userInfo.getUserId() != null;
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
