package com.cf.login.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * 设置跨域环境
 * @author xulihua
 */
//@Conditional({OfflineEnvCondition.class,OnlineCondition.class})
@Configuration
public class CORSConfig {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1 允许任何域名使用
        corsConfiguration.addAllowedOrigin("*");
        // 2 允许任何头
        corsConfiguration.addAllowedHeader("*");
        // 3 允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 4
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

}
