package com.huawei.springboot.aspect;
import com.huawei.springboot.domain.User;
import com.huawei.springboot.service.UserValidator;
import com.huawei.springboot.service.impl.UserValidatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * Author：胡灯
 * Date：2020-06-02 22:53
 * Description：<描述>
 */
@Component
@Aspect
public class MyAspect
{

   /* @DeclareParents(value = "com.huawei.springboot.service.impl.UserServiceImpl+",defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;*/

    @Pointcut("execution(* com.huawei.springboot.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut(){

    }
    @Before("pointCut()")
    public void before(){
        System.out.println("before....");
    }
    @After("pointCut()")
    public void after(){
        System.out.println("after....");
    }
    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("after returning....");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("after throwing...");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable
    {
        System.out.println("arount before");
        jp.proceed();
        System.out.println("around after");
    }
    @Before("pointCut()&&args(user)")
    public void beforParm(JoinPoint point, User user){
        Object[] args = point.getArgs();
        System.out.println("beforeParm...."+user);
    }

}
