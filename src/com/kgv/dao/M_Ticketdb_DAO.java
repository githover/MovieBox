package com.kgv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kgv.dto.Imsi_DTO;
import com.kgv.dto.M_Ticketdb_DTO;

public class M_Ticketdb_DAO {
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
	public M_Ticketdb_DAO() {
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

	

	
	
	public int insertdb(Imsi_DTO imsiDTO) {
		int res=0;
		try {
			ps = con.prepareStatement("insert into m_ticketdb(m_id, title, theater, ticket_date, ticket_time, seat, ad_num, ch_num, ticket_num) values(?,?,?,?,?,?,?,?,?)");

			ps.setString(1, imsiDTO.getM_id());
			ps.setString(2, imsiDTO.getTitle());
			ps.setString(3, imsiDTO.getTheater());
			ps.setString(4, imsiDTO.getTicket_date());
			ps.setString(5, imsiDTO.getTicket_time());
			ps.setString(6, imsiDTO.getSeat());
			ps.setInt(7, imsiDTO.getAd_num());
			ps.setInt(8, imsiDTO.getCh_num());
			ps.setString(9, imsiDTO.getTicket_num());

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
	public List<M_Ticketdb_DTO> showList(String m_id) {
		List<M_Ticketdb_DTO> listdto = new ArrayList<M_Ticketdb_DTO>();

		try {
			ps = con.prepareStatement("select*from m_ticketdb where m_id=?");
			ps.setNString(1, m_id);
			rs = ps.executeQuery();

			while(rs.next()) {
				M_Ticketdb_DTO dto = new M_Ticketdb_DTO();
				dto.setTicket_num(rs.getString(7));
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
				ps = con.prepareStatement("delete from m_ticketdb where ticket_num=?");
				int i = Integer.parseInt(value.toString());
				ps.setInt(1, i);
				result = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}		
			return result;
		}
		//예매 개수 확인
		public int moviecount(String string) {
			int result = 0;
			
			try {
				ps = con.prepareStatement("select sum(ad_num)+sum(ch_num) from m_ticketdb where title=?");
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
