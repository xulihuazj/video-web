package com.cf.login.apiinterface.common;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.common.AppHouseDetailRequest;
import com.cf.api.request.app.user.RegisteredRequest;
import com.cf.api.request.sms.SMSCodeRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.house.AppHouseDetailResponse;
import com.cf.api.response.app.house.AppHouseListResponse;
import com.cf.api.response.app.user.RegisteredResponse;

public interface CommonApiInterface {
	
	APIResponse<AppHouseListResponse> house(APIRequest<?> apiRequest);

	APIResponse<AppHouseDetailResponse> houseDetail(APIRequest<AppHouseDetailRequest> apiRequest);
	
}
