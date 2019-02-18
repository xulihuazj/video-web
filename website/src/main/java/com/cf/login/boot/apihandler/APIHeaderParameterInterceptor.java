package com.cf.login.boot.apihandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yinqiang
 * 针对api头参数信息拦截处理
 */
public class APIHeaderParameterInterceptor extends BaseInterceptor {

    @Override
    public boolean runHandler(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }

}
