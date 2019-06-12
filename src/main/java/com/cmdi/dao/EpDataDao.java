package com.cmdi.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cmdi.model.EpData;

@Repository
public interface EpDataDao {
	int deleteByProvince(String province);
	int insertBatch(List<EpData> lists);
	int insertcellenbnumtable(HashMap<String, Object> map);
	int insertcellenbnumtablewithscenarioandvendor(HashMap<String, Object> map);
	int updatecellenbnumtablewithscenarioandvendor(HashMap<String, Object> map);
	int insertglobalcellenbnumtable(HashMap<String, Object> map);
	
	int insertcellenbnumtablewithscenarioandvendor2(HashMap<String, Object> map);
	int insertglobalcellenbnumtablewithscenarioandvendor2(HashMap<String, Object> map);
	
	int deletecellenbnum(HashMap<String, Object> map);
	int deleteglobalcellenbnum(HashMap<String, Object> map);
	
	int updateSimpleGc(String province);
	
	int deleteSimpleGc(String province);
}
