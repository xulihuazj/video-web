package com.cf.login.apiinterface.home.impl;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.appoint.AppointRoomRequest;
import com.cf.api.request.app.smart.*;
import com.cf.api.request.room.ProjectsRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.smart.ClientMessageAlertResponse;
import com.cf.api.response.app.smart.PaymentRecordListResponse;
import com.cf.api.response.app.smart.RoomHistoryListResponse;
import com.cf.api.response.app.smart.RoomRecordResponse;
import com.cf.api.response.project.ProjectsResponse;
import com.cf.api.response.room.AppointRoomResponse;
import com.cf.login.apiinterface.IApiInterface;
import com.cf.login.apiinterface.home.HomeInterface;
import com.cf.login.constant.APIUrlUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HomeInterfaceImpl implements HomeInterface {

    @Resource
    private IApiInterface iApiInterface;

    @Override
    public APIResponse<ProjectsResponse> homeProjects(APIRequest<ProjectsRequest> apiRequest){
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_ROOM_PROJECTS, ProjectsResponse.class);
    }

    @Override
    public APIResponse<RoomRecordResponse> roomRecordDetail(APIRequest<RoomRecordRequest> apiRequest) {
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_ROOM_RECORD_DETAIL, RoomRecordResponse.class);
    }

    @Override
    public APIResponse<RoomHistoryListResponse> roomHistoryList(APIRequest<RoomHistoryListRequest> apiRequest){
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_ROOM_HISTORY_LIST, RoomHistoryListResponse.class);
    }

    @Override
    public APIResponse<PaymentRecordListResponse> paymentBillRecordList(APIRequest<PaymentRecordListRequest> apiRequest) {
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_SMART_PAYMENT_BILL_RECORD_LIST, PaymentRecordListResponse.class);
    }

    @Override
    public APIResponse<ClientMessageAlertResponse> clientMessageAlert(APIRequest<ClientMessageAlertRequest> apiRequest) {
        return iApiInterface.sendGetJson(apiRequest, APIUrlUtil.GET_CLIENT_MESSAGE_ALERT, ClientMessageAlertResponse.class);
    }

    @Override
    public APIResponse<Void> clientMessageRead(APIRequest<ClientMessageReadRequest> apiRequest) {
        return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.POST_CLIENT_MESSAGE_READ, Void.class);
    }

    @Override
    public APIResponse<AppointRoomResponse> appointRoom(APIRequest<AppointRoomRequest> apiRequest){
        return iApiInterface.sendPostJson(apiRequest, APIUrlUtil.POST_HOME_APPOINT_ROOM, AppointRoomResponse.class);
    }
}
