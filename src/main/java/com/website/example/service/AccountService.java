/*
 * 	주제(Subject): Spring Framework 4.2 - JDBC Template 
 *  파일명(Filename): AccountService.java
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  제작일자(Create Date): 2020-10-11
 *  설명(Description):
 *  
 *  
 */

package com.website.example.service;

import java.sql.SQLException;

public interface AccountService {

	void accountTransfer(String sender, String receiver, int money) throws SQLException;
	
}
