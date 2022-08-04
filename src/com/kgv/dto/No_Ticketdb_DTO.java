package com.kgv.dto;
//비회원 예매정보 DTO(Data Transfer Object)
public class No_Ticketdb_DTO {
	private String no_phone;	//비회원 전화번호
	private String title;	// 영화제목
	private String theater;	//상영 극장
	private String ticket_date;	// 날짜
	private String ticket_time;	// 시간
	private String seat;	// 좌석
	private int ad_num;		// 성인매수
	private int ch_num;		//청소년 매수
	private String ticket_num;	//티켓 번호
	

	public String getNo_phone() {
		return no_phone;
	}
	public void setNo_phone(String no_phone) {
		this.no_phone = no_phone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTheater() {
		return theater;
	}
	public void setTheater(String theater) {
		this.theater = theater;
	}
	public String getTicket_date() {
		return ticket_date;
	}
	public void setTicket_date(String ticket_date) {
		this.ticket_date = ticket_date;
	}
	public String getTicket_time() {
		return ticket_time;
	}
	public void setTicket_time(String ticket_time) {
		this.ticket_time = ticket_time;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public int getAd_num() {
		return ad_num;
	}
	public void setAd_num(int ad_num) {
		this.ad_num = ad_num;
	}
	public int getCh_num() {
		return ch_num;
	}
	public void setCh_num(int ch_num) {
		this.ch_num = ch_num;
	}
	public String getTicket_num() {
		return ticket_num;
	}
	public void setTicket_num(String ticketNumber) {
		this.ticket_num = ticketNumber;
	}
	
}
