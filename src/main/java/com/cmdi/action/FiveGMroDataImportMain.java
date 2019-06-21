package com.cmdi.action;

import com.cmdi.dao.FivegMroDataDao;
import com.cmdi.impl.LteDataImportService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class FiveGMroDataImportMain {
    /**
     *
     * main 方法运行逻辑
     * 基于4g mro 原始数据 汇聚5g mro 插入表 fiveg_mro_cell
     *
     * 数据插入完毕 后调用 analysisMroData 分析 4G 5G 模块所用到的 数据
     * 涉及到的表
     * 表一 fourg_mro_cell_cover mro  小区及覆盖率
     * 表二 fiveg_mro_cell_cover 小区及覆盖率
     *
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
        FivegMroDataDao bean = context.getBean("fivegMroDataDao", FivegMroDataDao.class);
        List<Map<String, Object>> effectMroCgi = bean.getEffectMroCgi();
        for(Map<String, Object> item:effectMroCgi){
            System.out.println("开始插入有效cgi--》"+item.get("cgi").toString());
            bean.insertEffectMroData(item.get("cgi").toString());
            System.out.println("插入有效cgi 完毕--》"+item.get("cgi").toString());
        }
        List<Map<String, Object>> unEffectMroCgi = bean.getUnEffectMroCgi();
        for(Map<String, Object> item:unEffectMroCgi){
            System.out.println("开始插入无效cgi--》"+item.get("cgi").toString());
            bean.insertunEffectMroData(item.get("cgi").toString());
            System.out.println("插入无效cgi 完毕--》"+item.get("cgi").toString());
        }
        LteDataImportService ss = context.getBean("lteDataImportService", LteDataImportService.class);
        System.out.println("开始统计分析--->mro");
        ss.analysisMroData();
        System.out.println("统计分析完毕--->mro");
        context.close();
    }
}
