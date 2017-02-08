package com.bit2017.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {
	
	public static void main(String[] args) {
		/*Integer i = 10;
		
		int k = i + 20;  --정보 은닉 */
		updateBook(1L, "토지1", true);
		updateBook(2L, "토지2", false);
	}
	
	public static void updateBook(Long no, String title, boolean rent) {
		Connection conn = null;    // 지역변수 외에서도 사용하기 위해 블럭 밖에서 선언
		PreparedStatement pstmt = null;
		try {   											// {} -> 안에 있는 건 지역변수
			//1. JDBC Driver Loading ( JDBC Class Loading )
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기 ( Connect to DB )
			String url =  "jdbc:oracle:thin:@localhost:1521:xe"; 
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL문 준비
			String sql = "update book set title = ?, state = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. 데이터 바인딩
			pstmt.setString(1, title);
			if(rent) {
				pstmt.setString(2, "대여중");
			} else {
				pstmt.setString(2, "대여가능");
			}
			pstmt.setLong(3, no);
			
			//5. SQL문 실행
			int count = pstmt.executeUpdate();
			if( count == 1 ) {
				System.out.println("updqte 성공");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패- " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
			//finally는 항상 꼭 실행된다.
		} finally {
			//3. 자원정리
			try {
				if( pstmt != null) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
		}
	}
}
