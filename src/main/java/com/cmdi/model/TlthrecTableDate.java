package com.cmdi.model;

public class TlthrecTableDate {
	
	private int id;
	private String month;
	private String province;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Override
	public String toString() {
		return "TlthrecTableDate [id=" + id + ", month=" + month + ", province=" + province + "]";
	}
}
