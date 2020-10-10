/*
 * 	프로젝트명(Project): Spring JDBC를 활용한 CRUD 프로젝트
 *  파일명(Filename): HomeController.java
 *  제작일자(Create Date): 2020-10-11
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  설명(Description):
 *  
 *  1. 기본 페이지
 *  
 */

package com.website.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest req, HttpServletResponse res) {
		
		ModelAndView mav = new ModelAndView();		
		
		mav.setViewName("index");
		
		return mav;
	}
	
}
