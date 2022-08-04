package com.kgv.dto;

public class Movie_info_DTO {
	private String title;
	private String s_poster;
	private String b_poster;
	private String info1;
	private String info2;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getS_poster() {
		return s_poster;
	}
	public void setS_poster(String s_poster) {
		this.s_poster = s_poster;
	}
	public String getB_Poster() {
		return b_poster;
	}
	public void setB_Poster(String b_poster) {
		this.b_poster = b_poster;
	}
	public String getInfo1() {
		return info1;
	}
	public void setInfo1(String info1) {
		this.info1 = info1;
	}
	public String getInfo2() {
		return info2;
	}
	public void setInfo2(String info2) {
		this.info2 = info2;
	}
	@Override
	   public String toString() {
	      return  title;
	   }
}
