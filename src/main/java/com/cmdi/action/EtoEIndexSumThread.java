package com.cmdi.action;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmdi.impl.EtoEIndexSumServiceImpl;

public class EtoEIndexSumThread implements Runnable {

	
	private ClassPathXmlApplicationContext context;

	private Date date;
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long time = System.currentTimeMillis();
		System.out.println("正在处理端到端分场景分场景分省市指标汇总:"  + " ,date=" + date);
		EtoEIndexSumServiceImpl bean = context.getBean(EtoEIndexSumServiceImpl.class);
		bean.sum2table(date);
		long costtime = System.currentTimeMillis()-time;
		System.out.println("use time (ms)" + costtime);
		System.out.println("处理端到端分场景分场景分省市指标汇总 date=" + date + " 完成");
	}
	public EtoEIndexSumThread(ClassPathXmlApplicationContext context,  Date date) {
		super();
		this.context = context;
		this.date = date;
	}
}
