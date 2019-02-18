package com.cf.login.apiinterface.complaints;

import com.cf.api.dto.complaints.ComplaintsInfoDTO;
import com.cf.api.request.APIRequest;
import com.cf.api.request.complaints.ComplaintsDetailRequest;
import com.cf.api.request.complaints.ComplaintsListRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.complaints.ComplaintsListResponse;
import com.cf.api.response.complaints.ComplaintsLogListResponse;

public interface ComplaintsApiInterface {

    /**
     * @Author: zhoujiahao
     * @Description: 投诉列表查询
     * @Date: 16:56 2018-08-27
     * @param request
     * @return
     */
    APIResponse<ComplaintsListResponse> list(APIRequest<ComplaintsListRequest> request);

    /**
     * @Author: zhoujiahao
     * @Description: 投诉详情查询
     * @Date: 16:56 2018-08-27
     * @param request
     * @return
     */
    APIResponse<ComplaintsInfoDTO> detail(APIRequest<ComplaintsDetailRequest> request);

    /**
     * @Author: zhoujiahao
     * @Description: 投诉操作日志
     * @Date: 16:56 2018-08-27
     * @param request
     * @return
     */
    APIResponse<ComplaintsLogListResponse> logList(APIRequest<ComplaintsDetailRequest> request);
}
