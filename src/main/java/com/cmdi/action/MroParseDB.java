package com.cmdi.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Set;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmdi.model.CellAddr;
import com.cmdi.model.CellIdAddr;
import com.cmdi.model.SaoPinData;
import com.cmdi.thread.XmlHandleThread;
import com.cmdi.util.MapDistance;

/** 
 * @ClassName: MroParseDB 
 * @Description: 给出mro压缩输入目录的进行处理
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
public class MroParseDB {
	private static Logger logger = Logger.getLogger(MroParseDB.class);
	public static void main(String[] args) {
		if(args.length != 2) {
			logger.error("error, call java -cp xxx.jar com.cmdi.action.MroParseDB inputpath outputpath");
			System.exit(0);
		}
		File inPut = new File(args[0]);
		File outPut = new File(args[1]);
		if(!inPut.exists() || !inPut.isDirectory() || !outPut.exists() || !outPut.isDirectory()) {
			logger.error("check directory inputpath outputpath");
			System.exit(0);
		}
		
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 2);
		
		File[] listFiles = inPut.listFiles();
		logger.info(inPut + " include " + listFiles.length + " files");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
		SqlSessionTemplate bean = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		HashMap<String, Object> input = new HashMap<String, Object>();
		input.put("date", "20190610");
		int delete = bean.delete("com.cmdi.dao.MroDataDao.deletemrocelldata", input);
		logger.info("delete old mro data count=" + delete);
		int i = 0;
		for (File gzfile : listFiles) {
			i++;
			logger.info("fileNum=" + i);
			pool.execute(new XmlHandleThread(gzfile, context, i));
		}
		
		pool.shutdown();
		while(!pool.isTerminated()){
			
		}
		logger.info("task done");
	}
}
