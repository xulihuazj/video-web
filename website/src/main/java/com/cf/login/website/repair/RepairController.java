package com.cf.login.website.repair;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.service.RepairDetailRequest;
import com.cf.api.request.repair.RepairListRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.service.ClientRepairListResponse;
import com.cf.api.response.app.service.RepairDetailResponse;
import com.cf.login.apiinterface.repair.RepairApiInterface;
import com.cf.login.website.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/repair")
public class RepairController extends BaseController {

    @Resource
    RepairApiInterface repairApiInterface;

    @GetMapping("/list")
    public ModelAndView MyCleanRepairList(RepairListRequest repairListRequest, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {
        Long repairId = null;
        if(repairListRequest != null && repairListRequest.getRepairId() != null){
            repairId = repairListRequest.getRepairId();
        }
        APIRequest request = BaseController.getRequest(httpServletRequest, repairListRequest);
        APIResponse<ClientRepairListResponse> apiResponse = repairApiInterface.list(request);
        return new ModelAndView("repair/repairList")
                .addObject("repairId",repairId)
                .addObject("response",apiResponse);
    }

    @GetMapping("/list/p")
    public ModelAndView MyCleanRepairListPage(RepairListRequest repairListRequest, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {
        repairListRequest.setPageSize(10);
        APIRequest request = BaseController.getRequest(httpServletRequest, repairListRequest);
        APIResponse<ClientRepairListResponse> apiResponse = repairApiInterface.list(request);
        return new ModelAndView("repair/repairList_p")
                .addObject("repairs", apiResponse)
                .addObject("request", repairListRequest)
                .addObject("response", apiResponse);
    }

    /**
     * @description: 维修保洁详情
     * @param:
     * @return:
     * @author: chenyl
     * @date: 2018/9/26 11:50
     **/
    @GetMapping(value = "/detail")
    public ModelAndView MyCleanDetail(RepairDetailRequest repairDetailRequest, HttpServletRequest httpServletRequest,
            HttpServletResponse
                    httpServletResponse) throws Exception {
        APIResponse<RepairDetailResponse> response = repairApiInterface
                .repairDetail(BaseController.getRequest(httpServletRequest, repairDetailRequest));
        return new ModelAndView("repair/repair_detail").addObject("detail", response.getBizResponse());
    }
}
