package com.bit2017.jdbc.dao.test;

import java.util.List;

import com.bit2017.jdbc.dao.BookDao;
import com.bit2017.jdbc.vo.BookVo;

public class BookDaoTest {
	
	public static void main(String[] args) {
		insertTest();
		
		deleteTest();
		
		getListTest();
	}

	private static void deleteTest() {
		BookDao bookDao = new BookDao();
		bookDao.delete(new Long(3L));
		
	}

	private static void getListTest() {
		BookDao bookDao = new BookDao();
		List<BookVo> list = bookDao.getList();
		
		for( BookVo vo : list ) {
			System.out.println(vo);
		}
		
	}

	private static void insertTest() {
		//Mock Data
		BookVo bookVo = new BookVo();
		bookVo.setTitle("어린왕자");
		bookVo.setPub_date("2005-02-23");
		bookVo.setState("대여중");
		bookVo.setAuthor_no((long)1);		
		
		BookDao dao = new BookDao();
		dao.insert(bookVo);
	}
	

}
