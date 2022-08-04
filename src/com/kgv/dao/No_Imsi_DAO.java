package com.kgv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kgv.dto.Imsi_DTO;
import com.kgv.dto.No_Imsi_DTO;

import signup_login.BuyTicket_Non;
import signup_login.BuyTicket_Non;

public class No_Imsi_DAO {

	/**
	 * 필요한 변수선언
	 * */
	Connection con; 
	Statement st; 
	PreparedStatement ps; 
	ResultSet rs; 
	
	/**
	 * 로드 연결을 위한 생성자
	 * */
	public No_Imsi_DAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "admin", "1234");
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> 로드 fail");
		} catch (SQLException e) {
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
//			if (con != null) con.close(); // con을 닫으니 연결이 되지 않는 에러 발생
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}//dbClose() 
	
	public int logincheck(BuyTicket_Non buyTicket_Non) {
		int result=0;
		try {
			if(check()==0) { //check()메서드를 통해 데이터가 들어있는지 확인하고 0이면 insert를 실행
			ps = con.prepareStatement("insert into no_imsi_table (no_phone) values(?)");
			ps.setString(1, buyTicket_Non.input2.getText()); 
			System.out.println("성공");
			result = ps.executeUpdate(); 
			}else { //만약 임시테이블에 값이 남아있는 경우
				System.out.println("기존에 남아있는 값을 삭제하고 새로운 값을 넣습니다."); //console에만 뜸 (확인용)
				imsi_delete(); //테이블에 들어있는 값을 삭제하는 메서드 호출
				ps = con.prepareStatement("insert into no_imsi_table (no_phone) values(?)"); 
				//기존 데이터를 삭제하고 새로 들어온 값을 insert한다.
				ps.setString(1, buyTicket_Non.input2.getText());
				result = ps.executeUpdate();			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}
	
	
	
	
	
	
	public Integer check() {
		int num = 1; //디폴트값은 false가 되는 값을 넣음
		try {
			st = con.createStatement();
			rs = st.executeQuery("select count(*) from no_imsi_table");
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
	
	public int imsi_delete() {
		int result=0;
		try {		
			ps = con.prepareStatement("delete from no_imsi_table"); //테이블의 모든 값을 없앤다.			
			result = ps.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}	
	
	
	//임시테이블에 저장되어있는 예매 정보를 불러옴
		public No_Imsi_DTO imsi_select() {
			No_Imsi_DTO dto = new No_Imsi_DTO();
			try {
				st = con.createStatement();
				rs = st.executeQuery("select*from no_imsi_table"); //값이 하나밖에 없기때문에 따로 where절을 사용하지 않음
				while (rs.next()) {
					dto.setNo_phone(rs.getString(1));
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
		
		//비회원 영화 예매정보 저장
		public int updatemovie(No_Imsi_DTO no_imsi_DTO) {
			int result = 0;
			try {
				ps = con.prepareStatement("update no_imsi_table set title=?, theater=?, ticket_date=?, ticket_time=? where no_phone=?");

				ps.setString(1,no_imsi_DTO.getTitle());
				ps.setString(2,no_imsi_DTO.getTheater());
				ps.setString(3,no_imsi_DTO.getTicket_date());
				ps.setString(4,no_imsi_DTO.getTicket_time());
				ps.setString(5,no_imsi_DTO.getNo_phone());
				
				result = ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				imsi_select();

				dbClose();
			}
			return result;
		}

		public void updateTicket(int ad, int ch, String seat,String ticket_num) {
			try {
				ps = con.prepareStatement("update no_imsi_table set ad_num=?, ch_num=?, seat=?, ticket_num=? where no_phone!= '2'");

				ps.setInt(1, ad);
				ps.setInt(2, ch);
				ps.setString(3, seat);
				ps.setString(4, ticket_num);
				ps.executeUpdate();
				System.out.println("db에 저장 완료");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				imsi_select();

				dbClose();
			}

		}
		public void reset(String no_phone) {
			try {
				ps = con.prepareStatement("update no_imsi_table set title=?, theater=?, ticket_date=?, ticket_time=?, "
						+ "seat=?, ad_num=?, ch_num=?, ticket_num=? where no_phone = ?");

				ps.setString(1, null);
				ps.setString(2, null);
				ps.setString(3, null);
				ps.setString(4, null);
				ps.setString(5, null);
				ps.setInt(6, 0);
				ps.setInt(7, 0);
				ps.setString(8, null);
				ps.setString(9, no_phone);
				
				ps.executeUpdate();			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
		}
}
