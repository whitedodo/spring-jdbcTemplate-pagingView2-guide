/*
 * 	프로젝트명(Project): Spring JDBC를 활용한 CRUD 프로젝트
 *  파일명(Filename): AccountDAO.java
 *  제작일자(Create Date): 2020-10-11
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  설명(Description):
 *  
 */


package com.website.example.dao;

import java.sql.SQLException;

import com.website.example.model.AccountVO;

public interface AccountDAO {

	void createAccount(AccountVO vo);
    int getBalance(String name);
    void minus(String name, int money) throws SQLException;
    void plus(String name, int money) throws SQLException;
}
