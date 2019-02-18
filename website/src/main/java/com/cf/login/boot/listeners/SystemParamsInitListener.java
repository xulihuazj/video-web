package com.cf.login.boot.listeners;

import com.cf.utils.log.LogHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Enumeration;

@WebListener()
public class SystemParamsInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        Enumeration<String> enumeration = sc.getInitParameterNames();

        LogHelper.info("----------------------系统参数初始化开始----------------------");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String vaule = sc.getInitParameter(name);
            sc.setAttribute(name, vaule);
            LogHelper.info(name + ":" + vaule);
        }
        LogHelper.info("----------------------系统参数初始化结束----------------------");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
