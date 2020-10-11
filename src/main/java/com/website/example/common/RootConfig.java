/*
 * 	프로젝트명(Project): Spring JDBC를 활용한 CRUD 프로젝트
 *  파일명(Filename): RootConfig.java
 *  제작일자(Create Date): 2020-10-09
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  설명(Description):
 *  1. AOP 자바 환경설정 파일 , 도도(Dodo), 2020-10-11
 */

package com.website.example.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.website.example"})
//@ComponentScan(basePackages = {"com.local.example.beans", "com.local.example.advisor"})
@EnableAspectJAutoProxy
public class RootConfig {
	
}
