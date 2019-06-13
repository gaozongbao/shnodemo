package com.cmdi.model;

/** 
 * @ClassName: CellAddr 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
public class SaoPinData {
	private Integer pci;
	private String timestamp;
	private Double longitude;
	private Double latitude;
	private Integer earfcn;
	private Double rs;
	private Integer mastercellId;
	private Double mastercellCover;
	
	public Integer getMastercellId() {
		return mastercellId;
	}
	public void setMastercellId(Integer mastercellId) {
		this.mastercellId = mastercellId;
	}
	public Double getMastercellCover() {
		return mastercellCover;
	}
	public void setMastercellCover(Double mastercellCover) {
		this.mastercellCover = mastercellCover;
	}
	public Integer getPci() {
		return pci;
	}
	public void setPci(Integer pci) {
		this.pci = pci;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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
	public Integer getEarfcn() {
		return earfcn;
	}
	public void setEarfcn(Integer earfcn) {
		this.earfcn = earfcn;
	}
	public Double getRs() {
		return rs;
	}
	public void setRs(Double rs) {
		this.rs = rs;
	}
	@Override
	public String toString() {
		return "SaoPinData [pci=" + pci + ", timestamp=" + timestamp + ", longitude=" + longitude + ", latitude="
				+ latitude + ", earfcn=" + earfcn + ", rs=" + rs + ", mastercellId=" + mastercellId
				+ ", mastercellCover=" + mastercellCover + "]";
	}
	
	

}
