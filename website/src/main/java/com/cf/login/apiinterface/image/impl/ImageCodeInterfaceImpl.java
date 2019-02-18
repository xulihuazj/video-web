package com.cf.login.apiinterface.image.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.user.CodeShowPCRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.user.CodeShowPCResponse;
import com.cf.login.apiinterface.IApiInterface;
import com.cf.login.apiinterface.image.ImageCodeInterface;
import com.cf.login.constant.APIUrlUtil;
import com.cf.utils.log.LogHelper;

@Service
public class ImageCodeInterfaceImpl implements ImageCodeInterface {
	@Resource
	IApiInterface iApiInterface;
	
	@Override
	public void getImageCode(OutputStream outPutS, String uuid, String IP, String useAgent) {
		HttpResponse httpResponse = this.iApiInterface.sendGet(APIUrlUtil.GET_IMAGE_CODE + uuid, IP, useAgent);
		BufferedReader br = null;
		InputStream inputStream = null;
		try {
			inputStream = httpResponse.getEntity().getContent();
			byte[] tempbytes = new byte[100];
			int byteread = 0;
			while ((byteread = inputStream.read(tempbytes)) != -1) {
				outPutS.write(tempbytes, 0, byteread);
			}
		} catch (Exception e) {
			LogHelper.error(e.getMessage());
		}finally {
			try {
				if(br != null){
					br.close();
				}
				if(inputStream != null){
					inputStream.close();
				}
			} catch (IOException e) {
				LogHelper.error(e);
			}
		}
	}

	@Override
	public APIResponse<CodeShowPCResponse> isImgCodeShow(APIRequest<CodeShowPCRequest> apiRequest) {
		return this.iApiInterface.sendGetJson(apiRequest, APIUrlUtil.CLIENT_USER_CODE_SHOW, CodeShowPCResponse.class);
	}
	
}
