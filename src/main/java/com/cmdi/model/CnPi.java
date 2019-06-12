package com.cmdi.model;

import java.util.Date;

public class CnPi {
    @Override
	public String toString() {
		return "CnPi [id=" + id + ", date=" + date + ", province=" + province + ", city=" + city + ", cellname="
				+ cellname + ", eci=" + eci + ", imsregattempnum=" + imsregattempnum + ", imsregsucnum=" + imsregsucnum
				+ ", cnvolteconnattempnum=" + cnvolteconnattempnum + ", cnvolteconnsucnum=" + cnvolteconnsucnum
				+ ", cnvoltedropnum=" + cnvoltedropnum + ", bsrvcchoattempnum=" + bsrvcchoattempnum
				+ ", bsrvcchosucnum=" + bsrvcchosucnum + ", asrvcchoattempnum=" + asrvcchoattempnum
				+ ", asrvcchosucnum=" + asrvcchosucnum + ", esrvcchoattempnum=" + esrvcchoattempnum
				+ ", esrvcchosucnum=" + esrvcchosucnum + ", srvcchoattempnum=" + srvcchoattempnum + ", srvcchosucnum="
				+ srvcchosucnum + ", vvcondelaytoohighnum=" + vvcondelaytoohighnum + ", intecondelaytoohighnum="
				+ intecondelaytoohighnum + ", vvcondelay=" + vvcondelay + ", intecondelay=" + intecondelay
				+ ", esrvcchofailradionum=" + esrvcchofailradionum + ", imsregfailradionum=" + imsregfailradionum
				+ ", cnvolteconnfailradionum=" + cnvolteconnfailradionum + "]";
	}

	private Long id;

    private Date date;

    private String province;

    private String city;

    private String cellname;

    private String eci;

    private Integer imsregattempnum;

    private Integer imsregsucnum;

    private Integer cnvolteconnattempnum;

    private Integer cnvolteconnsucnum;

    private Integer cnvoltedropnum;

    private Integer bsrvcchoattempnum;

    private Integer bsrvcchosucnum;

    private Integer asrvcchoattempnum;

    private Integer asrvcchosucnum;

    private Integer esrvcchoattempnum;

    private Integer esrvcchosucnum;

    private Integer srvcchoattempnum;

    private Integer srvcchosucnum;

    private Integer vvcondelaytoohighnum;

    private Integer intecondelaytoohighnum;

    private Double vvcondelay;

    private Double intecondelay;
    
    private Integer esrvcchofailradionum;
    
    private Integer asrvcchofailradionum;
    
    private Integer bsrvcchofailradionum;
    
    public Integer getAsrvcchofailradionum() {
		return asrvcchofailradionum;
	}

	public void setAsrvcchofailradionum(Integer asrvcchofailradionum) {
		this.asrvcchofailradionum = asrvcchofailradionum;
	}

	public Integer getBsrvcchofailradionum() {
		return bsrvcchofailradionum;
	}

	public void setBsrvcchofailradionum(Integer bsrvcchofailradionum) {
		this.bsrvcchofailradionum = bsrvcchofailradionum;
	}

	private Integer imsregfailradionum;
    
    private Integer cnvolteconnfailradionum;

    public Integer getCnvolteconnfailradionum() {
		return cnvolteconnfailradionum;
	}

	public void setCnvolteconnfailradionum(Integer cnvolteconnfailradionum) {
		this.cnvolteconnfailradionum = cnvolteconnfailradionum;
	}

	public Integer getImsregfailradionum() {
		return imsregfailradionum;
	}

	public void setImsregfailradionum(Integer imsregfailradionum) {
		this.imsregfailradionum = imsregfailradionum;
	}

	public Integer getEsrvcchofailradionum() {
		return esrvcchofailradionum;
	}

	public void setEsrvcchofailradionum(Integer esrvcchofailradionum) {
		this.esrvcchofailradionum = esrvcchofailradionum;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}