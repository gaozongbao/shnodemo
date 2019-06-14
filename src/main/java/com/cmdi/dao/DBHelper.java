package com.cmdi.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.cmdi.model.mro.ScMeasureMasterCell;

/** 
 * @ClassName: DBHelper 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月14日
 * @version: 1.0 
 */
@Configuration
public class DBHelper {
	@Value("${jdbc.mysql.driverClassName}")
	private String driver;
	@Value("${jdbc.mysql.url}")
	private String dburl;
	@Value("${jdbc.mysql.username}")
	private String userName;
	@Value("${jdbc.mysql.password}")
	private String passWd;
	
	private Connection connection;

	public Connection initDB() {
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(dburl, userName, passWd);
			return this.connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConn() {
		if (null != connection)
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//utf8
	public void load(InputStream is, String tableName, String encoder) {
		PreparedStatement preparedStatement  = null;
		String sql = "load data local infile '' " + " ignore into table "+ tableName + " character set "+encoder+ " fields terminated by ',' enclosed by ''";
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
//			StringBuilder sb = new StringBuilder();
//			ByteArrayInputStream is = new ByteArrayInputStream(sb.toString().getBytes());
			((com.mysql.jdbc.Statement) preparedStatement).setLocalInfileInputStream(is);
			int aaa = preparedStatement.executeUpdate();
			connection.commit();
			is.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(is != null)
					is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int load2(ArrayList<ScMeasureMasterCell> arrayList, String tableName, String encoder, String delimiter, String ts) {
		int cnt = 0;
		PreparedStatement preparedStatement  = null;
		String sql = "load data local infile '' " + " ignore into table "+ tableName + " character set "+encoder+ " fields terminated by '"+delimiter+"' enclosed by ''";
		ByteArrayInputStream is = null;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			StringBuilder sb = new StringBuilder();
			for (ScMeasureMasterCell sc : arrayList) {
				sb.append("20190610" + delimiter + 
						ts + delimiter + 
						sc.getCgi() + delimiter + 
						sc.getIndex() + delimiter +
						sc.getEnbId() + delimiter +
						sc.getLteScEarfcn() + delimiter + 
						sc.getLteScPci() + delimiter + 
						sc.getLteScRSRP() + delimiter + 
						sc.getLteScTadv() + delimiter +
						sc.getLteScAOA());
				sb.append("\n");
			}
			is = new ByteArrayInputStream(sb.toString().getBytes());
			((com.mysql.jdbc.Statement) preparedStatement).setLocalInfileInputStream(is);
			cnt = preparedStatement.executeUpdate();
			connection.commit();
			is.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(is != null)
					is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}
}
