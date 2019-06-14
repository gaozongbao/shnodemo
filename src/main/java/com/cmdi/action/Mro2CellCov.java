package com.cmdi.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.Set;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmdi.model.CellAddr;
import com.cmdi.model.CellIdAddr;
import com.cmdi.model.GlobalConfig;
import com.cmdi.model.SaoPinData;
import com.cmdi.thread.XmlHandleThread;
import com.cmdi.util.LogUtil;
import com.cmdi.util.MapDistance;

/** 
 * @ClassName: Mro2CellCov 
 * @Description: 统计mysql中fourg_mro_cell表，得到分小区分扇区号的覆盖率
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0 
 */
public class Mro2CellCov {
	private static Logger logger = Logger.getLogger(Mro2CellCov.class);
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
		SqlSessionTemplate bean = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		
		HashMap<String, Object> input = new HashMap<String, Object>();
		int insert = bean.insert("com.cmdi.dao.MroDataDao.getmrocellcover", input);
		logger.info("insert count=" + insert);
		
		context.close();
		
		logger.info("task done");
	}
	
}
