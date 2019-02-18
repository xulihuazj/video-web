package com.cf.login.apiinterface.mybill;

import com.cf.api.request.APIRequest;
import com.cf.api.request.finance.BillTypeRequest;
import com.cf.api.request.finance.MyBillDetailRequest;
import com.cf.api.request.finance.MyBillRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.finance.BillTypeResponse;
import com.cf.api.response.finance.MyBillListResponse;
import com.cf.api.response.finance.MyBillResponse;

public interface MyBillInterface {
    
    //账单类型列表
    APIResponse<BillTypeResponse> queryBillType(APIRequest<BillTypeRequest> apiRequest);
    
    //我的账单列表
    APIResponse<MyBillListResponse> list(APIRequest<MyBillRequest> apiRequest);

    //我的账单详情
    APIResponse<MyBillResponse> myBillDetail(APIRequest<MyBillDetailRequest> apiRequest);

}
