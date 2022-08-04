package com.kgv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kgv.dto.Memdb_DTO;
import com.kgv.dto.No_Memdb_DTO;

public class No_Memdb_DAO {
	
	/**
	 * 필요한 변수선언
	 * */
	Connection con; // 데이터베이스와 연결하는 객체
	Statement st; // sql 문장을 완벽하게 만들어 전송. 
	PreparedStatement ps; // sql 문장에 ?를 가지고 있는 미완성의 문장
	ResultSet rs; //select 결과를 저장하는 객체 .next()를 사용해서 값을 받는다.
	String sql;
	
	

	/**
	 * 로드 연결을 위한 생성자
	 * */
	public No_Memdb_DAO() {
		try {
			// 로드: Driver Class를 로딩하면 객체가 생성되고, DriverManager에 등록된다.
			// DriverManager는 Class.forName() 메소드를 통해서 생성
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 : DriverManager.getConnection(연결문자열, DB_ID, DB_PW) 으로 Connection 객체를 생성
			// DriverManager 클래스는 JDBC 드라이버를 통하여 Connection을 만드는 역할
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "admin", "1234");
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> 로드 fail");
			//Driver 클래스를 찾지 못할 경우, ClassNotFoundException 예외가 발생
		} catch (SQLException e) {
			//쿼리 실행이나 데이터베이스 관련 예외처리
			System.out.println(e + "=> 연결 fail");
		}
	}//생성자 
	
	//회원가입
	public int No_Signup(String hp, String birth, String pwd ) {
		int result = 0;
		try {
			sql = "insert into no_memdb(no_phone, no_birth, no_pwd)"
					+ "values(?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, hp);
			ps.setString(2, birth);
			ps.setString(3, pwd);
			
			 
			result = ps.executeUpdate(); //저장 성공한 레코드 행의 개수 반환
		}catch(Exception e) {e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
		
	}//No_Signup
	
	//비회원 예매확인 유효성 검사
	public int noMemCheck(String birth, String phone, String pwd) {				//07.27 수정
		No_Memdb_DTO m = new No_Memdb_DTO();
		try {
			// 모든컬럼에서 오라클로 부터 검색
			ps = con.prepareStatement("select no_birth, no_phone, no_pwd from no_memdb where no_birth=? AND no_phone=? AND no_pwd=?");
			// 쿼리문 물음표에 입력받은 값 저장
			ps.setString(1, birth);
			ps.setString(2, phone);
			ps.setString(3, pwd);
			// executeQuery()로 select문 실행
			rs = ps.executeQuery();
			// 해서 결과 레코드를 rs에 저장
			
			if(rs.next()) {
				m.setNo_birth(rs.getString(1));
				m.setNo_phone(rs.getString(2));
				m.setNo_pwd(rs.getString(3));	
			}
			if(birth.equals(m.getNo_birth()) && phone.equals(m.getNo_phone()) && pwd.equals(m.getNo_pwd())) {
				return 1;
			}
		}catch(SQLException e) {
			System.out.println("DB연결안됨!");
		}finally {
			dbClose();
		}
		return 0;
	}
	
	
	//DB 닫는 메서드
		public void dbClose() {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
//				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println(e + "=> dbClose fail");
			}
		}
}
