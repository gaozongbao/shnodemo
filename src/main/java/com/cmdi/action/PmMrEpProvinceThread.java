package com.cmdi.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmdi.dao.MrPmEpDataDao;
import com.cmdi.impl.InsertMonthDataImpl;
import com.cmdi.model.MonthParamData;
import com.cmdi.model.TlthrecTableDate;
import com.cmdi.util.DateUtil;
import com.cmdi.util.ExcelColumnToIndex;

public class PmMrEpProvinceThread implements Runnable {
	private String province;
	private String date;	
	private SqlSessionTemplate bean;
	private ClassPathXmlApplicationContext context;


	public PmMrEpProvinceThread(String date, String province,SqlSessionTemplate bean,ClassPathXmlApplicationContext context) 
	{
		super();
		this.province = province;		
		this.date = date;
		this.bean = bean;
		this.context = context;	
		}

	@Override
	public void run() {
		InsertMonthDataImpl insertMonthDataImpl = context.getBean(com.cmdi.impl.InsertMonthDataImpl.class);
		HashMap<String, Object>task = new HashMap<String,Object>();	
		HashMap<String, Object>t2 = new HashMap<String,Object>();
		String month= date.substring(0, 7);
		t2.put("month", month);
		t2.put("province", province);
		List<String> pString=insertMonthDataImpl.getRecentDate(t2);
		System.out.println("查询是否已存在"+pString);
		if(pString.size()==0){
			task.put("date", date);
			task.put("province", province);
			if(province.equals("all")){
				countryData();
			}else {
				List<String> mrp= insertMonthDataImpl.getMrP(task);
				List<String> pmp=insertMonthDataImpl.getPmP(task);
				System.out.println("mr是否有"+mrp+"pm:"+pmp);
				if(mrp.size()!=0 && pmp.size()!=0){	
					provinceData();
					cityData();	
				}
			}			
		}
	}
	private void countryData() {
		InsertMonthDataImpl insertMonthDataImpl = context.getBean(com.cmdi.impl.InsertMonthDataImpl.class);
		//删除表中该月全国的数据
		String month =date.substring(0,7);
		HashMap<String, Object> hh = new HashMap<String,Object>();
		hh.put("province", province);
		hh.put("month", month);
		int deleteP = insertMonthDataImpl.deleteByProvinceAndMonth(hh);
		System.out.println("删除"+deleteP+"条数据");
		//添加数据
		HashMap<String, Object> param = new HashMap<String,Object>();
		List<MonthParamData> alist = new ArrayList<MonthParamData>();		
		String ep_date = insertMonthDataImpl.getCNewCellData(date);
		System.out.println("EP最新时间"+ep_date);
		if(ep_date == null){
			System.out.println("111");
			param.put("mro_date", date); 
			param.put("pm_date",date);
			param.put("ep_date", date);
			param.put("pq_date",date);
			List<MonthParamData> list = insertMonthDataImpl.getAllCountryData(param);
			System.out.println("长度"+list.size()+list);
			MonthParamData pdata = new MonthParamData();
//			for(MonthParamData pdata:list){
				pdata.setProvince("all");
				pdata.setCity("all");
				pdata.setCellnum(list.get(0).getCellnum());
				pdata.setEsrvcchoratio(list.get(0).getEsrvcchoratio());
				pdata.setEsrvcchosucratio(list.get(0).getEsrvcchosucratio());
				pdata.setHighvoltedropratiocellnum(list.get(0).getHighvoltedropratiocellnum());
				pdata.setHighvoltemrdlplratiocellnum(list.get(0).getHighvoltemrdlplratiocellnum());
				pdata.setHigvoltemrulhplratiocellnum(list.get(0).getHigvoltemrulhplratiocellnum());
				pdata.setLowesrvcchosucratiocellnum(list.get(0).getLowesrvcchosucratiocellnum());
				pdata.setLowvolteconnratiocellnum(list.get(0).getLowvolteconnratiocellnum());
				pdata.setLtecellnum(list.get(0).getLtecellnum());
				pdata.setLteenbnum(list.get(0).getLteenbnum());
				pdata.setMonth(list.get(0).getMonth());
				pdata.setMruldlratio(list.get(0).getMruldlratio());
				pdata.setMrulplratio(list.get(0).getMrulplratio());
				pdata.setSinglecellmaxactvolteusernum(list.get(0).getSinglecellmaxactvolteusernum());
				pdata.setTrafficvaluegt1erlcellnum(list.get(0).getTrafficvaluegt1erlcellnum());
				pdata.setTwohitwolowcellnum(list.get(0).getTwohitwolowcellnum());
				pdata.setVolteradconnratio(list.get(0).getVolteradconnratio());
				pdata.setVolteraddropratio(list.get(0).getVolteraddropratio());
				pdata.setVoltetraval(list.get(0).getVoltetraval());
				pdata.setVoltetravaleq0erlcellnum(list.get(0).getVoltetravaleq0erlcellnum());
				alist.add(pdata);
//			}
			System.out.println(alist.size()+"hah"+alist);
				if(alist.size()!=0){
					int insertp= insertMonthDataImpl.insertBatch(alist);
					System.out.println(insertp);
					System.out.println(province + " 数据完成"+insertp+"条");
					//插入TlthrecTableDate
					TlthrecTableDate tlthrecTableDate = new TlthrecTableDate();
					tlthrecTableDate.setMonth(list.get(0).getMonth());
					tlthrecTableDate.setProvince(province);
					int insert = insertMonthDataImpl.insertSelective(tlthrecTableDate);
					System.out.println("tlthrecTableDate表中插入"+insert+"条数据");
				}			

		}else {
			param.put("mro_date", date); 
			param.put("pm_date",date);
			param.put("ep_date", ep_date);
			param.put("pq_date",date);	
			List<MonthParamData> list = insertMonthDataImpl.getAllCountryData(param);
			System.out.println("国家"+list.size()+list);
			if(list.size()!=0){
				int insertp= insertMonthDataImpl.insertBatch(list);
				System.out.println(province + " 数据2完成"+insertp+"条");
				//插入TlthrecTableDate
				TlthrecTableDate tlthrecTableDate = new TlthrecTableDate();
				tlthrecTableDate.setMonth(list.get(0).getMonth());
				tlthrecTableDate.setProvince(list.get(0).getProvince());
				int insert = insertMonthDataImpl.insertSelective(tlthrecTableDate);
				System.out.println("tlthrecTableDate2表中插入"+insert+"条数据");
			}	
		}			
//		if(list.size()!=0){
//			int insertp= insertMonthDataImpl.insertBatch(list);
//			System.out.println(province + " 数据完成"+insertp+"条");
//			//插入TlthrecTableDate
//			TlthrecTableDate tlthrecTableDate = new TlthrecTableDate();
//			tlthrecTableDate.setMonth(list.get(0).getMonth());
//			tlthrecTableDate.setProvince(province);
//			int insert = insertMonthDataImpl.insertSelective(tlthrecTableDate);
//			System.out.println("tlthrecTableDate表中插入"+insert+"条数据");
//		}	
	}
	private void provinceData() {
		InsertMonthDataImpl insertMonthDataImpl = context.getBean(com.cmdi.impl.InsertMonthDataImpl.class);
		//删除
		String month =date.substring(0,7);
		HashMap<String, Object> hh = new HashMap<String,Object>();
		hh.put("province", province);
		hh.put("month", month);
		int deleteP = insertMonthDataImpl.deleteByProvinceAndMonth(hh);
		System.out.println("删除"+deleteP+"条数据");
		//添加数据
			HashMap<String, Object> param = new HashMap<String,Object>();	
			String ep_date = insertMonthDataImpl.getPNewCellData(date);
			System.out.println("EP最新时间"+ep_date);
			if(ep_date == null){
				param.put("mro_date", date); 
				param.put("pm_date",date);
				param.put("ep_date", date);
				param.put("pq_date",date);
			}else {
				param.put("mro_date", date); 
				param.put("pm_date",date);
				param.put("ep_date", ep_date);
				param.put("pq_date",date);	
			}
			param.put("province", province);
			System.out.println(param);
			List<MonthParamData> list = insertMonthDataImpl.getProvinceData(param);
			System.out.println("省份共有"+list.size()+list);
			if(list.size()!=0){
				int insertp =insertMonthDataImpl.insertBatch(list);
				System.out.println(province + " 数据完成"+insertp+"条");
				
				TlthrecTableDate tlthrecTableDate = new TlthrecTableDate();
				tlthrecTableDate.setMonth(list.get(0).getMonth());
				tlthrecTableDate.setProvince(province);
				int insert = insertMonthDataImpl.insertSelective(tlthrecTableDate);
				System.out.println("tlthrecTableDate表中插入"+insert+"条数据");
			}		
	}
	private void cityData() {
		InsertMonthDataImpl insertMonthDataImpl = context.getBean(com.cmdi.impl.InsertMonthDataImpl.class);
			HashMap<String, Object> param = new HashMap<String,Object>();	
			String ep_date = insertMonthDataImpl.getCityNewCellData(date);
			System.out.println("EP最新时间"+ep_date);
			if(ep_date == null){
				param.put("mro_date", date); 
				param.put("pm_date",date);
				param.put("ep_date", date);
				param.put("pq_date",date);
			}else {
				param.put("mro_date", date); 
				param.put("pm_date",date);
				param.put("ep_date", ep_date);
				param.put("pq_date",date);	
			}	
			param.put("province", province);
			System.out.println("城市"+param);
			List<MonthParamData> list = insertMonthDataImpl.getCityData(param);
			System.out.println("city共有"+list.size()+list);
			if(list.size()!=0){
				int insert = insertMonthDataImpl.insertBatch(list);
				System.out.println("城市处理完成，共插入"+insert+"条数据");				
			}
	}
}