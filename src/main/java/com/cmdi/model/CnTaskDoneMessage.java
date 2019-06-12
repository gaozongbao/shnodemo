package com.cmdi.model;

/** 
 * @ClassName: CnTaskDoneMessage 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2018年11月15日
 * @version: 1.0 
 */
public class CnTaskDoneMessage {
	public CnTaskDoneMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String date;
	private String status;
	@Override
	public String toString() {
		return "CnTaskDoneMessage [date=" + date + ", status=" + status + "]";
	}
	public CnTaskDoneMessage(String date, String status) {
		super();
		this.date = date;
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
