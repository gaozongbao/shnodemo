package com.cmdi.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmdi.dao.CnPiDao;
import com.cmdi.dao.CnPoorQualityCellDao;
import com.cmdi.model.CnPi;
import com.cmdi.model.CnPoorCellTaskDoneMessage;
import com.cmdi.model.CnPoorQualityCell;
import com.cmdi.model.EpData;
import com.cmdi.util.DateUtil;

@Service
@Scope(value = "prototype")
public class CnPi2CnPoorQualityCellServiceImpl {

	@Autowired
	private CnPiDao cnPiDao;
	@Autowired
	private CnPoorQualityCellDao cnPoorQualityCellDao;

	// 得到cnpi表中的所有数据
	public void transCnEcitoCnPoorQuality(String province, Date date) {
		List<CnPi> allCnEciInfo = cnPiDao.getAllCnEciInfo(province, date);
		// System.out.println(allCnEciInfo);
		if (allCnEciInfo == null || allCnEciInfo.isEmpty()) {
			System.err.println(province + " 的核心网小区数据不存在");
			return;
		}
		ArrayList<CnPoorQualityCell> arrayList = new ArrayList<CnPoorQualityCell>();
		for (CnPi cnPi : allCnEciInfo) {
			CnPoorQualityCell cell = cnpi2CnPoorQualityCell(cnPi);
			if (cell != null)
				arrayList.add(cell);
		}
		 System.out.println("-->" + arrayList.size());
		// 删除已有数据
		int deleteCnPoorCgi = cnPoorQualityCellDao.deleteCnPoorCgi(province, date);
		System.out.println("删除province=" + province + ", date=" + DateUtil.getDate(date, "YYYY-MM-dd") + " rows="
				+ deleteCnPoorCgi);
		if (arrayList != null && !arrayList.isEmpty()) {
			int insertBatch = insertCnPoorCgiToDB(arrayList, 1000);
			System.out.println("batch insert count=" + insertBatch);
		} else {
			System.out.println("batch insert count=0");
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("cn_date", date);
		params.put("province", province);
		params.put("ana_status", 1);
		//新增 task数据
		cnPoorQualityCellDao.insertCnTask(params);
	}

	private int insertCnPoorCgiToDB(ArrayList<CnPoorQualityCell> list, int dataSizePerBatch) {
		int count = 0;
		if (list != null && !list.isEmpty()) {
			int page = list.size() / dataSizePerBatch;
			for (int i = 0; i < page; i++) {
				List<CnPoorQualityCell> subList = list.subList(0, dataSizePerBatch);
				count += cnPoorQualityCellDao.insertBatch(subList);
				System.out.println("batch " + count);
				subList.clear();
			}
			if (!list.isEmpty()) {
				count += cnPoorQualityCellDao.insertBatch(list);
				list.clear();
			}
		}
		return count;
	}

	//
	private String eci2cgi(String eci) {
		String res = null;
		try {
			int parseInt = Integer.parseInt(eci.substring(5));
			int enb = parseInt / 256;
			int id = parseInt % 256;
			res = enb + "-" + id;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}

	private CnPoorQualityCell cnpi2CnPoorQualityCell(CnPi cnPi) {
		String eci2cgi = eci2cgi(cnPi.getEci());
		if (eci2cgi == null)
			return null;
		Double hexinwangjietonglv = null;
		Double hexinwangdiaoxianlv = null;
		Double esrvccqiehuanchenggonglv = null;
		Integer allsrvccqiehuanshibaicishu = 0;
		Integer allsrvccqiehuanqingqiucishu = 0;
		Double vvgaochixushichang = null;
		Double hexinwangzhucechenggonglv = null;
		Double hexinwanggaoesrvccqiehuanshibailv = null;
		Double hexinwangzhuceshibailv = null;
		try {
			hexinwangjietonglv = 1 - 1.0 * cnPi.getCnvolteconnfailradionum() / cnPi.getCnvolteconnattempnum();
			hexinwangdiaoxianlv = 1.0 * cnPi.getCnvoltedropnum() / cnPi.getCnvolteconnsucnum();
			

			if (cnPi.getEsrvcchofailradionum() != null)
				allsrvccqiehuanshibaicishu += cnPi.getEsrvcchofailradionum();
			if (cnPi.getAsrvcchofailradionum() != null)
				allsrvccqiehuanshibaicishu += cnPi.getAsrvcchofailradionum();
			if (cnPi.getBsrvcchofailradionum() != null)
				allsrvccqiehuanshibaicishu += cnPi.getBsrvcchofailradionum();
			
			if(cnPi.getAsrvcchoattempnum() != null)
				allsrvccqiehuanqingqiucishu += cnPi.getAsrvcchoattempnum();
			if(cnPi.getBsrvcchoattempnum() != null)
				allsrvccqiehuanqingqiucishu += cnPi.getBsrvcchoattempnum();
			if(cnPi.getEsrvcchoattempnum() != null)
				allsrvccqiehuanqingqiucishu += cnPi.getEsrvcchoattempnum();
			
			hexinwanggaoesrvccqiehuanshibailv = 1.0 * allsrvccqiehuanshibaicishu / allsrvccqiehuanqingqiucishu;
			esrvccqiehuanchenggonglv = 1 - hexinwanggaoesrvccqiehuanshibailv;
			
			vvgaochixushichang = cnPi.getVvcondelay();
			
			hexinwangzhuceshibailv = 1.0 * cnPi.getImsregfailradionum() / cnPi.getImsregattempnum();
			hexinwangzhucechenggonglv = 1 - hexinwangzhuceshibailv;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		boolean lowCNvolteConnRatio = hexinwangjietonglv != null && hexinwangjietonglv < 0.95 ? true : false;
		boolean highCNvolteDropRatio = hexinwangdiaoxianlv != null && hexinwangdiaoxianlv > 0.04 ? true : false;
		boolean highCNesrvccDropRatio = (esrvccqiehuanchenggonglv != null && allsrvccqiehuanshibaicishu != null
				&& esrvccqiehuanchenggonglv < 0.92 && allsrvccqiehuanshibaicishu > 5) ? true : false;
		boolean highCNConnDelay = vvgaochixushichang != null && vvgaochixushichang > 3500 ? true : false;
		boolean highCNRegFailureRatio = hexinwangzhucechenggonglv != null && hexinwangzhucechenggonglv < 0.97 ? true
				: false;

		try {
			if (lowCNvolteConnRatio || highCNvolteDropRatio || highCNesrvccDropRatio || highCNConnDelay
					|| highCNRegFailureRatio) {
				CnPoorQualityCell cell = new CnPoorQualityCell();
				cell.setDate(cnPi.getDate());
				cell.setProvince(cnPi.getProvince());
				cell.setCity(cnPi.getCity());
				cell.setCellname(cnPi.getCellname());
				cell.setCgi(eci2cgi);
				cell.setLowcnvolteconnratio(lowCNvolteConnRatio);
				cell.setHighcnvoltedropratio(highCNvolteDropRatio);
				cell.setHighcnesrvccdropratio(highCNesrvccDropRatio);
				cell.setHighcnconndelay(highCNConnDelay);
				cell.setHighcnregfailureratio(highCNRegFailureRatio);
				cell.setCnvolteconnratio(Double.isNaN(hexinwangjietonglv) ? null : hexinwangjietonglv * 100);
				cell.setCnvoltedropratio(Double.isNaN(hexinwangdiaoxianlv) ? null : hexinwangdiaoxianlv * 100);
				cell.setCnesrvccdropratio(Double.isNaN(hexinwanggaoesrvccqiehuanshibailv) ? null
						: hexinwanggaoesrvccqiehuanshibailv * 100);
				cell.setVvcondelay(vvgaochixushichang);
				cell.setCnregfailureratio(Double.isNaN(hexinwangzhuceshibailv) ? null : hexinwangzhuceshibailv * 100);
				return cell;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
}
