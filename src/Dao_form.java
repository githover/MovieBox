

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//JDBC 연동 form
public class Dao_form {
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "admin"; //오라클 사용자
	String pwd = "1234"; //오라클 사용자 비번
	
	Connection con = null; //DB연결 con
	PreparedStatement pstmt = null; //쿼리문 수행
	ResultSet rs = null; //쿼리문 수행 후 레코드 저장할 rs
	String sql = null; //쿼리문


	public void form(){
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pwd);
			//sql = "";
			pstmt = con.prepareStatement(sql);
			//rs = pstmt.executeQuery(); 	
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				//if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			}catch(Exception e) {e.printStackTrace();}
		}
	}


}//Dao_form