package com.huawei.springboot.config;
import com.huawei.springboot.interceptor.Intercepttor1;
import com.huawei.springboot.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import sun.font.TrueTypeFont;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
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
        registry.addInterceptor(new Intercepttor1()).addPathPatterns("/interceptor/*");
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/user/login","/api/user/register","/api/user/code/*");

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

    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

    @Bean("myTaskExecutor")
    public Executor myTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("myTask_");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
