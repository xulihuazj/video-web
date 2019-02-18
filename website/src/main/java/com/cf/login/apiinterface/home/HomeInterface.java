package com.cf.login.apiinterface.home;

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

public interface HomeInterface {

    /**
     * 查询项目与房型
     * @return
     */
    APIResponse<ProjectsResponse> homeProjects(APIRequest<ProjectsRequest> apiRequest );

    /**
     * 我的房间
     * @param apiRequest
     * @return
     */
    APIResponse<RoomRecordResponse> roomRecordDetail(APIRequest<RoomRecordRequest> apiRequest);

    /**
     * 房间历史
     * @return
     */
    APIResponse<RoomHistoryListResponse> roomHistoryList(APIRequest<RoomHistoryListRequest> request);

    /**
     * 充值记录
     * @param request
     * @return
     */
    APIResponse<PaymentRecordListResponse> paymentBillRecordList(APIRequest<PaymentRecordListRequest> request);

    /**
     * 客户端-新消息提醒
     * @param request
     * @return
     */
    APIResponse<ClientMessageAlertResponse> clientMessageAlert(APIRequest<ClientMessageAlertRequest> request);

    /**
     * 查看消息
     * @param request
     * @return
     */
    APIResponse<Void> clientMessageRead(APIRequest<ClientMessageReadRequest> request);

    /**
     * 预约看房
     * @param request
     * @return
     */
    APIResponse<AppointRoomResponse> appointRoom(APIRequest<AppointRoomRequest> request);



}
