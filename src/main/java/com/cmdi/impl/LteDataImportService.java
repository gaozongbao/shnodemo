package com.cmdi.impl;

import com.cmdi.action.SaoPinHandle;
import com.cmdi.dao.FourSaopinAddrDao;
import com.cmdi.model.FourSaopinAddr;
import com.cmdi.util.CSVHandler;
import com.cmdi.util.DealCSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class LteDataImportService {
    @Autowired
    private FourSaopinAddrDao fourSaopinAddrDao;
    @Autowired
    private SaoPinHandle saoPinHandle;

    public void importLteSaoPinData(String filePath,String type){//type 值为 4g/5g
        if(StringUtils.isNotBlank(filePath)){
            File f = new File(filePath);
            String name = f.getName();
            Integer gridid = Integer.parseInt(name.split("-")[1].replace("网格", "").replace(".csv",""));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(new Date());
            today="2019-06-13";
            //先把旧数据删除
            String tableName="";
            if("4g".equals(type)){
                tableName="fourg_saopin_addr";
            }else if("5g".equals(type)){
                tableName="fiveg_saopin_addr";
            }else{
                throw new RuntimeException("导入类型有误");
            }

            //删除旧数据
            fourSaopinAddrDao.deleteSwSaopinAddr(today,tableName,gridid);

            DealCSV deal = new DealCSV();
            System.out.println(type+" 扫频数据录入---》文件:"+name);
            SaoPinHandler saoPinHandler = new SaoPinHandler();
            saoPinHandler.setToday(today);
            saoPinHandler.setTableName(tableName);
            saoPinHandler.setGridid(gridid);
            saoPinHandler.setFourSaopinAddrDao(fourSaopinAddrDao);
            deal.process(filePath,saoPinHandler);
            System.out.println(type+" 扫频数据录入完毕!!!!!---》文件:"+name);

        }else{
            throw new RuntimeException("没有找到入库文件");
        }

    }

    public void importGcData(String filePath){
        if(StringUtils.isNotBlank(filePath)){
            DealCSV deal = new DealCSV();
            System.out.println("lte 扫频数据录入");
            GcHandler handler = new GcHandler();
            handler.setFourSaopinAddrDao(fourSaopinAddrDao);
            deal.process(filePath,handler);
        }else{
            throw new RuntimeException("没有找到入库文件");
        }

    }

    /**
     * 分析 4 5g mro 数据
     *
     * */
    public void analysisMroData(){
        //4g mro 分析 处理流程
        System.out.println("4g5gMro数据统计分析.....");
        String today="2019-06-13";
        List<Integer> earfcn = new ArrayList<>();
        earfcn.add(37900);
        earfcn.add(38400);
        fourSaopinAddrDao.analysisMroData(today,earfcn);

        //5g....


        System.out.println("4g5gMro数据统计分析完毕!!!!");

    }
    public void analysisSaoPinData(){
        //4g mro 分析 处理流程
        String today="2019-06-13";
        System.out.println("4g5g扫频数据统计分析.....");
        saoPinHandle.analysisSaoPinData("4g",today);
        saoPinHandle.analysisSaoPinData("5g",today);
        System.out.println("4g5g扫频数据统计分析完毕！！！！");


    }

}
class GcHandler implements CSVHandler{
    private FourSaopinAddrDao fourSaopinAddrDao;
    @Override
    public void getData(int num, List<String[]> res) {
        List<Map<String,Object>> inser = new ArrayList<>();
        for(String [] item:res){
            Map<String,Object> data = new HashMap<>();
            data.put("districtandcounty",item[0]);
            data.put("gnodeb",item[1]);
            data.put("cellname",item[2]);
            data.put("localcellid",item[3]);
            data.put("longitude",Double.parseDouble(item[4]));
            data.put("latitude",Double.parseDouble(item[5]));
            data.put("workfrequencyband",item[6]);
            data.put("centerfrequency",Integer.parseInt(item[7]));
            data.put("cellbandwidth",Integer.parseInt(item[8].replace("M","")));
            data.put("cellpower",Double.parseDouble(item[9].replace("dBm","")));
            data.put("electronictiltangle",Double.parseDouble(item[10]));
            data.put("mechanicaltiltangle",Double.parseDouble(item[11]));
            data.put("totaldowntiltangle",Double.parseDouble(item[12]));
            data.put("azimuth",Double.parseDouble(item[13]));
            data.put("antennaheight",Double.parseDouble(item[14]));
            data.put("grid",item[15]);
            inser.add(data);
        }
        fourSaopinAddrDao.insert5gGc(inser);
    }

