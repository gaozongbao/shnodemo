package com.cmdi.model;

import java.util.Date;

public class CnPoorQualityCell {
    private Integer id;

    private Date date;

    private String province;

    private String city;

    private String cellname;

    private String cgi;

    private Boolean lowcnvolteconnratio;

    private Boolean highcnvoltedropratio;

    private Boolean highcnesrvccdropratio;

    private Boolean highcnconndelay;

    private Boolean highcnregfailureratio;

    private Double cnvolteconnratio;

    private Double cnvoltedropratio;

    private Double cnesrvccdropratio;

    private Double vvcondelay;

    private Double cnregfailureratio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCgi() {
        return cgi;
    }

    public void setCgi(String cgi) {
        this.cgi = cgi;
    }

    public Boolean getLowcnvolteconnratio() {
        return lowcnvolteconnratio;
    }

    public void setLowcnvolteconnratio(Boolean lowcnvolteconnratio) {
        this.lowcnvolteconnratio = lowcnvolteconnratio;
    }

    public Boolean getHighcnvoltedropratio() {
        return highcnvoltedropratio;
    }

    public void setHighcnvoltedropratio(Boolean highcnvoltedropratio) {
        this.highcnvoltedropratio = highcnvoltedropratio;
    }

    public Boolean getHighcnesrvccdropratio() {
        return highcnesrvccdropratio;
    }

    public void setHighcnesrvccdropratio(Boolean highcnesrvccdropratio) {
        this.highcnesrvccdropratio = highcnesrvccdropratio;
    }

    public Boolean getHighcnconndelay() {
        return highcnconndelay;
    }

    public void setHighcnconndelay(Boolean highcnconndelay) {
        this.highcnconndelay = highcnconndelay;
    }

    public Boolean getHighcnregfailureratio() {
        return highcnregfailureratio;
    }

    public void setHighcnregfailureratio(Boolean highcnregfailureratio) {
        this.highcnregfailureratio = highcnregfailureratio;
    }

    public Double getCnvolteconnratio() {
        return cnvolteconnratio;
    }

    public void setCnvolteconnratio(Double cnvolteconnratio) {
        this.cnvolteconnratio = cnvolteconnratio;
    }

    public Double getCnvoltedropratio() {
        return cnvoltedropratio;
    }

    public void setCnvoltedropratio(Double cnvoltedropratio) {
        this.cnvoltedropratio = cnvoltedropratio;
    }

    public Double getCnesrvccdropratio() {
        return cnesrvccdropratio;
    }

    public void setCnesrvccdropratio(Double cnesrvccdropratio) {
        this.cnesrvccdropratio = cnesrvccdropratio;
    }

    public Double getVvcondelay() {
        return vvcondelay;
    }

    public void setVvcondelay(Double vvcondelay) {
        this.vvcondelay = vvcondelay;
    }

    public Double getCnregfailureratio() {
        return cnregfailureratio;
    }

    public void setCnregfailureratio(Double cnregfailureratio) {
        this.cnregfailureratio = cnregfailureratio;
    }
}