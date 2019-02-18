package com.cf.login.apiinterface.image;

import java.io.OutputStream;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.user.CodeShowPCRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.user.CodeShowPCResponse;

public interface ImageCodeInterface {
	
	/**
	 * 
	 * @Function: ImageCodeInterface.java
	 * @Description: 获取图片验证码
	 * @outPutS:图片输出流
	 * @uuid：全局唯一UUID
	 * @IP：IP地址
	 * @useAgent：客户端信息
	 * @return：void
	 * @author: mazy
	 * @date: 2018年10月10日 下午7:26:46
	 *
	 */
	void getImageCode(OutputStream outPutS,String uuid,String IP,String useAgent);
	
	APIResponse<CodeShowPCResponse> isImgCodeShow(APIRequest<CodeShowPCRequest> apiRequest);
}
