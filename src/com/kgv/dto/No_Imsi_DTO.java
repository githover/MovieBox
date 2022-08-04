package com.kgv.dto;

public class No_Imsi_DTO {
	private String no_phone;
	private String title;
	private String theater;
	private String ticket_date;
	private String ticket_time;
	private String seat;
	private int ad_num;
	private int ch_num;
	private String ticket_num;
	
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
	
	@Override
	public String toString() {
		return "NoImsiDTO [no_phone=" + no_phone + ", title=" + title + ", theater=" + theater + ", ticket_date="
				+ ticket_date + ", ticket_time=" + ticket_time + ", seat=" + seat + ", ad_num=" + ad_num + ", ch_num="
				+ ch_num + ", ticket_num= "+ ticket_num+ "]";
	}
	public String getTicket_num() {
		return ticket_num;
	}
	public void setTicket_num(String ticket_num) {
		this.ticket_num = ticket_num;
	}
	
	
}