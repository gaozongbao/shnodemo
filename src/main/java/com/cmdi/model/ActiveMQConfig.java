package com.cmdi.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: ActiveMQConfig 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2018年11月16日
 * @version: 1.0 
 */
@Component("mqconfig")
public class ActiveMQConfig {
	@Value("${activemq.url}")
	private String url;
	
	@Value("${mq.topic0.name}")
	private String topic0Name;
	
	@Value("${topic0.client1}")
	private String topic0client1;
	
	@Value("${topic0.client1.durablesubscribername1}")
	private String topic0client1durablename1;
	
	@Value("${topic0.client1.waittime}")
	private String topic0client1waittime;
	
	@Value("${topic0.client1.maxmessagecount}")
	private String topic0client1maxmessagecount;
	
	@Value("${mq.topic4.name}")
	private String topic4Name;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopic0Name() {
		return topic0Name;
	}
	public void setTopic0Name(String topic0Name) {
		this.topic0Name = topic0Name;
	}
	public String getTopic0client1() {
		return topic0client1;
	}
	public void setTopic0client1(String topic0client1) {
		this.topic0client1 = topic0client1;
	}
	public String getTopic0client1durablename1() {
		return topic0client1durablename1;
	}
	public void setTopic0client1durablename1(String topic0client1durablename1) {
		this.topic0client1durablename1 = topic0client1durablename1;
	}
	public String getTopic0client1waittime() {
		return topic0client1waittime;
	}
	public void setTopic0client1waittime(String topic0client1waittime) {
		this.topic0client1waittime = topic0client1waittime;
	}
	public String getTopic0client1maxmessagecount() {
		return topic0client1maxmessagecount;
	}
	public void setTopic0client1maxmessagecount(String topic0client1maxmessagecount) {
		this.topic0client1maxmessagecount = topic0client1maxmessagecount;
	}
	
	public String getTopic4Name() {
		return topic4Name;
	}
	public void setTopic4Name(String topic4Name) {
		this.topic4Name = topic4Name;
	}
	
}
