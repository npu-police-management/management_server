package com.nwpu.managementserver.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/28 20:21
 * @email 3349495429@qq.com
 * @className com.nwpu.managementserver.aop.MyAop
 * @description:
 *      日志输出切片，记录请求访问了哪个控制器的哪个方法
 */
@Aspect
@Component
public class MyAop {
    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     切入点的为所有controller包的所有函数。
     *     只要进入其中的函数，就会打印进入该函数的信息，在出其中的函数，就会打印出该函数的信息
     */
    @Pointcut("execution(* com.nwpu.managementserver.controller..*.*(..))")
    public void where(){}

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     前置切片
     */
    @Before("where()")
    public void before(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        /*
          <p> 变量描述如下:
          @Description:
         *     方法名
         */
        String methodName = signature.getName();
        /*
          <p> 变量描述如下:
          @Description:
         *     类名
         */
        String className = signature.getDeclaringTypeName();
        System.out.println("======>进入"+className+"类的"+methodName+"方法");
    }

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     后置切片
     */
    @After("where()")
    public void after(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        /*
          <p> 变量描述如下:
          @Description:
         *     方法名
         */
        String methodName = signature.getName();
        /*
          <p> 变量描述如下:
          @Description:
         *     类名
         */
        String className = signature.getDeclaringTypeName();
        System.out.println("======>出"+className+"类的"+methodName+"方法");
    }
}
