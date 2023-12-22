package spring.mvc.group_buy.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class LoggingAspect implements HandlerInterceptor{
	@Before("execution(* spring.mvc.group_buy.controller.*.*(..))")
	public void logMethodParams(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.printf("調用方法: %s%n 參數 %s%n", methodName, Arrays.toString(args)); 
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
