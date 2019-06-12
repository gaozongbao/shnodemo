package com.cmdi.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmdi.model.VCnetoetable;

public interface VCnetoetableDao {

	
    List<VCnetoetable> sumall(@Param("date") Date date);    
    List<VCnetoetable> sumByVendor(@Param("date") Date date);
    List<VCnetoetable> sumByScenario(@Param("date") Date date);
    List<VCnetoetable> sumByScenarioVendor(@Param("date") Date date);
    List<VCnetoetable> sumByProvince(@Param("date") Date date);
    List<VCnetoetable> sumByProvinceVendor(@Param("date") Date date);
    List<VCnetoetable> sumByProvinceScenario(@Param("date") Date date);
    List<VCnetoetable> sumByProvinceScenarioVendor(@Param("date") Date date);
    List<VCnetoetable> sumByProvinceCity(@Param("date") Date date);
    List<VCnetoetable> sumByProvinceCityVendor(@Param("date") Date date);
    List<VCnetoetable> sumByProvinceCityScenario(@Param("date") Date date);
    List<VCnetoetable> sumByProvinceCityScenarioVendor(@Param("date") Date date);

    

}
