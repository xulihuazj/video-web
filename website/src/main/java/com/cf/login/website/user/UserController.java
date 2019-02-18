package com.cf.login.website.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cf.api.dto.app.user.UserInfoDTO;
import com.cf.api.request.app.smart.RoomRecordRequest;
import com.cf.api.request.app.user.*;
import com.cf.api.response.app.house.AppHouseListResponse;
import com.cf.api.response.app.smart.RoomRecordResponse;
import com.cf.api.response.app.user.UserLoginResponse;
import com.cf.login.apiinterface.home.HomeInterface;
import com.cf.login.constant.KeyName;
import com.cf.utils.web.WebHelper;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.Serializers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.cf.api.request.APIRequest;
import com.cf.api.request.sms.SMSCodeRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.user.RegisteredResponse;
import com.cf.api.system.SystemType;
import com.cf.login.apiinterface.user.UserApiInterface;
import com.cf.login.website.BaseController;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Resource
	UserApiInterface userApiInterface;
	@Resource
	HomeInterface homeInterface;
	
	@GetMapping("/register/page")
	@SystemType
	public ModelAndView registerPage(){
		ModelAndView mw = new ModelAndView("reg/reg");
		return mw;
	}
	
	@PostMapping("/register")
	@ResponseBody
	@SystemType
	public JSONObject register(String mobile,String password,String mobileCode,HttpServletRequest httpRequest){
		JSONObject result = new JSONObject();
		RegisteredRequest bizRequest = new RegisteredRequest();
		bizRequest.setMobile(mobile);
		bizRequest.setPassword(password);
		bizRequest.setSmsCode(mobileCode);
		APIRequest<RegisteredRequest> apiRequest = BaseController.getRequest(httpRequest, bizRequest);
		APIResponse<RegisteredResponse> apiResponse = userApiInterface.registered(apiRequest);
		if("SUCCESS".equals(apiResponse.getStatusCode())){
			result.put("code", "ok");
		}else{
			result.put("code", "error");
			result.put("message", apiResponse.getMessage());
		}
		return result;
	}
	
	@PostMapping("/mobile/code")
	@ResponseBody
	@SystemType
	public JSONObject mobileCode(String mobile,String imgCode,HttpServletRequest httpRequest,Integer serviceType){
		JSONObject result = new JSONObject();
		SMSCodeRequest bizRequest = new SMSCodeRequest();
		bizRequest.setCodeType(serviceType == null ? 9:serviceType);
		bizRequest.setMobile(mobile);
		bizRequest.setImgCode(imgCode);
		bizRequest.setUuid(BaseController.getUuid(httpRequest));
		APIRequest<SMSCodeRequest> apiRequest = BaseController.getRequest(httpRequest, bizRequest);
		APIResponse<Void> apiResponse = userApiInterface.sendMobileCode(apiRequest);
		if("SUCCESS".equals(apiResponse.getStatusCode())){
			result.put("code", "ok");
		}else{
			result.put("code", "error");
			result.put("message", apiResponse.getMessage());
		}
		return result;
	}

	@GetMapping("/account/page")
	public ModelAndView modifyAccountPage(HttpServletRequest httpServletRequest, HttpServletResponse httpResponse) throws IOException {
		UserInfoDTO userInfo = BaseController.getUserInfo(httpServletRequest);
		if (userInfo != null && StringUtils.isNotBlank(userInfo.getUserPhone())) {
			ModelAndView mw = new ModelAndView("setting/modifyAccount");
			mw.addObject("userPhone", userInfo.getUserPhone());
			RoomRecordRequest bizRequest = new RoomRecordRequest();
			APIRequest<RoomRecordRequest> request = BaseController.getRequest(httpServletRequest, bizRequest);
			APIResponse<RoomRecordResponse> apiResponse = homeInterface.roomRecordDetail(request);
			if (apiResponse.getStatusCode().equals("20010008")) {
				mw.addObject("room_status", "not_check_in");
			}
			return mw;
		}else{
			ModelAndView mv = new ModelAndView("login/login");
			WebHelper.delCookie(httpResponse, KeyName.INFO_KEY_NAME);
			return mv;
		}
	}

	/**
	 * 获取短信验证码
	 *
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws Exception
	 */
	@PostMapping("/code")
	@ResponseBody
	public APIResponse getLoginCode(SMSCodeRequest smsCodeRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception {
		APIRequest<SMSCodeRequest> apiRequest = BaseController.getRequest(httpServletRequest, smsCodeRequest);
		APIResponse<?> response = userApiInterface.getSMSCode(apiRequest);
		return response;
	}

	/**
	 * 手机号换绑
	 *
	 * @param bindAccountRequest
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/account")
	@ResponseBody
	public APIResponse changeAccount(BindAccountRequest bindAccountRequest, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {
		APIRequest<BindAccountRequest> apiRequest = BaseController.getRequest(httpServletRequest, bindAccountRequest);
		APIResponse<RegisteredResponse> response = userApiInterface.changePhone(apiRequest);
		if("SUCCESS".equals(response.getStatusCode())){
			UserLoginResponse userLoginResponse = new UserLoginResponse();
			UserInfoDTO userInfo = BaseController.getUserInfo(httpServletRequest);
			userLoginResponse.setToken(apiRequest.getToken());
			userInfo.setUserPhone(bindAccountRequest.getAccountCert());
			userLoginResponse.setUserInfoDTO(userInfo);
			BaseController.setUserInfo(httpServletRequest, httpServletResponse, userLoginResponse);
		}
		return response;
	}

    /**
     * 验证码验证
     * @param phoneCodeVaildRequest
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    @PostMapping("/validcode")
    @ResponseBody
    public APIResponse validCode(PhoneCodeVaildPCRequest phoneCodeVaildRequest, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {
        APIRequest<PhoneCodeVaildPCRequest> apiRequest = BaseController.getRequest(httpServletRequest, phoneCodeVaildRequest);
        APIResponse<RegisteredResponse> response = userApiInterface.changePhoneValidCode(apiRequest);
        return response;
    }

	/**
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws Exception
	 */
	@PostMapping("/pwd")
	@ResponseBody
	public APIResponse pwdDo(ModifyPasswordPCRequest modifyPasswordPCRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception {
		APIRequest<ModifyPasswordPCRequest> apiRequest = BaseController.getRequest(httpServletRequest, modifyPasswordPCRequest);
		APIResponse<?> response = userApiInterface.resetPassword(apiRequest);
		return response;
	}
	
	@GetMapping("/forget/password")
	@SystemType
	public ModelAndView forgetPassword(){
		ModelAndView mv = new ModelAndView("password/forget_password");
		return mv;
	}
	
	@PostMapping("/check/mobile/code")
	@SystemType
	@ResponseBody
	public JSONObject checkMobileCode(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String mobile,
			String mobileCode, String serviceType) {
		JSONObject result = new JSONObject();
		result.put("code", "ok");
		CheckMobileCodeRequest bizRequest = new CheckMobileCodeRequest();
		bizRequest.setCode(mobileCode);
		bizRequest.setMobile(mobile);
		bizRequest.setServiceType(serviceType);
		APIRequest<CheckMobileCodeRequest> apiRequest = super.getRequest(httpRequest, bizRequest);
		APIResponse<Void> apiResponse = this.userApiInterface.checkMobileCode(apiRequest);
		if("SUCCESS".equals(apiResponse.getStatusCode())){
			result.put("code", "ok");
		}else{
			result.put("code", "error");
			result.put("message", apiResponse.getMessage());
		}
		return result;
	}

	@PostMapping("/password/mobile/code")
	@SystemType
	@ResponseBody
	public JSONObject resetPasswordByMobileCode(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			String mobile, String mobileCode,String password) {
		JSONObject result = new JSONObject();
		result.put("code", "ok");
		RegisteredRequest bizRequest = new RegisteredRequest();
		bizRequest.setMobile(mobile);
		bizRequest.setPassword(password);
		bizRequest.setSmsCode(mobileCode);
		APIRequest<RegisteredRequest> apiRequest = super.getRequest(httpRequest, bizRequest);
		APIResponse<RegisteredResponse> apiResponse = this.userApiInterface.resetPasswordByMobileCode(apiRequest);
		if("SUCCESS".equals(apiResponse.getStatusCode())){
			result.put("code", "ok");
		}else{
			result.put("code", "error");
			result.put("message", apiResponse.getMessage());
		}
		return result;
	}
	
}
