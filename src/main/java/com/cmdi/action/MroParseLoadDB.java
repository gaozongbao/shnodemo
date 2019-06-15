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

import com.cmdi.dao.DBHelper;
import com.cmdi.model.CellAddr;
import com.cmdi.model.CellIdAddr;
import com.cmdi.model.GlobalConfig;
import com.cmdi.model.SaoPinData;
import com.cmdi.thread.XmlHandleLoadThread;
import com.cmdi.thread.XmlHandleThread;
import com.cmdi.util.LogUtil;
import com.cmdi.util.MapDistance;

/**
 * @ClassName: MroParseDB
 * @Description: 给出mro压缩输入目录的进行处理，通过sql-load方式
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0
 */
public class MroParseLoadDB {
	private static Logger logger = Logger.getLogger(MroParseLoadDB.class);

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println(
					"error, call java -cp xxx.jar com.cmdi.action.MroParseDB inputpath outputpath threadcount");
			System.exit(0);
		}
		LogUtil.initLog4j(args[1], "sh");
		File inPut = new File(args[0]);
		File outPut = new File(args[1]);
		if (!inPut.exists() || !inPut.isDirectory() || !outPut.exists() || !outPut.isDirectory()) {
			logger.error("check directory inputpath outputpath");
			System.exit(0);
		}

		File[] listFiles = inPut.listFiles();

		int threadNum = Integer.parseInt(args[2]);
		// ExecutorService pool =
		// Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()
		// - 2);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(threadNum, threadNum, 5, TimeUnit.MINUTES,
				new ArrayBlockingQueue<Runnable>(listFiles.length));

		logger.info(inPut + " include " + listFiles.length + " files");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
		
		SqlSessionTemplate bean = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		HashMap<String, Object> input = new HashMap<String, Object>();
		input.put("date", "20190610");
		int delete = bean.delete("com.cmdi.dao.MroDataDao.deletemrocelldata", input);
		logger.info("delete old mro data count=" + delete);

		int count = 0;
		for (File gzFile : listFiles) {
			count++;
			long completedTaskCount = pool.getCompletedTaskCount();
			long taskCount = pool.getTaskCount();
			long poolSize = pool.getPoolSize();
			logger.info("complete=" + completedTaskCount + ",taskcount=" + taskCount + ",poolsize=" + poolSize);
			while (taskCount - completedTaskCount > poolSize) {
				try {
					Thread.sleep(2 * 1000);
					logger.info("waiting for resource");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				completedTaskCount = pool.getCompletedTaskCount();
				taskCount = pool.getTaskCount();
				poolSize = pool.getPoolSize();
				logger.info("complete=" + completedTaskCount + ",taskcount=" + taskCount + ",poolsize=" + poolSize);
				if (taskCount - completedTaskCount < poolSize) {
					break;
				}
			}
			pool.execute(new XmlHandleLoadThread(gzFile, context, count));
		}
		pool.shutdown();
		while (!pool.isTerminated()) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//主服务小区覆盖率统计
		int insertcovercount = bean.insert("com.cmdi.dao.MroDataDao.getmrocellcover", input);
		logger.info("insert mrocellcover count=" + insertcovercount);
		logger.info("task done");
	}
}
