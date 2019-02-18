package com.cf.login.apiinterface.user.impl;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.user.*;
import com.cf.api.request.sms.SMSCodeRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.user.RegisteredResponse;
import com.cf.api.response.app.user.UserLoginResponse;
import com.cf.login.apiinterface.IApiInterface;
import com.cf.login.apiinterface.user.UserApiInterface;
import com.cf.login.constant.APIUrlUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserApiInterfaceImpl implements UserApiInterface {
	
	@Resource
	IApiInterface iApiInterface;
	
	@Override
	public APIResponse<Void> sendMobileCode(APIRequest<SMSCodeRequest> apiRequest) {
		return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.APP_SMS_MOBILE_CODE, Void.class);
	}

	@Override
	public APIResponse<Void> getSMSCode(APIRequest<SMSCodeRequest> apiRequest) {
		return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.POST_PMS_SMS_CODE, Void.class);
	}

	@Override
	public APIResponse<RegisteredResponse> registered(APIRequest<RegisteredRequest> apiRequest) {
		return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.CLIENT_USER_REGISTERED, RegisteredResponse.class);
	}

	@Override
	public APIResponse<UserLoginResponse> loginPC(APIRequest<ClientLoginPCRequest> apiRequest) {
		return this.iApiInterface.sendPostJson(apiRequest, APIUrlUtil.CLIENT_USER_PC_LOGIN, UserLoginResponse.class);
	}

	@Override
	public APIResponse<RegisteredResponse> changePhone(APIRequest<BindAccountRequest> apiRequest) {
		return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.POST_PMS_APP_USER_CHANGE_PHONE, RegisteredResponse.class);
	}

	@Override
	public APIResponse<RegisteredResponse> resetPassword(APIRequest<ModifyPasswordPCRequest> apiRequest) {
		return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.POST_PMS_APP_USER_PWD_RESET, RegisteredResponse.class);
	}

    @Override
    public APIResponse<RegisteredResponse> changePhoneValidCode(APIRequest<PhoneCodeVaildPCRequest> apiRequest) {
        return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.POST_PMS_APP_USER_CHANGE_PHONE_CODE, RegisteredResponse.class);
    }

	@Override
	public APIResponse<Void> checkMobileCode(APIRequest<CheckMobileCodeRequest> apiRequest) {
		return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.APP_SMS_CODE_CHECK, Void.class);
	}

	@Override
	public APIResponse<RegisteredResponse> resetPasswordByMobileCode(APIRequest<RegisteredRequest> apiRequest) {
		return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.CLIENT_USER_PASSWORD_RESET, RegisteredResponse.class);
	}
    
    
}
