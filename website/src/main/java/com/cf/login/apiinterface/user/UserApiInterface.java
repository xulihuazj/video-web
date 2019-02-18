package com.cf.login.apiinterface.user;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.user.*;
import com.cf.api.request.sms.SMSCodeRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.user.RegisteredResponse;
import com.cf.api.response.app.user.UserLoginResponse;

public interface UserApiInterface {
	
	APIResponse<Void> sendMobileCode(APIRequest<SMSCodeRequest> apiRequest);

	APIResponse<Void> getSMSCode(APIRequest<SMSCodeRequest> apiRequest);
	
	APIResponse<RegisteredResponse> registered(APIRequest<RegisteredRequest> apiRequest);
	
	APIResponse<UserLoginResponse> loginPC(APIRequest<ClientLoginPCRequest> apiRequest);

	APIResponse<RegisteredResponse> changePhone(APIRequest<BindAccountRequest> apiRequest);

	APIResponse<RegisteredResponse> changePhoneValidCode(APIRequest<PhoneCodeVaildPCRequest> apiRequest);

	APIResponse<RegisteredResponse> resetPassword(APIRequest<ModifyPasswordPCRequest> apiRequest);
	
	APIResponse<Void> checkMobileCode(APIRequest<CheckMobileCodeRequest> apiRequest);
	
	APIResponse<RegisteredResponse> resetPasswordByMobileCode(APIRequest<RegisteredRequest> apiRequest);
	
}
