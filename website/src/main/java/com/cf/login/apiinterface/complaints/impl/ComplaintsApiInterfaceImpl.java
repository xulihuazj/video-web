package com.cf.login.apiinterface.complaints.impl;

import com.cf.api.dto.complaints.ComplaintsInfoDTO;
import com.cf.api.request.APIRequest;
import com.cf.api.request.complaints.ComplaintsDetailRequest;
import com.cf.api.request.complaints.ComplaintsListRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.complaints.ComplaintsListResponse;
import com.cf.api.response.complaints.ComplaintsLogListResponse;
import com.cf.login.apiinterface.IApiInterface;
import com.cf.login.apiinterface.complaints.ComplaintsApiInterface;
import com.cf.login.constant.APIUrlUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ComplaintsApiInterfaceImpl  implements ComplaintsApiInterface {
    @Resource
    private IApiInterface iApiInterface;

    @Override
    public APIResponse<ComplaintsListResponse> list(APIRequest<ComplaintsListRequest> apiRequest) {
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_COMPLAINTS_LIST, ComplaintsListResponse.class);    }

    @Override
    public APIResponse<ComplaintsInfoDTO> detail(APIRequest<ComplaintsDetailRequest> apiRequest) {
        return iApiInterface.sendGetJson(apiRequest,APIUrlUtil.GET_COMPLAINTS_DETAIL,ComplaintsInfoDTO.class);
    }

    @Override
    public APIResponse<ComplaintsLogListResponse> logList(APIRequest<ComplaintsDetailRequest> request) {
        return iApiInterface.sendGetJson(request,APIUrlUtil.GET_COMPLAINTS_DETAIL_LOG,ComplaintsLogListResponse.class);
    }
}
