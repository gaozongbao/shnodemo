package com.cmdi.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmdi.dao.VCnetoetableDao;
import com.cmdi.dao.VCnresulttableDao;
import com.cmdi.model.VCnetoetable;
import com.cmdi.model.VCnresulttable;

@Service
@Transactional
public class EtoEIndexSumServiceImpl {
	@Autowired
	private VCnetoetableDao vCnetoetableDao;

	@Autowired
	private VCnresulttableDao vCnresulttableDao;

	/**
	 * 根据接口文档的描述 实现端到端分场景分场景分省市指标汇总
	 * 
	 * @param date
	 */
	public void sum2table(Date date) {
		List<VCnresulttable> resultlist = new ArrayList<VCnresulttable>();

		try {
			// <!-- 全国所有地市不分场景不分厂家统计结果 -->
			List<VCnetoetable> sumalllist = vCnetoetableDao.sumall(date);
			resultlist.addAll(convert2ResultList(sumalllist, "all", "all",
					"all", "all"));

			// <!-- 全国所有地市不分场景分厂家统计结果 -->
			List<VCnetoetable> sumByVendorlist = vCnetoetableDao.sumByVendor(date);
			resultlist.addAll(convert2ResultList(sumByVendorlist, "all", "all",
					"all", ""));

			// <!-- 全国所有地市分场景不分厂家统计结果 -->
			List<VCnetoetable> sumByScenariolist = vCnetoetableDao
					.sumByScenario(date);
			resultlist.addAll(convert2ResultList(sumByScenariolist, "all",
					"all", "", "all"));

			// <!-- 全国所有地市分场景分厂家统计结果 -->
			List<VCnetoetable> sumByScenarioVendorlist = vCnetoetableDao
					.sumByScenarioVendor(date);
			resultlist.addAll(convert2ResultList(sumByScenarioVendorlist,
					"all", "all", "", ""));

			// <!-- 分省所有地市不分场景不分厂家统计结果 -->
			List<VCnetoetable> sumByProvincelist = vCnetoetableDao
					.sumByProvince(date);
			resultlist.addAll(convert2ResultList(sumByProvincelist, "", "all",
					"all", "all"));

			// <!-- 分省所有地市不分场景分厂家统计结果 -->
			List<VCnetoetable> sumByProvinceVendorlist = vCnetoetableDao
					.sumByProvinceVendor(date);
			resultlist.addAll(convert2ResultList(sumByProvinceVendorlist, "",
					"all", "all", ""));

			// <!-- 分省所有地市分场景不分厂家统计结果 -->
			List<VCnetoetable> sumByProvinceScenariolist = vCnetoetableDao
					.sumByProvinceScenario(date);
			resultlist.addAll(convert2ResultList(sumByProvinceScenariolist, "",
					"all", "", "all"));

			// 分省所有地市分场景分厂家统计结果
			List<VCnetoetable> sumByProvinceScenarioVendorlist = vCnetoetableDao
					.sumByProvinceScenarioVendor(date);
			resultlist.addAll(convert2ResultList(
					sumByProvinceScenarioVendorlist, "", "all", "", ""));

			// 分省分地市不分场景不分厂家统计结果
			List<VCnetoetable> sumByProvinceCitylist = vCnetoetableDao
					.sumByProvinceCity(date);
			resultlist.addAll(convert2ResultList(sumByProvinceCitylist, "", "",
					"all", "all"));

			// 分省分地市不分场景分厂家统计结果
			List<VCnetoetable> sumByProvinceCityVendorlist = vCnetoetableDao
					.sumByProvinceCityVendor(date);
			resultlist.addAll(convert2ResultList(sumByProvinceCityVendorlist,
					"", "", "all", ""));

			// 分省分地市分场景不分厂家统计结果
			List<VCnetoetable> sumByProvinceCityScenariolist = vCnetoetableDao
					.sumByProvinceCityScenario(date);
			resultlist.addAll(convert2ResultList(sumByProvinceCityScenariolist,
					"", "", "", "all"));

			// 分省分地市分场景分厂家统计结果
			List<VCnetoetable> sumByProvinceCityScenarioVendorlist = vCnetoetableDao
					.sumByProvinceCityScenarioVendor(date);
			resultlist.addAll(convert2ResultList(
					sumByProvinceCityScenarioVendorlist, "", "", "", ""));

			vCnresulttableDao.insertBatch(resultlist);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private List<VCnresulttable> convert2ResultList(List<VCnetoetable> list,
			String province, String city, String scenario, String vendor) {
		List<VCnresulttable> resultlist = new ArrayList<VCnresulttable>();

		for (VCnetoetable e2e : list) {

			VCnresulttable result = new VCnresulttable();
			
			result.setCnDate(e2e.getDate());

			if ("".equals(province)) {
				result.setProvince(e2e.getProvince());
			} else {
				result.setProvince(province);
			}

			if ("".equals(city)) {
				result.setCity(e2e.getCity());
			} else {
				result.setCity(city);
			}

			if ("".equals(scenario)) {
				result.setScenario(e2e.getScenario());
			} else {
				result.setScenario(scenario);
			}

			if ("".equals(vendor)) {
				result.setVendor(e2e.getVendor());
			} else {
				result.setVendor(vendor);
			}

			// VoLTE接通率
			result.setVolteconnratio(e2e.getVolteconnratio());
			// VoLTE掉线率
			result.setVoltedropratio(e2e.getVoltedropratio());
			// eSRVCC切换成功率
			result.setEsrvcchosucratio(e2e.getEsrvcchosucratio());

			resultlist.add(result);
		}

		return resultlist;

	}

}
