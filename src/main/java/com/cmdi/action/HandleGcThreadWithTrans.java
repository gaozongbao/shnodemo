package com.cmdi.action;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmdi.impl.FtpClient;
import com.cmdi.impl.UpDateServiceImpl;
import com.cmdi.model.EpData;
import com.cmdi.model.GcTask;
import com.cmdi.model.HeaderColumnIndex;
import com.cmdi.model.Tuple2;
import com.cmdi.util.DateUtil;
import com.cmdi.util.XLSX2CSV;
import com.csvreader.CsvReader;
import com.mysql.jdbc.Buffer;

import net.sf.json.util.NewBeanInstanceStrategy;

public class HandleGcThreadWithTrans implements Runnable {
	private String province;
	private Tuple2<String, FTPFile> tuple;
	private ClassPathXmlApplicationContext context;
	private String fileEncoding;
	private FtpClient client;

	public HandleGcThreadWithTrans(String province, Tuple2<String, FTPFile> tuple, ClassPathXmlApplicationContext context,
			String fileEncoding) {
		super();
		this.province = province;
		this.tuple = tuple;
		this.context = context;
		this.fileEncoding = fileEncoding;
		client = new FtpClient(MainWithTrans.hostIp, MainWithTrans.port, MainWithTrans.userName, MainWithTrans.passWord, MainWithTrans.controlEncoding);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		start3();
	}
	
	
	/**
	 * xlsx文件处理，根据表头自动确定列，加入事务支持
	 * */
	private void start3() {
		String tmpcsvFilePath = MainWithTrans.localPath + "/" + tuple.getS().getName().substring(0, tuple.getS().getName().indexOf(".")) + ".csv";
		UpDateServiceImpl upDateServiceImpl = context.getBean(com.cmdi.impl.UpDateServiceImpl.class);
		//工参数据列表
		ArrayList<EpData> list = new ArrayList<EpData>();
		CsvReader reader = null;
		try {
			if(tuple.getS().getName().endsWith(".csv")) {
				reader = client.getFtpFile(MainWithTrans.ftpPath, tuple.getS(), fileEncoding);
			} else if(tuple.getS().getName().endsWith(".xlsx")) {
				
				XLSX2CSV xlsx2csv = new XLSX2CSV(client.getFtpFileIuputStream(MainWithTrans.ftpPath, tuple.getS()),tmpcsvFilePath);
				xlsx2csv.process();
				reader = new CsvReader(tmpcsvFilePath, ',', Charset.forName("GBK"));
			}
			if(reader == null)
				return;
			reader.readHeaders();
			String[] headers = reader.getHeaders();
			HeaderColumnIndex headerColumnIndex = new HeaderColumnIndex();
			headerColumnIndex.getIndexFromHeader(headers);
			System.out.println(headerColumnIndex);
			int allCount = 0;
			//剔除重复工参数据
			HashSet<String> cgiSets = new HashSet<String>();
			while (reader.readRecord()) {
				allCount++;
				try {
					EpData data = new EpData();
					String cgi = reader.get(headerColumnIndex.cgiIndex);
					if(cgiSets.contains(cgi)) {
						continue;
					} else {
						cgiSets.add(cgi);
					}
					if(cgi == null || "".equals(cgi))
						continue;
					if(cgi.startsWith("460-00")) {
						cgi = cgi.substring(7);
					}
					data.setCgi(cgi);
					data.setEci(cgi2eci(cgi));
					data.setEnodebname(reader.get(headerColumnIndex.enodebnameIndex));
					data.setCellname(reader.get(headerColumnIndex.cellnameIndex));
					data.setTac(reader.get(headerColumnIndex.tacIndex));
					data.setLocalcellid(reader.get(headerColumnIndex.localcellidIndex));
					String[] splits = cgi.split("-",-1);
					if(splits.length != 2)
						continue;
					data.setEnbid(cgi == null || "".equals(cgi) ? null : splits[0]);
					String longtitude = reader.get(headerColumnIndex.longitudeIndex);
					data.setLongitude(longtitude == null || "".equals(longtitude) ? null : Double.parseDouble(longtitude));
					String lat = reader.get(headerColumnIndex.latitudeIndex);
					data.setLatitude(lat == null || "".equals(lat) ? null : Double.parseDouble(lat));
					String covertype = getCovertype(reader.get(headerColumnIndex.coveragetypeIndex));
					if(covertype == null)
						continue;
					if("四期".equals(covertype)) {
						data.setCoveragetype("室外");
					} else {
						data.setCoveragetype(covertype);
					}
					String azi = reader.get(headerColumnIndex.AzimuthIndex);
					data.setAzimuth(azi == null || "".equals(azi) ? null : Double.parseDouble(azi));
					String anten = reader.get(headerColumnIndex.antennaheightIndex);
					data.setAntennaheight(anten == null || "".equals(anten) ? null : Double.parseDouble(anten));
					String td = reader.get(headerColumnIndex.totaldowntiltangleIndex);
					data.setTotaldowntiltangle(td == null || "".equals(td) ? null : Double.parseDouble(td));
					String carr = reader.get(headerColumnIndex.carrierffrequencynumIndex);
					data.setCarrierffrequencynum(carr == null || "".equals(carr) ? null : Integer.parseInt(carr));
					data.setWorkfrequencyband(reader.get(headerColumnIndex.workfrequencybandIndex));
					data.setPci(reader.get(headerColumnIndex.pciIndex));
					String maxp = reader.get(headerColumnIndex.MaxTransmitPowerIndex);
					data.setMaxtransmitpower(maxp == null || "".equals(maxp) || "#N/A".equals(maxp) || "null".equals(maxp.toLowerCase()) ? null : Double.parseDouble(maxp));
					data.setCoverscene(getScenarioNew(covertype, reader.get(headerColumnIndex.CoverSceneIndex)));
					String pr = reader.get(headerColumnIndex.ProvinceNameIndex);
					if(pr.endsWith("省")) {
						pr = pr.substring(0, pr.lastIndexOf("省"));
					}
					data.setProvincename(pr);
					String ci = reader.get(headerColumnIndex.CityNameIndex);
					if(ci.endsWith("市")) {
						ci = ci.substring(0, ci.lastIndexOf("市"));
					}
					if("贵安新区".equals(ci)) {
						ci = "贵安";
					}
					data.setCityname(ci);
					data.setDistrictandcounty(reader.get(headerColumnIndex.DistrictandcountyIndex));
					data.setVendor(getVendor(reader.get(headerColumnIndex.VendorIndex)));
					String elec = reader.get(headerColumnIndex.ElectronicTiltAngleIndex);
					data.setElectronictiltangle(elec == null || "".equals(elec) ? null : Double.parseDouble(elec));
					String mech = reader.get(headerColumnIndex.MechanicalTiltAngleIndex);
					data.setMechanicaltiltangle(mech == null || "".equals(mech) ? null : Double.parseDouble(mech));
					data.setIscorearea("是".equals(reader.get(headerColumnIndex.IsCoreAreaIndex)) ? true : false);
					data.setStatus(reader.get(headerColumnIndex.statusIndex));
					list.add(data);
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("province=" + province + ",date=" + tuple.getT() + ": 第" + allCount + "条记录数据异常");
					e.printStackTrace();
//					System.exit(0);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(reader != null)
					reader.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		client.disconnect();
		// 新增工参任务表数据
		GcTask task = new GcTask();
		task.setGcDate(DateUtil.getDate(tuple.getT(), MainT.DATEFORMAT));
		task.setProvince(province);
		// 基站、小区数目
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("date", tuple.getT());
		System.out.println(tuple.getT());
		map.put("province", province);
		if(!list.isEmpty())
			upDateServiceImpl.update(province, list, task, map);
		System.out.println("处理 " + province + " 数据完成");
		upDateServiceImpl.updateSimpleGc(province);
		new File(tmpcsvFilePath).delete();
	}
	
	private static String cgi2eci(String cgi) {
		if(cgi == null)
			return null;
		String[] split = cgi.split("-");
		if(split == null || split.length != 2)
			return null;
		try {
			Integer i = Integer.parseInt(split[0]) * 256 + Integer.parseInt(split[1]);
			int diff = 9 - String.valueOf(i).length();
			StringBuilder builder = null;
			if(diff >= 0) {
				builder = new StringBuilder("46000");
				for (int j = 0; j < diff; j++) {
					builder.append("0");
				}
				builder.append(String.valueOf(i));
			}
			return builder.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(cgi2eci("556493-1"));
	}
	
	private String getCovertype(String covertype) {
		if("小站".equals(covertype)) {
			return null;
		}
		if("四期".equals(covertype)) {
			return "四期";
		}
		if(covertype == null || "".equals(covertype) || "室外".equals(covertype) || "宏站".equals(covertype)) {
			return "室外";
		} else {
			return "室分";
		}
	}

	@Deprecated
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
	
	private String getScenarioNew(String covertype, String scenario) {
		if("四期".equals(covertype)) {
			return "其他";
		}
		if ("别墅群".equals(scenario) || "城区道路".equals(scenario) || "党政军机关".equals(scenario) || "低层居民区".equals(scenario) 
				|| "工业园区".equals(scenario) || "广场公园".equals(scenario) || "集贸市场".equals(scenario) || "其他".equals(scenario) 
				|| "企事业单位".equals(scenario) || "写字楼".equals(scenario) || "星级酒店".equals(scenario) || "休闲娱乐场所".equals(scenario)
				|| "医院".equals(scenario) || "中小学".equals(scenario) || "其它".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "一般场景";
		}
		if (scenario.contains("星级酒店")) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "一般场景";
		}
		if ("城中村".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "城中村";
		}
		if("村庄".equals(scenario) || "风景区".equals(scenario) || "公墓".equals(scenario) || "国道省道".equals(scenario) 
				|| "乡镇".equals(scenario) || "农村住宅区".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "郊区农村";
		}
		if ("地铁".equals(scenario)) {
			return "宏站".equals(covertype) || "室外".equals(covertype) ? "地铁" : "其他";
		}
		if ("高层居民区".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "高层住宅小区群";
		}
		if ("高速公路".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "高速道路";
		}
		if ("高铁".equals(scenario)) {
			return "宏站".equals(covertype) || "室外".equals(covertype) ? "高铁" : "其他";
		}
		if ("高校".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "高校园区";
		}
		if("会展中心".equals(scenario) || "火车站".equals(scenario) || "机场".equals(scenario) || "体育场馆".equals(scenario) 
				|| "长途汽车站".equals(scenario)) {
			return "大型场馆";
		}
		if ("郊区道路".equals(scenario)) {
			return "宏站".equals(covertype) || "室外".equals(covertype) ? "郊区农村" : "其他";
		}
		if("码头".equals(scenario)) {
			return "宏站".equals(covertype) || "室外".equals(covertype) ? "大型场馆" : "其他";
		}
		if("普铁".equals(scenario)) {
			return "宏站".equals(covertype) || "室外".equals(covertype) ? "郊区农村" : "其他";
		}
		if ("商业中心".equals(scenario)) {
			return "室分".equals(covertype) || "室内".equals(covertype) ? "室分" : "核心商业区";
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
