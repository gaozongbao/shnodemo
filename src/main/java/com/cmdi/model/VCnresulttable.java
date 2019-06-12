
package com.cmdi.model;

import java.util.Date;

/**
 * cmdiEntity
 * @author cmdi
 * @version 2017-12-15
 */
public class VCnresulttable  {
	
	private static final long serialVersionUID = 1L;
	private Date cnDate;		// cn_date
	private String province;		// province
	private String city;		// city
	private String scenario;		// scenario
	private String vendor;		// vendor
	private Double volteconnratio;		// volteconnratio
	private Double voltedropratio;		// voltedropratio
	private Double esrvcchosucratio;		// esrvcchosucratio
	private Double volteplratio;		// volteplratio
	

	public Date getCnDate() {
		return cnDate;
	}

	public void setCnDate(Date cnDate) {
		this.cnDate = cnDate;
	}
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	
	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public Double getVolteconnratio() {
		return volteconnratio;
	}

	public void setVolteconnratio(Double volteconnratio) {
		this.volteconnratio = volteconnratio;
	}
	
	public Double getVoltedropratio() {
		return voltedropratio;
	}

	public void setVoltedropratio(Double voltedropratio) {
		this.voltedropratio = voltedropratio;
	}
	
	public Double getEsrvcchosucratio() {
		return esrvcchosucratio;
	}

	public void setEsrvcchosucratio(Double esrvcchosucratio) {
		this.esrvcchosucratio = esrvcchosucratio;
	}
	
	public Double getVolteplratio() {
		return volteplratio;
	}

	public void setVolteplratio(Double volteplratio) {
		this.volteplratio = volteplratio;
	}
	
}