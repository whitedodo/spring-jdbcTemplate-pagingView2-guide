/*
 * 	프로젝트명(Project): Spring JDBC를 활용한 CRUD 프로젝트
 *  파일명(Filename): FoodDAO.java
 *  제작일자(Create Date): 2020-10-11
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  설명(Description):
 *  
 */

package com.website.example.dao;

import java.sql.SQLException;
import java.util.List;

import com.website.example.model.FoodMenuVO;
import com.website.example.model.FoodMenuViewVO;

public interface FoodDAO {

	List<FoodMenuViewVO> getList(long start, long end);
	FoodMenuViewVO getFoodMenuView(long id);
	int getCount();
	void insertTest() throws SQLException;
	void deleteFoodMenu(FoodMenuVO vo);
	
}
