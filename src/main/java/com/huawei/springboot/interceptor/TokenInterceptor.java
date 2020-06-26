package com.huawei.springboot.interceptor;
import com.huawei.springboot.exception.BusinessException;
import com.huawei.springboot.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Author：胡灯
 * Date：2020-06-26 13:37
 * Description：<描述>
 */
public class TokenInterceptor implements HandlerInterceptor
{
    @Autowired
    private RedisService redisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token))
        {
            throw new BusinessException(4001002,"用户凭证不能为空，请重新登录");
        }else {
            if (!redisService.hasKey(token))
            {
                throw new BusinessException(4001002,"用户凭证无效，请重新登录");
            }
            String userId = (String) redisService.get(token);
            if (redisService.hasKey(userId)&&!token.equals(redisService.get(userId)))
            {
                throw new BusinessException(4001002,"你的账号在异地登录,请重新登录");
            }

        }
        return true;
    }
}
