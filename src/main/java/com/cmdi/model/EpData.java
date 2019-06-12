package com.cmdi.model;

public class EpData {
    @Override
	public String toString() {
		return "EpData [id=" + id + ", cgi=" + cgi + ", eci=" + eci + ", enodebname=" + enodebname + ", cellname="
				+ cellname + ", tac=" + tac + ", localcellid=" + localcellid + ", enbid=" + enbid + ", longitude="
				+ longitude + ", latitude=" + latitude + ", coveragetype=" + coveragetype + ", azimuth=" + azimuth
				+ ", antennaheight=" + antennaheight + ", totaldowntiltangle=" + totaldowntiltangle
				+ ", carrierffrequencynum=" + carrierffrequencynum + ", workfrequencyband=" + workfrequencyband
				+ ", pci=" + pci + ", maxtransmitpower=" + maxtransmitpower + ", coverscene=" + coverscene
				+ ", provincename=" + provincename + ", cityname=" + cityname + ", districtandcounty="
				+ districtandcounty + ", vendor=" + vendor + ", electronictiltangle=" + electronictiltangle
				+ ", mechanicaltiltangle=" + mechanicaltiltangle + ", iscorearea=" + iscorearea + ", status=" + status
				+ "]";
	}

	private Integer id;

    private String cgi;
    
    private String eci;

    private String enodebname;

    private String cellname;

    private String tac;

    private String localcellid;

    private String enbid;

    private Double longitude;

    private Double latitude;

    private String coveragetype;

    private Double azimuth;

    private Double antennaheight;

    private Double totaldowntiltangle;

    private Integer carrierffrequencynum;

    private String workfrequencyband;

    private String pci;

    private Double maxtransmitpower;

    private String coverscene;

    private String provincename;

    private String cityname;

    private String districtandcounty;

    private String vendor;

    private Double electronictiltangle;

    private Double mechanicaltiltangle;

    private Boolean iscorearea;
    
    private String status;

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCgi() {
        return cgi;
    }

    public void setCgi(String cgi) {
        this.cgi = cgi;
    }

    public String getEnodebname() {
        return enodebname;
    }

    public void setEnodebname(String enodebname) {
        this.enodebname = enodebname;
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

	public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    public String getLocalcellid() {
        return localcellid;
    }

    public void setLocalcellid(String localcellid) {
        this.localcellid = localcellid;
    }

    public String getEnbid() {
        return enbid;
    }

    public void setEnbid(String enbid) {
        this.enbid = enbid;
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

    public String getCoveragetype() {
        return coveragetype;
    }

    public void setCoveragetype(String coveragetype) {
        this.coveragetype = coveragetype;
    }

    public Double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(Double azimuth) {
        this.azimuth = azimuth;
    }

    public Double getAntennaheight() {
        return antennaheight;
    }

    public void setAntennaheight(Double antennaheight) {
        this.antennaheight = antennaheight;
    }

    public Double getTotaldowntiltangle() {
        return totaldowntiltangle;
    }

    public void setTotaldowntiltangle(Double totaldowntiltangle) {
        this.totaldowntiltangle = totaldowntiltangle;
    }

    public Integer getCarrierffrequencynum() {
        return carrierffrequencynum;
    }

    public void setCarrierffrequencynum(Integer carrierffrequencynum) {
        this.carrierffrequencynum = carrierffrequencynum;
    }

    public String getWorkfrequencyband() {
        return workfrequencyband;
    }

    public void setWorkfrequencyband(String workfrequencyband) {
        this.workfrequencyband = workfrequencyband;
    }

    public String getPci() {
        return pci;
    }

    public void setPci(String pci) {
        this.pci = pci;
    }

    public Double getMaxtransmitpower() {
        return maxtransmitpower;
    }

    public void setMaxtransmitpower(Double maxtransmitpower) {
        this.maxtransmitpower = maxtransmitpower;
    }

    public String getCoverscene() {
        return coverscene;
    }

    public void setCoverscene(String coverscene) {
        this.coverscene = coverscene;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getDistrictandcounty() {
        return districtandcounty;
    }

    public void setDistrictandcounty(String districtandcounty) {
        this.districtandcounty = districtandcounty;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Double getElectronictiltangle() {
        return electronictiltangle;
    }

    public void setElectronictiltangle(Double electronictiltangle) {
        this.electronictiltangle = electronictiltangle;
    }

    public Double getMechanicaltiltangle() {
        return mechanicaltiltangle;
    }

    public void setMechanicaltiltangle(Double mechanicaltiltangle) {
        this.mechanicaltiltangle = mechanicaltiltangle;
    }

    public Boolean getIscorearea() {
        return iscorearea;
    }

    public void setIscorearea(Boolean iscorearea) {
        this.iscorearea = iscorearea;
    }
}