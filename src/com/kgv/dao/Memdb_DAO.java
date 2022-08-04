package com.kgv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kgv.dto.Memdb_DTO;
import com.kgv.dto.No_Memdb_DTO;


public class Memdb_DAO {
	
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
	public Memdb_DAO() {
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
	public int Signup(Memdb_DTO dto) {
		System.out.println(dto.toString());
		int result = 0;
		try {
			sql = "insert into memdb(m_id, m_name, m_pwd, m_email, m_phone)"
					+ "values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getM_id());
			ps.setString(2, dto.getM_name());
			ps.setString(3, dto.getM_pwd());
			ps.setString(4, dto.getM_email());
			ps.setString(5, dto.getM_phone());
			
			result = ps.executeUpdate(); //저장 성공한 레코드 행의 개수 반환
		}catch(Exception e) {e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
		
	}
	
	//중복 아이디 처리
	public int id_Check(String m_id){
		int result = 1;
		try {
			sql = "select count(m_id) from memdb where m_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, m_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception e) {e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	
	//아이디 비밀번호 확인 메서드
		public int loginCheck(String id, String pwd) {
			Memdb_DTO m = new Memdb_DTO();
			int result = 0;
			try {
				// 모든컬럼에서 오라클로 부터 아이디,비밀번호검색
				ps = con.prepareStatement("select m_pwd from memdb where m_id=?");
				// 쿼리문 물음표에 입력받은 값 저장
				ps.setString(1, id);
				// executeQuery()로 select문 실행
				rs = ps.executeQuery();
				// 해서 결과 레코드를 rs에 저장
				
				
				if (rs.next()) {
					m.setM_pwd(rs.getString(1));
				}
				
				if(pwd.equals(m.getM_pwd())) {
					result=1;
				}
			} catch (SQLException e) {
				System.out.println("DB연결안됨!");
			} finally {
				dbClose();
			} // finally
			return result;
		}
	
		
		//비밀번호 찾기
		public String pwFinder(String id, String name, String email, String phone) {		//추가된 메서드 07/26
			Memdb_DTO m = new Memdb_DTO();
			String answer = null;
			try {
				// 모든컬럼에서 오라클로 부터 아이디,비밀번호검색
				ps = con.prepareStatement("select m_id,m_name,m_email,m_phone,m_pwd from memdb where m_id=? AND m_name=? AND m_email=? AND m_phone=?");
				// 쿼리문 물음표에 입력받은 값 저장
				ps.setString(1, id);
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, phone);
				// executeQuery()로 select문 실행
				rs = ps.executeQuery();
				// 해서 결과 레코드를 rs에 저장
				
				if(rs.next()) {
					m.setM_id(rs.getString(1));
					m.setM_name(rs.getString(2));
					m.setM_email(rs.getString(3));
					m.setM_phone(rs.getString(4));	
					m.setM_pwd(rs.getString(5));
				}
				if(id.equals(m.getM_id()) && name.equals(m.getM_name()) && email.equals(m.getM_email()) && phone.equals(m.getM_phone())) {
					answer = rs.getString(5);
				}
			}catch(SQLException e) {
				System.out.println("DB연결안됨!");
			}finally {
				dbClose();
			}
			return answer;
		}
		
		//아이디 찾기
		public String idFinder(String pwd, String name, String email, String phone) {			//추가된 메서드 07/26
			Memdb_DTO m = new Memdb_DTO();
			String answer = null;
			try {
				// 모든컬럼에서 오라클로 부터 검색
				ps = con.prepareStatement("select m_pwd,m_name,m_email,m_phone,m_id from memdb where m_pwd =? AND m_name=? AND m_email=? AND m_phone=?");
				// 쿼리문 물음표에 입력받은 값 저장
				ps.setString(1, pwd);
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, phone);
				// executeQuery()로 select문 실행
				rs = ps.executeQuery();
				// 해서 결과 레코드를 rs에 저장

				if(rs.next()) {
					m.setM_pwd(rs.getString(1));
					m.setM_name(rs.getString(2));
					m.setM_email(rs.getString(3));	
					m.setM_phone(rs.getString(4));
					m.setM_id(rs.getString(5));
					System.out.println(rs.getString(5));
				}
				if(name.equals(m.getM_name()) && email.equals(m.getM_email()) && phone.equals(m.getM_phone()) && pwd.equals(m.getM_pwd())) {
					answer = rs.getString(5);
				}
			}catch(SQLException e) {
				System.out.println("DB연결안됨!");
			}finally {
				dbClose();
			}
			return answer;
		}	
		
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
				
	//계정 삭제
		public int deleteAcccont(Memdb_DTO mdto) {
			try {
				/* memdb 에서 아이디 검색후 삭제 
				 * delete from memdb where m_id=?
				 * 
				 * memdb_DOA 를 통하여 dto 로 값 불러오기 
				 * 
				 * 
				  */
				sql= "delete from memdb where m_id=?";
				ps = con.prepareStatement(sql);
				
				ps.setString(1, mdto.getM_id());
				System.out.println(mdto.getM_id());
			} catch (Exception e) {
				e.printStackTrace();
		}
			
			
			return 0;
			
		}
	//DB 닫는 메서드
	public void dbClose() {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
//			if (con != null) con.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}
	
	
}//Memdb_DAO


/* 쿼리문 작성 시 참고!
 * executeQuery() : select 쿼리인 경우에 사용, 
 * 					ResultSet을 리턴타입으로 가지며, next()를 한 후에 컬럼값을 읽어 사용한다.
 * executeUpdate() : insert, update, delete 쿼리인 경우에 사용, 
 * 					결과 행의 개수를 가지는 int타입을 리턴타입으로 가진다.	 */