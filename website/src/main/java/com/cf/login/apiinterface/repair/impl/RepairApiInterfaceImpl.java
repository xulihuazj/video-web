package com.cf.login.apiinterface.repair.impl;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.service.RepairDetailRequest;
import com.cf.api.request.repair.RepairListRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.service.ClientRepairListResponse;
import com.cf.api.response.app.service.RepairDetailResponse;
import com.cf.login.apiinterface.IApiInterface;
import com.cf.login.apiinterface.repair.RepairApiInterface;
import com.cf.login.constant.APIUrlUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RepairApiInterfaceImpl implements RepairApiInterface {

    @Resource
    private IApiInterface iApiInterface;

    //维修保洁列表
    @Override
    public APIResponse<ClientRepairListResponse> list(APIRequest<RepairListRequest> apiRequest) {
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_REPAIR_LIST, ClientRepairListResponse.class);
    }

    //维修保洁详情
    @Override
    public APIResponse<RepairDetailResponse> repairDetail(APIRequest<RepairDetailRequest> apiRequest){
        return iApiInterface.sendGetJson(apiRequest,APIUrlUtil.GET_REPAIR_DETAIL,RepairDetailResponse.class);
    }
}
