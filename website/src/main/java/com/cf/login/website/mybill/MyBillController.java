package com.cf.login.website.mybill;

import com.cf.api.request.APIRequest;
import com.cf.api.request.finance.BillTypeRequest;
import com.cf.api.request.finance.MyBillDetailRequest;
import com.cf.api.request.finance.MyBillRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.finance.BillTypeResponse;
import com.cf.api.response.finance.MyBillListResponse;
import com.cf.api.response.finance.MyBillResponse;
import com.cf.api.system.SystemType;
import com.cf.login.apiinterface.mybill.MyBillInterface;
import com.cf.login.website.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description : 我的账单
 * @Author : zsy
 * @Date : 2018年10月12日 上午11:45:23
 */
@Controller
@RequestMapping("/my/bill")
public class MyBillController extends BaseController {

    @Resource
    MyBillInterface myBillInterface;
    
    @GetMapping("/billtype")
    @ResponseBody
    public APIResponse<BillTypeResponse> queryBilltype(BillTypeRequest billTypeRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        APIRequest<BillTypeRequest> request = BaseController.getRequest(httpServletRequest, billTypeRequest);
        APIResponse<BillTypeResponse> response = myBillInterface.queryBillType(request);
        return response;
    }

    @GetMapping("/list")
    public ModelAndView myBillList(MyBillRequest myBillRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Long billId=null;
        if(null != myBillRequest && null != myBillRequest.getBillId()){
            billId=myBillRequest.getBillId();
        }
        return new ModelAndView("mybill/mybill").addObject("billId", billId);
    }
    
    @GetMapping("/list_p")
    public ModelAndView myBillListPage(MyBillRequest myBillRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView mw = new ModelAndView("mybill/mybill_p");
        myBillRequest.setPageSize(10);
        APIRequest<MyBillRequest> request = BaseController.getRequest(httpServletRequest, myBillRequest);
        APIResponse<MyBillListResponse> apiResponse = myBillInterface.list(request);
        mw.addObject("apiResponse", apiResponse.getBizResponse());
        mw.addObject("request", myBillRequest);
        mw.addObject("response", apiResponse);
        return mw;
    }
    
    @GetMapping("/detail")
    public ModelAndView myBillDetail(MyBillDetailRequest myBillDetailRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView mw = new ModelAndView("mybill/mybillDetail");
        APIRequest<MyBillDetailRequest> request = BaseController.getRequest(httpServletRequest, myBillDetailRequest);
        APIResponse<MyBillResponse> response = myBillInterface.myBillDetail(request);
        mw.addObject("apiRresponse", response);
        return mw;
    }

    @GetMapping("/test")
    @SystemType
    public ModelAndView test() {
        ModelAndView mw = new ModelAndView("mybill/test");
        return mw;
    }
}
