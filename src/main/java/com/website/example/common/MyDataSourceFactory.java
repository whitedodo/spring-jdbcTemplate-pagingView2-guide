/*
 * 	주제(Subject): Spring Framework 4.2 - JDBC Template 
 *  파일명(Filename): MyDataSourceFactory.java
 *  설명(Description):
 *  1. 
 */

package com.website.example.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import oracle.jdbc.pool.OracleDataSource;

public class MyDataSourceFactory {
	
	private InputStream is = null;
	
	public MyDataSourceFactory()  {
		
        String resource = "db.properties";
        is = getClass().getClassLoader().getResourceAsStream(resource);
	}
	
	public DataSource getMySQLDataSource() {
		
        Properties props = new Properties();
        
        MysqlDataSource mysqlDS = null;
        
        try {
        	
            props.load(is);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return mysqlDS;
        
    }
     
    public DataSource getOracleDataSource(){
    	
        Properties props = new Properties();
        OracleDataSource oracleDS = null;
        
        try {
        	
            props.load(is);
            oracleDS = new OracleDataSource();
            oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
            oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
            oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }
        
        return oracleDS;
        
    }
    

    /*
    public DataSource getBasicDataSource() {

        Properties props = new Properties();
    	// BasicDataSource ds = null;
    	Connection conn = null;
        
        try {
 
            props.load(is);
 
            ds = new BasicDataSource();
            ds.setDriverClassName(props.getProperty("MYSQL_DB_DRIVER_CLASS"));
            ds.setUrl(props.getProperty("MYSQL_DB_URL"));
            ds.setUsername(props.getProperty("MYSQL_DB_USERNAME"));
            ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
 
                ds.setInitialSize(10); // The initial number of connections that
                                    // are created when the pool is started.
            ds.setMaxTotal(20); // The maximum number of active connections
                                    // that can be allocated from this pool
                
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
 
        return conn;
 
    }
    */

}
