package com.cmdi.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.net.ftp.FTPFile;
import org.mybatis.spring.SqlSessionTemplate;

import com.cmdi.impl.FtpClient;
import com.cmdi.model.EpData;
import com.cmdi.model.GcTask;
import com.cmdi.model.HeaderColumnIndex;
import com.cmdi.model.Tuple2;
import com.cmdi.util.DateUtil;
import com.cmdi.util.ExcelColumnToIndex;
import com.csvreader.CsvReader;

public class HandleGcThread implements Runnable {
	private String province;
	private Tuple2<String, FTPFile> tuple;
	private SqlSessionTemplate bean;
	private String fileEncoding;
	private FtpClient client;

	public HandleGcThread(String province, Tuple2<String, FTPFile> tuple, SqlSessionTemplate bean,
			String fileEncoding) {
		super();
		this.province = province;
		this.tuple = tuple;
		this.bean = bean;
		this.fileEncoding = fileEncoding;
		client = new FtpClient(MainT.hostIp, MainT.port, MainT.userName, MainT.passWord, MainT.controlEncoding);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		start1();
		start2();
	}
	
	/**
	 * 文件的列位置固定
	 * */
	private void start1() {
		// 删除该省工参表数据
		int delete = bean.delete("com.cmdi.dao.EpDataDao.deleteByProvince", province);
		System.out.println("delete " + province + " gc rows " + delete);
		// 插入新的工参数据
		ArrayList<EpData> list = new ArrayList<EpData>();
		CsvReader reader = null;
		try {
			reader = client.getFtpFile(MainT.ftpPath, tuple.getS(), fileEncoding);
			reader.readHeaders();
			int cgiIndex = ExcelColumnToIndex.excelColStrToNum("L") - 1;
			int enodebnameIndex = ExcelColumnToIndex.excelColStrToNum("F") - 1;
			int cellnameIndex = ExcelColumnToIndex.excelColStrToNum("G") - 1;
			int tacIndex = ExcelColumnToIndex.excelColStrToNum("H") - 1;
			int localcellidIndex = ExcelColumnToIndex.excelColStrToNum("J") - 1;
			// int enbidIndex = ExcelColumnToIndex.excelColStrToNum("XXX")-1;
			int longitudeIndex = ExcelColumnToIndex.excelColStrToNum("R") - 1;
			int latitudeIndex = ExcelColumnToIndex.excelColStrToNum("S") - 1;
			int coveragetypeIndex = ExcelColumnToIndex.excelColStrToNum("N") - 1;
			int AzimuthIndex = ExcelColumnToIndex.excelColStrToNum("AA") - 1;
			int antennaheightIndex = ExcelColumnToIndex.excelColStrToNum("AB") - 1;
			int totaldowntiltangleIndex = ExcelColumnToIndex.excelColStrToNum("Z") - 1;
			int carrierffrequencynumIndex = ExcelColumnToIndex.excelColStrToNum("K") - 1;
			int workfrequencybandIndex = ExcelColumnToIndex.excelColStrToNum("T") - 1;
			int pciIndex = ExcelColumnToIndex.excelColStrToNum("I") - 1;
			int MaxTransmitPowerIndex = ExcelColumnToIndex.excelColStrToNum("AD") - 1;
			int CoverSceneIndex = ExcelColumnToIndex.excelColStrToNum("O") - 1;
			int ProvinceNameIndex = ExcelColumnToIndex.excelColStrToNum("C") - 1;
			int CityNameIndex = ExcelColumnToIndex.excelColStrToNum("D") - 1;
			int DistrictandcountyIndex = ExcelColumnToIndex.excelColStrToNum("E") - 1;
			int VendorIndex = ExcelColumnToIndex.excelColStrToNum("P") - 1;
			int ElectronicTiltAngleIndex = ExcelColumnToIndex.excelColStrToNum("X") - 1;
			int MechanicalTiltAngleIndex = ExcelColumnToIndex.excelColStrToNum("Y") - 1;
			// int IsCoreAreaIndex =
			// ExcelColumnToIndex.excelColStrToNum("XXX")-1;
			int allCount = 0;
			while (reader.readRecord()) {
				allCount++;
				try {
					EpData data = new EpData();
					data.setCgi(reader.get(cgiIndex).substring(7));
					data.setEnodebname(reader.get(enodebnameIndex));
					data.setCellname(reader.get(cellnameIndex));
					data.setTac(reader.get(tacIndex));
					data.setLocalcellid(reader.get(localcellidIndex));
					data.setEnbid(reader.get(cgiIndex).substring(7).split("-")[0]);
					data.setLongitude(Double.parseDouble(reader.get(longitudeIndex)));
					data.setLatitude(Double.parseDouble(reader.get(latitudeIndex)));
					data.setCoveragetype(reader.get(coveragetypeIndex));
					data.setAzimuth(Double.parseDouble(reader.get(AzimuthIndex)));
					data.setAntennaheight(Double.parseDouble(reader.get(antennaheightIndex)));
					data.setTotaldowntiltangle(Double.parseDouble(reader.get(totaldowntiltangleIndex)));
					data.setCarrierffrequencynum(Integer.parseInt(reader.get(carrierffrequencynumIndex)));
					data.setWorkfrequencyband(reader.get(workfrequencybandIndex));
					data.setPci(reader.get(pciIndex));
					data.setMaxtransmitpower(Double.parseDouble(reader.get(MaxTransmitPowerIndex)));
					data.setCoverscene(getScenario(reader.get(coveragetypeIndex), reader.get(CoverSceneIndex)));
					data.setProvincename(reader.get(ProvinceNameIndex));
					data.setCityname(reader.get(CityNameIndex));
					data.setDistrictandcounty(reader.get(DistrictandcountyIndex));
					data.setVendor(getVendor(reader.get(VendorIndex)));
					data.setElectronictiltangle(Double.parseDouble(reader.get(ElectronicTiltAngleIndex)));
					data.setMechanicaltiltangle(Double.parseDouble(reader.get(MechanicalTiltAngleIndex)));
					// data.setIscorearea(null);
					list.add(data);
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("province=" + province + ",date=" + tuple.getT() + ": 第" + allCount + "条记录数据异常");
					e.printStackTrace();
				}
			}
			int insert = bean.insert("com.cmdi.dao.EpDataDao.insertBatch", list);
			System.out.println("insert " + province + " gc rows " + insert);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			reader.close();
		}
		// 新增工参任务表数据
		GcTask task = new GcTask();
		task.setGcDate(DateUtil.getDate(tuple.getT(), MainT.DATEFORMAT));
		task.setProvince(province);
		client.disconnect();
		int insert2 = bean.insert("com.cmdi.dao.GcTaskDao.insertSelective", task);
		System.out.println("insert " + province + " gctask rows " + insert2);
		System.out.println("处理 " + province + " 工参数据完成");
	}

