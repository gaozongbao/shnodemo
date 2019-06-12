package com.cmdi.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmdi.model.CnPi;

public interface CnPiDao {
    int deleteByPrimaryKey(Long id);

    int insert(CnPi record);

    int insertSelective(CnPi record);

    CnPi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CnPi record);

    int updateByPrimaryKey(CnPi record);
    
    List<CnPi> getAllCnEciInfo(@Param("province") String province, @Param("date") Date date);
}