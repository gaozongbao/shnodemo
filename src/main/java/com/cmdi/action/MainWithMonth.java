package com.cmdi.action;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainWithMonth {	
	
	public static void main(String[] args){
		String date = args[0];
		String[] provinceList=args[1].split(",");
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
		SqlSessionTemplate bean = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		
		ExecutorService pool = Executors.newFixedThreadPool(32);
		for(String province:provinceList){
			pool.execute(new PmMrEpProvinceThread(date, province,bean,context));
		}
		
		pool.shutdown();
	}
}
