package com.cf.login.website.home;

import com.cf.api.dto.app.smart.CheckInHistoryDTO;
import com.cf.api.request.APIRequest;
import com.cf.api.request.app.appoint.AppointRoomRequest;
import com.cf.api.request.app.smart.*;
import com.cf.api.request.room.ProjectsRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.house.AppHouseListResponse;
import com.cf.api.response.app.smart.ClientMessageAlertResponse;
import com.cf.api.response.app.smart.PaymentRecordListResponse;
import com.cf.api.response.app.smart.RoomHistoryListResponse;
import com.cf.api.response.app.smart.RoomRecordResponse;
import com.cf.api.response.project.ProjectsResponse;
import com.cf.api.response.room.AppointRoomResponse;
import com.cf.api.system.SystemType;
import com.cf.login.apiinterface.common.CommonApiInterface;
import com.cf.login.apiinterface.home.HomeInterface;
import com.cf.login.website.BaseController;
import com.cf.utils.validate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {

    @Resource
    private HomeInterface homeInterface;

    @Autowired
    private CommonApiInterface commonApiInterface;

    @SystemType
    @GetMapping("/projects")
    public APIResponse<ProjectsResponse> homeProjects(HttpServletRequest httpServletRequest) {
        ProjectsRequest bizRequest = new ProjectsRequest();
        APIRequest request = BaseController.getRequest(httpServletRequest, bizRequest);
        APIResponse<ProjectsResponse> apiResponse = homeInterface.homeProjects(request);
        return apiResponse;
    }

    /**
     * 我的房间-未办理入住
     *
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/not/check/in")
    public ModelAndView notCheckInHome(HttpServletRequest httpServletRequest) {
        APIRequest request = BaseController.getRequest(httpServletRequest, null);
        APIResponse<AppHouseListResponse> apiResponse = commonApiInterface.house(request);
        return new ModelAndView("home/not_checked_in_home").addObject("apiResponse", apiResponse)
                .addObject("userInfoDTO",BaseController.getUserInfo(httpServletRequest));
    }

    /**
     * 我的房间
     *
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/room/detail")
    public ModelAndView clientMessageAlert(HttpServletRequest httpServletRequest) {
        RoomRecordRequest bizRequest = new RoomRecordRequest();
        APIRequest<RoomRecordRequest> request = BaseController.getRequest(httpServletRequest, bizRequest);
        APIResponse<RoomRecordResponse> apiResponse = homeInterface.roomRecordDetail(request);
        if (apiResponse.getStatusCode().equals("20010008")) {
            APIResponse<AppHouseListResponse> response = commonApiInterface.house(request);
            return new ModelAndView("home/not_checked_in_home")
                    .addObject("apiResponse", response)
                    .addObject("userInfoDTO", BaseController.getUserInfo(httpServletRequest));
        } else if (apiResponse.getStatusCode().equals("20010028")) {
            RoomHistoryListRequest bizRequestHistory = new RoomHistoryListRequest();
            bizRequestHistory.setPageNum(1);
            bizRequestHistory.setPageSize(100);
            APIRequest<RoomHistoryListRequest> requestHistory = BaseController.getRequest(httpServletRequest, bizRequestHistory);
            APIResponse<RoomHistoryListResponse> response = homeInterface.roomHistoryList(requestHistory);
            CheckInHistoryDTO checkInHistoryDTO = null;
            if (response.getBizResponse() != null) {
                List<CheckInHistoryDTO> checkInHistorys = response.getBizResponse().getCheckInHistorys();
                checkInHistoryDTO = checkInHistorys.get(0);
            }
            APIResponse<AppHouseListResponse> houseResponse = commonApiInterface.house(request);
            return new ModelAndView("home/room_history_list")
                    .addObject("checkInHistoryDTO", checkInHistoryDTO)
                    .addObject("apiResponse", houseResponse)
                    .addObject("userInfoDTO", BaseController.getUserInfo(httpServletRequest));
        } else {
            ClientMessageAlertRequest messageRequest = new ClientMessageAlertRequest();
            messageRequest.setPageNum(1);
            messageRequest.setPageSize(100);
            APIRequest<ClientMessageAlertRequest> apiMessageRequest = BaseController.getRequest(httpServletRequest, messageRequest);
            APIResponse<ClientMessageAlertResponse> apimessageResponse = homeInterface.clientMessageAlert(apiMessageRequest);
            return new ModelAndView("home/myhome")
                    .addObject("roomRecordResponse", apiResponse)
                    .addObject("clientMessageAlertResponse", apimessageResponse)
                    .addObject("userInfoDTO", BaseController.getUserInfo(httpServletRequest));
        }
    }

    /**
     * 充值记录
     *
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/payment/record/list")
    public APIResponse<PaymentRecordListResponse> paymentBillRecordList(PaymentRecordListRequest bizRequest, HttpServletRequest httpServletRequest) {
        bizRequest.setPageNum(1);
        bizRequest.setPageSize(50);
        APIRequest<PaymentRecordListRequest> request = BaseController.getRequest(httpServletRequest, bizRequest);
        APIResponse<PaymentRecordListResponse> apiResponse = homeInterface.paymentBillRecordList(request);
        return apiResponse;
    }

    /**
     * 客户端-新消息提醒
     *
     * @param bizRequest
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/client/message")
    public ModelAndView clientMessageList(ClientMessageAlertRequest bizRequest, HttpServletRequest httpServletRequest) {
        bizRequest.setPageNum(1);
        bizRequest.setPageSize(100);
        APIRequest<ClientMessageAlertRequest> request = BaseController.getRequest(httpServletRequest, bizRequest);
        APIResponse<ClientMessageAlertResponse> apiResponse = homeInterface.clientMessageAlert(request);
        return new ModelAndView("home/myhome").addObject("clientMessageAlertResponse", apiResponse);
    }

    /**
     * 查看消息
     *
     * @param bizRequest
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/client/message/read")
    public String clientMessageRead(ClientMessageReadRequest bizRequest, HttpServletRequest httpServletRequest) {
        APIRequest<ClientMessageReadRequest> request = BaseController.getRequest(httpServletRequest, bizRequest);
        this.homeInterface.clientMessageRead(request);
        return "success";
    }

    /**
     * 预约看房
     * @param bizRequest
     * @param httpServletRequest
     * @return
     */
    @SystemType
    @PostMapping("/appoint/room")
    public APIResponse<AppointRoomResponse> appointRoom(AppointRoomRequest bizRequest, HttpServletRequest httpServletRequest) {
        APIRequest<AppointRoomRequest> request = BaseController.getRequest(httpServletRequest, bizRequest);
        APIResponse<AppointRoomResponse> apiResponse = this.homeInterface.appointRoom(request);
        return apiResponse;
    }

}
