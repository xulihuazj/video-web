package com.cf.login.apiinterface.mybill.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cf.api.request.APIRequest;
import com.cf.api.request.finance.BillTypeRequest;
import com.cf.api.request.finance.MyBillDetailRequest;
import com.cf.api.request.finance.MyBillRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.finance.BillTypeResponse;
import com.cf.api.response.finance.MyBillListResponse;
import com.cf.api.response.finance.MyBillResponse;
import com.cf.login.apiinterface.IApiInterface;
import com.cf.login.apiinterface.mybill.MyBillInterface;
import com.cf.login.constant.APIUrlUtil;
@Service
public class MyBillInterfaceImpl implements MyBillInterface{
    @Resource
    private IApiInterface iApiInterface;

    @Override
    public APIResponse<BillTypeResponse> queryBillType(APIRequest<BillTypeRequest> apiRequest) {
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.BILL_TYPES, BillTypeResponse.class);
    }
    
    @Override
    public APIResponse<MyBillListResponse> list(APIRequest<MyBillRequest> apiRequest) {
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.MY_BILL_LIST, MyBillListResponse.class);
    }

    @Override
    public APIResponse<MyBillResponse> myBillDetail(APIRequest<MyBillDetailRequest> apiRequest) {
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.MY_BILL_DETAIL, MyBillResponse.class);
    }
 
}
