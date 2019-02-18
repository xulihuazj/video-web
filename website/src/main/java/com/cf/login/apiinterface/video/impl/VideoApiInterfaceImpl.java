package com.cf.login.apiinterface.video.impl;

import com.cf.api.request.APIRequest;
import com.cf.api.request.sms.SMSCodeRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.user.RegisteredResponse;
import com.cf.login.apiinterface.IApiInterface;
import com.cf.login.apiinterface.video.VideoApiInterface;
import com.cf.login.constant.APIUrlUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 视频首页
 */
@Service
public class VideoApiInterfaceImpl implements VideoApiInterface {

    @Resource
    IApiInterface iApiInterface;

    @Override
    public APIResponse<RegisteredResponse> clientVideoList(APIRequest<SMSCodeRequest> apiRequest) {
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_CLIENT_VIDEO_LIST, RegisteredResponse.class);
    }

    @Override
    public  APIResponse<RegisteredResponse> clientVideoDetail(APIRequest<SMSCodeRequest> apiRequest){
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_CLIENT_VIDEO_DETIAL, RegisteredResponse.class);
    }

}
