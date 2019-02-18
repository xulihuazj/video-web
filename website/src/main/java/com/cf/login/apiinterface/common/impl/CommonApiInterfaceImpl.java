package com.cf.login.apiinterface.common.impl;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.common.AppHouseDetailRequest;
import com.cf.api.request.app.user.RegisteredRequest;
import com.cf.api.request.sms.SMSCodeRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.house.AppHouseDetailResponse;
import com.cf.api.response.app.house.AppHouseListResponse;
import com.cf.api.response.app.user.RegisteredResponse;
import com.cf.login.apiinterface.IApiInterface;
import com.cf.login.apiinterface.common.CommonApiInterface;
import com.cf.login.constant.APIUrlUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommonApiInterfaceImpl implements CommonApiInterface {
	
	@Resource
	IApiInterface iApiInterface;

    @Override
	public APIResponse<AppHouseListResponse> house(APIRequest<?> apiRequest) {
		return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_HOUSE_LIST, AppHouseListResponse.class);
	}

    @Override
    public APIResponse<AppHouseDetailResponse> houseDetail(APIRequest<AppHouseDetailRequest> apiRequest) {
		return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_HOUSE_DETAIL, AppHouseDetailResponse.class);
	}

}
