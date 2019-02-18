package com.cf.login.website.complaints;

import com.cf.api.dto.complaints.ComplaintsInfoDTO;
import com.cf.api.request.APIRequest;
import com.cf.api.request.complaints.ComplaintsDetailRequest;
import com.cf.api.request.complaints.ComplaintsListRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.complaints.ComplaintsListResponse;
import com.cf.login.apiinterface.complaints.ComplaintsApiInterface;
import com.cf.login.website.BaseController;
import com.cf.utils.Date.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/complaints")
public class ComplaintsController {
    @Resource
    ComplaintsApiInterface complaintsApiInterface;
    //    @MustLogin
    @GetMapping("/list")
    public ModelAndView complaintsList(ComplaintsListRequest listRequest, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {
        String complaintsId=null;
        if(listRequest!=null&& StringUtils.isNotBlank(listRequest.getComplaintsId())){
            complaintsId=listRequest.getComplaintsId();
        }
        //为空的话设置开始时间为3个月前,截止时间为当前时间
        if(StringUtils.isBlank(listRequest.getStartTime())){
            listRequest.setStartTime(DateUtils.format(DateUtils.getDateMonth(-3,new Date()),"yyyy-MM-dd"));
            listRequest.setEndTime(DateUtils.format(new Date(),"yyyy-MM-dd"));
        }
        listRequest.setPageNum(1);
        listRequest.setPageSize(10);
        APIRequest request = BaseController.getRequest(httpServletRequest, listRequest);
        APIResponse<ComplaintsListResponse> apiResponse = complaintsApiInterface.list(request);
        return new ModelAndView("complaints/complaintsList").addObject("complaintsId",complaintsId).addObject("response",apiResponse);
    }

    @GetMapping("/list/page")
    public ModelAndView complaintsListPage(ComplaintsListRequest listRequest, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {
        if(CollectionUtils.isEmpty(listRequest.getComplaintsStatusList())){
            listRequest.setComplaintsStatusList(null);
        }
        listRequest.setPageNum(listRequest.getPageNum());
        listRequest.setPageSize(10);
        //为空的话设置开始时间为3个月前,截止时间为当前时间
        if(StringUtils.isBlank(listRequest.getStartTime())){
            listRequest.setStartTime(DateUtils.format(DateUtils.getDateMonth(-3,new Date()),"yyyy-MM-dd"));
            listRequest.setEndTime(DateUtils.format(new Date(),"yyyy-MM-dd"));
        }
        APIRequest request = BaseController.getRequest(httpServletRequest, listRequest);
        APIResponse<ComplaintsListResponse> apiResponse = complaintsApiInterface.list(request);
        return new ModelAndView("complaints/complaintsList_p").
                addObject("response",apiResponse).addObject("request",listRequest);
    }



    /**
     * @Author: zjh
     * @Description: 投诉详情
     * @Date: 20:17 2018-10-15
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(ComplaintsDetailRequest detailRequest, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {
        APIRequest request = BaseController.getRequest(httpServletRequest, detailRequest);
        //获得投诉详情
        APIResponse<ComplaintsInfoDTO> apiResponse = complaintsApiInterface.detail(request);
        //获得投诉处理日志
        APIResponse<ComplaintsListResponse> logResponse=complaintsApiInterface.logList(request);
        return new ModelAndView("complaints/complaintaDetail").
                addObject("detailResponse",apiResponse).addObject("logResponse",logResponse);
    }



}

