package com.huawei;
import com.huawei.springboot.domain.pojo.BussinessPerson;
import com.huawei.springboot.interceptor.Intercepttor1;
import com.huawei.springboot.service.AdvancedEtcdService;
import com.huawei.springboot.service.EtcdService;
import com.huawei.springboot.service.impl.AdvancedEtcdServiceImpl;
import com.huawei.springboot.service.impl.EtcdServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
/**
 * Author：胡灯
 * Date：2019-08-17 9:26
 * Description：<描述>
 */
@SpringBootApplication
@ComponentScan(lazyInit = true)
@ImportResource(value = {"classpath:spring_other.xml"})
@PropertySource(value = {"classpath:config.properties"},ignoreResourceNotFound = true)
public class Daily_App
{
    private static final String IP = "192.168.1.120";

    public static final String endpoints = "http://" + IP + ":2379";


    public static void main(String[] args)
    {
       SpringApplication.run(Daily_App.class, args);
    }

    @Bean
    public EtcdService getEtcdService(){
        return new EtcdServiceImpl(endpoints);
    }

    @Bean
    public AdvancedEtcdService getAdvancedEtcdService(){
        return new AdvancedEtcdServiceImpl(endpoints);
    }

}
