package com.cmdi.impl;

import com.cmdi.dao.FourSaopinAddrDao;
import com.cmdi.model.FourSaopinAddr;
import com.cmdi.util.CSVHandler;
import com.cmdi.util.DealCSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class LteDataImportService {
    @Autowired
    private FourSaopinAddrDao fourSaopinAddrDao;

    public void importLteSaoPinData(String filePath){
        if(StringUtils.isNotBlank(filePath)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(new Date());


            DealCSV deal = new DealCSV();
            System.out.println("lte 扫频数据录入");
            SaoPinHandler saoPinHandler = new SaoPinHandler();
            saoPinHandler.setToday(today);
            saoPinHandler.setFourSaopinAddrDao(fourSaopinAddrDao);
            deal.process(filePath,saoPinHandler);


        }else{
            throw new RuntimeException("没有找到入库文件");
        }

    }


}

class SaoPinHandler implements CSVHandler {
    private FourSaopinAddrDao fourSaopinAddrDao;
    private String today;

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
                sao.setOperator(keyMap.get(item[3]).split("-")[0]);
                sao.setPattern(keyMap.get(item[3]).split("-")[1]);

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
        fourSaopinAddrDao.insertSwSaopinAddr(data);

    }

    public void setToday(String today) {
        this.today = today;
    }




    public void setFourSaopinAddrDao(FourSaopinAddrDao fourSaopinAddrDao) {
        this.fourSaopinAddrDao = fourSaopinAddrDao;
    }
}