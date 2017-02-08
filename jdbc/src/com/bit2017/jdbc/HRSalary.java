package com.bit2017.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HRSalary {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("최소임금 입력 > ");
		int minSalary = scanner.nextInt();
		
		System.out.print("최대임금 입력 > ");
		int maxSalary = scanner.nextInt();
		
		System.out.println("=====================================");
		serchEmployeesBySalary(minSalary, maxSalary);
		
		scanner.close();
	}

	private static void serchEmployeesBySalary(int minSalary, int maxSalary) {
		Connection conn = null;    // 지역변수 외에서도 사용하기 위해 블럭 밖에서 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {   											// {} -> 안에 있는 건 지역변수
			//1. JDBC Driver Loading ( JDBC Class Loading )
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기 ( Connect to DB )
			String url =  "jdbc:oracle:thin:@localhost:1521:xe"; 
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			//3. SQL문 준비
			String sql = "select first_name || ' ' || last_name, salary from employees" +
						 "       where ? < salary and ? > salary";
			pstmt = conn.prepareStatement(sql);
			
			//4. 데이터바인딩
			pstmt.setInt(1, minSalary);
			pstmt.setInt(2, maxSalary);
			
			//5. SQL문 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 출력
			while( rs.next() ) {
				String name = rs.getString(1);
				int salary = rs.getInt(2);
				
				System.out.println(name + " | " + salary);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패- " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
			//finally는 항상 꼭 실행된다.
		} finally {
			//3. 자원정리
			try {
				if( rs != null ) {
					rs.close();
				}
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

}
