package com.cf.login.website.image;


import com.alibaba.fastjson.JSONObject;
import com.cf.api.request.APIRequest;
import com.cf.api.request.app.user.CodeShowPCRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.user.CodeShowPCResponse;
import com.cf.api.system.SystemType;
import com.cf.login.apiinterface.image.ImageCodeInterface;
import com.cf.login.website.BaseController;
import com.cf.utils.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/image")
public class ImageCodeController extends BaseController {
	
	@Resource
	ImageCodeInterface imageCodeInterface;
	
	@GetMapping("/code")
	@SystemType
	public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 取出UUID
		String uuid = super.getUuid(request);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        // 指定生成的响应是图片
        response.setContentType("image/gif");
		imageCodeInterface.getImageCode(response.getOutputStream(), uuid, WebHelper.getRemoteAddr(),
				WebHelper.getUseAgent(request));
	}
	
	@GetMapping("/code/show")
	@ResponseBody
	@SystemType
	public JSONObject isImgCodeShow(String mobile, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		CodeShowPCRequest bizRequest = new CodeShowPCRequest();
		bizRequest.setAccount(mobile);
		APIRequest<CodeShowPCRequest> apiRequest = super.getRequest(request, bizRequest);
		APIResponse<CodeShowPCResponse> apiResponse = this.imageCodeInterface.isImgCodeShow(apiRequest);
		if (apiResponse != null && "SUCCESS".equals(apiResponse.getStatusCode())) {
			CodeShowPCResponse bizResponse = apiResponse.getBizResponse();
			result.put("code", bizResponse.getCodeShow());
		} else {
			result.put("code", "SHOW");
		}
		return result;
	}
}
