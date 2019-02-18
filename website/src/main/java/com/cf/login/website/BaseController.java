package com.cf.login.website;

import com.alibaba.fastjson.JSONObject;
import com.cf.api.dto.app.user.UserInfoDTO;
import com.cf.api.enums.common.Source;
import com.cf.api.request.APIRequest;
import com.cf.api.response.app.user.UserLoginResponse;
import com.cf.login.constant.KeyName;
import com.cf.utils.log.LogHelper;
import com.cf.utils.security.SecurityHelper;
import com.cf.utils.web.WebHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BaseController是所有Controller的基类,用于一些统一/公共部分的处理
 *
 * @author mazy
 * @description 所有Controller的基类
 */

public abstract class BaseController {
	@SuppressWarnings("all")
    public static <C> APIRequest<C> getRequest(HttpServletRequest httpRequest, C bizRequest) {
    	UserLoginResponse userResponse = getUserResponse(httpRequest);
    	APIRequest apiRequest = new APIRequest();
    	if(userResponse != null && userResponse.getUserInfoDTO() != null){
    		 apiRequest.setUserId(Long.parseLong(userResponse.getUserInfoDTO().getUserId()));
    		 apiRequest.setToken(userResponse.getToken());
    	}
        apiRequest.setDeviceId(getUuid(httpRequest));
        apiRequest.setDtMonitor(WebHelper.getUseAgent(httpRequest));
        apiRequest.setSource(Source.WEB);
        apiRequest.setVersion("1.0.0");
        apiRequest.setBizRequest(bizRequest);
        return apiRequest;
    }

    public static String getServerURL(HttpServletRequest httpRequest) {
    	String port = "";
    	if(StringUtils.isNotBlank(httpRequest.getServerPort()+"")){
    		port = ":"+httpRequest.getServerPort()+"";
    	}
    	String path = httpRequest.getScheme() + "://" + httpRequest.getServerName() + port+httpRequest.getContextPath();
		path = path.substring(path.length()-1, path.length()).equals("/")?path.substring(0, path.length()-1):path;
		return path;
    }
    
    public static String getUuid(HttpServletRequest httpRequest){
    	String uuid = WebHelper.getCookie(httpRequest, KeyName.UUID_KEY_NAME);
    	return uuid;
    }
    
    public static UserInfoDTO getUserInfo(HttpServletRequest httpRequest){
    	UserInfoDTO userInfo = null; 
		try {
			String sign = WebHelper.getCookie(httpRequest, KeyName.INFO_KEY_NAME);
			if(StringUtils.isNotBlank(sign)){
				String strUserInfo = SecurityHelper.desDecrypt(sign, KeyName.DES_KEY_NAME);
				if(StringUtils.isNotBlank(strUserInfo)){
					UserLoginResponse userResponse = JSONObject.parseObject(strUserInfo, UserLoginResponse.class);
					userInfo = userResponse.getUserInfoDTO();
				}
			}
		} catch (Exception e) {
			LogHelper.error(e);
		}
		return userInfo;
    }
    
	public static void setUserInfo(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			UserLoginResponse info) {
		try {
			if(info != null){
				String strInfo = JSONObject.toJSONString(info);
				String sign = SecurityHelper.desEncrypt(strInfo, KeyName.DES_KEY_NAME);
				// 登录成功后将登录信息写入cookie中
				WebHelper.setCookie(httpResponse, KeyName.INFO_KEY_NAME, sign, KeyName.COOKIE_TIME);
			}
		} catch (Exception e) {
			LogHelper.error(e);
		}
	}
    
    private static UserLoginResponse getUserResponse(HttpServletRequest httpRequest){
    	UserLoginResponse userResponse = null;
    	try {
			String sign = WebHelper.getCookie(httpRequest, KeyName.INFO_KEY_NAME);
			if(StringUtils.isNotBlank(sign)){
				String strUserInfo = SecurityHelper.desDecrypt(sign, KeyName.DES_KEY_NAME);
				if(StringUtils.isNotBlank(strUserInfo)){
					userResponse = JSONObject.parseObject(strUserInfo, UserLoginResponse.class);
				}
			}
		} catch (Exception e) {
			LogHelper.error(e);
		}
    	return userResponse;
    }
    
    public static void SetBackurl(String backurl,HttpServletResponse response){
    	try {
			backurl = SecurityHelper.desEncrypt(backurl, KeyName.DES_KEY_NAME);
		} catch (Exception e) {
			LogHelper.error(e);
		}
    	WebHelper.setCookie(response, KeyName.LOGIN_BACKURL_NAME, backurl, KeyName.COOKIE_TIME);
    }
    
    public static String getBackurl(HttpServletRequest httpRequest){
    	String backurl = WebHelper.getCookie(httpRequest, KeyName.LOGIN_BACKURL_NAME);
    	try {
    		if(StringUtils.isNotBlank(backurl)){
    			backurl = SecurityHelper.desDecrypt(backurl, KeyName.DES_KEY_NAME);
    			if(backurl.contains("login/")){
    				backurl = getServerURL(httpRequest)+"/home/room/detail";
    			}
    		}else{
    			backurl = getServerURL(httpRequest)+"/home/room/detail";
    		}
		} catch (Exception e) {
			LogHelper.error(e);
		}
    	return backurl;
    }
    
    public static void removeBackurl(HttpServletResponse response){
    	try {
			WebHelper.delCookie(response, KeyName.LOGIN_BACKURL_NAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogHelper.error(e);
		}
    }
}
