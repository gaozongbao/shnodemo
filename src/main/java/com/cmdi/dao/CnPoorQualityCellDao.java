package com.cmdi.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmdi.model.CnPoorQualityCell;

public interface CnPoorQualityCellDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CnPoorQualityCell record);

    int insertSelective(CnPoorQualityCell record);

    CnPoorQualityCell selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CnPoorQualityCell record);

    int updateByPrimaryKey(CnPoorQualityCell record);
    
    int insertBatch(@Param("list") List<CnPoorQualityCell> list);
    
    int deleteCnPoorCgi(@Param("province") String province, @Param("date") Date date);
    
    int insertCnTask(HashMap<String, Object> params);
    
}