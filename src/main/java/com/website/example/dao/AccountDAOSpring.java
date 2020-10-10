/*
 * 	프로젝트명(Project): Spring JDBC를 활용한 CRUD 프로젝트
 *  파일명(Filename): AccountDAOSpring.java
 *  제작일자(Create Date): 2020-10-11
 *  저자(Author): 도도(Dodo) / rabbit.white at daum dot net
 *  설명(Description):
 *  
 */

package com.website.example.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.website.example.mapper.AccountRowMapper;
import com.website.example.common.MyDataSourceFactory;
import com.website.example.model.AccountVO;

public class AccountDAOSpring implements AccountDAO {

	// Spring Framework - JDBC
	private JdbcTemplate jdbcTemplate = null;
	
	private final String INSERT = "insert into account_tbl(name, balance, regidate) values(?, ?, ?, ?)";
		
	public AccountDAOSpring(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	
	public void createAccount(AccountVO vo) {
		
		this.jdbcTemplate.update(INSERT, vo.getName(), vo.getRegidate());
		
	}
	
    public int getBalance(String name){
    	
    	Object[] args = {name};
        AccountVO vo = this.jdbcTemplate.queryForObject("select * from account_tbl where name = ?",
        												 args, new AccountRowMapper());
        int result = vo.getBalance();
        
        return result;
    }
     
    public void minus(String name, int money) throws SQLException{

    	// 예외 발생시키기
    	/*
    	if(true){
    		throw new SQLException(); // 의도적 예외 발생
   	    }
   	    */
    	
        this.jdbcTemplate.update("update account_tbl set balance = (select balance from account_tbl where name = ?) - ? "
                + "where name = ?", name, money, name);
        
    }
     
    public void plus(String name, int money) throws SQLException{
    	    	
        this.jdbcTemplate.update("update account_tbl set balance = (select balance from account_tbl where name = ?) + ? "
                + "where name = ?", name, money, name);
    }
	
}
