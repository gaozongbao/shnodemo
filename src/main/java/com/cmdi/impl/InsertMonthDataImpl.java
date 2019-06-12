package com.cmdi.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmdi.dao.MrPmEpDataDao;
import com.cmdi.model.MonthParamData;
import com.cmdi.model.TlthrecTableDate;

@Service
@Transactional

public class InsertMonthDataImpl {
	
	@Autowired
	MrPmEpDataDao mpedatadao;
	
	//查询该日期内是否被分析
	public List<MonthParamData> getAllCountryData(HashMap<String, Object> param){
		
		List<MonthParamData> countryData = mpedatadao.getAllCountryData(param);
		return countryData;
	}

	public List<MonthParamData> getProvinceData(HashMap<String, Object> param){
		List<MonthParamData> provinceData = mpedatadao.getProvinceData(param);
		return provinceData;		
	}

	public List<MonthParamData> getCityData(HashMap<String, Object> param){
		List<MonthParamData> cityData = mpedatadao.getCityData(param);
		return cityData;
	}
	public List<String> getRecentDate(HashMap<String, Object> param){
		List<String> pString = mpedatadao.getRecentDate(param);
		return pString;
	}
	public List<String> getPmP(HashMap<String, Object> param){
		List<String> string = mpedatadao.getPmP(param);
		return string;
	}
	
	public List<String> getMrP(HashMap<String, Object> param){
		List<String> string = mpedatadao.getMrP(param);
		return string;
	}
	public String getCNewCellData(String date){
		String string = mpedatadao.getCNewCellData(date);
		return string;
	}
	public String getPNewCellData(String date){
		String string = mpedatadao.getPNewCellData(date);
		return string;
	}
	public String getCityNewCellData(String date){
		String string = mpedatadao.getCityNewCellData(date);
		return string;
	}
	public int insertBatch(List<MonthParamData> list){
		int insertBatch = mpedatadao.insertBatch(list);
		return insertBatch;
	}

	public int insertSelective(TlthrecTableDate tlthrecTable){
		int insertSelective = mpedatadao.insertSelective(tlthrecTable);
		return insertSelective;
	}
	public int deleteByProvinceAndMonth(HashMap<String, Object> param){
		int delete = mpedatadao.deleteByProvinceAndMonth(param);
		return delete;
	}

	
	
	

}
