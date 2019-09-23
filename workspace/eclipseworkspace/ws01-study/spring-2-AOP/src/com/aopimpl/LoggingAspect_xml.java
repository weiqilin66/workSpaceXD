package com.aopimpl;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class LoggingAspect_xml {
		//前置通知
		
		public void beforeMethod(JoinPoint joinpoint) {
			//joinpoint  lang包，访问连接方法的细节
			String methodName = joinpoint.getSignature().getName();
			List<Object> args = Arrays.asList(joinpoint.getArgs());
			System.out.println("beforeMethod "+ methodName +" arg[]:" +args);
		}
		
			//@After 后置通知
			//都会在方法结束后或者抛异常后执行
		
			public void afterMethod(JoinPoint joinpoint) {
				String methodName = joinpoint.getSignature().getName();
				System.out.println("Method "+methodName+" end");
			}
			
			//@AfterReturning 返回通知  ..
			
			public void AfterReturning(JoinPoint joinpoint,Object result) {
				String methodName = joinpoint.getSignature().getName();
				System.out.println("Method "+methodName+" return with "+ result);
			}
			
			//@AfterThrowing 异常通知  ..
			
			public void afterThowing(JoinPoint joinpoint,Exception ex) {
				String methodName = joinpoint.getSignature().getName();
				System.out.println("Method "+methodName+" throwing with" +ex);
			}
			
	
			
			
		
			
			public void declarePointCut() {}
			
	
			public Object Around(ProceedingJoinPoint pjd) {
				String methodName = pjd.getSignature().getName();
				Object res= null;
				
				try {
					//前置通知
					System.out.println("begins");
					//执行目标方法
					res = pjd.proceed();
					//返回通知
					System.out.println("return");
				} catch (Throwable e) {
					//异常通知
					e.printStackTrace();
				}
				//后置通知
				System.out.println("end");
				return res;
				
			}
}
