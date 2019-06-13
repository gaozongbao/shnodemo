package com.cmdi.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DealCSV {

    public void process(String filePath, CSVHandler handler){
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(filePath));
            String line = null;
            int i=1;
            int num=1;
            List<String []> res = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                res.add(line.split(","));
                if(i%5000==0){//每5000条数据触发一次回调函数
                    handler.getData(num,res);
                    res.clear();
                    num++;
                }
                i++;
            }
            if(res.size()!=0){
                //最后不够五千条
                handler.getData(num,res);
                res.clear();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void main(String[] args) {
        DealCSV deal = new DealCSV();
        deal.process("D:\\其他公司\\上海设计院\\地铁\\subwayresult.csv",new myHandler());
    }


}

class myHandler implements CSVHandler {

    @Override
    public void getData(int num,List<String[]> res) {
        System.out.println(res.size());
    }
}