package com.cmdi.model;

/** 
 * @ClassName: CellAddr 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
public class CellAddr {
	private Integer earfcn;
	private Integer pci;
	private Integer nums;
	private String cellidaddr;
	
	public Integer getNums() {
		return nums;
	}
	public void setNums(Integer nums) {
		this.nums = nums;
	}
	public Integer getEarfcn() {
		return earfcn;
	}
	public void setEarfcn(Integer earfcn) {
		this.earfcn = earfcn;
	}
	public Integer getPci() {
		return pci;
	}
	public void setPci(Integer pci) {
		this.pci = pci;
	}
	public String getCellidaddr() {
		return cellidaddr;
	}
	public void setCellidaddr(String cellidaddr) {
		this.cellidaddr = cellidaddr;
	}
	@Override
	public String toString() {
		return "CellAddr [earfcn=" + earfcn + ", pci=" + pci + ", nums=" + nums + ", cellidaddr=" + cellidaddr + "]";
	}
	

}
