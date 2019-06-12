package com.cmdi.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmdi.dao.EpDataDao;
import com.cmdi.dao.GcTaskDao;
import com.cmdi.model.EpData;
import com.cmdi.model.GcTask;

@Service
@Transactional
public class UpDateServiceImpl {
	@Autowired
	private EpDataDao epDataDao;
	@Autowired
	private GcTaskDao gcTaskDao;
	
	public void delete() {
		//
	}
	/**
	 * 删除某省工参数据
	 * */
	public int deleteGcByProvince(String province) {
		int deleteByProvince = epDataDao.deleteByProvince(province);
		return deleteByProvince;
	}
	/**
	 * 插入新的工参数据
	 * */
	public int insertGc(ArrayList<EpData> list) {
		int dataListSize = 10000;
		int count = 0;
		if(list != null && !list.isEmpty()) {
			int page = list.size() / dataListSize;
			for(int i = 0; i < page; i++) {
				List<EpData> subList = list.subList(0, dataListSize);
				count += epDataDao.insertBatch(subList);
				subList.clear();
			}
			if(!list.isEmpty()) {
				count += epDataDao.insertBatch(list);
				list.clear();
			}
		}
//		int insertBatch = epDataDao.insertBatch(list);
//		return insertBatch;
		return count;
	}
	/**
	 * 新增工参任务表数据
	 * */
	public int insertGcTask(GcTask task) {
		int insertSelective = gcTaskDao.insertSelective(task);
		return insertSelective;
	}
	/**
	 * 更新基站、小区数目
	 * */
	public int insertEnbCellNum(HashMap<String, Object> map) {
//		int insertcellenbnumtable = epDataDao.insertcellenbnumtablewithscenarioandvendor(map);
//		int updatecellenbnumtable = epDataDao.updatecellenbnumtablewithscenarioandvendor(map);
//		return insertcellenbnumtable + updatecellenbnumtable;
		int deletecellenbnum = epDataDao.deletecellenbnum(map);
		System.out.println("delete 基站、小区 rows " + deletecellenbnum);
		return epDataDao.insertcellenbnumtablewithscenarioandvendor2(map);
	}
	
	/**
	 * 数据操作
	 * */
	public void update(String province,ArrayList<EpData> list,GcTask task,HashMap<String, Object> map) {
		int deleteGcByProvince = deleteGcByProvince(province);
		System.out.println("删除 " + province + " 工参数据行数: " + deleteGcByProvince);
		int insertGc = insertGc(list);
		System.out.println("新增 " + province + " 工参数据行数: " + insertGc);
		int insertGcTask = insertGcTask(task);
		System.out.println("新增 " + province + " 工参任务行数: " + insertGcTask);
		int insertEnbCellNum = insertEnbCellNum(map);
		System.out.println("新增 " + province + " 基站、小区行数: " + insertEnbCellNum);
	}
	
	public void updateSimpleGc(String province) {
		System.out.println("正在处理simple工参");
		int deleteSimpleGc = epDataDao.deleteSimpleGc(province);
		System.out.println("delete simple gc " + deleteSimpleGc);
		int updateSimpleGc = epDataDao.updateSimpleGc(province);
		System.out.println("insert simple gc " + updateSimpleGc);
	}
}
