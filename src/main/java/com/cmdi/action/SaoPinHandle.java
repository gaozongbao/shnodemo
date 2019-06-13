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
/** 
 * @ClassName: SaoPinHandle 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
/** 
 * @ClassName: SaoPinHandle 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
/** 
 * @ClassName: SaoPinHandle 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
/** 
 * @ClassName: SaoPinHandle 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
/** 
 * @ClassName: SaoPinHandle 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
/** 
 * @ClassName: SaoPinHandle 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
/** 
 * @ClassName: SaoPinHandle 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
/** 
 * @ClassName: SaoPinHandle 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
/** 
 * @ClassName: SaoPinHandle 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
/** 
 * @ClassName: SaoPinHandle 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
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
		ArrayList<Integer> earfcnsArrayList = new ArrayList<Integer>();
		earfcnsArrayList.add(37900);
//		earfcnsArrayList.add(38400);
		input.put("earfcnlist", earfcnsArrayList);


		// 公参数据
		List<CellAddr> selectList = bean.selectList("com.cmdi.dao.GongcanDao.selectearfcnpcicelllist", input);
		System.out.println("公参数据对earfcn=" + earfcnsArrayList + ",pci分组后行数:" + selectList.size());

		//得到相同earfcn、pci的小区列表（id、经纬度）,key=earfcn:pci,value=list<cell>
		//为扫频点求最短距离的主服务小区提供数据
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

		// 扫频数据
		List<SaoPinData> allSaopinData = bean.selectList("com.cmdi.dao.SaopinDao.selectsaopindata", input);

		System.out.println("earfcn="+ earfcnsArrayList +"的扫频数据行数" + allSaopinData.size());

		//存放每个主服务小区的所有rsrp值，key=cellid:earfcn，value=list<rsrp>
		HashMap<String, ArrayList<Double>> masterrsrp = new HashMap<String, ArrayList<Double>>();
		
		//得到主服务小区
		for (SaoPinData saoPinData : allSaopinData) {
			String earfcnpci = saoPinData.getEarfcn() + ":" + saoPinData.getPci();
			ArrayList<CellIdAddr> list = map.getOrDefault(earfcnpci, null);
			//如果当前扫频数据根据earfcn、pci无法得到有效的小区集合，那么该条扫频数据丢弃
			if(list == null || list.isEmpty())
				continue;
			//根据经纬度得到距离最近的小区id作为主服务小区
			CellIdAddr masterCellIdAddr = getMasterCellIdByMinDistance(saoPinData.getLongitude(), saoPinData.getLatitude(), list);
			saoPinData.setMastercellId(masterCellIdAddr.getCellid());
//			System.out.println(saoPinData);
			//获取每个小区的所有频点的rsrp值
			String idearfcn=masterCellIdAddr.getCellid() + ":" + saoPinData.getEarfcn();
			if(masterrsrp.containsKey(idearfcn)) {
				masterrsrp.get(idearfcn).add(saoPinData.getRs());
			} else {
				ArrayList<Double> list2 = new ArrayList<Double>();
				list2.add(saoPinData.getRs());
				masterrsrp.put(idearfcn, list2);
			}
		}
		
		//所有小区所有频点的覆盖率,key=cellid:earfcn，value=int[3][earfncs]，第一行表示earfcn的值，第二行表示大于等于earfcn的个数，第三行表示小于earfcn的个数
		HashMap<String, Integer[][]> mastercover = new HashMap<String, Integer[][]>();
		
		ArrayList<Integer> listthreshold = new ArrayList<Integer>();
		listthreshold.add(-70);
		listthreshold.add(-90);
		listthreshold.add(-110);
		
		Set<Entry<String,ArrayList<Double>>> entrySet = masterrsrp.entrySet();
		for (Entry<String, ArrayList<Double>> entry : entrySet) {
			String masteridearfcn = entry.getKey();
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
			mastercover.put(masteridearfcn, integers);
		}
		
		Set<Entry<String, Integer[][]>> entrySet2 = mastercover.entrySet();
		for (Entry<String, Integer[][]> entry : entrySet2) {
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
	
	
	
	/** 
	* @Title: getMasterCellIdByMinDistance 
	* @Description: 从所有小区中得到距离给定经纬度最近的小区 
	* @param lon
	* @param lat
	* @param list
	* @return CellIdAddr
	* @author 高宗宝
	* @date 2019年6月13日下午4:57:02
	*/ 
	public static CellIdAddr getMasterCellIdByMinDistance(double lon, double lat, ArrayList<CellIdAddr> list) { CellIdAddr addr = new CellIdAddr(-1,0D,0D);
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
