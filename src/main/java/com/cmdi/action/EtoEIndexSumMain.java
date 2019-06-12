package com.cmdi.action;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmdi.util.DateUtil;

public class EtoEIndexSumMain {

	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Date date = DateUtil.getDate(args[0], "yyyy-MM-dd");
		System.out.println("处理日期是：" + args[0]);
		pool.execute(new EtoEIndexSumThread(context, date));

		pool.shutdown();
		while(!pool.isTerminated()){}
		System.out.println("EtoE Index Sum task done");
	}
	
}
