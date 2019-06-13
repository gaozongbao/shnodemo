package com.cmdi.util;

import java.util.List;

public interface CSVHandler {
    /**
     * 每一定数量会触发一次 入csv 10w数据 则每5000条触发一次函数
     * @param res 结果
     * @param num 第几次 从1开始
     */
    public void getData(int num, List<String[]> res);
}
