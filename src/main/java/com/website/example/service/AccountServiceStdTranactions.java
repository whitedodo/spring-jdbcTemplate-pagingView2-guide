/*
 * 	주제(Subject): Spring Framework 4.2 - JDBC Template 
 *  파일명(Filename): AccountServiceStdTransactions.java
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  제작일자(Create Date): 2020-10-11
 *  설명(Description):
 *  
 *  
 */

package com.website.example.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.website.example.dao.AccountDAO;
import com.website.example.dao.AccountDAOSpring;
import com.website.example.common.MyDataSourceFactory;

public class AccountServiceStdTranactions implements AccountService{

	private AccountDAO accountDAO;
	private DataSource ds = null;
	
	public AccountServiceStdTranactions(DataSource ds) {
		accountDAO = new AccountDAOSpring(ds);
		this.ds = ds;
	}
	
    public void accountTransfer(String sender, String receiver, int money) throws SQLException{
        
    	int balance = accountDAO.getBalance(sender); // 보내는 사람 잔액 체크
    	Connection conn = null;
    	
        if(balance >= money){ // 보내는 돈이 잔액보다 많으면

        	conn = ds.getConnection();
        	conn.setAutoCommit(false);
        	
        	try {
	        	accountDAO.minus(sender, money);
				accountDAO.plus(receiver, money);
	        	
				conn.commit();	// 성공
				
        	}catch(SQLException e) {
        		conn.rollback();	// 실패
        	}
            
        } else{

        	System.out.println("돈 없음");
        	//throw new NoMoneyException();
        }
        
    }
	
}
