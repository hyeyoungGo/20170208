package com.bit2017.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.bit2017.jdbc.dao.EmployeesDao;
import com.bit2017.jdbc.vo.EmployeesVo;

public class HRSearchEmployee {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//키워드 입력
		System.out.print("입력 > ");
		String keyword = scanner.nextLine();
		
		//검색
		searchEmployees(keyword);
		List<EmployeesVo> list = searchEmployees( keyword );
				
		//출력 
		displayEmployees(list);
				
		//자원정리
		scanner.close();
	}
	
	private static void displayEmployees(List<EmployeesVo> list) {
		for( EmployeesVo vo : list) {
			System.out.println(
					vo.getFirstName() + " " +
					vo.getLastName() + " | " +
					vo.getEmail() + " | " +
					vo.getPhoneNumber() + " | " +
					vo.getHireDate()
					);
			
		}
		
	}

	private static List<EmployeesVo> searchEmployees(String keyword) {
		EmployeesDao dao = new EmployeesDao();
		List<EmployeesVo> list = dao.getList(keyword);
		return list;		
	}

}
