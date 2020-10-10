/*
 * 	주제(Subject): Spring Framework 4.2 - JDBC Template 
 *  파일명(Filename): FoodMenuViewRowMapper.java
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  제작일자(Create Date): 2020-10-11
 *  설명(Description):
 *  1. JDBC Template와 함께 사용됨.
 *  2. RowMapper<> interface는 Spring POM (JDBC)를 붙이면 나옴
 */

package com.website.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.website.example.model.FoodMenuVO;
import com.website.example.model.FoodMenuViewVO;

public class FoodMenuViewRowMapper implements RowMapper<FoodMenuViewVO>  {

	@Override
	public FoodMenuViewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FoodMenuViewVO vo = new FoodMenuViewVO();
		
		vo.setRnum(rs.getInt(1));
		vo.setId(rs.getInt(2));
		vo.setName(rs.getString(3));
		vo.setPrice(rs.getInt(4));
		vo.setStorename(rs.getString(5));
		vo.setRegidate(rs.getTimestamp(6));
		vo.setCnt(rs.getInt(7));
		
		return vo;
	}

}
