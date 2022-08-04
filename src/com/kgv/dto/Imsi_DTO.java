package com.kgv.dto;

public class Imsi_DTO {

	private String m_id;
	private String title;
	private String theater;
	private String ticket_date;
	private String ticket_time;
	private String seat;
	private int ad_num;
	private int ch_num;
	private String ticket_num;
	
//	public ImsiDTO(String string, String string2, String string3, String string4, String string5, String string6,
//			int int1, int int2) {
//		// TODO Auto-generated constructor stub
//	}
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
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
	public void setTicket_num(String ticket_num) {
		this.ticket_num = ticket_num;
	}
	@Override
	public String toString() {
		return "ImsiDTO [m_id=" + m_id + ", title=" + title + ", theater=" + theater + ", ticket_date=" + ticket_date
				+ ", ticket_time=" + ticket_time + ", seat=" + seat + ", ad_num=" + ad_num + ", ch_num=" + ch_num + ", ticket_num= "+ ticket_num+"]";
	}
	
	
	
}