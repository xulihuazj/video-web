package com.cf.login.apiinterface.order;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.order.CreateOrderRequest;
import com.cf.api.request.app.order.OrderQueryRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.order.LoginCreateOrderResponse;
import com.cf.api.response.app.order.OrderCancelResponse;
import com.cf.api.response.app.order.OrderQueryResponse;

public interface OrderInterface {


    /**
     * 创建订单
     *
     * @param apiRequest
     * @return
     */
    APIResponse<LoginCreateOrderResponse> creteOrderRequest(APIRequest<CreateOrderRequest> apiRequest);

    /**
     * 取消订单
     *
     * @param request
     * @return
     */
    APIResponse<OrderCancelResponse> orderCancel(APIRequest<OrderQueryRequest> request);

    /**
     * 订单查询
     *
     * @param request
     * @return
     */
    APIResponse<OrderQueryResponse> orderRechargeQuery(APIRequest<OrderQueryRequest> request);
}
