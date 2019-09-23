package com.aopimpl;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * 
 @aspect 指定为切面类,
 并且在applicationContext中声明 <aop:aspectj-autoproxy></aop:aspectj-autoproxy>才能生效
 @order 指定多个切面的顺序 越小越靠前执行
 @poincut 声明切入点表达式
 @Before前置通知
 @After后置通知 都会在方法结束后或者抛异常后执行,不能访问方法执行的结果--原理,方法可能异常,所以访问不到返回值
 @AfterReturning 返回通知
 @AfterThrowing 异常通知
 
 */
@Order(1)
@Aspect
@Component
public class LoggingAspect {
	//前置通知
	@Before("execution(public int com.spring.aop.helloworld.ArithmeticCalculator.*(int , int))")
	public void beforeMethod(JoinPoint joinpoint) {
		//joinpoint  lang包，访问连接方法的细节
		String methodName = joinpoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		System.out.println("beforeMethod "+ methodName +" arg[]:" +args);
	}
	
		//@After 后置通知
		//都会在方法结束后或者抛异常后执行
		//不能访问方法执行的结果--原理,方法可能异常,所以访问不到返回值
		
		@After("execution(*  com.spring.aop.helloworld.*.*(..))")
		public void afterMethod(JoinPoint joinpoint) {
			String methodName = joinpoint.getSignature().getName();
			System.out.println("Method "+methodName+" end");
		}
		
		//@AfterReturning 返回通知  ..
		//在方法正常结束后执行的代码
		@AfterReturning(value ="execution(* com.spring.aop.helloworld.*.*(..))",returning = "result")
		public void AfterReturning(JoinPoint joinpoint,Object result) {
			String methodName = joinpoint.getSignature().getName();
			System.out.println("Method "+methodName+" return with "+ result);
		}
		
		//@AfterThrowing 异常通知  ..
		//可以访问到异常对象,并且可以指定异常 再执行该方法,例如把exception改成 nullpointexception  则只有空指针异常才执行
		@AfterThrowing(value ="execution(* com.spring.aop.helloworld.*.*(..))",throwing="ex")
		public void afterThowing(JoinPoint joinpoint,Exception ex) {
			String methodName = joinpoint.getSignature().getName();
			System.out.println("Method "+methodName+" throwing with" +ex);
		}
		
		
		
		
		
		/**
		 *  声明表达式方法 不用再写execution()....
		 *  
		 */
		@Pointcut("execution(*  com.spring.aop.helloworld.*.*(..))")
		public void declarePointCut() {}
		
		
		/**
		 * @Around 环绕通知
		 * 需要携带proceedingJoinPoint 参数 可以决定是否执行目标方法
		 * 且环绕通知必须有目标方法的返回值
		 * 类似于动态代理
		 * 并不常用
		*/
		@Around("declarePointCut()")
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