	/**
	 * 根据表头自动确定列
	 * */
	private void start2() {
		// 删除该省工参表数据
		int delete = bean.delete("com.cmdi.dao.EpDataDao.deleteByProvince", province);
		System.out.println("delete " + province + " gc rows " + delete);
		// 插入新的工参数据
		ArrayList<EpData> list = new ArrayList<EpData>();
		CsvReader reader = null;
		try {
			reader = client.getFtpFile(MainT.ftpPath, tuple.getS(), fileEncoding);
			reader.readHeaders();
			String[] headers = reader.getHeaders();
//			print(headers);
			HeaderColumnIndex headerColumnIndex = new HeaderColumnIndex();
			headerColumnIndex.getIndexFromHeader(headers);
			System.out.println(headerColumnIndex);
			int allCount = 0;
			while (reader.readRecord()) {
				allCount++;
				try {
					EpData data = new EpData();
					data.setCgi(reader.get(headerColumnIndex.cgiIndex).substring(7));
					data.setEnodebname(reader.get(headerColumnIndex.enodebnameIndex));
					data.setCellname(reader.get(headerColumnIndex.cellnameIndex));
					data.setTac(reader.get(headerColumnIndex.tacIndex));
					data.setLocalcellid(reader.get(headerColumnIndex.localcellidIndex));
					data.setEnbid(reader.get(headerColumnIndex.cgiIndex).substring(7).split("-")[0]);
					data.setLongitude(Double.parseDouble(reader.get(headerColumnIndex.longitudeIndex)));
					data.setLatitude(Double.parseDouble(reader.get(headerColumnIndex.latitudeIndex)));
					data.setCoveragetype(reader.get(headerColumnIndex.coveragetypeIndex));
					data.setAzimuth(Double.parseDouble(reader.get(headerColumnIndex.AzimuthIndex)));
					data.setAntennaheight(Double.parseDouble(reader.get(headerColumnIndex.antennaheightIndex)));
					data.setTotaldowntiltangle(Double.parseDouble(reader.get(headerColumnIndex.totaldowntiltangleIndex)));
					data.setCarrierffrequencynum(Integer.parseInt(reader.get(headerColumnIndex.carrierffrequencynumIndex)));
					data.setWorkfrequencyband(reader.get(headerColumnIndex.workfrequencybandIndex));
					data.setPci(reader.get(headerColumnIndex.pciIndex));
					data.setMaxtransmitpower(Double.parseDouble(reader.get(headerColumnIndex.MaxTransmitPowerIndex)));
					data.setCoverscene(getScenario(reader.get(headerColumnIndex.coveragetypeIndex), reader.get(headerColumnIndex.CoverSceneIndex)));
					data.setProvincename(reader.get(headerColumnIndex.ProvinceNameIndex));
					data.setCityname(reader.get(headerColumnIndex.CityNameIndex));
					data.setDistrictandcounty(reader.get(headerColumnIndex.DistrictandcountyIndex));
					data.setVendor(getVendor(reader.get(headerColumnIndex.VendorIndex)));
					data.setElectronictiltangle(Double.parseDouble(reader.get(headerColumnIndex.ElectronicTiltAngleIndex)));
					data.setMechanicaltiltangle(Double.parseDouble(reader.get(headerColumnIndex.MechanicalTiltAngleIndex)));
					data.setIscorearea("是".equals(reader.get(headerColumnIndex.IsCoreAreaIndex)) ? true : false);
					list.add(data);
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("province=" + province + ",date=" + tuple.getT() + ": 第" + allCount + "条记录数据异常");
					e.printStackTrace();
				}
			}
			int insert = bean.insert("com.cmdi.dao.EpDataDao.insertBatch", list);
			System.out.println("insert " + province + " gc rows " + insert);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			reader.close();
		}
		// 新增工参任务表数据
		GcTask task = new GcTask();
		task.setGcDate(DateUtil.getDate(tuple.getT(), MainT.DATEFORMAT));
		task.setProvince(province);
		client.disconnect();
		int insert2 = bean.insert("com.cmdi.dao.GcTaskDao.insertSelective", task);
		System.out.println("insert " + province + " gctask rows " + insert2);
		System.out.println("处理 " + province + " 工参数据完成");
		System.out.println("正在更新 " + province + " 基站、小区数目");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("date", tuple.getT());
		System.out.println(tuple.getT());
		map.put("province", province);
		int insert3 = bean.insert("com.cmdi.dao.EpDataDao.insertcellenbnumtablewithscenarioandvendor", map);
		int insert4 = bean.insert("com.cmdi.dao.EpDataDao.updatecellenbnumtablewithscenarioandvendor", map);
		System.out.println("insert " + province + " 基站、小区 rows " + (insert3 + insert4));
		System.out.println("更新 " + province + " 基站、小区完成");
	}
	

