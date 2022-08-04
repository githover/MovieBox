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
import com.kgv.dto.Movie_info_DTO;

public class Movie_info_DAO {
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
	public Movie_info_DAO() {
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
	
	public Movie_info_DTO selectMovie(String title) {
		Movie_info_DTO dto = new Movie_info_DTO();
		try {
			ps = con.prepareStatement("select*from moviedb where title=?"); //값이 하나밖에 없기때문에 따로 where절을 사용하지 않음
			ps.setString(1, title);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto.setTitle(rs.getString(1));
				dto.setB_Poster(rs.getString(2));
				dto.setS_poster(rs.getString(3));
				dto.setInfo1(rs.getString(4));
				dto.setInfo2(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return dto;
	}
	
	//전체 영화 확인
		public List<Movie_info_DTO> allMovie() {
			List<Movie_info_DTO> listdto = new ArrayList<Movie_info_DTO>();

			try {
				st = con.createStatement();
				rs = st.executeQuery("select*from moviedb order by title desc");

				while(rs.next()) {
					Movie_info_DTO dto = new Movie_info_DTO();
					dto.setTitle(rs.getString(1));
					dto.setB_Poster(rs.getString(2));
					dto.setS_poster(rs.getString(3));
					dto.setInfo1(rs.getString(4));
					dto.setInfo2(rs.getString(5));

					listdto.add(dto);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}		

			return listdto;
		}
		

		public int movieInsert(Movie_info_DTO dto) {
			int result = 0;
			try {
				ps = con.prepareStatement("insert into moviedb values(?,?,?,?,?)");
				ps.setString(1, dto.getTitle());
				ps.setString(2, dto.getB_Poster());
				ps.setString(3, dto.getS_poster());
				ps.setString(4, dto.getInfo1());
				ps.setString(5, dto.getInfo2());
				
				result = ps.executeUpdate(); 
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
			return result;
		}

		public int movieDelete(Object value) {
			int result=0;
			try {
				ps = con.prepareStatement("delete from moviedb where title=?");
				String s = String.valueOf(value);
				System.out.println(s);
				ps.setString(1, s);
				
				result = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
			return result;
		}
}
