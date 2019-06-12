package com.cmdi.model;

/** 
 * @ClassName: CnPoorCellTaskDoneMessage 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2018年11月16日
 * @version: 1.0 
 */
public class CnPoorCellTaskDoneMessage extends CnTaskDoneMessage {
	private String type;
	private String city;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "CnPoorCellTaskDoneMessage [type=" + type + ", city=" + city + ", getDate()=" + getDate()
				+ ", getStatus()=" + getStatus() + "]";
	}
	public CnPoorCellTaskDoneMessage(String date, String status, String type, String city) {
		super(date, status);
		this.type = type;
		this.city = city;
	}
	
}
