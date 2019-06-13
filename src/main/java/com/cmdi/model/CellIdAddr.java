package com.cmdi.model;

/** 
 * @ClassName: CellAddr 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
public class CellIdAddr {
	
	public CellIdAddr() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CellIdAddr(Integer cellid, Double longitude, Double latitude) {
		super();
		this.cellid = cellid;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	private Integer cellid;
	private Double longitude;
	private Double latitude;
	public Integer getCellid() {
		return cellid;
	}
	public void setCellid(Integer cellid) {
		this.cellid = cellid;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "CellIdAddr [cellid=" + cellid + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}


}
