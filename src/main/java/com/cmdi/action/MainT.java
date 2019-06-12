package com.cmdi.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.net.ftp.FTPFile;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmdi.impl.FtpClient;
import com.cmdi.model.GcTask;
import com.cmdi.model.Tuple2;
import com.cmdi.util.DateUtil;

/**
 * 定时任务：每天晚上10点运行，当天有新的工参数据，那么更新数据库
 * 工参数据格式：province_date.csv
 * */
public class MainT {
	public static String DATEFORMAT = "yyyy-MM-dd";
	public static String hostIp;
	public static int port;
	public static String userName;
	public static String passWord;
	public static String controlEncoding;
	public static String fileEncoding;
	public static String ftpPath;
	public static void main(String[] args) throws IOException {
		hostIp = args[0];
		port = Integer.parseInt(args[1]);
		userName = args[2];
		passWord = args[3];
		controlEncoding = args[4];
		fileEncoding = args[5];
		ftpPath = args[6];
		
		System.out.println("当前时间  " + DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
		FtpClient client = new FtpClient(hostIp, port, userName, passWord, controlEncoding);
		String regex = "^.*[0-9]{4}\\-[0-2]{2}\\-[0-9]{2}\\.csv$";
		FTPFile[] listAllFile = client.listAllFile(ftpPath,regex);
		if(listAllFile.length <= 0) {
			System.err.println("不存在合理的工参数据");
			return;
		}
		//FTP下各省工参最新时间及其文件
		HashMap<String, Tuple2<String, FTPFile>> provinceDateFile = new HashMap<String, Tuple2<String, FTPFile>>();
		for (FTPFile ftpFile : listAllFile) {
			String[] provincedate = ftpFile.getName().split("_");
			if(provincedate.length != 2)
				continue;
			String[] datetype = provincedate[1].split("\\.");
//			System.out.println(datetype);
			if(datetype.length != 2)
				continue;
			if(provinceDateFile.containsKey(provincedate[0])) {
				if(DateUtil.getNewerDate(provinceDateFile.get(provincedate[0]).getT(), datetype[0], DATEFORMAT) == -1) {
					provinceDateFile.get(provincedate[0]).setT(datetype[0]);
					provinceDateFile.get(provincedate[0]).setS(ftpFile);
				}
			} else {
				provinceDateFile.put(provincedate[0], new Tuple2<String, FTPFile>(datetype[0], ftpFile));
			}
		}
		//test
		for (Entry<String, Tuple2<String, FTPFile>> entry : provinceDateFile.entrySet()) {
			System.out.println(entry.getKey() + "->" + entry.getValue());
		}

		//读取数据库，得到每个省工参最新时间
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
		SqlSessionTemplate bean = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		List<GcTask> selectList = bean.selectList("com.cmdi.dao.GcTaskDao.getMaxDateOfProvinces");
		//遍历得到哪个省需要更新工参数据
		for (GcTask gcTask : selectList) {
			System.out.println(gcTask);
			String province = gcTask.getProvince();
			String currentDate = DateUtil.getDate(gcTask.getGcDate(), DATEFORMAT);
			try {
				if(DateUtil.getNewerDate(provinceDateFile.get(province).getT(), currentDate, DATEFORMAT) == -1) {
					provinceDateFile.remove(province);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		int count = 1;
		ExecutorService pool = Executors.newFixedThreadPool(4);
		//得到最新时间
		String newDate = "0000-00-00";
		for (Entry<String, Tuple2<String, FTPFile>> entry : provinceDateFile.entrySet()) {
			System.out.println(count++ + " --> " + entry.getKey() + "->" + entry.getValue());
			pool.execute(new HandleGcThread(entry.getKey(), entry.getValue(), bean, fileEncoding));
			if(DateUtil.getNewerDate(newDate, entry.getValue().getT(), DATEFORMAT) == -1)
				newDate = entry.getValue().getT();
		}
		
		pool.shutdown();
		while(!pool.isTerminated()){}
		System.out.println("正在更新所有省的基站、小区数量");
		if(!"0000-00-00".equals(newDate)) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("date", newDate);
			int insert = bean.insert("com.cmdi.dao.EpDataDao.insertglobalcellenbnumtable", map);
			System.out.println("insert 全国 基站、小区 rows " + insert);
		}
		System.out.println("工参数据更新完毕");
		
		client.disconnect();
	}
}
