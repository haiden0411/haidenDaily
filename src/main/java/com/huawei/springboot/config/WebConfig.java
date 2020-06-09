package com.huawei.springboot.config;
import com.huawei.springboot.interceptor.Intercepttor1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
/**
 * Author：胡灯
 * Date：2020-06-06 20:58
 * Description：<描述>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer
{
    private LocaleChangeInterceptor lci = null;
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        InterceptorRegistration ir = registry.addInterceptor(new Intercepttor1());
        ir.addPathPatterns("/interceptor/*");
        registry.addInterceptor(localeChangeInterceptor());

    }
    @Bean(name="localeResolver")
    public LocaleResolver initLocaleResolver(){
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        if (lci!=null)
        {
            return lci;
        }
        lci = new LocaleChangeInterceptor();
        lci.setParamName("language");
        return lci;
    }
}
