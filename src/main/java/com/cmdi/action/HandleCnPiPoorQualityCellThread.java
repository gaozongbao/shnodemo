/**   
 * @ClassName:  HabdleCnPiPoorQualityCellThread   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 高宗宝 
*/
package com.cmdi.action;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.cmdi.impl.CnPi2CnPoorQualityCellServiceImpl;
import com.cmdi.model.CnPoorCellTaskDoneMessage;
import com.cmdi.util.ActiveMqClient;

/**     
 *  
 * @author: 高宗宝
 * @Description: 线程类，实现核心网质差小区算法
 */
public class HandleCnPiPoorQualityCellThread implements Runnable {
	private ClassPathXmlApplicationContext context;
	private String province;
	private Date date;
	private ActiveMqClient mqclient;
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("正在处理核心网数据，province=" + province + " ,date=" + date);
		CnPi2CnPoorQualityCellServiceImpl bean = context.getBean(CnPi2CnPoorQualityCellServiceImpl.class);
		bean.transCnEcitoCnPoorQuality(province, date);
		//mq发送数据处理完毕
		CnPoorCellTaskDoneMessage message = new CnPoorCellTaskDoneMessage("2018-07-13", "success", "cn", "all");
		mqclient.sendmessageToTopic(CnPiData2CnPoorQualityCellMain.mqConfig.getTopic4Name(), JSON.toJSONString(message));
		System.out.println("处理 province=" + province + " ,date=" + date + " 完成");
	}
	public HandleCnPiPoorQualityCellThread(ClassPathXmlApplicationContext context, String province, Date date, ActiveMqClient mqclient) {
		super();
		this.context = context;
		this.province = province;
		this.date = date;
		this.mqclient = mqclient;
	}

	
}
