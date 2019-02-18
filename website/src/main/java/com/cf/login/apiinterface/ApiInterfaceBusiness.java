package com.cf.login.apiinterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cf.api.request.APIRequest;
import com.cf.api.response.APIResponse;
import com.cf.login.exception.BusinessException;
import com.cf.utils.log.LogHelper;
import com.cf.utils.net.IP;
import com.cf.utils.security.SecurityHelper;
import com.cf.utils.web.WebHelper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * @author Administrator
 */
@Service
public class ApiInterfaceBusiness implements IApiInterface {

    private static final int TOKEN_LOSE_EFFICACY = -1;

    @Value("${apiHost}")
    String API_HOST;

    @Value("${debug}")
    Boolean debug;

    ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private static final String HTTP = "http";

    private static final String HTTPS = "https";

    private static HttpClientConnectionManager httpClientConnectionManager = null;

    static {
        try {

            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, (X509Certificate[] arg0, String arg1) -> true).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" },
                    null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Registry registry = RegistryBuilder.create().register(HTTP, PlainConnectionSocketFactory.INSTANCE).register(HTTPS, sslsf).build();
            httpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);

            ((PoolingHttpClientConnectionManager) httpClientConnectionManager).setMaxTotal(50);
            ((PoolingHttpClientConnectionManager) httpClientConnectionManager).setDefaultMaxPerRoute(25);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个http连接
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setConnectionManager(httpClientConnectionManager).build();
    }

    private void setHeader(APIRequest api, HttpRequestBase httpRequestBase) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        httpRequestBase.setConfig(RequestConfig.custom().setConnectTimeout(60000).build());
        httpRequestBase.addHeader("content-type", "application/json;charset=utf-8");
        httpRequestBase.addHeader("x-Forwarded-For", IP.getIpAddress(request));
        httpRequestBase.addHeader("user-agent", WebHelper.getUseAgent(request));

        if (StringUtils.isNotEmpty(api.getToken())) {
            httpRequestBase.addHeader("Authorization", api.getToken());
        }
        // 幂等requestId 防止多次重复请求
        // UUID-SHA1(request(json stirng))
        httpRequestBase.addHeader("requestId", UUID.randomUUID().toString() + "-" + SecurityHelper.SHA1(JSON.toJSONString(api)));
    }

    /**
     * 发送消息
     *
     * @param api
     * @param url
     * @return
     */
    private String sendPostJsonStr(APIRequest api, String url, Boolean throwBl) {

        //Log.error("用户Authorization:"+api.getAuthorization()+"访问路径为:"+url);

        HttpResponse response;
        HttpClient httpClient;
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(API_HOST + url);
            this.setHeader(api, httpPost);

            String entity = objectMapper.writeValueAsString(api);
            if (debug) {
                LogHelper.info("entity: " + entity);
            }
            StringEntity se = new StringEntity(entity, Charset.forName("UTF-8"));
            httpPost.setEntity(se);
            httpClient = getHttpClient();
            response = httpClient.execute(httpPost);
            return printResponse(response, throwBl);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            LogHelper.error(e);
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return null;
    }

    /**
     * 发送数据并接收返回结果
     *
     * @param api
     * @param url
     * @return
     */
    @Override
    public <T> APIResponse<T> sendPostJson(APIRequest api, String url, Class<T> t) {
        return this.sendPostJsonThrowEx(api, url, false, t);
    }

    @Override
    public <T> APIResponse sendPostJsonThrowEx(APIRequest api, String url, Class<T> t) {
        return this.sendPostJsonThrowEx(api, url, true, t);
    }

    private <T> APIResponse sendPostJsonThrowEx(APIRequest api, String url, Boolean throwBl, Class<T> t) {
        APIResponse APIResponse = new APIResponse<>();
        APIResponse.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR + "");
        APIResponse.setMessage("系统异常");
        String jsonResp = sendPostJsonStr(api, url, throwBl);
        if (StringUtils.isNotBlank(jsonResp)) {
            APIResponse = JSON.parseObject(jsonResp, new TypeReference<APIResponse<T>>(t) {
            });
        }
        if (throwBl && !APIResponse.getStatusCode().equals(org.apache.http.HttpStatus.SC_OK + "")) {
            throw new BusinessException(APIResponse.getMessage());
        }
        return APIResponse;
    }

    private String printResponse(HttpResponse httpResponse, boolean throwEx) throws ParseException, IOException {
        // 获取响应消息实体
        if (httpResponse.getStatusLine().getStatusCode() == org.apache.http.HttpStatus.SC_OK) {
            HttpEntity entity = httpResponse.getEntity();
            // 响应状态
            // 判断响应实体是否为空
            if (entity != null) {
                String responseString = EntityUtils.toString(entity, Charset.forName("UTF-8"));
                if (debug) {
                    LogHelper.info("response content:" + responseString.replace("\r\n", ""));
                }
                return responseString;
            }
        } else if (httpResponse.getStatusLine().getStatusCode() == org.apache.http.HttpStatus.SC_FORBIDDEN) {

            if (true) {
                throw new BusinessException(HttpStatus.SC_FORBIDDEN, TOKEN_LOSE_EFFICACY, "需要重新登录 token 已失效");
            }

//            HttpEntity entity = httpResponse.getEntity();
//            // 响应状态
//            // 判断响应实体是否为空
//            if (entity != null) {
//                String responseString = EntityUtils.toString(entity, Charset.forName("UTF-8"));
//                if (debug) {
//                    LogHelper.info("response content:" + responseString.replace("\r\n", ""));
//                }
//                return responseString;
//            }

        }
        return null;
    }

    /**
     * 发送消息
     * get请求
     *
     * @param api
     * @param url
     * @return
     */
    private String sendGetJsonStr(APIRequest<?> api, String url, Boolean throwBl) {
        //Log.error("用户Authorization:"+api.getAuthorization()+"访问路径为:"+url);
        HttpResponse response;
        HttpClient httpClient;
        HttpGet httpGet = null;

        StringBuffer sb = new StringBuffer(API_HOST).append(url).append("?");

        try {

            JSONObject params = new JSONObject();
            if (api.getBizRequest() != null) {
                Object object = api.getBizRequest();
                params.putAll((JSONObject) JSONObject.toJSON(object));
            }
            params.put("deviceId", api.getDeviceId());
            params.put("dtMonitor", api.getDtMonitor());
            params.put("source", api.getSource().getCode());
            params.put("version", api.getVersion());

            int count = 0;
            for (Iterator<Map.Entry<String, Object>> iter = params.entrySet().iterator(); iter.hasNext(); ) {
                count++;
                Map.Entry<String, Object> entry = iter.next();
                try {
                    if (count == 1) {
                        if (entry.getValue() instanceof JSONArray) {
                            JSONArray jsonArray = (JSONArray) entry.getValue();
                            if(jsonArray != null && jsonArray.size() > 0){
                            	// 这里只处理非对象的数据
                                for (int i = 0; i < jsonArray.size(); i++) {
                                	if(jsonArray.get(i) != null){
                                		sb.append("&").append(entry.getKey()).append("=")
                                        .append(entry.getValue() == null ? "" : URLEncoder.encode(jsonArray.get(i).toString(), "UTF-8"));
                                	}
    							}
                            }
                        } else {
                            if (entry.getValue() != null) {
                                sb.append(entry.getKey()).append("=")
                                        .append(entry.getValue() == null ? "" : URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                            }
                        }
                    } else {
                        if (entry.getValue() instanceof JSONArray) {
                            JSONArray jsonArray = (JSONArray) entry.getValue();
//                            String jsonString = jsonArray.toJSONString();
//                            jsonString = jsonString.substring(1, jsonString.length() - 1).replace("\"", "");
                            if(jsonArray != null && jsonArray.size() > 0){
                            	// 这里只处理非对象的数据
                                for (int i = 0; i < jsonArray.size(); i++) {
                                	if(jsonArray.get(i) != null){
                                		sb.append("&").append(entry.getKey()).append("=")
                                        .append(entry.getValue() == null ? "" : URLEncoder.encode(jsonArray.get(i).toString(), "UTF-8"));
                                	}
    							}
                            }
                        } else {
                            if (entry.getValue() != null) {
                                sb.append("&")
                                        .append(entry.getKey()).append("=")
                                        .append(entry.getValue() == null ? "" : URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (debug) {
                LogHelper.info(sb.toString());
            }
            httpGet = new HttpGet(sb.toString());
            this.setHeader(api, httpGet);

            httpClient = getHttpClient();
            response = httpClient.execute(httpGet);
            return printResponse(response, throwBl);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            LogHelper.error(e);
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }
        return null;
    }

    @Override
    public <T> APIResponse sendGetJson(APIRequest api, String url, Class<T> t) {
        return this.sendGetJsonThrowEx(api, url, false, t);
    }

    @Override
    public <T> APIResponse sendGetJsonThrowEx(APIRequest api, String url, Class<T> t) {
        return this.sendGetJsonThrowEx(api, url, true, t);
    }

    private <T> APIResponse sendGetJsonThrowEx(APIRequest api, String url, Boolean throwBl, Class<T> t) {
        APIResponse<T> apiResponse = new APIResponse<>();
        apiResponse.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR + "");
        apiResponse.setMessage("系统异常");
        String jsonResp = sendGetJsonStr(api, url, throwBl);
        if (StringUtils.isNotBlank(jsonResp)) {
            Class c = t.getClass();
            apiResponse = JSON.parseObject(jsonResp, new TypeReference<APIResponse<T>>(t) {
            });
        }

        // 500
        if (apiResponse.getStatusCode().equals(HttpStatus.SC_INTERNAL_SERVER_ERROR + "")) {
            throw new BusinessException(apiResponse.getMessage());
        }

        // 其他错误
        if (throwBl && !apiResponse.getStatusCode().equals(org.apache.http.HttpStatus.SC_OK + "")) {
            throw new BusinessException(apiResponse.getMessage());
        }
        return apiResponse;
    }

	@Override
	public HttpResponse sendGet(String url,String IP,String useAgent) {
		HttpGet httpGet = new HttpGet(API_HOST + url);
		httpGet.setConfig(RequestConfig.custom().setConnectTimeout(60000).build());
		httpGet.addHeader("content-type", "application/json;charset=utf-8");
		httpGet.addHeader("x-Forwarded-For", IP);
		httpGet.addHeader("user-agent", useAgent);

		HttpClient httpClient = getHttpClient();
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (IOException e) {
			throw new BusinessException(e.getMessage());
		}
		return response;
	}

}
