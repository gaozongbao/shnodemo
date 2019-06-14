package com.cmdi.model.mro;

import java.util.List;

/**
 * @author: 高宗宝
 * @date: 2019/5/23
 * @version: 1.0
 * @description: someword
 */
public class MroData {
    public ScMeasure scMeasure;
    public List<NcInOneMeasure> ncs;
    public TwoMeasure twoMeasure;
    public ThreeMeasure threeMeasure;

    @Override
    public String toString() {
        return "MroData{" +
                "scMeasure=" + scMeasure +
                ", ncs=" + ncs +
                ", twoMeasure=" + twoMeasure +
                ", threeMeasure=" + threeMeasure +
                '}';
    }

    public MroData() {
        this.threeMeasure = new ThreeMeasure();
    }
}
