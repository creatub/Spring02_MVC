package com.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@EnableAspectJAutoProxy
public class LoggingAspect {
	@Pointcut("execution(public * com.shop..*Service.*(..))")
	private void loggingTarget() {
		//주의: 메서드는 void여야 함==>@Pointcut을 이용할 때
	}
	
	@Around("loggingTarget()")
	public Object trace(ProceedingJoinPoint jpoint) throws Throwable{
		Object[] arr=jpoint.getArgs();
		if(arr!=null) {
			for(Object args:arr) {
				System.out.print(args+",");
			}
			System.out.println();
		}
		String str=jpoint.getSignature().toLongString();
		System.out.println(str+"시작=========");
		long start=System.currentTimeMillis();
		try {
			Object result=jpoint.proceed();
			return result;
		}finally {
			long end=System.currentTimeMillis();
			System.out.println(str+"종료=========");
			System.out.println(str+"실행시간: "+(end-start)+"ms");
		}
	}
	
}
