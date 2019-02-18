package com.cf.login.apiinterface.video;

import com.cf.api.request.APIRequest;
import com.cf.api.request.sms.SMSCodeRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.user.RegisteredResponse;

public interface VideoApiInterface {

    /**
     * 视频列表
     * @param apiRequest
     * @return
     */
    APIResponse<RegisteredResponse> clientVideoList(APIRequest<SMSCodeRequest> apiRequest);

    /**
     * 视频详情
     * @param apiRequest
     * @return
     */
    APIResponse<RegisteredResponse> clientVideoDetail(APIRequest<SMSCodeRequest> apiRequest);



}
