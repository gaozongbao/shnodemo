package com.cmdi.model;

import java.util.Date;

import com.cmdi.action.MainT;
import com.cmdi.util.DateUtil;

public class GcTask {
    @Override
	public String toString() {
		return "GcTask [id=" + id + ", gcDate=" + DateUtil.getDate(gcDate, MainT.DATEFORMAT) + ", province=" + province + "]";
	}

	private Integer id;

    private Date gcDate;

    private String province;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGcDate() {
        return gcDate;
    }

    public void setGcDate(Date gcDate) {
        this.gcDate = gcDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}