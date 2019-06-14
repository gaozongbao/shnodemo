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
			logger.info("handle num=" + currentnum);
			GZIPInputStream gzipInputStream = null;
			try {
				String ts = file.getName().split("_", -1)[5];
				gzipInputStream = new GZIPInputStream(new FileInputStream(file));
				ArrayList<ScMeasureMasterCell> arrayList = XmlParse.parseByDom4j(gzipInputStream, "UTF-8");
				if(arrayList == null || arrayList.isEmpty()) {
					logger.warn("no right data");
					return;
				}
				SqlSessionTemplate bean = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
				HashMap<String, Object> input = new HashMap<String, Object>();
				
				input.put("date", "20190610");
				input.put("ts", ts);
				input.put("mrolist", arrayList);
				logger.info("mro count=" + arrayList.size());
				int insert = bean.insert("com.cmdi.dao.MroDataDao.insertmrocelldata", input);
				logger.info("insert old mro data count=" + insert);
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
}
