package com.kgv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kgv.dto.No_Imsi_DTO;
import com.kgv.dto.No_Ticketdb_DTO;

public class No_Ticketdb_DAO {
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
	public No_Ticketdb_DAO() {
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

	public int insertdb(No_Imsi_DTO no_imsiDTO) {
		int res=0;
		try {
			ps = con.prepareStatement("insert into no_ticketdb(no_phone, title, theater, ticket_date,ticket_time, seat, ad_num, ch_num,ticket_num) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, no_imsiDTO.getNo_phone());
			ps.setString(2, no_imsiDTO.getTitle());
			ps.setString(3, no_imsiDTO.getTheater());
			ps.setString(4, no_imsiDTO.getTicket_date());
			ps.setString(5, no_imsiDTO.getTicket_time());
			ps.setString(6, no_imsiDTO.getSeat());
			ps.setInt(7, no_imsiDTO.getAd_num());
			ps.setInt(8, no_imsiDTO.getCh_num());
			ps.setString(9, no_imsiDTO.getTicket_num());


			res = ps.executeUpdate(); 
			System.out.println("성공 여부 : "+res);//확인용
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	
		return res;		
	}
	
	//전체 예매내역 확인
	public List<No_Ticketdb_DTO> showList(String no_phone) {
		List<No_Ticketdb_DTO> listdto = new ArrayList<No_Ticketdb_DTO>();
		
		try {
			ps = con.prepareStatement("select*from no_ticketdb where no_phone=?");
			ps.setString(1, no_phone);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				No_Ticketdb_DTO dto = new No_Ticketdb_DTO();
				dto.setTicket_num(rs.getString(7));
				dto.setNo_phone(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setTheater(rs.getString(3));
				dto.setTicket_date(rs.getString(4));
				dto.setTicket_time(rs.getString(5));
				dto.setSeat(rs.getString(6));
				dto.setAd_num(rs.getInt(8));
				dto.setCh_num(rs.getInt(9));

				listdto.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}		
		
		return listdto;
	}

	//예약번호로 예매취소
	public int cancle(Object value) {
		int result = 0;
		
		try {
			ps = con.prepareStatement("delete from no_ticketdb where ticket_num=?");
			ps.setObject(1, value);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}		
		
		return result;
	}
	//예매개수 확인
		public int moviecount(String string) {
			int result = 0;

			try {
				ps = con.prepareStatement("select sum(ad_num)+sum(ch_num) from no_ticketdb where title=?");
				ps.setString(1, string);
				rs = ps.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);
				}
				System.out.println(result);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}		
			return result;
		}
	
	
}
