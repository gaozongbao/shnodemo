package com.cmdi.model;

/** 
 * @ClassName: CellAddr 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
public class SaoPinData {
	public Integer getCountf100() {
		return countf100;
	}
	public void setCountf100(Integer countf100) {
		this.countf100 = countf100;
	}
	public Integer getCountf110() {
		return countf110;
	}
	public void setCountf110(Integer countf110) {
		this.countf110 = countf110;
	}
	public Integer getCountf120() {
		return countf120;
	}
	public void setCountf120(Integer countf120) {
		this.countf120 = countf120;
	}
	private Integer pci;
	private String timestamp;
	private Double longitude;
	private Double latitude;
	private Integer earfcn;
	private Double rs;
	private Integer mastercellId;
	
	private Integer countf100;
	private Integer countf110;
	private Integer countf120;
	private Integer countall;
	
	public Integer getCountall() {
		return countall;
	}
	public void setCountall(Integer countall) {
		this.countall = countall;
	}
	public Integer getMastercellId() {
		return mastercellId;
	}
	public void setMastercellId(Integer mastercellId) {
		this.mastercellId = mastercellId;
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
				+ latitude + ", earfcn=" + earfcn + ", rs=" + rs + ", mastercellId=" + mastercellId + "]";
	}

	
	
	

}
