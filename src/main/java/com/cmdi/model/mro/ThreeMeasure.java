package com.cmdi.model.mro;

/**
 * @author: 高宗宝
 * @date: 2019/5/23
 * @version: 1.0
 * @description: someword
 */
public class ThreeMeasure {
    Integer lteScRIP2 = -111;
    Integer lteScRIP7 = -111;

    @Override
    public String toString() {
        return "ThreeMeasure{" +
                "lteScRIP2=" + lteScRIP2 +
                ", lteScRIP7=" + lteScRIP7 +
                '}';
    }

    public Integer getLteScRIP2() {
        return lteScRIP2;
    }

    public void setLteScRIP2(Integer lteScRIP2) {
        this.lteScRIP2 = lteScRIP2;
    }

    public Integer getLteScRIP7() {
        return lteScRIP7;
    }

    public void setLteScRIP7(Integer lteScRIP7) {
        this.lteScRIP7 = lteScRIP7;
    }
}
