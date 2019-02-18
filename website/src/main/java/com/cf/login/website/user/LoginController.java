package com.cf.login.website.user;

import com.alibaba.fastjson.JSONObject;
import com.cf.api.enums.app.user.LoginTypeEnum;
import com.cf.api.request.APIRequest;
import com.cf.api.request.app.user.ClientLoginPCRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.user.UserLoginResponse;
import com.cf.api.system.SystemType;
import com.cf.login.apiinterface.user.UserApiInterface;
import com.cf.login.constant.KeyName;
import com.cf.login.website.BaseController;
import com.cf.utils.log.LogHelper;
import com.cf.utils.web.WebHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	@Resource
	private UserApiInterface userApiInterface;
	
	@RequestMapping("/page")
	@SystemType
	public ModelAndView loginPage(HttpServletRequest httpRequest,HttpServletResponse httpResponse) throws IOException{
		ModelAndView mv = new ModelAndView("login/login");
		WebHelper.delCookie(httpResponse, KeyName.INFO_KEY_NAME);
		return mv;
	}
	
	@PostMapping
	@SystemType
	@ResponseBody
	public JSONObject login(String mobile, String imgCode, String password, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws Exception {
		JSONObject result = new JSONObject();
		result.put("code", "error");
		ClientLoginPCRequest bizRequest = new ClientLoginPCRequest();
		bizRequest.setImgCode(imgCode);
		bizRequest.setLoginCert(password);
		bizRequest.setLoginType(LoginTypeEnum.PASSWORD);
		bizRequest.setMobile(mobile);
		bizRequest.setUuid(super.getUuid(httpRequest));
		APIRequest<ClientLoginPCRequest> apiRequest = super.getRequest(httpRequest, bizRequest);
		APIResponse<UserLoginResponse> apiResponse = userApiInterface.loginPC(apiRequest);
		if("SUCCESS".equals(apiResponse.getStatusCode())){
			UserLoginResponse info = apiResponse.getBizResponse();
			if(info != null){
				result.put("code", "ok");
				super.setUserInfo(httpRequest, httpResponse, info);
				String url = super.getBackurl(httpRequest);
				// 判断是否需要跳转到指定页
				if(StringUtils.isNotBlank(url)){
					result.put("backurl", url);
					super.removeBackurl(httpResponse);
				}
			}
		}else{
			result.put("message", apiResponse.getMessage());
		}
		return result;
	}
	
	@GetMapping("/out")
	@ResponseBody
	public void loginOut(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse){
		// 清除用户登录信息
		try {
			// api接口调用(待开发)
			// 清除本地登录信息
			httpRequest.getSession().removeAttribute(KeyName.SESSION_USER_NAME);
			WebHelper.delCookie(httpResponse, KeyName.INFO_KEY_NAME);
			String url = BaseController.getServerURL(httpRequest);
			httpResponse.sendRedirect(url);
		} catch (IOException e) {
			LogHelper.error(e);
		}
	}
	
}
