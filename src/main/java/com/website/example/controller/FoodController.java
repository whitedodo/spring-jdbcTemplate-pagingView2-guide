/*
 * 	프로젝트명(Project): Spring JDBC를 활용한 CRUD 프로젝트
 *  파일명(Filename): FoodController.java
 *  제작일자(Create Date): 2020-10-11
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  설명(Description):
 *  1. 목록과 조회 구현, Dodo(도도), 2020-10-11
 *  2. 페이징 처리 구현, Dodo(도도), 2020-10-
 */

package com.website.example.controller;

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
import com.website.example.dao.FoodDAO;
import com.website.example.dao.FoodMenuDAOSpring;
import com.website.example.logic.Paging;
import com.website.example.model.FoodMenuVO;
import com.website.example.model.FoodMenuViewVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/food")
public class FoodController {
	
	private static final Logger logger = LoggerFactory.getLogger(FoodController.class);
	
	@RequestMapping(value = "list.do", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		
		ModelAndView mav = new ModelAndView();

		MyDataSourceFactory sourceFactory = new MyDataSourceFactory();
		DataSource ds = sourceFactory.getOracleDataSource();
		FoodDAO dao = new FoodMenuDAOSpring(ds);
		
		List<FoodMenuViewVO> listVO = null;
		
		// dao.insertTest(); 							// 쓰레기용 삽입 코드
		
		long currentPage = 1;							// 기본값
		long pageSize = 10;
		long totalCount = dao.getCount();
		long startNum, endNum;
		
		String pageUrl = "list.do?";
		
		if ( req.getParameter("page") != null )
			currentPage = Long.valueOf( req.getParameter("page") );
		
        Paging paging = new Paging();
        
        paging.setPageNo(currentPage);
        paging.setPageSize(pageSize);
        paging.setTotalCount(totalCount);
        
        System.out.println("reqParameter(page):" + req.getParameter("page"));
        
        System.out.println("현재페이지번호:" + currentPage);
        System.out.println("페이지크기:" + pageSize);
       
        // totalCount 호출시 생성됨.
        startNum = paging.getDbStartNum();
        endNum = paging.getDbEndNum();

        System.out.println("시작번호:" + startNum);
        System.out.println("종료번호:" + endNum);
        
        listVO = dao.getList(startNum, endNum);

        mav.addObject("paging", paging);
		mav.addObject("foodMenuList", listVO);
		mav.addObject("pagingUrl", pageUrl);
		
		mav.setViewName("/food/list");
		
		return mav;
	}
	
	@RequestMapping(value = "view.do", method = RequestMethod.GET)
	public ModelAndView view(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		
		ModelAndView mav = new ModelAndView();

		MyDataSourceFactory sourceFactory = new MyDataSourceFactory();
		DataSource ds = sourceFactory.getOracleDataSource();
		FoodDAO dao = new FoodMenuDAOSpring(ds);
		
		long id = 0;
		
		if (req.getParameter("id") != null) {
			id = Long.valueOf( req.getParameter("id") );
		}
		
		FoodMenuViewVO view = dao.getFoodMenuView(id);
		
		mav.addObject("foodMenu", view);
		mav.setViewName("/food/view");
		
		return mav;
	}
	
}
