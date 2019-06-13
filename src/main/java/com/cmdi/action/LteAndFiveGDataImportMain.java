package com.cmdi.action;

import com.cmdi.impl.LteDataImportService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LteAndFiveGDataImportMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
        LteDataImportService bean = context.getBean("lteDataImportService", LteDataImportService.class);
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\4G扫频数据.csv");
    }
}
