package com.kgv.dto;

public class No_Memdb_DTO { //데이터 저장빈 클래스
	
	private String no_phone; //비회원 전화번호 
	private String no_birth; //비회원 생년월일
	private String no_pwd; //비회원 비밀번호
	
	
	public String getNo_phone() {
		return no_phone;
	}
	public void setNo_phone(String no_phone) {
		this.no_phone = no_phone;
	}
	public String getNo_birth() {
		return no_birth;
	}
	public void setNo_birth(String no_birth) {
		this.no_birth = no_birth;
	}
	public String getNo_pwd() {
		return no_pwd;
	}
	public void setNo_pwd(String no_pwd) {
		this.no_pwd = no_pwd;
	}	
}
