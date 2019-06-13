package com.cmdi.model;


public class FourSaopinAddr {
    /**
     * id
     */
    private Integer id;


    /**
     * 时间
     */
    private String date;

    /**
     * 时间字符串
     */
    private String timestamp;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 维度
     */
    private Double latitude;

    /**
     * 频段
     */
    private Integer earfcn;

    private Integer pci;

    private Double sssRssi;

    private Double sssRp;

    /**
     * rsrp值
     */
    private Double r0Rp;

    private Double r0Rq;

    private Double r0Cinr;

    /**
     * timing
     */
    private Integer timing;

    private String antCnt;

    private String bw;

    private String antRpDif;

    private String prbAntNo;
    private String operator;
    private String pattern;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * 获取时间
     *
     * @return date - 时间
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置时间
     *
     * @param date 时间
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 获取时间字符串
     *
     * @return timestamp - 时间字符串
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * 设置时间字符串
     *
     * @param timestamp 时间字符串
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取维度
     *
     * @return latitude - 维度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 设置维度
     *
     * @param latitude 维度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取频段
     *
     * @return earfcn - 频段
     */
    public Integer getEarfcn() {
        return earfcn;
    }

    /**
     * 设置频段
     *
     * @param earfcn 频段
     */
    public void setEarfcn(Integer earfcn) {
        this.earfcn = earfcn;
    }

    /**
     * @return pci
     */
    public Integer getPci() {
        return pci;
    }

    /**
     * @param pci
     */
    public void setPci(Integer pci) {
        this.pci = pci;
    }

    /**
     * @return sss_rssi
     */
    public Double getSssRssi() {
        return sssRssi;
    }

    /**
     * @param sssRssi
     */
    public void setSssRssi(Double sssRssi) {
        this.sssRssi = sssRssi;
    }

    /**
     * @return sss_rp
     */
    public Double getSssRp() {
        return sssRp;
    }

    /**
     * @param sssRp
     */
    public void setSssRp(Double sssRp) {
        this.sssRp = sssRp;
    }

    /**
     * 获取rsrp值
     *
     * @return r0_rp - rsrp值
     */
    public Double getR0Rp() {
        return r0Rp;
    }

    /**
     * 设置rsrp值
     *
     * @param r0Rp rsrp值
     */
    public void setR0Rp(Double r0Rp) {
        this.r0Rp = r0Rp;
    }

    /**
     * @return r0_rq
     */
    public Double getR0Rq() {
        return r0Rq;
    }

    /**
     * @param r0Rq
     */
    public void setR0Rq(Double r0Rq) {
        this.r0Rq = r0Rq;
    }

    /**
     * @return r0_cinr
     */
    public Double getR0Cinr() {
        return r0Cinr;
    }

    /**
     * @param r0Cinr
     */
    public void setR0Cinr(Double r0Cinr) {
        this.r0Cinr = r0Cinr;
    }

    /**
     * 获取timing
     *
     * @return timing - timing
     */
    public Integer getTiming() {
        return timing;
    }

    /**
     * 设置timing
     *
     * @param timing timing
     */
    public void setTiming(Integer timing) {
        this.timing = timing;
    }

    /**
     * @return ant_cnt
     */
    public String getAntCnt() {
        return antCnt;
    }

    /**
     * @param antCnt
     */
    public void setAntCnt(String antCnt) {
        this.antCnt = antCnt;
    }

    /**
     * @return bw
     */
    public String getBw() {
        return bw;
    }

    /**
     * @param bw
     */
    public void setBw(String bw) {
        this.bw = bw;
    }

    /**
     * @return ant_rp_dif
     */
    public String getAntRpDif() {
        return antRpDif;
    }

    /**
     * @param antRpDif
     */
    public void setAntRpDif(String antRpDif) {
        this.antRpDif = antRpDif;
    }

    /**
     * @return prb_ant_no
     */
    public String getPrbAntNo() {
        return prbAntNo;
    }

    /**
     * @param prbAntNo
     */
    public void setPrbAntNo(String prbAntNo) {
        this.prbAntNo = prbAntNo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}