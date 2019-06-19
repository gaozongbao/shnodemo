package com.cmdi.action;

import com.cmdi.impl.LteDataImportService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LteAndFiveGDataImportMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
        LteDataImportService bean = context.getBean("lteDataImportService", LteDataImportService.class);
       /* bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\4G扫频数据-网格19.csv","4g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\4G扫频数据-网格27.csv","4g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\4G扫频数据-网格38.csv","4g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\4G扫频数据-网格39.csv","4g");


        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\5G扫频数据-网格19.csv","5g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\5G扫频数据-网格27.csv","5g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\5G扫频数据-网格38.csv","5g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\5G扫频数据-网格39.csv","5g");
*/
        bean.analysisSaoPinData();

        //bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\4G扫频数据.csv","4g");
       // bean.importGcData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\five.csv");
        context.close();
    }
}
