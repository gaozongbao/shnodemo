package com.cmdi.dao;

import com.cmdi.model.Gongcan;

public interface GongcanDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Gongcan record);

    int insertSelective(Gongcan record);

    Gongcan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gongcan record);

    int updateByPrimaryKey(Gongcan record);
}