	private String getScenario(String covertype, String scenario) {
		if ("党政军机关".equals(scenario) || "党政军宿舍".equals(scenario) || "武警军区".equals(scenario) || "星级酒店".equals(scenario)
				|| "写字楼".equals(scenario) || "企事业单位".equals(scenario) || "休闲娱乐场所".equals(scenario)
				|| "医院".equals(scenario) || "低层居民区".equals(scenario) || "别墅群".equals(scenario)
				|| "工业园区".equals(scenario) || "集贸市场".equals(scenario) || "中小学".equals(scenario)
				|| "广场公园".equals(scenario) || "城区道路".equals(scenario) || "其他".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "一般场景";
		}
		if ("机场".equals(scenario) || "会展中心".equals(scenario) || "火车站".equals(scenario) || "长途汽车站".equals(scenario)
				|| "体育场馆".equals(scenario) || "码头".equals(scenario)) {
			return "大型场馆";
		}
		if ("商业中心".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "核心商业区";
		}
		if ("高校".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "高校园区";
		}
		if ("地铁".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "大型场馆" : "地铁";
		}
		if ("高层居民区".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "高层住宅小区群";
		}
		if ("城中村".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "城中村";
		}
		if ("高铁".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "高速铁路";
		}
		if ("高速公路".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "高速道路";
		}
		if ("普铁".equals(scenario) || "国道省道".equals(scenario) || "乡镇".equals(scenario) || "风景区".equals(scenario)
				|| "村庄".equals(scenario) || "郊区道路".equals(scenario) || "边境小区".equals(scenario)
				|| "山农牧林".equals(scenario) || "公墓".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "郊区农村";
		}
		if ("航道".equals(scenario) || "沙漠戈壁".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "海域沙漠区域(超远)";
		}
		if ("近水近海域".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "城区水域";
		}
		return "其他";
	}

	private String getVendor(String vendor) {
		if ("华为".equals(vendor) || "huawei".equals(vendor.toLowerCase())) {
			return "华为";
		} else if ("中兴".equals(vendor) || "zte".equals(vendor.toLowerCase())) {
			return "中兴";
		} else if ("诺基亚".equals(vendor) || "nokia".equals(vendor.toLowerCase()) || "卡特".equals(vendor)
				|| "阿尔卡特".equals(vendor) || "alcatel".equals(vendor.toLowerCase()) || "诺西".equals(vendor)) {
			return "诺基亚";
		} else if ("大唐".equals(vendor) || "datang".equals(vendor.toLowerCase())) {
			return "大唐";
		} else if ("爱立信".equals(vendor) || "ericsson".equals(vendor.toLowerCase())) {
			return "爱立信";
		} else {
			return "其他";
		}
	}
	
	private void print(Object[] o) {
		for (Object object : o) {
			System.out.print(object + " ");
		}
		System.out.println();
	}
}
