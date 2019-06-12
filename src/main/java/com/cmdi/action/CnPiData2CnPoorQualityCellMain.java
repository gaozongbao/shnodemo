/**   
 * @ClassName:  CnPiData2CnPoorQualityCell   
 * @Description:程序入口类，用来分析核心网小区详细数据，得到核心网质差小区数据
 * @author: 高宗宝 
*/
package com.cmdi.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.cmdi.model.ActiveMQConfig;
import com.cmdi.model.CnTaskDoneMessage;
import com.cmdi.util.ActiveMqClient;
import com.cmdi.util.DateUtil;

public class CnPiData2CnPoorQualityCellMain {
	public static Logger logger = Logger.getLogger(CnPiData2CnPoorQualityCellMain.class);
	public static ActiveMQConfig mqConfig;
	public static void main(String[] args) throws Exception{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
		mqConfig = context.getBean("mqconfig", ActiveMQConfig.class);
		logger.info(mqConfig);
		ArrayList<CnTaskDoneMessage> taskList = new ArrayList<CnTaskDoneMessage>();
		ActiveMqClient mqclient = new ActiveMqClient();
		mqclient.init(mqConfig.getUrl());
		for(int i = 0; i < Integer.parseInt( mqConfig.getTopic0client1maxmessagecount()); i++) {
			Message message = mqclient.getmessageFromTopictb(mqConfig.getTopic0Name(), mqConfig.getTopic0client1(), mqConfig.getTopic0client1durablename1(), Integer.parseInt(mqConfig.getTopic0client1waittime()));
			CnTaskDoneMessage taskDoneMessage = null;
			if(message != null) {
				try {
					taskDoneMessage = JSON.parseObject(((TextMessage) message).getText(), CnTaskDoneMessage.class);
					taskList.add(taskDoneMessage);
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(e);
				}
			}
		}
		logger.info("task num=" + taskList.size() + " to handle");
//		System.out.println(taskList);
		ExecutorService pool = Executors.newFixedThreadPool(4);
//		Date date = DateUtil.getDate(args[0], "yyyy-MM-dd");
//		System.out.println("处理日期是：" + args[0]);
//		String prov = args[1].trim();
//		if ("all".equals(prov)) {
//			prov = "安徽,北京,福建,甘肃,广东,广西,贵州,海南,河北,河南,黑龙江,湖北,湖南,吉林,江苏,江西,辽宁,内蒙古,宁夏,青海,山东,山西,陕西,上海,四川,天津,云南,浙江,重庆";
//		}
//		String[] provicnes = prov.split(",");
//		for (String p : provicnes) {
//			pool.execute(new HandleCnPiPoorQualityCellThread(context, p, date));
//		}
		for (CnTaskDoneMessage cnTaskDoneMessage : taskList) {
			if("success".equals(cnTaskDoneMessage.getStatus().toLowerCase()))
				pool.execute(new HandleCnPiPoorQualityCellThread(context,"贵州",DateUtil.getDate(cnTaskDoneMessage.getDate(), "yyyy-MM-dd"), mqclient));
		}
		pool.shutdown();
		while(!pool.isTerminated()){}
		System.out.println("cnpi to cnpoorqualitycell task done");
	}
}
