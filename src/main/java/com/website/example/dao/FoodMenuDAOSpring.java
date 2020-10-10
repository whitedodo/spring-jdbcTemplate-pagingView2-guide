/*
 * 
 *  파일명(Filename): FoodDAOSpring.java
 *  설명(Description):
 *  1. JDBC Template를 사용하면, MyBatis에 준하는 생산성을 가져올 수 있음.
 *  2. 과거 Connection, ResultSet, PreparedStatement, Close 등 작업을 할 필요가 없어짐.
 *  3. DataSource Factory 하나만 작업 잘 해놔도 쉽게 붙여짐.
 *  4. BoardRowMapper.java 소스와 연계됨.
 *  
 */

package com.website.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.website.example.common.MyDataSourceFactory;
import com.website.example.mapper.FoodMenuViewCntRowMapper;
import com.website.example.mapper.FoodMenuViewRowMapper;
import com.website.example.model.FoodMenuVO;
import com.website.example.model.FoodMenuViewVO;

public class FoodMenuDAOSpring implements FoodDAO {
	
	// Spring Framework - JDBC
	private JdbcTemplate jdbcTemplate = null;
	
	private final String FOODMENU_INSERT = "insert into foodmenu_tbl(name, price, store_id, cnt, regidate) values(?, ?, ?, ?, ?)";
	private final String FOODMENU_UPDATE = "update board set subject=?, memo=? where id=?";
	private final String FOODMENU_DELETE = "delete foodmenu_tbl where id=?";
	
	private final String FOODMENUVIEW_LIST = "SELECT * FROM ( " + 
																"SELECT /*+ INDEX_DESC(Z OP_SAMPLE_PK) */ ROWNUM AS RNUM, Z.* FROM ( " + 
															    "SELECT f1.id, f1.name, f1.price, f2.name as storename, f1.regidate, f1.cnt " +
																"from foodmenu_tbl f1, foodstore_tbl f2 " + 
																"where f1.store_id = f2.id order by f1.id desc " +
																") Z WHERE ROWNUM <= ? " +
																") WHERE RNUM >= ?";
	
	private final String FOODMENUVIEW_GET = "SELECT ROWNUM AS RNUM, f1.id, f1.name, f1.price, " +
				"f2.name as storename, f1.regidate, f1.cnt " +
				"from foodmenu_tbl f1, foodstore_tbl f2 " + 
				"where f1.store_id = f2.id and f1.id = ?";

	
	private final String FOODMENUVIEW_FULL_COUNT = "select count(*) from foodmenu_tbl f1, foodstore_tbl f2 where f1.store_id = f2.id";

	public FoodMenuDAOSpring(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	
	public List<FoodMenuViewVO> getList(long start, long end){
	
		// 코드 간결하게 작성가능해짐.
		System.out.println("Spring JDBC - GetBoardList()");
		//return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
		
		// Object args[] = {10, 1};
		Object args[] = {end, start};
		
		System.out.println( "start:" + start + "/end:" + end);
		
		return jdbcTemplate.query(FOODMENUVIEW_LIST, args, new FoodMenuViewRowMapper());
		// return null;
	}
	

	public FoodMenuViewVO getFoodMenuView(long id){
	
		// 코드 간결하게 작성가능해짐.
		System.out.println("Spring JDBC - GetBoard()");
		//return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
		
		// Object args[] = {10, 1};
		Object args[] = {id};
		
		return jdbcTemplate.queryForObject(FOODMENUVIEW_GET, args, new FoodMenuViewRowMapper());
		// return null;
	}
	
	public int getCount() {
		
		int result = 0;
		FoodMenuVO vo =  jdbcTemplate.queryForObject(FOODMENUVIEW_FULL_COUNT, new FoodMenuViewCntRowMapper());
		result = vo.getId();
		
		System.out.println("갯수:" + result);
		
		return result;
		
	}
	
	public void insertTest() throws SQLException {
		
		FoodMenuVO vo = new FoodMenuVO();

    	TransactionSynchronizationManager.initSynchronization(); // 트랜잭션 동기화 작업 초기화
    	Connection conn = null;
    	DataSource ds = this.jdbcTemplate.getDataSource();
    	
		// Connection Pool 없이 동시 10000개 넣어보기
		// insert 후에 commit 할 것

		// 트랜젝션
		try {
			
	    	conn = ds.getConnection();
	    	conn.setAutoCommit(false);
	    	
			for(int j=0; j< 20000 ; j++) { 
				for ( int i = 0; i < 50000; i++) {
	
					vo.setName("헬로우식당" + i);
					vo.setPrice(1000);
					vo.setStore_id(1);
					vo.setCnt(0);
					vo.setRegidate(Date.valueOf("2020-01-03"));
					vo.setStore_id(1);
	
				    	
					jdbcTemplate.update(FOODMENU_INSERT, vo.getName(), 
											vo.getPrice(), vo.getStore_id(), 
											vo.getCnt(), vo.getRegidate());
	
				} // end of for
				
			} // end of for
		
			conn.commit();	// 성공		
		
		}catch(SQLException e) {
    		conn.rollback();	// 실패
    	}
    	finally {
    		// 커넥션 종료(Spring)
    		DataSourceUtils.releaseConnection(conn, ds);

            // 동기화 작업을 종료하고 저장소를 비운다
            TransactionSynchronizationManager.clearSynchronization();
    	}
		// end of if
		
		/*
		vo.setName("야해해");
		vo.setPrice(1000);
		vo.setStore_id(1);
		vo.setCnt(0);
		vo.setRegidate(Date.valueOf("2020-01-05"));
		vo.setStore_id(1);
		
		Object[] args = {vo.getName(),
						 vo.getPrice(),
						 vo.getStore_id(),
						 vo.getCnt(),
						 vo.getRegidate()};
		
		// 트랜젝션
		try {
			jdbcTemplate.update(FOODMENU_INSERT, args);
			platformTransactionManager.commit(status);			
		}
		catch(Exception e) {
			platformTransactionManager.rollback(status);
		}
		
		*/
	}
	

	// 글 삭제
	public void deleteFoodMenu(FoodMenuVO vo) {
		
		System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update( FOODMENU_DELETE, vo.getId() );
		
	}
	
	
}
