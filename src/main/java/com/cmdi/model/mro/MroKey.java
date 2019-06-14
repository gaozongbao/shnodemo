package com.cmdi.model.mro;

/**
 * @author: 高宗宝
 * @date: 2019/5/23
 * @version: 1.0
 * @description: someword
 */
public class MroKey {
    Integer enbId = -111;
    Integer eci = -111;
    String cgi = "Nil";
    Long mmeUeSlapId = -111L;
    Integer mmeGroupId = -111;
    Integer mmeCode = -111;
    String timeStamp = "Nil";

    @Override
    public String toString() {
        return "MroKey{" +
                "enbId=" + enbId +
                ", eci=" + eci +
                ", cgi='" + cgi + '\'' +
                ", mmeUeSlapId=" + mmeUeSlapId +
                ", mmeGroupId=" + mmeGroupId +
                ", mmeCode=" + mmeCode +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }

    public MroKey(Integer enbId, Integer eci, String cgi, Long mmeUeSlapId, Integer mmeGroupId, Integer mmeCode, String timeStamp) {
        this.enbId = enbId;
        this.eci = eci;
        this.cgi = cgi;
        this.mmeUeSlapId = mmeUeSlapId;
        this.mmeGroupId = mmeGroupId;
        this.mmeCode = mmeCode;
        this.timeStamp = timeStamp;
    }

    public MroKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MroKey mroKey = (MroKey) o;

        if (enbId != null ? !enbId.equals(mroKey.enbId) : mroKey.enbId != null) return false;
        if (eci != null ? !eci.equals(mroKey.eci) : mroKey.eci != null) return false;
        if (cgi != null ? !cgi.equals(mroKey.cgi) : mroKey.cgi != null) return false;
        if (mmeUeSlapId != null ? !mmeUeSlapId.equals(mroKey.mmeUeSlapId) : mroKey.mmeUeSlapId != null) return false;
        if (mmeGroupId != null ? !mmeGroupId.equals(mroKey.mmeGroupId) : mroKey.mmeGroupId != null) return false;
        if (mmeCode != null ? !mmeCode.equals(mroKey.mmeCode) : mroKey.mmeCode != null) return false;
        return timeStamp != null ? timeStamp.equals(mroKey.timeStamp) : mroKey.timeStamp == null;
    }

    @Override
    public int hashCode() {
        int result = enbId != null ? enbId.hashCode() : 0;
        result = 31 * result + (eci != null ? eci.hashCode() : 0);
        result = 31 * result + (cgi != null ? cgi.hashCode() : 0);
        result = 31 * result + (mmeUeSlapId != null ? mmeUeSlapId.hashCode() : 0);
        result = 31 * result + (mmeGroupId != null ? mmeGroupId.hashCode() : 0);
        result = 31 * result + (mmeCode != null ? mmeCode.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        return result;
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

    public String getCgi() {
        return cgi;
    }

    public void setCgi(String cgi) {
        this.cgi = cgi;
    }

    public Long getMmeUeSlapId() {
        return mmeUeSlapId;
    }

    public void setMmeUeSlapId(Long mmeUeSlapId) {
        this.mmeUeSlapId = mmeUeSlapId;
    }

    public Integer getMmeGroupId() {
        return mmeGroupId;
    }

    public void setMmeGroupId(Integer mmeGroupId) {
        this.mmeGroupId = mmeGroupId;
    }

    public Integer getMmeCode() {
        return mmeCode;
    }

    public void setMmeCode(Integer mmeCode) {
        this.mmeCode = mmeCode;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
