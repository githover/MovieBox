package com.kgv.dto;

public class Memdb_DTO {
	
	private String m_id;//회원 아이디
	private String m_name;//회원 이름
	private String m_pwd;//회원 비밀번호
	private String m_email;//이메일 주소
	private String m_phone;//전화번호

	public Memdb_DTO() {
	}

	public Memdb_DTO(String m_id, String m_name, String m_pwd, String m_email, String m_phone) {
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_pwd = m_pwd;
		this.m_email = m_email;
		this.m_phone = m_phone;
	}

	

	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_pwd() {
		return m_pwd;
	}
	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_phone() {
		return m_phone;
	}
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
}
