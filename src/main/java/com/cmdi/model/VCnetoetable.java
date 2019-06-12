
package com.cmdi.model;

import java.util.Date;

/**
 * cmdiEntity
 * @author cmdi
 * @version 2017-12-15
 */
public class VCnetoetable  {
	
	private static final long serialVersionUID = 1L;
	private Date date;		// date
	private String province;		// province
	private String city;		// city
	private String cellname;		// cellname
	private String eci;		// eci
	private String scenario;		// scenario
	private String vendor;		// vendor
	private Integer imsregattempnum;		// imsregattempnum
	private Integer imsregsucnum;		// imsregsucnum
	private Integer cnvolteconnattempnum;		// cnvolteconnattempnum
	private Integer cnvolteconnsucnum;		// cnvolteconnsucnum
	private Integer cnvoltedropnum;		// cnvoltedropnum
	private Integer bsrvcchoattempnum;		// bsrvcchoattempnum
	private Integer bsrvcchosucnum;		// bsrvcchosucnum
	private Integer asrvcchoattempnum;		// asrvcchoattempnum
	private Integer asrvcchosucnum;		// asrvcchosucnum
	private Integer esrvcchoattempnum;		// esrvcchoattempnum
	private Integer esrvcchosucnum;		// esrvcchosucnum
	private Integer srvcchoattempnum;		// srvcchoattempnum
	private Integer srvcchosucnum;		// srvcchosucnum
	private Integer vvcondelaytoohighnum;		// vvcondelaytoohighnum
	private Integer intecondelaytoohighnum;		// intecondelaytoohighnum
	private Double vvcondelay;		// vvcondelay
	private Double intecondelay;		// intecondelay
	
	private Double volteconnratio;		// volteconnratio
	private Double voltedropratio;		// voltedropratio
	private Double esrvcchosucratio;		// esrvcchosucratio

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
	
	public String getCellname() {
		return cellname;
	}

	public void setCellname(String cellname) {
		this.cellname = cellname;
	}
	
	public String getEci() {
		return eci;
	}

	public void setEci(String eci) {
		this.eci = eci;
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
	
	public Integer getImsregattempnum() {
		return imsregattempnum;
	}

	public void setImsregattempnum(Integer imsregattempnum) {
		this.imsregattempnum = imsregattempnum;
	}
	
	public Integer getImsregsucnum() {
		return imsregsucnum;
	}

	public void setImsregsucnum(Integer imsregsucnum) {
		this.imsregsucnum = imsregsucnum;
	}
	
	public Integer getCnvolteconnattempnum() {
		return cnvolteconnattempnum;
	}

	public void setCnvolteconnattempnum(Integer cnvolteconnattempnum) {
		this.cnvolteconnattempnum = cnvolteconnattempnum;
	}
	
	public Integer getCnvolteconnsucnum() {
		return cnvolteconnsucnum;
	}

	public void setCnvolteconnsucnum(Integer cnvolteconnsucnum) {
		this.cnvolteconnsucnum = cnvolteconnsucnum;
	}
	
	public Integer getCnvoltedropnum() {
		return cnvoltedropnum;
	}

	public void setCnvoltedropnum(Integer cnvoltedropnum) {
		this.cnvoltedropnum = cnvoltedropnum;
	}
	
	public Integer getBsrvcchoattempnum() {
		return bsrvcchoattempnum;
	}

	public void setBsrvcchoattempnum(Integer bsrvcchoattempnum) {
		this.bsrvcchoattempnum = bsrvcchoattempnum;
	}
	
	public Integer getBsrvcchosucnum() {
		return bsrvcchosucnum;
	}

	public void setBsrvcchosucnum(Integer bsrvcchosucnum) {
		this.bsrvcchosucnum = bsrvcchosucnum;
	}
	
	public Integer getAsrvcchoattempnum() {
		return asrvcchoattempnum;
	}

	public void setAsrvcchoattempnum(Integer asrvcchoattempnum) {
		this.asrvcchoattempnum = asrvcchoattempnum;
	}
	
	public Integer getAsrvcchosucnum() {
		return asrvcchosucnum;
	}

	public void setAsrvcchosucnum(Integer asrvcchosucnum) {
		this.asrvcchosucnum = asrvcchosucnum;
	}
	
	public Integer getEsrvcchoattempnum() {
		return esrvcchoattempnum;
	}

	public void setEsrvcchoattempnum(Integer esrvcchoattempnum) {
		this.esrvcchoattempnum = esrvcchoattempnum;
	}
	
	public Integer getEsrvcchosucnum() {
		return esrvcchosucnum;
	}

	public void setEsrvcchosucnum(Integer esrvcchosucnum) {
		this.esrvcchosucnum = esrvcchosucnum;
	}
	
	public Integer getSrvcchoattempnum() {
		return srvcchoattempnum;
	}

	public void setSrvcchoattempnum(Integer srvcchoattempnum) {
		this.srvcchoattempnum = srvcchoattempnum;
	}
	
	public Integer getSrvcchosucnum() {
		return srvcchosucnum;
	}

	public void setSrvcchosucnum(Integer srvcchosucnum) {
		this.srvcchosucnum = srvcchosucnum;
	}
	
	public Integer getVvcondelaytoohighnum() {
		return vvcondelaytoohighnum;
	}

	public void setVvcondelaytoohighnum(Integer vvcondelaytoohighnum) {
		this.vvcondelaytoohighnum = vvcondelaytoohighnum;
	}
	
	public Integer getIntecondelaytoohighnum() {
		return intecondelaytoohighnum;
	}

	public void setIntecondelaytoohighnum(Integer intecondelaytoohighnum) {
		this.intecondelaytoohighnum = intecondelaytoohighnum;
	}
	
	public Double getVvcondelay() {
		return vvcondelay;
	}

	public void setVvcondelay(Double vvcondelay) {
		this.vvcondelay = vvcondelay;
	}
	
	public Double getIntecondelay() {
		return intecondelay;
	}

	public void setIntecondelay(Double intecondelay) {
		this.intecondelay = intecondelay;
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
	
}