package com.cmdi.action;

import com.cmdi.impl.LteDataImportService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LteAndFiveGDataImportMain {

    /**
     * 文件名格式
     *      4G扫频数据-网格39.csv     -- 4g 扫频文件名
     *      5G扫频数据-网格19.csv     -- 5g 扫频文件名
     *      其中 网格id 可变化 其他不能变动
     * main 方法
     *     运行逻辑  数据导入---》数据分析
     *    导入方法  importLteSaoPinData(String filePath,String type)
     *              参数一 文件所在路径
     *              参数二  区分4g 5g  其值为  4g/5g
     *    导入涉及到的表   fiveg_saopin_addr   5g扫频
     *                      fourg_saopin_addr  4g扫频
     *    数据分析  analysisSaoPinData()
     *      涉及到表 分析后数据 会插入到一下表
     *      表一 fourg_saopin_addr_mastercell  4g扫频点及主服务小区
     *      表二 fourg_saopin_mastercell_cover 4g 主服务小区覆盖率
     *      表三 fiveg_saopin_addr_mastercell 5g 扫频点及主服务小区
     *      表四 fiveg_saopin_mastercell_cover 5g 主服务小区覆盖率
     *
     *      注意 数据导入 同一批次同一网格  覆盖 导入 （批次 已在代码写死）
     *      数据分析  同一批次 覆盖分析
     *
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
        LteDataImportService bean = context.getBean("lteDataImportService", LteDataImportService.class);
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\4G扫频数据-网格19.csv","4g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\4G扫频数据-网格27.csv","4g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\4G扫频数据-网格38.csv","4g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\4G扫频数据-网格39.csv","4g");


        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\new\\5G扫频数据-网格19.csv","5g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\new\\5G扫频数据-网格27.csv","5g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\new\\5G扫频数据-网格38.csv","5g");
        bean.importLteSaoPinData("D:\\其他公司\\上海设计院\\地铁\\45g模块\\new\\5G扫频数据-网格39.csv","5g");
        bean.analysisSaoPinData();

        context.close();
    }
}
