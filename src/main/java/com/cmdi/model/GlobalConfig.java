package com.cmdi.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/** 
 * @ClassName: GlobalConfig 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月11日
 * @version: 1.0 
 */
@Configuration
public class GlobalConfig {
	
	@Value("${jdbc.mysql.driverClassName}")
	public String prokectName;
	
	@Value("${project.file.tmpdir}")
	public String dir;

	@Override
	public String toString() {
		return "GlobalConfig [prokectName=" + prokectName + ", dir=" + dir + "]";
	}
	
	

}
