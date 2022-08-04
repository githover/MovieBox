package com.kgv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kgv.dto.Imsi_DTO;

import buyTicket.BuyTicket_List;
import buyTicket.BuyTicket_Pay;
import buyTicket.BuyTicket_Seats;
import buyTicket.BuyTicket_SelMovie;
import signup_login.Login;


public class Imsi_DAO {

	/**
	 * 필요한 변수선언
	 * */
	Connection con; // 데이터베이스와 연결하는 객체
	Statement st; // sql 문장을 완벽하게 만들어 전송. 
	PreparedStatement ps; // sql 문장에 ?를 가지고 있는 미완성의 문장
	ResultSet rs; //select 결과를 저장하는 객체 .next()를 사용해서 값을 받는다.
	
	
	/**
	 * 로드 연결을 위한 생성자
	 * */
	public Imsi_DAO() {
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
	
	/**
	 * DB닫기 기능 메소드
	 * */
	public void dbClose() {
		try {
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (ps != null) ps.close();
//			if (con != null) con.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}//dbClose() 
	
	// 메서드
	/* 쿼리문 작성 시 참고!
	 * executeQuery() : select 쿼리인 경우에 사용, 
	 * 					ResultSet을 리턴타입으로 가지며, next()를 한 후에 컬럼값을 읽어 사용한다.
	 * executeUpdate() : insert, update, delete 쿼리인 경우에 사용, 
	 * 					결과 행의 개수를 가지는 int타입을 리턴타입으로 가진다.	 */

	
	//저장된 튜플이 없으면 임시테이블에 로그인하는 회원 아이디를 insert하는 메서드
	public int logincheck(Login login) {
		int result=0;
		try {
			if(check()==0) { //check()메서드를 통해 데이터가 들어있는지 확인하고 0이면 insert를 실행
			ps = con.prepareStatement("insert into imsi_table (m_id) values(?)");
			ps.setString(1, login.input1.getText()); 
			//Login 페이지의 input1에 들어온 텍스트 값을 insert한다.
			
			result = ps.executeUpdate(); //실행 -> 저장
			}else { //만약 임시테이블에 값이 남아있는 경우
				System.out.println("이미 튜플이 들어있습니다."); //console에만 뜸 (확인용)
				imsi_delete(); //테이블에 들어있는 값을 삭제하는 메서드 호출
				ps = con.prepareStatement("insert into imsi_table (m_id) values(?)"); 
				//기존 데이터를 삭제하고 새로 들어온 로그인 아이디값을 insert한다.
				ps.setString(1, login.input1.getText());
				
				result = ps.executeUpdate(); //실행 -> 저장				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}
	
	//현재 저장된 레코드가 있는지 확인하는 메서드
	//저장된 레코드가 0이어야 현재 로그인하는 회원의 아이디를 저장 할 수 있음
	public Integer check() {
		int num = 1; //디폴트값은 false가 되는 값을 넣음
		try {
			st = con.createStatement();
			rs = st.executeQuery("select count(*) from imsi_table");
			if(rs.next()) {
				num=rs.getInt(1); //num에 현재 레코드 개수를 받음
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return num; //count된 개수를 반환함
	}
	
	//임시 테이블에 저장되어 있는 값을 제거하는 메서드
	//로그아웃 시에도 해당 메서드가 실행되도록 해야 함. -> 로그아웃 버튼 생기면 적용
	public int imsi_delete() {
		int result=0;
		try {		
			ps = con.prepareStatement("delete from imsi_table"); //테이블의 모든 값을 없앤다.			
			result = ps.executeUpdate(); //실행 -> 저장			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}	
	
	//임시테이블에 저장되어있는 예매 정보를 불러옴
	public Imsi_DTO imsi_select() {
		Imsi_DTO dto = new Imsi_DTO();
		try {
			st = con.createStatement();
			rs = st.executeQuery("select*from imsi_table"); //값이 하나밖에 없기때문에 따로 where절을 사용하지 않음
			while (rs.next()) {
				dto.setM_id(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setTheater(rs.getString(3));
				dto.setTicket_date(rs.getString(4));
				dto.setTicket_time(rs.getString(5));
				dto.setSeat(rs.getString(6));
				dto.setAd_num(rs.getInt(7));
				dto.setCh_num(rs.getInt(8));
				dto.setTicket_num(rs.getString(9));
			}
			System.out.println(dto.toString()); // 제대로 실행 되는지 콘솔창에 확인차 출력
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return dto;
	}

	
	
	//영화 티켓 매수 저장
	public void updateTicket(int ad, int ch, String seat, String ticket_num)  {

		try {
			ps = con.prepareStatement("update imsi_table set ad_num=?, ch_num=?, seat=?, ticket_num=? where m_id != '2'");

			ps.setInt(1, ad);
			ps.setInt(2, ch);
			ps.setString(3, seat);
			ps.setString(4, ticket_num);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			imsi_select();

			dbClose();
		}

	}
	
	//회원 영화 예매정보 저장
	public int updatemovie(Imsi_DTO imsi_DTO) {
		int result = 0;
		try {
			ps = con.prepareStatement("update imsi_table set title=?, theater=?, ticket_date=?, ticket_time=? where m_id = ?");

			ps.setString(1,imsi_DTO.getTitle());
			ps.setString(2,imsi_DTO.getTheater());
			ps.setString(3,imsi_DTO.getTicket_date());
			ps.setString(4,imsi_DTO.getTicket_time());
			ps.setString(5,imsi_DTO.getM_id());
			
			result = ps.executeUpdate();
			//System.out.println(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			imsi_select();

			dbClose();
		}
		return result;
	}
	
	//리셋
	public void reset(String m_id) {
		try {
			ps = con.prepareStatement("update imsi_table set title=?, theater=?, ticket_date=?, ticket_time=?, "
					+ "seat=?, ad_num=?, ch_num=?, ticket_num=? where m_id = ?");

			ps.setString(1, null);
			ps.setString(2, null);
			ps.setString(3, null);
			ps.setString(4, null);
			ps.setString(5, null);
			ps.setInt(6, 0);
			ps.setInt(7, 0);
			ps.setString(8, null);
			ps.setString(9, m_id);
			
			ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

	}
}// 클래스끝