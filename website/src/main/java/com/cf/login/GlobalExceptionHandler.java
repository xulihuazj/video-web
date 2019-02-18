package com.cf.login;

import com.cf.api.response.APIResponse;
import com.cf.login.exception.BusinessException;
import com.cf.login.website.BaseController;
import com.cf.utils.log.LogHelper;
import com.cf.utils.web.WebHelper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * GlobalExceptionHandler
 *
 * @author liwei
 * @date 2017/3/29
 * @description 统一的异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public void errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        // 异常走错误流程 HttpStatus.EXPECTATION_FAILED
        APIResponse model = new APIResponse();
        HttpStatus httpStatus;
        if (e instanceof BusinessException) {
            LogHelper.error(logger, "GlobalExceptionHandler异常,errorcode={0}, message={1}", ((BusinessException) e).getErrorCode(), e.getMessage());
            model.setStatusCode(((BusinessException) e).getErrorCode());
            model.setMessage(e.getMessage());
            if (((BusinessException) e).getHttpStatus() != null) {
                try {
                    httpStatus = HttpStatus.valueOf(((BusinessException) e).getHttpStatus());
                } catch (Exception ex) {
                    httpStatus = HttpStatus.EXPECTATION_FAILED;
                }
            } else {
                //如果业务异常并且没有定义http状态吗 这里默认是200 尽量不要使用500 否则前端拿到这个状态码会认为是不允许跨域
                httpStatus = HttpStatus.OK;
            }
        } else {
            LogHelper.exception(e, logger, "发生运行时异常");
            if( e instanceof HttpRequestMethodNotSupportedException){
            	httpStatus = HttpStatus.FORBIDDEN;
            }else{
            	httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            model.setMessage("服务器处理错误");
            model.setStatusCode(httpStatus.toString());
        }

        if (WebHelper.isAjax(request)) {
            // 如果是ajax请求的403错误，这里使用二次跳转到login页
        	if(httpStatus.equals(HttpStatus.FORBIDDEN)){
        		response.sendRedirect(BaseController.getServerURL(request)+"/login/page");
        	}else{
        		APIResponse apiResponse = new APIResponse();
                apiResponse.setStatusCode("-10000");
                apiResponse.setMessage(e.getMessage());
//                if (((BusinessException) ex).getCode().equals(APIExceptionEnum.TOKEN_LOSE_EFFICACY.getCode())) {
//                    response.setStatus(org.apache.http.HttpStatus.SC_MOVED_PERMANENTLY);
//                }
                output(request, response, apiResponse);
        	}
            return;
        } else {
            if (httpStatus.value() == org.apache.http.HttpStatus.SC_FORBIDDEN) {
                response.sendRedirect(
                        BaseController.getServerURL(request) + "/login/page");
            } else {
                response.sendRedirect(BaseController.getServerURL(request)+"/404");
            }
        }
    }

    protected void output(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object object) {
        OutputStream ps = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            ps = httpResponse.getOutputStream();
            String type = httpRequest.getParameter("type");
            // 如果不需要简化输出,则输出所有
            ps.write(objectMapper.writeValueAsString(object).getBytes("UTF-8"));
        } catch (IOException e) {
            LogHelper.error(e.getMessage(), e);
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
