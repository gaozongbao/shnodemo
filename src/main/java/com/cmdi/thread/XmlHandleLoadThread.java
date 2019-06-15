package com.cmdi.thread;

import com.cmdi.dao.DBHelper;
import com.cmdi.model.GlobalConfig;
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
 * @description: 通过mysql load的方式导入数据，未验证
 */
public class XmlHandleLoadThread implements Runnable {
    private static final Logger logger = Logger.getLogger(XmlHandleLoadThread.class);
    private File file;
    private ClassPathXmlApplicationContext context;
    private int currentnum;
 
    public XmlHandleLoadThread(File file, ClassPathXmlApplicationContext context, int currentnum) {
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
				
				DBHelper helper = context.getBean(DBHelper.class);
				helper.initDB();
				int load2 = helper.load2(arrayList, "fourg_mro_cell", "utf8", "|", ts);
				helper.closeConn();
				logger.info("insert mro data count=" + load2);
				
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
}