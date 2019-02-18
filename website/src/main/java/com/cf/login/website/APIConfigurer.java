package com.cf.login.website;

import com.cf.login.boot.apihandler.APIHandlerMethodArgumentResolver;
import com.cf.login.boot.auth.PrivilegeInterceptor;
import com.cf.login.boot.auth.UUIDInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * APIConfigurer
 *
 * @author liwei
 * @date 2017/3/30
 * @description
 */
@Configuration
public class APIConfigurer extends WebMvcConfigurationSupport {

    @Resource
    private PrivilegeInterceptor privilegeInterceptor;
    @Resource
    private UUIDInterceptor uUIDInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new FrequencyInterceptor()).addPathPatterns("*//**");
        registry.addInterceptor(privilegeInterceptor).addPathPatterns("/**").excludePathPatterns("/resources/**");
        registry.addInterceptor(uUIDInterceptor).addPathPatterns("/**").excludePathPatterns("/resources/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);

    }

    @Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        //设置中文编码格式
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        return mappingJackson2HttpMessageConverter;
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new APIHandlerMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new MyErrorPageRegistrar();
    }

    class MyErrorPageRegistrar implements ErrorPageRegistrar {

        @Override
        public void registerErrorPages(ErrorPageRegistry registry) {
            registry.addErrorPages(new ErrorPage("/404"));
        }

    }
}
