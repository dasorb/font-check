package com.nebula.font.check.configs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 对协议返回值进行处理
 *
 * @author chenjie
 * @date 2019/04/03
 **/
@Aspect     // 定义切面
@Component
public class ResponseAspect {

    @Pointcut("execution(* com.nebula.font.check.controller.*Controller.*(..))")
    public void execPointcut() {}

    /*@AfterReturning(pointcut = "execPointcut()", returning = "ret")
    public Object afterReturning(Object ret) throws Throwable {
        // 被拦截方法的返回值经过包装
        System.out.println("后置执行成功通知");
        ret = ResponseMessage.success(ret);
        return ResponseMessage.success(ret);
    }*/

    @Around("execPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (Exception e) {
            throw new ResponseException(e.getMessage());
        }
        return ResponseMessage.success(proceed);
    }

}
