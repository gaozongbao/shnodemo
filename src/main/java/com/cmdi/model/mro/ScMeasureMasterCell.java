package com.cmdi.model.mro;

/** 
 * @ClassName: ScMeasureMasterCell 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月14日
 * @version: 1.0 
 */
public class ScMeasureMasterCell {
	Integer MmeUeS1apId;
	String cgi;
	Integer enbId;
    Integer eci;
    Integer lteScEarfcn;
    Integer lteScPci;
    Integer lteScRSRP;
    Integer lteScTadv;
    Integer lteScAOA;
    Integer index;
	
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getMmeUeS1apId() {
		return MmeUeS1apId;
	}

	public void setMmeUeS1apId(Integer mmeUeS1apId) {
		MmeUeS1apId = mmeUeS1apId;
	}

	public String getCgi() {
		return cgi;
	}

	public void setCgi(String cgi) {
		this.cgi = cgi;
	}

	public Integer getEnbId() {
		return enbId;
	}

	public void setEnbId(Integer enbId) {
		this.enbId = enbId;
	}

	public Integer getEci() {
		return eci;
	}

	public void setEci(Integer eci) {
		this.eci = eci;
	}

	public Integer getLteScEarfcn() {
		return lteScEarfcn;
	}

	public void setLteScEarfcn(Integer lteScEarfcn) {
		this.lteScEarfcn = lteScEarfcn;
	}

	public Integer getLteScPci() {
		return lteScPci;
	}

	public void setLteScPci(Integer lteScPci) {
		this.lteScPci = lteScPci;
	}

	public Integer getLteScRSRP() {
		return lteScRSRP;
	}

	public void setLteScRSRP(Integer lteScRSRP) {
		this.lteScRSRP = lteScRSRP;
	}

	public Integer getLteScTadv() {
		return lteScTadv;
	}

	public void setLteScTadv(Integer lteScTadv) {
		this.lteScTadv = lteScTadv;
	}

	public Integer getLteScAOA() {
		return lteScAOA;
	}

	public void setLteScAOA(Integer lteScAOA) {
		this.lteScAOA = lteScAOA;
	}

	@Override
	public String toString() {
		return "ScMeasureMasterCell [MmeUeS1apId=" + MmeUeS1apId + ", cgi=" + cgi + ", enbId=" + enbId + ", eci=" + eci
				+ ", lteScEarfcn=" + lteScEarfcn + ", lteScPci=" + lteScPci + ", lteScRSRP=" + lteScRSRP
				+ ", lteScTadv=" + lteScTadv + ", lteScAOA=" + lteScAOA + ", index=" + index + "]";
	}

}
