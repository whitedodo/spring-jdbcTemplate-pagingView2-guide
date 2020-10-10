/*
 * 	프로젝트명(Project): Spring JDBC를 활용한 CRUD 프로젝트
 *  파일명(Filename): AccountController.java
 *  제작일자(Create Date): 2020-10-09
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  설명(Description):
 *  
 *  1. 트랜젝션 직접 구현 (어노테이션 방식 아님)
 *  
 */

package com.website.example.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.website.example.common.MyDataSourceFactory;
import com.website.example.dao.AccountDAO;
import com.website.example.dao.AccountDAOSpring;
import com.website.example.model.AccountVO;
import com.website.example.service.AccountService;
import com.website.example.service.AccountServiceSpringJDBC;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/account")
public class AccountController {
	
	private MyDataSourceFactory sourceFactory = new MyDataSourceFactory();
	private DataSource ds = null;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value = "create.do", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res) {
		
		ModelAndView mav = new ModelAndView();
		ds = sourceFactory.getOracleDataSource();
		
		AccountDAO dao = new AccountDAOSpring(ds);
		AccountService service = new AccountServiceSpringJDBC(ds);
		AccountVO vo = new AccountVO();
		
		vo.setName("홍길자");
		vo.setBalance(10000);
		vo.setRegidate(Date.valueOf("2020-01-03"));
		
		//dao.createAccount(vo);
		/*
		 * 
		 */
		try {
			service.accountTransfer("홍길자", "홍길동", 500);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mav.setViewName("/account/create");
		
		return mav;
	}
	
}
