package com.cf.login.apiinterface.repair;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.service.RepairDetailRequest;
import com.cf.api.request.repair.RepairListRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.service.ClientRepairListResponse;
import com.cf.api.response.app.service.RepairDetailResponse;

public interface RepairApiInterface {

    //维修保洁列表
    APIResponse<ClientRepairListResponse> list(APIRequest<RepairListRequest> apiRequest);

    //维修保洁详情
    APIResponse<RepairDetailResponse> repairDetail(APIRequest<RepairDetailRequest> apiRequest);

}
