package com.cmdi.action;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmdi.model.CellAddr;
import com.cmdi.model.CellIdAddr;
import com.cmdi.model.SaoPinData;
import com.cmdi.util.MapDistance;
import com.sun.org.apache.xml.internal.security.Init;

/**
 * @ClassName: SaoPinHandle
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0
 */
public class SaoPinHandle {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
		SqlSessionTemplate bean = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		HashMap<String, Object> input = new HashMap<String, Object>();
		String type = "4g";
		int earfcn = 37900;
		if (StringUtils.equals(type, "4g")) {

		}
		input.put("earfcn", earfcn);
		ArrayList<Integer> earfcnsArrayList = new ArrayList<Integer>();
		earfcnsArrayList.add(earfcn);
		input.put("list", earfcnsArrayList);

		// 公参数据
		List<CellAddr> selectList = bean.selectList("com.cmdi.dao.GongcanDao.selectearfcnpcicelllist", input);
		System.out.println("公参数据分组后行数:" + selectList.size());
		HashMap<String, ArrayList<CellIdAddr>> map = new HashMap<String, ArrayList<CellIdAddr>>();
		for (CellAddr cellAddr : selectList) {
//			System.out.println(cellAddr);
			String earfcnpci = cellAddr.getEarfcn() + ":" + cellAddr.getPci();
			String[] splits = cellAddr.getCellidaddr().split(",", -1);
			ArrayList<CellIdAddr> list = new ArrayList<CellIdAddr>();
			for (String ss : splits) {
				CellIdAddr addr = string2cellIdAddr(ss);
				if (addr != null) {
					list.add(addr);
				}
			}
			map.put(earfcnpci, list);
		}
		System.out.println(map.get("37900:356").size());
		// 扫频数据

		List<SaoPinData> selectList2 = bean.selectList("com.cmdi.dao.SaopinDao.selectsaopindata", input);
		System.out.println("扫频数据行数" + selectList2.size());
		HashMap<Integer, ArrayList<Double>> masterrsrp = new HashMap<Integer, ArrayList<Double>>();
		for (SaoPinData saoPinData : selectList2) {
			// System.out.println(saoPinData);
			String earfcnpci = saoPinData.getEarfcn() + ":" + saoPinData.getPci();
			ArrayList<CellIdAddr> list = map.getOrDefault(earfcnpci, null);
			if(list == null || list.isEmpty())
				continue;
			CellIdAddr masterCellIdAddr = getMasterCellIdByMinDistance(saoPinData.getLongitude(), saoPinData.getLatitude(), list);
			saoPinData.setMastercellId(masterCellIdAddr.getCellid());
			System.out.println(saoPinData);
			if(masterrsrp.containsKey(masterCellIdAddr.getCellid())) {
				masterrsrp.get(masterCellIdAddr.getCellid()).add(saoPinData.getRs());
			} else {
				ArrayList<Double> list2 = new ArrayList<Double>();
				list2.add(saoPinData.getRs());
				masterrsrp.put(masterCellIdAddr.getCellid(), list2);
			}
		}
		
		HashMap<Integer, Integer[][]> mastercover = new HashMap<Integer, Integer[][]>();
		
		ArrayList<Integer> listthreshold = new ArrayList<Integer>();
		listthreshold.add(-70);
		listthreshold.add(-90);
		listthreshold.add(-110);
		
		Set<Entry<Integer,ArrayList<Double>>> entrySet = masterrsrp.entrySet();
		for (Entry<Integer, ArrayList<Double>> entry : entrySet) {
			Integer master = entry.getKey();
			Integer[][] integers = init(listthreshold);
			for(Double d: entry.getValue()) {
				for(int i = 0; i < listthreshold.size(); i++) {
					if(d >= listthreshold.get(i)) {
						integers[1][i]++;
					} else {
						integers[2][i]++;
					}
				}
			}
			mastercover.put(master, integers);
		}
		
		Set<Entry<Integer,Integer[][]>> entrySet2 = mastercover.entrySet();
		for (Entry<Integer, Integer[][]> entry : entrySet2) {
			System.out.println(entry.getKey());
			print(entry.getValue());
		}
		context.close();
	}

	public static CellIdAddr string2cellIdAddr(String s) {
		String[] split = s.split(":", -1);
		if (split.length != 3)
			return null;
		return new CellIdAddr(Integer.parseInt(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
	}
	
	public static CellIdAddr getMasterCellIdByMinDistance(double lon, double lat, ArrayList<CellIdAddr> list) {
		CellIdAddr addr = new CellIdAddr(-1,0D,0D);
		double minD = Integer.MAX_VALUE;
		for (CellIdAddr cellIdAddr : list) {
			double d = MapDistance.GetDistance(lat, lon, cellIdAddr.getLatitude(), cellIdAddr.getLongitude());
			if(d <= minD) {
				minD = d;
				addr.setCellid(cellIdAddr.getCellid());
				addr.setLongitude(cellIdAddr.getLongitude());
				addr.setLatitude(cellIdAddr.getLatitude());
			}
		}
		return addr;
	}
	
	public static  void print(Object[][] os) {
		for(Object[] objects : os) {
			for (Object object : objects) {
				System.out.print(object + " ");
			}
			System.out.println();
		}
	}
	
	public static Integer[][] init(ArrayList<Integer> listthreshold) {
		Integer[][] coverres = new Integer[3][listthreshold.size()];
		for(int i = 0; i < listthreshold.size(); i++) {
			coverres[0][i] = listthreshold.get(i);
			coverres[1][i] = 0;
			coverres[2][i] = 0;
		}
		return coverres;
	}

}
