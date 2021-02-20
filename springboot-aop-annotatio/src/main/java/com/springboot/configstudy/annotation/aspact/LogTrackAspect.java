package com.springboot.configstudy.annotation.aspact;

import com.alibaba.fastjson.JSON;
import com.springboot.configstudy.annotation.logRecord.LogTrack;
import com.springboot.configstudy.annotation.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * 切面配置类
 * @author Nuri
 * @CreateTime 2021/2/20
 * @Describe
 */
@Component
@Aspect
public class LogTrackAspect {
    private static final Logger log = LoggerFactory.getLogger(LogTrackAspect.class);

    /**
     * 配置切点：将自定义的注解作为切点
     */
    @Pointcut(value = "@annotation(com.springboot.configstudy.annotation.logRecord.LogTrack)")
    public void access(){

    }

    @Before("access()")
    public void before(JoinPoint joinPoint) throws Throwable{
        System.out.println("before增强 -aop 日志启动记录 - "+new Date());
    }

    /**
     * 环绕增强在before之前触发
     * @return
     */
    @Around("@annotation(logTrack)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, LogTrack logTrack) throws Throwable{
        System.out.println("环绕增强 - 日志环绕阶段 - "+new Date());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = IpUtil.getIpAddr(request);
        String logTrackValue = logTrack.value();
        Object[] pjpArray = proceedingJoinPoint.getArgs();
        if (pjpArray.length>1){
            ArrayList<Object> list = new ArrayList<>();
            for (Object obj : pjpArray){
                if (obj instanceof HttpServletRequest){
                    list.add("request");
                }else if(obj instanceof HttpServletResponse){
                    list.add("response");
                }else{
                    list.add(JSON.toJSON(obj));
                }
            }
            Signature signature = proceedingJoinPoint.getSignature();
            MethodSignature methodSignature =(MethodSignature)signature;
            String[] parameterNames = methodSignature.getParameterNames();
            System.out.println("参数名数组："+new ArrayList(Arrays.asList(parameterNames)));
            System.out.println("参数是:"+list.toString());
            System.out.println("LogTrackValue:"+logTrackValue);
            System.out.println("url:"+url);
            System.out.println("Ip:"+ip);
            return proceedingJoinPoint.proceed();
        }
        Object param = pjpArray[0];
        System.out.println("logTrackValue:"+logTrackValue);
        System.out.println("url:"+url);
        System.out.println("ip:"+ip);
        System.out.println("param:"+param.toString());
        return proceedingJoinPoint.proceed();
    }

    @After("access()")
    public void after() throws Throwable{
        System.out.println("后置增强  - 日志记录结束 - "+new Date());
    }
}
