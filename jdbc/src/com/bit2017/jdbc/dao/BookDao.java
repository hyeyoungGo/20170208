package com.bit2017.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2017.jdbc.vo.BookVo;

public class BookDao {
	
	public boolean delete(Long no) {
		Connection conn = null;    // 지역변수 외에서도 사용하기 위해 블럭 밖에서 선언
		PreparedStatement pstmt = null;
		
		try {   											// {} -> 안에 있는 건 지역변수
			//1. JDBC Driver Loading ( JDBC Class Loading )
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기 ( Connect to DB )
			String url =  "jdbc:oracle:thin:@localhost:1521:xe"; 
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL문 준비
			String sql = "delete from book where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding data
			pstmt.setLong(1, no);
						
			//5. execute SQL
			int count = pstmt.executeUpdate();
			boolean result = (count == 1);   // 더 짧게 이 줄 빼고 return을 count == 1로 바꿈
			
			return result;

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패- " + e);
			return false;
		} catch (SQLException e) {
			System.out.println("error3: " + e);
			return false;
			//finally는 항상 꼭 실행된다.
		} finally {
			//3. 자원정리
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
		}
	}
	
	public boolean insert(BookVo bookVo) {
		Connection conn = null;    // 지역변수 외에서도 사용하기 위해 블럭 밖에서 선언
		PreparedStatement pstmt = null;
		
		try {   											// {} -> 안에 있는 건 지역변수
			//1. JDBC Driver Loading ( JDBC Class Loading )
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기 ( Connect to DB )
			String url =  "jdbc:oracle:thin:@localhost:1521:xe"; 
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL문 준비
			String sql = "insert into book values ( book_seq.nextval, ?, to_date(?, 'yyyy-mm-dd'), ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩 데이터
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPub_date());
			pstmt.setString(3, bookVo.getState());
			pstmt.setLong(4, bookVo.getAuthor_no());
			
			//5. execute SQL
			int count = pstmt.executeUpdate();
			boolean result = (count == 1);   // 더 짧게 이 줄 빼고 return을 count == 1로 바꿈
			
			return result;

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패- " + e);
			return false;
		} catch (SQLException e) {
			System.out.println("error1: " + e);
			return false;
			//finally는 항상 꼭 실행된다.
		} finally {
			//3. 자원정리
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
		}
		
	}

	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<>();
		
		Connection conn = null;    // 지역변수 외에서도 사용하기 위해 블럭 밖에서 선언
		Statement stmt = null;
		ResultSet rs = null;
		
		try {   											// {} -> 안에 있는 건 지역변수
			//1. JDBC Driver Loading ( JDBC Class Loading )
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기 ( Connect to DB )
			String url =  "jdbc:oracle:thin:@localhost:1521:xe"; 
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL문 준비
			stmt = conn.createStatement();
			String sql = "select * from book";
					
			rs = stmt.executeQuery(sql);
			
			//4. 결과처리
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String pub_date = rs.getString(3);
				String state = rs.getString(4);
				Long author_no = rs.getLong(5);
				
				BookVo bookVo = new BookVo();
				bookVo.setNo(no);
				bookVo.setTitle(title);
				bookVo.setPub_date(pub_date);
				bookVo.setState(state);
				bookVo.setAuthor_no(author_no);
				
				list.add(bookVo);				
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패- " + e);
		} catch (SQLException e) {
			System.out.println("error2: " + e);
			//finally는 항상 꼭 실행된다.
		} finally {
			//3. 자원정리
			try {
				if( rs != null) {
					rs.close();
				}
				if( stmt != null) {
					stmt.close();
				}
				if( conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
			
		}
		return list;
	}

}
