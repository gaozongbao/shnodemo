package com.cmdi.util;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.util.Properties;

/**
 * @author: 高宗宝
 * @date: 2019/5/17
 * @version: 1.0
 * @description: someword
 */
public class LogUtil {
    public static void initLog4j(String logPath, String logFileName) {
        Properties prop = new Properties();
        if(logPath == null) {
            prop.setProperty("log4j.rootLogger", "info,stdout");
        } else {
            prop.setProperty("log4j.rootLogger", "info,stdout,ServerDailyRollingFile");
            prop.setProperty("log4j.appender.ServerDailyRollingFile", "org.apache.log4j.DailyRollingFileAppender");
            prop.setProperty("log4j.appender.ServerDailyRollingFile.DatePattern", "'.'yyyy-MM-dd'.bak'");
            prop.setProperty("log4j.appender.ServerDailyRollingFile.File",
                    logPath + File.separator + logFileName);
            prop.setProperty("log4j.appender.ServerDailyRollingFile.layout", "org.apache.log4j.PatternLayout");
            prop.setProperty("log4j.appender.ServerDailyRollingFile.layout.ConversionPattern",
                    "%d{yyyy-MM-dd HH:mm:ss} %p [%c:%L] %m%n");
            prop.setProperty("log4j.appender.ServerDailyRollingFile.encoding","UTF-8");
            prop.setProperty("log4j.appender.ServerDailyRollingFile.Append", "true");
        }
        prop.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
        prop.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.stdout.layout.ConversionPattern", "%d{yyyy-MM-dd HH:mm:ss} %p [%c:%L] %m%n");
        prop.setProperty("log4j.appender.stdout.encoding","UTF-8");
        prop.setProperty("log4j.appender.stdout.Target","System.out");
        PropertyConfigurator.configure(prop);
    }
}
