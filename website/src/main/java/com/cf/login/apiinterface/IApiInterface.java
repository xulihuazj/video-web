package com.cf.login.apiinterface;


import com.cf.api.request.APIRequest;
import com.cf.api.response.APIResponse;
import org.apache.http.HttpResponse;

/**
 * @author Administrator
 */
public interface IApiInterface {

    <T> APIResponse<T> sendPostJson(APIRequest api, String url, Class<T> t);
    //非 200 会抛出异常
    <T> APIResponse<T> sendPostJsonThrowEx(APIRequest api, String url, Class<T> t);

    <T> APIResponse<T> sendGetJson(APIRequest api, String url, Class<T> t);

    <T> APIResponse<T> sendGetJsonThrowEx(APIRequest api, String url, Class<T> t);
    
    HttpResponse sendGet(String url,String IP,String useAgent);
}
