package com.cf.login.apiinterface.order.impl;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.order.CreateOrderRequest;
import com.cf.api.request.app.order.OrderQueryRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.order.LoginCreateOrderResponse;
import com.cf.api.response.app.order.OrderCancelResponse;
import com.cf.api.response.app.order.OrderQueryResponse;
import com.cf.login.apiinterface.IApiInterface;
import com.cf.login.apiinterface.order.OrderInterface;
import com.cf.login.constant.APIUrlUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderInterfaceImpl implements OrderInterface {

    @Resource
    private IApiInterface iApiInterface;

    @Override
    public APIResponse<LoginCreateOrderResponse> creteOrderRequest(APIRequest<CreateOrderRequest> apiRequest) {
        return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.POST_QR_CODE_ORDER, LoginCreateOrderResponse.class);
    }

    @Override
    public APIResponse<OrderCancelResponse> orderCancel(APIRequest<OrderQueryRequest> apiRequest) {
        return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.POST_CANCEL_ORDER, OrderCancelResponse.class);
    }

    @Override
    public APIResponse<OrderQueryResponse> orderRechargeQuery(APIRequest<OrderQueryRequest> apiRequest) {
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_QUERY_ORDER, OrderQueryResponse.class);
    }
}
