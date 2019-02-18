package com.cf.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableAsync
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Configuration
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // 取消（默认启动security 需要验证权限）
            http.authorizeRequests()
                    .antMatchers("/**").permitAll().anyRequest().fullyAuthenticated()
                    .and()
                    .headers().frameOptions().disable()
            ;

            // 取消（默认启动csrf 不允许POST PUT DELETE）
            http.csrf().disable();
        }

    }

}
