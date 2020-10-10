/*
 * 	프로젝트명(Project): Spring JDBC를 활용한 CRUD 프로젝트
 *  파일명(Filename): LogAdvisor.java
 *  제작일자(Create Date): 2020-10-11
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  설명(Description):
 *  
 */

package com.website.example.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public interface LogAdvisor {

	public void beforeAdvice();
	public void afterAdvice();
	public void aroundAdvice();
	
}
