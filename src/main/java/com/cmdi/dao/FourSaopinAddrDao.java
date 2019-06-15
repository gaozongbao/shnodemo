package com.cmdi.dao;



import com.cmdi.model.FourSaopinAddr;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FourSaopinAddrDao {

    public void insertSwSaopinAddr(@Param("list") List<FourSaopinAddr> list,@Param("tableName") String tableName);
    public void insert5gGc(List<Map<String,Object>> list);
    public void deleteSwSaopinAddr(@Param("date") String date,@Param("tableName") String tableName);
}