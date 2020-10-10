/*
 * 	프로젝트명(Project): Spring JDBC를 활용한 CRUD 프로젝트
 *  파일명(Filename): LogAdvisorJava.java
 *  제작일자(Create Date): 2020-10-11
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  설명(Description):
 *  
 */

package com.website.example.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

// 어노테이션 방식
@Aspect
public class LogAdvisorJava implements LogAdvisor{

	@Before("execution(* com.website.example.test.ResultAOPJava.*(..))")
	public void beforeAdvice() {
		System.out.println("전 단계");
	}

	@After("execution(* com.website.example.test.ResultAOPJava.*(..))")
	public void afterAdvice() {
		System.out.println("후 단계");
	}

	public void aroundAdvice() {
		
	}
	
}
