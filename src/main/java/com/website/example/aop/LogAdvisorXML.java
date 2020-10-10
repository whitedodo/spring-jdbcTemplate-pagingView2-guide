/*
 * 	프로젝트명(Project): Spring JDBC를 활용한 CRUD 프로젝트
 *  파일명(Filename): LogAdvisorXML.java
 *  제작일자(Create Date): 2020-10-11
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  설명(Description):
 *  
 */

package com.website.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAdvisorXML {

	
	// before advice
	public void beforeMethod() {
		System.out.println("beforeMethod 호출");
	}
	
	// after advice
	public void afterMethod() {
		System.out.println("afterMethod 호출");
	}
	
	// around advice
	public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("aroundMethod 호출1");
		
		// 원래의 메소드를 호출한다.
		Object obj = pjp.proceed();
		
		System.out.println("aroundMethod 호출 2");
		
		return obj;
	}
	
	
	
}
