package com.cmdi.thread;

import com.cmdi.model.mro.ScMeasureMasterCell;
import com.cmdi.thread.XmlParse;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author: 高宗宝
 * @date: 2019/5/16
 * @version: 1.0
 * @description: someword
 */
public class XmlHandleThread implements Runnable {
    private static final Logger logger = Logger.getLogger(XmlHandleThread.class);
    private File file;
    private ClassPathXmlApplicationContext context;
    private int currentnum;
 
    public XmlHandleThread(File file, ClassPathXmlApplicationContext context, int currentnum) {
		super();
		this.file = file;
		this.context = context;
		this.currentnum = currentnum;
	}

	@Override
    public void run() {
		if(file.getName().endsWith(".gz")) {
			logger.info("warking on filenum=" + currentnum);
			GZIPInputStream gzipInputStream = null;
			try {
				String ts = file.getName().split("_", -1)[5];
				gzipInputStream = new GZIPInputStream(new FileInputStream(file));
				ArrayList<ScMeasureMasterCell> arrayList = XmlParse.parseByDom4j(gzipInputStream, "UTF-8");
				if(arrayList == null || arrayList.isEmpty()) {
					logger.warn("no right data");
					return;
				}
				
				HashMap<String, Object> input = new HashMap<String, Object>();
				input.put("date", "20190610");
				input.put("ts", ts);
				//input.put("mrolist", arrayList);
				
//				logger.info("mro count=" + arrayList.size());
//				SqlSessionTemplate bean = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
//				int insert = bean.insert("com.cmdi.dao.MroDataDao.insertmrocelldata", input);
//				logger.info("insert old mro data count=" + insert);
				
				long doBatch = doBatch(10000, arrayList, input);
				logger.info("insert mro data count=" + doBatch);
				
				gzipInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(gzipInputStream != null) {
					try {
						gzipInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
		}
    }
	
	public long doBatch(int batchSize, List<ScMeasureMasterCell> list, HashMap<String, Object> input) {
		int page = list.size() / batchSize;
		long count = 0L;
		for(int i = 0; i < page; i++) {
			List<ScMeasureMasterCell> subList = list.subList(0, batchSize);
			input.put("mrolist", subList);
			SqlSessionTemplate bean = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
			int insert = bean.insert("com.cmdi.dao.MroDataDao.insertmrocelldata", input);
			count = count + insert;
			subList.clear();
		}
		if(!list.isEmpty()) {
			SqlSessionTemplate bean = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
			input.put("mrolist", list);
			int insert = bean.insert("com.cmdi.dao.MroDataDao.insertmrocelldata", input);
			count = count + insert;
			list.clear();
		}
		return count;
	}
}