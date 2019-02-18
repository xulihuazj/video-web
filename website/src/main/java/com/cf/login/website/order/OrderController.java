package com.cf.login.website.order;

import com.cf.api.dto.app.order.OrderBatchQueryDTO;
import com.cf.api.enums.app.order.OrderStatusEnum;
import com.cf.api.request.APIRequest;
import com.cf.api.request.app.order.CreateOrderRequest;
import com.cf.api.request.app.order.OrderQueryRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.order.LoginCreateOrderResponse;
import com.cf.api.response.app.order.OrderCancelResponse;
import com.cf.api.response.app.order.OrderQueryResponse;
import com.cf.login.apiinterface.order.OrderInterface;
import com.cf.login.website.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Resource
    private OrderInterface orderInterface;

    /**
     * web订单下单
     *
     * @param bizRequest
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/payment/recharge")
    public APIResponse<LoginCreateOrderResponse> orderCreate(CreateOrderRequest bizRequest, HttpServletRequest httpServletRequest) {
//        bizRequest.setOrderBuinessType(OrderBuinessTypeEnum.RECHANGE);
        APIRequest<CreateOrderRequest> request = BaseController.getRequest(httpServletRequest, bizRequest);
        APIResponse<LoginCreateOrderResponse> apiResponse = this.orderInterface.creteOrderRequest(request);
        return apiResponse;
    }

    /**
     * web订单取消
     *
     * @param bizRequest
     * @param httpServletRequest
     * @return
     */
//    @MustLogin
    @PostMapping("/recharge/cancel")
    public APIResponse<OrderCancelResponse> orderRechargeCancel(OrderQueryRequest bizRequest, HttpServletRequest httpServletRequest) {
        APIRequest<OrderQueryRequest> request = BaseController.getRequest(httpServletRequest, bizRequest);
        APIResponse<OrderCancelResponse> apiResponse = this.orderInterface.orderCancel(request);
        return apiResponse;
    }


    /**
     * web订单查询
     *
     * @param bizRequest
     * @param httpServletRequest
     * @return
     */
//    @MustLogin
    @GetMapping("/recharge/query")
    public APIResponse<OrderQueryResponse> orderRechargeQuery(OrderQueryRequest bizRequest, HttpServletRequest httpServletRequest) {
        APIRequest<OrderQueryRequest> request = BaseController.getRequest(httpServletRequest, bizRequest);
        APIResponse<OrderQueryResponse> apiResponse = this.orderInterface.orderRechargeQuery(request);
        return apiResponse;
    }

    /**
     * web账单订单查询
     *
     * @param bizRequest
     * @param httpServletRequest
     * @return
     */
//    @MustLogin
    @GetMapping("/bill/query")
    @ResponseBody
    public String orderBillQuery(OrderQueryRequest bizRequest, HttpServletRequest httpServletRequest) {
        APIRequest<OrderQueryRequest> request = BaseController.getRequest(httpServletRequest, bizRequest);
        APIResponse<OrderQueryResponse> apiResponse = this.orderInterface.orderRechargeQuery(request);
        if(apiResponse.getBizResponse()!=null){
            for(OrderBatchQueryDTO order:apiResponse.getBizResponse().getOrderBatchQueryDTOS()){
                if(order.getOrderStatus().getCode().equalsIgnoreCase(OrderStatusEnum.SUCCESS.getCode())){
                    return OrderStatusEnum.SUCCESS.getCode();
                }
            }
            return OrderStatusEnum.WAITING_PAY.getCode();
        }
        return OrderStatusEnum.WAITING_PAY.getCode();
    }
}
