package com.cmdi.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FivegMroDataDao {
    public void deleteMroAnalysis(@Param("tableName")String tableName,@Param("date") String date);
    public void analysisMroData(@Param("mroCoverTable")String mroCoverTable,@Param("mroTable")String mroTable,@Param("mroTable") String date, @Param("earfcn") List<Integer> earfcn);
    public void insertEffectMroData(@Param("cgi") String cgi);
    public void insertunEffectMroData(@Param("cgi") String cgi);

    public List<Map<String,Object>> getEffectMroCgi();
    public List<Map<String,Object>> getUnEffectMroCgi();

}
