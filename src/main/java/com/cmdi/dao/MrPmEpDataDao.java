package com.cmdi.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cmdi.model.GcTask;
import com.cmdi.model.MonthParamData;
import com.cmdi.model.TlthrecTableDate;

@Repository
public interface MrPmEpDataDao {
	
	public List<MonthParamData> getAllCountryData(HashMap<String, Object> param);

	public List<MonthParamData> getProvinceData(HashMap<String, Object> param);

	public List<MonthParamData> getCityData(HashMap<String, Object> param);

	public List<String> getRecentDate(HashMap<String, Object> param);
	
	public List<String> getPmP(HashMap<String, Object> param);
	
	public List<String> getMrP(HashMap<String, Object> param);
	
	public String getCityNewCellData(String date);
	
	public String getCNewCellData(String date);
	
	public String getPNewCellData(String date);
	
	int deleteByProvinceAndMonth(HashMap<String, Object> param);
	
	int insertBatch(List<MonthParamData> list);

	int insertSelective(TlthrecTableDate tlthrecTable);

}