    public void setFourSaopinAddrDao(FourSaopinAddrDao fourSaopinAddrDao) {
        this.fourSaopinAddrDao = fourSaopinAddrDao;
    }
}


class SaoPinHandler implements CSVHandler {
    private FourSaopinAddrDao fourSaopinAddrDao;
    private String today;
    private String tableName;
    private Integer gridid;

    private String currentTimeStamp;
    private Double currentLongitude;
    private Double currentLatitude;

    static Map<String,String> keyMap = new HashMap<>();
    static {
        keyMap.put("100","电信-FDD");
        keyMap.put("450","联通-FDD");
        keyMap.put("1300","移动-FDD");
        keyMap.put("1650","联通-FDD");
        keyMap.put("1825","电信-FDD");
        keyMap.put("2452","电信-FDD");
        keyMap.put("3683","移动-FDD");
        keyMap.put("37900","移动-TDD");
        keyMap.put("38098","移动-TDD");
        keyMap.put("38400","移动-TDD");
        keyMap.put("38544","移动-TDD");
        keyMap.put("38950","移动-TDD");
        keyMap.put("39148","移动-TDD");
        keyMap.put("39292","移动-TDD");
        keyMap.put("40340","联通-TDD");
        keyMap.put("40936","移动-TDD");
        keyMap.put("41140","电信-TDD");
    }
    @Override
    public void getData(int num, List<String[]> res) {
        List<FourSaopinAddr> data = new ArrayList<>();
        if(num==1){
            //移除标题
            res.remove(0);
        }

        for (String[] item:res){

            FourSaopinAddr sao = new FourSaopinAddr();
            sao.setDate(today);
            sao.setGridid(gridid);
            if(StringUtils.isNotBlank(item[0])){
                currentTimeStamp=item[0];
            }
            if(StringUtils.isNotBlank(item[1])){
                currentLongitude=Double.parseDouble(item[1]);
            }
            if(StringUtils.isNotBlank(item[2])){
                currentLatitude=Double.parseDouble(item[2]);
            }
            sao.setTimestamp(currentTimeStamp);
            sao.setLongitude(currentLongitude);
            sao.setLatitude(currentLatitude);

            if(StringUtils.isNotBlank(item[3])){
                sao.setEarfcn(Integer.parseInt(item[3]));
                if(keyMap.get(item[3])!=null){
                    sao.setOperator(keyMap.get(item[3]).split("-")[0]);
                    sao.setPattern(keyMap.get(item[3]).split("-")[1]);
                }


            }
            if(StringUtils.isNotBlank(item[4])){
                sao.setPci(Integer.parseInt(item[4]));
            }
            if(StringUtils.isNotBlank(item[5])){
                sao.setSssRssi(Double.parseDouble(item[5]));
            }
            if(StringUtils.isNotBlank(item[6])){
                sao.setSssRp(Double.parseDouble(item[6]));
            }

            if(StringUtils.isNotBlank(item[7])){
                sao.setR0Rp(Double.parseDouble(item[7]));
            }
            if(StringUtils.isNotBlank(item[8])){
                sao.setR0Rq(Double.parseDouble(item[8]));
            }
            if(StringUtils.isNotBlank(item[9])){
                sao.setR0Cinr(Double.parseDouble(item[9]));
            }


            if(StringUtils.isNotBlank(item[10])){
                sao.setTiming(Integer.parseInt(item[10]));
            }

            data.add(sao);
        }


        fourSaopinAddrDao.insertSwSaopinAddr(data,tableName);

    }

    public void setGridid(Integer gridid) {
        this.gridid = gridid;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setFourSaopinAddrDao(FourSaopinAddrDao fourSaopinAddrDao) {
        this.fourSaopinAddrDao = fourSaopinAddrDao;
    }
}