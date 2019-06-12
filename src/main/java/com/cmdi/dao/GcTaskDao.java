package com.cmdi.dao;

import org.springframework.stereotype.Repository;

import com.cmdi.model.GcTask;

@Repository
public interface GcTaskDao {
	int insert(GcTask gcTask);
	int insertSelective(GcTask gcTask);
	GcTask getMaxDateOfProvinces();
}
