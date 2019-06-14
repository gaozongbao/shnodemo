package com.cmdi.model.mro;

/**
 * @author: 高宗宝
 * @date: 2019/5/23
 * @version: 1.0
 * @description: someword
 */
public class NcInOneMeasure {
    Integer lteNcEarfcn= -111;
    Integer lteNcPci= -111;
    Integer lteNcRSRP= -111;
    Integer lteNcRSRQ= -111;
    Integer tdsNcellUarfcn= -111;
    Integer tdsCellParameterId= -111;
    Integer tdsPccpchRSCP= -111;
    Integer gsmNcellNcc= -111;
    Integer gsmNcellBcc= -111;
    Integer gsmNcellBcch= -111;
    Integer gsmNcellCarrierRSSI= -111;

    public Integer getLteNcEarfcn() {
        return lteNcEarfcn;
    }

    public void setLteNcEarfcn(Integer lteNcEarfcn) {
        this.lteNcEarfcn = lteNcEarfcn;
    }

    public Integer getLteNcPci() {
        return lteNcPci;
    }

    public void setLteNcPci(Integer lteNcPci) {
        this.lteNcPci = lteNcPci;
    }

    public Integer getLteNcRSRP() {
        return lteNcRSRP;
    }

    public void setLteNcRSRP(Integer lteNcRSRP) {
        this.lteNcRSRP = lteNcRSRP;
    }

    public Integer getLteNcRSRQ() {
        return lteNcRSRQ;
    }

    public void setLteNcRSRQ(Integer lteNcRSRQ) {
        this.lteNcRSRQ = lteNcRSRQ;
    }

    public Integer getTdsNcellUarfcn() {
        return tdsNcellUarfcn;
    }

    public void setTdsNcellUarfcn(Integer tdsNcellUarfcn) {
        this.tdsNcellUarfcn = tdsNcellUarfcn;
    }

    public Integer getTdsCellParameterId() {
        return tdsCellParameterId;
    }

    public void setTdsCellParameterId(Integer tdsCellParameterId) {
        this.tdsCellParameterId = tdsCellParameterId;
    }

    public Integer getTdsPccpchRSCP() {
        return tdsPccpchRSCP;
    }

    public void setTdsPccpchRSCP(Integer tdsPccpchRSCP) {
        this.tdsPccpchRSCP = tdsPccpchRSCP;
    }

    public Integer getGsmNcellNcc() {
        return gsmNcellNcc;
    }

    public void setGsmNcellNcc(Integer gsmNcellNcc) {
        this.gsmNcellNcc = gsmNcellNcc;
    }

    public Integer getGsmNcellBcc() {
        return gsmNcellBcc;
    }

    public void setGsmNcellBcc(Integer gsmNcellBcc) {
        this.gsmNcellBcc = gsmNcellBcc;
    }

    public Integer getGsmNcellBcch() {
        return gsmNcellBcch;
    }

    public void setGsmNcellBcch(Integer gsmNcellBcch) {
        this.gsmNcellBcch = gsmNcellBcch;
    }

    public Integer getGsmNcellCarrierRSSI() {
        return gsmNcellCarrierRSSI;
    }

    public void setGsmNcellCarrierRSSI(Integer gsmNcellCarrierRSSI) {
        this.gsmNcellCarrierRSSI = gsmNcellCarrierRSSI;
    }

    public NcInOneMeasure() {

    }

    @Override
    public String toString() {
        return "NcInOneMeasure{" +
                "lteNcEarfcn=" + lteNcEarfcn +
                ", lteNcPci=" + lteNcPci +
                ", lteNcRSRP=" + lteNcRSRP +
                ", lteNcRSRQ=" + lteNcRSRQ +
                ", tdsNcellUarfcn=" + tdsNcellUarfcn +
                ", tdsCellParameterId=" + tdsCellParameterId +
                ", tdsPccpchRSCP=" + tdsPccpchRSCP +
                ", gsmNcellNcc=" + gsmNcellNcc +
                ", gsmNcellBcc=" + gsmNcellBcc +
                ", gsmNcellBcch=" + gsmNcellBcch +
                ", gsmNcellCarrierRSSI=" + gsmNcellCarrierRSSI +
                '}';
    }
}
